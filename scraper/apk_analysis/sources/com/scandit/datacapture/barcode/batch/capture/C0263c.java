package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener;
import com.scandit.datacapture.core.data.FrameData;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.batch.capture.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0263c implements BarcodeBatchListener {
    public final WeakReference a;

    public C0263c(BarcodeBatch owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

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
        CopyOnWriteArrayList<BarcodeBatchListener> listeners$scandit_barcode_capture;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeBatch barcodeBatch = (BarcodeBatch) this.a.get();
        if (barcodeBatch == null || (listeners$scandit_barcode_capture = barcodeBatch.getListeners$scandit_barcode_capture()) == null) {
            return;
        }
        Iterator<T> it = listeners$scandit_barcode_capture.iterator();
        while (it.hasNext()) {
            ((BarcodeBatchListener) it.next()).onSessionUpdated(mode, session, data);
        }
    }
}
