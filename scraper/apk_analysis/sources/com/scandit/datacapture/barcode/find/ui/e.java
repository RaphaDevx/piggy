package com.scandit.datacapture.barcode.find.ui;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes2.dex */
public final class e implements PreviewResolutionRatioHandler {
    public float a = -1.0f;
    public Function1 b;

    static {
        final DefaultConstructorMarker defaultConstructorMarker = null;
        new Object(defaultConstructorMarker) { // from class: com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandlerImpl$Companion
        };
    }

    @Override // com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler
    public final boolean getCloseToFourByThree() {
        float f = this.a;
        return f != -1.0f && Math.abs(f - 1.3333334f) < 0.1f;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler
    public final Function1 getListener() {
        return this.b;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler
    public final float getRatio() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler
    public final void onResolutionChanged(int i, int i2) {
        float max = Math.max(i, i2) / Math.min(i, i2);
        this.a = max;
        Function1 function1 = this.b;
        if (function1 != null) {
            function1.invoke(Float.valueOf(max));
        }
    }

    @Override // com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler
    public final void setListener(Function1 function1) {
        this.b = function1;
    }
}
