package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017¨\u0006\u001c"}, d2 = {"Lch/datatrans/payment/paymentmethods/Card;", "", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "type", "", "number", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "expiryDate", "cvv", "cardholder", "<init>", "(Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;Ljava/lang/String;Ljava/lang/String;)V", "a", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getType", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "b", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "getExpiryDate", "()Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "c", "Ljava/lang/String;", "getCvv", "()Ljava/lang/String;", "d", "getCardholder", "e", "getNumber", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class Card {

    /* renamed from: a, reason: from kotlin metadata */
    public final PaymentMethodType type;

    /* renamed from: b, reason: from kotlin metadata */
    public final CardExpiryDate expiryDate;

    /* renamed from: c, reason: from kotlin metadata */
    public final String cvv;

    /* renamed from: d, reason: from kotlin metadata */
    public final String cardholder;

    /* renamed from: e, reason: from kotlin metadata */
    public final String number;

    public Card(PaymentMethodType type, String number, CardExpiryDate expiryDate, String str, String str2) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(expiryDate, "expiryDate");
        this.type = type;
        this.expiryDate = expiryDate;
        this.cvv = str;
        this.cardholder = str2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char charAt = number.charAt(i);
            if (!CharsKt.isWhitespace(charAt)) {
                sb.append(charAt);
            }
        }
        this.number = sb.toString();
        if (!this.type.isCreditCard$lib_release()) {
            throw new IllegalArgumentException("Invalid payment method");
        }
        if (this.type.getCvvLength() != 0 && this.cvv == null) {
            throw new IllegalArgumentException("CVV must not be null for this card type.");
        }
    }

    public final String getCardholder() {
        return this.cardholder;
    }

    public final String getCvv() {
        return this.cvv;
    }

    public final CardExpiryDate getExpiryDate() {
        return this.expiryDate;
    }

    public final String getNumber() {
        return this.number;
    }

    public final PaymentMethodType getType() {
        return this.type;
    }

    public /* synthetic */ Card(PaymentMethodType paymentMethodType, String str, CardExpiryDate cardExpiryDate, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(paymentMethodType, str, cardExpiryDate, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3);
    }
}
