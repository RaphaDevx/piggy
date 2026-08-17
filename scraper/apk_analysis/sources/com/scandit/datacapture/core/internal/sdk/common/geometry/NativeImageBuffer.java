package com.scandit.datacapture.core.internal.sdk.common.geometry;

import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.common.graphic.ImagePlane;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeImageBuffer {

    public static final class CppProxy extends NativeImageBuffer {
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

        private native NativeImageBuffer native_deepCopy(long j);

        private native byte[] native_getBitmapRepresentationFromYUV(long j, Rect rect);

        private native int native_getHeight(long j);

        private native ArrayList<ImagePlane> native_getPlanes(long j);

        private native int native_getWidth(long j);

        private native byte[] native_toBitmap(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer
        public NativeImageBuffer deepCopy() {
            return native_deepCopy(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer
        public byte[] getBitmapRepresentationFromYUV(Rect rect) {
            return native_getBitmapRepresentationFromYUV(this.nativeRef, rect);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer
        public int getHeight() {
            return native_getHeight(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer
        public ArrayList<ImagePlane> getPlanes() {
            return native_getPlanes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer
        public int getWidth() {
            return native_getWidth(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer
        public byte[] toBitmap() {
            return native_toBitmap(this.nativeRef);
        }
    }

    public static native NativeImageBuffer create(int i, int i2, ArrayList<ImagePlane> arrayList);

    public abstract NativeImageBuffer deepCopy();

    public abstract byte[] getBitmapRepresentationFromYUV(Rect rect);

    public abstract int getHeight();

    public abstract ArrayList<ImagePlane> getPlanes();

    public abstract int getWidth();

    public abstract byte[] toBitmap();
}
