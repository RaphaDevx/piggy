# Piggy iPhone: Google- und E-Mail-Anmeldung

## Befund

Nutzer meldet Fehler 400 / redirect beim Google-Login in der iPhone-App. Live reproduziert: Der Swift-Client ruft Supabase-Web-OAuth auf, im Google-Provider ist jedoch nur derselbe iOS-Client wie im älteren Expo-Client eingetragen. Supabase leitet ihn an die HTTPS-Supabase-Callbackadresse weiter; Google endet auf `/signin/oauth/error` mit `redirect_uri_mismatch`.

Mit demselben öffentlichen iOS-Client, dem passenden Reverse-Client-Schema als Callback, S256-PKCE und Nonce öffnet Google `/v3/signin/identifier` ohne Redirectfehler. Dieser Test durchläuft die Autorisierungsanforderung, aber keinen echten Google-Login/Consent.

Das bestätigte Outlook-Konto ist weiterhin vorhanden, Provider `email`, letzter erfolgreicher Login laut Account-Metadaten 19.11.2026. Keine Kontoänderung, keine neuen Konten und keine Zusammenführung. Der reguläre E-Mail-Login fehlte im Swift-UI, obwohl der bisherige Expo-Client ihn hat.

## Korrektur

- Direkter nativer Google-PKCE-Flow im System-Anmeldefenster mit State-Abgleich, Nonce-Hash, sicher erzeugtem Verifier und Codeaustausch ohne Client-Secret. Supabase verifiziert danach das Google-ID-Token und stellt die Piggy-Sitzung aus. Google-Client und Provider-Allowlist unverändert.
- Reversed Google-Schema in der App registriert. Fehlerhafte/mehrdeutige Callbackparameter werden abgelehnt. Abbrechen ist kein Loginfehler.
- Normaler E-Mail-/Passwort-Login für vorhandene Konten wieder sichtbar, keine Registrierung.
- Passwort-Zurücksetzen nur auf ausdrücklichen Tastendruck des Nutzers. SDK-PKCE, exakter Callback `piggy://auth/reset-password`, eigener Bildschirm für das neue Passwort; Abbrechen meldet ausschliesslich die lokale Sitzung ab.
- Genau diesen Recovery-Callback an Supabase-Redirect-Allowlist angehängt; alle vorhandenen Einträge, Site URL und Google-Provider unverändert. Keine automatisierte Passwort-Mail an Raphael geschickt.

## Verifikation

324 bestehende Jest-Tests und TypeScript bestanden. Zusätzliche Swift-Core-Prüfungen decken nativen Redirect/PKCE, State/Host/Pfad/Parameter-Duplikate, Google-Abbruch, Form-Encoding und Recovery-URL-Grenzen ab. Apple-Build und UI-Nachweis werden nach K-Dev-Lauf ergänzt.

Grenze: Die Web-Google-Konfiguration braucht weiterhin einen Web-Client; dieser Fix stellt den passenden iPhone-Flow wieder her. Ein kompletter Google-Login mit echtem Benutzer-Consent sowie die tatsächliche Zustellung einer Recovery-Mail sind noch nicht nachgewiesen. Die installierte iPhone-App benötigt das Update; kein Production-/TestFlight-Upload ohne gesonderten Auftrag.
