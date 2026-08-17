package com.scandit.datacapture.barcode.internal.module.count.ui;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class q {
    public static final q a;
    public static final q b;
    public static final /* synthetic */ q[] c;

    static {
        q qVar = new q("UP", 0);
        a = qVar;
        q qVar2 = new q("DOWN", 1);
        b = qVar2;
        q[] qVarArr = {qVar, qVar2};
        c = qVarArr;
        EnumEntriesKt.enumEntries(qVarArr);
    }

    public q(String str, int i) {
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) c.clone();
    }
}
