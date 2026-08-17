package com.scandit.datacapture.core.internal.module.device;

import android.content.Context;
import android.os.BatteryManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements a {
    public final Context a;
    public final Lazy b;

    public c(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        this.b = LazyKt.lazy(new b(this));
    }

    public final NativeBatteryData a() {
        return new NativeBatteryData(((BatteryManager) this.b.getValue()).getIntProperty(4));
    }
}
