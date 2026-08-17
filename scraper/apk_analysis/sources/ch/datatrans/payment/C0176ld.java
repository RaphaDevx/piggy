package ch.datatrans.payment;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.exception.TWINTNotInstalledException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.ld, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0176ld extends D4 implements Ib {
    public static final /* synthetic */ int j = 0;
    public final Xa h;
    public final C0149ga i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0176ld(Nb transactionModel) {
        super(C4.e, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        this.h = new Xa();
        this.i = new C0149ga(ViewModelKt.getViewModelScope(this), this);
    }

    public static final Unit a(C0176ld c0176ld, String str) {
        c0176ld.f.postValue(null);
        c0176ld.e.postValue(new TWINTNotInstalledException(str));
        return Unit.INSTANCE;
    }

    @Override // ch.datatrans.payment.Ib
    public final void a(String token, String transactionId) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        e().n = transactionId;
        this.a.postValue(token);
    }

    public final void a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final String paymentMethodTitle = PaymentMethodType.TWINT.getTitle$lib_release().a(context);
        MutableLiveData mutableLiveData = this.f;
        C7 c7 = r4.a;
        Function0 dismissAction = new Function0() { // from class: ch.datatrans.payment.ld$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0176ld.a(C0176ld.this, paymentMethodTitle);
            }
        };
        Intrinsics.checkNotNullParameter(paymentMethodTitle, "paymentMethodTitle");
        Intrinsics.checkNotNullParameter(dismissAction, "dismissAction");
        mutableLiveData.postValue(new C0178m4(R.string.datatrans_sdk_error_title_generic, new Z9(R.string.datatrans_sdk_error_message_app_not_installed, paymentMethodTitle, paymentMethodTitle), dismissAction));
    }
}
