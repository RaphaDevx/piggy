package com.salesforce.marketingcloud;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

/* loaded from: classes2.dex */
public class MCService extends c {
    static final String k = "com.salesforce.marketingcloud.HTTP_REQUEST";
    static final String l = "com.salesforce.marketingcloud.ALARM_WAKE";
    static final String m = "com.salesforce.marketingcloud.SYSTEM_BEHAVIOR";
    static final String n = "com.salesforce.marketingcloud.TOKEN_REQUEST";
    private static final String o = "behavior";
    private static final String p = "data";
    private static final String q = "alarmName";
    private static final String r = "senderId";
    private static final int s = 3000;

    class a implements OnCompleteListener<String> {
        final /* synthetic */ String[] a;
        final /* synthetic */ Context b;
        final /* synthetic */ String c;

        a(String[] strArr, Context context, String str) {
            this.a = strArr;
            this.b = context;
            this.c = str;
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<String> task) {
            if (task.isSuccessful()) {
                this.a[0] = task.getResult();
            }
            com.salesforce.marketingcloud.messages.push.a.a(this.b, !TextUtils.isEmpty(this.a[0]), this.c, this.a[0]);
        }
    }

    public static void a(Context context, com.salesforce.marketingcloud.http.b bVar) {
        g.d(c.h, "enqueue handleHttpRequest - %s", bVar.r());
        a(context, k, bVar.s());
    }

    @Override // com.salesforce.marketingcloud.c
    public /* bridge */ /* synthetic */ void b(boolean z) {
        super.b(z);
    }

    @Override // com.salesforce.marketingcloud.c
    public /* bridge */ /* synthetic */ boolean c() {
        return super.c();
    }

    @Override // com.salesforce.marketingcloud.c
    public /* bridge */ /* synthetic */ boolean d() {
        return super.d();
    }

    @Override // com.salesforce.marketingcloud.c, android.app.Service
    public /* bridge */ /* synthetic */ IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override // com.salesforce.marketingcloud.c, android.app.Service
    public /* bridge */ /* synthetic */ void onCreate() {
        super.onCreate();
    }

