package ch.datatrans.payment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.exception.TechnicalException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class ig extends D4 implements S9, Be {
    public static final /* synthetic */ int p = 0;
    public final Xa n;
    public final U9 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ig(Nb transactionModel, SavedStateHandle savedStateHandle) {
        super(C4.i, transactionModel);
        U9 u9;
        SavedPaymentMethod savedPaymentMethod;
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.n = new Xa();
        W8 w8 = transactionModel.k;
        if (w8 != null) {
            u9 = new U9(new C0149ga(ViewModelKt.getViewModelScope(this), this), this, w8, transactionModel.h, PaymentMethodType.POST_FINANCE_PAY, (!transactionModel.b || (savedPaymentMethod = transactionModel.l) == null) ? null : savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String());
        } else {
            C0149ga retryRequestHandler = new C0149ga(ViewModelKt.getViewModelScope(this), this);
            C0131e aliasRequest = transactionModel.d;
            Intrinsics.checkNotNull(aliasRequest);
            Q5 options = transactionModel.h;
            PaymentMethodType paymentMethodType = PaymentMethodType.POST_FINANCE_PAY;
            Intrinsics.checkNotNullParameter(retryRequestHandler, "retryRequestHandler");
            Intrinsics.checkNotNullParameter(this, "callback");
            Intrinsics.checkNotNullParameter(aliasRequest, "aliasRequest");
            Intrinsics.checkNotNullParameter(options, "options");
            Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
            Intrinsics.checkNotNullParameter(aliasRequest, "<this>");
            u9 = new U9(retryRequestHandler, this, new Y8(0, aliasRequest.a, aliasRequest.b, aliasRequest.c, aliasRequest.d, aliasRequest.e, null), options, paymentMethodType);
        }
        this.o = u9;
    }

    @Override // ch.datatrans.payment.Be
    public final void a(BackendException e) {
        Intrinsics.checkNotNullParameter(e, "e");
    }

    @Override // ch.datatrans.payment.Be
    public final void c() {
        this.c.postValue(null);
    }

    public final void f() {
        Function0 function0 = new Function0() { // from class: ch.datatrans.payment.ig$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ig.a(ig.this);
            }
        };
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        mutableLiveData.postValue(v4.a(function0));
    }

    @Override // ch.datatrans.payment.Be
    public final void a(String str, Map parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
    }

    public static final Unit a(ig igVar) {
        igVar.f.postValue(null);
        igVar.e.postValue(new TechnicalException("Invalid server response.", null, PaymentMethodType.POST_FINANCE_PAY, null, 10, null));
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.S9
    public final void a(String str, String transactionId, PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        if (str == null) {
            f();
        } else {
            e().n = transactionId;
            this.a.postValue(str);
        }
    }
}
