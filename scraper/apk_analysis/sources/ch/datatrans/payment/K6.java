package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes3.dex */
public final class K6 {
    public static final K6 a;
    public static final K6 b;
    public static final K6 c;
    public static final K6 d;
    public static final /* synthetic */ K6[] e;

    static {
        K6 k6 = new K6("STEP0", 0);
        a = k6;
        K6 k62 = new K6("STEP1", 1);
        b = k62;
        K6 k63 = new K6("STEP2", 2);
        c = k63;
        K6 k64 = new K6("STEP3", 3);
        d = k64;
        K6[] k6Arr = {k6, k62, k63, k64};
        e = k6Arr;
        EnumEntriesKt.enumEntries(k6Arr);
    }

    public K6(String str, int i) {
    }

    public static K6 valueOf(String str) {
        return (K6) Enum.valueOf(K6.class, str);
    }

    public static K6[] values() {
        return (K6[]) e.clone();
    }
}
