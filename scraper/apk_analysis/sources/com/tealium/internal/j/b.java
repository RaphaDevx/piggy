package com.tealium.internal.j;

import android.app.Activity;
import com.tealium.internal.listeners.ActivityResumeListener;

/* compiled from: ActivityResumeMessenger.java */
/* loaded from: classes2.dex */
public class b extends n<ActivityResumeListener> {
    private final Activity b;

    public b(Activity activity) {
        super(ActivityResumeListener.class);
        this.b = activity;
    }

    @Override // com.tealium.internal.j.n
    public void a(ActivityResumeListener activityResumeListener) {
        activityResumeListener.onActivityResume(this.b);
    }
}
