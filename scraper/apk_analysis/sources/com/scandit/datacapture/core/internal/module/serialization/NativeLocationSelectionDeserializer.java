package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.sdk.area.NativeLocationSelection;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeLocationSelectionDeserializer {

    public static final class CppProxy extends NativeLocationSelectionDeserializer {
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

        private native NativeLocationSelectionDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native NativeLocationSelection native_locationSelectionFromJson(long j, NativeJsonValue nativeJsonValue);

        private native void native_setHelper(long j, NativeLocationSelectionDeserializerHelper nativeLocationSelectionDeserializerHelper);

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializer
        public NativeLocationSelectionDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializer
        public NativeLocationSelection locationSelectionFromJson(NativeJsonValue nativeJsonValue) {
            return native_locationSelectionFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializer
        public void setHelper(NativeLocationSelectionDeserializerHelper nativeLocationSelectionDeserializerHelper) {
            native_setHelper(this.nativeRef, nativeLocationSelectionDeserializerHelper);
        }
    }

    public static native NativeLocationSelectionDeserializer create();

    public abstract NativeLocationSelectionDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract NativeLocationSelection locationSelectionFromJson(NativeJsonValue nativeJsonValue);

    public abstract void setHelper(NativeLocationSelectionDeserializerHelper nativeLocationSelectionDeserializerHelper);
}
