# Piggy für Geschäfte: papierlose Quittungen empfangen

Plan vom 27.11.2026. **Produktvorschlag, noch keine Händleranbindung oder Registrierung umgesetzt.** Es gibt noch keinen Pilotbetrieb. Die Firmen-Spesenlösung ist ein eigener Bereich: Ein privater Einkauf wird nicht automatisch an den Arbeitgeber weitergereicht.

## Zielbild für Kundinnen und Kunden

Bezahlen → „Digital mit Piggy“ wählen → QR-Code am Kassendisplay scannen → Geschäft und Betrag prüfen → „In Piggy speichern“. Die Quittung liegt mit Artikeln und Originalbeleg in Piggy. Kein Foto und keine OCR nötig, wenn die Kasse strukturierte Daten liefert. Der Empfang darf keine Marketinganmeldung verlangen. Die Kasse bestätigt die digitale Übergabe; bei Störung bleibt ein vereinbarter Ersatzweg verfügbar.

Der Code muss auf einem Display erscheinen. Ein QR-Code auf einem erst auszudruckenden Bon wäre für dieses Ziel nicht papierlos. Ein allgemeiner Aufkleber auf dem Tresen kann die Funktion erklären, identifiziert aber allein keinen konkreten Einkauf.

Ohne installierte App öffnet sich eine mobile Webseite mit Abruf-/Downloadmöglichkeit für den konkreten Beleg. Ein späterer Import nach Installation braucht eine ausdrücklich bestätigte und sichere Zuordnung; kein allgemeines Auslesen der Zwischenablage. Dauerhaftes Speichern in einem Piggy-Konto verlangt eine Anmeldung, der einmalige Belegdownload sollte ohne Piggy-Pflichtkonto möglich sein.

Optional später: Die Person zeigt ihren kurzlebigen Piggy-Empfangscode auf dem Telefon; die Kasse scannt ihn und verknüpft ausschliesslich diesen Einkauf. Dafür muss das Geschäft einen passenden Scanner bzw. eine freigegebene Eingabemöglichkeit haben. Ein statischer persönlicher Barcode wäre ungeeignet als unbegrenzte Empfangsvollmacht und könnte Einkäufe verknüpfbar machen.

## Angebot für das Geschäft

„Digitale Quittungen direkt an deine Kundschaft, mit Artikeln, Original und Wiederabruf.“

Das Händlerpaket umfasst einen verifizierten Geschäftsbereich, Kassenanbindung, einfache Anzeige „zugestellt/offen/fehlgeschlagen“, Storno-/Retourenablauf, Support und eine kurze Kassenschulung. Kundenservice kann einen Beleg anhand der eigenen Verkaufsreferenz erneut bereitstellen, ohne fremde Piggy-Belege einzusehen. Mitarbeitendenrechte werden pro Geschäft/Filiale begrenzt. Händler sehen keine privaten Finanzprofile, Kartenhistorien oder Arbeitgeberdaten.

Vertrieb zunächst über wenige lokale Geschäfte und anschliessend über Kassenanbieter/Betreuungspartner. Eine Integration bei einem Kassenanbieter kann mehrere Geschäfte erreichen; eine Zusage oder Partnerschaft besteht noch nicht. Zuerst einen kleinen Betrieb mit zugänglicher Kasse auswählen, etwa Café, Restaurant oder Fachgeschäft. Grössere Ketten erst nach einem belastbaren Pilot.

Preismodell als zu prüfende Hypothese: Pilot mit vereinbartem Umfang; danach monatliches Filialpaket plus nachvollziehbare Integrations-/Supportkosten. Preise erst nach Messung von Aufwand, Belegvolumen und Zahlungsbereitschaft festlegen. Keine Einsparungs- oder Umsatzversprechen ohne Daten. Kein Verkauf persönlicher Einkaufsdaten als Voraussetzung des Modells.

## Wege zum ersten Pilot

| Weg | Nutzen | Begrenzung / Prüfung |
|---|---|---|
| Kassenexport plus Händleroberfläche | Kontrollierter erster Ablauf mit existierenden strukturierten Belegen und PDF | Format und Weitergaberechte prüfen; keine erneute manuelle Artikelabtipparbeit als Dauerlösung |
| Direkter POS-Adapter | Automatischer Empfang direkt nach Verkaufsabschluss | Offizielle API, Beleg-/Artikelumfang, Refunds, Authentifizierung, Display-Erweiterbarkeit und Anbieterfreigabe konkret prüfen |
| Bestehenden digitalen Beleg importieren | Brücke, wenn eine Kasse bereits digitale Originale erzeugt | Kein Zugriff auf fremde Konten; kein beliebiges Server-Nachladen ungeprüfter Links; noch kein Piggy-Direktempfang |
| NFC / Wallet / automatische Zuordnung beim Bezahlen | Mögliche spätere Vereinfachung | Hardware, Plattformfreigaben und klare Zustimmung nötig; nicht Teil des ersten Pilots |

