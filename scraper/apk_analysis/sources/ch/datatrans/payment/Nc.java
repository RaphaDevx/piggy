package ch.datatrans.payment;

import ch.twint.payment.sdk.TwintPayResult;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class Nc {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[TwintPayResult.values().length];
        try {
            iArr[TwintPayResult.TW_B_SUCCESS.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[TwintPayResult.TW_B_ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[TwintPayResult.TW_B_APP_NOT_INSTALLED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
