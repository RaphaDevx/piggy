#!/usr/bin/env python3
"""
Supercard.ch Kassenzettel-Scraper für Piggy App.

Strategien gegen DataDome CAPTCHA:
  1. Stealth-Modus: Browser tarnt sich als echter Nutzer (playwright-stealth)
  2. Bootstrap: Einmaliger manueller Login via Xvfb (virtual display) → Cookies speichern
     Danach läuft alles headless mit gespeicherter Session

Features:
  - PDF-Download aller Kassenzettel (oder Zeitraum-Filter)
  - Vollständigkeitsprüfung: Anzahl auf Website vs. heruntergeladene PDFs
  - Session-Persistenz: Login nur beim ersten Mal nötig

Verwendung:
  python supercard_scraper.py                          # Headless mit gespeicherter Session
  python supercard_scraper.py --bootstrap              # Einmaliger manueller Login (Xvfb)
  python supercard_scraper.py --debug                  # HTML-Dumps für Selector-Analyse
  python supercard_scraper.py --from 2024-01-01        # Nur ab diesem Datum
  python supercard_scraper.py --from 2024-01-01 --to 2024-12-31
  python supercard_scraper.py --check                  # Nur Vollständigkeit prüfen
  python supercard_scraper.py --clear-session          # Session löschen → neu einloggen
"""

import asyncio
import json
import math
import os
import random
import re
import subprocess
import sys
import time
from datetime import datetime, date
from pathlib import Path
from typing import Optional

from dotenv import load_dotenv
from playwright.async_api import BrowserContext, Frame, Page, Response, async_playwright

try:
    from playwright_stealth import Stealth
    HAS_STEALTH = True
except ImportError:
    HAS_STEALTH = False

SCRAPER_DIR = Path(__file__).parent
load_dotenv(SCRAPER_DIR / ".env")

RECEIPTS_DIR = SCRAPER_DIR / "receipts"
PDFS_DIR = SCRAPER_DIR / "pdfs"
DEBUG_DIR = SCRAPER_DIR / "debug"
SESSION_FILE = SCRAPER_DIR / ".session.json"

for d in (RECEIPTS_DIR, PDFS_DIR, DEBUG_DIR):
    d.mkdir(exist_ok=True)

LOGIN_URL = "https://www.supercard.ch/de/login.html"
PURCHASES_URL = "https://www.supercard.ch/de/app-digitale-services/meine-einkaeufe.html"


# ---------------------------------------------------------------------------
# Session
# ---------------------------------------------------------------------------

async def load_session(context: BrowserContext) -> bool:
    if not SESSION_FILE.exists():
        return False
    try:
        data = json.loads(SESSION_FILE.read_text())
        await context.add_cookies(data)
        return True
    except Exception:
        return False


async def save_session(context: BrowserContext) -> None:
    cookies = await context.cookies()
    SESSION_FILE.write_text(json.dumps(cookies, ensure_ascii=False))
    SESSION_FILE.chmod(0o600)
    print("  💾 Session gespeichert")


def clear_session() -> None:
    if SESSION_FILE.exists():
        SESSION_FILE.unlink()
        print("🗑️  Session gelöscht")


# ---------------------------------------------------------------------------
# Xvfb (Virtual Display für headed Mode auf Server ohne Monitor)
# ---------------------------------------------------------------------------

VNC_PORT = 5999


