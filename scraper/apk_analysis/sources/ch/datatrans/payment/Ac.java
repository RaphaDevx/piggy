package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class Ac {
    public static final Ac a;
    public static final Ac b;
    public static final Ac c;
    public static final Ac d;
    public static final Ac e;
    public static final Ac f;
    public static final Ac g;
    public static final Ac h;
    public static final Ac i;
    public static final Ac j;
    public static final Ac k;
    public static final /* synthetic */ Ac[] l;
    public static final Ac m;

    static {
        Ac ac = new Ac("PAYMENT_METHOD_ALIAS_SELECTION", 0);
        a = ac;
        Ac ac2 = new Ac("PAYMENT_METHOD_SELECTION", 1);
        b = ac2;
        Ac ac3 = new Ac("CREDIT_CARD", 2);
        c = ac3;
        Ac ac4 = new Ac("PAYPAL", 3);
        d = ac4;
        Ac ac5 = new Ac("GOOGLE_PAY", 4);
        e = ac5;
        Ac ac6 = new Ac("SAMSUNG_PAY", 5);
        f = ac6;
        Ac ac7 = new Ac("TWINT", 6);
        g = ac7;
        Ac ac8 = new Ac("KLARNA", 7);
        h = ac8;
        Ac ac9 = new Ac("SWISH", 8);
        i = ac9;
        Ac ac10 = new Ac("VIPPS", 9);
        j = ac10;
        Ac ac11 = new Ac("MOBILE_PAY", 10);
        k = ac11;
        Ac ac12 = new Ac("POST_FINANCE_PAY", 11);
        m = ac12;
        Ac[] acArr = {ac, ac2, ac3, ac4, ac5, ac6, ac7, ac8, ac9, ac10, ac11, ac12};
        l = acArr;
        EnumEntriesKt.enumEntries(acArr);
    }

    public Ac(String str, int i2) {
    }

    public static Ac valueOf(String str) {
        return (Ac) Enum.valueOf(Ac.class, str);
    }

    public static Ac[] values() {
        return (Ac[]) l.clone();
    }
}
