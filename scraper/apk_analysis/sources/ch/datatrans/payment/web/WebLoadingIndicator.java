package ch.datatrans.payment.web;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.ve;
import ch.datatrans.payment.we;
import io.sentry.Session;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\rB\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lch/datatrans/payment/web/WebLoadingIndicator;", "Landroid/widget/ProgressBar;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "progress", "", "setProgress", "(F)V", "ch/datatrans/payment/ve", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class WebLoadingIndicator extends ProgressBar {
    public float a;
    public ve b;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WebLoadingIndicator(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final Unit a(final WebLoadingIndicator webLoadingIndicator) {
        webLoadingIndicator.a(new AccelerateDecelerateInterpolator(), new Function0() { // from class: ch.datatrans.payment.web.WebLoadingIndicator$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebLoadingIndicator.b(WebLoadingIndicator.this);
            }
        });
        return Unit.INSTANCE;
    }

    public static final Unit c(WebLoadingIndicator webLoadingIndicator) {
        webLoadingIndicator.a(0.7f, 1000L, new LinearInterpolator(), null);
        return Unit.INSTANCE;
    }

    private final void setProgress(float progress) {
        setProgress((int) (progress * 100));
    }

    public final void b() {
        setAlpha(1.0f);
        float progress = getProgress() / 100.0f;
        ve veVar = this.b;
        if (veVar != null) {
            veVar.cancel();
        }
        if (progress == 1.0f) {
            progress = 0.0f;
        }
        this.a = progress;
        setProgress(progress);
        a(0.13f, 1000L, new LinearInterpolator(), null);
    }

    public final void d() {
        ve veVar = this.b;
        if (veVar != null) {
            veVar.cancel();
        }
        a(0.2f, 1500L, new DecelerateInterpolator(), new Function0() { // from class: ch.datatrans.payment.web.WebLoadingIndicator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebLoadingIndicator.c(WebLoadingIndicator.this);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebLoadingIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        setMax(100);
    }

    public /* synthetic */ WebLoadingIndicator(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final void c() {
        ve veVar = this.b;
        if (veVar != null) {
            veVar.cancel();
        }
        a(1.0f, 400L, new AccelerateDecelerateInterpolator(), new Function0() { // from class: ch.datatrans.payment.web.WebLoadingIndicator$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebLoadingIndicator.a(WebLoadingIndicator.this);
            }
        });
    }

    public final void a() {
        ve veVar = this.b;
        if (veVar != null) {
            veVar.cancel();
        }
        setProgress(0);
        setAlpha(0.0f);
    }

    public static final Unit b(WebLoadingIndicator webLoadingIndicator) {
        webLoadingIndicator.setProgress(0);
        return Unit.INSTANCE;
    }

    public final void a(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator, Function0 function0) {
        int i = ve.b;
        float[] values = {getAlpha(), 0.0f};
        Intrinsics.checkNotNullParameter(values, "values");
        ve veVar = new ve();
        veVar.setFloatValues(Arrays.copyOf(values, 2));
        veVar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: ch.datatrans.payment.web.WebLoadingIndicator$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WebLoadingIndicator.a(WebLoadingIndicator.this, valueAnimator);
            }
        });
        veVar.setDuration(300L);
        veVar.setInterpolator(accelerateDecelerateInterpolator);
        veVar.addListener(new we(veVar, function0));
        veVar.start();
        this.b = veVar;
    }

    public static final void b(WebLoadingIndicator webLoadingIndicator, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        Object animatedValue = animation.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        webLoadingIndicator.setProgress(((Float) animatedValue).floatValue());
    }

    public static final void a(WebLoadingIndicator webLoadingIndicator, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        Object animatedValue = animation.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        webLoadingIndicator.setAlpha(((Float) animatedValue).floatValue());
    }

    public final void a(float f, long j, Interpolator interpolator, Function0 function0) {
        float f2 = this.a;
        float f3 = ((1.0f - f2) * f) + f2;
        int i = ve.b;
        float[] values = {getProgress() / 100.0f, f3};
        Intrinsics.checkNotNullParameter(values, "values");
        ve veVar = new ve();
        veVar.setFloatValues(Arrays.copyOf(values, 2));
        veVar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: ch.datatrans.payment.web.WebLoadingIndicator$$ExternalSyntheticLambda3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                WebLoadingIndicator.b(WebLoadingIndicator.this, valueAnimator);
            }
        });
        veVar.setDuration(j);
        veVar.setInterpolator(interpolator);
        veVar.addListener(new we(veVar, function0));
        veVar.start();
        this.b = veVar;
    }
}
