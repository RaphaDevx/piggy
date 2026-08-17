#!/usr/bin/env python3
"""
Supercard MCP-Server für Claude Code.

Stellt Claude folgende Tools zur Verfügung:
  - supercard_sync      → Kassenzettel von supercard.ch herunterladen
  - supercard_list      → Lokal gecachte Dateien auflisten
  - supercard_get       → Inhalt einer gecachten Datei lesen
  - supercard_status    → Login-Status + Session-Info

Setup (einmalig):
  1. cp .env.example .env  →  Credentials eintragen
  2. In Claude Code Settings (.claude/settings.json) eintragen:
     {
       "mcpServers": {
         "supercard": {
           "command": "python3",
           "args": ["/home/raphael/Piggy/scraper/supercard_mcp.py"]
         }
       }
     }
  3. Claude Code neu starten
"""

import asyncio
import json
import os
import sys
from datetime import datetime
from pathlib import Path

from dotenv import load_dotenv
from mcp.server.fastmcp import FastMCP

# .env laden (Pfad relativ zu dieser Datei)
SCRAPER_DIR = Path(__file__).parent
load_dotenv(SCRAPER_DIR / ".env")

# Scraper-Modul importieren
sys.path.insert(0, str(SCRAPER_DIR))
from supercard_scraper import (
    run_scraper,
    list_local_receipts,
    list_local_pdfs,
    get_local_receipt_file,
    clear_session,
    check_completeness,
    SESSION_FILE,
    RECEIPTS_DIR,
    PDFS_DIR,
)

mcp = FastMCP("supercard")


# ---------------------------------------------------------------------------
# Tool: Status
# ---------------------------------------------------------------------------

