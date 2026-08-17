package ch.datatrans.payment;

import android.content.Context;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/c7;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: ch.datatrans.payment.c7, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0118c7 extends A4 {
    public final Lazy c;
    public String d;
    public final ActivityResultLauncher e;

    public C0118c7() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.c7$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0118c7.a(C0118c7.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new U6(new T6(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(k7.class), new V6(lazy), new W6(lazy), function0);
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: ch.datatrans.payment.c7$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                C0118c7.a(C0118c7.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.e = registerForActivityResult;
    }

    public static final ViewModelProvider.Factory a(C0118c7 owner) {
        Nb transactionModel = B4.b;
        if (B4.a != C4.g) {
            transactionModel = null;
        }
        if (transactionModel == null) {
            ((Jc) owner.b.getValue()).getClass();
            transactionModel = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        int i = k7.i;
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return new C0128d7(owner, transactionModel);
    }

    public final void b() {
        ((k7) this.c.getValue()).a.observe(this, new S6(new Function1() { // from class: ch.datatrans.payment.c7$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0118c7.a(C0118c7.this, (String) obj);
            }
        }));
        ((k7) this.c.getValue()).e.observe(this, new S6(new Function1() { // from class: ch.datatrans.payment.c7$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0118c7.a(C0118c7.this, (TransactionException) obj);
            }
        }));
        ((k7) this.c.getValue()).f.observe(this, new S6(new Function1() { // from class: ch.datatrans.payment.c7$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0118c7.a(C0118c7.this, (t4) obj);
            }
        }));
        ((k7) this.c.getValue()).d.observe(this, new S6(new Function1() { // from class: ch.datatrans.payment.c7$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0118c7.a(C0118c7.this, (Unit) obj);
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
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new R6(this, null), 3, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    public static final Unit a(C0118c7 c0118c7, t4 errorModel) {
        if (errorModel != null) {
            Pa pa = (Pa) c0118c7.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) c0118c7.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0118c7 c0118c7, Unit unit) {
        Xa xa = ((Pa) c0118c7.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    public static final Unit a(C0118c7 c0118c7, TransactionException transactionException) {
        Jc jc = (Jc) c0118c7.b.getValue();
        Nb e = ((k7) c0118c7.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final void a(C0118c7 c0118c7, ActivityResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == 13) {
            k7 k7Var = (k7) c0118c7.c.getValue();
            Context requireContext = c0118c7.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            k7Var.a(requireContext);
            return;
        }
        if (result.getResultCode() == -1) {
            Intent data = result.getData();
            String stringExtra = data != null ? data.getStringExtra(ExternalProcessRelayActivity.EXTRA_TRANSACTION_ID) : null;
            if (stringExtra != null) {
                if (Intrinsics.areEqual(((k7) c0118c7.c.getValue()).e().n, stringExtra)) {
                    ((Jc) c0118c7.b.getValue()).a(((k7) c0118c7.c.getValue()).e());
                    return;
                }
                if (c0118c7.d != null) {
                    Intent intent = new Intent(c0118c7.requireContext(), (Class<?>) ExternalProcessRelayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(ExternalProcessRelayActivity.EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL, c0118c7.d);
                    intent.putExtras(bundle);
                    c0118c7.e.launch(intent);
                    return;
                }
                ((Jc) c0118c7.b.getValue()).e.postValue(Unit.INSTANCE);
                return;
            }
            ((Jc) c0118c7.b.getValue()).a(((k7) c0118c7.c.getValue()).e());
            return;
        }
        ((Jc) c0118c7.b.getValue()).a(((k7) c0118c7.c.getValue()).e());
    }

    public static final Unit a(C0118c7 c0118c7, String str) {
        c0118c7.d = str;
        if (str != null) {
            Intent intent = new Intent(c0118c7.requireContext(), (Class<?>) ExternalProcessRelayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(ExternalProcessRelayActivity.EXTRA_EXTERNAL_PAYMENT_APP_REDIRECT_URL, c0118c7.d);
            intent.putExtras(bundle);
            c0118c7.e.launch(intent);
        } else {
            ((Jc) c0118c7.b.getValue()).e.postValue(Unit.INSTANCE);
        }
        return Unit.INSTANCE;
    }
}
