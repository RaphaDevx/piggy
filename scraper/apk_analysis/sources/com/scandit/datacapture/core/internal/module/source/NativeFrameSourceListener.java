package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeFrameSourceListener {

    public static final class CppProxy extends NativeFrameSourceListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean a = new AtomicBoolean(false);
        private final long nativeRef;

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);

        private native void native_onDataCaptureContextAttached(long j, NativeDataCaptureContext nativeDataCaptureContext);

        private native void native_onDataCaptureContextDetached(long j, NativeDataCaptureContext nativeDataCaptureContext);

        private native void native_onFrameOutputAndroid(long j, NativeFrameSource nativeFrameSource, NativeFrameData nativeFrameData);

        private native void native_onObservationStarted(long j, NativeFrameSource nativeFrameSource);

        private native void native_onObservationStopped(long j, NativeFrameSource nativeFrameSource);

        private native void native_onStateChanged(long j, NativeFrameSource nativeFrameSource, FrameSourceState frameSourceState);

        @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
        public void onDataCaptureContextAttached(NativeDataCaptureContext nativeDataCaptureContext) {
            native_onDataCaptureContextAttached(this.nativeRef, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
        public void onDataCaptureContextDetached(NativeDataCaptureContext nativeDataCaptureContext) {
            native_onDataCaptureContextDetached(this.nativeRef, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
        public void onFrameOutputAndroid(NativeFrameSource nativeFrameSource, NativeFrameData nativeFrameData) {
            native_onFrameOutputAndroid(this.nativeRef, nativeFrameSource, nativeFrameData);
        }

        @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
        public void onObservationStarted(NativeFrameSource nativeFrameSource) {
            native_onObservationStarted(this.nativeRef, nativeFrameSource);
        }

        @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
        public void onObservationStopped(NativeFrameSource nativeFrameSource) {
            native_onObservationStopped(this.nativeRef, nativeFrameSource);
        }

        @Override // com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener
        public void onStateChanged(NativeFrameSource nativeFrameSource, FrameSourceState frameSourceState) {
            native_onStateChanged(this.nativeRef, nativeFrameSource, frameSourceState);
        }
    }

    public abstract void onDataCaptureContextAttached(NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void onDataCaptureContextDetached(NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void onFrameOutputAndroid(NativeFrameSource nativeFrameSource, NativeFrameData nativeFrameData);

    public abstract void onObservationStarted(NativeFrameSource nativeFrameSource);

    public abstract void onObservationStopped(NativeFrameSource nativeFrameSource);

    public abstract void onStateChanged(NativeFrameSource nativeFrameSource, FrameSourceState frameSourceState);
}
