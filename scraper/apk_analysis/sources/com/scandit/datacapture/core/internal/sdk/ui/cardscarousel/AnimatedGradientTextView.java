package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B'\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0014¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/AnimatedGradientTextView;", "Landroid/widget/TextView;", "Landroid/graphics/drawable/Animatable;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Landroid/graphics/Canvas;", "canvas", "", "onDraw", "(Landroid/graphics/Canvas;)V", "w", "h", "oldw", "oldh", "onSizeChanged", "(IIII)V", "start", "()V", "stop", "", "isRunning", "()Z", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class AnimatedGradientTextView extends TextView implements Animatable {

    @Deprecated
    public static final long ANIMATION_DURATION = 2500;

    @Deprecated
    public static final long FPS = 16;
    private final Paint a;
    private LinearGradient b;
    private float c;
    private final Matrix d;
    private long e;
    private boolean f;
    private final AnimatedGradientTextView$updater$1 g;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AnimatedGradientTextView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        CharSequence text = getText();
        if (text == null || text.length() == 0 || this.b == null) {
            return;
        }
        this.d.reset();
        this.d.setTranslate(this.c * getWidth(), 0.0f);
        LinearGradient linearGradient = this.b;
        if (linearGradient != null) {
            linearGradient.setLocalMatrix(this.d);
        }
        canvas.drawText(getText().toString(), 0.0f, getBaseline(), this.a);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float width = getWidth();
        int currentTextColor = getCurrentTextColor();
        int argb = Color.argb(64, Color.red(currentTextColor), Color.green(currentTextColor), Color.blue(currentTextColor));
        int currentTextColor2 = getCurrentTextColor();
        int argb2 = Color.argb(255, Color.red(currentTextColor2), Color.green(currentTextColor2), Color.blue(currentTextColor2));
        int currentTextColor3 = getCurrentTextColor();
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, width, 0.0f, new int[]{argb, argb2, Color.argb(64, Color.red(currentTextColor3), Color.green(currentTextColor3), Color.blue(currentTextColor3))}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.REPEAT);
        this.b = linearGradient;
        this.a.setShader(linearGradient);
        this.a.setTextSize(getTextSize());
        this.a.setTypeface(getTypeface());
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.e = AnimationUtils.currentAnimationTimeMillis();
        this.g.run();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.f) {
            removeCallbacks(this.g);
            this.f = false;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AnimatedGradientTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ AnimatedGradientTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimatedGradientTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = new Paint(1);
        this.d = new Matrix();
        setWillNotDraw(false);
        this.g = new AnimatedGradientTextView$updater$1(this);
    }
}
