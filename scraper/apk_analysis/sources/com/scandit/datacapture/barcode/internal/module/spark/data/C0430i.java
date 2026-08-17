package com.scandit.datacapture.barcode.internal.module.spark.data;

import android.content.SharedPreferences;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0430i {
    public final void a(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        if (sharedPreferences.getInt("com.scandit.barcode.spark_capture-version", -1) < 3) {
            sharedPreferences.edit().clear().putInt("com.scandit.barcode.spark_capture-version", 3).apply();
        }
    }
}
