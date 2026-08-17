package com.scandit.datacapture.barcode.internal.module.extensions;

import android.util.SparseArray;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class c {
    public static final void a(SparseArray sparseArray, Function1 action) {
        Intrinsics.checkNotNullParameter(sparseArray, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            Object valueAt = sparseArray.valueAt(i);
            if (valueAt != null) {
                action.invoke(valueAt);
            }
        }
    }
}
