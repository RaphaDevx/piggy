package com.scandit.datacapture.core.internal.module.source.api2;

import android.os.Handler;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class B implements Executor {
    public final Handler a;

    public B(Handler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = handler;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (runnable != null) {
            this.a.post(runnable);
        }
    }
}
