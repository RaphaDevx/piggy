package com.tealium.internal.j;

import android.app.Activity;
import com.tealium.internal.listeners.ActivityPauseListener;

/* compiled from: ActivityPauseMessenger.java */
/* loaded from: classes2.dex */
public class a extends n<ActivityPauseListener> {
    private final Activity b;

    public a(Activity activity) {
        super(ActivityPauseListener.class);
        this.b = activity;
        if (activity == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(ActivityPauseListener activityPauseListener) {
        activityPauseListener.onActivityPause(this.b);
    }
}
