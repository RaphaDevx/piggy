package com.scandit.datacapture.barcode.selection.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeFeedback;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSelection {

    public static final class CppProxy extends NativeBarcodeSelection {
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

        private native void native_addListenerAsync(long j, NativeBarcodeSelectionListener nativeBarcodeSelectionListener);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native void native_freezeCamera(long j);

        private native NativeBarcodeSelectionLicenseInfo native_getBarcodeSelectionLicenseInfo(long j);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeFeedback native_getFeedback(long j);

        private native PointWithUnit native_getPointOfInterest(long j);

        private native NativeBarcodeSelectionSession native_getSession(long j);

        private native void native_increaseCountForBarcodes(long j, ArrayList<NativeBarcode> arrayList);

        private native void native_increaseCountForBarcodesFromJsonString(long j, String str);

        private native boolean native_isEnabled(long j);

        private native void native_removeListenerAsync(long j, NativeBarcodeSelectionListener nativeBarcodeSelectionListener);

        private native void native_reset(long j);

        private native void native_selectAimedBarcode(long j);

        private native void native_selectAllUnselectedBarcodes(long j);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setPointOfInterest(long j, PointWithUnit pointWithUnit);

        private native void native_setSelectBarcodeEnabled(long j, NativeBarcode nativeBarcode, boolean z);

        private native void native_setSelectBarcodeEnabledFromJsonString(long j, String str, boolean z);

        private native void native_setSelectionFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_unfreezeCamera(long j);

        private native void native_unselectBarcodes(long j, ArrayList<NativeBarcode> arrayList);

        private native void native_unselectBarcodesFromJsonString(long j, String str);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void addListenerAsync(NativeBarcodeSelectionListener nativeBarcodeSelectionListener) {
            native_addListenerAsync(this.nativeRef, nativeBarcodeSelectionListener);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public NativeWrappedFuture applySettingsWrapped(NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeBarcodeSelectionSettings);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void freezeCamera() {
            native_freezeCamera(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public NativeBarcodeSelectionLicenseInfo getBarcodeSelectionLicenseInfo() {
            return native_getBarcodeSelectionLicenseInfo(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public NativeFeedback getFeedback() {
            return native_getFeedback(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public PointWithUnit getPointOfInterest() {
            return native_getPointOfInterest(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public NativeBarcodeSelectionSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void increaseCountForBarcodes(ArrayList<NativeBarcode> arrayList) {
            native_increaseCountForBarcodes(this.nativeRef, arrayList);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void increaseCountForBarcodesFromJsonString(String str) {
            native_increaseCountForBarcodesFromJsonString(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void removeListenerAsync(NativeBarcodeSelectionListener nativeBarcodeSelectionListener) {
            native_removeListenerAsync(this.nativeRef, nativeBarcodeSelectionListener);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void reset() {
            native_reset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void selectAimedBarcode() {
            native_selectAimedBarcode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void selectAllUnselectedBarcodes() {
            native_selectAllUnselectedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void setPointOfInterest(PointWithUnit pointWithUnit) {
            native_setPointOfInterest(this.nativeRef, pointWithUnit);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void setSelectBarcodeEnabled(NativeBarcode nativeBarcode, boolean z) {
            native_setSelectBarcodeEnabled(this.nativeRef, nativeBarcode, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void setSelectBarcodeEnabledFromJsonString(String str, boolean z) {
            native_setSelectBarcodeEnabledFromJsonString(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void setSelectionFeedback(NativeFeedback nativeFeedback) {
            native_setSelectionFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void unfreezeCamera() {
            native_unfreezeCamera(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void unselectBarcodes(ArrayList<NativeBarcode> arrayList) {
            native_unselectBarcodes(this.nativeRef, arrayList);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection
        public void unselectBarcodesFromJsonString(String str) {
            native_unselectBarcodesFromJsonString(this.nativeRef, str);
        }
    }

    public static native NativeBarcodeSelection create(NativeDataCaptureContext nativeDataCaptureContext, NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings);

    public abstract void addListenerAsync(NativeBarcodeSelectionListener nativeBarcodeSelectionListener);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract void freezeCamera();

    public abstract NativeBarcodeSelectionLicenseInfo getBarcodeSelectionLicenseInfo();

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeFeedback getFeedback();

    public abstract PointWithUnit getPointOfInterest();

    public abstract NativeBarcodeSelectionSession getSession();

    public abstract void increaseCountForBarcodes(ArrayList<NativeBarcode> arrayList);

    public abstract void increaseCountForBarcodesFromJsonString(String str);

    public abstract boolean isEnabled();

    public abstract void removeListenerAsync(NativeBarcodeSelectionListener nativeBarcodeSelectionListener);

    public abstract void reset();

    public abstract void selectAimedBarcode();

    public abstract void selectAllUnselectedBarcodes();

    public abstract void setEnabled(boolean z);

    public abstract void setPointOfInterest(PointWithUnit pointWithUnit);

    public abstract void setSelectBarcodeEnabled(NativeBarcode nativeBarcode, boolean z);

    public abstract void setSelectBarcodeEnabledFromJsonString(String str, boolean z);

    public abstract void setSelectionFeedback(NativeFeedback nativeFeedback);

    public abstract void unfreezeCamera();

    public abstract void unselectBarcodes(ArrayList<NativeBarcode> arrayList);

    public abstract void unselectBarcodesFromJsonString(String str);
}
