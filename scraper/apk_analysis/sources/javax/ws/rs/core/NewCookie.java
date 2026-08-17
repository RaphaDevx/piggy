package javax.ws.rs.core;

import java.util.Date;
import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public class NewCookie extends Cookie {
    public static final int DEFAULT_MAX_AGE = -1;
    private static final RuntimeDelegate.HeaderDelegate<NewCookie> delegate = RuntimeDelegate.getInstance().createHeaderDelegate(NewCookie.class);
    private final String comment;
    private final Date expiry;
    private final boolean httpOnly;
    private final int maxAge;
    private final boolean secure;

    public NewCookie(String str, String str2) {
        this(str, str2, null, null, 1, null, -1, null, false, false);
    }

    public NewCookie(String str, String str2, String str3, String str4, String str5, int i, boolean z) {
        this(str, str2, str3, str4, 1, str5, i, null, z, false);
    }

    public NewCookie(String str, String str2, String str3, String str4, String str5, int i, boolean z, boolean z2) {
        this(str, str2, str3, str4, 1, str5, i, null, z, z2);
    }

    public NewCookie(String str, String str2, String str3, String str4, int i, String str5, int i2, boolean z) {
        this(str, str2, str3, str4, i, str5, i2, null, z, false);
    }

    public NewCookie(String str, String str2, String str3, String str4, int i, String str5, int i2, Date date, boolean z, boolean z2) {
        super(str, str2, str3, str4, i);
        this.comment = str5;
        this.maxAge = i2;
        this.expiry = date;
        this.secure = z;
        this.httpOnly = z2;
    }

    public NewCookie(Cookie cookie) {
        this(cookie, null, -1, null, false, false);
    }

    public NewCookie(Cookie cookie, String str, int i, boolean z) {
        this(cookie, str, i, null, z, false);
    }

    public NewCookie(Cookie cookie, String str, int i, Date date, boolean z, boolean z2) {
        super(cookie == null ? null : cookie.getName(), cookie == null ? null : cookie.getValue(), cookie == null ? null : cookie.getPath(), cookie != null ? cookie.getDomain() : null, cookie == null ? 1 : cookie.getVersion());
        this.comment = str;
        this.maxAge = i;
        this.expiry = date;
        this.secure = z;
        this.httpOnly = z2;
    }

    public static NewCookie valueOf(String str) {
        return delegate.fromString(str);
    }

    public String getComment() {
        return this.comment;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public Date getExpiry() {
        return this.expiry;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    public Cookie toCookie() {
        return new Cookie(getName(), getValue(), getPath(), getDomain(), getVersion());
    }

    @Override // javax.ws.rs.core.Cookie
    public String toString() {
        return delegate.toString(this);
    }

    @Override // javax.ws.rs.core.Cookie
    public int hashCode() {
        int hashCode = super.hashCode() * 59;
        String str = this.comment;
        int hashCode2 = ((hashCode + (str != null ? str.hashCode() : 0)) * 59) + this.maxAge + 59;
        Date date = this.expiry;
        return ((((hashCode2 + (date != null ? date.hashCode() : 0)) * 59) + (this.secure ? 1 : 0)) * 59) + (this.httpOnly ? 1 : 0);
    }

    @Override // javax.ws.rs.core.Cookie
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NewCookie newCookie = (NewCookie) obj;
        if (getName() != newCookie.getName() && (getName() == null || !getName().equals(newCookie.getName()))) {
            return false;
        }
        if ((getValue() != newCookie.getValue() && (getValue() == null || !getValue().equals(newCookie.getValue()))) || getVersion() != newCookie.getVersion()) {
            return false;
        }
        if (getPath() != newCookie.getPath() && (getPath() == null || !getPath().equals(newCookie.getPath()))) {
            return false;
        }
        if (getDomain() != newCookie.getDomain() && (getDomain() == null || !getDomain().equals(newCookie.getDomain()))) {
            return false;
        }
        String str = this.comment;
        String str2 = newCookie.comment;
        if ((str != str2 && (str == null || !str.equals(str2))) || this.maxAge != newCookie.maxAge) {
            return false;
        }
        Date date = this.expiry;
        Date date2 = newCookie.expiry;
        return (date == date2 || (date != null && date.equals(date2))) && this.secure == newCookie.secure && this.httpOnly == newCookie.httpOnly;
    }
}
