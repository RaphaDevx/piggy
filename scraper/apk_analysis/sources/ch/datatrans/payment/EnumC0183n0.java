package ch.datatrans.payment;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: ch.datatrans.payment.n0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class EnumC0183n0 {
    public static final EnumC0183n0 d;
    public static final /* synthetic */ EnumC0183n0[] e;
    public final String a;
    public final String b;
    public final String c;

    static {
        EnumC0183n0 enumC0183n0 = new EnumC0183n0("SUBTYPE", 0, "sub_pmethod");
        EnumC0183n0 enumC0183n02 = new EnumC0183n0("CUSTOMER_EMAIL_CONFIRMED", 1, "intrumCustomerEmailConfirmed");
        d = enumC0183n02;
        EnumC0183n0[] enumC0183n0Arr = {enumC0183n0, enumC0183n02, new EnumC0183n0("CUSTOMER_INFO_1", 2, "intrumCustomerInfo1"), new EnumC0183n0("CUSTOMER_INFO_2", 3, "intrumCustomerInfo2"), new EnumC0183n0("DELIVERY_METHOD", 4, "intrumDeliveryMethod"), new EnumC0183n0("DEVICE_FINGERPRINT_ID", 5, "intrumDeviceFingerprintId"), new EnumC0183n0(), new EnumC0183n0("REPAYMENT_TYPE", 7, "intrumRepaymentType"), new EnumC0183n0("RISK_OWNER", 8, "intrumRiskOwner"), new EnumC0183n0("SITE_ID", 9, "intrumSiteID"), new EnumC0183n0("VERIFIED_DOCUMENT_TYPE", 10, "intrumVerifiedDocument1Type"), new EnumC0183n0("VERIFIED_DOCUMENT_NUMBER", 11, "intrumVerifiedDocument1Number"), new EnumC0183n0("VERIFIED_DOCUMENT_ISSUER", 12, "intrumVerifiedDocument1Issuer"), new EnumC0183n0("CUSTOM_DATA", 13, "intrumCustomData"), new EnumC0183n0("FIRST_RATE_AMOUNT", 14, "intrumFirstRateAmount")};
        e = enumC0183n0Arr;
        EnumEntriesKt.enumEntries(enumC0183n0Arr);
    }

    public EnumC0183n0() {
        this.a = "intrumPaperInvoice";
        this.b = "YES";
        this.c = "NO";
    }

    public static EnumC0183n0 valueOf(String str) {
        return (EnumC0183n0) Enum.valueOf(EnumC0183n0.class, str);
    }

    public static EnumC0183n0[] values() {
        return (EnumC0183n0[]) e.clone();
    }

    public EnumC0183n0(String str, int i, String str2) {
        this.a = str2;
        this.b = null;
        this.c = null;
    }
}
