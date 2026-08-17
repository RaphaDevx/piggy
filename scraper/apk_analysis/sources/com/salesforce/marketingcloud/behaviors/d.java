package com.salesforce.marketingcloud.behaviors;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.f;
import com.salesforce.marketingcloud.g;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class d extends f implements LifecycleObserver {
    static d g;
    private final Application d;
    private final AtomicBoolean e = new AtomicBoolean(false);
    AtomicBoolean f = new AtomicBoolean(false);

    private d(Application application) {
        this.d = application;
    }

    public static synchronized d a(Application application) {
        d dVar;
        synchronized (d.class) {
            if (g == null) {
                g = new d(application);
            }
            dVar = g;
        }
        return dVar;
    }

    @Override // com.salesforce.marketingcloud.d
    public String componentName() {
        return "LifecycleManager";
    }

    @Override // com.salesforce.marketingcloud.d
    public JSONObject componentState() {
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onApplicationBackgrounded() {
        if (this.f.getAndSet(false)) {
            g.a(c.k, "Application went into the background.", new Object[0]);
            c.a(this.d, a.BEHAVIOR_APP_BACKGROUNDED, new Bundle());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onApplicationForegrounded() {
        if (this.f.getAndSet(true) || !this.e.get()) {
            return;
        }
        g.a(c.k, "Application came into the foreground.", new Object[0]);
        c.a(this.d, a.BEHAVIOR_APP_FOREGROUNDED, new Bundle());
    }

    @Override // com.salesforce.marketingcloud.f, com.salesforce.marketingcloud.d
    public void tearDown(boolean z) {
        this.e.set(false);
    }

    @Override // com.salesforce.marketingcloud.f
    protected void a(InitializationStatus.a aVar) {
        this.e.set(true);
        if (this.f.get()) {
            c.a(this.d, a.BEHAVIOR_APP_FOREGROUNDED, null);
        }
    }
}
