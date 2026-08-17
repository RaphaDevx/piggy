package uk.org.okapibarcode.util;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* loaded from: classes4.dex */
public class EciMode {
    public static final EciMode NONE = new EciMode(-1, null);
    public final Charset charset;
    public final int mode;

    private EciMode(int i, Charset charset) {
        this.mode = i;
        this.charset = charset;
    }

    public static EciMode of(String str, String str2, int i) {
        try {
            Charset forName = Charset.forName(str2);
            if (forName.canEncode() && forName.newEncoder().canEncode(str)) {
                return new EciMode(i, forName);
            }
            return NONE;
        } catch (UnsupportedCharsetException unused) {
            return NONE;
        }
    }

    public EciMode or(String str, String str2, int i) {
        return !equals(NONE) ? this : of(str, str2, i);
    }

    public boolean equals(Object obj) {
        return (obj instanceof EciMode) && ((EciMode) obj).mode == this.mode;
    }

    public int hashCode() {
        return Integer.valueOf(this.mode).hashCode();
    }

    public String toString() {
        return "EciMode[mode=" + this.mode + ", charset=" + this.charset + "]";
    }
}