@mcp.tool()
def supercard_status() -> str:
    """
    Zeigt den aktuellen Status: Session vorhanden, Anzahl gecachte Dateien, Credentials gesetzt.
    """
    email = os.getenv("SUPERCARD_EMAIL", "")
    has_creds = bool(email and os.getenv("SUPERCARD_PASSWORD"))
    session_exists = SESSION_FILE.exists()
    session_age = ""
    if session_exists:
        age_sec = (datetime.now().timestamp() - SESSION_FILE.stat().st_mtime)
        hours = int(age_sec // 3600)
        session_age = f" (vor {hours}h gespeichert)"

    files = list_local_receipts()
    pdfs = list_local_pdfs()
    total_receipts = sum(f["count"] for f in files)

    lines = [
        "## Supercard Scraper Status",
        f"- Credentials: {'✅ gesetzt' if has_creds else '❌ fehlen — .env befüllen'}",
        f"- E-Mail: {email if email else '(nicht gesetzt)'}",
        f"- Session: {'✅ vorhanden' + session_age if session_exists else '❌ keine Session → bootstrap nötig'}",
        f"- JSON-Dateien: {len(files)} ({total_receipts} Kassenzettel total)",
        f"- PDFs: {len(pdfs)} heruntergeladen",
        f"- Speicherort: {RECEIPTS_DIR.parent}",
        "",
        "**Nächster Schritt:** `supercard_bootstrap()` für ersten Login (CAPTCHA via VNC lösen)",
    ]
    if files:
        lines.append("\n**Letzte JSON-Dateien:**")
        for f in files[:3]:
            lines.append(f"  - {f['file']} ({f['count']} Einträge)")
    if pdfs:
        lines.append("\n**Letzte PDFs:**")
        for f in pdfs[:3]:
            lines.append(f"  - {f['file']} ({f['size_kb']} KB)")
    return "\n".join(lines)


# ---------------------------------------------------------------------------
# Tool: Sync (Scraper ausführen)
# ---------------------------------------------------------------------------

@mcp.tool()
def supercard_bootstrap() -> str:
    """
    Startet einen VNC-Browser-Session für den ersten Login (CAPTCHA-Lösung).
    Xvfb + x11vnc: Browser läuft auf virtuellem Display, per VNC von Windows aus sichtbar.
    Nach erfolgreichem Login werden Cookies gespeichert — danach laufen alle Sync-Aufrufe headless.
    """
    email = os.getenv("SUPERCARD_EMAIL", "")
    password = os.getenv("SUPERCARD_PASSWORD", "")
    if not email or not password:
        return "❌ Credentials fehlen — .env befüllen."

    try:
        result = asyncio.run(
            run_scraper(email, password, headless=False, debug=False, download_pdfs=False)
        )
        return (
            "✅ Bootstrap abgeschlossen — Session gespeichert.\n"
            "Ab jetzt läuft `supercard_sync()` vollautomatisch headless."
        )
    except Exception as e:
        return f"❌ Bootstrap-Fehler:\n{e}"


@mcp.tool()
def supercard_sync(
    from_date: str = "",
    to_date: str = "",
    download_pdfs: bool = True,
    debug: bool = False,
    clear_session_first: bool = False,
) -> str:
    """
    Lädt Kassenzettel von supercard.ch herunter (headless mit gespeicherter Session).
    Für ersten Login zuerst supercard_bootstrap() aufrufen.

    Args:
        from_date: Nur ab diesem Datum herunterladen (Format: YYYY-MM-DD, leer = alle)
        to_date:   Nur bis zu diesem Datum (Format: YYYY-MM-DD, leer = heute)
        download_pdfs: PDFs der Kassenzettel herunterladen (Standard: True)
        debug: HTML-Dumps speichern für Selector-Analyse
        clear_session_first: Session löschen und neu einloggen
    """
    from datetime import date as dateclass

    email = os.getenv("SUPERCARD_EMAIL", "")
    password = os.getenv("SUPERCARD_PASSWORD", "")
    if not email or not password:
        return "❌ Credentials fehlen — .env befüllen."

    if clear_session_first:
        clear_session()

    fd = dateclass.fromisoformat(from_date) if from_date else None
    td = dateclass.fromisoformat(to_date) if to_date else None

    try:
        result = asyncio.run(
            run_scraper(email, password, headless=True, debug=debug,
                        from_date=fd, to_date=td, download_pdfs=download_pdfs)
        )
    except RuntimeError as e:
        return f"❌ Fehler:\n{e}"
    except Exception as e:
        return f"❌ Unerwarteter Fehler:\n{type(e).__name__}: {e}"

    receipts = result.get("receipts", [])
    pdfs = result.get("pdfs", [])
    check = result.get("check", {})

    if not receipts:
        return (
            "⚠️ Keine Kassenzettel gefunden.\n\n"
            "Falls noch kein Login: `supercard_bootstrap()` aufrufen.\n"
            "Falls Session abgelaufen: `supercard_sync(clear_session_first=True)`"
        )

    vollst = check.get("vollstaendig")
    vollst_icon = "✅" if vollst else ("⚠️ unvollständig" if vollst is False else "❓")

    lines = [
        f"✅ **{len(receipts)} Kassenzettel verarbeitet**",
        f"📄 **{len(pdfs)} PDFs heruntergeladen** — {vollst_icon}",
    ]
    if from_date or to_date:
        lines.append(f"📅 Zeitraum: {from_date or '∞'} → {to_date or '∞'}")
    lines += ["", "Mit `supercard_check()` Vollständigkeit prüfen."]
    return "\n".join(lines)


# ---------------------------------------------------------------------------
# Tool: Vollständigkeitsprüfung
# ---------------------------------------------------------------------------

@mcp.tool()
def supercard_check(expected_total: int = 0) -> str:
    """
    Prüft ob alle Kassenzettel vollständig heruntergeladen wurden.
    Vergleicht Anzahl PDFs mit Anzahl gefundener Einträge in JSON.

    Args:
        expected_total: Erwartete Gesamtzahl (0 = aus JSON ermitteln)
    """
    pdfs = list_local_pdfs()
    files = list_local_receipts()
    total_in_json = sum(f["count"] for f in files)
    ref = expected_total or total_in_json

    vollst = len(pdfs) >= ref if ref > 0 else None
    icon = "✅" if vollst else ("⚠️" if vollst is False else "❓")

    lines = [
        f"## {icon} Vollständigkeitsprüfung",
        f"- Kassenzettel in JSON: {total_in_json}",
        f"- PDFs heruntergeladen: {len(pdfs)}",
        f"- Erwartet: {ref if ref else 'unbekannt'}",
        f"- Status: {'vollständig' if vollst else 'unvollständig' if vollst is False else 'unbekannt'}",
    ]
    if vollst is False:
        fehlend = ref - len(pdfs)
        lines.append(f"\n⚠️ **{fehlend} PDFs fehlen** — nochmal `supercard_sync(download_pdfs=True)` aufrufen")
    if pdfs:
        lines.append(f"\n**Letzte PDFs:**")
        for f in pdfs[:5]:
            lines.append(f"  - {f['file']} ({f['size_kb']} KB)")
    return "\n".join(lines)


# ---------------------------------------------------------------------------
# Tool: Liste
# ---------------------------------------------------------------------------

@mcp.tool()
def supercard_list() -> str:
    """Listet alle lokal gespeicherten Kassenzettel-JSON-Dateien und PDFs auf."""
    files = list_local_receipts()
    pdfs = list_local_pdfs()

    lines = []
    if files:
        lines.append(f"📁 **{len(files)} JSON-Dateien** ({sum(f['count'] for f in files)} Einträge):")
        for f in files[:5]:
            lines.append(f"  - `{f['file']}` — {f['count']} Einträge, {f['modified'][:16]}")
    else:
        lines.append("📭 Noch keine JSON-Dateien — zuerst `supercard_sync()` aufrufen.")

    lines.append("")
    if pdfs:
        lines.append(f"📄 **{len(pdfs)} PDFs** in scraper/pdfs/:")
        for f in pdfs[:5]:
            lines.append(f"  - {f['file']} ({f['size_kb']} KB)")
    else:
        lines.append("📭 Noch keine PDFs — `supercard_sync(download_pdfs=True)` aufrufen.")

    return "\n".join(lines)


# ---------------------------------------------------------------------------
# Tool: Inhalt lesen
# ---------------------------------------------------------------------------

@mcp.tool()
def supercard_get(filename: str, limit: int = 50) -> str:
    """
    Liest den Inhalt einer gespeicherten Kassenzettel-Datei.

    Args:
        filename: Dateiname aus supercard_list() (z.B. 'supercard_20240315_142301.json')
        limit: Maximale Anzahl Einträge (Standard: 50, für Übersicht)
    """
    data = get_local_receipt_file(filename)
    if data is None:
        return f"❌ Datei nicht gefunden: {filename}\nVerfügbare Dateien mit `supercard_list()` anzeigen."

    if isinstance(data, list):
        subset = data[:limit]
        result = json.dumps(subset, ensure_ascii=False, indent=2)
        suffix = f"\n\n(Zeige {len(subset)} von {len(data)} Einträgen)" if len(data) > limit else ""
        return f"```json\n{result}\n```{suffix}"
    else:
        return f"```json\n{json.dumps(data, ensure_ascii=False, indent=2)}\n```"


# ---------------------------------------------------------------------------
# Tool: Session löschen
# ---------------------------------------------------------------------------

@mcp.tool()
def supercard_clear_session() -> str:
    """
    Löscht die gespeicherte Session — nächster Sync erzwingt neuen Login.
    Nützlich wenn der Login nicht mehr funktioniert.
    """
    clear_session()
    return "✅ Session gelöscht. Nächster `supercard_sync()` startet frischen Login."


# ---------------------------------------------------------------------------
# Server starten
# ---------------------------------------------------------------------------

if __name__ == "__main__":
    mcp.run()
