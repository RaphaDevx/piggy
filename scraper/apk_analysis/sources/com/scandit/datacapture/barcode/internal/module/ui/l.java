package com.scandit.datacapture.barcode.internal.module.ui;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class l {
    public static final l a;
    public static final l b;
    public static final l c;
    public static final l d;
    public static final /* synthetic */ l[] e;

    static {
        l lVar = new l("Hidden", 0);
        a = lVar;
        l lVar2 = new l("MoveAway", 1);
        b = lVar2;
        l lVar3 = new l("MoveCloser", 2);
        c = lVar3;
        l lVar4 = new l("TextOnly", 3);
        d = lVar4;
        l[] lVarArr = {lVar, lVar2, lVar3, lVar4};
        e = lVarArr;
        EnumEntriesKt.enumEntries(lVarArr);
    }

    public l(String str, int i) {
    }

    public static l valueOf(String str) {
        return (l) Enum.valueOf(l.class, str);
    }

    public static l[] values() {
        return (l[]) e.clone();
    }
}
