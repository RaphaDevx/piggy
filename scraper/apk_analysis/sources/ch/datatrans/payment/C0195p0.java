package ch.datatrans.payment;

import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;

/* renamed from: ch.datatrans.payment.p0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0195p0 {
    public final String a;
    public final Boolean b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final Boolean g;
    public final Integer h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final List n;
    public final Integer o;

    public C0195p0(String str, Boolean bool, String str2, String str3, String str4, String str5, Boolean bool2, Integer num, String str6, String str7, String str8, String str9, String str10, List list, Integer num2) {
        this.a = str;
        this.b = bool;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = bool2;
        this.h = num;
        this.i = str6;
        this.j = str7;
        this.k = str8;
        this.l = str9;
        this.m = str10;
        this.n = list;
        this.o = num2;
    }

    public final Map a() {
        String str;
        Map createMapBuilder = MapsKt.createMapBuilder();
        EnumC0183n0 enumC0183n0 = EnumC0183n0.d;
        x4.a(createMapBuilder, "sub_pmethod", this.a);
        EnumC0183n0 enumC0183n02 = EnumC0183n0.d;
        Boolean bool = this.b;
        if (bool == null) {
            str = null;
        } else if (bool.booleanValue()) {
            str = enumC0183n02.b;
            if (str == null) {
                str = "true";
            }
        } else {
            str = enumC0183n02.c;
            if (str == null) {
                str = BooleanUtils.FALSE;
            }
        }
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumCustomerEmailConfirmed", "key");
        if (str != null) {
            createMapBuilder.put("intrumCustomerEmailConfirmed", str);
        }
        String str2 = this.c;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumCustomerInfo1", "key");
        if (str2 != null) {
            createMapBuilder.put("intrumCustomerInfo1", str2);
        }
        String str3 = this.d;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumCustomerInfo2", "key");
        if (str3 != null) {
            createMapBuilder.put("intrumCustomerInfo2", str3);
        }
        String str4 = this.e;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumDeliveryMethod", "key");
        if (str4 != null) {
            createMapBuilder.put("intrumDeliveryMethod", str4);
        }
        String str5 = this.f;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumDeviceFingerprintId", "key");
        if (str5 != null) {
            createMapBuilder.put("intrumDeviceFingerprintId", str5);
        }
        EnumC0183n0 enumC0183n03 = EnumC0183n0.d;
        Boolean bool2 = this.g;
        String str6 = bool2 != null ? bool2.booleanValue() ? "YES" : "NO" : null;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumPaperInvoice", "key");
        if (str6 != null) {
            createMapBuilder.put("intrumPaperInvoice", str6);
        }
        Integer num = this.h;
        String num2 = num != null ? num.toString() : null;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumRepaymentType", "key");
        if (num2 != null) {
            createMapBuilder.put("intrumRepaymentType", num2);
        }
        String str7 = this.i;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumRiskOwner", "key");
        if (str7 != null) {
            createMapBuilder.put("intrumRiskOwner", str7);
        }
        String str8 = this.j;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumSiteID", "key");
        if (str8 != null) {
            createMapBuilder.put("intrumSiteID", str8);
        }
        String str9 = this.k;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumVerifiedDocument1Type", "key");
        if (str9 != null) {
            createMapBuilder.put("intrumVerifiedDocument1Type", str9);
        }
        String str10 = this.l;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumVerifiedDocument1Number", "key");
        if (str10 != null) {
            createMapBuilder.put("intrumVerifiedDocument1Number", str10);
        }
        String str11 = this.m;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumVerifiedDocument1Issuer", "key");
        if (str11 != null) {
            createMapBuilder.put("intrumVerifiedDocument1Issuer", str11);
        }
        List list = this.n;
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str12 = (String) obj;
                StringBuilder sb = new StringBuilder("intrumCustomData_");
                EnumC0183n0 enumC0183n04 = EnumC0183n0.d;
                sb.append(i2);
                sb.append('_');
                String key = sb.toString();
                Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
                Intrinsics.checkNotNullParameter(key, "key");
                if (str12 != null) {
                    createMapBuilder.put(key, str12);
                }
                i = i2;
            }
        }
        EnumC0183n0 enumC0183n05 = EnumC0183n0.d;
        Integer num3 = this.o;
        String num4 = num3 != null ? num3.toString() : null;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("intrumFirstRateAmount", "key");
        if (num4 != null) {
            createMapBuilder.put("intrumFirstRateAmount", num4);
        }
        return MapsKt.build(createMapBuilder);
    }
}
