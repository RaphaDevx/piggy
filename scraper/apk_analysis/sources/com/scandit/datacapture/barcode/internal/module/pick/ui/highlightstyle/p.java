package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.core.ui.style.BrushSerializer;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p implements BarcodePickBrush {
    public final Brush b;

    public /* synthetic */ p(Brush brush) {
        this.b = brush;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush
    public final Brush asBrush() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof p) && Intrinsics.areEqual(this.b, ((p) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush
    public final String toJson() {
        return BrushSerializer.toJson(this.b);
    }

    public final String toString() {
        return "SetBrush(brush=" + this.b + ')';
    }
}
