package javax.ws.rs.core;

import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public class Cookie {
    public static final int DEFAULT_VERSION = 1;
    private static final RuntimeDelegate.HeaderDelegate<Cookie> HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(Cookie.class);
    private final String domain;
    private final String name;
    private final String path;
    private final String value;
    private final int version;

    public Cookie(String str, String str2, String str3, String str4, int i) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("name==null");
        }
        this.name = str;
        this.value = str2;
        this.version = i;
        this.domain = str4;
        this.path = str3;
    }

    public Cookie(String str, String str2, String str3, String str4) throws IllegalArgumentException {
        this(str, str2, str3, str4, 1);
    }

    public Cookie(String str, String str2) throws IllegalArgumentException {
        this(str, str2, null, null);
    }

    public static Cookie valueOf(String str) {
        return HEADER_DELEGATE.fromString(str);
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public int getVersion() {
        return this.version;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getPath() {
        return this.path;
    }

    public String toString() {
        return HEADER_DELEGATE.toString(this);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (679 + (str != null ? str.hashCode() : 0)) * 97;
        String str2 = this.value;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 97) + this.version) * 97;
        String str3 = this.path;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 97;
        String str4 = this.domain;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        String str = this.name;
        String str2 = cookie.name;
        if (str != str2 && (str == null || !str.equals(str2))) {
            return false;
        }
        String str3 = this.value;
        String str4 = cookie.value;
        if ((str3 != str4 && (str3 == null || !str3.equals(str4))) || this.version != cookie.version) {
            return false;
        }
        String str5 = this.path;
        String str6 = cookie.path;
        if (str5 != str6 && (str5 == null || !str5.equals(str6))) {
            return false;
        }
        String str7 = this.domain;
        String str8 = cookie.domain;
        if (str7 != str8) {
            return str7 != null && str7.equals(str8);
        }
        return true;
    }
}
