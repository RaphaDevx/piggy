package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.R;
import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;
import com.scandit.datacapture.core.source.TorchState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.l, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0463l {
    public static StateListDrawable a(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (z) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.core.R.drawable.sc_ic_zoomed_in_pressed));
            stateListDrawable.addState(new int[0], context.getDrawable(com.scandit.datacapture.core.R.drawable.sc_ic_zoomed_in));
            return stateListDrawable;
        }
        if (z) {
            throw new NoWhenBranchMatchedException();
        }
        StateListDrawable stateListDrawable2 = new StateListDrawable();
        stateListDrawable2.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.core.R.drawable.sc_ic_zoomed_out_pressed));
        stateListDrawable2.addState(new int[0], context.getDrawable(com.scandit.datacapture.core.R.drawable.sc_ic_zoomed_out));
        return stateListDrawable2;
    }

    public static StateListDrawable a(Context context, SparkScanMiniPreviewSize miniPreviewSize) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(miniPreviewSize, "miniPreviewSize");
        int i = AbstractC0462k.a[miniPreviewSize.ordinal()];
        if (i == 1) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_spark_scan_collapsed_pressed));
            stateListDrawable.addState(new int[0], context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_spark_scan_collapsed));
            return stateListDrawable;
        }
        if (i == 2) {
            StateListDrawable stateListDrawable2 = new StateListDrawable();
            stateListDrawable2.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_spark_scan_expanded_pressed));
            stateListDrawable2.addState(new int[0], context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_spark_scan_expanded));
            return stateListDrawable2;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static StateListDrawable a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_close_pressed));
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_close));
        stateListDrawable.addState(new int[0], context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_close_disabled));
        return stateListDrawable;
    }

    public static StateListDrawable a(Context context, TorchState torchState) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(torchState, "torchState");
        if (AbstractC0462k.b[torchState.ordinal()] == 1) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_torch_on_pressed));
            stateListDrawable.addState(new int[0], context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_torch_on));
            return stateListDrawable;
        }
        StateListDrawable stateListDrawable2 = new StateListDrawable();
        stateListDrawable2.addState(new int[]{R.attr.state_pressed}, context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_torch_off_pressed));
        stateListDrawable2.addState(new int[0], context.getDrawable(com.scandit.datacapture.barcode.R.drawable.sc_ic_spark_scan_torch_off));
        return stateListDrawable2;
    }
}
