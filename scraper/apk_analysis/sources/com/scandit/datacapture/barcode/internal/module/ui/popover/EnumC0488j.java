package com.scandit.datacapture.barcode.internal.module.ui.popover;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: com.scandit.datacapture.barcode.internal.module.ui.popover.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class EnumC0488j {
    public static final EnumC0488j a;
    public static final EnumC0488j b;
    public static final /* synthetic */ EnumC0488j[] c;

    static {
        EnumC0488j enumC0488j = new EnumC0488j("HORIZONTAL", 0);
        a = enumC0488j;
        EnumC0488j enumC0488j2 = new EnumC0488j("VERTICAL", 1);
        b = enumC0488j2;
        EnumC0488j[] enumC0488jArr = {enumC0488j, enumC0488j2};
        c = enumC0488jArr;
        EnumEntriesKt.enumEntries(enumC0488jArr);
    }

    public EnumC0488j(String str, int i) {
    }

    public static EnumC0488j valueOf(String str) {
        return (EnumC0488j) Enum.valueOf(EnumC0488j.class, str);
    }

    public static EnumC0488j[] values() {
        return (EnumC0488j[]) c.clone();
    }
}
