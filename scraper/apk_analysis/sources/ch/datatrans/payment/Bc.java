package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class Bc {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PaymentMethodType.values().length];
        try {
            iArr[PaymentMethodType.PAY_PAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PaymentMethodType.GOOGLE_PAY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PaymentMethodType.SAMSUNG_PAY.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PaymentMethodType.TWINT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PaymentMethodType.KLARNA.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[PaymentMethodType.SWISH.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[PaymentMethodType.VIPPS.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[PaymentMethodType.MOBILE_PAY.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr[PaymentMethodType.POST_FINANCE_PAY.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr[PaymentMethodType.IDEAL.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        a = iArr;
    }
}