class VirtualDisplay:
    """Xvfb + x11vnc: Browser läuft headless auf virtualem Display, VNC macht es sichtbar."""

    def __init__(self, display_num: int = 99, vnc_port: int = VNC_PORT):
        self.display_num = display_num
        self.display = f":{display_num}"
        self.vnc_port = vnc_port
        self._xvfb: Optional[subprocess.Popen] = None
        self._vnc: Optional[subprocess.Popen] = None

    def start(self) -> bool:
        # Xvfb starten
        try:
            self._xvfb = subprocess.Popen(
                ["Xvfb", self.display, "-screen", "0", "1280x900x24", "-ac"],
                stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL,
            )
            time.sleep(1)
            os.environ["DISPLAY"] = self.display
        except FileNotFoundError:
            print("  ⚠️  Xvfb nicht gefunden: sudo apt install xvfb")
            return False

        # x11vnc starten (kein Passwort — nur lokal via SSH-Tunnel sicher)
        try:
            self._vnc = subprocess.Popen(
                [
                    "x11vnc", "-display", self.display,
                    "-nopw",           # kein Passwort (Tunnel-Schutz reicht)
                    "-port", str(self.vnc_port),
                    "-forever",        # bleibt offen nach erstem Client
                    "-quiet",
                    "-bg",             # Hintergrund
                ],
                stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL,
            )
            time.sleep(1)
        except FileNotFoundError:
            print("  ⚠️  x11vnc nicht gefunden: sudo apt install x11vnc")

        # Verbindungs-Anleitung
        import socket
        hostname = socket.gethostname()
        print()
        print("  ┌─────────────────────────────────────────────────────────┐")
        print("  │  🖥️  VNC-BOOTSTRAP AKTIV                                │")
        print("  │                                                         │")
        print("  │  Verbinde von Windows mit einem VNC-Viewer:             │")
        print(f"  │  SSH-Tunnel:  ssh -L {self.vnc_port}:localhost:{self.vnc_port} raphael@{hostname:<15}│")
        print(f"  │  VNC-Adresse: localhost:{self.vnc_port}                              │")
        print("  │  (TigerVNC, RealVNC oder MobaXterm empfohlen)          │")
        print("  │                                                         │")
        print("  │  → CAPTCHA lösen → Einloggen → warten bis \"✅ Session\"  │")
        print("  └─────────────────────────────────────────────────────────┘")
        print()
        return True

    def stop(self) -> None:
        for proc in (self._vnc, self._xvfb):
            if proc:
                try:
                    proc.terminate()
                except Exception:
                    pass
        self._vnc = None
        self._xvfb = None


# ---------------------------------------------------------------------------
# Debug-Helpers
# ---------------------------------------------------------------------------

async def screenshot(page: Page, name: str) -> Path:
    path = DEBUG_DIR / f"{datetime.now().strftime('%H%M%S')}_{name}.png"
    await page.screenshot(path=str(path), full_page=True)
    print(f"  📸 {path.name}")
    return path


async def dump_html(page: Page, name: str = "page") -> None:
    html = await page.content()
    (DEBUG_DIR / f"{name}.html").write_text(html, encoding="utf-8")
    print(f"  📄 HTML-Dump: debug/{name}.html")


def is_datadome(html: str) -> bool:
    return "captcha-delivery.com" in html or "datadome" in html.lower()


# ---------------------------------------------------------------------------
# DataDome Slider-CAPTCHA Solver
# ---------------------------------------------------------------------------

async def _human_drag(page: Page, start_x: float, start_y: float, end_x: float, end_y: float) -> None:
    """Simuliert eine menschliche Drag-Bewegung mit Easing und Rauschen."""
    await page.mouse.move(start_x + random.uniform(-3, 3), start_y + random.uniform(-2, 2))
    await asyncio.sleep(random.uniform(0.15, 0.30))
    await page.mouse.down()
    await asyncio.sleep(random.uniform(0.05, 0.12))

    steps = random.randint(28, 40)
    for i in range(steps + 1):
        t = i / steps
        # Ease-in-out cubic
        eased = 4 * t * t * t if t < 0.5 else 1 - (-2 * t + 2) ** 3 / 2
        x = start_x + (end_x - start_x) * eased + random.uniform(-1.5, 1.5)
        y = start_y + math.sin(t * math.pi) * random.uniform(-3, 3)
        await page.mouse.move(x, y)
        # Langsamer am Anfang und Ende, schneller in der Mitte
        delay = random.uniform(8, 18) if 0.2 < t < 0.8 else random.uniform(20, 45)
        await asyncio.sleep(delay / 1000)

    await page.mouse.up()
    await asyncio.sleep(random.uniform(0.4, 0.8))


