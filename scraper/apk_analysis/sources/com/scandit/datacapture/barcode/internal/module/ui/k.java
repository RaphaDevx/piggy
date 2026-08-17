package com.scandit.datacapture.barcode.internal.module.ui;

import android.view.MotionEvent;
import com.scandit.datacapture.core.common.geometry.Point;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k implements j {
    public final Function1 a;
    public final Function3 b;
    public final Function1 c;
    public Object d;

    public k(Function1 selectedItemForTouchLocation, Function3 shouldAcceptItemForTouch, Function1 onTouchConfirmed) {
        Intrinsics.checkNotNullParameter(selectedItemForTouchLocation, "selectedItemForTouchLocation");
        Intrinsics.checkNotNullParameter(shouldAcceptItemForTouch, "shouldAcceptItemForTouch");
        Intrinsics.checkNotNullParameter(onTouchConfirmed, "onTouchConfirmed");
        this.a = selectedItemForTouchLocation;
        this.b = shouldAcceptItemForTouch;
        this.c = onTouchConfirmed;
    }

    public final boolean a(MotionEvent event) {
        Object obj;
        Object invoke;
        Intrinsics.checkNotNullParameter(event, "event");
        Point point = new Point(event.getX(), event.getY());
        if (event.getAction() == 0 && (invoke = this.a.invoke(point)) != null) {
            this.d = invoke;
            return true;
        }
        if (event.getAction() != 1 || (obj = this.d) == null) {
            return false;
        }
        this.d = null;
        if (((Boolean) this.b.invoke(obj, this.a.invoke(point), point)).booleanValue()) {
            this.c.invoke(obj);
            return true;
        }
        return false;
    }
}
