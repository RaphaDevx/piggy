package com.scandit.datacapture.core.internal.sdk.data;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeCameraCaptureParameters {

    public static final class CppProxy extends NativeCameraCaptureParameters {
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

        private native void native_clear(long j);

        private native float native_getFloatForKeyOr(long j, NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, float f);

        private native long native_getInt64ForKeyOr(long j, NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, long j2);

        private native void native_insertBool(long j, NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, boolean z);

        private native void native_insertFloat(long j, NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, float f);

        private native void native_insertInt64(long j, NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, long j2);

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters
        public void clear() {
            native_clear(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters
        public float getFloatForKeyOr(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, float f) {
            return native_getFloatForKeyOr(this.nativeRef, nativeCameraCaptureParameterKey, f);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters
        public long getInt64ForKeyOr(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, long j) {
            return native_getInt64ForKeyOr(this.nativeRef, nativeCameraCaptureParameterKey, j);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters
        public void insertBool(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, boolean z) {
            native_insertBool(this.nativeRef, nativeCameraCaptureParameterKey, z);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters
        public void insertFloat(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, float f) {
            native_insertFloat(this.nativeRef, nativeCameraCaptureParameterKey, f);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters
        public void insertInt64(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, long j) {
            native_insertInt64(this.nativeRef, nativeCameraCaptureParameterKey, j);
        }
    }

    public static native NativeCameraCaptureParameters create();

    public abstract void clear();

    public abstract float getFloatForKeyOr(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, float f);

    public abstract long getInt64ForKeyOr(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, long j);

    public abstract void insertBool(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, boolean z);

    public abstract void insertFloat(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, float f);

    public abstract void insertInt64(NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey, long j);
}
