package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.AuthorizationException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import com.klarna.mobile.sdk.api.KlarnaLoggingLevel;
import com.klarna.mobile.sdk.api.KlarnaMobileSDKCommon;
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentsSDKError;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* renamed from: ch.datatrans.payment.t6, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0221t6 extends D4 {
    public static final /* synthetic */ int l = 0;
    public final Xa h;
    public final MutableLiveData i;
    public final MutableLiveData j;
    public final MutableLiveData k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0221t6(Nb transactionModel) {
        super(C4.d, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.h = new Xa();
        Boolean bool = Boolean.FALSE;
        this.i = new MutableLiveData(bool);
        this.j = new MutableLiveData(bool);
        this.k = new MutableLiveData();
    }

    public static final Unit b(C0221t6 c0221t6) {
        c0221t6.f.postValue(null);
        c0221t6.e.postValue(new TechnicalException("No payment categories returned for Klarna", null, PaymentMethodType.KLARNA, c0221t6.e().n, 2, null));
        return Unit.INSTANCE;
    }

    public static final Unit c(C0221t6 c0221t6) {
        c0221t6.f.postValue(null);
        c0221t6.e.postValue(new TechnicalException("Registering a Klarna saved payment method is not supported.", null, PaymentMethodType.KLARNA, c0221t6.e().n));
        return Unit.INSTANCE;
    }

    public final void a(final KlarnaPaymentsSDKError klarnaPaymentsSDKError) {
        C0178m4 a;
        MutableLiveData mutableLiveData = this.f;
        if (Intrinsics.areEqual(klarnaPaymentsSDKError.getAction(), "Authorize") && Intrinsics.areEqual(klarnaPaymentsSDKError.getName(), "ShowFormFalseError")) {
            C7 c7 = r4.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.t6$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0221t6.a(C0221t6.this);
                }
            };
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            a = new C0178m4(R.string.datatrans_sdk_error_title_payment, R.string.datatrans_sdk_error_message_authorize_failed, dismissAction);
        } else {
            C7 c72 = r4.a;
            a = v4.a(new Function0() { // from class: ch.datatrans.payment.t6$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0221t6.a(C0221t6.this, klarnaPaymentsSDKError);
                }
            });
        }
        mutableLiveData.postValue(a);
    }

    public final void f() {
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.t6$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0221t6.b(C0221t6.this);
            }
        };
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        String paymentMethodIdentifier = PaymentMethodType.KLARNA.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String();
        Intrinsics.checkNotNullParameter(paymentMethodIdentifier, "paymentMethodIdentifier");
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, new Z9(R.string.datatrans_sdk_error_message_payment_method_not_available, paymentMethodIdentifier), dismissAction));
    }

    public final void g() {
        if (!AbstractC0239v6.a) {
            KlarnaMobileSDKCommon.Companion.setLoggingLevel(KlarnaLoggingLevel.Off);
            AbstractC0239v6.a = true;
        }
        if (e().d != null) {
            MutableLiveData mutableLiveData = this.f;
            C7 c7 = r4.a;
            mutableLiveData.postValue(v4.a(new Function0() { // from class: ch.datatrans.payment.t6$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return C0221t6.c(C0221t6.this);
                }
            }));
        } else {
            MutableLiveData mutableLiveData2 = this.k;
            int i = R.string.datatrans_sdk_confirm_pay_amount_button;
            W8 w8 = e().k;
            Intrinsics.checkNotNull(w8);
            mutableLiveData2.postValue(new Z9(i, w8.g));
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C0215s6(this, null), 3, null);
        }
    }

    public static final Unit a(C0221t6 c0221t6) {
        c0221t6.f.postValue(null);
        c0221t6.e.postValue(new AuthorizationException(null, PaymentMethodType.KLARNA, c0221t6.e().n));
        return Unit.INSTANCE;
    }

    public static final Unit a(C0221t6 c0221t6, KlarnaPaymentsSDKError klarnaPaymentsSDKError) {
        c0221t6.f.postValue(null);
        c0221t6.e.postValue(new TechnicalException("KlarnaPaymentError action:" + klarnaPaymentsSDKError.getAction() + ", name:" + klarnaPaymentsSDKError.getName() + ", message:'" + klarnaPaymentsSDKError.getMessage() + "', isFatal:" + klarnaPaymentsSDKError.isFatal() + ", invalidFields:" + klarnaPaymentsSDKError.getInvalidFields(), null, PaymentMethodType.KLARNA, c0221t6.e().n));
        return Unit.INSTANCE;
    }
}
