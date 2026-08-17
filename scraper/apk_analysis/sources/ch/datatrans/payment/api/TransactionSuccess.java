package ch.datatrans.payment.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\f\u001a\u0004\b\u0014\u0010\u000eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lch/datatrans/payment/api/TransactionSuccess;", "", "", "transactionId", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodType", "mobileToken", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "savedPaymentMethod", "<init>", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;)V", "a", "Ljava/lang/String;", "getTransactionId", "()Ljava/lang/String;", "b", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getPaymentMethodType", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "c", "getMobileToken", "d", "Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "getSavedPaymentMethod", "()Lch/datatrans/payment/paymentmethods/SavedPaymentMethod;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class TransactionSuccess {

    /* renamed from: a, reason: from kotlin metadata */
    public final String transactionId;

    /* renamed from: b, reason: from kotlin metadata */
    public final PaymentMethodType paymentMethodType;

    /* renamed from: c, reason: from kotlin metadata */
    public final String mobileToken;

    /* renamed from: d, reason: from kotlin metadata */
    public final SavedPaymentMethod savedPaymentMethod;

    public TransactionSuccess(String transactionId, PaymentMethodType paymentMethodType, String mobileToken, SavedPaymentMethod savedPaymentMethod) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        this.transactionId = transactionId;
        this.paymentMethodType = paymentMethodType;
        this.mobileToken = mobileToken;
        this.savedPaymentMethod = savedPaymentMethod;
    }

    public final String getMobileToken() {
        return this.mobileToken;
    }

    public final PaymentMethodType getPaymentMethodType() {
        return this.paymentMethodType;
    }

    public final SavedPaymentMethod getSavedPaymentMethod() {
        return this.savedPaymentMethod;
    }

    public final String getTransactionId() {
        return this.transactionId;
    }

    public /* synthetic */ TransactionSuccess(String str, PaymentMethodType paymentMethodType, String str2, SavedPaymentMethod savedPaymentMethod, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, paymentMethodType, str2, (i & 8) != 0 ? null : savedPaymentMethod);
    }
}