async def solve_datadome_captcha(page: Page, debug: bool = False) -> bool:
    """
    Versucht den DataDome Slider-CAPTCHA automatisch zu lösen.
    Gibt True zurück wenn erfolgreich, False wenn nicht lösbar.
    """
    print("  🤖 Versuche CAPTCHA automatisch zu lösen...")

    # Captcha-Frame finden
    captcha_frame: Optional[Frame] = None
    for attempt in range(8):
        for frame in page.frames:
            if "captcha-delivery.com" in frame.url:
                captcha_frame = frame
                break
        if captcha_frame:
            break
        await asyncio.sleep(0.5)

    if not captcha_frame:
        print("  ⚠️  Kein CAPTCHA-iframe gefunden")
        return False

    await asyncio.sleep(1.5)  # Iframe vollständig laden

    # Slider-Handle finden (der linke Pfeil-Button)
    slider = None
    for sel in [
        "div.dd-slider-handle",
        "[class*='slider-handle']",
        "[class*='slider'] button",
        "div[role='button']",
        "button:first-of-type",
        "div[tabindex='0']",
    ]:
        try:
            el = await captcha_frame.query_selector(sel)
            if el and await el.is_visible():
                slider = el
                print(f"  🎯 Slider gefunden: {sel}")
                break
        except Exception:
            pass

    if not slider:
        # Fallback: Screenshot machen und Koordinaten schätzen
        if debug:
            await screenshot(page, "captcha_no_slider")
        print("  ⚠️  Slider-Element nicht gefunden — versuche Koordinaten-Schätzung")

        # Aus Screenshot: Slider ist ca. bei x=530, y=370 im 1280x900 Viewport
        # (Mitte des linken Pfeils im iframe)
        iframe_el = await page.query_selector("iframe[src*='captcha']")
        if not iframe_el:
            return False
        box = await iframe_el.bounding_box()
        if not box:
            return False
        # Slider ist ca. 37% von links, 76% von oben im iframe
        start_x = box["x"] + box["width"] * 0.37
        start_y = box["y"] + box["height"] * 0.76
        end_x = box["x"] + box["width"] * 0.90
        end_y = start_y
        await _human_drag(page, start_x, start_y, end_x, end_y)
    else:
        box = await slider.bounding_box()
        if not box:
            return False

        # Track-Breite ermitteln (Parent-Element)
        track_width = 300  # Standardwert
        try:
            track = await captcha_frame.query_selector("[class*='track'], [class*='bar'], [class*='slider-container']")
            if track:
                tb = await track.bounding_box()
                if tb:
                    track_width = tb["width"] - box["width"]
        except Exception:
            pass

        # Iframe-Offset berücksichtigen
        iframe_el = await page.query_selector("iframe[src*='captcha']")
        offset_x, offset_y = 0.0, 0.0
        if iframe_el:
            ib = await iframe_el.bounding_box()
            if ib:
                offset_x, offset_y = ib["x"], ib["y"]

        start_x = offset_x + box["x"] + box["width"] / 2
        start_y = offset_y + box["y"] + box["height"] / 2
        end_x = start_x + track_width * 0.95

        await _human_drag(page, start_x, start_y, end_x, start_y)

    if debug:
        await screenshot(page, "captcha_after_drag")

    # Prüfen ob CAPTCHA gelöst (Seite ändert sich)
    await asyncio.sleep(2)
    html = await page.content()
    if not is_datadome(html):
        print("  ✅ CAPTCHA gelöst!")
        return True

    # Zweiter Versuch mit leicht anderen Koordinaten
    print("  🔄 Zweiter Versuch...")
    await asyncio.sleep(1)
    return False


# ---------------------------------------------------------------------------
# Login
# ---------------------------------------------------------------------------

async def dismiss_cookie_banner(page: Page) -> None:
    for sel in ["#onetrust-accept-btn-handler", "button[id*='accept']", "button[class*='accept']"]:
        try:
            await page.click(sel, timeout=2000)
            print("  🍪 Cookie-Banner akzeptiert")
            return
        except Exception:
            pass


