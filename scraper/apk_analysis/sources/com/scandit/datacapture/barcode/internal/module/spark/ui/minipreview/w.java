package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.C0438b;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;

/* loaded from: classes2.dex */
public final class w implements View.OnTouchListener {
    public final SparkScanStateManager a;
    public final View b;
    public Function1 c;
    public final C0438b d;
    public float e;
    public float f;
    public final PointF g;
    public final PointF h;
    public long i;
    public MotionEvent j;
    public final int k;
    public final Handler l;
    public boolean m;
    public final v n;

    public w(Context context, SparkScanStateManager stateManager, View movingTarget) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(movingTarget, "movingTarget");
        this.a = stateManager;
        this.b = movingTarget;
        this.c = u.a;
        this.d = new C0438b(context);
        this.g = new PointF(0.0f, 0.0f);
        this.h = new PointF(stateManager.p(), stateManager.q());
        this.k = ViewConfiguration.getLongPressTimeout();
        this.l = new Handler(Looper.getMainLooper());
        this.n = new v(this);
    }

    public final PointF a(int i, int i2, Pair sizes) {
        Intrinsics.checkNotNullParameter(sizes, "sizes");
        Size size = (Size) sizes.getFirst();
        Size size2 = (Size) sizes.getSecond();
        Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.h;
        float intValue = ((Number) lazy.getValue()).intValue() + 0.0f;
        Lazy lazy2 = com.scandit.datacapture.barcode.internal.module.spark.ui.E.i;
        float intValue2 = ((Number) lazy2.getValue()).intValue() + 0.0f;
        float width = (i - size2.getWidth()) - ((Number) lazy2.getValue()).intValue();
        float height = (i2 - size2.getHeight()) - ((Number) lazy.getValue()).intValue();
        int i3 = 0;
        boolean z = size2.getWidth() > size.getWidth();
        float p = this.a.p() > 0.0f ? this.a.p() : this.b.getX();
        float q = this.a.q() > 0.0f ? this.a.q() : this.b.getY();
        int width2 = (!z || (p - ((float) (size2.getWidth() - size.getWidth()))) - ((float) ((Number) lazy2.getValue()).intValue()) >= 0.0f) ? (z || this.a.m() <= 0.0f) ? size.getWidth() - size2.getWidth() : (int) (this.a.m() - p) : 0;
        if (!z && this.a.n() > 0.0f) {
            i3 = (int) (this.a.n() - q);
        }
        float coerceIn = RangesKt.coerceIn(q + i3, intValue, height);
        float coerceIn2 = RangesKt.coerceIn(p + width2, intValue2, width);
        this.a.c(coerceIn2);
        this.a.d(coerceIn);
        return new PointF(coerceIn2, coerceIn);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
    
        if (r9 != 3) goto L34;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r9, android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 385
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.w.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void a(MotionEvent motionEvent) {
        this.i = System.currentTimeMillis();
        this.e = this.b.getX() - motionEvent.getRawX();
        this.f = this.b.getY() - motionEvent.getRawY();
        this.g.x = motionEvent.getRawX() + this.e;
        this.g.y = motionEvent.getRawY() + this.f;
        this.m = false;
        this.l.removeCallbacksAndMessages(null);
        Handler handler = this.l;
        final v vVar = this.n;
        handler.postDelayed(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.w$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                w.a(KFunction.this);
            }
        }, this.k);
    }

    public static final void a(KFunction tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        ((Function0) tmp0).invoke();
    }
}
