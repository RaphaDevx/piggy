package javax.ws.rs.core;

import androidx.compose.runtime.ComposerKt;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public class Variant {
    private String encoding;
    private Locale language;
    private MediaType mediaType;

    public Variant(MediaType mediaType, String str, String str2) {
        if (mediaType == null && str == null && str2 == null) {
            throw new IllegalArgumentException("mediaType, language, encoding all null");
        }
        this.encoding = str2;
        this.language = str == null ? null : new Locale(str);
        this.mediaType = mediaType;
    }

    public Variant(MediaType mediaType, String str, String str2, String str3) {
        if (mediaType == null && str == null && str3 == null) {
            throw new IllegalArgumentException("mediaType, language, encoding all null");
        }
        this.encoding = str3;
        this.language = str == null ? null : new Locale(str, str2);
        this.mediaType = mediaType;
    }

    public Variant(MediaType mediaType, String str, String str2, String str3, String str4) {
        if (mediaType == null && str == null && str4 == null) {
            throw new IllegalArgumentException("mediaType, language, encoding all null");
        }
        this.encoding = str4;
        this.language = str == null ? null : new Locale(str, str2, str3);
        this.mediaType = mediaType;
    }

    public Variant(MediaType mediaType, Locale locale, String str) {
        if (mediaType == null && locale == null && str == null) {
            throw new IllegalArgumentException("mediaType, language, encoding all null");
        }
        this.encoding = str;
        this.language = locale;
        this.mediaType = mediaType;
    }

    public Locale getLanguage() {
        return this.language;
    }

    public String getLanguageString() {
        Locale locale = this.language;
        if (locale == null) {
            return null;
        }
        return locale.toString();
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public static VariantListBuilder mediaTypes(MediaType... mediaTypeArr) {
        VariantListBuilder newInstance = VariantListBuilder.newInstance();
        newInstance.mediaTypes(mediaTypeArr);
        return newInstance;
    }

    public static VariantListBuilder languages(Locale... localeArr) {
        VariantListBuilder newInstance = VariantListBuilder.newInstance();
        newInstance.languages(localeArr);
        return newInstance;
    }

    public static VariantListBuilder encodings(String... strArr) {
        VariantListBuilder newInstance = VariantListBuilder.newInstance();
        newInstance.encodings(strArr);
        return newInstance;
    }

    public int hashCode() {
        Locale locale = this.language;
        int hashCode = (ComposerKt.providerValuesKey + (locale != null ? locale.hashCode() : 0)) * 29;
        MediaType mediaType = this.mediaType;
        int hashCode2 = (hashCode + (mediaType != null ? mediaType.hashCode() : 0)) * 29;
        String str = this.encoding;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Variant variant = (Variant) obj;
        Locale locale = this.language;
        Locale locale2 = variant.language;
        if (locale != locale2 && (locale == null || !locale.equals(locale2))) {
            return false;
        }
        MediaType mediaType = this.mediaType;
        MediaType mediaType2 = variant.mediaType;
        if (mediaType != mediaType2 && (mediaType == null || !mediaType.equals(mediaType2))) {
            return false;
        }
        String str = this.encoding;
        String str2 = variant.encoding;
        return str == str2 || (str != null && str.equals(str2));
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        stringWriter.append((CharSequence) "Variant[mediaType=");
        MediaType mediaType = this.mediaType;
        stringWriter.append((CharSequence) (mediaType == null ? "null" : mediaType.toString()));
        stringWriter.append((CharSequence) ", language=");
        Locale locale = this.language;
        stringWriter.append((CharSequence) (locale == null ? "null" : locale.toString()));
        stringWriter.append((CharSequence) ", encoding=");
        String str = this.encoding;
        stringWriter.append((CharSequence) (str != null ? str : "null"));
        stringWriter.append((CharSequence) "]");
        return stringWriter.toString();
    }

    public static abstract class VariantListBuilder {
        public abstract VariantListBuilder add();

        public abstract List<Variant> build();

        public abstract VariantListBuilder encodings(String... strArr);

        public abstract VariantListBuilder languages(Locale... localeArr);

        public abstract VariantListBuilder mediaTypes(MediaType... mediaTypeArr);

        protected VariantListBuilder() {
        }

        public static VariantListBuilder newInstance() {
            return RuntimeDelegate.getInstance().createVariantListBuilder();
        }
    }
}
