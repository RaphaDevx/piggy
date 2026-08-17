package com.scandit.capacitor.datacapture.barcode.factories;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: BarcodeCaptureActionFactory.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/factories/BarcodeCaptureActionFactory;", "", "<init>", "()V", "ACTION_SELECTION_UPDATED", "", "ACTION_SELECTION_SESSION_UPDATED", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCaptureActionFactory {
    public static final String ACTION_SELECTION_SESSION_UPDATED = "BarcodeSelectionListener.didUpdateSession";
    public static final String ACTION_SELECTION_UPDATED = "BarcodeSelectionListener.didUpdateSelection";
    public static final BarcodeCaptureActionFactory INSTANCE = new BarcodeCaptureActionFactory();

    private BarcodeCaptureActionFactory() {
    }
}
