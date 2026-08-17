package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class a1 {
    public static final a1 a;
    public static final a1 b;
    public static final a1 c;
    public static final a1 d;
    public static final /* synthetic */ a1[] e;

    static {
        a1 a1Var = new a1("NOT_KNOWN", 0);
        a = a1Var;
        a1 a1Var2 = new a1("NEEDED", 1);
        b = a1Var2;
        a1 a1Var3 = new a1("NOT_NEEDED", 2);
        c = a1Var3;
        a1 a1Var4 = new a1("ERROR", 3);
        d = a1Var4;
        a1[] a1VarArr = {a1Var, a1Var2, a1Var3, a1Var4};
        e = a1VarArr;
        EnumEntriesKt.enumEntries(a1VarArr);
    }

    public a1(String str, int i) {
    }

    public static a1 valueOf(String str) {
        return (a1) Enum.valueOf(a1.class, str);
    }

    public static a1[] values() {
        return (a1[]) e.clone();
    }
}
