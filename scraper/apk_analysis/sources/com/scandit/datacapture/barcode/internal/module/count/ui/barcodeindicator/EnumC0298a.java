package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class EnumC0298a {
    public static final EnumC0298a a;
    public static final EnumC0298a b;
    public static final EnumC0298a c;
    public static final /* synthetic */ EnumC0298a[] d;

    static {
        EnumC0298a enumC0298a = new EnumC0298a("BRUSH", 0);
        a = enumC0298a;
        EnumC0298a enumC0298a2 = new EnumC0298a("RESOURCE", 1);
        b = enumC0298a2;
        EnumC0298a enumC0298a3 = new EnumC0298a("BRUSHABLE_RESOURCE", 2);
        c = enumC0298a3;
        EnumC0298a[] enumC0298aArr = {enumC0298a, enumC0298a2, enumC0298a3};
        d = enumC0298aArr;
        EnumEntriesKt.enumEntries(enumC0298aArr);
    }

    public EnumC0298a(String str, int i) {
    }

    public static EnumC0298a valueOf(String str) {
        return (EnumC0298a) Enum.valueOf(EnumC0298a.class, str);
    }

    public static EnumC0298a[] values() {
        return (EnumC0298a[]) d.clone();
    }
}
