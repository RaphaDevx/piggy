package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeHintStyleBuilder {

    public static final class CppProxy extends NativeHintStyleBuilder {
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

        private native NativeHintStyle native_build(long j);

        private native void native_withBackgroundColor(long j, NativeColor nativeColor);

        private native void native_withCanBeDismissed(long j, boolean z);

        private native void native_withScanditIcon(long j, String str);

        private native void native_withTextColor(long j, NativeColor nativeColor);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintStyleBuilder
        public NativeHintStyle build() {
            return native_build(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintStyleBuilder
        public void withBackgroundColor(NativeColor nativeColor) {
            native_withBackgroundColor(this.nativeRef, nativeColor);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintStyleBuilder
        public void withCanBeDismissed(boolean z) {
            native_withCanBeDismissed(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintStyleBuilder
        public void withScanditIcon(String str) {
            native_withScanditIcon(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintStyleBuilder
        public void withTextColor(NativeColor nativeColor) {
            native_withTextColor(this.nativeRef, nativeColor);
        }
    }

    public static native NativeHintStyleBuilder create();

    public abstract NativeHintStyle build();

    public abstract void withBackgroundColor(NativeColor nativeColor);

    public abstract void withCanBeDismissed(boolean z);

    public abstract void withScanditIcon(String str);

    public abstract void withTextColor(NativeColor nativeColor);
}
