package com.scandit.datacapture.core.internal.sdk;

import com.scandit.internal.sdk.bar.Callable;
import com.scandit.internal.sdk.bar.HandlerThread;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends HandlerThread {
    public final d a;

    public e(String name, Callable function, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        d dVar = new d(name, function, i);
        this.a = dVar;
        dVar.start();
    }

    @Override // com.scandit.internal.sdk.bar.HandlerThread
    public final void quitSafely() {
        try {
            this.a.quitSafely();
        } catch (InterruptedException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
        }
    }
}