async def login(page: Page, email: str, password: str, headed: bool, debug: bool) -> None:
    print("  🔐 Anmelden bei supercard.ch...")
    await page.goto(LOGIN_URL, wait_until="domcontentloaded", timeout=30000)
    await page.wait_for_load_state("networkidle", timeout=15000)

    html = await page.content()

    if is_datadome(html):
        # Zuerst automatisch versuchen (bis zu 3 Versuche)
        solved = False
        for attempt in range(3):
            print(f"  🤖 CAPTCHA-Versuch {attempt + 1}/3...")
            solved = await solve_datadome_captcha(page, debug)
            if solved:
                break
            await asyncio.sleep(random.uniform(1.5, 3.0))
            # Seite neu laden für nächsten Versuch
            if attempt < 2:
                html = await page.content()
                if not is_datadome(html):
                    solved = True
                    break

        if not solved:
            if headed:
                # Headed: auf manuelle Lösung warten
                print("  ⚠️  Auto-Lösung fehlgeschlagen → bitte CAPTCHA manuell lösen")
                print("  ⏳ Warte bis zu 3 Minuten auf manuelle Lösung...")
                try:
                    # Warten bis Seite kein CAPTCHA mehr hat
                    await page.wait_for_function(
                        "!document.body.innerHTML.includes('captcha-delivery')",
                        timeout=180000,
                    )
                    print("  ✅ CAPTCHA manuell gelöst")
                except Exception:
                    pass
            else:
                raise RuntimeError(
                    "DataDome CAPTCHA konnte nicht automatisch gelöst werden.\n"
                    "Lösung: python supercard_scraper.py --bootstrap\n"
                    "(Einmaliger manueller Login via VNC, danach alles headless)"
                )

    await dismiss_cookie_banner(page)

    if debug:
        await screenshot(page, "login_page")

    # E-Mail-Feld
    email_selectors = [
        'input[type="email"]', 'input[name="email"]', 'input[name="username"]',
        "#email", "#username", 'input[autocomplete="email"]',
        'input[placeholder*="E-Mail"]', 'input[placeholder*="email"]',
    ]
    for sel in email_selectors:
        try:
            await page.fill(sel, email, timeout=2000)
            print(f"  ✍️  E-Mail eingetragen")
            break
        except Exception:
            pass
    else:
        if debug:
            await dump_html(page, "login_no_email_field")
        await screenshot(page, "error_no_email_field")
        raise RuntimeError("Kein E-Mail-Feld gefunden. debug/login_no_email_field.html prüfen.")

    # Passwort-Feld
    for sel in ['input[type="password"]', 'input[name="password"]', "#password"]:
        try:
            await page.fill(sel, password, timeout=2000)
            break
        except Exception:
            pass

    # Submit
    for sel in ['button[type="submit"]', 'input[type="submit"]', "button[class*='login']"]:
        try:
            await page.click(sel, timeout=2000)
            break
        except Exception:
            pass
    else:
        await page.keyboard.press("Enter")

    # Warten
    try:
        await page.wait_for_url("**supercard**", timeout=15000)
    except Exception:
        pass

    html = await page.content()

    # 2FA
    body = await page.inner_text("body")
    if any(kw in body.lower() for kw in ["bestätigungscode", "verification code", "otp", "sms-code", "tan"]):
        print("  ⚠️  2FA erkannt — bitte Code eingeben (90s Timeout)...")
        if debug:
            await screenshot(page, "2fa_page")
        try:
            await page.wait_for_url("**/meine**", timeout=90000)
        except Exception:
            raise RuntimeError("2FA-Timeout. Mit --bootstrap nochmal versuchen.")

    if "login" in page.url.lower():
        if debug:
            await screenshot(page, "login_failed")
        raise RuntimeError("Login fehlgeschlagen. Credentials prüfen.")

    print("  ✅ Login erfolgreich")


async def ensure_logged_in(
    page: Page, context: BrowserContext, email: str, password: str,
    headed: bool, debug: bool
) -> None:
    session_loaded = await load_session(context)

    if session_loaded:
        await page.goto(PURCHASES_URL, wait_until="domcontentloaded", timeout=30000)
        await page.wait_for_load_state("networkidle", timeout=10000)
        if "login" not in page.url.lower():
            print("  ✅ Session aus Cache")
            return
        print("  ⏰ Session abgelaufen — neu anmelden")

    await login(page, email, password, headed, debug)
    await save_session(context)


