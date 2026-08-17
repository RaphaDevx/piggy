package com.scandit.datacapture.barcode.internal.module.ui.popover;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: com.scandit.datacapture.barcode.internal.module.ui.popover.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class EnumC0494p {
    public static final EnumC0494p a;
    public static final EnumC0494p b;
    public static final EnumC0494p c;
    public static final EnumC0494p d;
    public static final /* synthetic */ EnumC0494p[] e;

    static {
        EnumC0494p enumC0494p = new EnumC0494p("VERTICAL_BELOW", 0);
        a = enumC0494p;
        EnumC0494p enumC0494p2 = new EnumC0494p("VERTICAL_ABOVE", 1);
        b = enumC0494p2;
        EnumC0494p enumC0494p3 = new EnumC0494p("HORIZONTAL_LEFT", 2);
        c = enumC0494p3;
        EnumC0494p enumC0494p4 = new EnumC0494p("HORIZONTAL_RIGHT", 3);
        d = enumC0494p4;
        EnumC0494p[] enumC0494pArr = {enumC0494p, enumC0494p2, enumC0494p3, enumC0494p4};
        e = enumC0494pArr;
        EnumEntriesKt.enumEntries(enumC0494pArr);
    }

    public EnumC0494p(String str, int i) {
    }

    public static EnumC0494p valueOf(String str) {
        return (EnumC0494p) Enum.valueOf(EnumC0494p.class, str);
    }

    public static EnumC0494p[] values() {
        return (EnumC0494p[]) e.clone();
    }
}
