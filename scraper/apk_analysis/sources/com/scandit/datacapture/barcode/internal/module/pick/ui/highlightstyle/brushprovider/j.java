package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider;

import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements i {
    public final Function1 a;
    public final Function1 b;

    public j(Function1 brushGetter, Function1 selectedBrushGetter) {
        Intrinsics.checkNotNullParameter(brushGetter, "brushGetter");
        Intrinsics.checkNotNullParameter(selectedBrushGetter, "selectedBrushGetter");
        this.a = brushGetter;
        this.b = selectedBrushGetter;
    }

    public final BarcodePickBrush a(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Brush brush = (Brush) this.a.invoke(state);
        if (brush == null) {
            return k.b;
        }
        Intrinsics.checkNotNullParameter(brush, "brush");
        return new p(brush);
    }
}
