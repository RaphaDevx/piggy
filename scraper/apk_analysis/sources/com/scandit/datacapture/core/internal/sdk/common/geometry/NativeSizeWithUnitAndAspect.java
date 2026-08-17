package com.scandit.datacapture.core.internal.sdk.common.geometry;

import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.SizeWithAspect;
import com.scandit.datacapture.core.common.geometry.SizeWithUnit;
import com.scandit.datacapture.core.common.geometry.SizingMode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSizeWithUnitAndAspect {

    public static final class CppProxy extends NativeSizeWithUnitAndAspect {
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

        private native SizeWithAspect native_getHeightWithAspect(long j);

        private native SizeWithAspect native_getShorterDimensionWithAspect(long j);

        private native SizingMode native_getSizingMode(long j);

        private native SizeWithUnit native_getWidthAndHeight(long j);

        private native SizeWithAspect native_getWidthWithAspect(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect
        public SizeWithAspect getHeightWithAspect() {
            return native_getHeightWithAspect(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect
        public SizeWithAspect getShorterDimensionWithAspect() {
            return native_getShorterDimensionWithAspect(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect
        public SizingMode getSizingMode() {
            return native_getSizingMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect
        public SizeWithUnit getWidthAndHeight() {
            return native_getWidthAndHeight(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect
        public SizeWithAspect getWidthWithAspect() {
            return native_getWidthWithAspect(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.geometry.NativeSizeWithUnitAndAspect
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeSizeWithUnitAndAspect createWithHeightAndAspectRatio(FloatWithUnit floatWithUnit, float f);

    public static native NativeSizeWithUnitAndAspect createWithShorterDimensionAndAspectRatio(float f, float f2);

    public static native NativeSizeWithUnitAndAspect createWithWidthAndAspectRatio(FloatWithUnit floatWithUnit, float f);

    public static native NativeSizeWithUnitAndAspect createWithWidthAndHeight(SizeWithUnit sizeWithUnit);

    public abstract SizeWithAspect getHeightWithAspect();

    public abstract SizeWithAspect getShorterDimensionWithAspect();

    public abstract SizingMode getSizingMode();

    public abstract SizeWithUnit getWidthAndHeight();

    public abstract SizeWithAspect getWidthWithAspect();

    public abstract String toJson();
}
