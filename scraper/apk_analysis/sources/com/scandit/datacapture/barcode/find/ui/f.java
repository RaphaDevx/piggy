package com.scandit.datacapture.barcode.find.ui;

import android.util.Size;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f implements ScreenSizeCategoryHandler {
    public final ScreenSizeCategory a;

    public f(ScreenSizeCategory screenCategory) {
        Intrinsics.checkNotNullParameter(screenCategory, "screenCategory");
        this.a = screenCategory;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler
    public final ScreenSizeCategory getScreenCategory() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler
    public final void onPreviewSizeMeasured(Size size, Size parentSize) {
        Intrinsics.checkNotNullParameter(size, "size");
        Intrinsics.checkNotNullParameter(parentSize, "parentSize");
    }
}
