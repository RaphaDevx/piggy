package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: ch.datatrans.payment.h0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class EnumC0152h0 {
    public static final EnumC0152h0 a;
    public static final EnumC0152h0 b;
    public static final EnumC0152h0 c;
    public static final /* synthetic */ EnumC0152h0[] d;

    static {
        EnumC0152h0 enumC0152h0 = new EnumC0152h0("CARD_NUMBER", 0);
        a = enumC0152h0;
        EnumC0152h0 enumC0152h02 = new EnumC0152h0("DATE_CVV", 1);
        b = enumC0152h02;
        EnumC0152h0 enumC0152h03 = new EnumC0152h0("DCC", 2);
        c = enumC0152h03;
        EnumC0152h0[] enumC0152h0Arr = {enumC0152h0, enumC0152h02, enumC0152h03};
        d = enumC0152h0Arr;
        EnumEntriesKt.enumEntries(enumC0152h0Arr);
    }

    public EnumC0152h0(String str, int i) {
    }

    public static EnumC0152h0 valueOf(String str) {
        return (EnumC0152h0) Enum.valueOf(EnumC0152h0.class, str);
    }

    public static EnumC0152h0[] values() {
        return (EnumC0152h0[]) d.clone();
    }
}
