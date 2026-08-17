package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;

/* renamed from: ch.datatrans.payment.b, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract /* synthetic */ class AbstractC0114b {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PaymentMethodType.values().length];
        try {
            iArr[PaymentMethodType.CEMBRA_PAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PaymentMethodType.SWISS_BILLING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PaymentMethodType.IDEAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PaymentMethodType.SWISS_PASS.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PaymentMethodType.POWERPAY.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[PaymentMethodType.PAYCARD.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        a = iArr;
    }
}
