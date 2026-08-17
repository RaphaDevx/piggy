package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class X9 {
    public static final W9 b;
    public static final X9 c;
    public static final X9 d;
    public static final /* synthetic */ X9[] e;
    public final String a;

    static {
        X9 x9 = new X9("AUTO_SETTLEMENT", 0, "CAA");
        X9 x92 = new X9("NORMAL", 1, "NOA");
        c = x92;
        X9 x93 = new X9("SPLIT", 2, "SPL");
        d = x93;
        X9[] x9Arr = {x9, x92, x93};
        e = x9Arr;
        EnumEntriesKt.enumEntries(x9Arr);
        b = new W9();
    }

    public X9(String str, int i, String str2) {
        this.a = str2;
    }

    public static X9 valueOf(String str) {
        return (X9) Enum.valueOf(X9.class, str);
    }

    public static X9[] values() {
        return (X9[]) e.clone();
    }
}
