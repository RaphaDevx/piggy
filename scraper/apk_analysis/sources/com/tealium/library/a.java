package com.tealium.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.tealium.internal.listeners.DisableListener;
import com.tealium.library.Tealium;

/* compiled from: ActivityObserver.java */
/* loaded from: classes2.dex */
final class a implements DisableListener {
    private final Application a;
    private final Application.ActivityLifecycleCallbacks b;

    /* compiled from: ActivityObserver.java */
    /* renamed from: com.tealium.library.a$a, reason: collision with other inner class name */
    class C0102a implements Application.ActivityLifecycleCallbacks {
        final /* synthetic */ com.tealium.internal.d a;

        C0102a(com.tealium.internal.d dVar) {
            this.a = dVar;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            this.a.a(new com.tealium.internal.j.a(activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            this.a.a(new com.tealium.internal.j.b(activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public a(Tealium.Config config, com.tealium.internal.d dVar) {
        Application application = config.getApplication();
        this.a = application;
        Application.ActivityLifecycleCallbacks a = a(dVar);
        this.b = a;
        application.registerActivityLifecycleCallbacks(a);
    }

    private static Application.ActivityLifecycleCallbacks a(com.tealium.internal.d dVar) {
        if (dVar != null) {
            return new C0102a(dVar);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.tealium.internal.listeners.DisableListener
    public void onDisable(Tealium tealium) {
        this.a.unregisterActivityLifecycleCallbacks(this.b);
    }
}
