package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lch/datatrans/payment/exception/ModuleMissingException;", "Lch/datatrans/payment/exception/TechnicalException;", "", "missingModuleFormat", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodType", "<init>", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/PaymentMethodType;)V", "d", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getPaymentMethodType", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class ModuleMissingException extends TechnicalException {

    /* renamed from: d, reason: from kotlin metadata */
    public final PaymentMethodType paymentMethodType;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ModuleMissingException(java.lang.String r9, ch.datatrans.payment.paymentmethods.PaymentMethodType r10) {
        /*
            r8 = this;
            java.lang.String r0 = "missingModuleFormat"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "paymentMethodType"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            kotlin.jvm.internal.StringCompanionObject r0 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r0 = r10.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            r1 = 1
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r1)
            java.lang.String r2 = java.lang.String.format(r9, r0)
            java.lang.String r9 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r9)
            r6 = 10
            r7 = 0
            r3 = 0
            r5 = 0
            r1 = r8
            r4 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.paymentMethodType = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.exception.ModuleMissingException.<init>(java.lang.String, ch.datatrans.payment.paymentmethods.PaymentMethodType):void");
    }

    @Override // ch.datatrans.payment.exception.TransactionException
    public PaymentMethodType getPaymentMethodType() {
        return this.paymentMethodType;
    }
}
