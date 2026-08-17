package com.scandit.datacapture.barcode.pick.serialization;

import android.graphics.Bitmap;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function3 {
    public static final g a = new g();

    public g() {
        super(3);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BarcodePickViewHighlightStyle.RectangularWithIcons style = (BarcodePickViewHighlightStyle.RectangularWithIcons) obj;
        BarcodePickState state = (BarcodePickState) obj3;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(state, "state");
        style.setSelectedIconForState((Bitmap) obj2, state);
        return Unit.INSTANCE;
    }
}
