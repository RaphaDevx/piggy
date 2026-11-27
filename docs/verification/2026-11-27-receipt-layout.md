# Belegbild und Angaben nebeneinander

Quellstand `f4d15936e045dd7eca12e62340adfa5c6d3cf4ae`, signiert und nach `kdev` übergeben.

- Belegübersicht: echtes privates Original neben Geschäft, Datum, bearbeitbarem Betrag, Zahlungsangabe und Artikelzahl. Bestehende Artikelzeilen und Zusatzinformationen bleiben erreichbar.
- Merge: beide Originale A/B mit Betrag/Datum/Artikelzahl nebeneinander, Bild per Tipp vergrösserbar; bisheriger Vergleich und bewusste Bestätigung erhalten.
- Gemeinsame Komponente `ReceiptOverview.swift`: frischer signierter Link beim Öffnen; fehlendes Bild, Downloadfehler, Fortschritt und erneutes Laden; vertikale Anordnung bei Bedienungshilfen-Schrift; semantische Systemfarben. Kein öffentliches Bild und kein Ersatzbild bei fehlendem Original.
- Keine Backend-/Finanzlogikänderung. Händlerempfang ausschliesslich als Plan dokumentiert, noch keine Implementierung.

Prüfungen:

- Ressourcenvalidierung erfolgreich: 261 Hashes, 17 Varianten, 222 Retina-Frames sowie Plists/deterministische Projekteingaben.
- `git diff --check` erfolgreich.
- K-Dev Apple-Job `3346b5294e250998`, Profil `local`: `succeeded`, `exitCode:0`, `gates.checks:passed`, `gates.simulatorBuild:passed`. Abschluss 27.11.2026 20:33:30 UTC. Swift-Coretests und vollständige Simulator-Kompilierung bestanden.
- Simulator-Artefakt SHA256 `44ebec50f93d7e3009962d719995ef842894e24f547c3d18c3647a1eab54d911`.

Keine interaktive Geräte-/Screenshotprüfung und kein neuer TestFlight-Upload. Die Anpassungen für grosse Schrift wurden im Code berücksichtigt, nicht visuell auf allen Grössen abgenommen. Für den Papierlos-Plan wurden offizielle Shopify-/SumUp-/GS1-Quellen geprüft; Quellen und Grenzen stehen direkt im Plan.
