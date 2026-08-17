package com.scandit.datacapture.barcode.selection.internal.module.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAutoSelectionStrategy {

    public static final class CppProxy extends NativeAutoSelectionStrategy {
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

        private native NativeSelectionStrategy native_asSelectionStrategy(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAutoSelectionStrategy
        public NativeSelectionStrategy asSelectionStrategy() {
            return native_asSelectionStrategy(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAutoSelectionStrategy
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeAutoSelectionStrategy create();

    public abstract NativeSelectionStrategy asSelectionStrategy();

    public abstract String toJson();
}
