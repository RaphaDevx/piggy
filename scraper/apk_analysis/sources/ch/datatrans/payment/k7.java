package ch.datatrans.payment;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.AppNotInstalledException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class k7 extends D4 implements S9 {
    public static final /* synthetic */ int i = 0;
    public final U9 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k7(Nb transactionModel, SavedStateHandle savedStateHandle) {
        super(C4.g, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        C0149ga c0149ga = new C0149ga(ViewModelKt.getViewModelScope(this), this);
        W8 w8 = transactionModel.k;
        Intrinsics.checkNotNull(w8);
        this.h = new U9(c0149ga, this, w8, transactionModel.h, PaymentMethodType.MOBILE_PAY, null);
    }

    public final void a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final String paymentMethodTitle = PaymentMethodType.MOBILE_PAY.getTitle$lib_release().a(context);
        if (e().j.size() == 1) {
            MutableLiveData mutableLiveData = this.f;
            C7 c7 = r4.a;
            Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.k7$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return k7.a(k7.this, paymentMethodTitle);
                }
            };
            Intrinsics.checkNotNullParameter(paymentMethodTitle, "paymentMethodTitle");
            Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
            mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, new Z9(R.string.datatrans_sdk_error_message_app_not_installed, paymentMethodTitle, paymentMethodTitle), dismissAction));
            return;
        }
        MutableLiveData mutableLiveData2 = this.f;
        C7 c72 = r4.a;
        Function0 dismissAction2 = new Function0() { // from class: ch.datatrans.payment.k7$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return k7.a(k7.this);
            }
        };
        Intrinsics.checkNotNullParameter(paymentMethodTitle, "paymentMethodTitle");
        Intrinsics.checkNotNullParameter(dismissAction2, "dismissAction");
        mutableLiveData2.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, new Z9(R.string.datatrans_sdk_error_message_app_not_installed, paymentMethodTitle, paymentMethodTitle), dismissAction2));
    }

    public static final Unit a(k7 k7Var, String str) {
        k7Var.f.postValue(null);
        k7Var.e.postValue(new AppNotInstalledException(str, PaymentMethodType.MOBILE_PAY));
        return Unit.INSTANCE;
    }

    public static final Unit a(k7 k7Var) {
        k7Var.f.postValue(null);
        Xa xa = k7Var.d;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }

    @Override // ch.datatrans.payment.S9
    public final void a(String str, String transactionId, PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        e().n = transactionId;
        this.a.postValue(str);
    }
}
