package org.apache.cordova;

/* loaded from: classes4.dex */
public interface ICordovaCookieManager {
    void clearCookies();

    void flush();

    String getCookie(final String url);

    void setCookie(final String url, final String value);

    void setCookiesEnabled(boolean accept);
}
