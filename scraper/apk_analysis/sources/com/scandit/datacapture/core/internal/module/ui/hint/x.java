package com.scandit.datacapture.core.internal.module.ui.hint;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class x {
    public static final x a;
    public static final x b;
    public static final /* synthetic */ x[] c;

    static {
        x xVar = new x("IDLE", 0);
        a = xVar;
        x xVar2 = new x("EXECUTING", 1);
        b = xVar2;
        x[] xVarArr = {xVar, xVar2};
        c = xVarArr;
        EnumEntriesKt.enumEntries(xVarArr);
    }

    public x(String str, int i) {
    }

    public static x valueOf(String str) {
        return (x) Enum.valueOf(x.class, str);
    }

    public static x[] values() {
        return (x[]) c.clone();
    }
}
