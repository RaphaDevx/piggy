package uk.org.okapibarcode.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* loaded from: classes4.dex */
class ExtendedOutputStreamWriter extends OutputStreamWriter {
    private final String doubleFormat;

    public ExtendedOutputStreamWriter(OutputStream outputStream, String str) {
        super(outputStream, StandardCharsets.UTF_8);
        this.doubleFormat = str;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public ExtendedOutputStreamWriter append(CharSequence charSequence) throws IOException {
        super.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public ExtendedOutputStreamWriter append(CharSequence charSequence, int i, int i2) throws IOException {
        super.append(charSequence, i, i2);
        return this;
    }

    public ExtendedOutputStreamWriter append(double d) throws IOException {
        super.append((CharSequence) String.format(Locale.ROOT, this.doubleFormat, Double.valueOf(d)));
        return this;
    }

    public ExtendedOutputStreamWriter appendInt(int i) throws IOException {
        super.append((CharSequence) String.valueOf(i));
        return this;
    }
}
