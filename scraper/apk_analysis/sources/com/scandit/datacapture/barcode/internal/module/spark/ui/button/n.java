package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.BounceInterpolator;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.C0438b;
import com.scandit.datacapture.barcode.internal.module.spark.ui.E;
import com.scandit.datacapture.barcode.internal.module.spark.ui.EnumC0437a;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;

/* loaded from: classes2.dex */
public final class n implements k {
    public final SparkScanStateManager a;
    public f b;
    public final C0438b c;
    public final int d;
    public final Handler e;
    public boolean f;
    public boolean g;
    public final m h;
    public final int i;
    public boolean j;
    public float k;
    public float l;
    public int m;
    public MotionEvent n;
    public final PointF o;
    public PointF p;
    public long q;
    public Function1 r;

    public n(Context context, SparkScanStateManager stateManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.a = stateManager;
        this.c = new C0438b(context);
        this.d = ViewConfiguration.getLongPressTimeout();
        this.e = new Handler(Looper.getMainLooper());
        this.h = new m(this);
        this.i = ViewConfiguration.get(context).getScaledTouchSlop();
        this.m = -1;
        this.o = new PointF(0.0f, 0.0f);
        this.p = new PointF(stateManager.z(), stateManager.A());
        this.r = l.a;
    }

    public final f a() {
        f fVar = this.b;
        if (fVar != null) {
            return fVar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("movingTarget");
        return null;
    }

    public final void b() {
        this.a.a(false);
        this.r.invoke(EnumC0437a.k);
        this.m = -1;
        if (System.currentTimeMillis() - this.q < 200) {
            MotionEvent motionEvent = this.n;
            float rawX = (motionEvent != null ? motionEvent.getRawX() : -1.0f) + this.k;
            MotionEvent motionEvent2 = this.n;
            this.r.invoke(this.c.a(this.o, new PointF(rawX, (motionEvent2 != null ? motionEvent2.getRawY() : -1.0f) + this.l)));
        }
        if (this.f) {
            this.r.invoke(EnumC0437a.h);
        }
        this.e.removeCallbacksAndMessages(null);
        this.r.invoke(EnumC0437a.j);
        if (this.j) {
            this.j = false;
            f a = a();
            a.getClass();
            float floatValue = 0.0f - (a().c instanceof a ? 0.0f : ((Number) E.s.getValue()).floatValue());
            float floatValue2 = 0.0f - (a().c instanceof a ? 0.0f : ((Number) E.r.getValue()).floatValue());
            Object parent = a.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            int width = ((View) parent).getWidth();
            int width2 = a.getWidth();
            float floatValue3 = width - (a().c instanceof a ? Integer.valueOf(width2) : Float.valueOf(width2 - ((Number) E.r.getValue()).floatValue())).floatValue();
            Object parent2 = a.getParent();
            Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.View");
            int height = ((View) parent2).getHeight();
            int height2 = a.getHeight();
            float floatValue4 = height - (a().c instanceof a ? Integer.valueOf(height2) : Float.valueOf(height2 - ((Number) E.s.getValue()).floatValue())).floatValue();
            PointF pointF = this.p;
            pointF.x = RangesKt.coerceIn(pointF.x, floatValue2, floatValue3);
            PointF pointF2 = this.p;
            pointF2.y = RangesKt.coerceIn(pointF2.y, floatValue, floatValue4);
            a.animate().x(this.p.x).y(this.p.y).setDuration(300L).setInterpolator(new BounceInterpolator()).start();
            this.p.x += a.getWidth() / 2;
            this.p.y += a.getHeight() / 2;
            this.a.e(this.p.x);
            this.a.f(this.p.y);
        }
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        int i;
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        int actionMasked = event.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int findPointerIndex = event.findPointerIndex(this.m);
                    if (findPointerIndex >= 0) {
                        this.n = event;
                        if (!(a().c instanceof b) || this.g) {
                            long currentTimeMillis = System.currentTimeMillis() - this.q;
                            float x = event.getX(findPointerIndex);
                            float y = event.getY(findPointerIndex);
                            float rawX = (event.getRawX() - event.getX()) + x;
                            float rawY = (event.getRawY() - event.getY()) + y;
                            float abs = Math.abs(this.o.x - (this.k + rawX));
                            float abs2 = Math.abs(this.o.y - (this.l + rawY));
                            float f = this.i;
                            boolean z = abs < f && abs2 < f;
                            i = currentTimeMillis < 200 ? 1 : 0;
                            if (!z && !this.j) {
                                this.e.removeCallbacksAndMessages(null);
                            }
                            if (i == 0 || !z) {
                                this.j = true;
                                PointF pointF = this.p;
                                pointF.x = rawX + this.k;
                                pointF.y = rawY + this.l;
                                f a = a();
                                a.getClass();
                                a.animate().cancel();
                                a.setX(this.p.x);
                                a.setY(this.p.y);
                            }
                        }
                    }
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        int actionIndex = event.getActionIndex();
                        if (event.getPointerId(actionIndex) == this.m) {
                            i = actionIndex == 0 ? 1 : 0;
                            if (i < event.getPointerCount()) {
                                this.m = event.getPointerId(i);
                                float x2 = event.getX(i);
                                float y2 = event.getY(i);
                                float rawX2 = (event.getRawX() - event.getX()) + x2;
                                float rawY2 = (event.getRawY() - event.getY()) + y2;
                                f a2 = a();
                                a2.getClass();
                                this.k = a2.getX() - rawX2;
                                f a3 = a();
                                a3.getClass();
                                this.l = a3.getY() - rawY2;
                            } else {
                                b();
                            }
                        }
                    }
                }
            }
            b();
        } else {
            f a4 = a();
            a4.getClass();
            float width = a4.getWidth() / 2;
            float x3 = event.getX() - width;
            float y3 = event.getY() - width;
            if ((y3 * y3) + (x3 * x3) > r9 * r9) {
                return false;
            }
            a(event);
        }
        return true;
    }

    public final void a(MotionEvent motionEvent) {
        this.a.a(true);
        this.m = motionEvent.getPointerId(0);
        this.n = motionEvent;
        this.r.invoke(EnumC0437a.a);
        this.q = System.currentTimeMillis();
        f a = a();
        a.getClass();
        this.k = a.getX() - motionEvent.getRawX();
        f a2 = a();
        a2.getClass();
        this.l = a2.getY() - motionEvent.getRawY();
        this.o.x = motionEvent.getRawX() + this.k;
        this.o.y = motionEvent.getRawY() + this.l;
        if (a().c instanceof a) {
            this.r.invoke(EnumC0437a.i);
        }
        this.f = false;
        this.g = false;
        this.e.removeCallbacksAndMessages(null);
        Handler handler = this.e;
        final m mVar = this.h;
        handler.postDelayed(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.button.n$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                n.a(KFunction.this);
            }
        }, this.d);
    }

    public static final void a(KFunction tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        ((Function0) tmp0).invoke();
    }
}
