package com.tealium.lifecycle;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.ActivityPauseListener;
import com.tealium.internal.listeners.ActivityResumeListener;
import com.tealium.internal.listeners.DisableListener;
import com.tealium.internal.listeners.PopulateDispatchListener;
import com.tealium.library.DataSources;
import com.tealium.library.Tealium;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class LifeCycle {
    private static final Map<String, LifeCycle> h = new ConcurrentHashMap();
    private final c a;
    private final d b;
    private final String c;
    private final com.tealium.lifecycle.a d;
    private final boolean e;
    private long f = Long.MIN_VALUE;
    private long g = Long.MIN_VALUE;

    static class a implements d {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.tealium.lifecycle.LifeCycle.d
        public boolean a(String str, Map<String, ?> map) {
            Tealium tealium = Tealium.getInstance(this.a);
            if (tealium == null) {
                return false;
            }
            if (str == null) {
                str = "lifecycle";
            }
            tealium.trackEvent(str, map);
            return true;
        }
    }

    private final class b implements Runnable {
        private final Map<String, Object> a;
        long b;

        public b(Map<String, Object> map) {
            this.a = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            LifeCycle.this.b(this.b, (Map<String, ?>) this.a);
        }
    }

    private final class c implements ActivityResumeListener, ActivityPauseListener, DisableListener, PopulateDispatchListener {
        private final b a;
        private final Handler b;
        private final Map<String, Object> c;

        c() {
            HashMap hashMap = new HashMap(1);
            hashMap.put(DataSources.Key.AUTOTRACKED, Boolean.TRUE);
            Map<String, Object> unmodifiableMap = Collections.unmodifiableMap(hashMap);
            this.c = unmodifiableMap;
            this.b = new Handler(Looper.getMainLooper());
            this.a = LifeCycle.this.new b(unmodifiableMap);
        }

        void a() {
            LifeCycle.this.d.b("disable");
            this.b.removeCallbacks(this.a);
            LifeCycle.h.remove(LifeCycle.this.c);
        }

        @Override // com.tealium.internal.listeners.ActivityPauseListener
        public void onActivityPause(Activity activity) {
            if (LifeCycle.this.e) {
                if (LifeCycle.this.f == Long.MIN_VALUE) {
                    LifeCycle lifeCycle = LifeCycle.this;
                    lifeCycle.a(lifeCycle.d.q(), (Map<String, ?>) null);
                }
                LifeCycle.this.d.b("pause");
                LifeCycle.this.g = SystemClock.elapsedRealtime();
                this.a.b = System.currentTimeMillis();
                this.b.postDelayed(this.a, 5000L);
            }
        }

        @Override // com.tealium.internal.listeners.ActivityResumeListener
        public void onActivityResume(Activity activity) {
            if (LifeCycle.this.e) {
                this.b.removeCallbacks(this.a);
                long j = LifeCycle.this.f;
                LifeCycle.this.f = SystemClock.elapsedRealtime();
                if (j == Long.MIN_VALUE) {
                    LifeCycle.this.a(System.currentTimeMillis(), (Map<String, ?>) this.c);
                } else if (LifeCycle.this.f - LifeCycle.this.g > 5000) {
                    LifeCycle.this.c(System.currentTimeMillis(), this.c);
                }
            }
        }

        @Override // com.tealium.internal.listeners.DisableListener
        public void onDisable(Tealium tealium) {
            a();
        }

        @Override // com.tealium.internal.listeners.PopulateDispatchListener
        public void onPopulateDispatch(Dispatch dispatch) {
            dispatch.putAll(LifeCycle.this.getCurrentState());
            dispatch.putIfAbsent(DataSources.Key.AUTOTRACKED, String.valueOf(false));
        }
    }

    interface d {
        boolean a(String str, Map<String, ?> map);
    }

    private LifeCycle(com.tealium.lifecycle.a aVar, d dVar, String str, Tealium.Config config, boolean z) {
        this.b = dVar;
        this.d = aVar;
        this.c = str;
        this.e = z;
        List<EventListener> eventListeners = config.getEventListeners();
        c cVar = new c();
        this.a = cVar;
        eventListeners.add(cVar);
    }

    public static LifeCycle getInstance(String str) {
        return h.get(str);
    }

    public static synchronized LifeCycle setupInstance(String str, Tealium.Config config, boolean z) {
        LifeCycle lifeCycle;
        synchronized (LifeCycle.class) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException();
            }
            lifeCycle = new LifeCycle(com.tealium.lifecycle.a.a(config), a(str), str, config, z);
            h.put(str, lifeCycle);
        }
        return lifeCycle;
    }

    public Map<String, Object> getCurrentState() {
        return a(System.currentTimeMillis());
    }

    public boolean isAutoTracking() {
        return this.e;
    }

    public void trackLaunchEvent(Map<String, ?> map) {
        if (this.e) {
            throw new UnsupportedOperationException();
        }
        a(System.currentTimeMillis(), map);
    }

    public void trackSleepEvent(Map<String, ?> map) {
        if (this.e) {
            throw new UnsupportedOperationException();
        }
        b(System.currentTimeMillis(), map);
    }

    public void trackWakeEvent(Map<String, ?> map) {
        if (this.e) {
            throw new UnsupportedOperationException();
        }
        c(System.currentTimeMillis(), map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, Map<String, ?> map) {
        this.d.w();
        Map<String, ?> a2 = a(j);
        if (map != null) {
            a2.putAll(map);
        }
        a2.put(DataSources.Key.LIFECYCLE_TYPE, "wake");
        a("wake", a2, j);
        this.d.b("wake");
        if (this.b.a("wake", a2)) {
            return;
        }
        this.a.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, Map<String, ?> map) {
        int p = (int) ((j - (this.d.p() > this.d.q() ? this.d.p() : this.d.q())) / 1000);
        this.d.v();
        this.d.a(p);
        Map<String, ?> a2 = a(j);
        if (map != null) {
            a2.putAll(map);
        }
        this.d.b("sleep");
        a2.put(DataSources.Key.LIFECYCLE_TYPE, "sleep");
        a2.put(DataSources.Key.LIFECYCLE_SECONDSAWAKE, Long.toString(p));
        this.d.f(j);
        if (this.b.a("sleep", a2)) {
            return;
        }
        this.a.a();
    }

    private Map<String, Object> a(long j) {
        HashMap hashMap = new HashMap(17);
        hashMap.put(DataSources.Key.LIFECYCLE_DAYOFWEEK_LOCAL, Integer.toString(this.d.c(j)));
        hashMap.put(DataSources.Key.LIFECYCLE_DAYSSINCELAUNCH, Long.toString((j - this.d.o()) / 86400000));
        hashMap.put(DataSources.Key.LIFECYCLE_DAYSSINCELASTWAKE, this.d.p() == Long.MIN_VALUE ? "0" : Long.toString((j - this.d.p()) / 86400000));
        hashMap.put(DataSources.Key.LIFECYCLE_HOUROFDAY_LOCAL, Integer.toString(this.d.d(j)));
        hashMap.put(DataSources.Key.LIFECYCLE_FIRSTLAUNCHDATE, this.d.h());
        hashMap.put(DataSources.Key.LIFECYCLE_FIRSTLAUNCHDATE_MMDDYYYY, this.d.i());
        hashMap.put(DataSources.Key.LIFECYCLE_LAUNCHCOUNT, Integer.valueOf(this.d.a()));
        hashMap.put(DataSources.Key.LIFECYCLE_SLEEPCOUNT, Integer.toString(this.d.b()));
        hashMap.put(DataSources.Key.LIFECYCLE_WAKECOUNT, Integer.toString(this.d.g()));
        hashMap.put(DataSources.Key.LIFECYCLE_TOTALCRASHCOUNT, Integer.valueOf(this.d.c()));
        hashMap.put(DataSources.Key.LIFECYCLE_TOTALLAUNCHCOUNT, Integer.valueOf(this.d.d()));
        hashMap.put(DataSources.Key.LIFECYCLE_TOTALSLEEPCOUNT, Integer.toString(this.d.e()));
        hashMap.put(DataSources.Key.LIFECYCLE_TOTALWAKECOUNT, Integer.toString(this.d.f()));
        hashMap.put(DataSources.Key.LIFECYCLE_TOTALSECONDSAWAKE, Integer.toString(this.d.s()));
        if (this.d.j() != null) {
            hashMap.put(DataSources.Key.LIFECYCLE_LASTLAUNCHDATE, this.d.j());
        }
        if (this.d.l() != null) {
            hashMap.put(DataSources.Key.LIFECYCLE_LASTWAKEDATE, this.d.l());
        }
        if (this.d.k() != null) {
            hashMap.put(DataSources.Key.LIFECYCLE_LASTSLEEPDATE, this.d.k());
        }
        if (this.d.r() != Long.MIN_VALUE) {
            hashMap.put(DataSources.Key.LIFECYCLE_UPDATELAUNCHDATE, this.d.n());
            hashMap.put(DataSources.Key.LIFECYCLE_DAYSSINCEUPDATE, Long.toString((j - this.d.r()) / 86400000));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, Map<String, ?> map) {
        boolean a2 = this.d.a(j);
        boolean b2 = this.d.b(j);
        this.d.u();
        this.d.w();
        Map<String, ?> a3 = a(j);
        if (map != null) {
            a3.putAll(map);
        }
        a3.put(DataSources.Key.LIFECYCLE_TYPE, "launch");
        this.d.e(j);
        a("launch", a3, j);
        this.d.b("launch");
        a3.put(DataSources.Key.LIFECYCLE_PRIORSECONDSAWAKE, this.d.m());
        if (a2) {
            a3.put(DataSources.Key.LIFECYCLE_ISFIRSTLAUNCH, String.valueOf(true));
        }
        if (b2) {
            a3.put(DataSources.Key.LIFECYCLE_ISFIRSTLAUNCHUPDATE, String.valueOf(true));
        }
        if (this.b.a("launch", a3)) {
            return;
        }
        this.a.a();
    }

    private void a(String str, Map<String, Object> map, long j) {
        long p = this.d.p();
        this.d.g(j);
        if (p == Long.MIN_VALUE) {
            map.put(DataSources.Key.LIFECYCLE_ISFIRSTWAKEMONTH, String.valueOf(true));
            map.put(DataSources.Key.LIFECYCLE_ISFIRSTWAKETODAY, String.valueOf(true));
            return;
        }
        if (this.d.a(str)) {
            map.put(DataSources.Key.LIFECYCLE_DIDDETECTCRASH, String.valueOf(true));
            map.put(DataSources.Key.LIFECYCLE_TOTALCRASHCOUNT, Integer.valueOf(this.d.c()));
        }
        int a2 = this.d.a(p, j);
        if (com.tealium.lifecycle.a.b(a2)) {
            map.put(DataSources.Key.LIFECYCLE_ISFIRSTWAKEMONTH, String.valueOf(true));
        }
        if (com.tealium.lifecycle.a.c(a2)) {
            map.put(DataSources.Key.LIFECYCLE_ISFIRSTWAKETODAY, Boolean.toString(true));
        }
    }

    private static d a(String str) {
        return new a(str);
    }
}
