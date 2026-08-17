package com.scandit.datacapture.core.internal.sdk.ui.viewfinder;

import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeLaserlineViewfinder {

    public static final class CppProxy extends NativeLaserlineViewfinder {
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

        private native NativeColor native_getDisabledColor(long j);

        private native NativeColor native_getEnabledColor(long j);

        private native FloatWithUnit native_getWidth(long j);

        private native void native_setDisabledColor(long j, NativeColor nativeColor);

        private native void native_setEnabledColor(long j, NativeColor nativeColor);

        private native void native_setWidth(long j, FloatWithUnit floatWithUnit);

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public NativeViewfinder asViewfinder() {
            return native_asViewfinder(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public NativeColor getDisabledColor() {
            return native_getDisabledColor(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public NativeColor getEnabledColor() {
            return native_getEnabledColor(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public FloatWithUnit getWidth() {
            return native_getWidth(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public void setDisabledColor(NativeColor nativeColor) {
            native_setDisabledColor(this.nativeRef, nativeColor);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public void setEnabledColor(NativeColor nativeColor) {
            native_setEnabledColor(this.nativeRef, nativeColor);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeLaserlineViewfinder
        public void setWidth(FloatWithUnit floatWithUnit) {
            native_setWidth(this.nativeRef, floatWithUnit);
        }
    }

    public static native NativeLaserlineViewfinder create();

    public abstract NativeViewfinder asViewfinder();

    public abstract NativeColor getDisabledColor();

    public abstract NativeColor getEnabledColor();

    public abstract FloatWithUnit getWidth();

    public abstract void setDisabledColor(NativeColor nativeColor);

    public abstract void setEnabledColor(NativeColor nativeColor);

    public abstract void setWidth(FloatWithUnit floatWithUnit);
}
