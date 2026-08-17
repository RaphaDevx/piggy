package com.salesforce.marketingcloud;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCLogListener;
import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.events.EventManager;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.legacycrypto.OldSdkHash;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.messages.RegionMessageManager;
import com.salesforce.marketingcloud.messages.iam.InAppMessageComponent;
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface;
import com.salesforce.marketingcloud.util.AesCrypto;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import kotlin.Deprecated;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class MarketingCloudSdk extends PushModuleInterface implements b.InterfaceC0049b {
    private static volatile boolean A = false;
    private static volatile boolean B = false;
    static final String t = "MarketingCloudPrefs";
    static final String u = "InitConfig";
    static MarketingCloudSdk y;
    private static Context z;
    private final MarketingCloudConfig a;
    private final List<d> b = new ArrayList();
    private final SFMCSdkComponents c;
    com.salesforce.marketingcloud.location.f d;
    com.salesforce.marketingcloud.behaviors.c e;
    private com.salesforce.marketingcloud.b f;
    private com.salesforce.marketingcloud.storage.h g;
    private com.salesforce.marketingcloud.http.d h;
    private com.salesforce.marketingcloud.messages.inbox.a i;
    private com.salesforce.marketingcloud.registration.d j;
    private com.salesforce.marketingcloud.notifications.a k;
    private com.salesforce.marketingcloud.messages.push.a l;
    private com.salesforce.marketingcloud.messages.d m;
    private com.salesforce.marketingcloud.events.c n;
    private AnalyticsManager o;
    private InitializationStatus p;
    private InAppMessageComponent q;
    private n r;
    private o s;
    static final String v = g.a("MarketingCloudSdk");
    private static final Object w = new Object();
    private static final List<c> x = new ArrayList();
    private static volatile boolean C = true;

    public interface InitializationListener {
        void complete(InitializationStatus initializationStatus);
    }

    public interface WhenReadyListener {
        void ready(MarketingCloudSdk marketingCloudSdk);
    }

    class a implements Runnable {
        final /* synthetic */ Context b;
        final /* synthetic */ MarketingCloudConfig c;
        final /* synthetic */ SFMCSdkComponents d;
        final /* synthetic */ InitializationListener e;

        a(Context context, MarketingCloudConfig marketingCloudConfig, SFMCSdkComponents sFMCSdkComponents, InitializationListener initializationListener) {
            this.b = context;
            this.c = marketingCloudConfig;
            this.d = sFMCSdkComponents;
            this.e = initializationListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("SFMC_init");
            try {
                String str = MarketingCloudSdk.v;
                g.d(str, "Starting init thread", new Object[0]);
                MarketingCloudSdk.a(this.b, this.c, this.d, this.e);
                Thread.currentThread().setName(name);
                g.d(str, "~~ MarketingCloudSdk v%s init complete ~~", MarketingCloudSdk.getSdkVersionName());
            } catch (Throwable th) {
                Thread.currentThread().setName(name);
                g.d(MarketingCloudSdk.v, "~~ MarketingCloudSdk v%s init complete ~~", MarketingCloudSdk.getSdkVersionName());
                throw th;
            }
        }
    }

    class b extends c {
        b(Looper looper, WhenReadyListener whenReadyListener) {
            super(looper, whenReadyListener);
        }

        @Override // com.salesforce.marketingcloud.MarketingCloudSdk.c
        protected void a(WhenReadyListener whenReadyListener) {
            if (whenReadyListener != null) {
                try {
                    whenReadyListener.ready(MarketingCloudSdk.y);
                } catch (Exception e) {
                    g.b(MarketingCloudSdk.v, e, "Error occurred in %s", whenReadyListener.getClass().getName());
                }
            }
        }
    }

    static abstract class c {
        private final Handler a;
        WhenReadyListener b;
        volatile boolean c;
        private final Runnable d = new a();
        private volatile boolean e;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    if (c.this.c) {
                        return;
                    }
                    c cVar = c.this;
                    cVar.a(cVar.b);
                    c.this.c = true;
                }
            }
        }

        c(Looper looper, WhenReadyListener whenReadyListener) {
            looper = looper == null ? Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper() : looper;
            this.b = whenReadyListener;
            this.a = new Handler(looper);
        }

        public void a() {
            synchronized (this) {
                if (!this.c && !this.e) {
                    this.e = true;
                    this.a.post(this.d);
                }
            }
        }

        protected abstract void a(WhenReadyListener whenReadyListener);
    }

    private MarketingCloudSdk(MarketingCloudConfig marketingCloudConfig, SFMCSdkComponents sFMCSdkComponents) {
        this.a = marketingCloudConfig;
        this.c = sFMCSdkComponents;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a7 A[Catch: all -> 0x00af, TryCatch #2 {, blocks: (B:4:0x000e, B:6:0x0012, B:7:0x001b, B:9:0x0026, B:10:0x0031, B:12:0x004c, B:13:0x005a, B:25:0x00a0, B:27:0x00a7, B:28:0x00aa, B:34:0x0091, B:35:0x0092, B:36:0x009b, B:43:0x00ae, B:38:0x009c, B:39:0x009f, B:15:0x005b, B:17:0x0074, B:18:0x0078, B:20:0x007e, B:22:0x0088, B:23:0x008d), top: B:3:0x000e, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(android.content.Context r5, com.salesforce.marketingcloud.MarketingCloudConfig r6, com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents r7, com.salesforce.marketingcloud.MarketingCloudSdk.InitializationListener r8) {
        /*
            java.lang.String r5 = com.salesforce.marketingcloud.MarketingCloudSdk.v
            java.lang.Object[] r0 = new java.lang.Object[]{r6}
            java.lang.String r1 = "executeInit %s"
            com.salesforce.marketingcloud.g.d(r5, r1, r0)
            java.lang.Object r0 = com.salesforce.marketingcloud.MarketingCloudSdk.w
            monitor-enter(r0)
            com.salesforce.marketingcloud.MarketingCloudSdk r1 = com.salesforce.marketingcloud.MarketingCloudSdk.y     // Catch: java.lang.Throwable -> Laf
            if (r1 == 0) goto L1b
            com.salesforce.marketingcloud.MarketingCloudConfig r2 = r1.a     // Catch: java.lang.Throwable -> Laf
            boolean r2 = com.salesforce.marketingcloud.internal.g.a(r6, r2)     // Catch: java.lang.Throwable -> Laf
            r1.b(r2)     // Catch: java.lang.Throwable -> Laf
        L1b:
            com.salesforce.marketingcloud.MarketingCloudSdk r1 = new com.salesforce.marketingcloud.MarketingCloudSdk     // Catch: java.lang.Throwable -> Laf
            r1.<init>(r6, r7)     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.MarketingCloudSdk.y = r1     // Catch: java.lang.Throwable -> Laf
            r1 = 0
            r2 = 0
            if (r7 == 0) goto L2f
            java.lang.String r3 = r7.getRegistrationId()     // Catch: java.lang.Throwable -> Laf
            boolean r7 = r7.getEncryptionChanged()     // Catch: java.lang.Throwable -> Laf
            goto L31
        L2f:
            r3 = r1
            r7 = r2
        L31:
            com.salesforce.marketingcloud.MarketingCloudSdk r4 = com.salesforce.marketingcloud.MarketingCloudSdk.y     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.InitializationStatus r6 = r4.a(r3, r6, r7)     // Catch: java.lang.Throwable -> Laf
            java.lang.String r7 = "MarketingCloudSdk init finished with status: %s"
            java.lang.Object[] r3 = new java.lang.Object[]{r6}     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.g.a(r5, r7, r3)     // Catch: java.lang.Throwable -> Laf
            boolean r7 = r6.isUsable()     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.MarketingCloudSdk.B = r7     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.MarketingCloudSdk.A = r2     // Catch: java.lang.Throwable -> Laf
            boolean r7 = com.salesforce.marketingcloud.MarketingCloudSdk.B     // Catch: java.lang.Throwable -> Laf
            if (r7 == 0) goto L92
            com.salesforce.marketingcloud.MarketingCloudSdk r7 = com.salesforce.marketingcloud.MarketingCloudSdk.y     // Catch: java.lang.Throwable -> Laf
            r7.a(r6)     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.MarketingCloudSdk r7 = com.salesforce.marketingcloud.MarketingCloudSdk.y     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.b r1 = r7.f     // Catch: java.lang.Throwable -> Laf
            r1.a(r7)     // Catch: java.lang.Throwable -> Laf
            java.util.List<com.salesforce.marketingcloud.MarketingCloudSdk$c> r7 = com.salesforce.marketingcloud.MarketingCloudSdk.x     // Catch: java.lang.Throwable -> Laf
            monitor-enter(r7)     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.MarketingCloudSdk.C = r2     // Catch: java.lang.Throwable -> L8f
            java.lang.String r1 = "Delivering queued SDK requests to %s listeners"
            int r2 = r7.size()     // Catch: java.lang.Throwable -> L8f
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L8f
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch: java.lang.Throwable -> L8f
            com.salesforce.marketingcloud.g.d(r5, r1, r2)     // Catch: java.lang.Throwable -> L8f
            boolean r5 = r7.isEmpty()     // Catch: java.lang.Throwable -> L8f
            if (r5 != 0) goto L8d
            java.util.Iterator r5 = r7.iterator()     // Catch: java.lang.Throwable -> L8f
        L78:
            boolean r1 = r5.hasNext()     // Catch: java.lang.Throwable -> L8f
            if (r1 == 0) goto L88
            java.lang.Object r1 = r5.next()     // Catch: java.lang.Throwable -> L8f
            com.salesforce.marketingcloud.MarketingCloudSdk$c r1 = (com.salesforce.marketingcloud.MarketingCloudSdk.c) r1     // Catch: java.lang.Throwable -> L8f
            r1.a()     // Catch: java.lang.Throwable -> L8f
            goto L78
        L88:
            java.util.List<com.salesforce.marketingcloud.MarketingCloudSdk$c> r5 = com.salesforce.marketingcloud.MarketingCloudSdk.x     // Catch: java.lang.Throwable -> L8f
            r5.clear()     // Catch: java.lang.Throwable -> L8f
        L8d:
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L8f
            goto La0
        L8f:
            r5 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L8f
            throw r5     // Catch: java.lang.Throwable -> Laf
        L92:
            com.salesforce.marketingcloud.MarketingCloudSdk r5 = com.salesforce.marketingcloud.MarketingCloudSdk.y     // Catch: java.lang.Throwable -> Laf
            r5.a(r2)     // Catch: java.lang.Throwable -> Laf
            com.salesforce.marketingcloud.MarketingCloudSdk.y = r1     // Catch: java.lang.Throwable -> Laf
            java.util.List<com.salesforce.marketingcloud.MarketingCloudSdk$c> r5 = com.salesforce.marketingcloud.MarketingCloudSdk.x     // Catch: java.lang.Throwable -> Laf
            monitor-enter(r5)     // Catch: java.lang.Throwable -> Laf
            r5.clear()     // Catch: java.lang.Throwable -> Lac
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lac
        La0:
            java.lang.Object r5 = com.salesforce.marketingcloud.MarketingCloudSdk.w     // Catch: java.lang.Throwable -> Laf
            r5.notifyAll()     // Catch: java.lang.Throwable -> Laf
            if (r8 == 0) goto Laa
            r8.complete(r6)     // Catch: java.lang.Throwable -> Laf
        Laa:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Laf
            return
        Lac:
            r6 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lac
            throw r6     // Catch: java.lang.Throwable -> Laf
        Laf:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Laf
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.MarketingCloudSdk.a(android.content.Context, com.salesforce.marketingcloud.MarketingCloudConfig, com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents, com.salesforce.marketingcloud.MarketingCloudSdk$InitializationListener):void");
    }

    static void b(Context context, MarketingCloudConfig marketingCloudConfig, SFMCSdkComponents sFMCSdkComponents, InitializationListener initializationListener) {
        MarketingCloudSdk marketingCloudSdk;
        String str = v;
        g.d(str, "~~ MarketingCloudSdk v%s init() ~~", getSdkVersionName());
        com.salesforce.marketingcloud.util.g.a(context, "Context cannot be null.");
        com.salesforce.marketingcloud.util.g.a(marketingCloudConfig, "Config cannot be null.");
        com.salesforce.marketingcloud.internal.f.a(marketingCloudConfig.applicationId(), marketingCloudConfig.accessToken(), marketingCloudConfig.senderId());
        synchronized (w) {
            if ((B || A) && (marketingCloudSdk = y) != null && marketingCloudConfig.equals(marketingCloudSdk.a)) {
                g.d(str, "MarketingCloudSdk is already %s", B ? "initialized" : "initializing");
                if (isReady() && initializationListener != null) {
                    initializationListener.complete(y.p);
                }
                return;
            }
            g.d(str, "Starting initialization", new Object[0]);
            B = false;
            A = true;
            C = true;
            z = context.getApplicationContext();
            new Thread(new a(context, marketingCloudConfig, sFMCSdkComponents, initializationListener)).start();
        }
    }

    static void c() {
        MarketingCloudSdk marketingCloudSdk = y;
        if (marketingCloudSdk != null) {
            marketingCloudSdk.a(false);
        }
        y = null;
    }

    public static MarketingCloudSdk getInstance() {
        if (!A && !B) {
            throw new IllegalStateException("MarketingCloudSdk#init must be called before calling MarketingCloudSdk#getInstance.");
        }
        synchronized (w) {
            if (B) {
                return y;
            }
            boolean z2 = false;
            while (!B && A) {
                try {
                    try {
                        w.wait(0L);
                    } catch (InterruptedException unused) {
                        z2 = true;
                    }
                } finally {
                    if (z2) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            return y;
        }
    }

    @MCLogListener.LogLevel
    public static int getLogLevel() {
        return com.salesforce.marketingcloud.internal.f.a();
    }

    public static int getSdkVersionCode() {
        return com.salesforce.marketingcloud.a.e;
    }

    public static String getSdkVersionName() {
        return com.salesforce.marketingcloud.a.f;
    }

    @Deprecated(message = "Initialize the SDK with SFMCSdk.configure()")
    public static void init(Context context, MarketingCloudConfig marketingCloudConfig, InitializationListener initializationListener) {
        b(context, marketingCloudConfig, null, initializationListener);
    }

    public static boolean isInitializing() {
        return A;
    }

    public static boolean isReady() {
        return B && y != null;
    }

    public static void requestSdk(WhenReadyListener whenReadyListener) {
        requestSdk(null, whenReadyListener);
    }

    public static void setLogLevel(@MCLogListener.LogLevel int i) {
        com.salesforce.marketingcloud.internal.f.a(i);
    }

    public static void setLogListener(MCLogListener mCLogListener) {
        com.salesforce.marketingcloud.internal.f.a(mCLogListener);
    }

    public static void unregisterWhenReadyListener(WhenReadyListener whenReadyListener) {
        if (whenReadyListener == null) {
            return;
        }
        List<c> list = x;
        synchronized (list) {
            Iterator<c> it = list.iterator();
            while (it.hasNext()) {
                if (whenReadyListener == it.next().b) {
                    it.remove();
                }
            }
        }
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public AnalyticsManager getAnalyticsManager() {
        return this.o;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public EventManager getEventManager() {
        return this.n;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public InAppMessageManager getInAppMessageManager() {
        return this.q;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public InboxMessageManager getInboxMessageManager() {
        return this.i;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public InitializationStatus getInitializationStatus() {
        return this.p;
    }

    public MarketingCloudConfig getMarketingCloudConfig() {
        return this.a;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface
    public ModuleIdentity getModuleIdentity() {
        return i.a(this.a.applicationId(), getRegistrationManager());
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public NotificationManager getNotificationManager() {
        return this.k;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public PushMessageManager getPushMessageManager() {
        return this.l;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public RegionMessageManager getRegionMessageManager() {
        return this.m;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface
    public RegistrationManager getRegistrationManager() {
        return this.j;
    }

    public JSONObject getSdkState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("initConfig", this.a.toString());
            jSONObject.put("initStatus", this.p.toString());
            for (d dVar : this.b) {
                if (dVar != null) {
                    try {
                        jSONObject.put(dVar.componentName(), dVar.componentState());
                    } catch (Exception e) {
                        g.b(v, e, "Failed to create component state for %s", dVar);
                    }
                }
            }
        } catch (Exception e2) {
            g.b(v, e2, "Unable to create Sdk state json", new Object[0]);
        }
        return jSONObject;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface
    public JSONObject getState() {
        return getSdkState();
    }

    public static void requestSdk(Looper looper, WhenReadyListener whenReadyListener) {
        b bVar = new b(looper, whenReadyListener);
        List<c> list = x;
        synchronized (list) {
            if (C) {
                list.add(bVar);
            } else {
                bVar.a();
            }
        }
    }

    private void b(boolean z2) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            try {
                this.b.get(size).tearDown(z2);
            } catch (Exception e) {
                g.b(v, e, "Error encountered tearing down component.", new Object[0]);
            }
        }
        this.b.clear();
        n nVar = this.r;
        if (nVar != null) {
            nVar.c();
        }
        com.salesforce.marketingcloud.storage.h hVar = this.g;
        if (hVar != null) {
            try {
                hVar.s();
            } catch (Exception e2) {
                g.b(v, e2, "Error encountered tearing down storage.", new Object[0]);
            }
            this.g = null;
        }
        List<c> list = x;
        synchronized (list) {
            list.clear();
        }
        B = false;
        C = true;
    }

    private void a(boolean z2) {
        b(z2);
        A = false;
    }

    private InitializationStatus a(String str, MarketingCloudConfig marketingCloudConfig, boolean z2) {
        InitializationStatus.a aVar;
        String a2;
        Crypto crypto;
        if (com.salesforce.marketingcloud.util.a.a()) {
            return com.salesforce.marketingcloud.internal.e.a();
        }
        InitializationStatus.a b2 = com.salesforce.marketingcloud.internal.e.b();
        try {
            a2 = com.salesforce.marketingcloud.util.c.a(z, str);
            if (z2 || marketingCloudConfig.legacyEncryptionDependencyForciblyRemoved()) {
                crypto = null;
            } else {
                crypto = a(marketingCloudConfig, a2);
                if (crypto == null) {
                    return com.salesforce.marketingcloud.internal.e.c();
                }
            }
            Crypto crypto2 = crypto;
            try {
                this.r = new n();
                com.salesforce.marketingcloud.storage.h hVar = new com.salesforce.marketingcloud.storage.h(z, new com.salesforce.marketingcloud.util.h(this.c.getEncryptionManager()), marketingCloudConfig.applicationId(), marketingCloudConfig.accessToken(), this.r, crypto2, z2);
                this.g = hVar;
                hVar.a(b2);
            } catch (Throwable th) {
                g.a(v, th, "Unable to initialize SDK storage.", new Object[0]);
                b2.a(th);
            }
        } catch (Exception e) {
            e = e;
            aVar = b2;
        }
        if (!b2.b()) {
            com.salesforce.marketingcloud.registration.d.a(marketingCloudConfig, z, a2, str);
            return b2.a();
        }
        this.e = new com.salesforce.marketingcloud.behaviors.c(z, Executors.newSingleThreadExecutor());
        this.h = new com.salesforce.marketingcloud.http.d(z, this.g.e(), this.r);
        com.salesforce.marketingcloud.alarms.b bVar = new com.salesforce.marketingcloud.alarms.b(z, this.g, this.e);
        com.salesforce.marketingcloud.analytics.h hVar2 = new com.salesforce.marketingcloud.analytics.h(marketingCloudConfig, this.g, a2, bVar, this.e, this.h, this.r);
        this.o = hVar2;
        try {
            k kVar = new k(a2, marketingCloudConfig, this.g, this.h, this.e, bVar, this.r, hVar2);
            this.f = new com.salesforce.marketingcloud.b(kVar, this.g.j());
            this.d = com.salesforce.marketingcloud.location.f.a(z, marketingCloudConfig);
            com.salesforce.marketingcloud.proximity.e a3 = com.salesforce.marketingcloud.proximity.e.a(z, marketingCloudConfig);
            this.k = com.salesforce.marketingcloud.notifications.a.a(z, this.g, marketingCloudConfig.notificationCustomizationOptions(), hVar2);
            this.i = new com.salesforce.marketingcloud.messages.inbox.a(marketingCloudConfig, this.g, a2, this.e, bVar, this.h, this.r, hVar2);
            InitializationStatus.a aVar2 = b2;
            try {
                this.m = new com.salesforce.marketingcloud.messages.d(z, marketingCloudConfig, this.g, a2, this.d, a3, this.e, bVar, this.h, this.k, this.r, hVar2);
                this.s = o.a(z, this.g);
                this.l = new com.salesforce.marketingcloud.messages.push.a(z, this.g, this.k, bVar, marketingCloudConfig.senderId(), hVar2, this.s, this.r);
                com.salesforce.marketingcloud.registration.f fVar = new com.salesforce.marketingcloud.registration.f(a2, marketingCloudConfig.applicationId(), com.salesforce.marketingcloud.util.f.a(z));
                this.j = new com.salesforce.marketingcloud.registration.d(z, marketingCloudConfig, this.g, fVar, this.e, bVar, this.h, this.l, this.r, this.c);
                com.salesforce.marketingcloud.config.a aVar3 = new com.salesforce.marketingcloud.config.a(kVar, this.g, hVar2);
                this.q = new InAppMessageComponent(z, this.g, bVar, kVar, this.e, this.s, marketingCloudConfig.urlHandler(), this.r, hVar2, this.c, aVar3);
                this.n = new com.salesforce.marketingcloud.events.c(z, fVar, this.g, kVar, this.e, hVar2, this.r, this.c, aVar3, this.q);
                this.b.add(this.e);
                this.b.add(com.salesforce.marketingcloud.behaviors.d.a((Application) z.getApplicationContext()));
                this.b.add(this.h);
                this.b.add(bVar);
                this.b.add(hVar2);
                this.b.add(kVar);
                this.b.add(this.f);
                this.b.add(this.d);
                this.b.add(a3);
                this.b.add(this.i);
                this.b.add(this.k);
                this.b.add(this.m);
                this.b.add(this.l);
                this.b.add(this.j);
                this.b.add(aVar3);
                this.b.add(this.q);
                this.b.add(this.n);
                int a4 = this.f.a();
                g.d(v, "Initializing all components with control channel flag [%d]", Integer.valueOf(a4));
                for (d dVar : this.b) {
                    g.d(v, "init called for %s", dVar.componentName());
                    if (dVar instanceof e) {
                        aVar = aVar2;
                        try {
                            ((e) dVar).init(aVar, a4);
                        } catch (Exception e2) {
                            e = e2;
                            aVar.a(e);
                            g.b(v, e, "Something wrong with internal init", new Object[0]);
                            return aVar.a();
                        }
                    } else {
                        aVar = aVar2;
                        if (dVar instanceof f) {
                            ((f) dVar).a(aVar);
                        }
                    }
                    aVar.a(dVar);
                    aVar2 = aVar;
                }
                aVar = aVar2;
            } catch (Exception e3) {
                e = e3;
                aVar = aVar2;
            }
        } catch (Exception e4) {
            e = e4;
            aVar = b2;
        }
        return aVar.a();
    }

    public com.salesforce.marketingcloud.storage.h b() {
        return this.g;
    }

    private Crypto a(MarketingCloudConfig marketingCloudConfig, String str) {
        try {
            g.c(v, "Checking for legacy hashing dependency", new Object[0]);
            return new AesCrypto(z, marketingCloudConfig.applicationId(), marketingCloudConfig.accessToken(), str, new OldSdkHash());
        } catch (Error e) {
            g.b(v, "Legacy hashing is not available", e);
            return null;
        }
    }

    private void a(InitializationStatus initializationStatus) {
        this.p = initializationStatus;
    }

    public com.salesforce.marketingcloud.http.d a() {
        return this.h;
    }

    @Override // com.salesforce.marketingcloud.b.InterfaceC0049b
    public void a(int i) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            try {
                d dVar = this.b.get(size);
                if (dVar instanceof e) {
                    ((e) dVar).controlChannelInit(i);
                }
            } catch (Exception e) {
                g.b(v, e, "Error encountered during control channel init.", new Object[0]);
            }
        }
    }
}
