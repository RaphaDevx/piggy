package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class M {
    public static final M a;
    public static final M b;
    public static final M c;
    public static final /* synthetic */ M[] d;

    static {
        M m = new M("NOT_USING_LIST", 0);
        a = m;
        M m2 = new M("IN_LIST", 1);
        b = m2;
        M m3 = new M("NOT_IN_LIST", 2);
        c = m3;
        M[] mArr = {m, m2, m3};
        d = mArr;
        EnumEntriesKt.enumEntries(mArr);
    }

    public M(String str, int i) {
    }

    public static M valueOf(String str) {
        return (M) Enum.valueOf(M.class, str);
    }

    public static M[] values() {
        return (M[]) d.clone();
    }
}
