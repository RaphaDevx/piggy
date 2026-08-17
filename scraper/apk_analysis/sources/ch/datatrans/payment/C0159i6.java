package ch.datatrans.payment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/i6;", "Lch/datatrans/payment/A4;", "<init>", "()V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: ch.datatrans.payment.i6, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0159i6 extends A4 {
    public static final /* synthetic */ int g = 0;
    public final Lazy c;
    public KlarnaPaymentView d;
    public Button e;
    public boolean f;

    public C0159i6() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0159i6.a(C0159i6.this);
            }
        };
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new V5(new U5(this)));
        this.c = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(C0221t6.class), new W5(lazy), new X5(lazy), function0);
    }

    public static final Unit a(C0159i6 c0159i6, J9 j9) {
        String str = j9.a;
        String str2 = j9.b;
        Jc jc = (Jc) c0159i6.b.getValue();
        Nb value = ((C0221t6) c0159i6.c.getValue()).e();
        jc.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        TransactionRegistry.INSTANCE.setTransactionModel$lib_release(value);
        KlarnaPaymentView klarnaPaymentView = c0159i6.d;
        C0156he c0156he = null;
        if (klarnaPaymentView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
            klarnaPaymentView = null;
        }
        klarnaPaymentView.setCategory(str2);
        KlarnaPaymentView klarnaPaymentView2 = c0159i6.d;
        if (klarnaPaymentView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
            klarnaPaymentView2 = null;
        }
        klarnaPaymentView2.registerPaymentViewCallback(new S5(c0159i6));
        KlarnaPaymentView klarnaPaymentView3 = c0159i6.d;
        if (klarnaPaymentView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
            klarnaPaymentView3 = null;
        }
        C0156he c0156he2 = r4.d;
        if (c0156he2 != null) {
            c0156he = c0156he2;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("webCallbackUrls");
        }
        klarnaPaymentView3.initialize(str, c0156he.c);
        return Unit.INSTANCE;
    }

    public static final Unit b(C0159i6 c0159i6, Unit unit) {
        ((Jc) c0159i6.b.getValue()).a(((C0221t6) c0159i6.c.getValue()).e());
        return Unit.INSTANCE;
    }

    public static final Unit c(C0159i6 c0159i6, Unit unit) {
        Xa xa = ((Pa) c0159i6.a.getValue()).f;
        Unit unit2 = Unit.INSTANCE;
        xa.postValue(unit2);
        return unit2;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Pa pa = (Pa) this.a.getValue();
        MutableLiveData mutableLiveData = pa.a;
        Boolean bool = Boolean.TRUE;
        mutableLiveData.postValue(bool);
        pa.b.postValue(bool);
        InterfaceC0235ud a = y4.a(PaymentMethodType.KLARNA);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        Pa.a(pa, a.a(requireContext), null, 6);
        b();
        if (bundle == null) {
            Nb nb = B4.b;
            if ((nb != null ? nb.n : null) == null) {
                ((C0221t6) this.c.getValue()).g();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.dtpl_klarna_fragment, viewGroup, false);
        KlarnaPaymentView findViewById = inflate.findViewById(R.id.klarna_payment_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.d = findViewById;
        View findViewById2 = inflate.findViewById(R.id.pay_button);
        Button button = (Button) findViewById2;
        button.setOnClickListener(new View.OnClickListener() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0159i6.a(C0159i6.this, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(findViewById2, "apply(...)");
        this.e = button;
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        if (this.f) {
            this.f = false;
            ((Pa) this.a.getValue()).c.postValue(Boolean.FALSE);
        }
        ((Pa) this.a.getValue()).b.postValue(Boolean.FALSE);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        ((Pa) this.a.getValue()).c.postValue(Boolean.valueOf(this.f));
    }

    @Override // ch.datatrans.payment.A4, androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        ((Pa) this.a.getValue()).c.postValue(Boolean.FALSE);
    }

    public final void b() {
        ((C0221t6) this.c.getValue()).a.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.a(C0159i6.this, (J9) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).b.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.b(C0159i6.this, (Unit) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).c.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.c(C0159i6.this, (Unit) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).e.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.a(C0159i6.this, (TransactionException) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).f.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.a(C0159i6.this, (t4) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).h.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.a(C0159i6.this, (Unit) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).i.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.a(C0159i6.this, (Boolean) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).j.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.b(C0159i6.this, (Boolean) obj);
            }
        }));
        ((C0221t6) this.c.getValue()).k.observe(this, new T5(new Function1() { // from class: ch.datatrans.payment.i6$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return C0159i6.a(C0159i6.this, (InterfaceC0235ud) obj);
            }
        }));
    }

    public static final Unit b(C0159i6 c0159i6, Boolean bool) {
        Button button = c0159i6.e;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payButton");
            button = null;
        }
        Intrinsics.checkNotNull(bool);
        button.setEnabled(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public static final ViewModelProvider.Factory a(C0159i6 c0159i6) {
        Nb nb = B4.b;
        if (B4.a != C4.d) {
            nb = null;
        }
        if (nb == null) {
            ((Jc) c0159i6.b.getValue()).getClass();
            nb = TransactionRegistry.INSTANCE.getTransactionModel$lib_release().a();
        }
        return new C0231u6(nb);
    }

    public static final Unit a(C0159i6 c0159i6, TransactionException transactionException) {
        Jc jc = (Jc) c0159i6.b.getValue();
        Nb e = ((C0221t6) c0159i6.c.getValue()).e();
        Intrinsics.checkNotNull(transactionException);
        jc.a(e, transactionException);
        return Unit.INSTANCE;
    }

    public static final void a(C0159i6 c0159i6, View view) {
        C0221t6 c0221t6 = (C0221t6) c0159i6.c.getValue();
        c0221t6.j.postValue(Boolean.FALSE);
        c0221t6.h.postValue(Unit.INSTANCE);
    }

    public static final Unit a(C0159i6 c0159i6, t4 errorModel) {
        if (errorModel != null) {
            if (c0159i6.f) {
                c0159i6.f = false;
                ((Pa) c0159i6.a.getValue()).c.postValue(Boolean.FALSE);
            }
            Pa pa = (Pa) c0159i6.a.getValue();
            pa.getClass();
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            pa.d.postValue(errorModel);
        } else {
            ((Pa) c0159i6.a.getValue()).d.postValue(null);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0159i6 c0159i6, Unit unit) {
        KlarnaPaymentView klarnaPaymentView = c0159i6.d;
        if (klarnaPaymentView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
            klarnaPaymentView = null;
        }
        klarnaPaymentView.authorize(Boolean.TRUE, (String) null);
        return Unit.INSTANCE;
    }

    public static final Unit a(C0159i6 c0159i6, Boolean bool) {
        Button button = c0159i6.e;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payButton");
            button = null;
        }
        Intrinsics.checkNotNull(bool);
        button.setVisibility(bool.booleanValue() ? 0 : 8);
        if (bool.booleanValue()) {
            Button button3 = c0159i6.e;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("payButton");
            } else {
                button2 = button3;
            }
            x4.a(button2, 1500L);
        }
        return Unit.INSTANCE;
    }

    public static final Unit a(C0159i6 c0159i6, InterfaceC0235ud interfaceC0235ud) {
        Button button = c0159i6.e;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payButton");
            button = null;
        }
        x4.a(button, interfaceC0235ud);
        return Unit.INSTANCE;
    }
}
