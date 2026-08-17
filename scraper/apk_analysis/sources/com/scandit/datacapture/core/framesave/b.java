package com.scandit.datacapture.core.framesave;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureContextFrameListener;
import com.scandit.datacapture.core.data.FrameData;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements DataCaptureContextFrameListener {
    public final WeakReference a;

    public b(CameraSequenceFrameSaveSession owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onFrameProcessingFinished(DataCaptureContext dataCaptureContext, FrameData frameData) {
        DataCaptureContextFrameListener.DefaultImpls.onFrameProcessingFinished(this, dataCaptureContext, frameData);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onFrameProcessingStarted(DataCaptureContext dataCaptureContext, FrameData frameData) {
        boolean z;
        boolean z2;
        SingleFrameRecorder singleFrameRecorder;
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        CameraSequenceFrameSaveSession cameraSequenceFrameSaveSession = (CameraSequenceFrameSaveSession) this.a.get();
        if (cameraSequenceFrameSaveSession != null) {
            z = cameraSequenceFrameSaveSession.g;
            if (z) {
                cameraSequenceFrameSaveSession.g = false;
                cameraSequenceFrameSaveSession.onFrameSaveFinished();
                return;
            }
            z2 = cameraSequenceFrameSaveSession.e;
            if (z2) {
                singleFrameRecorder = cameraSequenceFrameSaveSession.b;
                if (singleFrameRecorder != null) {
                    singleFrameRecorder.saveFrame(frameData, dataCaptureContext);
                }
                cameraSequenceFrameSaveSession.onFrameSaveSuccess();
            }
        }
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onObservationStarted(DataCaptureContext dataCaptureContext) {
        DataCaptureContextFrameListener.DefaultImpls.onObservationStarted(this, dataCaptureContext);
    }

    @Override // com.scandit.datacapture.core.capture.DataCaptureContextFrameListener
    public final void onObservationStopped(DataCaptureContext dataCaptureContext) {
        DataCaptureContextFrameListener.DefaultImpls.onObservationStopped(this, dataCaptureContext);
    }
}
