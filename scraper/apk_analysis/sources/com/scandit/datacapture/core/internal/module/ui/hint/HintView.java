package com.scandit.datacapture.core.internal.module.ui.hint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scandit.datacapture.core.R;
import com.scandit.datacapture.core.common.geometry.MarginsF;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.internal.module.ui.NativeHintAlignment;
import com.scandit.datacapture.core.internal.module.ui.NativeHintAnchor;
import com.scandit.datacapture.core.internal.module.ui.NativeHintIcon;
import com.scandit.datacapture.core.internal.module.ui.NativeHintStyle;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.ui.hint.GuidanceHint;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2Impl;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.icon.ScanditIconDeserializer;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class HintView extends RelativeLayout {
    private final TextView a;
    private final ImageView b;
    private Function0 c;
    private boolean d;
    private final PointF e;
    private final PointF f;
    private boolean g;
    private final float h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HintView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        View.inflate(context, R.layout.sc_hint_view_v2, this);
        View findViewById = findViewById(R.id.textView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.a = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.iconView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.b = (ImageView) findViewById2;
        setBackgroundResource(R.drawable.sc_hint_view_background);
        setAlpha(0.0f);
        setFocusable(true);
        setScreenReaderFocusable(true);
        this.e = new PointF(0.0f, 0.0f);
        this.f = new PointF(0.0f, 0.0f);
        this.h = PixelExtensionsKt.pxFromDp(200.0f, context);
    }

    public final void a(Function0 function0) {
        this.c = function0;
    }

    public final void b(NativeHintStyle hintStyle, Rect rect, boolean z) {
        Intrinsics.checkNotNullParameter(hintStyle, "hintStyle");
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        a(layoutParams2, hintStyle, rect, z, layoutParams2.leftMargin);
        setLayoutParams(layoutParams2);
    }

    public final void c(NativeHintStyle hintStyle, Rect rect, boolean z) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(hintStyle, "hintStyle");
        int i3 = hintStyle.getFitToText() ? -2 : -1;
        Object parent = getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        int width = ((View) parent).getWidth();
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int min = Math.min(width, Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels));
        Intrinsics.checkNotNull(getParent(), "null cannot be cast to non-null type android.view.View");
        int roundToInt = MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(hintStyle.getHorizontalMargin(), (Context) null, 1, (Object) null) + ((((View) r4).getWidth() - (hintStyle.getMaxWidthFraction() * min)) / 2.0f));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, -2);
        layoutParams.addRule(14);
        a(layoutParams, hintStyle, rect, z, roundToInt);
        setLayoutParams(layoutParams);
        MarginsF padding = hintStyle.getPadding();
        Intrinsics.checkNotNullExpressionValue(padding, "getPadding(...)");
        setPadding(MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(padding.getLeft(), (Context) null, 1, (Object) null)), MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(padding.getTop(), (Context) null, 1, (Object) null)), MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(padding.getRight(), (Context) null, 1, (Object) null)), MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(padding.getBottom(), (Context) null, 1, (Object) null)));
        TextView textView = this.a;
        textView.setTextSize(2, hintStyle.getTextSize());
        NativeColor textColor = hintStyle.getTextColor();
        Intrinsics.checkNotNullExpressionValue(textColor, "getTextColor(...)");
        textView.setTextColor(Color.argb((int) ((textColor.getA() * 255.0f) + 0.5f), (int) ((textColor.getR() * 255.0f) + 0.5f), (int) ((textColor.getG() * 255.0f) + 0.5f), (int) ((textColor.getB() * 255.0f) + 0.5f)));
        NativeHintAlignment textAlignment = hintStyle.getTextAlignment();
        Intrinsics.checkNotNull(textAlignment);
        int i4 = A.a[textAlignment.ordinal()];
        if (i4 == 1) {
            i = 8388627;
        } else if (i4 == 2) {
            i = 17;
        } else {
            if (i4 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = 8388629;
        }
        textView.setGravity(i);
        textView.setMaxLines(hintStyle.getMaxLines());
        textView.setLineSpacing(PixelExtensionsKt.pxFromSp$default((hintStyle.getLineHeight() >= 0.0f ? hintStyle.getLineHeight() : hintStyle.getTextSize()) - hintStyle.getTextSize(), null, 1, null), 1.0f);
        int i5 = 0;
        textView.setTypeface(Typeface.create(null, hintStyle.getTextWeight(), false));
        float cornerRadius = hintStyle.getCornerRadius();
        NativeColor backgroundColor = hintStyle.getBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(backgroundColor, "getBackgroundColor(...)");
        Drawable background = getBackground();
        Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        GradientDrawable gradientDrawable = (GradientDrawable) background;
        gradientDrawable.setCornerRadius(PixelExtensionsKt.pxFromDp$default(cornerRadius, (Context) null, 1, (Object) null));
        gradientDrawable.setTint(Color.argb((int) ((backgroundColor.getA() * 255.0f) + 0.5f), (int) ((backgroundColor.getR() * 255.0f) + 0.5f), (int) ((backgroundColor.getG() * 255.0f) + 0.5f), (int) ((backgroundColor.getB() * 255.0f) + 0.5f)));
        String scanditIcon = hintStyle.getScanditIcon();
        if (scanditIcon != null) {
            ScanditIcon fromJson = ScanditIconDeserializer.fromJson(scanditIcon);
            this.b.setVisibility(0);
            this.b.setImageTintList(null);
            ImageView imageView = this.b;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            imageView.setImageDrawable(fromJson.getDrawable$scandit_capture_core(context));
        } else {
            NativeHintIcon hintIcon = hintStyle.getHintIcon();
            int i6 = hintIcon != null ? A.b[hintIcon.ordinal()] : -1;
            if (i6 == 1) {
                this.b.setVisibility(8);
            } else if (i6 != 2) {
                this.b.setVisibility(0);
                NativeHintIcon hintIcon2 = hintStyle.getHintIcon();
                Intrinsics.checkNotNullExpressionValue(hintIcon2, "getHintIcon(...)");
                int i7 = C.a[hintIcon2.ordinal()];
                if (i7 != 1) {
                    if (i7 == 2) {
                        i2 = R.drawable.sc_ic_hint_check;
                    } else if (i7 == 3) {
                        i2 = R.drawable.sc_ic_hint_exclamation_mark;
                    } else if (i7 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    this.b.setImageResource(i2);
                    ImageView imageView2 = this.b;
                    NativeColor iconColor = hintStyle.getIconColor();
                    Intrinsics.checkNotNullExpressionValue(iconColor, "getIconColor(...)");
                    imageView2.setImageTintList(ColorStateList.valueOf(Color.argb((int) ((iconColor.getA() * 255.0f) + 0.5f), (int) ((iconColor.getR() * 255.0f) + 0.5f), (int) ((iconColor.getG() * 255.0f) + 0.5f), (int) ((iconColor.getB() * 255.0f) + 0.5f))));
                }
                i2 = 0;
                this.b.setImageResource(i2);
                ImageView imageView22 = this.b;
                NativeColor iconColor2 = hintStyle.getIconColor();
                Intrinsics.checkNotNullExpressionValue(iconColor2, "getIconColor(...)");
                imageView22.setImageTintList(ColorStateList.valueOf(Color.argb((int) ((iconColor2.getA() * 255.0f) + 0.5f), (int) ((iconColor2.getR() * 255.0f) + 0.5f), (int) ((iconColor2.getG() * 255.0f) + 0.5f), (int) ((iconColor2.getB() * 255.0f) + 0.5f))));
            } else if (Intrinsics.areEqual(hintStyle.getIconResource(), "user-swipe")) {
                ImageView iconView = this.b;
                Intrinsics.checkNotNullParameter(iconView, "iconView");
                Context context2 = iconView.getContext();
                Drawable drawable = context2.getResources().getDrawable(R.drawable.sc_ic_user_swipe, context2.getTheme());
                Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.AnimatedVectorDrawable");
                AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                iconView.setImageDrawable(animatedVectorDrawable);
                iconView.setImageTintList(null);
                animatedVectorDrawable.registerAnimationCallback(new B(iconView, animatedVectorDrawable));
                animatedVectorDrawable.start();
            } else {
                this.b.setVisibility(8);
            }
        }
        NativeHintIcon hintIcon3 = hintStyle.getHintIcon();
        Intrinsics.checkNotNullExpressionValue(hintIcon3, "getHintIcon(...)");
        if (A.b[hintIcon3.ordinal()] != 1) {
            this.a.onPreDraw();
            i5 = RangesKt.coerceAtLeast(this.a.getLayout().getTopPadding() + ((PixelExtensionsKt.pxFromDp$default(24, (Context) null, 1, (Object) null) - this.a.getLayout().getLineBaseline(0)) / 2), 0);
        }
        ViewGroup.LayoutParams layoutParams2 = this.a.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = i5;
            marginLayoutParams.bottomMargin = i5;
            this.a.setLayoutParams(marginLayoutParams);
        }
        this.d = hintStyle.getCanBeDismissed();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        if (r0 != 3) goto L22;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            int r0 = r7.getAction()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L87
            r3 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            if (r0 == r2) goto L41
            r5 = 2
            if (r0 == r5) goto L19
            r5 = 3
            if (r0 == r5) goto L41
            goto L82
        L19:
            boolean r0 = r6.g
            if (r0 == 0) goto L82
            android.graphics.PointF r0 = r6.f
            float r1 = r7.getRawX()
            r0.x = r1
            android.graphics.PointF r0 = r6.f
            float r7 = r7.getRawY()
            r0.y = r7
            android.graphics.PointF r7 = r6.f
            android.graphics.PointF r0 = r6.e
            float r7 = com.scandit.datacapture.core.internal.sdk.extensions.PointFExtensionKt.distance(r7, r0)
            float r0 = r6.h
            float r7 = r7 / r0
            float r7 = kotlin.ranges.RangesKt.coerceIn(r7, r3, r4)
            float r4 = r4 - r7
            r6.setAlpha(r4)
            return r2
        L41:
            boolean r0 = r6.g
            if (r0 == 0) goto L82
            r6.g = r1
            android.graphics.PointF r0 = r6.f
            float r1 = r7.getRawX()
            r0.x = r1
            android.graphics.PointF r0 = r6.f
            float r7 = r7.getRawY()
            r0.y = r7
            android.graphics.PointF r7 = r6.f
            android.graphics.PointF r0 = r6.e
            float r7 = com.scandit.datacapture.core.internal.sdk.extensions.PointFExtensionKt.distance(r7, r0)
            float r0 = r6.h
            float r7 = r7 / r0
            float r7 = kotlin.ranges.RangesKt.coerceIn(r7, r3, r4)
            r0 = 1056964608(0x3f000000, float:0.5)
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 <= 0) goto L70
            r6.a()
            goto L81
        L70:
            android.view.ViewPropertyAnimator r6 = r6.animate()
            android.view.ViewPropertyAnimator r6 = r6.alpha(r4)
            r0 = 200(0xc8, double:9.9E-322)
            android.view.ViewPropertyAnimator r6 = r6.setDuration(r0)
            r6.start()
        L81:
            return r2
        L82:
            boolean r6 = super.onTouchEvent(r7)
            return r6
        L87:
            boolean r0 = r6.d
            if (r0 != 0) goto L8c
            return r1
        L8c:
            android.graphics.PointF r0 = r6.e
            float r1 = r7.getRawX()
            r0.x = r1
            android.graphics.PointF r0 = r6.e
            float r7 = r7.getRawY()
            r0.y = r7
            r6.g = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.ui.hint.HintView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private final void a() {
        animate().alpha(0.0f).setDuration(200L).withEndAction(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.HintView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HintView.a(HintView.this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(HintView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 function0 = this$0.c;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void a(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setText(text);
    }

    public final void a(GuidanceHint guidance, Rect rect) {
        Intrinsics.checkNotNullParameter(guidance, "guidance");
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        final RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (layoutParams2.getRule(10) != -1) {
            return;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(layoutParams2.topMargin, a(guidance.getHintStyle(), rect, true));
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.HintView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                HintView.a(layoutParams2, this, valueAnimator);
            }
        });
        ofInt.setDuration(100L);
        ofInt.setInterpolator(new DecelerateInterpolator());
        ofInt.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(RelativeLayout.LayoutParams params, HintView this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(params, "$params");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int i = params.leftMargin;
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        params.setMargins(i, ((Integer) animatedValue).intValue(), params.rightMargin, params.bottomMargin);
        this$0.setLayoutParams(params);
    }

    private final void a(RelativeLayout.LayoutParams layoutParams, NativeHintStyle nativeHintStyle, Rect rect, boolean z, int i) {
        layoutParams.removeRule(10);
        layoutParams.removeRule(12);
        int a = a(nativeHintStyle, rect, z);
        int roundToInt = MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(nativeHintStyle.getHintAnchorOffset(), (Context) null, 1, (Object) null));
        int i2 = 0;
        if (nativeHintStyle.getHintAnchor() != NativeHintAnchor.TOP && rect != null) {
            if (nativeHintStyle.getHintAnchor() == NativeHintAnchor.ABOVE_VIEW_FINDER) {
                Object parent = getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
                i2 = (((View) parent).getHeight() - MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(rect.getOrigin().getY(), (Context) null, 1, (Object) null))) + roundToInt;
                layoutParams.addRule(12);
            } else if (nativeHintStyle.getHintAnchor() == NativeHintAnchor.BELOW_VIEW_FINDER) {
                layoutParams.addRule(10);
            } else {
                throw new UnsupportedOperationException("Unsupported anchor " + nativeHintStyle.getHintAnchor());
            }
        } else {
            layoutParams.addRule(10);
        }
        layoutParams.setMargins(i, a, i, i2);
    }

    private final int a(NativeHintStyle nativeHintStyle, Rect rect, boolean z) {
        ViewParent parent = getParent();
        HintHolderV2Impl hintHolderV2Impl = parent instanceof HintHolderV2Impl ? (HintHolderV2Impl) parent : null;
        HintView anyToastView$scandit_capture_core = hintHolderV2Impl != null ? hintHolderV2Impl.anyToastView$scandit_capture_core() : null;
        int roundToInt = MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(nativeHintStyle.getHintAnchorOffset(), (Context) null, 1, (Object) null));
        int y = (!z || anyToastView$scandit_capture_core == null) ? 0 : ((int) (anyToastView$scandit_capture_core.getY() + anyToastView$scandit_capture_core.getHeight())) + roundToInt;
        if (nativeHintStyle.getHintAnchor() != NativeHintAnchor.TOP && rect != null) {
            if (nativeHintStyle.getHintAnchor() == NativeHintAnchor.ABOVE_VIEW_FINDER) {
                return 0;
            }
            if (nativeHintStyle.getHintAnchor() == NativeHintAnchor.BELOW_VIEW_FINDER) {
                return Math.max(MathKt.roundToInt(PixelExtensionsKt.pxFromDp$default(rect.getSize().getHeight() + rect.getOrigin().getY(), (Context) null, 1, (Object) null)) + roundToInt, y);
            }
            throw new UnsupportedOperationException("Unsupported anchor " + nativeHintStyle.getHintAnchor());
        }
        return Math.max(roundToInt, y);
    }
}
