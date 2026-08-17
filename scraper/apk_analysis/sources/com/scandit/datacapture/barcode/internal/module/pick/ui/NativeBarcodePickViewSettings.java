package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.scandit.datacapture.core.common.geometry.Anchor;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickViewSettings {

    public static final class CppProxy extends NativeBarcodePickViewSettings {
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

        private native boolean native_getHardwareTriggerEnabled(long j);

        private native Integer native_getHardwareTriggerKeyCode(long j);

        private native NativeBarcodePickViewHighlightStyle native_getHighlightStyle(long j);

        private native NativeBarcodePickViewHighlightStyleCustomView native_getHighlightStyleAsCustomView(long j);

        private native NativeBarcodePickViewHighlightStyleDot native_getHighlightStyleAsDot(long j);

        private native NativeBarcodePickViewHighlightStyleDotWithIcons native_getHighlightStyleAsDotWithIcons(long j);

        private native NativeBarcodePickViewHighlightStyleRectangular native_getHighlightStyleAsRectangular(long j);

        private native NativeBarcodePickViewHighlightStyleRectangularWithIcons native_getHighlightStyleAsRectangularWithIcons(long j);

        private native NativeBarcodePickViewHighlightType native_getHighlightStyleType(long j);

        private native String native_getInitialGuidelineText(long j);

        private native String native_getLoadingDialogTextForPicking(long j);

        private native String native_getLoadingDialogTextForUnpicking(long j);

        private native String native_getMoveCloserGuidelineText(long j);

        private native String native_getOnFirstItemPickCompletedHintText(long j);

        private native String native_getOnFirstItemToPickFoundHintText(long j);

        private native String native_getOnFirstItemUnpickCompletedHintText(long j);

        private native String native_getOnFirstUnmarkedItemPickCompletedHintText(long j);

        private native boolean native_getShowFinishButton(long j);

        private native boolean native_getShowGuidelines(long j);

        private native boolean native_getShowHints(long j);

        private native boolean native_getShowLoadingDialog(long j);

        private native boolean native_getShowPauseButton(long j);

        private native boolean native_getShowTorchButton(long j);

        private native boolean native_getShowZoomButton(long j);

        private native String native_getTapShutterToPauseGuidelineText(long j);

        private native Anchor native_getTorchButtonPosition(long j);

        private native Anchor native_getZoomButtonPosition(long j);

        private native void native_setHardwareTriggerEnabled(long j, boolean z);

        private native void native_setHardwareTriggerKeyCode(long j, Integer num);

        private native void native_setHighlightStyle(long j, NativeBarcodePickViewHighlightStyle nativeBarcodePickViewHighlightStyle);

        private native void native_setInitialGuidelineText(long j, String str);

        private native void native_setLoadingDialogTextForPicking(long j, String str);

        private native void native_setLoadingDialogTextForUnpicking(long j, String str);

        private native void native_setMoveCloserGuidelineText(long j, String str);

        private native void native_setOnFirstItemPickCompletedHintText(long j, String str);

        private native void native_setOnFirstItemToPickFoundHintText(long j, String str);

        private native void native_setOnFirstItemUnpickCompletedHintText(long j, String str);

        private native void native_setOnFirstUnmarkedItemPickCompletedHintText(long j, String str);

        private native void native_setShowFinishButton(long j, boolean z);

        private native void native_setShowGuidelines(long j, boolean z);

        private native void native_setShowHints(long j, boolean z);

        private native void native_setShowLoadingDialog(long j, boolean z);

        private native void native_setShowPauseButton(long j, boolean z);

        private native void native_setShowTorchButton(long j, boolean z);

        private native void native_setShowZoomButton(long j, boolean z);

        private native void native_setTapShutterToPauseGuidelineText(long j, String str);

        private native void native_setTorchButtonPosition(long j, Anchor anchor);

        private native void native_setZoomButtonPosition(long j, Anchor anchor);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getHardwareTriggerEnabled() {
            return native_getHardwareTriggerEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public Integer getHardwareTriggerKeyCode() {
            return native_getHardwareTriggerKeyCode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightStyle getHighlightStyle() {
            return native_getHighlightStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightStyleCustomView getHighlightStyleAsCustomView() {
            return native_getHighlightStyleAsCustomView(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightStyleDot getHighlightStyleAsDot() {
            return native_getHighlightStyleAsDot(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightStyleDotWithIcons getHighlightStyleAsDotWithIcons() {
            return native_getHighlightStyleAsDotWithIcons(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightStyleRectangular getHighlightStyleAsRectangular() {
            return native_getHighlightStyleAsRectangular(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightStyleRectangularWithIcons getHighlightStyleAsRectangularWithIcons() {
            return native_getHighlightStyleAsRectangularWithIcons(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public NativeBarcodePickViewHighlightType getHighlightStyleType() {
            return native_getHighlightStyleType(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getInitialGuidelineText() {
            return native_getInitialGuidelineText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getLoadingDialogTextForPicking() {
            return native_getLoadingDialogTextForPicking(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getLoadingDialogTextForUnpicking() {
            return native_getLoadingDialogTextForUnpicking(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getMoveCloserGuidelineText() {
            return native_getMoveCloserGuidelineText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getOnFirstItemPickCompletedHintText() {
            return native_getOnFirstItemPickCompletedHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getOnFirstItemToPickFoundHintText() {
            return native_getOnFirstItemToPickFoundHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getOnFirstItemUnpickCompletedHintText() {
            return native_getOnFirstItemUnpickCompletedHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getOnFirstUnmarkedItemPickCompletedHintText() {
            return native_getOnFirstUnmarkedItemPickCompletedHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowFinishButton() {
            return native_getShowFinishButton(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowGuidelines() {
            return native_getShowGuidelines(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowHints() {
            return native_getShowHints(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowLoadingDialog() {
            return native_getShowLoadingDialog(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowPauseButton() {
            return native_getShowPauseButton(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowTorchButton() {
            return native_getShowTorchButton(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public boolean getShowZoomButton() {
            return native_getShowZoomButton(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public String getTapShutterToPauseGuidelineText() {
            return native_getTapShutterToPauseGuidelineText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public Anchor getTorchButtonPosition() {
            return native_getTorchButtonPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public Anchor getZoomButtonPosition() {
            return native_getZoomButtonPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setHardwareTriggerEnabled(boolean z) {
            native_setHardwareTriggerEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setHardwareTriggerKeyCode(Integer num) {
            native_setHardwareTriggerKeyCode(this.nativeRef, num);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setHighlightStyle(NativeBarcodePickViewHighlightStyle nativeBarcodePickViewHighlightStyle) {
            native_setHighlightStyle(this.nativeRef, nativeBarcodePickViewHighlightStyle);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setInitialGuidelineText(String str) {
            native_setInitialGuidelineText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setLoadingDialogTextForPicking(String str) {
            native_setLoadingDialogTextForPicking(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setLoadingDialogTextForUnpicking(String str) {
            native_setLoadingDialogTextForUnpicking(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setMoveCloserGuidelineText(String str) {
            native_setMoveCloserGuidelineText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setOnFirstItemPickCompletedHintText(String str) {
            native_setOnFirstItemPickCompletedHintText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setOnFirstItemToPickFoundHintText(String str) {
            native_setOnFirstItemToPickFoundHintText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setOnFirstItemUnpickCompletedHintText(String str) {
            native_setOnFirstItemUnpickCompletedHintText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setOnFirstUnmarkedItemPickCompletedHintText(String str) {
            native_setOnFirstUnmarkedItemPickCompletedHintText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowFinishButton(boolean z) {
            native_setShowFinishButton(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowGuidelines(boolean z) {
            native_setShowGuidelines(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowHints(boolean z) {
            native_setShowHints(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowLoadingDialog(boolean z) {
            native_setShowLoadingDialog(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowPauseButton(boolean z) {
            native_setShowPauseButton(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowTorchButton(boolean z) {
            native_setShowTorchButton(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setShowZoomButton(boolean z) {
            native_setShowZoomButton(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setTapShutterToPauseGuidelineText(String str) {
            native_setTapShutterToPauseGuidelineText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setTorchButtonPosition(Anchor anchor) {
            native_setTorchButtonPosition(this.nativeRef, anchor);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettings
        public void setZoomButtonPosition(Anchor anchor) {
            native_setZoomButtonPosition(this.nativeRef, anchor);
        }
    }

    public static native NativeBarcodePickViewSettings create();

    public abstract boolean getHardwareTriggerEnabled();

    public abstract Integer getHardwareTriggerKeyCode();

    public abstract NativeBarcodePickViewHighlightStyle getHighlightStyle();

    public abstract NativeBarcodePickViewHighlightStyleCustomView getHighlightStyleAsCustomView();

    public abstract NativeBarcodePickViewHighlightStyleDot getHighlightStyleAsDot();

    public abstract NativeBarcodePickViewHighlightStyleDotWithIcons getHighlightStyleAsDotWithIcons();

    public abstract NativeBarcodePickViewHighlightStyleRectangular getHighlightStyleAsRectangular();

    public abstract NativeBarcodePickViewHighlightStyleRectangularWithIcons getHighlightStyleAsRectangularWithIcons();

    public abstract NativeBarcodePickViewHighlightType getHighlightStyleType();

    public abstract String getInitialGuidelineText();

    public abstract String getLoadingDialogTextForPicking();

    public abstract String getLoadingDialogTextForUnpicking();

    public abstract String getMoveCloserGuidelineText();

    public abstract String getOnFirstItemPickCompletedHintText();

    public abstract String getOnFirstItemToPickFoundHintText();

    public abstract String getOnFirstItemUnpickCompletedHintText();

    public abstract String getOnFirstUnmarkedItemPickCompletedHintText();

    public abstract boolean getShowFinishButton();

    public abstract boolean getShowGuidelines();

    public abstract boolean getShowHints();

    public abstract boolean getShowLoadingDialog();

    public abstract boolean getShowPauseButton();

    public abstract boolean getShowTorchButton();

    public abstract boolean getShowZoomButton();

    public abstract String getTapShutterToPauseGuidelineText();

    public abstract Anchor getTorchButtonPosition();

    public abstract Anchor getZoomButtonPosition();

    public abstract void setHardwareTriggerEnabled(boolean z);

    public abstract void setHardwareTriggerKeyCode(Integer num);

    public abstract void setHighlightStyle(NativeBarcodePickViewHighlightStyle nativeBarcodePickViewHighlightStyle);

    public abstract void setInitialGuidelineText(String str);

    public abstract void setLoadingDialogTextForPicking(String str);

    public abstract void setLoadingDialogTextForUnpicking(String str);

    public abstract void setMoveCloserGuidelineText(String str);

    public abstract void setOnFirstItemPickCompletedHintText(String str);

    public abstract void setOnFirstItemToPickFoundHintText(String str);

    public abstract void setOnFirstItemUnpickCompletedHintText(String str);

    public abstract void setOnFirstUnmarkedItemPickCompletedHintText(String str);

    public abstract void setShowFinishButton(boolean z);

    public abstract void setShowGuidelines(boolean z);

    public abstract void setShowHints(boolean z);

    public abstract void setShowLoadingDialog(boolean z);

    public abstract void setShowPauseButton(boolean z);

    public abstract void setShowTorchButton(boolean z);

    public abstract void setShowZoomButton(boolean z);

    public abstract void setTapShutterToPauseGuidelineText(String str);

    public abstract void setTorchButtonPosition(Anchor anchor);

    public abstract void setZoomButtonPosition(Anchor anchor);
}
