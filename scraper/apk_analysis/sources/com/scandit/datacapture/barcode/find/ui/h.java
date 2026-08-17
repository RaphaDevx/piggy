package com.scandit.datacapture.barcode.find.ui;

import android.content.Context;
import android.util.Size;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements ScreenSizeCategoryHandler {
    public final Function1 a;
    public final int b;
    public final int c;
    public ScreenSizeCategory d;

    public h() {
        g isTallFormFactor = new g();
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        this.a = isTallFormFactor;
        this.b = 120;
        this.c = 200;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler
    public final ScreenSizeCategory getScreenCategory() {
        ScreenSizeCategory screenSizeCategory = this.d;
        if (screenSizeCategory != null) {
            return screenSizeCategory;
        }
        throw new IllegalStateException("An unexpected error occurred setting up views in BarcodeFind. \nThe screen category wasn't initialized while the views are being laid out and \npositioned.                  ".toString());
    }

    @Override // com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler
    public final void onPreviewSizeMeasured(Size size, Size parentSize) {
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(parentSize, "parentSize");
        boolean booleanValue = ((Boolean) this.a.invoke(parentSize)).booleanValue();
        float dpFromPx$default = PixelExtensionsKt.dpFromPx$default((booleanValue ? parentSize.getHeight() : parentSize.getWidth()) - (booleanValue ? size.getHeight() : size.getWidth()), (Context) null, 1, (Object) null);
        this.d = dpFromPx$default < ((float) this.b) ? ScreenSizeCategory.SMALL : dpFromPx$default < ((float) this.c) ? ScreenSizeCategory.MID : ScreenSizeCategory.LARGE;
    }
}
