package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.datatrans.payment.exception.AuthenticationException;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class D4 extends ViewModel implements InterfaceC0141f4 {
    public final Xa a;
    public final Xa b;
    public final Xa c;
    public final Xa d;
    public final Xa e;
    public final MutableLiveData f;
    public final MutableLiveData g;
    public final Nb m;

    public D4(C4 type, Nb value) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "trxModel");
        this.m = value;
        this.a = new Xa();
        this.b = new Xa();
        this.c = new Xa();
        this.d = new Xa();
        this.e = new Xa();
        this.f = new MutableLiveData();
        this.g = new MutableLiveData();
        B4.a = type;
        Intrinsics.checkNotNullParameter(value, "value");
        B4.b = value;
    }

    public static final Unit a(D4 d4, Function0 function0) {
        d4.f.postValue(null);
        function0.invoke();
        return Unit.INSTANCE;
    }

    public static final Unit b(D4 d4) {
        d4.f.postValue(null);
        Xa xa = d4.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void c(Function0 retryAction) {
        Intrinsics.checkNotNullParameter(retryAction, "retry");
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.D4$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return D4.b(D4.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_no_secure_connection, retryAction, cancelAction));
    }

    public final Nb e() {
        if (B4.b == null) {
            B4.b = this.m;
        }
        Nb nb = B4.b;
        Intrinsics.checkNotNull(nb);
        return nb;
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void b(final Function0 retry) {
        Intrinsics.checkNotNullParameter(retry, "retry");
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Function0 retryAction = new Function0() { // from class: ch.datatrans.payment.D4$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return D4.a(D4.this, retry);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        mutableLiveData.postValue(new C0228u(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction));
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void a(Function0 retryAction) {
        Intrinsics.checkNotNullParameter(retryAction, "retry");
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Function0 cancelAction = new Function0() { // from class: ch.datatrans.payment.D4$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return D4.a(D4.this);
            }
        };
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        mutableLiveData.postValue(new aa(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_connection_retry, retryAction, cancelAction));
    }

    public static final Unit a(D4 d4) {
        d4.f.postValue(null);
        Xa xa = d4.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // ch.datatrans.payment.InterfaceC0141f4
    public final void a(final Exception e, final PaymentMethodType paymentMethodType) {
        C0178m4 c0178m4;
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.D4$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return D4.a(D4.this, e, paymentMethodType);
            }
        };
        MutableLiveData mutableLiveData = this.f;
        if (e().d == null) {
            C7 c7 = r4.a;
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_generic, R.string.datatrans_sdk_error_message_authentication_failed, dismissAction);
        } else {
            C7 c72 = r4.a;
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            c0178m4 = new C0178m4(R.string.datatrans_sdk_error_title_registration_failed, R.string.datatrans_sdk_error_message_authentication_failed_short, dismissAction);
        }
        mutableLiveData.postValue(c0178m4);
    }

    public static final Unit a(D4 d4, Exception exc, PaymentMethodType paymentMethodType) {
        String str;
        d4.f.postValue(null);
        BackendException backendException = exc instanceof BackendException ? (BackendException) exc : null;
        if (backendException == null || (str = backendException.getTransactionId()) == null) {
            str = d4.e().n;
        }
        d4.e.postValue(exc instanceof TechnicalException ? (TransactionException) exc : new AuthenticationException(exc, paymentMethodType, str, null, 8, null));
        return Unit.INSTANCE;
    }
}
