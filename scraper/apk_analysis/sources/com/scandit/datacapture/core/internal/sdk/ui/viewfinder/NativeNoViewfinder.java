package com.scandit.datacapture.core.internal.sdk.ui.viewfinder;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeNoViewfinder {

    public static final class CppProxy extends NativeNoViewfinder {
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

        private native NativeViewfinder native_asViewfinder(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeNoViewfinder
        public NativeViewfinder asViewfinder() {
            return native_asViewfinder(this.nativeRef);
        }
    }

    public static native NativeNoViewfinder create();

    public abstract NativeViewfinder asViewfinder();
}
