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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/gg;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class gg extends A4 {
    public final Lazy h;
    public final ActivityResultLauncher i;

    public gg() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return gg.a(gg.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new cg(new bg(this)));
        this.h = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(ig.class), new dg(lazy), new eg(lazy), function0);
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                gg.a(gg.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.i = registerForActivityResult;
    }

    public static final ViewModelProvider.Factory a(gg owner) {
        Nb transactionModel = B4.b;
        if (B4.a != C4.i) {
            transactionModel = null;
        }
        if (transactionModel == null) {
            ((Jc) owner.b.getValue()).getClass();
            transactionModel = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        int i = ig.p;
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return new hg(owner, transactionModel);
    }

    public static final Unit b(gg ggVar, Unit unit) {
        ((Jc) ggVar.b.getValue()).a(((ig) ggVar.h.getValue()).e());
        return Unit.INSTANCE;
    }

    public static final Unit c(gg ggVar, Unit unit) {
        Xa xa = ((Pa) ggVar.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    public static final Unit d(gg ggVar, Unit unit) {
        Xa xa = ((Jc) ggVar.b.getValue()).e;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((Pa) this.a.getValue()).b.postValue(Boolean.TRUE);
        b();
        if (bundle == null) {
            Nb nb = B4.b;
            if ((nb != null ? nb.n : null) == null) {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new Zf(this, null), 3, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    public final void b() {
        ((ig) this.h.getValue()).a.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.a(gg.this, (String) obj);
            }
        }));
        ((ig) this.h.getValue()).n.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.a(gg.this, (Unit) obj);
            }
        }));
        ((ig) this.h.getValue()).b.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.b(gg.this, (Unit) obj);
            }
        }));
        ((ig) this.h.getValue()).e.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.a(gg.this, (TransactionException) obj);
            }
        }));
        ((ig) this.h.getValue()).f.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.a(gg.this, (t4) obj);
            }
        }));
        ((ig) this.h.getValue()).d.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.c(gg.this, (Unit) obj);
            }
        }));
        ((ig) this.h.getValue()).c.observe(this, new ag(new Function1() { // from class: ch.datatrans.payment.gg$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return gg.d(gg.this, (Unit) obj);
            }
        }));
    }

    public static final Unit a(gg ggVar, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) ggVar.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) ggVar.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final void a(gg ggVar, ActivityResult activityResult) {
        ((Jc) ggVar.b.getValue()).a(((ig) ggVar.h.getValue()).e());
    }

    public static final Unit a(gg ggVar, Unit unit) {
        ((Jc) ggVar.b.getValue()).a((ig) ggVar.h.getValue());
        return Unit.INSTANCE;
    }

    public static final Unit a(gg ggVar, TransactionException transactionException) {
        Jc jc = (Jc) ggVar.b.getValue();
        Nb e = ((ig) ggVar.h.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final Unit a(gg ggVar, String str) {
        ((ig) ggVar.h.getValue()).e().h.D = true;
        Intent intent = new Intent(ggVar.requireContext(), (Class<?>) ExternalProcessRelayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ExternalProcessRelayActivity.EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL, str);
        intent.putExtras(bundle);
        ggVar.i.launch(intent);
        return Unit.INSTANCE;
    }
}
