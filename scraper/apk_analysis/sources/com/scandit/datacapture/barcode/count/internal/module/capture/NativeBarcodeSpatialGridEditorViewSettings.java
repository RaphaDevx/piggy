package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSpatialGridEditorViewSettings {

    public static final class CppProxy extends NativeBarcodeSpatialGridEditorViewSettings {
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

        private native String native_getCancelMappingButtonText(long j);

        private native String native_getFinishMappingButtonText(long j);

        private native String native_getReorderHintText(long j);

        private native NativeColor native_getSubColor(long j);

        private native NativeColor native_getToteColor(long j);

        private native String native_getToteTextFormat(long j);

        private native void native_setCancelMappingButtonText(long j, String str);

        private native void native_setFinishMappingButtonText(long j, String str);

        private native void native_setReorderHintText(long j, String str);

        private native void native_setSubColor(long j, NativeColor nativeColor);

        private native void native_setToteColor(long j, NativeColor nativeColor);

        private native void native_setToteTextFormat(long j, String str);

        private native void native_updateFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public String getCancelMappingButtonText() {
            return native_getCancelMappingButtonText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public String getFinishMappingButtonText() {
            return native_getFinishMappingButtonText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public String getReorderHintText() {
            return native_getReorderHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public NativeColor getSubColor() {
            return native_getSubColor(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public NativeColor getToteColor() {
            return native_getToteColor(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public String getToteTextFormat() {
            return native_getToteTextFormat(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void setCancelMappingButtonText(String str) {
            native_setCancelMappingButtonText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void setFinishMappingButtonText(String str) {
            native_setFinishMappingButtonText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void setReorderHintText(String str) {
            native_setReorderHintText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void setSubColor(NativeColor nativeColor) {
            native_setSubColor(this.nativeRef, nativeColor);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void setToteColor(NativeColor nativeColor) {
            native_setToteColor(this.nativeRef, nativeColor);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void setToteTextFormat(String str) {
            native_setToteTextFormat(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridEditorViewSettings
        public void updateFromJson(NativeJsonValue nativeJsonValue) {
            native_updateFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeBarcodeSpatialGridEditorViewSettings create();

    public abstract String getCancelMappingButtonText();

    public abstract String getFinishMappingButtonText();

    public abstract String getReorderHintText();

    public abstract NativeColor getSubColor();

    public abstract NativeColor getToteColor();

    public abstract String getToteTextFormat();

    public abstract void setCancelMappingButtonText(String str);

    public abstract void setFinishMappingButtonText(String str);

    public abstract void setReorderHintText(String str);

    public abstract void setSubColor(NativeColor nativeColor);

    public abstract void setToteColor(NativeColor nativeColor);

    public abstract void setToteTextFormat(String str);

    public abstract void updateFromJson(NativeJsonValue nativeJsonValue);
}
