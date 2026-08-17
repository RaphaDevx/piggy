package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFindBasicOverlay {

    public static final class CppProxy extends NativeBarcodeFindBasicOverlay {
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

        private native NativeDataCaptureOverlay native_asDataCaptureOverlay(long j);

        private native boolean native_getShouldShowScanAreaGuides(long j);

        private native boolean native_isDisplayingLogo(long j);

        private native void native_setGuidanceHandler(long j, NativeBarcodeFindGuidanceHandler nativeBarcodeFindGuidanceHandler);

        private native void native_setShouldShowHint(long j, boolean z);

        private native void native_setShouldShowScanAreaGuides(long j, boolean z);

        private native void native_setTextForAllItemsFoundSuccessfullyHint(long j, String str);

        private native void native_setTextForItemListUpdatedHint(long j, String str);

        private native void native_setTextForItemListUpdatedWhenPausedHint(long j, String str);

        private native void native_setTextForMoveCloserToBarcodesHint(long j, String str);

        private native void native_setTextForPointAtBarcodesToSearchHint(long j, String str);

        private native void native_setTextForTapShutterToPauseScreenHint(long j, String str);

        private native void native_setTextForTapShutterToResumeScreenHint(long j, String str);

        private native void native_setUserGuidanceEnabled(long j, boolean z);

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public NativeDataCaptureOverlay asDataCaptureOverlay() {
            return native_asDataCaptureOverlay(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public boolean getShouldShowScanAreaGuides() {
            return native_getShouldShowScanAreaGuides(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public boolean isDisplayingLogo() {
            return native_isDisplayingLogo(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setGuidanceHandler(NativeBarcodeFindGuidanceHandler nativeBarcodeFindGuidanceHandler) {
            native_setGuidanceHandler(this.nativeRef, nativeBarcodeFindGuidanceHandler);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setShouldShowHint(boolean z) {
            native_setShouldShowHint(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setShouldShowScanAreaGuides(boolean z) {
            native_setShouldShowScanAreaGuides(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForAllItemsFoundSuccessfullyHint(String str) {
            native_setTextForAllItemsFoundSuccessfullyHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForItemListUpdatedHint(String str) {
            native_setTextForItemListUpdatedHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForItemListUpdatedWhenPausedHint(String str) {
            native_setTextForItemListUpdatedWhenPausedHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForMoveCloserToBarcodesHint(String str) {
            native_setTextForMoveCloserToBarcodesHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForPointAtBarcodesToSearchHint(String str) {
            native_setTextForPointAtBarcodesToSearchHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForTapShutterToPauseScreenHint(String str) {
            native_setTextForTapShutterToPauseScreenHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setTextForTapShutterToResumeScreenHint(String str) {
            native_setTextForTapShutterToResumeScreenHint(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay
        public void setUserGuidanceEnabled(boolean z) {
            native_setUserGuidanceEnabled(this.nativeRef, z);
        }
    }

    public static native NativeBarcodeFindBasicOverlay create(NativeBarcodeFind nativeBarcodeFind);

    public abstract NativeDataCaptureOverlay asDataCaptureOverlay();

    public abstract boolean getShouldShowScanAreaGuides();

    public abstract boolean isDisplayingLogo();

    public abstract void setGuidanceHandler(NativeBarcodeFindGuidanceHandler nativeBarcodeFindGuidanceHandler);

    public abstract void setShouldShowHint(boolean z);

    public abstract void setShouldShowScanAreaGuides(boolean z);

    public abstract void setTextForAllItemsFoundSuccessfullyHint(String str);

    public abstract void setTextForItemListUpdatedHint(String str);

    public abstract void setTextForItemListUpdatedWhenPausedHint(String str);

    public abstract void setTextForMoveCloserToBarcodesHint(String str);

    public abstract void setTextForPointAtBarcodesToSearchHint(String str);

    public abstract void setTextForTapShutterToPauseScreenHint(String str);

    public abstract void setTextForTapShutterToResumeScreenHint(String str);

    public abstract void setUserGuidanceEnabled(boolean z);
}
