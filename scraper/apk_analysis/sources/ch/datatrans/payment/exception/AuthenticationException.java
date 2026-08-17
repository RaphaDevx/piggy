package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lch/datatrans/payment/exception/AuthenticationException;", "Lch/datatrans/payment/exception/TransactionException;", "", "cause", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodType", "", "transactionId", "userMessage", "<init>", "(Ljava/lang/Throwable;Lch/datatrans/payment/paymentmethods/PaymentMethodType;Ljava/lang/String;Ljava/lang/String;)V", "d", "Ljava/lang/String;", "getUserMessage", "()Ljava/lang/String;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class AuthenticationException extends TransactionException {

    /* renamed from: d, reason: from kotlin metadata */
    public final String userMessage;

    public /* synthetic */ AuthenticationException(Throwable th, PaymentMethodType paymentMethodType, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, paymentMethodType, str, (i & 8) != 0 ? null : str2);
    }

    public final String getUserMessage() {
        return this.userMessage;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthenticationException(Throwable cause, PaymentMethodType paymentMethodType, String str, String str2) {
        super("Authentication Error", cause, paymentMethodType, str);
        Intrinsics.checkNotNullParameter(cause, "cause");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        this.userMessage = str2;
    }
}