# ---------------------------------------------------------------------------
# API-Interceptor
# ---------------------------------------------------------------------------

def make_api_interceptor(captured: list):
    async def handler(response: Response):
        url = response.url
        if not any(k in url.lower() for k in ["purchase", "receipt", "einkauf", "transaction", "order", "bon"]):
            return
        if "json" not in response.headers.get("content-type", ""):
            return
        try:
            data = await response.json()
            captured.append({"url": url, "data": data})
            print(f"  🎯 API: {url}")
        except Exception:
            pass
    return handler


# ---------------------------------------------------------------------------
# Datum-Parsing
# ---------------------------------------------------------------------------

MONTH_MAP = {
    "jan": 1, "feb": 2, "mär": 3, "mar": 3, "apr": 4, "mai": 5, "may": 5,
    "jun": 6, "jul": 7, "aug": 8, "sep": 9, "okt": 10, "oct": 10, "nov": 11,
    "dez": 12, "dec": 12,
}


def parse_date(text: str) -> Optional[date]:
    # "15. März 2024" oder "15.03.2024" oder "2024-03-15"
    m = re.search(r"(\d{4})-(\d{2})-(\d{2})", text)
    if m:
        return date(int(m.group(1)), int(m.group(2)), int(m.group(3)))

    m = re.search(r"(\d{1,2})\.(\d{2})\.(\d{4})", text)
    if m:
        return date(int(m.group(3)), int(m.group(2)), int(m.group(1)))

    m = re.search(r"(\d{1,2})\.\s*(\w+)\s+(\d{4})", text)
    if m:
        month_str = m.group(2).lower()[:3]
        month = MONTH_MAP.get(month_str)
        if month:
            return date(int(m.group(3)), month, int(m.group(1)))

    return None


def in_date_range(d: Optional[date], from_date: Optional[date], to_date: Optional[date]) -> bool:
    if d is None:
        return True  # unbekanntes Datum: nicht ausschliessen
    if from_date and d < from_date:
        return False
    if to_date and d > to_date:
        return False
    return True


# ---------------------------------------------------------------------------
# PDF-Download
# ---------------------------------------------------------------------------

def pdf_filename(receipt: dict, index: int) -> str:
    """Generiert eindeutigen Dateinamen für PDF."""
    date_str = ""
    if receipt.get("date"):
        date_str = receipt["date"].replace("-", "") + "_"
    elif receipt.get("date_raw"):
        d = parse_date(receipt["date_raw"])
        date_str = d.strftime("%Y%m%d") + "_" if d else ""

    store = re.sub(r"[^a-zA-Z0-9äöüÄÖÜ]", "_", receipt.get("store_name", "unknown"))[:20]
    amount = str(receipt.get("total_amount", "")).replace(".", "-")
    return f"{date_str}{store}_{amount}_{index:04d}.pdf"


async def download_pdf(page: Page, receipt: dict, index: int, debug: bool) -> Optional[Path]:
    """Versucht PDF für einen Kassenzettel herunterzuladen."""
    pdf_url = receipt.get("pdf_url") or receipt.get("pdfUrl") or receipt.get("downloadUrl")

    if pdf_url:
        # Direkter PDF-Download via URL
        try:
            response = await page.request.get(pdf_url)
            if response.ok:
                filename = pdf_filename(receipt, index)
                path = PDFS_DIR / filename
                path.write_bytes(await response.body())
                return path
        except Exception as e:
            if debug:
                print(f"    ⚠️  PDF-Download fehlgeschlagen ({pdf_url}): {e}")

    # Fallback: Detail-Seite öffnen und PDF-Link suchen
    detail_url = receipt.get("detail_url") or receipt.get("detailUrl") or receipt.get("link")
    if detail_url:
        try:
            await page.goto(detail_url, wait_until="domcontentloaded", timeout=15000)
            # PDF-Link suchen
            for sel in ["a[href*='.pdf']", "a[href*='download']", "button[class*='pdf']", "a[class*='pdf']"]:
                try:
                    el = await page.query_selector(sel)
                    if el:
                        href = await el.get_attribute("href")
                        if href:
                            resp = await page.request.get(href)
                            if resp.ok:
                                filename = pdf_filename(receipt, index)
                                path = PDFS_DIR / filename
                                path.write_bytes(await resp.body())
                                return path
                except Exception:
                    pass
        except Exception:
            pass

    return None


