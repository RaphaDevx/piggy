package com.scandit.datacapture.core.internal.module.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.scandit.datacapture.core.common.geometry.Point;
import java.util.EnumSet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p implements GestureRecognizer {
    public final o a;
    public final GestureDetector b;
    public GestureListener c;
    public EnumSet d;
    public final float e;

    public p(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        o oVar = new o(this);
        this.a = oVar;
        this.b = new GestureDetector(context, oVar, new Handler(Looper.getMainLooper()));
        EnumSet noneOf = EnumSet.noneOf(NativeGestureType.class);
        Intrinsics.checkNotNullExpressionValue(noneOf, "noneOf(...)");
        this.d = noneOf;
        this.e = 1.0f / f;
    }

    public static final Point a(p pVar, MotionEvent motionEvent) {
        pVar.getClass();
        return new Point(motionEvent.getX() * pVar.e, motionEvent.getY() * pVar.e);
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.GestureRecognizer
    public final void a(GestureListener listener, EnumSet gestures) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(gestures, "gestures");
        this.c = listener;
        this.d = gestures;
        if (gestures.contains(NativeGestureType.DOUBLE_TAP)) {
            this.b.setOnDoubleTapListener(this.a);
        } else {
            this.b.setOnDoubleTapListener(null);
        }
    }
}
