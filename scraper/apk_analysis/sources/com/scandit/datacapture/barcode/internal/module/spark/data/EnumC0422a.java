package com.scandit.datacapture.barcode.internal.module.spark.data;

import io.sentry.SentryBaseEvent;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class EnumC0422a {
    public static final EnumC0422a b;
    public static final EnumC0422a c;
    public static final /* synthetic */ EnumC0422a[] d;
    public final String a;

    static {
        EnumC0422a enumC0422a = new EnumC0422a("USER", 0, SentryBaseEvent.JsonKeys.USER);
        b = enumC0422a;
        EnumC0422a enumC0422a2 = new EnumC0422a("TIMEOUT", 1, "timeout");
        c = enumC0422a2;
        EnumC0422a[] enumC0422aArr = {enumC0422a, enumC0422a2};
        d = enumC0422aArr;
        EnumEntriesKt.enumEntries(enumC0422aArr);
    }

    public EnumC0422a(String str, int i, String str2) {
        this.a = str2;
    }

    public static EnumC0422a valueOf(String str) {
        return (EnumC0422a) Enum.valueOf(EnumC0422a.class, str);
    }

    public static EnumC0422a[] values() {
        return (EnumC0422a[]) d.clone();
    }
}
