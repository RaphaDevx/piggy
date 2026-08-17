package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationWidthPreset;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final BarcodeArInfoAnnotationWidthPreset d;

    public i(boolean z, boolean z2, boolean z3, BarcodeArInfoAnnotationWidthPreset width) {
        Intrinsics.checkNotNullParameter(width, "width");
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = width;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return this.a == iVar.a && this.b == iVar.b && this.c == iVar.c && this.d == iVar.d;
    }

    public final int hashCode() {
        return this.d.hashCode() + ((Boolean.hashCode(this.c) + ((Boolean.hashCode(this.b) + (Boolean.hashCode(this.a) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "LayoutState(hasLeftIcon=" + this.a + ", hasRightIcon=" + this.b + ", hasText=" + this.c + ", width=" + this.d + ')';
    }
}
