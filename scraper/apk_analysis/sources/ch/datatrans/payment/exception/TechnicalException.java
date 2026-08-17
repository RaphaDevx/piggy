package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B7\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lch/datatrans/payment/exception/TechnicalException;", "Lch/datatrans/payment/exception/TransactionException;", "message", "", "cause", "", "paymentMethodType", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "transactionId", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public class TechnicalException extends TransactionException {
    public /* synthetic */ TechnicalException(String str, Throwable th, PaymentMethodType paymentMethodType, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th, (i & 4) != 0 ? null : paymentMethodType, (i & 8) != 0 ? null : str2);
    }

    public TechnicalException(String str, Throwable th, PaymentMethodType paymentMethodType, String str2) {
        super(str, th, paymentMethodType, str2);
    }
}
