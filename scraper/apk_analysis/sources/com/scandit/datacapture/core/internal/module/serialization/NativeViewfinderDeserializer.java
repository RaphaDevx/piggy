package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeViewfinder;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeViewfinderDeserializer {

    public static final class CppProxy extends NativeViewfinderDeserializer {
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

        private native NativeViewfinderDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setHelper(long j, NativeViewfinderDeserializerHelper nativeViewfinderDeserializerHelper);

        private native NativeViewfinder native_viewfinderFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeViewfinderDeserializer
        public NativeViewfinderDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeViewfinderDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeViewfinderDeserializer
        public void setHelper(NativeViewfinderDeserializerHelper nativeViewfinderDeserializerHelper) {
            native_setHelper(this.nativeRef, nativeViewfinderDeserializerHelper);
        }

        @Override // com.scandit.datacapture.core.internal.module.serialization.NativeViewfinderDeserializer
        public NativeViewfinder viewfinderFromJson(NativeJsonValue nativeJsonValue) {
            return native_viewfinderFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeViewfinderDeserializer create();

    public abstract NativeViewfinderDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setHelper(NativeViewfinderDeserializerHelper nativeViewfinderDeserializerHelper);

    public abstract NativeViewfinder viewfinderFromJson(NativeJsonValue nativeJsonValue);
}
