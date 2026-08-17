package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureViewDeserializer {

    public static final class CppProxy extends NativeDataCaptureViewDeserializer {
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

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setHelper(long j, NativeDataCaptureViewDeserializerHelper nativeDataCaptureViewDeserializerHelper);

        private native void native_setListener(long j, NativeDataCaptureViewDeserializerListener nativeDataCaptureViewDeserializerListener);

        private native NativeDataCaptureView native_updateViewFromJson(long j, NativeDataCaptureView nativeDataCaptureView, NativeJsonValue nativeJsonValue);

        private native NativeDataCaptureView native_viewFromJson(long j, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer
        public void setHelper(NativeDataCaptureViewDeserializerHelper nativeDataCaptureViewDeserializerHelper) {
            native_setHelper(this.nativeRef, nativeDataCaptureViewDeserializerHelper);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer
        public void setListener(NativeDataCaptureViewDeserializerListener nativeDataCaptureViewDeserializerListener) {
            native_setListener(this.nativeRef, nativeDataCaptureViewDeserializerListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer
        public NativeDataCaptureView updateViewFromJson(NativeDataCaptureView nativeDataCaptureView, NativeJsonValue nativeJsonValue) {
            return native_updateViewFromJson(this.nativeRef, nativeDataCaptureView, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureViewDeserializer
        public NativeDataCaptureView viewFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue) {
            return native_viewFromJson(this.nativeRef, nativeDataCaptureContext, nativeJsonValue);
        }
    }

    public static native NativeDataCaptureViewDeserializer create(ArrayList<NativeDataCaptureModeDeserializer> arrayList);

    public abstract ArrayList<String> getWarnings();

    public abstract void setHelper(NativeDataCaptureViewDeserializerHelper nativeDataCaptureViewDeserializerHelper);

    public abstract void setListener(NativeDataCaptureViewDeserializerListener nativeDataCaptureViewDeserializerListener);

    public abstract NativeDataCaptureView updateViewFromJson(NativeDataCaptureView nativeDataCaptureView, NativeJsonValue nativeJsonValue);

    public abstract NativeDataCaptureView viewFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);
}
