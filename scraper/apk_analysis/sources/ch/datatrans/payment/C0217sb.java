package ch.datatrans.payment;

import android.content.Intent;
import android.net.Uri;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelKt;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;
import net.openid.appauth.ResponseTypeValues;

/* renamed from: ch.datatrans.payment.sb, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0217sb extends D4 implements Ib {
    public static final /* synthetic */ int j = 0;
    public final boolean h;
    public final Kb i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0217sb(Nb transactionModel, boolean z, SavedStateHandle savedStateHandle) {
        super(C4.a, transactionModel);
        Intrinsics.checkNotNullParameter(transactionModel, "transactionModel");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.h = z;
        C0149ga c0149ga = new C0149ga(ViewModelKt.getViewModelScope(this), this);
        W8 w8 = transactionModel.k;
        Intrinsics.checkNotNull(w8);
        this.i = new Kb(c0149ga, this, w8, transactionModel.h, PaymentMethodType.SWISH);
    }

    @Override // ch.datatrans.payment.Ib
    public final void a(String token, String transactionId) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        e().n = transactionId;
        Intent intent = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("swish").authority("paymentrequest").appendQueryParameter(ResponseTypeValues.TOKEN, token).build());
        intent.setPackage(this.h ? "se.bankgirot.swish.sandbox" : "se.bankgirot.swish");
        this.a.postValue(intent);
    }
}
