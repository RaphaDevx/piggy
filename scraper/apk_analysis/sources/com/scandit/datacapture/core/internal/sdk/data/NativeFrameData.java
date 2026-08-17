package com.scandit.datacapture.core.internal.sdk.data;

import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeFrameData {

    public static final class CppProxy extends NativeFrameData {
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

        private native NativeCameraCaptureParameters native_collectCameraCaptureParametersForAndroid(long j);

        private native NativeImageBuffer native_getImageBuffer(long j);

        private native Long native_getNativeStartOfExposureTimestampForAndroid(long j);

        private native int native_getOrientation(long j);

        private native long native_getTimestampForAndroid(long j);

        private native void native_release(long j);

        private native void native_retain(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public NativeCameraCaptureParameters collectCameraCaptureParametersForAndroid() {
            return native_collectCameraCaptureParametersForAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public NativeImageBuffer getImageBuffer() {
            return native_getImageBuffer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public Long getNativeStartOfExposureTimestampForAndroid() {
            return native_getNativeStartOfExposureTimestampForAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public int getOrientation() {
            return native_getOrientation(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public long getTimestampForAndroid() {
            return native_getTimestampForAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public void release() {
            native_release(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeFrameData
        public void retain() {
            native_retain(this.nativeRef);
        }
    }

    public abstract NativeCameraCaptureParameters collectCameraCaptureParametersForAndroid();

    public abstract NativeImageBuffer getImageBuffer();

    public abstract Long getNativeStartOfExposureTimestampForAndroid();

    public abstract int getOrientation();

    public abstract long getTimestampForAndroid();

    public abstract void release();

    public abstract void retain();
}