**Empfohlener Einstieg:** Eine Kasse und ein Geschäft auswählen, echte Beispielbelege plus Testzugang besorgen und einen einzigen sauberen Adapter samt QR-Empfang bauen. Kein Versprechen, jede Kasse sofort zu unterstützen. Ein Tablet neben einer geschlossenen Kasse bekommt nicht von selbst die einzelnen Artikel; dafür braucht es einen Export oder eine erlaubte Schnittstelle.

## Geprüfte Anknüpfungspunkte

- [Shopify POS: Customer receipt selection](https://help.shopify.com/en/manual/sell-in-person/shopify-pos/receipt-management/customer-receipt-selection) dokumentiert die Auswahl digitaler Belege via E-Mail, SMS bzw. Shop-App. Dafür gelten konkrete Voraussetzungen. Ein eigener Piggy-Empfangskanal ist daraus nicht automatisch verfügbar; die passende POS-/App-Erweiterung müsste separat bestätigt werden.
- [SumUp: Retrieve receipt details](https://developer.sumup.com/api/receipts/get/) dokumentiert den authentifizierten Abruf strukturierter Transaktionsbelege eines Händlers. Das ist ein Kandidat für eine Machbarkeitsprüfung. Mit echten Daten muss geprüft werden, ob vollständige Einzelartikel und der benötigte Kassen-/Steuerbeleg vorliegen; eine Zahlungsbestätigung allein reicht nicht für alle Produktanforderungen.
- [GS1: Unterschiede der 2D-Barcodes](https://support.gs1.org/support/solutions/articles/43000756000-what-is-the-difference-between-the-2d-barcode-options-gs1-datamatrix-data-matrix-with-gs1-digital-l) beschreibt QR-/Digital-Link-Verfahren für Produktidentifikation und Webverknüpfung. Daraus folgt kein universeller Standard für den privaten Empfang einer ganzen Quittung. Für den Pilot genügt ein eigener sicherer HTTPS-Empfangslink; eine Standardbezeichnung darf nicht als vorhandene Interoperabilität ausgegeben werden.

Quellen am 27.11.2026 geprüft. Keine dieser Quellen bestätigt eine bestehende Piggy-Partnerschaft. Schweizer Anforderungen an Beleginhalt, Aufbewahrung, Datenschutz und Papierersatz werden vor einem echten Pilot anhand dessen Branche und Prozess separat geprüft; dieser Plan erteilt keine rechtliche Freigabe.

## Technischer Vertrag des geplanten Adapters

Die folgenden Punkte sind Entwurf, keine bereits deployten Endpunkte:

1. Händler-/Filialkonto verifizieren, Zugang auf dessen Kassen begrenzen. Anbieterzugänge nur serverseitig; Widerruf und Schlüsselrotation einplanen.
2. Verkauf serverseitig empfangen: Händler, Filiale/Kasse, eindeutige Verkaufsreferenz, Zeitpunkt mit Zeitzone, Währung, Positionen mit Menge/Einheit/Preis/Rabatt/MWST, Gesamtsumme und erlaubte Zahlungsinformationen. Betrag als Integer in kleinster Währungseinheit oder exakter Dezimalstring, nie binäre Gleitkommazahl. Keine volle Kartennummer oder Kartenprüfnummer.
3. Originalbeleg/PDF und erhaltenes Rohformat privat speichern, Hash und Herkunft festhalten. Piggy-Darstellung von Händleroriginal und späteren Nutzerkorrekturen trennen. Fehlen Einzelartikel, diese als fehlend anzeigen statt erfinden. Ein strukturierter Originalbeleg braucht keine zusätzliche KI-Übertragung.
4. Wiederholbare Verarbeitung über eindeutige Kombination aus Händler, Verkauf und Ereignis. Ein wiederholtes Webhook erzeugt keine zweite Ausgabe. Abweichender Inhalt unter gleicher Referenz ist ein Konflikt, kein stilles Überschreiben.
5. Kurzlebige, zufällige Empfangssitzung erzeugen. QR enthält keinen Namen, keine Karten-/Artikelinformationen und keinen öffentlichen Dateipfad. Alle Verbindungen HTTPS; Empfangscodes gehasht speichern und nicht in Analyse-/Zugriffslogs übernehmen.
6. Belegübersicht nach dem Scannen zeigen, bewusste Annahme erfassen und die endgültige Zuordnung atomar durchführen. Mehrere Geräte/gleichzeitige Versuche brauchen eine definierte Konfliktantwort. Wiederholung durch dasselbe Konto bleibt idempotent.
7. Ein sichtbarer QR-Code allein beweist nicht, wer bezahlt hat. Für den Pilot muss eine passende Bindung gewählt werden: kontrollierte Anzeige nur für den aktuellen Einkauf plus kurze Gültigkeit und Bestätigung an der Kasse oder zuvor gescannter kurzlebiger Kundencode. Fotografierte/weitergereichte Codes und falsche Empfänger als eigene Testfälle behandeln. Vertrauliche Originaldaten nicht schon in einer öffentlichen Vorschauseite offenlegen.
8. App übernimmt bestätigte Händlerquittung mit Herkunftskennzeichnung; der Empfang startet keine Speseneinreichung, Zahlung oder Erstattung. Bestehende Fotoquittungen als mögliche Doppelung anzeigen, ohne sie automatisch zu löschen. Nicht allein wegen gleichem Betrag/Datum automatisch zusammenführen.
9. Storno, Teilretoure und Rückerstattung als neue referenzierte Ereignisse abbilden. Originalverkauf und finanzielle Gegenbewegung nachvollziehbar erhalten; den alten Beleg nicht einfach mit einem negativen Betrag überschreiben.
10. Händlerzustellung, Kundenannahme und bestätigte Zahlung als getrennte Zustände behandeln. Offline-Verkäufe puffern und später wiederholen; „zugestellt“ erst nach dauerhafter Speicherung bestätigen. Download/Abruf, Löschung, Aufbewahrung und Supportrechte vor Pilot konkret festlegen.

## Umsetzung mit Abnahmekriterien

| Etappe | Konkretes Ergebnis | Abnahme |
|---|---|---|
| 1 · Machbarkeit | Eine Kasse, Ansprechpartner und anonymisierte Originalbelege; Schnittstellen-/Displayfreigabe | Artikel, Summen, Steuern, Original und Retouren anhand realer Daten geprüft |
| 2 · Empfangsprototyp | Isolierter Händlerbereich, Adapter und QR-Webablauf | Zwei getrennte Kunden können nur den jeweils vorgesehenen Beleg abrufen; fremde/abgelaufene Codes abgelehnt |
| 3 · SwiftUI-Import | Universal Link/QR-Empfang, Vorschau, Zustimmung und idempotentes Speichern | Neuinstallation, Anmeldung, App-Wechsel und wiederholtes Scannen ohne Datenverlust/Duplikat |
| 4 · Kassenbetrieb | Zustellanzeige, Offline-Warteschlange, Wiederholung, Storno/Teilretoure, Supportablauf | Timeout nach erfolgreichem Speichern erzeugt keinen zweiten Beleg; Ausfall blockiert Zahlung nicht |
| 5 · Kontrollierter Pilot | Ein Betrieb, definierte Kassen und freiwillig teilnehmende Kundschaft | Empfangsdauer, Erfolgsquote, Fehlzuordnung, Papierverbrauch und Supportfälle gemessen; Abbruch-/Fallbackweg getestet |
| 6 · Händlerangebot | Onboarding, Vertrag, Preis, Dokumentation und verifizierter Adapterumfang | Kein Händlerstart ohne Zugriffsprüfung; keine „funktioniert mit allen Kassen“-Aussage |

Vorgeschlagene Pilotziele, noch keine Messwerte: fehlerfreie Empfängerzuordnung in allen Sicherheitstests, keine Doppelbelege bei Wiederholung, Empfang im normalen Onlinefall in höchstens zehn Sekunden nach QR-Scan, mindestens 99 % erfolgreiche technische Zustellung im vereinbarten Testumfang. Langsame oder fehlgeschlagene Fälle bleiben sichtbar und werden ausgewertet. Die Beurteilung hängt von einer ausreichend grossen und dokumentierten Testserie ab.

Als nächster konkreter Arbeitsschritt wird ein Test-Kassensystem samt anonymisiertem JSON/PDF-Beleg benötigt. Bis dahin können Schema und isolierter Empfangsprototyp vorbereitet werden; Produktivzugang, Händlerverträge oder automatische Zustellungen werden durch diesen Plan nicht ausgelöst.
