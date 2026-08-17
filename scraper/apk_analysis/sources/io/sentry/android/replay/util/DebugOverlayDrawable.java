package io.sentry.android.replay.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DebugOverlayDrawable.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J(\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0017J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\u0012\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0014\u0010\u001b\u001a\u00020\f2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lio/sentry/android/replay/util/DebugOverlayDrawable;", "Landroid/graphics/drawable/Drawable;", "()V", "masks", "", "Landroid/graphics/Rect;", "padding", "", "paint", "Landroid/graphics/Paint;", "tmpRect", "draw", "", "canvas", "Landroid/graphics/Canvas;", "drawTextWithOutline", "bottomRightLabel", "", "x", "y", "getOpacity", "", "setAlpha", "alpha", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "updateMasks", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DebugOverlayDrawable extends Drawable {
    private static final float STROKE_WIDTH = 6.0f;
    private static final int TEXT_COLOR = -16777216;
    private static final int TEXT_OUTLINE_COLOR = -1;
    private static final float TEXT_SIZE = 32.0f;
    public static final int $stable = 8;
    private static final int maskBackgroundColor = Color.argb(32, 255, 20, 20);
    private static final int maskBorderColor = Color.argb(128, 255, 20, 20);
    private final Paint paint = new Paint(1);
    private final float padding = STROKE_WIDTH;
    private final Rect tmpRect = new Rect();
    private List<Rect> masks = CollectionsKt.emptyList();

    @Override // android.graphics.drawable.Drawable
    @Deprecated(message = "Deprecated in Java")
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.paint.setTextSize(TEXT_SIZE);
        this.paint.setColor(-16777216);
        this.paint.setStrokeWidth(STROKE_WIDTH);
        for (Rect rect : this.masks) {
            this.paint.setColor(maskBackgroundColor);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, this.paint);
            this.paint.setColor(maskBorderColor);
            this.paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(rect, this.paint);
            StringBuilder sb = new StringBuilder();
            sb.append(rect.left);
            sb.append(JsonPointer.SEPARATOR);
            sb.append(rect.top);
            String sb2 = sb.toString();
            this.paint.getTextBounds(sb2, 0, sb2.length(), this.tmpRect);
            drawTextWithOutline(canvas, sb2, rect.left, rect.top);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(rect.right);
            sb3.append(JsonPointer.SEPARATOR);
            sb3.append(rect.bottom);
            String sb4 = sb3.toString();
            this.paint.getTextBounds(sb4, 0, sb4.length(), this.tmpRect);
            drawTextWithOutline(canvas, sb4, rect.right - this.tmpRect.width(), rect.bottom + this.tmpRect.height());
        }
    }

    private final void drawTextWithOutline(Canvas canvas, String bottomRightLabel, float x, float y) {
        this.paint.setColor(-1);
        this.paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(bottomRightLabel, x, y, this.paint);
        this.paint.setColor(-16777216);
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawText(bottomRightLabel, x, y, this.paint);
    }

    public final void updateMasks(List<Rect> masks) {
        Intrinsics.checkNotNullParameter(masks, "masks");
        this.masks = masks;
        invalidateSelf();
    }
}
