package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.common.geometry.Point;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class GestureListener {
    private final /* synthetic */ n a;

    public GestureListener(NativeGestureListener impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new n(impl);
    }

    public final boolean a(Point point) {
        Intrinsics.checkNotNullParameter(point, "point");
        n nVar = this.a;
        nVar.getClass();
        Intrinsics.checkNotNullParameter(point, "point");
        return nVar.a.onDoubleTap(point);
    }

    public final boolean b(Point point) {
        Intrinsics.checkNotNullParameter(point, "point");
        n nVar = this.a;
        nVar.getClass();
        Intrinsics.checkNotNullParameter(point, "point");
        return nVar.a.onTap(point);
    }

    public final boolean c() {
        return this.a.a.onSwipeRight();
    }

    public final boolean d() {
        return this.a.a.onSwipeUp();
    }

    public final boolean b() {
        return this.a.a.onSwipeLeft();
    }

    public final boolean a() {
        return this.a.a.onSwipeDown();
    }
}
