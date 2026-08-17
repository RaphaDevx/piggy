package com.tealium.library;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.data.DispatchStore;
import com.tealium.internal.data.DispatchStoreImpl;
import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.data.UserConsentPreferences;
import com.tealium.internal.dispatcher.CollectDispatcher;
import com.tealium.internal.dispatcher.WebViewDispatcher;
import com.tealium.internal.j.i;
import com.tealium.internal.j.j;
import com.tealium.internal.j.l;
import com.tealium.internal.j.s;
import com.tealium.internal.listeners.AddRemoteCommandListener;
import com.tealium.internal.listeners.BatteryUpdateListener;
import com.tealium.internal.listeners.DispatchReadyListener;
import com.tealium.internal.listeners.PublishSettingsUpdateListener;
import com.tealium.internal.listeners.RemoveRemoteCommandListener;
import com.tealium.internal.listeners.RequestFlushListener;
import com.tealium.internal.listeners.TraceUpdateListener;
import com.tealium.internal.listeners.UserConsentPreferencesUpdateListener;
import com.tealium.internal.listeners.WebViewCrashedListener;
import com.tealium.internal.listeners.WebViewLoadedListener;
import com.tealium.library.ConsentManager;
import com.tealium.library.DataSources;
import com.tealium.library.Tealium;
import com.tealium.remotecommands.RemoteCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: DispatchRouter.java */
/* loaded from: classes2.dex */
final class c implements WebViewLoadedListener, WebViewCrashedListener, DispatchReadyListener, PublishSettingsUpdateListener, UserConsentPreferencesUpdateListener, BatteryUpdateListener, AddRemoteCommandListener, RemoveRemoteCommandListener, TraceUpdateListener, RequestFlushListener {
    private final Tealium a;
    private final Tealium.Config b;
    private final com.tealium.internal.d c;
    private final com.tealium.internal.a d;
    private final DispatchValidator[] e;
    private DispatchStore f;
    private final com.tealium.internal.c g;
    private final String h;
    private final List<RemoteCommand> i;
    private volatile WebViewDispatcher j;
    private CollectDispatcher k;
    private PublishSettings l;
    private boolean m;
    private boolean n;
    private String o;
    private volatile boolean p;
    private final boolean q;
    private final AtomicBoolean r;
    private Timer s;
    private TimerTask t;

    /* compiled from: DispatchRouter.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.a();
        }
    }

    /* compiled from: DispatchRouter.java */
    class b extends TimerTask {
        b() {
        }

        @Override // java.util.TimerTask
        public boolean cancel() {
            c.this.r.set(false);
            return super.cancel();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.this.c.b(new s("timeout"));
            cancel();
        }
    }

