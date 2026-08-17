package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k {
    public final long a;
    public final boolean b;
    public final a c;

    public k(long j, boolean z, a augmentationData) {
        Intrinsics.checkNotNullParameter(augmentationData, "augmentationData");
        this.a = j;
        this.b = z;
        this.c = augmentationData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return this.a == kVar.a && this.b == kVar.b && Intrinsics.areEqual(this.c, kVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((Boolean.hashCode(this.b) + (Long.hashCode(this.a) * 31)) * 31);
    }

    public final String toString() {
        return "BarcodeArPendingRemovalData(removeAtTimestamp=" + this.a + ", wasAnnotationShowing=" + this.b + ", augmentationData=" + this.c + ')';
    }
}
