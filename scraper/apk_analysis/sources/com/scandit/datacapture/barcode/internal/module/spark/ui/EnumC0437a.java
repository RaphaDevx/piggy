package com.scandit.datacapture.barcode.internal.module.spark.ui;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class EnumC0437a {
    public static final EnumC0437a a;
    public static final EnumC0437a b;
    public static final EnumC0437a c;
    public static final EnumC0437a d;
    public static final EnumC0437a e;
    public static final EnumC0437a f;
    public static final EnumC0437a g;
    public static final EnumC0437a h;
    public static final EnumC0437a i;
    public static final EnumC0437a j;
    public static final EnumC0437a k;
    public static final /* synthetic */ EnumC0437a[] l;

    static {
        EnumC0437a enumC0437a = new EnumC0437a("TOUCH_DOWN", 0);
        a = enumC0437a;
        EnumC0437a enumC0437a2 = new EnumC0437a("CLICK", 1);
        b = enumC0437a2;
        EnumC0437a enumC0437a3 = new EnumC0437a("DRAG_UP", 2);
        c = enumC0437a3;
        EnumC0437a enumC0437a4 = new EnumC0437a("DRAG_RIGHT", 3);
        d = enumC0437a4;
        EnumC0437a enumC0437a5 = new EnumC0437a("DRAG_DOWN", 4);
        e = enumC0437a5;
        EnumC0437a enumC0437a6 = new EnumC0437a("DRAG_LEFT", 5);
        f = enumC0437a6;
        EnumC0437a enumC0437a7 = new EnumC0437a("LONG_PRESS_STARTED", 6);
        g = enumC0437a7;
        EnumC0437a enumC0437a8 = new EnumC0437a("LONG_PRESS_FINISHED", 7);
        h = enumC0437a8;
        EnumC0437a enumC0437a9 = new EnumC0437a("MOVE_STARTED", 8);
        i = enumC0437a9;
        EnumC0437a enumC0437a10 = new EnumC0437a("MOVE_FINISHED", 9);
        j = enumC0437a10;
        EnumC0437a enumC0437a11 = new EnumC0437a("TOUCH_UP", 10);
        k = enumC0437a11;
        EnumC0437a[] enumC0437aArr = {enumC0437a, enumC0437a2, enumC0437a3, enumC0437a4, enumC0437a5, enumC0437a6, enumC0437a7, enumC0437a8, enumC0437a9, enumC0437a10, enumC0437a11};
        l = enumC0437aArr;
        EnumEntriesKt.enumEntries(enumC0437aArr);
    }

    public EnumC0437a(String str, int i2) {
    }

    public static EnumC0437a valueOf(String str) {
        return (EnumC0437a) Enum.valueOf(EnumC0437a.class, str);
    }

    public static EnumC0437a[] values() {
        return (EnumC0437a[]) l.clone();
    }
}
