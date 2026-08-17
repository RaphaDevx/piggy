package com.scandit.datacapture.barcode.selection.internal.module.capture;

import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerBehavior;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAimerSelection {

    public static final class CppProxy extends NativeAimerSelection {
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

        private native NativeSelectionType native_asSelectionType(long j);

        private native BarcodeSelectionAimerBehavior native_getAimerBehavior(long j);

        private native NativeSelectionStrategy native_getSelectionStrategy(long j);

        private native void native_setAimerBehavior(long j, BarcodeSelectionAimerBehavior barcodeSelectionAimerBehavior);

        private native void native_setSelectionStrategy(long j, NativeSelectionStrategy nativeSelectionStrategy);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection
        public NativeSelectionType asSelectionType() {
            return native_asSelectionType(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection
        public BarcodeSelectionAimerBehavior getAimerBehavior() {
            return native_getAimerBehavior(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection
        public NativeSelectionStrategy getSelectionStrategy() {
            return native_getSelectionStrategy(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection
        public void setAimerBehavior(BarcodeSelectionAimerBehavior barcodeSelectionAimerBehavior) {
            native_setAimerBehavior(this.nativeRef, barcodeSelectionAimerBehavior);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection
        public void setSelectionStrategy(NativeSelectionStrategy nativeSelectionStrategy) {
            native_setSelectionStrategy(this.nativeRef, nativeSelectionStrategy);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeAimerSelection
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeAimerSelection create();

    public abstract NativeSelectionType asSelectionType();

    public abstract BarcodeSelectionAimerBehavior getAimerBehavior();

    public abstract NativeSelectionStrategy getSelectionStrategy();

    public abstract void setAimerBehavior(BarcodeSelectionAimerBehavior barcodeSelectionAimerBehavior);

    public abstract void setSelectionStrategy(NativeSelectionStrategy nativeSelectionStrategy);

    public abstract String toJson();
}
