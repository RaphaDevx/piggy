package ch.datatrans.payment;

import android.content.Context;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TransactionException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/L8;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class L8 extends A4 {
    public final Lazy c;

    public L8() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return L8.a(L8.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new A8(new C0259z8(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(S8.class), new B8(lazy), new C8(lazy), function0);
    }

    public static final ViewModelProvider.Factory a(L8 l8) {
        Nb nb = B4.b;
        if (B4.a != C4.c) {
            nb = null;
        }
        if (nb == null) {
            ((Jc) l8.b.getValue()).getClass();
            nb = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        return new T8(nb);
    }

    public static final Unit c(L8 l8, Unit unit) {
        Xa xa = ((Pa) l8.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    public final void b() {
        ((S8) this.c.getValue()).h.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.a(L8.this, (String) obj);
            }
        }));
        ((S8) this.c.getValue()).i.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.a(L8.this, (Unit) obj);
            }
        }));
        ((S8) this.c.getValue()).a.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.b(L8.this, (String) obj);
            }
        }));
        ((S8) this.c.getValue()).b.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.b(L8.this, (Unit) obj);
            }
        }));
        ((S8) this.c.getValue()).c.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.c(L8.this, (Unit) obj);
            }
        }));
        ((S8) this.c.getValue()).e.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.a(L8.this, (TransactionException) obj);
            }
        }));
        ((S8) this.c.getValue()).f.observe(this, new C0256y8(new Function1() { // from class: ch.datatrans.payment.L8$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return L8.a(L8.this, (t4) obj);
            }
        }));
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((Pa) this.a.getValue()).b.postValue(Boolean.TRUE);
        b();
        if (bundle == null) {
            Nb nb = B4.b;
            if ((nb != null ? nb.n : null) == null) {
                S8 s8 = (S8) this.c.getValue();
                if (s8.e().l == null) {
                    BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(s8), null, null, new R8(s8, null), 3, null);
                } else {
                    s8.i.postValue(Unit.INSTANCE);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    public static final Unit a(L8 l8, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) l8.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) l8.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(L8 l8, TransactionException transactionException) {
        Jc jc = (Jc) l8.b.getValue();
        Nb e = ((S8) l8.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final Unit b(L8 l8, String str) {
        Jc jc = (Jc) l8.b.getValue();
        Nb value = ((S8) l8.c.getValue()).e();
        jc.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        ((Jc) l8.b.getValue()).a((S8) l8.c.getValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(L8 l8, String str) {
        Context requireContext = l8.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        U8.a(requireContext, str);
        return Unit.INSTANCE;
    }

    public static final Unit a(L8 l8, Unit unit) {
        Context context = l8.requireContext();
        Intrinsics.checkNotNullExpressionValue(context, "requireContext(...)");
        Intrinsics.checkNotNullParameter(context, "context");
        String pairingId = U8.a(context, null);
        if (pairingId != null && pairingId.length() != 0) {
            S8 s8 = (S8) l8.c.getValue();
            s8.getClass();
            Intrinsics.checkNotNullParameter(pairingId, "pairingId");
            s8.e().o = pairingId;
        }
        ((S8) l8.c.getValue()).b.postValue(null);
        return Unit.INSTANCE;
    }

    public static final Unit b(L8 l8, Unit unit) {
        ((Jc) l8.b.getValue()).a(((S8) l8.c.getValue()).e());
        return Unit.INSTANCE;
    }
}
