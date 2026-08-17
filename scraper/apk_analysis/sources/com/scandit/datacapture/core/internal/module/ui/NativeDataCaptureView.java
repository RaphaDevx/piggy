package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MarginsWithUnit;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.internal.module.ui.video.NativeVideoGeometry;
import com.scandit.datacapture.core.internal.module.ui.video.NativeVideoGeometryListener;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.ui.NativeNeedsRedrawDelegate;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.LogoStyle;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureView {

    public static final class CppProxy extends NativeDataCaptureView {
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

        private native void native_addOverlay(long j, NativeDataCaptureOverlay nativeDataCaptureOverlay);

        private native void native_attachToWindow(long j);

        private native void native_detachFromWindow(long j);

        private native void native_draw(long j);

        private native NativeDataCaptureContext native_getDataCaptureContext(long j);

        private native NativeFocusGesture native_getFocusGesture(long j);

        private native NativeHintPresenter native_getHintPresenter(long j);

        private native NativeHintPresenterV2 native_getHintPresenterV2(long j);

        private native Anchor native_getLogoAnchor(long j);

        private native PointWithUnit native_getLogoOffset(long j);

        private native LogoStyle native_getLogoStyle(long j);

        private native PointWithUnit native_getPointOfInterest(long j);

        private native MarginsWithUnit native_getScanAreaMargins(long j);

        private native NativeVideoGeometry native_getVideoGeometry(long j);

        private native Rect native_getViewfinderRect(long j);

        private native NativeZoomGesture native_getZoomGesture(long j);

        private native boolean native_isDisplayingViewfinder(long j);

        private native boolean native_isLogoHidden(long j);

        private native boolean native_isZoomNotificationShowingAllowed(long j);

        private native Point native_mapFramePointToView(long j, Point point);

        private native void native_performUiTriggeredZoomIn(long j);

        private native void native_performUiTriggeredZoomOut(long j);

        private native void native_releaseGlResources(long j);

        private native void native_removeOverlay(long j, NativeDataCaptureOverlay nativeDataCaptureOverlay);

        private native void native_setContextStatusListener(long j, NativeContextStatusListener nativeContextStatusListener);

        private native void native_setDataCaptureContext(long j, NativeDataCaptureContext nativeDataCaptureContext);

        private native void native_setFocusGesture(long j, NativeFocusGesture nativeFocusGesture);

        private native void native_setGestureRecognizer(long j, NativeGestureRecognizer nativeGestureRecognizer);

        private native void native_setHintPresenter(long j, NativeHintPresenter nativeHintPresenter);

        private native void native_setHintPresenterV2(long j, NativeHintPresenterV2 nativeHintPresenterV2);

        private native void native_setLogoAnchor(long j, Anchor anchor);

        private native void native_setLogoHidden(long j, boolean z);

        private native void native_setLogoOffset(long j, PointWithUnit pointWithUnit);

        private native void native_setLogoStyle(long j, LogoStyle logoStyle);

        private native void native_setNeedsRedraw(long j);

        private native void native_setNeedsRedrawDelegate(long j, NativeNeedsRedrawDelegate nativeNeedsRedrawDelegate);

        private native void native_setPointOfInterest(long j, PointWithUnit pointWithUnit);

        private native void native_setPreviewOrientation(long j, int i);

        private native void native_setScanAreaMargins(long j, MarginsWithUnit marginsWithUnit);

        private native void native_setZoomGesture(long j, NativeZoomGesture nativeZoomGesture);

        private native void native_setZoomNotificationShowingAllowed(long j, boolean z);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void addOverlay(NativeDataCaptureOverlay nativeDataCaptureOverlay) {
            native_addOverlay(this.nativeRef, nativeDataCaptureOverlay);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void attachToWindow() {
            native_attachToWindow(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void detachFromWindow() {
            native_detachFromWindow(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void draw() {
            native_draw(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public NativeDataCaptureContext getDataCaptureContext() {
            return native_getDataCaptureContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public NativeFocusGesture getFocusGesture() {
            return native_getFocusGesture(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public NativeHintPresenter getHintPresenter() {
            return native_getHintPresenter(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public NativeHintPresenterV2 getHintPresenterV2() {
            return native_getHintPresenterV2(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public Anchor getLogoAnchor() {
            return native_getLogoAnchor(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public PointWithUnit getLogoOffset() {
            return native_getLogoOffset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public LogoStyle getLogoStyle() {
            return native_getLogoStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public PointWithUnit getPointOfInterest() {
            return native_getPointOfInterest(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public MarginsWithUnit getScanAreaMargins() {
            return native_getScanAreaMargins(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public NativeVideoGeometry getVideoGeometry() {
            return native_getVideoGeometry(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public Rect getViewfinderRect() {
            return native_getViewfinderRect(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public NativeZoomGesture getZoomGesture() {
            return native_getZoomGesture(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public boolean isDisplayingViewfinder() {
            return native_isDisplayingViewfinder(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public boolean isLogoHidden() {
            return native_isLogoHidden(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public boolean isZoomNotificationShowingAllowed() {
            return native_isZoomNotificationShowingAllowed(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public Point mapFramePointToView(Point point) {
            return native_mapFramePointToView(this.nativeRef, point);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void performUiTriggeredZoomIn() {
            native_performUiTriggeredZoomIn(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void performUiTriggeredZoomOut() {
            native_performUiTriggeredZoomOut(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void releaseGlResources() {
            native_releaseGlResources(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void removeOverlay(NativeDataCaptureOverlay nativeDataCaptureOverlay) {
            native_removeOverlay(this.nativeRef, nativeDataCaptureOverlay);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setContextStatusListener(NativeContextStatusListener nativeContextStatusListener) {
            native_setContextStatusListener(this.nativeRef, nativeContextStatusListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setDataCaptureContext(NativeDataCaptureContext nativeDataCaptureContext) {
            native_setDataCaptureContext(this.nativeRef, nativeDataCaptureContext);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setFocusGesture(NativeFocusGesture nativeFocusGesture) {
            native_setFocusGesture(this.nativeRef, nativeFocusGesture);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setGestureRecognizer(NativeGestureRecognizer nativeGestureRecognizer) {
            native_setGestureRecognizer(this.nativeRef, nativeGestureRecognizer);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setHintPresenter(NativeHintPresenter nativeHintPresenter) {
            native_setHintPresenter(this.nativeRef, nativeHintPresenter);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setHintPresenterV2(NativeHintPresenterV2 nativeHintPresenterV2) {
            native_setHintPresenterV2(this.nativeRef, nativeHintPresenterV2);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setLogoAnchor(Anchor anchor) {
            native_setLogoAnchor(this.nativeRef, anchor);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setLogoHidden(boolean z) {
            native_setLogoHidden(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setLogoOffset(PointWithUnit pointWithUnit) {
            native_setLogoOffset(this.nativeRef, pointWithUnit);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setLogoStyle(LogoStyle logoStyle) {
            native_setLogoStyle(this.nativeRef, logoStyle);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setNeedsRedraw() {
            native_setNeedsRedraw(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setNeedsRedrawDelegate(NativeNeedsRedrawDelegate nativeNeedsRedrawDelegate) {
            native_setNeedsRedrawDelegate(this.nativeRef, nativeNeedsRedrawDelegate);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setPointOfInterest(PointWithUnit pointWithUnit) {
            native_setPointOfInterest(this.nativeRef, pointWithUnit);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setPreviewOrientation(int i) {
            native_setPreviewOrientation(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setScanAreaMargins(MarginsWithUnit marginsWithUnit) {
            native_setScanAreaMargins(this.nativeRef, marginsWithUnit);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setZoomGesture(NativeZoomGesture nativeZoomGesture) {
            native_setZoomGesture(this.nativeRef, nativeZoomGesture);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView
        public void setZoomNotificationShowingAllowed(boolean z) {
            native_setZoomNotificationShowingAllowed(this.nativeRef, z);
        }
    }

    public static native NativeDataCaptureView create(float f, NativeVideoGeometryListener nativeVideoGeometryListener);

    public abstract void addOverlay(NativeDataCaptureOverlay nativeDataCaptureOverlay);

    public abstract void attachToWindow();

    public abstract void detachFromWindow();

    public abstract void draw();

    public abstract NativeDataCaptureContext getDataCaptureContext();

    public abstract NativeFocusGesture getFocusGesture();

    public abstract NativeHintPresenter getHintPresenter();

    public abstract NativeHintPresenterV2 getHintPresenterV2();

    public abstract Anchor getLogoAnchor();

    public abstract PointWithUnit getLogoOffset();

    public abstract LogoStyle getLogoStyle();

    public abstract PointWithUnit getPointOfInterest();

    public abstract MarginsWithUnit getScanAreaMargins();

    public abstract NativeVideoGeometry getVideoGeometry();

    public abstract Rect getViewfinderRect();

    public abstract NativeZoomGesture getZoomGesture();

    public abstract boolean isDisplayingViewfinder();

    public abstract boolean isLogoHidden();

    public abstract boolean isZoomNotificationShowingAllowed();

    public abstract Point mapFramePointToView(Point point);

    public abstract void performUiTriggeredZoomIn();

    public abstract void performUiTriggeredZoomOut();

    public abstract void releaseGlResources();

    public abstract void removeOverlay(NativeDataCaptureOverlay nativeDataCaptureOverlay);

    public abstract void setContextStatusListener(NativeContextStatusListener nativeContextStatusListener);

    public abstract void setDataCaptureContext(NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void setFocusGesture(NativeFocusGesture nativeFocusGesture);

    public abstract void setGestureRecognizer(NativeGestureRecognizer nativeGestureRecognizer);

    public abstract void setHintPresenter(NativeHintPresenter nativeHintPresenter);

    public abstract void setHintPresenterV2(NativeHintPresenterV2 nativeHintPresenterV2);

    public abstract void setLogoAnchor(Anchor anchor);

    public abstract void setLogoHidden(boolean z);

    public abstract void setLogoOffset(PointWithUnit pointWithUnit);

    public abstract void setLogoStyle(LogoStyle logoStyle);

    public abstract void setNeedsRedraw();

    public abstract void setNeedsRedrawDelegate(NativeNeedsRedrawDelegate nativeNeedsRedrawDelegate);

    public abstract void setPointOfInterest(PointWithUnit pointWithUnit);

    public abstract void setPreviewOrientation(int i);

    public abstract void setScanAreaMargins(MarginsWithUnit marginsWithUnit);

    public abstract void setZoomGesture(NativeZoomGesture nativeZoomGesture);

    public abstract void setZoomNotificationShowingAllowed(boolean z);
}
