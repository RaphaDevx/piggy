package com.scandit.datacapture.core.internal.module.ui.video;

import com.scandit.datacapture.core.common.geometry.Size2;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeCopiedCameraTexture {

    public static final class CppProxy extends NativeCopiedCameraTexture {
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

        private native int native_getTextureId(long j);

        private native void native_releaseGlResources(long j);

        private native void native_update(long j, Size2 size2, int i, ArrayList<Float> arrayList);

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture
        public int getTextureId() {
            return native_getTextureId(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture
        public void releaseGlResources() {
            native_releaseGlResources(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture
        public void update(Size2 size2, int i, ArrayList<Float> arrayList) {
            native_update(this.nativeRef, size2, i, arrayList);
        }
    }

    public static native NativeCopiedCameraTexture create();

    public abstract int getTextureId();

    public abstract void releaseGlResources();

    public abstract void update(Size2 size2, int i, ArrayList<Float> arrayList);
}
