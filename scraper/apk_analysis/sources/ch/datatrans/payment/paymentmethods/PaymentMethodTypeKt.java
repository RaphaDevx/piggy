package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.Q5;
import ch.datatrans.payment.exception.ModuleMissingException;
import ch.datatrans.payment.exception.PaymentMethodValidationException;
import ch.datatrans.payment.exception.TechnicalException;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a%\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "Lch/datatrans/payment/Q5;", RRWebOptionsEvent.EVENT_TAG, "", "isSavedPaymentMethod", "", "validateConfigurations", "(Lch/datatrans/payment/paymentmethods/PaymentMethodType;Lch/datatrans/payment/Q5;Z)V", "lib_release"}, k = 2, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class PaymentMethodTypeKt {
    public static final void validateConfigurations(PaymentMethodType paymentMethodType, Q5 options, boolean z) {
        String str;
        Intrinsics.checkNotNullParameter(paymentMethodType, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        Function0<Boolean> isModuleAvailable$lib_release = paymentMethodType.isModuleAvailable$lib_release();
        if (isModuleAvailable$lib_release != null && !isModuleAvailable$lib_release.invoke().booleanValue()) {
            throw new ModuleMissingException("Module required for payment method %s not found. Please refer to the SDK documentation.", paymentMethodType);
        }
        if (z && options.r && !paymentMethodType.isCreditCard$lib_release() && paymentMethodType != PaymentMethodType.GOOGLE_PAY && paymentMethodType != PaymentMethodType.HALF_FARE_PLUS && paymentMethodType != PaymentMethodType.POST_FINANCE_PAY) {
            throw new PaymentMethodValidationException("%s requires that the option authenticationOnly is not set (or false) when using a saved payment method.", paymentMethodType);
        }
        if (paymentMethodType.getRequiresAppCallbackScheme$lib_release() && ((str = options.a) == null || str.length() == 0)) {
            throw new PaymentMethodValidationException("%s requires option appCallbackScheme to be set. Please refer to the SDK documentation.", paymentMethodType);
        }
        if (paymentMethodType.getRequiresGooglePayConfig$lib_release() && options.i == null) {
            throw new PaymentMethodValidationException("%s requires option googlePayConfig to be set. Please refer to the SDK documentation.", paymentMethodType);
        }
        if (paymentMethodType.getRequiresSamsungPayConfig$lib_release() && options.o == null) {
            throw new PaymentMethodValidationException("%s requires option samsungPayConfig to be set. Please refer to the SDK documentation.", paymentMethodType);
        }
        String str2 = options.a;
        if (str2 != null && str2.length() != 0) {
            try {
                if (!Intrinsics.areEqual(URI.create(options.a + "://datatrans.ch").getScheme(), options.a)) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException unused) {
                throw new TechnicalException("appCallbackScheme is invalid.", null, null, null, 14, null);
            }
        }
        if (!options.l && !z && paymentMethodType == PaymentMethodType.HALF_FARE_PLUS) {
            throw new PaymentMethodValidationException("%s requires creates alias or alias payment.", paymentMethodType);
        }
    }

    public static /* synthetic */ void validateConfigurations$default(PaymentMethodType paymentMethodType, Q5 q5, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        validateConfigurations(paymentMethodType, q5, z);
    }
}
