package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0015\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR&\u0010 \u001a\u00020\u000f2\b\b\u0001\u0010\u001d\u001a\u00020\u000f8G@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0018\"\u0004\b\u001f\u0010\u0012¨\u0006!"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/count/ui/mapeditor/components/ToteDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/graphics/Rect;", "bounds", "", "onBoundsChange", "(Landroid/graphics/Rect;)V", "Landroid/graphics/Canvas;", "canvas", "draw", "(Landroid/graphics/Canvas;)V", "", "alpha", "setAlpha", "(I)V", "Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "getOpacity", "()I", "Landroid/graphics/Outline;", "outline", "getOutline", "(Landroid/graphics/Outline;)V", TypedValues.Custom.S_COLOR, "getToteColor", "setToteColor", "toteColor", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ToteDrawable extends Drawable {
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final Path f;
    private final Path g;
    private final Paint h;
    private final Paint i;
    private final Drawable j;

    public ToteDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = PixelExtensionsKt.pxFromDp(16.0f, context);
        this.b = PixelExtensionsKt.pxFromDp(4.0f, context);
        this.c = PixelExtensionsKt.pxFromDp(80.0f, context);
        this.d = PixelExtensionsKt.pxFromDp(20.0f, context);
        this.e = PixelExtensionsKt.pxFromDp(10.0f, context);
        this.f = new Path();
        this.g = new Path();
        Paint paint = new Paint();
        paint.setColor(-1);
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.h = paint;
        Paint paint2 = new Paint();
        paint2.setColor(BinsView.DEFAULT_TOTE_BACKGROUND_COLOR);
        paint2.setStyle(style);
        this.i = paint2;
        Drawable drawable = context.getDrawable(R.drawable.sc_map_editor_grab);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.setTint(MapEditorUtils.INSTANCE.uiElementColorOnBackground(getToteColor()));
        } else {
            drawable = null;
        }
        this.j = drawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Rect bounds = getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        float f = this.a;
        canvas.drawRect(f, 0.0f, f + (bounds.width() - (this.a * 2.0f)), bounds.height() / 2.0f, this.h);
        canvas.drawPath(this.f, this.i);
        Drawable drawable = this.j;
        if (drawable != null) {
            Rect bounds2 = drawable.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds2, "getBounds(...)");
            float width = bounds.width() - bounds2.width();
            float f2 = this.e;
            canvas.save();
            canvas.translate(width - f2, f2);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Intrinsics.checkNotNullParameter(outline, "outline");
        outline.setRoundRect(0, 0, getBounds().width(), getBounds().height(), this.a);
    }

    public final int getToteColor() {
        return this.i.getColor();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        float width = bounds.width();
        float height = bounds.height();
        this.f.rewind();
        float f = (width - this.c) / 2.0f;
        this.f.moveTo(f - this.b, 0.0f);
        this.f.lineTo(f, this.d);
        this.f.lineTo(this.c + f, this.d);
        this.f.lineTo(f + this.c + this.b, 0.0f);
        float f2 = this.a * 2;
        float f3 = width - f2;
        this.f.arcTo(f3, 0.0f, width, f2, 270.0f, 90.0f, false);
        float f4 = height - f2;
        this.f.arcTo(f3, f4, width, height, 0.0f, 90.0f, false);
        this.f.arcTo(0.0f, f4, f2, height, 90.0f, 90.0f, false);
        this.f.arcTo(0.0f, 0.0f, f2, f2, 180.0f, 80.0f, false);
        this.f.close();
        this.g.rewind();
        Path path = this.g;
        float f5 = this.a;
        path.addRoundRect(0.0f, 0.0f, width, height, new float[]{f5, f5, f5, f5, f5, f5, f5, f5}, Path.Direction.CW);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public final void setToteColor(int i) {
        this.i.setColor(i);
        Drawable drawable = this.j;
        if (drawable != null) {
            drawable.setTint(MapEditorUtils.INSTANCE.uiElementColorOnBackground(i));
        }
        invalidateSelf();
    }
}
