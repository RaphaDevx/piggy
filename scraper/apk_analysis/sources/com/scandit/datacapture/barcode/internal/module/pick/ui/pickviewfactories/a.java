package com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories;

import android.content.Context;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class a {
    public static final com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j a(Context context, BarcodePickViewSettings settings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settings, "viewSettings");
        Intrinsics.checkNotNullParameter(context, "context");
        com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.h factory = new com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.h(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return new com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j(context, factory, settings);
    }

    public static final com.scandit.datacapture.barcode.internal.module.pick.ui.f b(Context context, BarcodePickViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        boolean showLoadingDialog = viewSettings.getShowLoadingDialog();
        if (showLoadingDialog) {
            return new com.scandit.datacapture.barcode.internal.module.pick.ui.h(context, viewSettings.getLoadingDialogTextForPicking(), viewSettings.getLoadingDialogTextForUnpicking());
        }
        if (showLoadingDialog) {
            throw new NoWhenBranchMatchedException();
        }
        return new com.scandit.datacapture.barcode.internal.module.pick.ui.g(context);
    }
}
