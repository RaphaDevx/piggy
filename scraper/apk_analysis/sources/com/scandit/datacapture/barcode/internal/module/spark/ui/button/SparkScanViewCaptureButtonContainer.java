package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.ui.E;
import com.scandit.datacapture.barcode.internal.module.spark.ui.EnumC0437a;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class SparkScanViewCaptureButtonContainer extends RelativeLayout {
    private static final Lazy j = LazyKt.lazy(h.a);
    private static final Lazy k = LazyKt.lazy(g.a);
    public static final /* synthetic */ int l = 0;
    private final SparkScanStateManager a;
    private final n b;
    private Bitmap c;
    private Integer d;
    private Integer e;
    private Integer f;
    private Integer g;
    private final f h;
    private ViewGroup i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SparkScanViewCaptureButtonContainer(Context context, SparkScanStateManager stateManager) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.a = stateManager;
        n nVar = new n(context, stateManager);
        this.b = nVar;
        this.c = SparkScanViewDefaults.getDefaultTriggerButtonImage();
        this.d = SparkScanViewDefaults.getDefaultTriggerButtonAnimationColor();
        this.e = SparkScanViewDefaults.getDefaultTriggerButtonExpandedColor();
        this.f = SparkScanViewDefaults.getDefaultTriggerButtonTintColor();
        Integer defaultTriggerButtonCollapsedColor = SparkScanViewDefaults.getDefaultTriggerButtonCollapsedColor();
        this.g = defaultTriggerButtonCollapsedColor;
        this.h = new f(context, this.c, defaultTriggerButtonCollapsedColor, this.e, this.f, this.d, nVar);
        setClipChildren(false);
    }

    private final void f() {
        Point a;
        f fVar = this.h;
        fVar.getClass();
        ViewGroup viewGroup = this.i;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup = null;
        }
        Size correctedViewSize = ViewExtensionsKt.getCorrectedViewSize(viewGroup, this.a.C());
        int height = correctedViewSize.getHeight();
        int width = correctedViewSize.getWidth();
        if (width <= 0 || height <= 0) {
            return;
        }
        SparkScanStateManager sparkScanStateManager = this.a;
        if ((sparkScanStateManager.z() <= 0.0f || sparkScanStateManager.A() <= 0.0f) && !this.a.j()) {
            a = a(correctedViewSize, this.h.c instanceof a);
        } else {
            n nVar = this.b;
            int i = fVar.getLayoutParams().width;
            int i2 = fVar.getLayoutParams().height;
            PointF pointF = nVar.p;
            float f = i / 2;
            float f2 = pointF.x - f;
            float f3 = i2 / 2;
            float f4 = pointF.y - f3;
            MotionEvent motionEvent = nVar.n;
            if (!nVar.a.j() || motionEvent == null) {
                a = new Point(RangesKt.coerceIn(f2, 0.0f - (nVar.a().c instanceof a ? 0.0f : ((Number) E.r.getValue()).floatValue()), width - (nVar.a().c instanceof a ? Integer.valueOf(i) : Float.valueOf(i - ((Number) E.r.getValue()).floatValue())).floatValue()), RangesKt.coerceIn(f4, 0.0f - (nVar.a().c instanceof a ? 0.0f : ((Number) E.s.getValue()).floatValue()), height - (nVar.a().c instanceof a ? Integer.valueOf(i2) : Float.valueOf(i2 - ((Number) E.s.getValue()).floatValue())).floatValue()));
            } else {
                float rawX = ((motionEvent.getRawX() + nVar.k) - f) + (((Number) E.u.getValue()).intValue() / 2);
                float rawY = ((motionEvent.getRawY() + nVar.l) - f3) + (((Number) E.v.getValue()).intValue() / 2);
                nVar.k = rawX - motionEvent.getRawX();
                nVar.l = rawY - motionEvent.getRawY();
                nVar.r.invoke(EnumC0437a.i);
                a = new Point(rawX, rawY);
            }
        }
        fVar.animate().cancel();
        fVar.setX(a.getX());
        fVar.setY(a.getY());
    }

    public final void a(Bitmap bitmap) {
        if (Intrinsics.areEqual(bitmap, this.c)) {
            return;
        }
        this.c = bitmap;
        this.h.a(bitmap);
    }

    public final Integer b() {
        return this.g;
    }

    public final Integer c() {
        return this.e;
    }

    public final Bitmap d() {
        return this.c;
    }

    public final Integer e() {
        return this.f;
    }

    public final void g() {
        n nVar = this.b;
        nVar.getClass();
        nVar.p = new PointF(nVar.a.z(), nVar.a.A());
        f();
    }

    public final void h() {
        ((n) this.h.a).e.removeCallbacksAndMessages(null);
    }

    public final void i() {
        this.h.a();
        f();
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Point a = a(new Size(i, i2), true);
        float x = a.getX();
        float y = a.getY();
        if (y < 0.0f || x < 0.0f) {
            return;
        }
        if (this.a.A() < 0.0f || this.a.z() < 0.0f) {
            PointF pointF = this.b.p;
            pointF.y = y;
            pointF.x = x;
        }
        f();
    }

    public final void b(Integer num) {
        if (Intrinsics.areEqual(this.g, num)) {
            return;
        }
        this.g = num;
        f fVar = this.h;
        fVar.f = num;
        fVar.b();
    }

    public final void c(Integer num) {
        if (Intrinsics.areEqual(num, this.e)) {
            return;
        }
        this.e = num;
        f fVar = this.h;
        fVar.e = num;
        fVar.b();
    }

    public final void d(Integer num) {
        if (Intrinsics.areEqual(this.f, num)) {
            return;
        }
        this.f = num;
        f fVar = this.h;
        fVar.g = num;
        fVar.b();
    }

    private final Point a(Size size, boolean z) {
        int intValue;
        int intValue2;
        float coerceAtLeast;
        if (z) {
            intValue = ((Number) E.u.getValue()).intValue();
        } else {
            intValue = ((Number) E.p.getValue()).intValue();
        }
        if (z) {
            intValue2 = ((Number) E.v.getValue()).intValue();
        } else {
            intValue2 = ((Number) E.o.getValue()).intValue();
        }
        float coerceAtLeast2 = RangesKt.coerceAtLeast((size.getHeight() * 2) / 3.0f, size.getHeight() - ((Number) k.getValue()).floatValue());
        if (j.a[this.a.r().ordinal()] == 1) {
            coerceAtLeast = RangesKt.coerceAtMost(size.getWidth() / 3.0f, ((Number) j.getValue()).floatValue());
        } else {
            coerceAtLeast = RangesKt.coerceAtLeast((size.getWidth() * 2) / 3.0f, size.getWidth() - ((Number) j.getValue()).floatValue());
        }
        return new Point(coerceAtLeast - (intValue / 2.0f), coerceAtLeast2 - (intValue2 / 2.0f));
    }

    public final Integer a() {
        return this.d;
    }

    public final void a(Integer num) {
        if (Intrinsics.areEqual(num, this.d)) {
            return;
        }
        this.d = num;
        f fVar = this.h;
        fVar.h = num;
        fVar.b();
    }

    public final void a(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        if (getParent() != null) {
            return;
        }
        this.i = container;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.width = -1;
        layoutParams.height = -1;
        container.addView(this, layoutParams);
        View view = this.h;
        view.getClass();
        int intValue = ((Number) E.u.getValue()).intValue();
        int intValue2 = ((Number) E.v.getValue()).intValue();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(intValue, intValue2);
        layoutParams2.width = intValue;
        layoutParams2.height = intValue2;
        addView(view, layoutParams2);
    }

    public final void a(i iVar) {
        this.h.b = iVar;
    }

    public final void a(SparkScanViewState viewState) {
        Intrinsics.checkNotNullParameter(viewState, "viewState");
        f fVar = this.h;
        boolean z = fVar.c instanceof a;
        fVar.a(viewState);
        if (z) {
            f();
        }
    }

    public final void a(boolean z) {
        f fVar = this.h;
        fVar.m = z;
        fVar.b();
    }
}
