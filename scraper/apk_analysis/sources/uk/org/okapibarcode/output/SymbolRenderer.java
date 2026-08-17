package uk.org.okapibarcode.output;

import java.io.IOException;
import uk.org.okapibarcode.backend.Symbol;

/* loaded from: classes4.dex */
public interface SymbolRenderer {
    void render(Symbol symbol) throws IOException;
}
