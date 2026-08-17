package com.scandit.datacapture.core.internal.module.ui.video;

import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeTextureBinding;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeVideoPreview {

    public static final class CppProxy extends NativeVideoPreview {
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

        private native NativeVideoGeometryListener native_asVideoGeometryListener(long j);

        private native void native_draw(long j, NativePreviewShaderFormat nativePreviewShaderFormat, ArrayList<NativeTextureBinding> arrayList, NativeVideoGeometry nativeVideoGeometry);

        private native void native_prepareForFormat(long j, NativePreviewShaderFormat nativePreviewShaderFormat);

        private native void native_releaseGlResources(long j);

        private native void native_setTextureCoordinateTransformation(long j, ArrayList<Float> arrayList);

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview
        public NativeVideoGeometryListener asVideoGeometryListener() {
            return native_asVideoGeometryListener(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview
        public void draw(NativePreviewShaderFormat nativePreviewShaderFormat, ArrayList<NativeTextureBinding> arrayList, NativeVideoGeometry nativeVideoGeometry) {
            native_draw(this.nativeRef, nativePreviewShaderFormat, arrayList, nativeVideoGeometry);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview
        public void prepareForFormat(NativePreviewShaderFormat nativePreviewShaderFormat) {
            native_prepareForFormat(this.nativeRef, nativePreviewShaderFormat);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview
        public void releaseGlResources() {
            native_releaseGlResources(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview
        public void setTextureCoordinateTransformation(ArrayList<Float> arrayList) {
            native_setTextureCoordinateTransformation(this.nativeRef, arrayList);
        }
    }

    public static native NativeVideoPreview create();

    public abstract NativeVideoGeometryListener asVideoGeometryListener();

    public abstract void draw(NativePreviewShaderFormat nativePreviewShaderFormat, ArrayList<NativeTextureBinding> arrayList, NativeVideoGeometry nativeVideoGeometry);

    public abstract void prepareForFormat(NativePreviewShaderFormat nativePreviewShaderFormat);

    public abstract void releaseGlResources();

    public abstract void setTextureCoordinateTransformation(ArrayList<Float> arrayList);
}
