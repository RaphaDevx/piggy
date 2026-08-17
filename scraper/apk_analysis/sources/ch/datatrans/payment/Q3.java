package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class Q3 {
    public static final Q3 a;
    public static final Q3 b;
    public static final Q3 c;
    public static final Q3 d;
    public static final /* synthetic */ Q3[] e;

    static {
        Q3 q3 = new Q3("CARD", 0);
        a = q3;
        Q3 q32 = new Q3("CARD_HIDDEN_MODE", 1);
        b = q32;
        Q3 q33 = new Q3("SAVED_PAYMENT", 2);
        c = q33;
        Q3 q34 = new Q3("SAVED_PAYMENT_HIDDEN_MODE", 3);
        d = q34;
        Q3[] q3Arr = {q3, q32, q33, q34};
        e = q3Arr;
        EnumEntriesKt.enumEntries(q3Arr);
    }

    public Q3(String str, int i) {
    }

    public static Q3 valueOf(String str) {
        return (Q3) Enum.valueOf(Q3.class, str);
    }

    public static Q3[] values() {
        return (Q3[]) e.clone();
    }
}
