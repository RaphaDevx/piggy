package com.scandit.datacapture.barcode.pick.serialization;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function3 {
    public static final d a = new d();

    public d() {
        super(3);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BarcodePickViewHighlightStyle.RectangularWithIcons style = (BarcodePickViewHighlightStyle.RectangularWithIcons) obj;
        ScanditIcon scanditIcon = (ScanditIcon) obj2;
        BarcodePickState state = (BarcodePickState) obj3;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(scanditIcon, "scanditIcon");
        Intrinsics.checkNotNullParameter(state, "state");
        style.setIconForState(scanditIcon, state);
        return Unit.INSTANCE;
    }
}
