package ch.datatrans.payment;

import ch.datatrans.payment.api.DCCShowMode;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class O3 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[DCCShowMode.values().length];
        try {
            iArr[DCCShowMode.ALWAYS.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[DCCShowMode.NEVER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[DCCShowMode.SMART.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
