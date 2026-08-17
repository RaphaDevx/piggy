package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.view.GestureDetector;
import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o extends GestureDetector.SimpleOnGestureListener {
    public final /* synthetic */ p a;

    public o(p pVar) {
        this.a = pVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        int i = p.h;
        return l.a(e, this.a.a, k.a);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        p pVar = this.a;
        if (!pVar.f) {
            return false;
        }
        int i = p.h;
        return l.a(e, pVar.a, new n(this.a));
    }
}
