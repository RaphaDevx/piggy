package com.scandit.datacapture.core.internal.module.framesave;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSingleFrameRecorder {

    public static final class CppProxy extends NativeSingleFrameRecorder {
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

        private native void native_saveAsync(long j, NativeFrameData nativeFrameData, NativeDataCaptureContext nativeDataCaptureContext);

        private native void native_saveAsyncWithPrefix(long j, String str, NativeFrameData nativeFrameData, NativeDataCaptureContext nativeDataCaptureContext);

        private native void native_waitUntilSavesAreCompleted(long j);

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeSingleFrameRecorder
        public void saveAsync(NativeFrameData nativeFrameData, NativeDataCaptureContext nativeDataCaptureContext) {
            native_saveAsync(this.nativeRef, nativeFrameData, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeSingleFrameRecorder
        public void saveAsyncWithPrefix(String str, NativeFrameData nativeFrameData, NativeDataCaptureContext nativeDataCaptureContext) {
            native_saveAsyncWithPrefix(this.nativeRef, str, nativeFrameData, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.framesave.NativeSingleFrameRecorder
        public void waitUntilSavesAreCompleted() {
            native_waitUntilSavesAreCompleted(this.nativeRef);
        }
    }

    public static native NativeSingleFrameRecorder create(String str);

    public abstract void saveAsync(NativeFrameData nativeFrameData, NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void saveAsyncWithPrefix(String str, NativeFrameData nativeFrameData, NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void waitUntilSavesAreCompleted();
}
