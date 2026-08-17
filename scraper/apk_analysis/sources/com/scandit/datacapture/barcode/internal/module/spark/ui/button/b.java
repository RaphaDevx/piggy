package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements c {
    public final SparkScanViewState a;

    public b(SparkScanViewState viewState) {
        Intrinsics.checkNotNullParameter(viewState, "viewState");
        this.a = viewState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof b) && this.a == ((b) obj).a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Expanded(viewState=" + this.a + ')';
    }
}
