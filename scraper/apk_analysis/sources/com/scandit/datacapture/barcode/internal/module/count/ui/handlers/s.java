package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import android.os.Handler;
import android.os.Looper;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.FrameSource;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class s implements BarcodeCountCameraHandler {
    public final BarcodeCount a;
    public final Handler b;

    public s(BarcodeCount barcodeCount) {
        Handler handler = new Handler(Looper.getMainLooper());
        Intrinsics.checkNotNullParameter(barcodeCount, "barcodeCount");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = barcodeCount;
        this.b = handler;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountCameraHandler
    public final void a(final com.scandit.datacapture.barcode.count.ui.view.h onFocus) {
        Intrinsics.checkNotNullParameter(onFocus, "onFocus");
        if (!this.a.getTriggerAutoFocusOnShutter$scandit_barcode_capture()) {
            onFocus.invoke();
            return;
        }
        DataCaptureContext c = this.a.getC();
        FrameSource frameSource = c != null ? c.get_frameSource() : null;
        Camera camera = frameSource instanceof Camera ? (Camera) frameSource : null;
        if (camera == null) {
            onFocus.invoke();
        } else {
            camera.getA().triggerAutoFocus();
            this.b.postDelayed(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.s$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    s.a(Function0.this);
                }
            }, this.a.getTriggerAutoFocusDelay$scandit_barcode_capture());
        }
    }

    public static final void a(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }
}
