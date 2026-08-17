package com.scandit.datacapture.barcode.pick.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionCallback;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"asNative", "Lcom/scandit/datacapture/barcode/internal/module/pick/capture/NativeBarcodePickActionListener;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickActionListener;", "scandit-barcode-capture"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickActionListenerKt {
    public static final NativeBarcodePickActionListener asNative(final BarcodePickActionListener barcodePickActionListener) {
        Intrinsics.checkNotNullParameter(barcodePickActionListener, "<this>");
        return new NativeBarcodePickActionListener() { // from class: com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListenerKt$asNative$1
            @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionListener
            public void onObservationStarted(NativeBarcodePick mode) {
                Intrinsics.checkNotNullParameter(mode, "mode");
            }

            @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionListener
            public void onObservationStopped(NativeBarcodePick mode) {
                Intrinsics.checkNotNullParameter(mode, "mode");
            }

            @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionListener
            public void onPick(String id, NativeBarcodePickActionCallback actionCallback) {
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(actionCallback, "actionCallback");
                BarcodePickActionListener.this.onPick(id, new BarcodePickActionCallback(actionCallback, id));
            }

            @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionListener
            public void onUnpick(String id, NativeBarcodePickActionCallback actionCallback) {
                Intrinsics.checkNotNullParameter(id, "id");
                Intrinsics.checkNotNullParameter(actionCallback, "actionCallback");
                BarcodePickActionListener.this.onUnpick(id, new BarcodePickActionCallback(actionCallback, id));
            }
        };
    }
}
