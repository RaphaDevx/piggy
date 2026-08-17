package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class N {
    public static final N a;
    public static final N b;
    public static final N c;
    public static final /* synthetic */ N[] d;

    static {
        N n = new N("SCANNED", 0);
        a = n;
        N n2 = new N("UNSCANNED", 1);
        b = n2;
        N n3 = new N("FILTERED_OUT", 2);
        c = n3;
        N[] nArr = {n, n2, n3};
        d = nArr;
        EnumEntriesKt.enumEntries(nArr);
    }

    public N(String str, int i) {
    }

    public static N valueOf(String str) {
        return (N) Enum.valueOf(N.class, str);
    }

    public static N[] values() {
        return (N[]) d.clone();
    }
}
