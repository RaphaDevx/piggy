package com.scandit.datacapture.core.internal.module.https.scandit.client;

import com.scandit.datacapture.core.internal.module.https.scandit.AbstractC0539k;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public final class d extends TimerTask {
    public final /* synthetic */ AbstractC0539k a;

    public d(AbstractC0539k abstractC0539k) {
        this.a = abstractC0539k;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.a.cancel();
    }
}
