package com.scandit.datacapture.core.internal.sdk.ui.viewfinder;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeRectangularViewfinderAnimation {

    public static final class CppProxy extends NativeRectangularViewfinderAnimation {
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

        private native boolean native_isLooping(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderAnimation
        public boolean isLooping() {
            return native_isLooping(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeRectangularViewfinderAnimation
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeRectangularViewfinderAnimation create(boolean z);

    public abstract boolean isLooping();

    public abstract String toJson();
}
