package ch.datatrans.payment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/p9;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class p9 extends Fragment {
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;
    public C0254x9 d;
    public RecyclerView e;
    public final Lazy f;
    public final int g;

    public p9() {
        Function0 a = Gd.a(new Ad(this), Jc.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.p9$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return p9.b(p9.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new C0168k9(new C0164j9(this)));
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(E9.class), new C0174l9(lazy), new C0180m9(lazy), function0);
        Function0 a2 = Gd.a(new Ad(this), Pa.class);
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
        this.f = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.p9$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(p9.a(p9.this));
            }
        });
        this.g = R.string.datatrans_sdk_new_method_selection_title;
    }

    public static final Unit a(p9 p9Var, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Jc jc = (Jc) p9Var.a.getValue();
        Nb value = ((E9) p9Var.b.getValue()).a;
        jc.getClass();
        Intrinsics.checkNotNullParameter(value, "model");
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        if (value.l != null) {
            jc.a();
        } else {
            jc.b();
        }
        return Unit.INSTANCE;
    }

    /* renamed from: b */
    public boolean getI() {
        return !((E9) this.b.getValue()).a.b();
    }

    /* renamed from: c, reason: from getter */
    public int getH() {
        return this.g;
    }

    public final void d() {
        ((E9) this.b.getValue()).b.observe(this, new C0160i9(new Function1() { // from class: ch.datatrans.payment.p9$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return p9.a(p9.this, (Unit) obj);
            }
        }));
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        d();
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_payment_method_selection_list, viewGroup, false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        RecyclerView recyclerView = (RecyclerView) inflate;
        this.e = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        Drawable drawable = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.dtpl_divider);
        Intrinsics.checkNotNull(drawable);
        recyclerView.addItemDecoration(new F7(drawable));
        C0254x9 a = a();
        this.d = a;
        recyclerView.setAdapter(a);
        ((Jc) this.a.getValue()).getClass();
        GooglePayConfig googlePayConfig = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().h.i;
        if (googlePayConfig != null && googlePayConfig.getShowLargeButton()) {
            RecyclerView recyclerView2 = this.e;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            recyclerView2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0148g9(this));
        }
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        String str;
        super.onResume();
        Pa pa = (Pa) this.c.getValue();
        if (getI()) {
            pa.a.postValue(Boolean.FALSE);
            W8 w8 = ((E9) this.b.getValue()).a.k;
            if (w8 == null || (str = w8.g) == null) {
                str = "";
            }
            Pa.a(pa, str, null, 4);
        } else {
            pa.a.postValue(Boolean.valueOf(((E9) this.b.getValue()).a.d == null));
            String string = getString(getH());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Pa.a(pa, string, null, 6);
        }
        G9.a.set(false);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        requireActivity.addMenuProvider(new h9(this), getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    public static final ViewModelProvider.Factory b(p9 p9Var) {
        ((Jc) p9Var.a.getValue()).getClass();
        return new F9(TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a());
    }

    public C0254x9 a() {
        return new C0254x9(((E9) this.b.getValue()).a(((E9) this.b.getValue()).a.j, false));
    }

    public static final boolean a(p9 p9Var) {
        RecyclerView recyclerView = p9Var.e;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        RecyclerView recyclerView3 = p9Var.e;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            recyclerView2 = recyclerView3;
        }
        RecyclerView.Adapter adapter = recyclerView2.getAdapter();
        return adapter != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() < adapter.getItemCount() - 1;
    }
}
