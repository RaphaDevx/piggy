package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j {
    public final boolean a;

    public j(BarcodePickViewSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a = settings.getDrawDebugInfo();
    }
}
