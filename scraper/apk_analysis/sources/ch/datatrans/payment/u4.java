package ch.datatrans.payment;

import android.content.Context;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class u4 implements InterfaceC0235ud {
    public final /* synthetic */ PaymentMethodType a;

    public u4(PaymentMethodType paymentMethodType) {
        this.a = paymentMethodType;
    }

    @Override // ch.datatrans.payment.InterfaceC0235ud
    public final String a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(R.string.datatrans_sdk_error_message_payment_method_not_available, this.a.getTitle$lib_release().a(context));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
