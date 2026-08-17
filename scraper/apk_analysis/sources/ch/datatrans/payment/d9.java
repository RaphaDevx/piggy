package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class d9 {
    public static final d9 a;
    public static final d9 b;
    public static final d9 c;
    public static final d9 d;
    public static final d9 e;
    public static final /* synthetic */ d9[] f;

    static {
        d9 d9Var = new d9("EXTERNAL_WEB_PROCESS", 0);
        a = d9Var;
        d9 d9Var2 = new d9("CREDIT_CARD", 1);
        b = d9Var2;
        d9 d9Var3 = new d9("REQUIRES_APP_CALLBACK_SCHEME", 2);
        c = d9Var3;
        d9 d9Var4 = new d9("REQUIRES_GOOGLE_PAY_CONFIG", 3);
        d = d9Var4;
        d9 d9Var5 = new d9("REQUIRES_SAMSUNG_PAY_CONFIG", 4);
        e = d9Var5;
        d9[] d9VarArr = {d9Var, d9Var2, d9Var3, d9Var4, d9Var5, new d9("REQUIRES_EXTRA_AUTHORIZATION", 5)};
        f = d9VarArr;
        EnumEntriesKt.enumEntries(d9VarArr);
    }

    public d9(String str, int i) {
    }

    public static d9 valueOf(String str) {
        return (d9) Enum.valueOf(d9.class, str);
    }

    public static d9[] values() {
        return (d9[]) f.clone();
    }
}
