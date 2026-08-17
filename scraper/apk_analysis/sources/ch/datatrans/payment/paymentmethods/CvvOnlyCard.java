package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u000f¨\u0006#"}, d2 = {"Lch/datatrans/payment/paymentmethods/CvvOnlyCard;", "", "", "maskedCardNumber", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "expiryDate", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "type", "<init>", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;Lch/datatrans/payment/paymentmethods/PaymentMethodType;)V", "component1", "()Ljava/lang/String;", "component2", "()Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "component3", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "copy", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;Lch/datatrans/payment/paymentmethods/PaymentMethodType;)Lch/datatrans/payment/paymentmethods/CvvOnlyCard;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getMaskedCardNumber", "b", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "getExpiryDate", "c", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getType", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class CvvOnlyCard {

    /* renamed from: a, reason: from kotlin metadata */
    public final String maskedCardNumber;

    /* renamed from: b, reason: from kotlin metadata */
    public final CardExpiryDate expiryDate;

    /* renamed from: c, reason: from kotlin metadata */
    public final PaymentMethodType type;

    public CvvOnlyCard(String maskedCardNumber, CardExpiryDate expiryDate, PaymentMethodType type) {
        Intrinsics.checkNotNullParameter(maskedCardNumber, "maskedCardNumber");
        Intrinsics.checkNotNullParameter(expiryDate, "expiryDate");
        Intrinsics.checkNotNullParameter(type, "type");
        this.maskedCardNumber = maskedCardNumber;
        this.expiryDate = expiryDate;
        this.type = type;
    }

    public static /* synthetic */ CvvOnlyCard copy$default(CvvOnlyCard cvvOnlyCard, String str, CardExpiryDate cardExpiryDate, PaymentMethodType paymentMethodType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cvvOnlyCard.maskedCardNumber;
        }
        if ((i & 2) != 0) {
            cardExpiryDate = cvvOnlyCard.expiryDate;
        }
        if ((i & 4) != 0) {
            paymentMethodType = cvvOnlyCard.type;
        }
        return cvvOnlyCard.copy(str, cardExpiryDate, paymentMethodType);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMaskedCardNumber() {
        return this.maskedCardNumber;
    }

    /* renamed from: component2, reason: from getter */
    public final CardExpiryDate getExpiryDate() {
        return this.expiryDate;
    }

    /* renamed from: component3, reason: from getter */
    public final PaymentMethodType getType() {
        return this.type;
    }

    public final CvvOnlyCard copy(String maskedCardNumber, CardExpiryDate expiryDate, PaymentMethodType type) {
        Intrinsics.checkNotNullParameter(maskedCardNumber, "maskedCardNumber");
        Intrinsics.checkNotNullParameter(expiryDate, "expiryDate");
        Intrinsics.checkNotNullParameter(type, "type");
        return new CvvOnlyCard(maskedCardNumber, expiryDate, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CvvOnlyCard)) {
            return false;
        }
        CvvOnlyCard cvvOnlyCard = (CvvOnlyCard) other;
        return Intrinsics.areEqual(this.maskedCardNumber, cvvOnlyCard.maskedCardNumber) && Intrinsics.areEqual(this.expiryDate, cvvOnlyCard.expiryDate) && this.type == cvvOnlyCard.type;
    }

    public final CardExpiryDate getExpiryDate() {
        return this.expiryDate;
    }

    public final String getMaskedCardNumber() {
        return this.maskedCardNumber;
    }

    public final PaymentMethodType getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode() + ((this.expiryDate.hashCode() + (this.maskedCardNumber.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "CvvOnlyCard(maskedCardNumber=" + this.maskedCardNumber + ", expiryDate=" + this.expiryDate + ", type=" + this.type + ')';
    }
}
