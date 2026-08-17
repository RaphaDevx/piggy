package com.scandit.datacapture.core.internal.module.framesave;

import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBufferedFrameRecordingSession {

    public static final class CppProxy extends NativeBufferedFrameRecordingSession {
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

        private native void native_saveCapturedFramesAsync(long j, String str, Long l, Long l2);

        private native void native_startRecordingAsync(long j, NativeFrameSource nativeFrameSource);

        private native void native_stopRecordingAsync(long j);

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBufferedFrameRecordingSession
        public void saveCapturedFramesAsync(String str, Long l, Long l2) {
            native_saveCapturedFramesAsync(this.nativeRef, str, l, l2);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBufferedFrameRecordingSession
        public void startRecordingAsync(NativeFrameSource nativeFrameSource) {
            native_startRecordingAsync(this.nativeRef, nativeFrameSource);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeBufferedFrameRecordingSession
        public void stopRecordingAsync() {
            native_stopRecordingAsync(this.nativeRef);
        }
    }

    public static native NativeBufferedFrameRecordingSession create(NativeDataCaptureContext nativeDataCaptureContext, int i);

    public abstract void saveCapturedFramesAsync(String str, Long l, Long l2);

    public abstract void startRecordingAsync(NativeFrameSource nativeFrameSource);

    public abstract void stopRecordingAsync();
}
