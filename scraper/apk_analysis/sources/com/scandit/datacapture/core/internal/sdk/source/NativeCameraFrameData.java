package com.scandit.datacapture.core.internal.sdk.source;

import com.scandit.datacapture.core.internal.module.common.geometry.NativeAxis;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.snapchat.djinni.NativeObjectManager;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeCameraFrameData {

    public static final class CppProxy extends NativeCameraFrameData {
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

        private native NativeFrameData native_asFrameData(long j);

        private native NativeCameraCaptureParameters native_getCaptureParameters(long j);

        private native void native_release(long j);

        private native void native_retain(long j);

        private native byte[] native_takeBuffer(long j);

        private native void native_update(long j, int i, int i2, byte[] bArr, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i3, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l);

        private native void native_updateNv21(long j, int i, int i2, byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i3, int i4, int i5, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i6, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l);

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public NativeFrameData asFrameData() {
            return native_asFrameData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public NativeCameraCaptureParameters getCaptureParameters() {
            return native_getCaptureParameters(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public void release() {
            native_release(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public void retain() {
            native_retain(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public byte[] takeBuffer() {
            return native_takeBuffer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public void update(int i, int i2, byte[] bArr, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i3, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l) {
            native_update(this.nativeRef, i, i2, bArr, nativeCameraFrameDataPool, i3, nativeAxis, nativeCameraCaptureParameters, l);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData
        public void updateNv21(int i, int i2, byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i3, int i4, int i5, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i6, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l) {
            native_updateNv21(this.nativeRef, i, i2, bArr, byteBuffer, byteBuffer2, byteBuffer3, i3, i4, i5, nativeCameraFrameDataPool, i6, nativeAxis, nativeCameraCaptureParameters, l);
        }
    }

    public static native NativeCameraFrameData create(int i, int i2, byte[] bArr, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i3, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l);

    public static native NativeCameraFrameData createEmpty();

    public static native NativeCameraFrameData createNv21FrameData(int i, int i2, byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i3, int i4, int i5, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i6, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l);

    public static native byte[] getByteBuffer(NativeFrameData nativeFrameData);

    public abstract NativeFrameData asFrameData();

    public abstract NativeCameraCaptureParameters getCaptureParameters();

    public abstract void release();

    public abstract void retain();

    public abstract byte[] takeBuffer();

    public abstract void update(int i, int i2, byte[] bArr, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i3, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l);

    public abstract void updateNv21(int i, int i2, byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i3, int i4, int i5, NativeCameraFrameDataPool nativeCameraFrameDataPool, int i6, NativeAxis nativeAxis, NativeCameraCaptureParameters nativeCameraCaptureParameters, Long l);
}
