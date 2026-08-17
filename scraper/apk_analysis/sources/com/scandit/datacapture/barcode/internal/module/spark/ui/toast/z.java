package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import android.os.Handler;
import android.os.Looper;
import com.scandit.datacapture.core.time.TimeInterval;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z implements y {
    public final Handler a = new Handler(Looper.getMainLooper());

    public final void a(final r onTimeout, TimeInterval timeout) {
        Intrinsics.checkNotNullParameter(onTimeout, "onTimeout");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.a.postDelayed(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.toast.z$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                z.a(Function0.this);
            }
        }, timeout.asMillis());
    }

    public static final void a(Function0 onTimeout) {
        Intrinsics.checkNotNullParameter(onTimeout, "$onTimeout");
        onTimeout.invoke();
    }
}
