package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ/\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u001b\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u000fH\u0017¢\u0006\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ColorBorderDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroid/graphics/drawable/Animatable;", "<init>", "()V", "", "start", "stop", "", "isRunning", "()Z", "Landroid/graphics/Canvas;", "canvas", "draw", "(Landroid/graphics/Canvas;)V", "", RRWebVideoEvent.JsonKeys.LEFT, RRWebVideoEvent.JsonKeys.TOP, "right", "bottom", "setBounds", "(IIII)V", "alpha", "setAlpha", "(I)V", "Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "getOpacity", "()I", "com/scandit/datacapture/core/internal/sdk/ui/cardscarousel/u", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ColorBorderDrawable extends Drawable implements Animatable {

    @Deprecated
    public static final long ALPHA_ANIMATION_DURATION = 300;

    @Deprecated
    public static final int BLUR_EDGE_COLOR = -1426063361;

    @Deprecated
    public static final long COLOR_ANIMATION_DELAY = 200;

    @Deprecated
    public static final long COLOR_ANIMATION_DURATION = 8000;

    @Deprecated
    public static final float COLOR_GRADIENT_SPREAD = 3.0f;

    @Deprecated
    public static final long FPS = 16;
    private static final Lazy l = LazyKt.lazy(t.a);
    private static final Lazy m = LazyKt.lazy(s.a);
    private static final List n = CollectionsKt.listOf((Object[]) new Integer[]{-10085, -36975, -5937409, -10160385, -10085});
    private static final List o = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(0.0f), Float.valueOf(0.2f), Float.valueOf(0.45f), Float.valueOf(0.7f), Float.valueOf(1.0f)});
    private final Map a;
    private final Map b;
    private final Path c;
    private final Paint d;
    private LinearGradient e;
    private float f;
    private float g;
    private final Matrix h;
    private long i;
    private boolean j;
    private final ColorBorderDrawable$updater$1 k;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[u.values().length];
            try {
                u uVar = u.a;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                u uVar2 = u.a;
                iArr[3] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                u uVar3 = u.a;
                iArr[5] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                u uVar4 = u.a;
                iArr[7] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                u uVar5 = u.a;
                iArr[0] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                u uVar6 = u.a;
                iArr[2] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                u uVar7 = u.a;
                iArr[4] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                u uVar8 = u.a;
                iArr[6] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.ColorBorderDrawable$updater$1] */
    public ColorBorderDrawable() {
        Map createMapBuilder = MapsKt.createMapBuilder();
        Iterator<E> it = u.j.iterator();
        while (it.hasNext()) {
            createMapBuilder.put((u) it.next(), new Path());
        }
        this.a = MapsKt.build(createMapBuilder);
        Map createMapBuilder2 = MapsKt.createMapBuilder();
        for (u uVar : u.j) {
            Paint paint = new Paint(1);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(((Number) m.getValue()).floatValue());
            Unit unit = Unit.INSTANCE;
            createMapBuilder2.put(uVar, paint);
        }
        this.b = MapsKt.build(createMapBuilder2);
        this.c = new Path();
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStrokeWidth(((Number) m.getValue()).floatValue());
        paint2.setColor(-1);
        this.d = paint2;
        this.h = new Matrix();
        this.k = new Runnable() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.ColorBorderDrawable$updater$1

            /* renamed from: a, reason: from kotlin metadata */
            private final AccelerateDecelerateInterpolator alphaInterpolator = new AccelerateDecelerateInterpolator();

            public final AccelerateDecelerateInterpolator getAlphaInterpolator() {
                return this.alphaInterpolator;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j;
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                j = ColorBorderDrawable.this.i;
                long j2 = currentAnimationTimeMillis - j;
                ColorBorderDrawable.this.g = this.alphaInterpolator.getInterpolation(1.0f - (RangesKt.coerceIn(j2, 0L, 300L) / 300.0f));
                ColorBorderDrawable.this.f = (RangesKt.coerceAtLeast(j2 - 200, 0L) % ColorBorderDrawable.COLOR_ANIMATION_DURATION) / 8000.0f;
                ColorBorderDrawable.this.scheduleSelf(this, SystemClock.uptimeMillis() + 16);
                ColorBorderDrawable.this.invalidateSelf();
            }
        };
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (getBounds().isEmpty()) {
            return;
        }
        if (this.e != null) {
            this.h.reset();
            this.h.setTranslate(this.f * getBounds().width() * 3.0f, 0.0f);
            LinearGradient linearGradient = this.e;
            if (linearGradient != null) {
                linearGradient.setLocalMatrix(this.h);
            }
        }
        for (u uVar : u.j) {
            canvas.drawPath((Path) MapsKt.getValue(this.a, uVar), (Paint) MapsKt.getValue(this.b, uVar));
        }
        this.d.setAlpha((int) (255 * this.g));
        canvas.drawPath(this.c, this.d);
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated(message = "Deprecated in Java", replaceWith = @ReplaceWith(expression = "PixelFormat.TRANSLUCENT", imports = {"android.graphics.PixelFormat"}))
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        Iterator it = this.b.values().iterator();
        while (it.hasNext()) {
            ((Paint) it.next()).setAlpha(alpha);
        }
        invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x026a  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setBounds(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 1100
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.ColorBorderDrawable.setBounds(int, int, int, int):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Iterator it = this.b.values().iterator();
        while (it.hasNext()) {
            ((Paint) it.next()).setColorFilter(colorFilter);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.j) {
            return;
        }
        this.j = true;
        this.i = AnimationUtils.currentAnimationTimeMillis();
        run();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.j) {
            unscheduleSelf(this.k);
            this.j = false;
        }
    }
}
