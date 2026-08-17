package com.scandit.datacapture.core.framesave;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.framesave.NativeBufferedFrameRecordingSession;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ*\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0097\u0001¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ'\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0097\u0001J\t\u0010\u0016\u001a\u00020\u000bH\u0097\u0001¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/core/framesave/BufferedFrameRecordingSession;", "Lcom/scandit/datacapture/core/framesave/BufferedFrameRecordingSessionProxy;", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "frameCapacity", "", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;I)V", "impl", "Lcom/scandit/datacapture/core/internal/module/framesave/NativeBufferedFrameRecordingSession;", "(Lcom/scandit/datacapture/core/internal/module/framesave/NativeBufferedFrameRecordingSession;)V", "_saveCapturedFrames", "", "storageDir", "", "discardFramesOlderThan", "", "discardFramesNewerThan", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "saveCapturedFrames", "startRecording", "frameSource", "Lcom/scandit/datacapture/core/source/FrameSource;", "stopRecording", "scandit-capture-core"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BufferedFrameRecordingSession implements BufferedFrameRecordingSessionProxy {
    private final /* synthetic */ BufferedFrameRecordingSessionProxyAdapter a;

    public BufferedFrameRecordingSession(NativeBufferedFrameRecordingSession impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BufferedFrameRecordingSessionProxyAdapter(impl, null, 2, null);
    }

    @Override // com.scandit.datacapture.core.framesave.BufferedFrameRecordingSessionProxy
    @ProxyFunction(nativeName = "saveCapturedFramesAsync")
    public void _saveCapturedFrames(String storageDir, Long discardFramesOlderThan, Long discardFramesNewerThan) {
        Intrinsics.checkNotNullParameter(storageDir, "storageDir");
        this.a._saveCapturedFrames(storageDir, discardFramesOlderThan, discardFramesNewerThan);
    }

    public final void saveCapturedFrames(String storageDir) {
        Intrinsics.checkNotNullParameter(storageDir, "storageDir");
        _saveCapturedFrames(storageDir, null, null);
    }

    @Override // com.scandit.datacapture.core.framesave.BufferedFrameRecordingSessionProxy
    @ProxyFunction(nativeName = "startRecordingAsync")
    public void startRecording(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        this.a.startRecording(frameSource);
    }

    @Override // com.scandit.datacapture.core.framesave.BufferedFrameRecordingSessionProxy
    @ProxyFunction(nativeName = "stopRecordingAsync")
    public void stopRecording() {
        this.a.stopRecording();
    }

    public final void saveCapturedFrames(String storageDir, Long discardFramesOlderThan, Long discardFramesNewerThan) {
        Intrinsics.checkNotNullParameter(storageDir, "storageDir");
        _saveCapturedFrames(storageDir, discardFramesOlderThan, discardFramesNewerThan);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BufferedFrameRecordingSession(com.scandit.datacapture.core.capture.DataCaptureContext r2, int r3) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext r2 = r2.getA()
            com.scandit.datacapture.core.internal.module.framesave.NativeBufferedFrameRecordingSession r2 = com.scandit.datacapture.core.internal.module.framesave.NativeBufferedFrameRecordingSession.create(r2, r3)
            java.lang.String r3 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.framesave.BufferedFrameRecordingSession.<init>(com.scandit.datacapture.core.capture.DataCaptureContext, int):void");
    }
}
