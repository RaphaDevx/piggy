package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class C4 {
    public static final C4 a;
    public static final C4 b;
    public static final C4 c;
    public static final C4 d;
    public static final C4 e;
    public static final C4 f;
    public static final C4 g;
    public static final /* synthetic */ C4[] h;
    public static final C4 i;

    static {
        C4 c4 = new C4("SWISH", 0);
        a = c4;
        C4 c42 = new C4("VIPPS", 1);
        b = c42;
        C4 c43 = new C4("PAY_PAL", 2);
        c = c43;
        C4 c44 = new C4("KLARNA", 3);
        d = c44;
        C4 c45 = new C4("TWINT", 4);
        e = c45;
        C4 c46 = new C4("GOOGLE_PAY", 5);
        f = c46;
        C4 c47 = new C4("MOBILE_PAY", 6);
        g = c47;
        C4 c48 = new C4("POST_FINANCE_PAY", 7);
        i = c48;
        C4[] c4Arr = {c4, c42, c43, c44, c45, c46, c47, c48};
        h = c4Arr;
        EnumEntriesKt.enumEntries(c4Arr);
    }

    public C4(String str, int i2) {
    }

    public static C4 valueOf(String str) {
        return (C4) Enum.valueOf(C4.class, str);
    }

    public static C4[] values() {
        return (C4[]) h.clone();
    }
}
