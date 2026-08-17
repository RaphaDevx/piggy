package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class J {
    public static final J a;
    public static final J b;
    public static final J c;
    public static final /* synthetic */ J[] d;

    static {
        J j = new J("ShowStatusView", 0);
        a = j;
        J j2 = new J("ShowNormalView", 1);
        b = j2;
        J j3 = new J("ShowNothing", 2);
        c = j3;
        J[] jArr = {j, j2, j3};
        d = jArr;
        EnumEntriesKt.enumEntries(jArr);
    }

    public J(String str, int i) {
    }

    public static J valueOf(String str) {
        return (J) Enum.valueOf(J.class, str);
    }

    public static J[] values() {
        return (J[]) d.clone();
    }
}
