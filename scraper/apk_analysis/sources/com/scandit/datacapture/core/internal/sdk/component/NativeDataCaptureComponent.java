package com.scandit.datacapture.core.internal.sdk.component;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureComponent {

    public static final class CppProxy extends NativeDataCaptureComponent {
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

        private native String native_getIdentifier(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.component.NativeDataCaptureComponent
        public String getIdentifier() {
            return native_getIdentifier(this.nativeRef);
        }
    }

    public abstract String getIdentifier();
}
