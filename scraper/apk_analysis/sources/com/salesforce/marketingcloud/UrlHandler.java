package com.salesforce.marketingcloud;

import android.app.PendingIntent;
import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public interface UrlHandler {
    public static final String ACTION = "action";
    public static final String APP_OPEN = "app_open";
    public static final String CLOUD_PAGE = "cloud_page";
    public static final String DEEPLINK = "deeplink";
    public static final String URL = "url";

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    PendingIntent handleUrl(Context context, String str, String str2);
}
