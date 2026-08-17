package com.tealium.internal;

import android.content.SharedPreferences;
import android.webkit.WebView;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.j.o;
import com.tealium.internal.j.t;
import com.tealium.internal.listeners.PopulateDispatchListener;
import com.tealium.internal.listeners.WebViewLoadedListener;
import com.tealium.library.Tealium;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: SessionManager.java */
/* loaded from: classes2.dex */
public final class f implements PopulateDispatchListener, WebViewLoadedListener {
    private final Tealium.Config a;
    private final d b;
    private final SharedPreferences c;
    private a d;
    private e e;
    private long f;
    private int g = 30000;
    private AtomicBoolean h = new AtomicBoolean(false);

    public f(Tealium.Config config, d dVar) {
        this.a = config;
        this.b = dVar;
        this.f = config.getMinutesBetweenSessionId();
        this.d = a.a(config.getApplication().getApplicationContext());
        SharedPreferences sharedPreferences = config.getApplication().getSharedPreferences(a(config), 0);
        this.c = sharedPreferences;
        e a = e.a(sharedPreferences);
        if (a(a, this.f)) {
            this.e = e();
        } else {
            this.e = a;
        }
    }

    private static long d() {
        return System.currentTimeMillis();
    }

    private e e() {
        e eVar = new e(d());
        this.e = eVar;
        e.a(this.c, eVar);
        this.b.b(new o(this.e.b() + ""));
        return this.e;
    }

    private void f() {
        if (this.h.get() && this.d.a()) {
            this.e.a(true);
            e.a(this.c, this.e);
            b(this.e.b() + "");
            this.b.b(new t(this.e.b() + ""));
        }
    }

    public e a() {
        return this.e;
    }

    public long b() {
        return this.f;
    }

    public void c() {
        if (a(this.e, this.f)) {
            e();
        }
        if (a(this.e)) {
            f();
        }
    }

    @Override // com.tealium.internal.listeners.PopulateDispatchListener
    public void onPopulateDispatch(Dispatch dispatch) {
        e eVar = this.e;
        eVar.a(eVar.a() + 1);
        c();
        this.e.a(d());
        e.a(this.c, this.e);
    }

    @Override // com.tealium.internal.listeners.WebViewLoadedListener
    public void onWebViewLoad(WebView webView, boolean z) {
        if (z) {
            this.h.set(true);
            if (a(this.e)) {
                f();
            }
        }
    }

    private void b(String str) {
        if (this.a.isSessionCountingEnabled()) {
            this.b.a(NetworkRequestBuilder.createGetRequest(a(str)).createRunnable());
        }
    }

    public void a(long j) {
        this.f = j;
    }

    String a(String str) {
        return String.format(Locale.ROOT, "https://tags.tiqcdn.com/utag/tiqapp/utag.v.js?a=%s/%s/%s&cb=%s", this.a.getAccountName(), this.a.getProfileName(), str, str);
    }

    private boolean a(e eVar) {
        return b(eVar, d());
    }

    private boolean b(e eVar, long j) {
        return !eVar.d() && eVar.a() > 1 && j <= eVar.c() + ((long) this.g);
    }

    static boolean a(e eVar, long j) {
        return Math.max(eVar.b(), eVar.c()) + (j * DateUtils.MILLIS_PER_MINUTE) <= d();
    }

    private static String a(Tealium.Config config) {
        return "tealium.sessionpreferences." + Integer.toHexString((config.getAccountName() + config.getProfileName() + config.getEnvironmentName()).hashCode());
    }
}