# ---------------------------------------------------------------------------
# Scraping
# ---------------------------------------------------------------------------

async def scrape_receipts_from_api(captured_api: list) -> list[dict]:
    receipts = []
    for entry in captured_api:
        data = entry["data"]
        items = []
        if isinstance(data, list):
            items = data
        elif isinstance(data, dict):
            for key in ("purchases", "receipts", "items", "data", "transactions", "orders", "content"):
                if isinstance(data.get(key), list):
                    items = data[key]
                    break
            else:
                items = [data]
        for item in items:
            if isinstance(item, dict):
                item["_api_url"] = entry["url"]
                item["scraped_at"] = datetime.now().isoformat()
                receipts.append(item)
    return receipts


async def scrape_receipts_dom(page: Page, debug: bool) -> list[dict]:
    print("  🔍 DOM-Scraping...")

    candidate_selectors = [
        "[class*='purchase']", "[class*='receipt']", "[class*='einkauf']",
        "[class*='transaction']", "[class*='bon']", "li[class*='item']",
    ]
    found_sel = None
    for sel in candidate_selectors:
        try:
            await page.wait_for_selector(sel, timeout=5000)
            items = await page.query_selector_all(sel)
            if items:
                found_sel = sel
                print(f"  ✅ Selector: {sel} ({len(items)} Elemente)")
                break
        except Exception:
            pass

    if not found_sel:
        if debug:
            await dump_html(page, "receipts_no_selector")
        print("  ⚠️  Keine Elemente gefunden — debug/receipts_no_selector.html prüfen")
        return []

    # Infinite Scroll
    prev = 0
    for _ in range(30):
        await page.evaluate("window.scrollTo(0, document.body.scrollHeight)")
        await page.wait_for_timeout(1200)
        items = await page.query_selector_all(found_sel)
        if len(items) == prev:
            break
        prev = len(items)
        print(f"    ↕ {len(items)} geladen...", end="\r")
    print()

    items = await page.query_selector_all(found_sel)
    receipts = []
    for i, el in enumerate(items):
        try:
            text = await el.inner_text()
            lines = [l.strip() for l in text.splitlines() if l.strip()]
            receipt: dict = {"source": "dom", "index": i, "raw_lines": lines, "scraped_at": datetime.now().isoformat()}
            for line in lines:
                if re.search(r"\d{1,2}[\.\s](?:jan|feb|mär|mar|apr|mai|jun|jul|aug|sep|okt|oct|nov|dez|dec|\d{2})", line, re.I):
                    receipt.setdefault("date_raw", line)
                if re.search(r"(\d+[.,]\d{2})\s*CHF|CHF\s*(\d+[.,]\d{2})", line, re.I):
                    receipt.setdefault("total_raw", line)
                    m = re.search(r"(\d+[.,]\d{2})", line)
                    if m:
                        receipt["total_amount"] = float(m.group(1).replace(",", "."))
            # Link suchen
            link_el = await el.query_selector("a[href]")
            if link_el:
                href = await link_el.get_attribute("href")
                if href:
                    receipt["detail_url"] = href if href.startswith("http") else f"https://www.supercard.ch{href}"
            receipts.append(receipt)
        except Exception as e:
            if len(receipts) > 0:
                pass
    return receipts


# ---------------------------------------------------------------------------
# Vollständigkeitsprüfung
# ---------------------------------------------------------------------------

def check_completeness(receipts_total: int, from_date: Optional[date], to_date: Optional[date]) -> dict:
    pdfs = list(PDFS_DIR.glob("*.pdf"))
    jsons = list(RECEIPTS_DIR.glob("*.json"))

    total_in_json = 0
    for f in jsons:
        try:
            data = json.loads(f.read_text(encoding="utf-8"))
            if isinstance(data, list):
                total_in_json += len(data)
        except Exception:
            pass

    return {
        "receipts_gefunden": receipts_total,
        "pdfs_heruntergeladen": len(pdfs),
        "json_eintraege": total_in_json,
        "vollstaendig": len(pdfs) >= receipts_total if receipts_total > 0 else None,
        "zeitraum": {
            "von": str(from_date) if from_date else "unbegrenzt",
            "bis": str(to_date) if to_date else "unbegrenzt",
        },
    }


