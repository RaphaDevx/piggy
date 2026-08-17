package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p extends View {
    public static final /* synthetic */ int h = 0;
    public List a;
    public final Paint b;
    public final Paint c;
    public final o d;
    public final Lazy e;
    public boolean f;
    public Function1 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = CollectionsKt.emptyList();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        this.b = paint;
        Paint paint2 = new Paint();
        paint2.setColor(-1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(PixelExtensionsKt.pxFromDp(1.0f, context));
        this.c = paint2;
        this.d = new o(this);
        this.e = LazyKt.lazy(new m(context, this));
        setFocusable(true);
        setFocusableInTouchMode(true);
        setImportantForAccessibility(2);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        for (D d : this.a) {
            int i = d.c;
            if (i != 0) {
                Path path = d.e;
                Paint paint = this.b;
                paint.setColor(i);
                Unit unit = Unit.INSTANCE;
                canvas.drawPath(path, paint);
                canvas.drawPath(path, this.c);
            }
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return ((GestureDetector) this.e.getValue()).onTouchEvent(event);
    }
}
