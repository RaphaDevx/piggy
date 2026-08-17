package com.scandit.datacapture.barcode.internal.module.find.ui.overlay;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class e {
    public static final e a;
    public static final e b;
    public static final e c;
    public static final e d;
    public static final /* synthetic */ e[] e;

    static {
        e eVar = new e("INITIAL", 0);
        a = eVar;
        e eVar2 = new e("MOVE_CLOSER", 1);
        b = eVar2;
        e eVar3 = new e("TAP_TO_PAUSE", 2);
        c = eVar3;
        e eVar4 = new e("TAP_TO_RESUME", 3);
        d = eVar4;
        e[] eVarArr = {eVar, eVar2, eVar3, eVar4};
        e = eVarArr;
        EnumEntriesKt.enumEntries(eVarArr);
    }

    public e(String str, int i) {
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) e.clone();
    }
}
