package com.scandit.datacapture.core.internal.module.https;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class a {
    public static final a a;
    public static final a b;
    public static final a c;
    public static final /* synthetic */ a[] d;

    static {
        a aVar = new a("NONE", 0);
        a = aVar;
        a aVar2 = new a("CELLULAR", 1);
        b = aVar2;
        a aVar3 = new a("UNRESTRICTED", 2);
        c = aVar3;
        a[] aVarArr = {aVar, aVar2, aVar3};
        d = aVarArr;
        EnumEntriesKt.enumEntries(aVarArr);
    }

    public a(String str, int i) {
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) d.clone();
    }
}
