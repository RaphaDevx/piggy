package com.scandit.datacapture.core.internal.module.common.geometry;

import com.scandit.datacapture.core.common.Direction;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeRectToQuadConverter {

    public static final class CppProxy extends NativeRectToQuadConverter {
        private final long nativeRef;

        private CppProxy(long j) {
            new AtomicBoolean(false);
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);
    }

    public static native Quadrilateral computeQuadrilateralFromRectAndDirection(Rect rect, Direction direction, NativeAxis nativeAxis);

    public static native Rect computeRectFromQuadrilateral(Quadrilateral quadrilateral);
}
