package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.component.NativeDataCaptureComponent;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureContextDeserializer {

    public static final class CppProxy extends NativeDataCaptureContextDeserializer {
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

        private native NativeDataCaptureContextDeserializerResult native_contextFromJson(long j, NativeJsonValue nativeJsonValue);

        private native boolean native_getAvoidThreadDependencies(long j);

        private native void native_setAvoidThreadDependencies(long j, boolean z);

        private native void native_setHelper(long j, NativeDataCaptureContextDeserializerHelper nativeDataCaptureContextDeserializerHelper);

        private native void native_setListener(long j, NativeDataCaptureContextDeserializerListener nativeDataCaptureContextDeserializerListener);

        private native NativeDataCaptureContextDeserializerResult native_updateContextFromJson(long j, NativeDataCaptureContext nativeDataCaptureContext, NativeDataCaptureView nativeDataCaptureView, ArrayList<NativeDataCaptureComponent> arrayList, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer
        public NativeDataCaptureContextDeserializerResult contextFromJson(NativeJsonValue nativeJsonValue) {
            return native_contextFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer
        public boolean getAvoidThreadDependencies() {
            return native_getAvoidThreadDependencies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer
        public void setAvoidThreadDependencies(boolean z) {
            native_setAvoidThreadDependencies(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer
        public void setHelper(NativeDataCaptureContextDeserializerHelper nativeDataCaptureContextDeserializerHelper) {
            native_setHelper(this.nativeRef, nativeDataCaptureContextDeserializerHelper);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer
        public void setListener(NativeDataCaptureContextDeserializerListener nativeDataCaptureContextDeserializerListener) {
            native_setListener(this.nativeRef, nativeDataCaptureContextDeserializerListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureContextDeserializer
        public NativeDataCaptureContextDeserializerResult updateContextFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeDataCaptureView nativeDataCaptureView, ArrayList<NativeDataCaptureComponent> arrayList, NativeJsonValue nativeJsonValue) {
            return native_updateContextFromJson(this.nativeRef, nativeDataCaptureContext, nativeDataCaptureView, arrayList, nativeJsonValue);
        }
    }

    public static native NativeDataCaptureContextDeserializer create(NativeFrameSourceDeserializer nativeFrameSourceDeserializer, NativeDataCaptureViewDeserializer nativeDataCaptureViewDeserializer, ArrayList<NativeDataCaptureModeDeserializer> arrayList, ArrayList<NativeDataCaptureComponentDeserializer> arrayList2);

    public abstract NativeDataCaptureContextDeserializerResult contextFromJson(NativeJsonValue nativeJsonValue);

    public abstract boolean getAvoidThreadDependencies();

    public abstract void setAvoidThreadDependencies(boolean z);

    public abstract void setHelper(NativeDataCaptureContextDeserializerHelper nativeDataCaptureContextDeserializerHelper);

    public abstract void setListener(NativeDataCaptureContextDeserializerListener nativeDataCaptureContextDeserializerListener);

    public abstract NativeDataCaptureContextDeserializerResult updateContextFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeDataCaptureView nativeDataCaptureView, ArrayList<NativeDataCaptureComponent> arrayList, NativeJsonValue nativeJsonValue);
}
