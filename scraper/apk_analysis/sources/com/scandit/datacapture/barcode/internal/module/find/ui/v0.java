package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class v0 {
    public static final v0 a;
    public static final v0 b;
    public static final v0 c;
    public static final /* synthetic */ v0[] d;

    static {
        v0 v0Var = new v0("Started", 0);
        a = v0Var;
        v0 v0Var2 = new v0("Paused", 1);
        b = v0Var2;
        v0 v0Var3 = new v0("Stopped", 2);
        c = v0Var3;
        v0[] v0VarArr = {v0Var, v0Var2, v0Var3};
        d = v0VarArr;
        EnumEntriesKt.enumEntries(v0VarArr);
    }

    public v0(String str, int i) {
    }

    public static v0 valueOf(String str) {
        return (v0) Enum.valueOf(v0.class, str);
    }

    public static v0[] values() {
        return (v0[]) d.clone();
    }
}