    @Override // com.salesforce.marketingcloud.c, android.app.Service
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.salesforce.marketingcloud.c, android.app.Service
    public /* bridge */ /* synthetic */ int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public static void b(Context context, String str) {
        g.d(c.h, "enqueueTokenRequest", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putString(r, str);
        a(context, n, bundle);
    }

    private static void c(Context context, String str) {
        if (str == null) {
            g.d(c.h, "alarm name not provided", new Object[0]);
        } else {
            g.d(c.h, "handleAlarmWakeup - %s", str);
            context.sendBroadcast(new Intent(com.salesforce.marketingcloud.alarms.b.j).putExtra("com.salesforce.marketingcloud.WAKE_FOR_ALARM", str).setPackage(context.getPackageName()));
        }
    }

    static void d(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            g.a(c.h, "Unable to refresh system token.  SenderId was invalid", new Object[0]);
            return;
        }
        g.d(c.h, "handleTokenRequest", new Object[0]);
        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new a(new String[]{null}, context, str));
        } catch (Exception e) {
            g.b(c.h, e, "Failed to retrieve InstanceId from Firebase.", new Object[0]);
        }
    }

    public static void a(Context context, String str) {
        g.d(c.h, "enqueueAlarmWake - %s", str);
        Bundle bundle = new Bundle();
        bundle.putString(q, str);
        a(context, l, bundle);
    }

    static void b(Context context, com.salesforce.marketingcloud.http.b bVar) {
        com.salesforce.marketingcloud.http.e a2;
        if (bVar == null) {
            g.d(c.h, "request was null", new Object[0]);
            return;
        }
        g.d(c.h, "handleHttpRequest - %s", bVar.r());
        if (a(context)) {
            a2 = bVar.j();
        } else {
            a2 = com.salesforce.marketingcloud.http.e.a("No connectivity", -1);
        }
        context.sendBroadcast(new Intent(com.salesforce.marketingcloud.http.d.j).putExtra(com.salesforce.marketingcloud.http.d.l, bVar.s()).putExtra(com.salesforce.marketingcloud.http.d.k, a2).setPackage(context.getPackageName()));
    }

    static void a(Context context, com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        g.d(c.h, "enqueueSystemBehavior - %s", aVar);
        Bundle bundle2 = new Bundle();
        bundle2.putString(o, aVar.b);
        bundle2.putBundle("data", bundle);
        a(context, m, bundle2);
    }

    private static void a(Context context, String str, Bundle bundle) {
        c.a(context, MCService.class, 3000, new Intent(str).putExtras(bundle));
    }

    private static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) ? false : true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0050, code lost:
    
        if (r0.equals(com.salesforce.marketingcloud.MCService.l) == false) goto L11;
     */
    @Override // com.salesforce.marketingcloud.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(android.content.Intent r6) {
        /*
            r5 = this;
            java.lang.String r0 = r6.getAction()
            if (r0 != 0) goto L7
            return
        L7:
            android.content.Context r5 = r5.getApplicationContext()
            r1 = 3000(0xbb8, double:1.482E-320)
            r3 = 50
            boolean r1 = com.salesforce.marketingcloud.util.j.a(r1, r3)
            r2 = 0
            if (r1 == 0) goto L8d
            com.salesforce.marketingcloud.MarketingCloudSdk r1 = com.salesforce.marketingcloud.MarketingCloudSdk.getInstance()
            if (r1 == 0) goto L8d
            r0.hashCode()
            int r1 = r0.hashCode()
            r3 = -1
            switch(r1) {
                case -1341919505: goto L4a;
                case -525195028: goto L3f;
                case 352488053: goto L34;
                case 848031877: goto L29;
                default: goto L27;
            }
        L27:
            r2 = r3
            goto L53
        L29:
            java.lang.String r1 = "com.salesforce.marketingcloud.SYSTEM_BEHAVIOR"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L32
            goto L27
        L32:
            r2 = 3
            goto L53
        L34:
            java.lang.String r1 = "com.salesforce.marketingcloud.HTTP_REQUEST"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L3d
            goto L27
        L3d:
            r2 = 2
            goto L53
        L3f:
            java.lang.String r1 = "com.salesforce.marketingcloud.TOKEN_REQUEST"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L48
            goto L27
        L48:
            r2 = 1
            goto L53
        L4a:
            java.lang.String r1 = "com.salesforce.marketingcloud.ALARM_WAKE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L53
            goto L27
        L53:
            switch(r2) {
                case 0: goto L83;
                case 1: goto L79;
                case 2: goto L6b;
                case 3: goto L57;
                default: goto L56;
            }
        L56:
            goto L96
        L57:
            java.lang.String r0 = "behavior"
            java.lang.String r0 = r6.getStringExtra(r0)
            com.salesforce.marketingcloud.behaviors.a r0 = com.salesforce.marketingcloud.behaviors.a.a(r0)
            java.lang.String r1 = "data"
            android.os.Bundle r6 = r6.getBundleExtra(r1)
            b(r5, r0, r6)
            goto L96
        L6b:
            android.os.Bundle r6 = r6.getExtras()
            if (r6 == 0) goto L96
            com.salesforce.marketingcloud.http.b r6 = com.salesforce.marketingcloud.http.b.a(r6)
            b(r5, r6)
            goto L96
        L79:
            java.lang.String r0 = "senderId"
            java.lang.String r6 = r6.getStringExtra(r0)
            d(r5, r6)
            goto L96
        L83:
            java.lang.String r0 = "alarmName"
            java.lang.String r6 = r6.getStringExtra(r0)
            c(r5, r6)
            goto L96
        L8d:
            java.lang.String r5 = com.salesforce.marketingcloud.c.h
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "MarketingCloudSdk#init must be called in your application's onCreate"
            com.salesforce.marketingcloud.g.e(r5, r0, r6)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MCService.a(android.content.Intent):void");
    }

    private static void b(Context context, com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        if (aVar == null) {
            g.d(c.h, "Behavior was null", new Object[0]);
        } else {
            g.d(c.h, "handleSystemBehavior - %s", aVar);
            com.salesforce.marketingcloud.behaviors.c.a(context, aVar, bundle);
        }
    }
}
