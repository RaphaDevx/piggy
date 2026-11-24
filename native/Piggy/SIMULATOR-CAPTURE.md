# Interne Aufnahme mit echten App-Daten

Debug-Builds im iOS-Simulator bieten unter den normalen Login-Buttons ein aufklappbares «Simulator-Testkonto». Es nutzt `auth.signIn(email:password:)`, die normale Supabase-Session, dieselben RLS-Regeln wie andere angemeldete Konten. Im unsignierten Debug-Simulator hält ein mit NSLock geschützter `AuthLocalStorage` die echte Sitzung ausschliesslich im Prozessspeicher. Nach App-Neustart erneut anmelden. Geräte-/Release-Builds verwenden unverändert Keychain.

Es werden keine lokalen Bildschirmattrappen oder privilegierten Sitzungen erzeugt.

Der Code wird nur unter `#if DEBUG && targetEnvironment(simulator)` kompiliert. Geräte- und Release-Builds enthalten diesen Zugang nicht. Keine Zugangsdaten in Quellcode, Launch-Argumenten oder Aufnahmemanifesten hinterlegen. Vor Beginn der Bildschirmaufnahme anmelden und nur ein isoliertes Konto mit eindeutig fiktiven Beispieldaten verwenden.

Bei der Aufnahme Build-Job, Commit, Raw-Hash und echte Navigationsereignisse dokumentieren. Eine solche Aufnahme zeigt die native App in einem internen Test-Build. Sie bestätigt weder den Apple-/Google-Anmeldeweg noch einen Store-Release. Der Google-Redirect-Fehler bleibt separat offen.
