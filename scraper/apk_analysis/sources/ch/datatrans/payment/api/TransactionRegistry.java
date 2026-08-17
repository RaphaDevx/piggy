package ch.datatrans.payment.api;

import android.app.Activity;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.Nb;
import ch.datatrans.payment.bottomsheet.TransactionSheetActivity;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import com.tealium.library.DataSources;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001J\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0011\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0010\u0010\nR(\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u001e\u001a\u00020\u00178\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lch/datatrans/payment/api/TransactionRegistry;", "", "Landroid/app/Activity;", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Lch/datatrans/payment/api/Transaction;", "transaction", "", "startTransaction", "(Landroid/app/Activity;Lch/datatrans/payment/api/Transaction;)V", "initTransactionFailure$lib_release", "()V", "initTransactionFailure", "", "isTransactionFailure$lib_release", "(Landroid/app/Activity;)Z", "isTransactionFailure", "clear$lib_release", "clear", "value", "a", "Lch/datatrans/payment/api/Transaction;", "getTransaction", "()Lch/datatrans/payment/api/Transaction;", "Lch/datatrans/payment/Nb;", "b", "Lch/datatrans/payment/Nb;", "getTransactionModel$lib_release", "()Lch/datatrans/payment/Nb;", "setTransactionModel$lib_release", "(Lch/datatrans/payment/Nb;)V", "transactionModel", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class TransactionRegistry {

    /* renamed from: a, reason: from kotlin metadata */
    public static Transaction transaction;
    public static final TransactionRegistry INSTANCE = new TransactionRegistry();

    /* renamed from: b, reason: from kotlin metadata */
    public static Nb transactionModel = new Nb();

    public final void clear$lib_release() {
        transaction = null;
        transactionModel = new Nb();
    }

    public final Transaction getTransaction() {
        return transaction;
    }

    public final Nb getTransactionModel$lib_release() {
        return transactionModel;
    }

    public final void initTransactionFailure$lib_release() {
        transaction = new Transaction("", (List<? extends SavedPaymentMethod>) CollectionsKt.emptyList());
    }

    public final boolean isTransactionFailure$lib_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Transaction transaction2 = transaction;
        if (activity instanceof TransactionSheetActivity) {
            return transaction2 == null || transaction2.getMobileToken().length() == 0;
        }
        return false;
    }

    public final void setTransactionModel$lib_release(Nb nb) {
        Intrinsics.checkNotNullParameter(nb, "<set-?>");
        transactionModel = nb;
    }

    public final void startTransaction(Activity activity, Transaction transaction2) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(transaction2, "transaction");
        Transaction transaction3 = transaction;
        if (transaction3 == null) {
            transaction = transaction2;
            transaction2.start$lib_release(activity);
        } else {
            Log.e("DTPL", "A transaction with mobileToken '" + transaction3.getMobileToken() + "' is already in progress");
        }
    }
}
