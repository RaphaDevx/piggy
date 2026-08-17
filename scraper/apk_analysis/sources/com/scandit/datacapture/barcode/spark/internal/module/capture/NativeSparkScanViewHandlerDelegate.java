package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanViewHandlerDelegate {
    public abstract void enableHapticWhenOverlapping();

    public abstract void onStateTransition(SparkScanViewState sparkScanViewState);

    public abstract void onSwitchedAwayFromSmartScanSelection();

    public abstract void onSwitchedToSmartScanSelection();

    public abstract void resetAllTimers();
}
