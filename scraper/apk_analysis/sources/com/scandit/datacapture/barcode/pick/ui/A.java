package com.scandit.datacapture.barcode.pick.ui;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class A {
    public static final A a;
    public static final A b;
    public static final A c;
    public static final A d;
    public static final /* synthetic */ A[] e;

    static {
        A a2 = new A("NONE", 0);
        a = a2;
        A a3 = new A("BITMAP", 1);
        b = a3;
        A a4 = new A("RESOURCE", 2);
        c = a4;
        A a5 = new A("SCANDIT", 3);
        d = a5;
        A[] aArr = {a2, a3, a4, a5};
        e = aArr;
        EnumEntriesKt.enumEntries(aArr);
    }

    public A(String str, int i) {
    }

    public static A valueOf(String str) {
        return (A) Enum.valueOf(A.class, str);
    }

    public static A[] values() {
        return (A[]) e.clone();
    }
}
