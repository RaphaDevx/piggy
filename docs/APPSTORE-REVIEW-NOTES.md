# Piggy – Review-Notizen für App Store Connect

Diese Datei enthält **keine** Zugangsdaten. Benutzername und Passwort stehen nur im ASC-Feld „Anmeldeinformationen“ (Datei bei Raphael: `~/Public/ToDo/piggy-review-zugang.txt`).

## Text für das Feld „Notizen“ (EN)

Piggy scans shopping receipts, categorises the items and shows spending overviews.

**Sign-in.** New accounts are created with Sign in with Apple or Google only. For review, please use the demo account supplied in the credentials fields: on the login screen tap "Mit E-Mail anmelden" (below the Apple and Google buttons) and enter the e-mail and password. The demo account contains six sample receipts. E-mail registration is intentionally disabled.

**Camera.** Used only to scan receipts (document scanner) and QR codes. Photos are uploaded to the user's private storage folder; no one else can read them.

**AI processing.** On first scan the app asks for consent before the recognized receipt text is sent to our AI provider (Anthropic, USA) for extraction. The dialog names the provider and the country; declining keeps the app fully usable with on-device recognition. Consent (with timestamp and text version) is stored in the user's account and can be withdrawn under Profile → Verarbeitung.

**Account deletion.** Profile → "Konto löschen" deletes the account and all receipts, images, projects, friends and splits, and revokes the Sign in with Apple token.

**Bank statements.** Not part of version 1.0. The Finance tab shows monthly and category overviews built from scanned receipts only; there is no statement upload or matching.

The app uses the dark appearance only.
