package uk.org.okapibarcode.output;

import java.io.OutputStream;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.graphics.TextAlignment;

/* loaded from: classes4.dex */
public class PostScriptRenderer implements SymbolRenderer {
    private final Color ink;
    private final double magnification;
    private final OutputStream out;
    private final Color paper;

    public PostScriptRenderer(OutputStream outputStream, double d, Color color, Color color2) {
        this.out = outputStream;
        this.magnification = d;
        this.paper = color;
        this.ink = color2;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x026b A[Catch: all -> 0x0239, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0239, blocks: (B:12:0x0117, B:14:0x0123, B:17:0x0193, B:19:0x01ad, B:23:0x020c, B:25:0x01ba, B:43:0x025c, B:45:0x0264, B:47:0x026b), top: B:11:0x0117 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0409 A[Catch: all -> 0x05a1, TryCatch #0 {all -> 0x05a1, blocks: (B:51:0x02c0, B:61:0x0300, B:64:0x043b, B:66:0x0357, B:67:0x036d, B:68:0x036e, B:69:0x03c0, B:71:0x0409, B:80:0x045b, B:82:0x0465, B:84:0x047d, B:86:0x04e5, B:91:0x0558, B:93:0x0562, B:96:0x0570, B:98:0x0590, B:100:0x0598), top: B:50:0x02c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02a1  */
    @Override // uk.org.okapibarcode.output.SymbolRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void render(uk.org.okapibarcode.backend.Symbol r28) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.output.PostScriptRenderer.render(uk.org.okapibarcode.backend.Symbol):void");
    }

    /* renamed from: uk.org.okapibarcode.output.PostScriptRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment;

        static {
            int[] iArr = new int[TextAlignment.values().length];
            $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment = iArr;
            try {
                iArr[TextAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[TextAlignment.JUSTIFY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[TextAlignment.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[TextAlignment.CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
