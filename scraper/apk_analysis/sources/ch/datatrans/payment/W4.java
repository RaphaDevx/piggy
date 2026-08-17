package ch.datatrans.payment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TransactionException;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/W4;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class W4 extends A4 {
    public final Lazy c;

    public W4() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return W4.a(W4.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new L4(new K4(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(i5.class), new M4(lazy), new N4(lazy), function0);
    }

    public static final ViewModelProvider.Factory a(W4 w4) {
        Nb nb = B4.b;
        if (B4.a != C4.f) {
            nb = null;
        }
        if (nb == null) {
            ((Jc) w4.b.getValue()).getClass();
            nb = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        return new C0163j5(nb);
    }

    public static final Unit b(W4 w4, Unit unit) {
        Xa xa = ((Pa) w4.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (i == 3333) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new I4(this, i2, intent, null), 3, null);
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        ((Jc) this.b.getValue()).k.postValue(Unit.INSTANCE);
        if (bundle == null) {
            Nb nb = B4.b;
            if ((nb != null ? nb.n : null) == null) {
                ((i5) this.c.getValue()).d();
            }
        }
    }

    public static final Unit a(W4 w4, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) w4.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) w4.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(W4 w4, Boolean bool) {
        Pa pa = (Pa) w4.a.getValue();
        Intrinsics.checkNotNull(bool);
        bool.booleanValue();
        pa.b.postValue(bool);
        return Unit.INSTANCE;
    }

    public static final Unit a(W4 w4, Ae webProcess) {
        ((Pa) w4.a.getValue()).b.postValue(Boolean.TRUE);
        Pa pa = (Pa) w4.a.getValue();
        Intrinsics.checkNotNull(webProcess);
        pa.getClass();
        Intrinsics.checkNotNullParameter(webProcess, "webProcess");
        pa.h = webProcess;
        Xa xa = pa.g;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    public static final Unit a(W4 w4, Unit unit) {
        ((Jc) w4.b.getValue()).a(((i5) w4.c.getValue()).e());
        return Unit.INSTANCE;
    }

    public static final Unit a(W4 w4, TransactionException transactionException) {
        Jc jc = (Jc) w4.b.getValue();
        Nb e = ((i5) w4.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public final void a() {
        ((i5) this.c.getValue()).a.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.a(W4.this, (PaymentDataRequest) obj);
            }
        }));
        ((i5) this.c.getValue()).b.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.a(W4.this, (Unit) obj);
            }
        }));
        ((i5) this.c.getValue()).c.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.b(W4.this, (Unit) obj);
            }
        }));
        ((i5) this.c.getValue()).e.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.a(W4.this, (TransactionException) obj);
            }
        }));
        ((i5) this.c.getValue()).f.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.a(W4.this, (t4) obj);
            }
        }));
        ((i5) this.c.getValue()).g.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.a(W4.this, (Boolean) obj);
            }
        }));
        ((i5) this.c.getValue()).h.observe(this, new J4(new Function1() { // from class: ch.datatrans.payment.W4$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return W4.a(W4.this, (Ae) obj);
            }
        }));
    }

    public static final Unit a(W4 w4, PaymentDataRequest paymentDataRequest) {
        Q5 q5 = ((i5) w4.c.getValue()).e().h;
        Context context = w4.requireContext();
        Intrinsics.checkNotNullExpressionValue(context, "requireContext(...)");
        boolean z = q5.v;
        Intrinsics.checkNotNullParameter(context, "context");
        PaymentsClient paymentsClient = Wallet.getPaymentsClient(context, new Wallet.WalletOptions.Builder().setEnvironment(z ? 3 : 1).build());
        Intrinsics.checkNotNullExpressionValue(paymentsClient, "getPaymentsClient(...)");
        AutoResolveHelper.resolveTask(paymentsClient.loadPaymentData(paymentDataRequest), w4.requireActivity(), 3333);
        return Unit.INSTANCE;
    }
}
