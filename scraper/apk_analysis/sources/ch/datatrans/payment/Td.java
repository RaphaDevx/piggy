package ch.datatrans.payment;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/Td;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class Td extends A4 {
    public final Lazy c;
    public final ActivityResultLauncher d;

    public Td() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.Td$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Td.a(Td.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Ld(new Kd(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(Zd.class), new Md(lazy), new Nd(lazy), function0);
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.Td$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                Td.a(Td.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.d = registerForActivityResult;
    }

    public static final ViewModelProvider.Factory a(Td owner) {
        Nb transactionModel = B4.b;
        if (B4.a != C4.b) {
            transactionModel = null;
        }
        if (transactionModel == null) {
            ((Jc) owner.b.getValue()).getClass();
            transactionModel = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        int i = Zd.i;
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return new Ud(owner, transactionModel);
    }

    public final void b() {
        ((Zd) this.c.getValue()).a.observe(this, new Jd(new Function1() { // from class: ch.datatrans.payment.Td$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Td.a(Td.this, (String) obj);
            }
        }));
        ((Zd) this.c.getValue()).e.observe(this, new Jd(new Function1() { // from class: ch.datatrans.payment.Td$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Td.a(Td.this, (TransactionException) obj);
            }
        }));
        ((Zd) this.c.getValue()).f.observe(this, new Jd(new Function1() { // from class: ch.datatrans.payment.Td$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Td.a(Td.this, (t4) obj);
            }
        }));
        ((Zd) this.c.getValue()).d.observe(this, new Jd(new Function1() { // from class: ch.datatrans.payment.Td$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Td.a(Td.this, (Unit) obj);
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
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new Id(this, null), 3, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    public static final Unit a(Td td, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) td.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) td.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(Td td, Unit unit) {
        Xa xa = ((Pa) td.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    public static final Unit a(Td td, TransactionException transactionException) {
        Jc jc = (Jc) td.b.getValue();
        Nb e = ((Zd) td.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final void a(Td td, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            ((Jc) td.b.getValue()).a(((Zd) td.c.getValue()).e());
        } else {
            ((Jc) td.b.getValue()).e.postValue(Unit.INSTANCE);
        }
    }

    public static final Unit a(Td td, String str) {
        Intent intent = new Intent(td.requireContext(), (Class<?>) ExternalProcessRelayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ExternalProcessRelayActivity.EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL, str);
        intent.putExtras(bundle);
        td.d.launch(intent);
        return Unit.INSTANCE;
    }
}
