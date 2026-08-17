package ch.datatrans.payment.bottomsheet;

import android.animation.LayoutTransition;
import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewKt;
import ch.datatrans.payment.K6;
import ch.datatrans.payment.R;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\fB\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u000b\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lch/datatrans/payment/bottomsheet/LoadingView;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", Session.JsonKeys.ATTRS, "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "getAnimatorDurationScale", "()F", "animatorDurationScale", "ch/datatrans/payment/K6", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class LoadingView extends FrameLayout {
    public final ImageView a;
    public final ImageView b;
    public final long c;
    public volatile boolean d;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LoadingView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final Unit b(final LoadingView loadingView) {
        loadingView.a(K6.c);
        loadingView.a(loadingView.c, new Function0() { // from class: ch.datatrans.payment.bottomsheet.LoadingView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoadingView.c(LoadingView.this);
            }
        });
        return Unit.INSTANCE;
    }

    public static final Unit c(final LoadingView loadingView) {
        loadingView.a(K6.d);
        loadingView.a(loadingView.c, new Function0() { // from class: ch.datatrans.payment.bottomsheet.LoadingView$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoadingView.d(LoadingView.this);
            }
        });
        return Unit.INSTANCE;
    }

    public static final Unit d(LoadingView loadingView) {
        loadingView.a();
        return Unit.INSTANCE;
    }

    private final float getAnimatorDurationScale() {
        return Settings.Global.getFloat(getContext().getContentResolver(), "animator_duration_scale", 1.0f);
    }

    public final void a() {
        this.d = true;
        a(K6.a);
        a(this.c, new Function0() { // from class: ch.datatrans.payment.bottomsheet.LoadingView$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoadingView.a(LoadingView.this);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.d = false;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View changedView, int i) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, i);
        if (i == 0) {
            a();
        } else {
            this.d = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        LayoutInflater.from(context).inflate(R.layout.dtpl_loading_view, this);
        View findViewById = findViewById(R.id.image1);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.a = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.image2);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.b = (ImageView) findViewById2;
        this.c = (long) (400 * getAnimatorDurationScale());
        LayoutTransition layoutTransition = ((ViewGroup) findViewById(R.id.loadingBox)).getLayoutTransition();
        layoutTransition.setDuration((long) (400 * 0.95d));
        layoutTransition.enableTransitionType(4);
    }

    public static final Unit a(final LoadingView loadingView) {
        loadingView.a(K6.b);
        loadingView.a(loadingView.c, new Function0() { // from class: ch.datatrans.payment.bottomsheet.LoadingView$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoadingView.b(LoadingView.this);
            }
        });
        return Unit.INSTANCE;
    }

    public final void a(K6 k6) {
        int ordinal = k6.ordinal();
        if (ordinal == 0) {
            a(this.a, 8388659);
            a(this.b, 8388693);
            return;
        }
        if (ordinal == 1) {
            a(this.a, 8388661);
            a(this.b, 8388691);
        } else if (ordinal == 2) {
            a(this.a, 8388693);
            a(this.b, 8388659);
        } else {
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            a(this.a, 8388691);
            a(this.b, 8388661);
        }
    }

    public /* synthetic */ LoadingView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public static void a(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        ((FrameLayout.LayoutParams) layoutParams).gravity = i;
        view.requestLayout();
    }

    public final void a(long j, final Function0 function0) {
        ViewKt.postOnAnimationDelayed(this, j, new Function0() { // from class: ch.datatrans.payment.bottomsheet.LoadingView$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoadingView.a(LoadingView.this, function0);
            }
        });
    }

    public static final Unit a(LoadingView loadingView, Function0 function0) {
        if (loadingView.d) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }
}
