package ch.datatrans.payment;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/Ba;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class Ba extends Fragment {
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;

    public Ba() {
        Function0 a = Gd.a(new Ad(this), Pa.class);
        this.a = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Pa.class), new xd(this), new yd(this), a == null ? new C0260zd(this) : a);
        Function0 a2 = Gd.a(new Ad(this), Jc.class);
        this.b = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Jc.class), new xd(this), new yd(this), a2 == null ? new C0260zd(this) : a2);
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.Ba$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Ba.a(Ba.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new ra(new C0203qa(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Ka.class), new C0216sa(lazy), new C0223ta(lazy), function0);
    }

    public static final Unit a(Ba ba, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) ba.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) ba.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit b(Ba ba, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Xa xa = ((Pa) ba.a.getValue()).f;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        ((Jc) this.b.getValue()).k.postValue(Unit.INSTANCE);
    }

    public static final Unit a(Ba ba, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ((Jc) ba.b.getValue()).a(((Ka) ba.c.getValue()).a);
        return Unit.INSTANCE;
    }

    public static final Unit a(Ba ba, TransactionException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        ((Jc) ba.b.getValue()).a(((Ka) ba.c.getValue()).a, exception);
        return Unit.INSTANCE;
    }

    public final void a() {
        ((Ka) this.c.getValue()).b.observe(this, new C0197pa(new Function1() { // from class: ch.datatrans.payment.Ba$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ba.a(Ba.this, (CustomSheetPaymentInfo) obj);
            }
        }));
        ((Ka) this.c.getValue()).c.observe(this, new C0197pa(new Function1() { // from class: ch.datatrans.payment.Ba$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ba.a(Ba.this, (Unit) obj);
            }
        }));
        ((Ka) this.c.getValue()).d.observe(this, new C0197pa(new Function1() { // from class: ch.datatrans.payment.Ba$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ba.b(Ba.this, (Unit) obj);
            }
        }));
        ((Ka) this.c.getValue()).e.observe(this, new C0197pa(new Function1() { // from class: ch.datatrans.payment.Ba$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ba.a(Ba.this, (TransactionException) obj);
            }
        }));
        ((Ka) this.c.getValue()).f.observe(this, new C0197pa(new Function1() { // from class: ch.datatrans.payment.Ba$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Ba.a(Ba.this, (t4) obj);
            }
        }));
    }

    public static final Unit a(Ba ba, CustomSheetPaymentInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        Q5 q5 = ((Ka) ba.c.getValue()).a.h;
        SamsungPayConfig samsungPayConfig = q5.o;
        Intrinsics.checkNotNull(samsungPayConfig);
        PaymentManager paymentManager = new PaymentManager(ba.requireContext(), samsungPayConfig.createPartnerInfo$lib_release(q5.v));
        paymentManager.startInAppPayWithCustomSheet(info, new C0233ua(ba, paymentManager));
        return Unit.INSTANCE;
    }

    public static final ViewModelProvider.Factory a(Ba ba) {
        ((Jc) ba.b.getValue()).getClass();
        return new La(TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a());
    }
}
