package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.widget.ImageButton;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o extends ImageButton {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackground(null);
        setImageResource(R.drawable.sc_ic_single_scan);
        setContentDescription(BarcodeCountViewDefaults.INSTANCE.getSingleScanButtonContentDescription());
    }
}
