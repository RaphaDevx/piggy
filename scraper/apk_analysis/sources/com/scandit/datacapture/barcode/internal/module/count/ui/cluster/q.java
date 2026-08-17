package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.extensions.CollectionsExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class q extends View {
    public static final float i = PixelExtensionsKt.pxFromDp$default(5.0f, (Context) null, 1, (Object) null);
    public static final int j = Color.argb(77, 46, 193, 194);
    public static final float k = PixelExtensionsKt.pxFromDp$default(8.0f, (Context) null, 1, (Object) null);
    public final Paint a;
    public final float b;
    public Path c;
    public final ArrayList d;
    public final PointF e;
    public float f;
    public F g;
    public boolean h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(j);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(k);
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.a = paint;
        this.b = context.getResources().getDisplayMetrics().density;
        this.d = new ArrayList();
        this.e = new PointF();
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setImportantForAccessibility(2);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Path path = this.c;
        if (path != null) {
            canvas.drawPath(path, this.a);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction();
        if (action == 0) {
            this.e.set(event.getX(), event.getY());
            this.f = 0.0f;
            if (!this.h) {
                return super.onTouchEvent(event);
            }
            ArrayList arrayList = this.d;
            float x = event.getX();
            float y = event.getY();
            float f = this.b;
            arrayList.add(new Point(x / f, y / f));
            Path path = new Path();
            path.moveTo(event.getX(), event.getY());
            this.c = path;
            F f2 = this.g;
            if (f2 == null) {
                return true;
            }
            B b = (B) f2;
            NativeBarcodeClusterLiveEditor beginClusterEditing = b.b.beginClusterEditing();
            b.e = beginClusterEditing;
            if (beginClusterEditing == null) {
                return true;
            }
            beginClusterEditing.beginFreehand();
            return true;
        }
        if (action != 1) {
            if (action != 2) {
                return false;
            }
            this.f = Math.max(this.f, Math.max(Math.abs(event.getX() - this.e.x), Math.abs(event.getY() - this.e.y)));
            if (!this.h) {
                return super.onTouchEvent(event);
            }
            ArrayList arrayList2 = this.d;
            float x2 = event.getX();
            float y2 = event.getY();
            float f3 = this.b;
            arrayList2.add(new Point(x2 / f3, y2 / f3));
            Path path2 = this.c;
            if (path2 != null) {
                path2.lineTo(event.getX(), event.getY());
            }
            invalidate();
            return true;
        }
        if (this.f < i) {
            performClick();
        }
        Path path3 = this.c;
        if (path3 == null) {
            return true;
        }
        path3.close();
        F f4 = this.g;
        if (f4 != null) {
            ArrayList points = this.d;
            B b2 = (B) f4;
            Intrinsics.checkNotNullParameter(points, "points");
            NativeBarcodeClusterLiveEditor nativeBarcodeClusterLiveEditor = b2.e;
            if (nativeBarcodeClusterLiveEditor != null) {
                nativeBarcodeClusterLiveEditor.updateFreehand(CollectionsExtensionsKt.toArrayList(points));
            }
            NativeBarcodeClusterLiveEditor nativeBarcodeClusterLiveEditor2 = b2.e;
            if (nativeBarcodeClusterLiveEditor2 != null) {
                nativeBarcodeClusterLiveEditor2.endFreehand();
            }
            NativeBarcodeClusterLiveEditor nativeBarcodeClusterLiveEditor3 = b2.e;
            if (nativeBarcodeClusterLiveEditor3 != null) {
                nativeBarcodeClusterLiveEditor3.endEditing();
            }
            b2.e = null;
        }
        this.c = null;
        this.d.clear();
        invalidate();
        return true;
    }

    @Override // android.view.View
    public final boolean performClick() {
        this.f = 0.0f;
        this.e.set(0.0f, 0.0f);
        invalidate();
        return super.performClick();
    }
}
