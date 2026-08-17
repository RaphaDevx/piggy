package com.salesforce.marketingcloud.toggles;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes2.dex */
public final class a {
    public static final a b = new a("UNKNOWN", 0);
    public static final a c = new a("ENABLED", 1);
    public static final a d = new a("DISABLED", 2);
    private static final /* synthetic */ a[] e;
    private static final /* synthetic */ EnumEntries f;

    static {
        a[] a = a();
        e = a;
        f = EnumEntriesKt.enumEntries(a);
    }

    private a(String str, int i) {
    }

    private static final /* synthetic */ a[] a() {
        return new a[]{b, c, d};
    }

    public static EnumEntries<a> b() {
        return f;
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) e.clone();
    }
}
