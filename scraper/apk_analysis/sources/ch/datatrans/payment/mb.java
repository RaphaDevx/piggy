package ch.datatrans.payment;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/mb;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class mb extends A4 {
    public final Lazy c;
    public final ActivityResultLauncher d;

    public mb() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.mb$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return mb.a(mb.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new C0136eb(new C0129db(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0217sb.class), new fb(lazy), new gb(lazy), function0);
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.mb$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                mb.a(mb.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.d = registerForActivityResult;
    }

    public static final ViewModelProvider.Factory a(mb owner) {
        Nb transactionModel = B4.b;
        if (B4.a != C4.a) {
            transactionModel = null;
        }
        if (transactionModel == null) {
            ((Jc) owner.b.getValue()).getClass();
            transactionModel = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        int i = C0217sb.j;
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return new C0187nb(owner, transactionModel);
    }

    public final void b() {
        ((C0217sb) this.c.getValue()).a.observe(this, new cb(new Function1() { // from class: ch.datatrans.payment.mb$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return mb.a(mb.this, (Intent) obj);
            }
        }));
        ((C0217sb) this.c.getValue()).e.observe(this, new cb(new Function1() { // from class: ch.datatrans.payment.mb$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return mb.a(mb.this, (TransactionException) obj);
            }
        }));
        ((C0217sb) this.c.getValue()).f.observe(this, new cb(new Function1() { // from class: ch.datatrans.payment.mb$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return mb.a(mb.this, (t4) obj);
            }
        }));
        ((C0217sb) this.c.getValue()).d.observe(this, new cb(new Function1() { // from class: ch.datatrans.payment.mb$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return mb.a(mb.this, (Unit) obj);
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
                Kb kb = ((C0217sb) this.c.getValue()).i;
                C0149ga c0149ga = kb.a;
                PaymentMethodType paymentMethodType = kb.e;
                Jb request = new Jb(kb, null);
                c0149ga.getClass();
                Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
                Intrinsics.checkNotNullParameter(request, "request");
                BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType, null), 3, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    public static final Unit a(mb mbVar, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) mbVar.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) mbVar.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(mb mbVar, Unit unit) {
        Xa xa = ((Pa) mbVar.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    public static final Unit a(mb mbVar, TransactionException transactionException) {
        Jc jc = (Jc) mbVar.b.getValue();
        Nb e = ((C0217sb) mbVar.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final void a(mb mbVar, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            ((Jc) mbVar.b.getValue()).a(((C0217sb) mbVar.c.getValue()).e());
        } else {
            ((Pa) mbVar.a.getValue()).f.postValue(Unit.INSTANCE);
        }
    }

    public static final Unit a(mb mbVar, Intent intent) {
        try {
            mbVar.d.launch(intent);
        } catch (Exception e) {
            ((C0217sb) mbVar.c.getValue()).a(e, PaymentMethodType.SWISH);
        }
        return Unit.INSTANCE;
    }
}
