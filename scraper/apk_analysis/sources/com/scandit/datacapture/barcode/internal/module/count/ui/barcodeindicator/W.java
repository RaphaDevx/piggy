package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class W {
    public static final W b;
    public static final W c;
    public static final W d;
    public static final W e;
    public static final W f;
    public static final W g;
    public static final /* synthetic */ W[] h;
    public final int a;

    static {
        W w = new W("Recognized", 0, 0);
        b = w;
        W w2 = new W("Accepted", 1, 1);
        c = w2;
        W w3 = new W("Rejected", 2, 2);
        d = w3;
        W w4 = new W("NotInList", 3, 3);
        e = w4;
        W w5 = new W("UnScanned", 4, 4);
        f = w5;
        W w6 = new W("FilteredOut", 5, 5);
        g = w6;
        W[] wArr = {w, w2, w3, w4, w5, w6};
        h = wArr;
        EnumEntriesKt.enumEntries(wArr);
    }

    public W(String str, int i, int i2) {
        this.a = i2;
    }

    public static W valueOf(String str) {
        return (W) Enum.valueOf(W.class, str);
    }

    public static W[] values() {
        return (W[]) h.clone();
    }
}
