package com.scandit.datacapture.barcode.internal.module.count.ui;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class l {
    public static final l a;
    public static final l b;
    public static final /* synthetic */ l[] c;

    static {
        l lVar = new l("FIXED", 0);
        a = lVar;
        l lVar2 = new l("FLOATING", 1);
        b = lVar2;
        l[] lVarArr = {lVar, lVar2};
        c = lVarArr;
        EnumEntriesKt.enumEntries(lVarArr);
    }

    public l(String str, int i) {
    }

    public static l valueOf(String str) {
        return (l) Enum.valueOf(l.class, str);
    }

    public static l[] values() {
        return (l[]) c.clone();
    }
}
