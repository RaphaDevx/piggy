package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeTapToFocus {

    public static final class CppProxy extends NativeTapToFocus {
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

        private native void native_addListener(long j, NativeFocusGestureListener nativeFocusGestureListener);

        private native NativeFocusGesture native_asFocusGesture(long j);

        private native boolean native_getShowUIIndicator(long j);

        private native void native_setShowUIIndicator(long j, boolean z);

        private native String native_toJson(long j);

        private native void native_triggerFocus(long j, PointWithUnit pointWithUnit);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus
        public void addListener(NativeFocusGestureListener nativeFocusGestureListener) {
            native_addListener(this.nativeRef, nativeFocusGestureListener);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus
        public NativeFocusGesture asFocusGesture() {
            return native_asFocusGesture(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus
        public boolean getShowUIIndicator() {
            return native_getShowUIIndicator(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus
        public void setShowUIIndicator(boolean z) {
            native_setShowUIIndicator(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus
        public String toJson() {
            return native_toJson(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeTapToFocus
        public void triggerFocus(PointWithUnit pointWithUnit) {
            native_triggerFocus(this.nativeRef, pointWithUnit);
        }
    }

    public static native NativeTapToFocus create(boolean z);

    public abstract void addListener(NativeFocusGestureListener nativeFocusGestureListener);

    public abstract NativeFocusGesture asFocusGesture();

    public abstract boolean getShowUIIndicator();

    public abstract void setShowUIIndicator(boolean z);

    public abstract String toJson();

    public abstract void triggerFocus(PointWithUnit pointWithUnit);
}
