package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.exception.GooglePayTransactionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCallback;", "", "onCustomerInfoReceived", "", "customerInfo", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfo;", "(Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public interface GooglePayCustomerInfoCallback {
    Object onCustomerInfoReceived(GooglePayCustomerInfo googlePayCustomerInfo, Continuation<? super Unit> continuation) throws GooglePayTransactionException;
}
