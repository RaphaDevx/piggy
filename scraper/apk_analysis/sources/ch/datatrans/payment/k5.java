package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class k5 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PaymentMethodType.values().length];
        try {
            iArr[PaymentMethodType.VISA.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PaymentMethodType.MASTER_CARD.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PaymentMethodType.AMERICAN_EXPRESS.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PaymentMethodType.JCB.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PaymentMethodType.DISCOVER.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        a = iArr;
    }
}
