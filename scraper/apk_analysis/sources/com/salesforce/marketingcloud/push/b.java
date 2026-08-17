package com.salesforce.marketingcloud.push;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.push.data.a;
import java.util.Iterator;
import java.util.UUID;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public class b {
    public static final a c = new a(null);
    private static final String d = com.salesforce.marketingcloud.g.a("IntentProvider");
    public static final String e = "com.salesforce.marketingcloud.notifications.INTENT_KEY_DATA_NOTIFICATION_MESSAGE";
    public static final String f = "com.salesforce.marketingcloud.notifications.INTENT_KEY_ANALYTIC_TYPE";
    public static final String g = "com.salesforce.marketingcloud.notifications.INTENT_KEY_ANALYTIC_CLICKED_ID";
    public static final String h = "com.salesforce.marketingcloud.notifications.INTENT_KEY_ANALYTIC_TITLE";
    private final Context a;
    private final NotificationMessage b;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String a() {
            return b.d;
        }

        private a() {
        }
    }

    /* renamed from: com.salesforce.marketingcloud.push.b$b, reason: collision with other inner class name */
    static final class C0073b extends Lambda implements Function0<String> {
        public static final C0073b b = new C0073b();

        C0073b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "MarketingCloudSdk#init must be called in your application's onCreate";
        }
    }

    public b(Context context, NotificationMessage message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        this.a = context;
        this.b = message;
    }

    public final PendingIntent a(com.salesforce.marketingcloud.push.data.a[] aVarArr, int i, String clickedId, String str) {
        Intrinsics.checkNotNullParameter(clickedId, "clickedId");
        PendingIntent pendingIntent = null;
        if (aVarArr != null) {
            Iterator it = ArrayIteratorKt.iterator(aVarArr);
            while (it.hasNext()) {
                com.salesforce.marketingcloud.push.data.a aVar = (com.salesforce.marketingcloud.push.data.a) it.next();
                Pair pair = aVar instanceof a.g ? TuplesKt.to(((a.g) aVar).l(), "url") : aVar instanceof a.c ? TuplesKt.to(((a.c) aVar).l(), UrlHandler.DEEPLINK) : aVar instanceof a.C0079a ? TuplesKt.to(((a.C0079a) aVar).l(), UrlHandler.CLOUD_PAGE) : aVar instanceof a.e ? TuplesKt.to(null, "app_open") : TuplesKt.to(null, UrlHandler.ACTION);
                String str2 = (String) pair.component1();
                String str3 = (String) pair.component2();
                PendingIntent activity = PendingIntent.getActivity(this.a, UUID.randomUUID().hashCode(), this.a.getPackageManager().getLaunchIntentForPackage(this.a.getPackageName()), com.salesforce.marketingcloud.util.j.a(134217728));
                if (!com.salesforce.marketingcloud.util.j.a(3000L, 50L) || MarketingCloudSdk.getInstance() == null) {
                    com.salesforce.marketingcloud.g.a.b(d, (Throwable) null, C0073b.b);
                    return activity;
                }
                MarketingCloudSdk marketingCloudSdk = MarketingCloudSdk.getInstance();
                if (marketingCloudSdk != null) {
                    if (str2 != null) {
                        UrlHandler urlHandler = marketingCloudSdk.getMarketingCloudConfig().urlHandler;
                        if (urlHandler != null) {
                            pendingIntent = urlHandler.handleUrl(this.a, str2, str3);
                        }
                    } else {
                        pendingIntent = activity;
                    }
                    if (pendingIntent != null) {
                        Context context = this.a;
                        NotificationMessage notificationMessage = this.b;
                        Bundle bundle = new Bundle();
                        bundle.putInt(f, i);
                        bundle.putString(g, clickedId);
                        if (str != null) {
                            bundle.putString(h, str);
                        }
                        Unit unit = Unit.INSTANCE;
                        PendingIntent redirectIntentForAnalytics = NotificationManager.redirectIntentForAnalytics(context, pendingIntent, notificationMessage, true, bundle);
                        if (redirectIntentForAnalytics != null) {
                            return redirectIntentForAnalytics;
                        }
                    }
                    return activity;
                }
            }
        }
        return null;
    }
}
