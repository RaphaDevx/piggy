package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.common.geometry.Point;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeGestureListener {

    public static final class CppProxy extends NativeGestureListener {
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

        private native boolean native_onDoubleTap(long j, Point point);

        private native boolean native_onSwipeDown(long j);

        private native boolean native_onSwipeLeft(long j);

        private native boolean native_onSwipeRight(long j);

        private native boolean native_onSwipeUp(long j);

        private native boolean native_onTap(long j, Point point);

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureListener
        public boolean onDoubleTap(Point point) {
            return native_onDoubleTap(this.nativeRef, point);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureListener
        public boolean onSwipeDown() {
            return native_onSwipeDown(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureListener
        public boolean onSwipeLeft() {
            return native_onSwipeLeft(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureListener
        public boolean onSwipeRight() {
            return native_onSwipeRight(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureListener
        public boolean onSwipeUp() {
            return native_onSwipeUp(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureListener
        public boolean onTap(Point point) {
            return native_onTap(this.nativeRef, point);
        }
    }

    public abstract boolean onDoubleTap(Point point);

    public abstract boolean onSwipeDown();

    public abstract boolean onSwipeLeft();

    public abstract boolean onSwipeRight();

    public abstract boolean onSwipeUp();

    public abstract boolean onTap(Point point);
}
