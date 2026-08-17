package com.scandit.datacapture.core.internal.module.ui;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeZoomGesture {

    public static final class CppProxy extends NativeZoomGesture {
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

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeZoomGesture
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract String toJson();
}
