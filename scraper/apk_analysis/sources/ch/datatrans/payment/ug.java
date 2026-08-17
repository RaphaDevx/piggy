package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class ug {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PaymentMethodType.values().length];
        try {
            iArr[PaymentMethodType.GOOGLE_PAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PaymentMethodType.SAMSUNG_PAY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
