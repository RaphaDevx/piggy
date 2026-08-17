package com.scandit.datacapture.core.internal.sdk.area;

import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeRectangularLocationSelection {

    public static final class CppProxy extends NativeRectangularLocationSelection {
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

        private native NativeLocationSelection native_asLocationSelection(long j);

        private native NativeSizeWithUnitAndAspect native_getSizeWithUnitAndAspect(long j);

        private native void native_setHeightAndAspectRatio(long j, FloatWithUnit floatWithUnit, float f);

        private native void native_setWidthAndAspectRatio(long j, FloatWithUnit floatWithUnit, float f);

        private native void native_setWidthAndHeight(long j, FloatWithUnit floatWithUnit, FloatWithUnit floatWithUnit2);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection
        public NativeLocationSelection asLocationSelection() {
            return native_asLocationSelection(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection
        public NativeSizeWithUnitAndAspect getSizeWithUnitAndAspect() {
            return native_getSizeWithUnitAndAspect(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection
        public void setHeightAndAspectRatio(FloatWithUnit floatWithUnit, float f) {
            native_setHeightAndAspectRatio(this.nativeRef, floatWithUnit, f);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection
        public void setWidthAndAspectRatio(FloatWithUnit floatWithUnit, float f) {
            native_setWidthAndAspectRatio(this.nativeRef, floatWithUnit, f);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection
        public void setWidthAndHeight(FloatWithUnit floatWithUnit, FloatWithUnit floatWithUnit2) {
            native_setWidthAndHeight(this.nativeRef, floatWithUnit, floatWithUnit2);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.area.NativeRectangularLocationSelection
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeRectangularLocationSelection create();

    public abstract NativeLocationSelection asLocationSelection();

    public abstract NativeSizeWithUnitAndAspect getSizeWithUnitAndAspect();

    public abstract void setHeightAndAspectRatio(FloatWithUnit floatWithUnit, float f);

    public abstract void setWidthAndAspectRatio(FloatWithUnit floatWithUnit, float f);

    public abstract void setWidthAndHeight(FloatWithUnit floatWithUnit, FloatWithUnit floatWithUnit2);

    public abstract String toJson();
}
