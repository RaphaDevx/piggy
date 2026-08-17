package ch.datatrans.payment;

import android.animation.LayoutTransition;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelLazy;
import ch.datatrans.payment.bottomsheet.ErrorView;
import ch.datatrans.payment.bottomsheet.LoadingView;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes3.dex */
public abstract class C extends AppCompatActivity {
    public static final /* synthetic */ int m = 0;
    public View a;
    public ImageView b;
    public TextView c;
    public ConstraintLayout d;
    public LoadingView e;
    public ErrorView f;
    public Toolbar g;
    public ViewGroup h;
    public ViewGroup i;
    public ViewGroup j;
    public final Lazy k = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.C$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Integer.valueOf(C.a(C.this));
        }
    });
    public final ViewModelLazy l;

    public C() {
        AbstractActivityC0147g4 abstractActivityC0147g4 = (AbstractActivityC0147g4) this;
        this.l = new ViewModelLazy(Reflection.getOrCreateKotlinClass(F.class), new A(abstractActivityC0147g4), new z(abstractActivityC0147g4), new B(abstractActivityC0147g4));
    }

    public static final void a(aa aaVar, DialogInterface dialogInterface, int i) {
        aaVar.c.invoke();
    }

    public static final void b(aa aaVar, DialogInterface dialogInterface, int i) {
        aaVar.d.invoke();
    }

    public final void c(boolean z) {
        LoadingView loadingView = this.e;
        ViewGroup viewGroup = null;
        if (loadingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingView");
            loadingView = null;
        }
        loadingView.setVisibility(z ? 0 : 8);
        int i = z ? 4 : 0;
        Toolbar toolbar = this.g;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar = null;
        }
        toolbar.setVisibility(i);
        ViewGroup viewGroup2 = this.h;
        if (viewGroup2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup2 = null;
        }
        viewGroup2.setVisibility(i);
        if (z) {
            ViewGroup viewGroup3 = this.j;
            if (viewGroup3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorViewContainer");
            } else {
                viewGroup = viewGroup3;
            }
            viewGroup.setVisibility(8);
        }
    }

    @Override // android.app.Activity
    public final void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.dtpl_slide_down);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dtpl_bottom_sheet_activity);
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(0);
        }
        View findViewById = findViewById(R.id.bottomSheet);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.d = (ConstraintLayout) findViewById;
        View findViewById2 = findViewById(R.id.loadingView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.e = (LoadingView) findViewById2;
        View findViewById3 = findViewById(R.id.errorView);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.f = (ErrorView) findViewById3;
        View findViewById4 = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.g = (Toolbar) findViewById4;
        View findViewById5 = findViewById(R.id.container);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.h = (ViewGroup) findViewById5;
        View findViewById6 = findViewById(R.id.footer);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.a = findViewById6;
        if (findViewById6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footer");
            findViewById6 = null;
        }
        View findViewById7 = findViewById6.findViewById(R.id.footer_lock_icon);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.b = (ImageView) findViewById7;
        View view = this.a;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footer");
            view = null;
        }
        View findViewById8 = view.findViewById(R.id.footer_datatrans_label);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.c = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.containerAndToolbarLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.i = (ViewGroup) findViewById9;
        View findViewById10 = findViewById(R.id.errorViewContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.j = (ViewGroup) findViewById10;
        ((F) this.l.getValue()).a.observe(this, new y(new Function1() { // from class: ch.datatrans.payment.C$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C.a(C.this, (Boolean) obj);
            }
        }));
        ConstraintLayout constraintLayout = this.d;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheet");
            constraintLayout = null;
        }
        constraintLayout.getLayoutTransition().enableTransitionType(4);
        ViewGroup viewGroup = this.i;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("containerAndToolbarLayout");
            viewGroup = null;
        }
        viewGroup.getLayoutTransition().enableTransitionType(4);
        Toolbar toolbar = this.g;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar = null;
        }
        toolbar.setLayoutTransition(new LayoutTransition());
        setSupportActionBar(toolbar);
        Intrinsics.checkNotNullParameter("", "title");
        E.a(this, "", false, null);
        if (getResources().getBoolean(R.bool.isTablet)) {
            return;
        }
        b();
    }

    public static final int a(C c) {
        return (int) c.getResources().getDimension(R.dimen.dtpl_bottom_sheet_min_height);
    }

    public final void b() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        ConstraintLayout constraintLayout = this.d;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheet");
            constraintLayout = null;
        }
        ViewCompat.setOnApplyWindowInsetsListener(constraintLayout, new OnApplyWindowInsetsListener() { // from class: ch.datatrans.payment.C$$ExternalSyntheticLambda4
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return C.a(C.this, view, windowInsetsCompat);
            }
        });
    }

    public boolean a() {
        boolean z;
        ViewGroup viewGroup = this.h;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup = null;
        }
        if (viewGroup.getChildCount() != 0) {
            ViewGroup viewGroup2 = this.h;
            if (viewGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
                viewGroup2 = null;
            }
            View childAt = viewGroup2.getChildAt(0);
            boolean z2 = childAt instanceof ViewGroup;
            ViewGroup viewGroup3 = z2 ? (ViewGroup) childAt : null;
            if (viewGroup3 == null || viewGroup3.getChildCount() != 0) {
                ViewGroup viewGroup4 = z2 ? (ViewGroup) childAt : null;
                KeyEvent.Callback childAt2 = viewGroup4 != null ? viewGroup4.getChildAt(0) : null;
                ViewGroup viewGroup5 = childAt2 instanceof ViewGroup ? (ViewGroup) childAt2 : null;
                if ((viewGroup5 != null ? viewGroup5.getChildCount() : 0) != 0) {
                    z = true;
                    return (getSupportFragmentManager().getBackStackEntryCount() == 0 || z) ? false : true;
                }
            }
        }
        z = false;
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
        }
    }

    public static final Unit a(C c, Boolean bool) {
        Intrinsics.checkNotNull(bool);
        boolean booleanValue = bool.booleanValue();
        if (booleanValue) {
            WindowManager.LayoutParams attributes = c.getWindow().getAttributes();
            attributes.dimAmount = 0.6f;
            c.getWindow().addFlags(2);
            c.getWindow().setAttributes(attributes);
        }
        ConstraintLayout constraintLayout = c.d;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheet");
            constraintLayout = null;
        }
        constraintLayout.setVisibility(booleanValue ? 0 : 8);
        return Unit.INSTANCE;
    }

    public static final WindowInsetsCompat a(C c, View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "windowInsetsCompat");
        View view2 = c.a;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footer");
            view2 = null;
        }
        view2.setVisibility(!windowInsetsCompat.isVisible(WindowInsetsCompat.Type.ime()) ? 0 : 8);
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), insets.bottom);
        Insets insets2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        view.setMinimumHeight(view.getHeight() + insets2.bottom <= ((Number) c.k.getValue()).intValue() ? ((Number) c.k.getValue()).intValue() : 0);
        return windowInsetsCompat;
    }

    public final void a(boolean z) {
        int color = getResources().getColor(z ? R.color.dtpl_bottom_line_text_on_light_background : R.color.dtpl_bottom_line_text);
        TextView textView = this.c;
        ConstraintLayout constraintLayout = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footerLabel");
            textView = null;
        }
        textView.setTextColor(color);
        ImageView imageView = this.b;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("footerIcon");
            imageView = null;
        }
        imageView.setImageTintList(ColorStateList.valueOf(color));
        if (z) {
            ConstraintLayout constraintLayout2 = this.d;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomSheet");
            } else {
                constraintLayout = constraintLayout2;
            }
            constraintLayout.setBackgroundColor(-1);
            return;
        }
        ConstraintLayout constraintLayout3 = this.d;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomSheet");
        } else {
            constraintLayout = constraintLayout3;
        }
        constraintLayout.setBackgroundResource(R.drawable.dtpl_bottom_sheet_background);
    }

    public final void a(t4 model, Function0 dismissLoader) {
        Intrinsics.checkNotNullParameter(dismissLoader, "dismissLoader");
        ViewGroup viewGroup = null;
        if (model != null) {
            if (model instanceof aa) {
                if (a()) {
                    a((aa) model);
                    return;
                } else {
                    aa aaVar = (aa) model;
                    a(new C0178m4(aaVar.a, aaVar.b, aaVar.d), dismissLoader);
                    return;
                }
            }
            ErrorView errorView = this.f;
            if (errorView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorView");
                errorView = null;
            }
            errorView.getClass();
            Intrinsics.checkNotNullParameter(model, "model");
            if (model instanceof C0178m4) {
                errorView.a(model.a, model.b, false, ((C0178m4) model).c);
            } else if (model instanceof C0228u) {
                errorView.a(model.a, model.b, true, ((C0228u) model).c);
            } else {
                throw new IllegalArgumentException("model of type " + model.getClass().getSimpleName() + " is not supported by ErrorView");
            }
            ViewGroup viewGroup2 = this.j;
            if (viewGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorViewContainer");
                viewGroup2 = null;
            }
            viewGroup2.setVisibility(0);
            LoadingView loadingView = this.e;
            if (loadingView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingView");
                loadingView = null;
            }
            loadingView.setVisibility(8);
            ViewGroup viewGroup3 = this.i;
            if (viewGroup3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("containerAndToolbarLayout");
                viewGroup3 = null;
            }
            viewGroup3.setVisibility(8);
            dismissLoader.invoke();
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.dimAmount = 0.6f;
            getWindow().addFlags(2);
            getWindow().setAttributes(attributes);
            ConstraintLayout constraintLayout = this.d;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomSheet");
            } else {
                viewGroup = constraintLayout;
            }
            viewGroup.setVisibility(0);
            return;
        }
        ViewGroup viewGroup4 = this.i;
        if (viewGroup4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("containerAndToolbarLayout");
            viewGroup4 = null;
        }
        viewGroup4.setVisibility(0);
        Toolbar toolbar = this.g;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar = null;
        }
        toolbar.setVisibility(0);
        ViewGroup viewGroup5 = this.h;
        if (viewGroup5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup5 = null;
        }
        viewGroup5.setVisibility(0);
        ViewGroup viewGroup6 = this.j;
        if (viewGroup6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorViewContainer");
        } else {
            viewGroup = viewGroup6;
        }
        viewGroup.setVisibility(8);
    }

    public final void a(final aa aaVar) {
        AlertDialog create = new AlertDialog.Builder(this).setTitle(aaVar.a).setMessage(aaVar.b.a(this)).setPositiveButton(R.string.datatrans_sdk_error_alert_retry, new DialogInterface.OnClickListener() { // from class: ch.datatrans.payment.C$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                C.a(aa.this, dialogInterface, i);
            }
        }).setNeutralButton(R.string.datatrans_sdk_error_alert_cancel, new DialogInterface.OnClickListener() { // from class: ch.datatrans.payment.C$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                C.b(aa.this, dialogInterface, i);
            }
        }).create();
        create.setCanceledOnTouchOutside(false);
        create.show();
    }
}
