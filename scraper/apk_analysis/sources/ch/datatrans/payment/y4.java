package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class y4 {
    public static final InterfaceC0235ud a(PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(paymentMethodType, "<this>");
        Map map = r4.f;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentMethodTitleOverrides");
            map = null;
        }
        InterfaceC0235ud interfaceC0235ud = (InterfaceC0235ud) map.get(paymentMethodType);
        return interfaceC0235ud == null ? paymentMethodType.getTitle$lib_release() : interfaceC0235ud;
    }
}