    /* compiled from: DispatchRouter.java */
    /* renamed from: com.tealium.library.c$c, reason: collision with other inner class name */
    class RunnableC0103c implements Runnable {
        RunnableC0103c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            WebViewDispatcher webViewDispatcher = c.this.j;
            if (webViewDispatcher == null) {
                return;
            }
            for (int i = 0; i < c.this.i.size(); i++) {
                webViewDispatcher.getTagBridge().a((RemoteCommand) c.this.i.get(i));
            }
        }
    }

    /* compiled from: DispatchRouter.java */
    class d implements DispatchStore {
        d(c cVar) {
        }

        @Override // com.tealium.internal.data.DispatchStore
        public Dispatch[] dequeueDispatches() {
            return new Dispatch[0];
        }

        @Override // com.tealium.internal.data.DispatchStore
        public void enqueueDispatch(Dispatch dispatch) {
        }

        @Override // com.tealium.internal.data.DispatchStore
        public int getCount() {
            return 0;
        }

        @Override // com.tealium.internal.data.DispatchStore
        public void purgeUserNotConsented(Dispatch dispatch) {
        }

        @Override // com.tealium.internal.data.DispatchStore
        public void update(int i, float f) {
        }
    }

    c(Tealium tealium, Tealium.Config config, com.tealium.internal.d dVar, DataSources dataSources) {
        this(tealium, config, dVar, dataSources, com.tealium.internal.a.a(config.getApplication()));
    }

    private boolean f() {
        boolean isCollectEnabled = this.l.isCollectEnabled();
        if (isCollectEnabled && !this.l.isTagManagementEnabled()) {
            return true;
        }
        if (this.l.isTagManagementEnabled() && this.n) {
            return true;
        }
        return isCollectEnabled && this.l.isTagManagementEnabled() && this.m;
    }

    private TimerTask g() {
        return new b();
    }

    private void h() {
        if (this.q) {
            this.t.cancel();
            this.t = g();
        }
    }

    private boolean i() {
        return this.p && this.l.isBatterySaver();
    }

    private boolean j() {
        return !(this.l.isWifiOnlySending() ? this.d.b() : this.d.a());
    }

    private boolean k() {
        return this.b.isConsentManagerEnabled() && "unknown".equals(this.b.getConsentManager().getUserConsentStatus());
    }

    private void l() {
        if (this.q && this.r.compareAndSet(false, true)) {
            this.s.schedule(this.t, this.b.getSecondsBeforeBatchTimeout() * 1000);
        }
    }

    @Override // com.tealium.internal.listeners.AddRemoteCommandListener
    public void onAddRemoteCommand(RemoteCommand remoteCommand) {
        this.i.add(remoteCommand);
        WebViewDispatcher webViewDispatcher = this.j;
        if (webViewDispatcher == null) {
            return;
        }
        webViewDispatcher.getTagBridge().a(remoteCommand);
    }

    @Override // com.tealium.internal.listeners.BatteryUpdateListener
    public void onBatteryUpdate(boolean z) {
        this.p = z;
    }

    @Override // com.tealium.internal.listeners.DispatchReadyListener
    public void onDispatchReady(Dispatch dispatch) {
        if (b(dispatch)) {
            return;
        }
        if (this.l.isBatchingEnabled() && a(1)) {
            l();
        }
        if (d(dispatch)) {
            dispatch.putIfAbsent(DataSources.Key.WAS_QUEUED, String.valueOf(true));
            this.f.enqueueDispatch(dispatch);
            this.c.b(new j(dispatch));
        } else {
            dispatch.putIfAbsent(DataSources.Key.WAS_QUEUED, String.valueOf(false));
            if (this.f.getCount() > 0) {
                a(dispatch);
            } else {
                this.c.b(new l(dispatch));
            }
        }
    }

    @Override // com.tealium.internal.listeners.PublishSettingsUpdateListener
    public void onPublishSettingsUpdate(PublishSettings publishSettings) {
        this.l = publishSettings;
        this.f.update(publishSettings.getOfflineDispatchLimit(), this.l.getDispatchExpiration());
        if (this.l.getSource() == null) {
            return;
        }
        c();
        d();
        a();
    }

    @Override // com.tealium.internal.listeners.RemoveRemoteCommandListener
    public void onRemoveRemoteCommand(RemoteCommand remoteCommand) {
        this.i.remove(remoteCommand);
        WebViewDispatcher webViewDispatcher = this.j;
        if (webViewDispatcher == null) {
            return;
        }
        webViewDispatcher.getTagBridge().b(remoteCommand);
    }

    @Override // com.tealium.internal.listeners.RequestFlushListener
    public void onRequestFlush(String str) {
        if (this.f.getCount() == 0) {
            return;
        }
        this.g.a(R.string.dispatch_router_flush_reason, str);
        a(null, true);
    }

    @Override // com.tealium.internal.listeners.TraceUpdateListener
    public void onTraceUpdate(String str, boolean z) {
        if (TextUtils.equals(this.o, str)) {
            return;
        }
        if (TextUtils.isEmpty(this.o)) {
            this.g.c(R.string.dispatch_router_join_trace, str);
        } else if (TextUtils.isEmpty(str)) {
            this.g.c(R.string.dispatch_router_leave_trace, this.o);
        } else {
            this.g.c(R.string.dispatch_router_update_trace, this.o, str);
        }
        this.o = str;
        CollectDispatcher collectDispatcher = this.k;
        if (collectDispatcher != null) {
            collectDispatcher.setTraceId(str);
        }
        if (this.j != null) {
            this.j.setTraceId(str, !z);
        }
    }

    @Override // com.tealium.internal.listeners.UserConsentPreferencesUpdateListener
    public void onUserConsentPreferencesUpdate(UserConsentPreferences userConsentPreferences) {
        if (ConsentManager.ConsentStatus.CONSENTED.equals(userConsentPreferences.getConsentStatus())) {
            a();
        } else if (ConsentManager.ConsentStatus.NOT_CONSENTED.equals(userConsentPreferences.getConsentStatus())) {
            b();
        }
    }

    @Override // com.tealium.internal.listeners.WebViewCrashedListener
    public void onWebViewCrashed(WebView webView) {
        this.m = false;
        this.n = false;
    }

    @Override // com.tealium.internal.listeners.WebViewLoadedListener
    public void onWebViewLoad(WebView webView, boolean z) {
        this.m = true;
        this.n = z;
        this.c.c(new a());
    }

    private boolean a(int i) {
        return this.f.getCount() + i < this.l.getEventBatchSize();
    }

    private boolean b(Dispatch dispatch, boolean z) {
        int i = 0;
        int i2 = dispatch == null ? 0 : 1;
        boolean z2 = a(i2) && !z;
        if (!z2) {
            z2 = i();
            if (!z2) {
                z2 = j();
                if (!z2) {
                    z2 = k();
                    if (!z2) {
                        boolean f = f();
                        z2 = !f;
                        if (!f && dispatch != null) {
                            this.g.a(R.string.dispatch_queue_debug_queued_dispatcher_not_ready, dispatch);
                        }
                    } else if (dispatch != null) {
                        this.g.a(R.string.dispatch_queue_debug_queued_user_preferences_unknown, dispatch);
                    }
                } else if (dispatch != null) {
                    this.g.a(this.l.isWifiOnlySending() ? R.string.dispatch_queue_debug_queued_no_wifi : R.string.dispatch_queue_debug_queued_no_network, dispatch);
                }
            } else if (dispatch != null) {
                this.g.a(R.string.dispatch_queue_debug_queued_battery_low, dispatch);
            }
        } else if (dispatch != null) {
            this.g.a(R.string.dispatch_queue_debug_queued_batch, dispatch, Integer.valueOf(this.f.getCount() + i2), Integer.valueOf(this.l.getEventBatchSize()));
        }
        if (dispatch != null) {
            while (true) {
                DispatchValidator[] dispatchValidatorArr = this.e;
                if (i >= dispatchValidatorArr.length || (z2 = dispatchValidatorArr[i].shouldQueue(dispatch, z2))) {
                    break;
                }
                i++;
            }
        }
        return z2;
    }

    private void c() {
        CollectDispatcher collectDispatcher;
        if (this.l.isCollectEnabled() && this.k == null) {
            CollectDispatcher collectDispatcher2 = new CollectDispatcher(this.b, this.c, this.g, this.h);
            this.k = collectDispatcher2;
            this.c.a(collectDispatcher2);
            this.k.setTraceId(this.o);
            return;
        }
        if (this.l.isCollectEnabled() || (collectDispatcher = this.k) == null) {
            return;
        }
        this.c.b(collectDispatcher);
        this.k = null;
    }

    private void d() {
        if (this.l.isTagManagementEnabled() && this.j == null) {
            Tealium.Config config = this.b;
            com.tealium.internal.d dVar = this.c;
            this.j = new WebViewDispatcher(config, dVar, new com.tealium.internal.k.c(this.a, config, dVar));
            this.c.a(this.j);
            this.c.b(e());
            this.j.setTraceId(this.o, false);
            return;
        }
        if (this.l.isTagManagementEnabled() || this.j == null) {
            return;
        }
        this.c.b(this.j);
        this.j = null;
        this.m = false;
        this.n = false;
    }

    private Runnable e() {
        return new RunnableC0103c();
    }

    private c(Tealium tealium, Tealium.Config config, com.tealium.internal.d dVar, DataSources dataSources, com.tealium.internal.a aVar) {
        this.r = new AtomicBoolean(false);
        this.a = tealium;
        this.b = config;
        this.h = dataSources.getVisitorId();
        this.g = config.getLogger();
        this.f = a(config);
        this.e = (DispatchValidator[]) config.getDispatchValidators().toArray(new DispatchValidator[config.getDispatchValidators().size()]);
        this.c = dVar;
        this.d = aVar;
        this.i = new LinkedList();
        boolean z = config.getSecondsBeforeBatchTimeout() > 0;
        this.q = z;
        if (z) {
            this.s = new Timer(true);
            this.t = g();
        }
        onPublishSettingsUpdate(config.getPublishSettings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        a(null, false);
    }

    private void a(Dispatch dispatch) {
        a(dispatch, false);
    }

    private void a(Dispatch dispatch, boolean z) {
        if (this.f.getCount() == 0 || b(dispatch, z)) {
            return;
        }
        h();
        Dispatch[] dequeueDispatches = this.f.dequeueDispatches();
        if (dispatch != null) {
            dequeueDispatches = (Dispatch[]) Arrays.copyOf(dequeueDispatches, dequeueDispatches.length + 1);
            dequeueDispatches[dequeueDispatches.length - 1] = dispatch;
        }
        if (b(dequeueDispatches)) {
            a(dequeueDispatches);
            return;
        }
        for (Dispatch dispatch2 : dequeueDispatches) {
            this.c.b(new l(dispatch2));
        }
    }

    private boolean c(Dispatch dispatch) {
        String str = (String) dispatch.get(DataSources.Key.TEALIUM_EVENT);
        return (str == null || !str.equals("update_consent_cookie")) && this.b.isConsentManagerEnabled() && ConsentManager.ConsentStatus.NOT_CONSENTED.equals(this.b.getConsentManager().getUserConsentStatus());
    }

    private boolean d(Dispatch dispatch) {
        return b(dispatch, false);
    }

    private void a(Dispatch[] dispatchArr) {
        int eventMaxBatchSize = this.l.getEventMaxBatchSize();
        ArrayList arrayList = new ArrayList(eventMaxBatchSize);
        for (int i = 0; i < dispatchArr.length; i++) {
            arrayList.add(dispatchArr[i]);
            if (arrayList.size() == eventMaxBatchSize) {
                this.c.b(new com.tealium.internal.j.e(arrayList));
                arrayList = new ArrayList(eventMaxBatchSize);
            } else if (i == dispatchArr.length - 1) {
                this.c.b(new com.tealium.internal.j.e(arrayList));
            }
        }
    }

    private DispatchStore a(Tealium.Config config) {
        try {
            return new DispatchStoreImpl(config);
        } catch (Exception unused) {
            Log.e(BuildConfig.TAG, "Error creating database. Queue settings will not work as expected.");
            return new d(this);
        }
    }

    private boolean b(Dispatch dispatch) {
        int i = 0;
        while (true) {
            DispatchValidator[] dispatchValidatorArr = this.e;
            if (i < dispatchValidatorArr.length) {
                DispatchValidator dispatchValidator = dispatchValidatorArr[i];
                if (dispatchValidator.shouldDrop(dispatch)) {
                    this.g.a(R.string.dispatch_queue_debug_format_suppressed_by, dispatchValidator, dispatch);
                    return true;
                }
                i++;
            } else {
                if (!c(dispatch)) {
                    return false;
                }
                this.g.a(R.string.dispatch_queue_debug_format_suppressed_no_consent, dispatch);
                return true;
            }
        }
    }

    private boolean b(Dispatch[] dispatchArr) {
        return dispatchArr.length > 1 && this.l.isBatchingEnabled();
    }

    private void b() {
        if (this.f.getCount() == 0) {
            return;
        }
        Dispatch[] dequeueDispatches = this.f.dequeueDispatches();
        for (int i = 0; i < dequeueDispatches.length; i++) {
            if (b(dequeueDispatches[i])) {
                this.f.purgeUserNotConsented(dequeueDispatches[i]);
                this.c.b(new i(dequeueDispatches[i]));
            }
        }
    }
}
