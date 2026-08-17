package com.scandit.datacapture.core.internal.module.ui;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.scandit.datacapture.core.common.geometry.Point;
import java.util.EnumSet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    public final /* synthetic */ p a;

    public o(p pVar) {
        this.a = pVar;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent e) {
        p pVar;
        GestureListener gestureListener;
        Intrinsics.checkNotNullParameter(e, "e");
        if (!this.a.d.contains(NativeGestureType.DOUBLE_TAP) || (gestureListener = (pVar = this.a).c) == null) {
            return false;
        }
        return gestureListener.a(p.a(pVar, e));
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTapEvent(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent e2, float f, float f2) {
        GestureListener gestureListener;
        GestureListener gestureListener2;
        Intrinsics.checkNotNullParameter(e2, "e2");
        EnumSet enumSet = this.a.d;
        NativeGestureType nativeGestureType = NativeGestureType.SWIPE_UP;
        if ((!enumSet.contains(nativeGestureType) && !this.a.d.contains(NativeGestureType.SWIPE_DOWN) && !this.a.d.contains(NativeGestureType.SWIPE_LEFT) && !this.a.d.contains(NativeGestureType.SWIPE_RIGHT)) || motionEvent == null) {
            return false;
        }
        Point a = p.a(this.a, motionEvent);
        Point a2 = p.a(this.a, e2);
        float x = a2.getX() - a.getX();
        float y = a2.getY() - a.getY();
        float f3 = 2;
        if (Math.abs(y) > Math.abs(x * f3)) {
            if (y <= 0.0f || !this.a.d.contains(NativeGestureType.SWIPE_DOWN)) {
                if (!this.a.d.contains(nativeGestureType) || (gestureListener2 = this.a.c) == null) {
                    return false;
                }
                return gestureListener2.d();
            }
            GestureListener gestureListener3 = this.a.c;
            if (gestureListener3 != null) {
                return gestureListener3.a();
            }
            return false;
        }
        if (Math.abs(x) <= Math.abs(y * f3)) {
            return false;
        }
        if (x <= 0.0f || !this.a.d.contains(NativeGestureType.SWIPE_RIGHT)) {
            if (!this.a.d.contains(NativeGestureType.SWIPE_LEFT) || (gestureListener = this.a.c) == null) {
                return false;
            }
            return gestureListener.b();
        }
        GestureListener gestureListener4 = this.a.c;
        if (gestureListener4 != null) {
            return gestureListener4.c();
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent e2, float f, float f2) {
        Intrinsics.checkNotNullParameter(e2, "e2");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onShowPress(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public final boolean onSingleTapConfirmed(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent e) {
        p pVar;
        GestureListener gestureListener;
        Intrinsics.checkNotNullParameter(e, "e");
        if (!this.a.d.contains(NativeGestureType.TAP) || (gestureListener = (pVar = this.a).c) == null) {
            return false;
        }
        return gestureListener.b(p.a(pVar, e));
    }
}
