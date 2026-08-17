package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.view.GestureDetector;
import android.view.MotionEvent;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i extends GestureDetector.SimpleOnGestureListener {
    public final /* synthetic */ j a;

    public i(j jVar) {
        this.a = jVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Lazy lazy = j.h;
        return AbstractC0328f.a(e, this.a.a, C0327e.a);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent e) {
        Intrinsics.checkNotNullParameter(e, "e");
        j jVar = this.a;
        if (!jVar.f) {
            return false;
        }
        Lazy lazy = j.h;
        return AbstractC0328f.a(e, jVar.a, new h(this.a));
    }
}
