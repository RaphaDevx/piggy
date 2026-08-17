package ch.datatrans.payment;

import android.content.Context;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.twint.payment.sdk.Twint;
import ch.twint.payment.sdk.TwintPayResult;
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
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/cd;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: ch.datatrans.payment.cd, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0126cd extends A4 {
    public final Lazy c;
    public Twint d;

    public C0126cd() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0126cd.a(C0126cd.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Rc(new Qc(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0176ld.class), new Sc(lazy), new Tc(lazy), function0);
    }

    public static final ViewModelProvider.Factory a(C0126cd c0126cd) {
        Nb nb = B4.b;
        if (B4.a != C4.e) {
            nb = null;
        }
        if (nb == null) {
            ((Jc) c0126cd.b.getValue()).getClass();
            nb = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        return new C0181md(nb);
    }

    public static final Unit b(C0126cd c0126cd, Unit unit) {
        Xa xa = ((Jc) c0126cd.b.getValue()).e;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    public static final Unit c(C0126cd c0126cd, Unit unit) {
        Xa xa = ((Pa) c0126cd.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        this.d = new Twint(this, (Function1<? super TwintPayResult, Unit>) new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.a(C0126cd.this, (TwintPayResult) obj);
            }
        });
        if (bundle == null) {
            Nb nb = B4.b;
            if ((nb != null ? nb.n : null) == null) {
                ((Pa) this.a.getValue()).b.postValue(Boolean.TRUE);
                C0176ld c0176ld = (C0176ld) this.c.getValue();
                if (c0176ld.e().d != null) {
                    C0149ga c0149ga = c0176ld.i;
                    PaymentMethodType paymentMethodType = PaymentMethodType.TWINT;
                    C0170kd request = new C0170kd(c0176ld, null);
                    c0149ga.getClass();
                    Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
                    Intrinsics.checkNotNullParameter(request, "request");
                    BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType, null), 3, null);
                    return;
                }
                C0149ga c0149ga2 = c0176ld.i;
                W8 w8 = c0176ld.e().k;
                Intrinsics.checkNotNull(w8);
                Q5 q5 = c0176ld.e().h;
                PaymentMethodType paymentMethodType2 = PaymentMethodType.TWINT;
                Jb request2 = new Jb(new Kb(c0149ga2, c0176ld, w8, q5, paymentMethodType2), null);
                c0149ga2.getClass();
                Intrinsics.checkNotNullParameter(paymentMethodType2, "paymentMethodType");
                Intrinsics.checkNotNullParameter(request2, "request");
                BuildersKt__Builders_commonKt.launch$default(c0149ga2.a, null, null, new fa(request2, c0149ga2, false, paymentMethodType2, null), 3, null);
                return;
            }
        }
        Nb nb2 = B4.b;
        if ((nb2 != null ? nb2.n : null) != null) {
            ((Jc) this.b.getValue()).a(((C0176ld) this.c.getValue()).e());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    public final void b() {
        ((C0176ld) this.c.getValue()).a.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.a(C0126cd.this, (String) obj);
            }
        }));
        ((C0176ld) this.c.getValue()).h.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.b(C0126cd.this, (String) obj);
            }
        }));
        ((C0176ld) this.c.getValue()).b.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.a(C0126cd.this, (Unit) obj);
            }
        }));
        ((C0176ld) this.c.getValue()).c.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.b(C0126cd.this, (Unit) obj);
            }
        }));
        ((C0176ld) this.c.getValue()).e.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.a(C0126cd.this, (TransactionException) obj);
            }
        }));
        ((C0176ld) this.c.getValue()).f.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.a(C0126cd.this, (t4) obj);
            }
        }));
        ((C0176ld) this.c.getValue()).d.observe(this, new Pc(new Function1() { // from class: ch.datatrans.payment.cd$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0126cd.c(C0126cd.this, (Unit) obj);
            }
        }));
    }

    public static final Unit a(C0126cd c0126cd, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) c0126cd.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) c0126cd.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0126cd c0126cd, Unit unit) {
        ((Jc) c0126cd.b.getValue()).a(((C0176ld) c0126cd.c.getValue()).e());
        return Unit.INSTANCE;
    }

    public static final Unit a(C0126cd c0126cd, TransactionException transactionException) {
        Jc jc = (Jc) c0126cd.b.getValue();
        Nb e = ((C0176ld) c0126cd.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final Unit b(C0126cd c0126cd, String str) {
        Twint twint = c0126cd.d;
        if (twint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("twint");
            twint = null;
        }
        Intrinsics.checkNotNull(str);
        twint.registerForUOF(str);
        return Unit.INSTANCE;
    }

    public static final Unit a(C0126cd c0126cd, TwintPayResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        c0126cd.getClass();
        int i = Nc.a[result.ordinal()];
        if (i == 1) {
            C0176ld c0176ld = (C0176ld) c0126cd.c.getValue();
            if (c0176ld.e().d == null) {
                c0176ld.b.postValue(Unit.INSTANCE);
            } else {
                C0149ga c0149ga = c0176ld.i;
                PaymentMethodType paymentMethodType = PaymentMethodType.TWINT;
                C0166jd request = new C0166jd(c0176ld, null);
                c0149ga.getClass();
                Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
                Intrinsics.checkNotNullParameter(request, "request");
                BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, true, paymentMethodType, null), 3, null);
            }
        } else if (i == 2) {
            C0176ld c0176ld2 = (C0176ld) c0126cd.c.getValue();
            c0176ld2.getClass();
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(c0176ld2), Dispatchers.getIO(), null, new C0155hd(c0176ld2, null), 2, null);
        } else if (i != 3) {
            C0176ld c0176ld3 = (C0176ld) c0126cd.c.getValue();
            int ordinal = result.ordinal();
            c0176ld3.getClass();
            PaymentMethodType paymentMethodType2 = PaymentMethodType.TWINT;
            c0176ld3.a(new TechnicalException("Invalid TWINT error. Error code: " + ordinal, null, paymentMethodType2, c0176ld3.e().n), paymentMethodType2);
        } else {
            C0176ld c0176ld4 = (C0176ld) c0126cd.c.getValue();
            Context requireContext = c0126cd.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            c0176ld4.a(requireContext);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0126cd c0126cd, String str) {
        Twint twint = c0126cd.d;
        if (twint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("twint");
            twint = null;
        }
        Intrinsics.checkNotNull(str);
        twint.payWithCode(str);
        return Unit.INSTANCE;
    }
}
