package com.scandit.datacapture.barcode.internal.module.batch.capture;

import com.scandit.datacapture.barcode.batch.capture.BarcodeBatch;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSession;
import com.scandit.datacapture.core.data.FrameData;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodeBatchListener {
    public Runnable a;
    public long b = 1500;
    public long c = -1;

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener
    public final void onObservationStarted(BarcodeBatch barcodeBatch) {
        BarcodeBatchListener.DefaultImpls.onObservationStarted(this, barcodeBatch);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener
    public final void onObservationStopped(BarcodeBatch barcodeBatch) {
        BarcodeBatchListener.DefaultImpls.onObservationStopped(this, barcodeBatch);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener
    public final void onSessionUpdated(BarcodeBatch mode, BarcodeBatchSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        long currentTimeMillis = System.currentTimeMillis();
        Intrinsics.checkNotNullParameter(session, "session");
        if (!session.getAddedTrackedBarcodes().isEmpty() || !session.getRemovedTrackedBarcodes().isEmpty() || this.c == -1) {
            this.c = currentTimeMillis;
            return;
        }
        if (session.getTrackedBarcodes().isEmpty() || currentTimeMillis - this.c < this.b) {
            return;
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            runnable.run();
        }
        this.c = -1L;
    }
}
