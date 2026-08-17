package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "This class is now deprecated. Please use AppNotInstalledException instead.", replaceWith = @ReplaceWith(expression = "AppNotInstalledException", imports = {}))
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lch/datatrans/payment/exception/TWINTNotInstalledException;", "Lch/datatrans/payment/exception/AppNotInstalledException;", "title", "", "<init>", "(Ljava/lang/String;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class TWINTNotInstalledException extends AppNotInstalledException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TWINTNotInstalledException(String title) {
        super(title, PaymentMethodType.TWINT);
        Intrinsics.checkNotNullParameter(title, "title");
    }
}
