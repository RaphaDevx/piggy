package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextFrameListener;
import com.scandit.datacapture.core.data.FrameData;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements a {
    public final c a;
    public final AtomicBoolean b;
    public long c;

    public b(c store) {
        Intrinsics.checkNotNullParameter(store, "store");
        this.a = store;
        this.b = new AtomicBoolean(false);
        this.c = -1L;
    }

    public final void a() {
        this.c = -1L;
        d dVar = (d) this.a;
        synchronized (dVar.a) {
            dVar.b = 0L;
            dVar.c = 0L;
            Unit unit = Unit.INSTANCE;
        }
        synchronized (dVar.d) {
            dVar.e = 0.0f;
            dVar.f = 0L;
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onFrameProcessingFinished(DataCaptureContext dataCaptureContext, FrameData frameData) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        DataCaptureContextFrameListener.DefaultImpls.onFrameProcessingFinished(this, dataCaptureContext, frameData);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onFrameProcessingStarted(DataCaptureContext dataCaptureContext, FrameData frameData) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        DataCaptureContextFrameListener.DefaultImpls.onFrameProcessingStarted(this, dataCaptureContext, frameData);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onObservationStarted(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        DataCaptureContextFrameListener.DefaultImpls.onObservationStarted(this, dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onObservationStopped(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        DataCaptureContextFrameListener.DefaultImpls.onObservationStopped(this, dataCaptureContext);
    }
}
