package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\b\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lch/datatrans/payment/exception/TransactionListenerOnSuccessException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "cause", "<init>", "(Ljava/lang/Throwable;)V", "a", "Ljava/lang/Throwable;", "getCause", "()Ljava/lang/Throwable;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class TransactionListenerOnSuccessException extends Exception {

    /* renamed from: a, reason: from kotlin metadata */
    public final Throwable cause;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionListenerOnSuccessException(Throwable cause) {
        super(cause);
        Intrinsics.checkNotNullParameter(cause, "cause");
        this.cause = cause;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
