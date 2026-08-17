package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class n {
    public static final n a;
    public static final n b;
    public static final /* synthetic */ n[] c;

    static {
        n nVar = new n("OnDemand", 0);
        a = nVar;
        n nVar2 = new n("Always", 1);
        b = nVar2;
        n[] nVarArr = {nVar, nVar2};
        c = nVarArr;
        EnumEntriesKt.enumEntries(nVarArr);
    }

    public n(String str, int i) {
    }

    public static n valueOf(String str) {
        return (n) Enum.valueOf(n.class, str);
    }

    public static n[] values() {
        return (n[]) c.clone();
    }
}
