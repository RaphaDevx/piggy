package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class J6 {
    public static final /* synthetic */ J6[] a;

    static {
        J6[] j6Arr = {new J6("VISA", 0), new J6("MASTERCARD", 1), new J6("DINERS", 2), new J6("AMEX", 3), new J6("JCB", 4), new J6("UATP", 5), new J6("DISCOVER", 6), new J6("SUPERCARD", 7), new J6("BONCARD", 8), new J6("PFEFINANCE", 9), new J6("PFCARD", 10), new J6("PAYPAL", 11), new J6("EASYPAY", 12), new J6("ELV", 13), new J6("SWISSBILLING", 14), new J6("REKA", 15), new J6("TWINT", 16), new J6("BYJUNO", 17), new J6("SAMSUNG_PAY", 18), new J6("GOOGLE_PAY", 19), new J6("SWISSPASS", 20), new J6("POWERPAY", 21), new J6("PAYSAFECARD", 22)};
        a = j6Arr;
        EnumEntriesKt.enumEntries(j6Arr);
    }

    public J6(String str, int i) {
    }

    public static J6 valueOf(String str) {
        return (J6) Enum.valueOf(J6.class, str);
    }

    public static J6[] values() {
        return (J6[]) a.clone();
    }
}
