package ch.datatrans.payment;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Zd extends D4 implements S9 {
    public static final /* synthetic */ int i = 0;
    public final U9 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Zd(Nb transactionModel, SavedStateHandle savedStateHandle) {
        super(C4.b, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        C0149ga c0149ga = new C0149ga(ViewModelKt.getViewModelScope(this), this);
        W8 w8 = transactionModel.k;
        Intrinsics.checkNotNull(w8);
        this.h = new U9(c0149ga, this, w8, transactionModel.h, PaymentMethodType.VIPPS, null);
    }

    @Override // ch.datatrans.payment.S9
    public final void a(String str, String transactionId, PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        e().n = transactionId;
        this.a.postValue(str);
    }
}
