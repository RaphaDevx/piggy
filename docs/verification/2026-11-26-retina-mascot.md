# Piggy Retina-Maskottchen und interne TestFlight-Auslieferung

Auftrag: bestehendes Piggy aus denselben K-Creative-Ressourcen schärfer und mit einheitlicher Auflösung rendern, abgeschnittene Schatten vermeiden, flüssig integrieren und anschliessend intern veröffentlichen. Keine neue Figur, keine öffentlichen Tester, kein App-Store-Release.

## Asset-Vertrag

- Quelle: K-Creative-Cloud/07_Brands/Piggy/dist/3d/piggy.blend. Vorhandene Geometrie, Materialien, Perspektive und die drei zuvor erstellten v2-Bewegungsabläufe bleiben erhalten.
- Neue Exporte: `dist/3d/app-motion-v3`, 864 × 864 RGBA, echte 30 fps, Cycles CPU, 128 Samples, fester Rausch-Seed. Keine hochskalierten Altbilder und keine interpolierten Zwischenbilder.
- Ein gemeinsamer 1.18-fach grösserer Kamerabildausschnitt hält Figur, Münze und Schatten im selben Koordinatensystem. Die äussersten 2% sind vollständig transparent; ein weicher Compositor-Verlauf bis 12% entfernt Rest-Alpha des Shadow Catchers. Die deckende Figur liegt in allen Frames vollständig innerhalb dieses Bereichs und wird nicht angefasst. Der Schatten wird weich ausgeblendet, statt am Canvas hart abgeschnitten.
- Idle 90 Frames / 3 s, Scan 72 / 2.4 s, Celebration 60 / 2 s. Kodierte Dauern 33/33/34 ms. Keine doppelten Endframes, periodische Pose und Geschwindigkeit aus dem bestehenden Bewegungsmodell.
- `package_app_motion_v3.py` prüft gerenderte Abmessungen, alle SHA-256-Werte, Pose-Abschluss, alpha-gewichtete Übergänge und den transparenten Rand. Retina-APNGs und Filmstreifen bleiben K-Creative-Prüfartefakte; die App verwendet die einzelnen originalen PNG-Frames.
- `sync_brand.py` kopiert nur geprüfte Exporte und die bestehenden 17 Markenvarianten. Die drei statischen Fallbacks entsprechen bytegenau dem jeweils ersten Animationsframe, ebenfalls 864 × 864.

## Native Wiedergabe

`MascotView` ersetzt die bisher vollständig dekodierten 256-Pixel-APNGs an allen bestehenden Verwendungsorten. Die bewusst unterschiedlich grossen UI-Flächen bleiben bestehen. Alle Posen verwenden denselben Master und dieselbe Retina-Pixeldichte. Der transparente 1.18-fache Canvas ragt ohne Clipping über die ursprüngliche Layoutfläche hinaus, sodass Piggy selbst dieselbe sichtbare Grösse behält.

Ein serieller Hintergrund-Decoder lädt unabhängige PNG-Frames in der tatsächlich benötigten Retina-Grösse. Der Puffer behält höchstens sechs Frames; das aktuell dargestellte Bild kann zusätzlich gehalten werden. Am grössten bestehenden Platz (240 pt bei 3×) werden maximal 850 × 850 Pixel pro Frame benötigt: sechs dekodierte Bilder etwa 16.5 MiB zuzüglich dargestelltem Bild und Decoder-Zwischenspeicher. Keine vollständigen 90-Frame-Retina-Arrays.

CADisplayLink folgt der verstrichenen monotonen Zeit und überspringt bei Verzögerungen überholte Frames. Unsichtbare Scroll-/Pager-Flächen führen keine neuen Frame-Requests aus und leeren ihren Puffer; beim Verlassen und im Hintergrund werden DisplayLink und Puffer freigegeben. «Bewegung reduzieren» zeigt denselben scharfen ersten Frame ohne Autoplay. Transparente Views ohne eigenes Weiss oder Maske erhalten den Schatten auch auf dunklem Untergrund.

## Prüfungen und Grenzen

- Erster Render visuell auf Weiss und Dunkel geprüft; kein sichtbares rechteckiges Schattenende.
- Vorhersage der maximalen Figur-/Münzausdehnung über alle 222 v2-Posen bestätigt, dass keine Figur in die Schattenblende gerät. Zusätzlich erfolgt dieselbe Pixelprüfung an allen neuen Frames.
- Neuer Player und Zeitlogik: K-Dev-Commit `7cad584c33113eef373c4d05dcfeb4fed8d92e92`; lokaler Build `c144871352c194ce` mit erfolgreichen Swift-Core-Tests und Apple-Simulator-Kompilierung. Dieser Vorab-Build enthält noch die alten Assets und ist keine Prüfung der endgültigen Exporte.
- Die abschliessende Ressourcenprüfung und Apple-ImageIO-Tests müssen den vollständigen v3-Export bestätigen. Ein Mac-Dekodierbenchmark ist keine gemessene Bildrate auf dem iPhone.
- Vollständiger aktualisierter UI-Durchlauf, echte Kamera und abschliessender Google-Consent auf dem iPhone bleiben gesonderte Geräteprüfungen. Backend/Auth/Storage werden in diesem Auftrag nicht verändert.

## Veröffentlichung

Ausdrücklich autorisiert: intern. Vorhandene App `com.heartbeat.piggy`, ASC `6802367826`, keine neue Registrierung. Apple-Prüfung vom 26.11. bestätigt: «Internal Testers» ist intern; «Raphael Private Beta» ist extern und darf diesen Build durch diesen Auftrag nicht erhalten. Keine neuen Tester oder Einladungen.

Status: Vollständiger Render und abschliessender Build laufen noch; keine TestFlight-Veröffentlichung erfolgt. Endgültiger Build, Apple-Verarbeitung und bestätigte Gruppenbeziehung werden nach erfolgreicher Auslieferung ergänzt.
