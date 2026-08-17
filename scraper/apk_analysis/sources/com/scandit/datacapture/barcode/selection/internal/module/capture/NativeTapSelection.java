package com.scandit.datacapture.barcode.selection.internal.module.capture;

import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionFreezeBehavior;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapBehavior;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeTapSelection {

    public static final class CppProxy extends NativeTapSelection {
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

        private native BarcodeSelectionFreezeBehavior native_getFreezeBehavior(long j);

        private native boolean native_getShouldFreezeOnDoubleTap(long j);

        private native BarcodeSelectionTapBehavior native_getTapBehavior(long j);

        private native void native_setFreezeBehavior(long j, BarcodeSelectionFreezeBehavior barcodeSelectionFreezeBehavior);

        private native void native_setShouldFreezeOnDoubleTap(long j, boolean z);

        private native void native_setTapBehavior(long j, BarcodeSelectionTapBehavior barcodeSelectionTapBehavior);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public NativeSelectionType asSelectionType() {
            return native_asSelectionType(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public BarcodeSelectionFreezeBehavior getFreezeBehavior() {
            return native_getFreezeBehavior(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public boolean getShouldFreezeOnDoubleTap() {
            return native_getShouldFreezeOnDoubleTap(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public BarcodeSelectionTapBehavior getTapBehavior() {
            return native_getTapBehavior(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public void setFreezeBehavior(BarcodeSelectionFreezeBehavior barcodeSelectionFreezeBehavior) {
            native_setFreezeBehavior(this.nativeRef, barcodeSelectionFreezeBehavior);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public void setShouldFreezeOnDoubleTap(boolean z) {
            native_setShouldFreezeOnDoubleTap(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public void setTapBehavior(BarcodeSelectionTapBehavior barcodeSelectionTapBehavior) {
            native_setTapBehavior(this.nativeRef, barcodeSelectionTapBehavior);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeTapSelection
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeTapSelection create();

    public abstract NativeSelectionType asSelectionType();

    public abstract BarcodeSelectionFreezeBehavior getFreezeBehavior();

    public abstract boolean getShouldFreezeOnDoubleTap();

    public abstract BarcodeSelectionTapBehavior getTapBehavior();

    public abstract void setFreezeBehavior(BarcodeSelectionFreezeBehavior barcodeSelectionFreezeBehavior);

    public abstract void setShouldFreezeOnDoubleTap(boolean z);

    public abstract void setTapBehavior(BarcodeSelectionTapBehavior barcodeSelectionTapBehavior);

    public abstract String toJson();
}
