package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.extensions.DrawableExtensionsKt;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j extends View {
    public static final Lazy h = LazyKt.lazy(C0324b.a);
    public static final Lazy i = LazyKt.lazy(C0325c.a);
    public static final Lazy j = LazyKt.lazy(C0326d.a);
    public static final int k = 1293030937;
    public List a;
    public final Paint b;
    public final Bitmap c;
    public final i d;
    public final Lazy e;
    public boolean f;
    public Function1 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = CollectionsKt.emptyList();
        this.b = new Paint();
        Drawable drawable = context.getDrawable(R.drawable.sc_ic_delete_clusters);
        if (drawable == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Intrinsics.checkNotNullExpressionValue(drawable, "requireNotNull(...)");
        Bitmap bitmap = DrawableExtensionsKt.toBitmap(drawable);
        float floatValue = ((Number) i.getValue()).floatValue();
        float floatValue2 = ((Number) j.getValue()).floatValue();
        int i2 = k;
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Paint paint = new Paint();
        paint.setMaskFilter(new BlurMaskFilter(floatValue, BlurMaskFilter.Blur.NORMAL));
        paint.setColor(i2);
        float f = 2;
        float f2 = floatValue * f;
        int abs = (int) ((Math.abs(0.0f) * f) + bitmap.getWidth() + f2);
        int abs2 = (int) ((Math.abs(floatValue2) * f) + bitmap.getHeight() + f2);
        Bitmap createBitmap = Bitmap.createBitmap(abs, abs2, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(abs / 2.0f, abs2 / 2.0f, Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2.0f, paint);
        canvas.drawBitmap(bitmap, (abs - bitmap.getWidth()) / 2.0f, (abs2 - bitmap.getHeight()) / 2.0f, (Paint) null);
        bitmap.recycle();
        this.c = createBitmap;
        this.d = new i(this);
        this.e = LazyKt.lazy(new g(context, this));
        setFocusable(true);
        setFocusableInTouchMode(true);
        setImportantForAccessibility(2);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        for (D d : this.a) {
            if (this.f && d.a != null) {
                Point point = d.d;
                canvas.drawBitmap(this.c, point.getX() - (this.c.getWidth() / 2), point.getY() - (this.c.getHeight() / 2), this.b);
            }
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return ((GestureDetector) this.e.getValue()).onTouchEvent(event);
    }
}