# ---------------------------------------------------------------------------
# Hauptfunktion
# ---------------------------------------------------------------------------

async def run_scraper(
    email: str,
    password: str,
    headless: bool = True,
    debug: bool = False,
    from_date: Optional[date] = None,
    to_date: Optional[date] = None,
    only_check: bool = False,
    download_pdfs: bool = True,
) -> dict:
    captured_api: list[dict] = []
    vdisplay: Optional[VirtualDisplay] = None

    if not headless and not os.environ.get("DISPLAY"):
        vdisplay = VirtualDisplay()
        if not vdisplay.start():
            print("  ⚠️  Kein Display — laufe headless")
            headless = True

    async with async_playwright() as p:
        browser = await p.chromium.launch(
            headless=headless,
            args=[
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage",
            ],
        )
        context = await browser.new_context(
            user_agent=(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                "AppleWebKit/537.36 (KHTML, like Gecko) "
                "Chrome/124.0.0.0 Safari/537.36"
            ),
            locale="de-CH",
            timezone_id="Europe/Zurich",
            viewport={"width": 1280, "height": 900},
        )

        page = await context.new_page()

        if HAS_STEALTH:
            stealth = Stealth(navigator_languages=False, navigator_user_agent=False)
            await stealth.apply_stealth_async(page)
            print("  🥷 Stealth-Modus aktiv")

        page.on("response", make_api_interceptor(captured_api))

        try:
            await ensure_logged_in(page, context, email, password, not headless, debug)

            if "meine-einkaeufe" not in page.url:
                await page.goto(PURCHASES_URL, wait_until="domcontentloaded", timeout=30000)
                await page.wait_for_load_state("networkidle", timeout=15000)

            if debug:
                await screenshot(page, "purchases_page")

            await page.wait_for_timeout(3000)  # SPA-Ladezeit

            # Receipts extrahieren
            if captured_api:
                print(f"  🎯 {len(captured_api)} API-Antworten abgefangen")
                receipts = await scrape_receipts_from_api(captured_api)
            else:
                receipts = await scrape_receipts_dom(page, debug)

            # Datum-Filter
            if from_date or to_date:
                before = len(receipts)
                receipts = [
                    r for r in receipts
                    if in_date_range(
                        parse_date(r.get("date_raw") or r.get("date") or r.get("purchaseDate") or ""),
                        from_date, to_date
                    )
                ]
                print(f"  📅 Datum-Filter: {before} → {len(receipts)} Kassenzettel")

            if not receipts:
                print("  ⚠️  Keine Kassenzettel gefunden")
                if not debug:
                    print("  💡 Tipp: --debug für HTML-Analyse, --bootstrap wenn CAPTCHA-Probleme")
                return {"receipts": [], "pdfs": [], "check": {}}

            # JSON speichern
            ts = datetime.now().strftime("%Y%m%d_%H%M%S")
            json_file = RECEIPTS_DIR / f"supercard_{ts}.json"
            json_file.write_text(json.dumps(receipts, ensure_ascii=False, indent=2), encoding="utf-8")
            print(f"\n  💾 {len(receipts)} Kassenzettel → receipts/{json_file.name}")

            # PDF-Download
            downloaded_pdfs = []
            if download_pdfs:
                print(f"\n  📄 PDF-Download ({len(receipts)} Kassenzettel)...")
                for i, receipt in enumerate(receipts):
                    pdf_path = await download_pdf(page, receipt, i, debug)
                    if pdf_path:
                        downloaded_pdfs.append(str(pdf_path))
                        print(f"  ✅ [{i+1}/{len(receipts)}] {pdf_path.name}")
                    else:
                        store = receipt.get("store_name") or receipt.get("storeName") or f"#{i}"
                        print(f"  ⚠️  [{i+1}/{len(receipts)}] {store} — kein PDF-Link gefunden")

            check = check_completeness(len(receipts), from_date, to_date)

            return {"receipts": receipts, "pdfs": downloaded_pdfs, "check": check}

        except Exception as e:
            await screenshot(page, "fatal_error")
            raise
        finally:
            await browser.close()
            if vdisplay:
                vdisplay.stop()


