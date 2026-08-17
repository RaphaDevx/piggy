package ch.coop.capacitor.barcodegenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.output.SymbolRenderer;

/* loaded from: classes3.dex */
public class SvgViewBoxRenderer implements SymbolRenderer {
    private final OutputStream out;

    public SvgViewBoxRenderer(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override // uk.org.okapibarcode.output.SymbolRenderer
    public void render(Symbol symbol) throws IOException {
        symbol.getContent();
        String format = format(symbol.getWidth() * 2.0d);
        String format2 = format(symbol.getHeight() * 2.0d);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.out, StandardCharsets.UTF_8);
        try {
            outputStreamWriter.append((CharSequence) "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" viewBox=\"0 0 ").append((CharSequence) format).append((CharSequence) StringUtils.SPACE).append((CharSequence) format2).append((CharSequence) "\">\n");
            outputStreamWriter.append((CharSequence) "  <rect x=\"0\" y=\"0\" width=\"").append((CharSequence) format).append((CharSequence) "\" height=\"").append((CharSequence) format2).append((CharSequence) "\" fill=\"#ffffff\" />\n");
            outputStreamWriter.append((CharSequence) "  <path d=\"");
            for (int i = 0; i < symbol.getRectangles().size(); i++) {
                Rectangle rectangle = symbol.getRectangles().get(i);
                String format3 = format(rectangle.x * 2.0d);
                String format4 = format(rectangle.y * 2.0d);
                String format5 = format(rectangle.width * 2.0d);
                outputStreamWriter.append((CharSequence) "M").append((CharSequence) format3).append((CharSequence) StringUtils.SPACE).append((CharSequence) format4).append((CharSequence) "h").append((CharSequence) format5).append((CharSequence) "v").append((CharSequence) format(rectangle.height * 2.0d)).append((CharSequence) "h-").append((CharSequence) format5).append((CharSequence) "Z");
            }
            outputStreamWriter.append((CharSequence) "\" fill=\"#000000\" />\n");
            outputStreamWriter.append((CharSequence) "</svg>");
            outputStreamWriter.close();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private String format(double d) {
        return String.format(Locale.US, "%.2f", Double.valueOf(d));
    }
}
