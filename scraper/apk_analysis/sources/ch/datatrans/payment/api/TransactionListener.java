package ch.datatrans.payment.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.exception.TransactionException;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lch/datatrans/payment/api/TransactionListener;", "", "onTransactionSuccess", "", "result", "Lch/datatrans/payment/api/TransactionSuccess;", "onTransactionError", SentryEvent.JsonKeys.EXCEPTION, "Lch/datatrans/payment/exception/TransactionException;", "onTransactionCancel", "mobileToken", "", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public interface TransactionListener {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        @Deprecated
        public static void onTransactionCancel(TransactionListener transactionListener, String mobileToken) {
            Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
            TransactionListener.super.onTransactionCancel(mobileToken);
        }
    }

    default void onTransactionCancel(String mobileToken) {
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
    }

    void onTransactionError(TransactionException exception);

    void onTransactionSuccess(TransactionSuccess result);
}