# ---------------------------------------------------------------------------
# Hilfsfunktionen (für MCP-Server)
# ---------------------------------------------------------------------------

def list_local_receipts() -> list[dict]:
    files = sorted(RECEIPTS_DIR.glob("*.json"), reverse=True)
    result = []
    for f in files:
        try:
            data = json.loads(f.read_text(encoding="utf-8"))
            result.append({
                "file": f.name,
                "count": len(data) if isinstance(data, list) else 1,
                "modified": datetime.fromtimestamp(f.stat().st_mtime).isoformat(),
            })
        except Exception:
            pass
    return result


def list_local_pdfs() -> list[dict]:
    pdfs = sorted(PDFS_DIR.glob("*.pdf"), reverse=True)
    return [{"file": f.name, "size_kb": round(f.stat().st_size / 1024, 1)} for f in pdfs]


def get_local_receipt_file(filename: str) -> Optional[list | dict]:
    path = RECEIPTS_DIR / filename
    if not path.exists():
        return None
    return json.loads(path.read_text(encoding="utf-8"))


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------

async def main() -> None:
    args = sys.argv[1:]

    if "--clear-session" in args:
        clear_session()
        if args == ["--clear-session"]:
            return

    email = os.getenv("SUPERCARD_EMAIL", "")
    password = os.getenv("SUPERCARD_PASSWORD", "")

    if not email or not password:
        print("❌  SUPERCARD_EMAIL + SUPERCARD_PASSWORD in scraper/.env eintragen")
        sys.exit(1)

    bootstrap = "--bootstrap" in args
    debug = "--debug" in args
    only_check = "--check" in args
    no_pdf = "--no-pdf" in args

    # Zeitraum
    from_date: Optional[date] = None
    to_date: Optional[date] = None
    if "--from" in args:
        idx = args.index("--from")
        try:
            from_date = date.fromisoformat(args[idx + 1])
        except (IndexError, ValueError):
            print("❌  --from YYYY-MM-DD erwartet")
            sys.exit(1)
    if "--to" in args:
        idx = args.index("--to")
        try:
            to_date = date.fromisoformat(args[idx + 1])
        except (IndexError, ValueError):
            print("❌  --to YYYY-MM-DD erwartet")
            sys.exit(1)

    headless = not bootstrap

    print("🛒 Supercard Scraper")
    print(f"   Modus: {'Bootstrap (manueller Login)' if bootstrap else 'Headless'}")
    if HAS_STEALTH:
        print("   Stealth: aktiv")
    if from_date or to_date:
        print(f"   Zeitraum: {from_date or '∞'} → {to_date or '∞'}")
    print()

    if bootstrap:
        print("  ℹ️  Bootstrap-Modus: Browser öffnet sich auf virtuellem Display.")
        print("     Falls CAPTCHA erscheint → manuell lösen, dann einloggen.")
        print("     Danach werden Cookies gespeichert — weitere Läufe headless.\n")

    result = await run_scraper(
        email, password,
        headless=headless,
        debug=debug,
        from_date=from_date,
        to_date=to_date,
        only_check=only_check,
        download_pdfs=not no_pdf,
    )

    receipts = result.get("receipts", [])
    pdfs = result.get("pdfs", [])
    check = result.get("check", {})

    print(f"\n{'='*50}")
    print(f"✅ {len(receipts)} Kassenzettel verarbeitet")
    print(f"📄 {len(pdfs)} PDFs heruntergeladen")
    if check:
        vollst = check.get("vollstaendig")
        icon = "✅" if vollst else ("⚠️" if vollst is False else "❓")
        print(f"{icon} Vollständigkeit: {len(pdfs)}/{check.get('receipts_gefunden', '?')} PDFs")


if __name__ == "__main__":
    asyncio.run(main())
