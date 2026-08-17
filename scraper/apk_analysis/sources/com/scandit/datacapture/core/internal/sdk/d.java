package com.scandit.datacapture.core.internal.sdk;

import android.os.HandlerThread;
import com.scandit.internal.sdk.bar.Callable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d extends HandlerThread {
    public final Callable a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(String name, Callable function, int i) {
        super(name, i);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(function, "function");
        this.a = function;
    }

    @Override // android.os.HandlerThread
    public final void onLooperPrepared() {
        super.onLooperPrepared();
        this.a.run();
    }
}
