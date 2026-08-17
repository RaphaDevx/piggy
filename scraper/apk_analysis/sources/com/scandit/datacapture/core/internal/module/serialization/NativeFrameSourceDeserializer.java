package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeFrameSourceDeserializer {

    public static final class CppProxy extends NativeFrameSourceDeserializer {
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

        private native NativeCameraSettings native_cameraSettingsFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeFrameSource native_frameSourceFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeFrameSourceDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setHelper(long j, NativeFrameSourceDeserializerHelper nativeFrameSourceDeserializerHelper);

        private native void native_setListener(long j, NativeFrameSourceDeserializerListener nativeFrameSourceDeserializerListener);

        private native NativeCameraSettings native_updateCameraSettingsFromJson(long j, NativeCameraSettings nativeCameraSettings, NativeJsonValue nativeJsonValue);

        private native NativeFrameSource native_updateFrameSourceFromJson(long j, NativeFrameSource nativeFrameSource, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public NativeCameraSettings cameraSettingsFromJson(NativeJsonValue nativeJsonValue) {
            return native_cameraSettingsFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public NativeFrameSource frameSourceFromJson(NativeJsonValue nativeJsonValue) {
            return native_frameSourceFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public NativeFrameSourceDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public void setHelper(NativeFrameSourceDeserializerHelper nativeFrameSourceDeserializerHelper) {
            native_setHelper(this.nativeRef, nativeFrameSourceDeserializerHelper);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public void setListener(NativeFrameSourceDeserializerListener nativeFrameSourceDeserializerListener) {
            native_setListener(this.nativeRef, nativeFrameSourceDeserializerListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public NativeCameraSettings updateCameraSettingsFromJson(NativeCameraSettings nativeCameraSettings, NativeJsonValue nativeJsonValue) {
            return native_updateCameraSettingsFromJson(this.nativeRef, nativeCameraSettings, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeFrameSourceDeserializer
        public NativeFrameSource updateFrameSourceFromJson(NativeFrameSource nativeFrameSource, NativeJsonValue nativeJsonValue) {
            return native_updateFrameSourceFromJson(this.nativeRef, nativeFrameSource, nativeJsonValue);
        }
    }

    public static native NativeFrameSourceDeserializer create(ArrayList<NativeDataCaptureModeDeserializer> arrayList);

    public abstract NativeCameraSettings cameraSettingsFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeFrameSource frameSourceFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeFrameSourceDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setHelper(NativeFrameSourceDeserializerHelper nativeFrameSourceDeserializerHelper);

    public abstract void setListener(NativeFrameSourceDeserializerListener nativeFrameSourceDeserializerListener);

    public abstract NativeCameraSettings updateCameraSettingsFromJson(NativeCameraSettings nativeCameraSettings, NativeJsonValue nativeJsonValue);

    public abstract NativeFrameSource updateFrameSourceFromJson(NativeFrameSource nativeFrameSource, NativeJsonValue nativeJsonValue);
}
