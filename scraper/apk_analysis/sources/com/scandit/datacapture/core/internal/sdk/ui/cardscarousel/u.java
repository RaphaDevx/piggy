package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class u {
    public static final u a;
    public static final u b;
    public static final u c;
    public static final u d;
    public static final u e;
    public static final u f;
    public static final u g;
    public static final u h;
    public static final /* synthetic */ u[] i;
    public static final /* synthetic */ EnumEntries j;

    static {
        u uVar = new u("TOP_LEFT", 0);
        a = uVar;
        u uVar2 = new u("TOP", 1);
        b = uVar2;
        u uVar3 = new u("TOP_RIGHT", 2);
        c = uVar3;
        u uVar4 = new u("RIGHT", 3);
        d = uVar4;
        u uVar5 = new u("BOTTOM_RIGHT", 4);
        e = uVar5;
        u uVar6 = new u("BOTTOM", 5);
        f = uVar6;
        u uVar7 = new u("BOTTOM_LEFT", 6);
        g = uVar7;
        u uVar8 = new u("LEFT", 7);
        h = uVar8;
        u[] uVarArr = {uVar, uVar2, uVar3, uVar4, uVar5, uVar6, uVar7, uVar8};
        i = uVarArr;
        j = EnumEntriesKt.enumEntries(uVarArr);
    }

    public u(String str, int i2) {
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) i.clone();
    }
}
