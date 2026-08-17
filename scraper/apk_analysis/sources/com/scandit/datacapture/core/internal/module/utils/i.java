package com.scandit.datacapture.core.internal.module.utils;

import android.util.Log;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class i {
    public static final void a(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.i("sdc-core", message);
    }

    public static final void a(String message, Exception e) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e("sdc-core", message);
        e.printStackTrace();
    }
}
