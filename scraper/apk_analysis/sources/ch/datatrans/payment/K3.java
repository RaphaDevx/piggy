package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes3.dex */
public final class K3 {
    public final C0116c a;
    public final C0158i4 b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;

    public K3(C0116c c0116c, C0158i4 c0158i4, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.a = c0116c;
        this.b = c0158i4;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = str9;
        this.l = str10;
        this.m = str11;
    }

    public final Map a() {
        Map createMapBuilder = MapsKt.createMapBuilder();
        C0116c c0116c = this.a;
        if (c0116c != null) {
            c0116c.a("uppCustomer", createMapBuilder);
        }
        C0158i4 c0158i4 = this.b;
        x4.a(createMapBuilder, "uppCustomerBirthDate", c0158i4 != null ? c0158i4.a : null);
        String str = this.c;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerCellPhone", "key");
        if (str != null) {
            createMapBuilder.put("uppCustomerCellPhone", str);
        }
        String str2 = this.d;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerCompanyRegisterNumber", "key");
        if (str2 != null) {
            createMapBuilder.put("uppCustomerCompanyRegisterNumber", str2);
        }
        String str3 = this.e;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerEmail", "key");
        if (str3 != null) {
            createMapBuilder.put("uppCustomerEmail", str3);
        }
        String str4 = this.f;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerFax", "key");
        if (str4 != null) {
            createMapBuilder.put("uppCustomerFax", str4);
        }
        String str5 = this.g;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerGender", "key");
        if (str5 != null) {
            createMapBuilder.put("uppCustomerGender", str5);
        }
        String str6 = this.h;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerId", "key");
        if (str6 != null) {
            createMapBuilder.put("uppCustomerId", str6);
        }
        String str7 = this.i;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerIpAddress", "key");
        if (str7 != null) {
            createMapBuilder.put("uppCustomerIpAddress", str7);
        }
        String str8 = this.j;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerLanguage", "key");
        if (str8 != null) {
            createMapBuilder.put("uppCustomerLanguage", str8);
        }
        String str9 = this.k;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerMobile", "key");
        if (str9 != null) {
            createMapBuilder.put("uppCustomerMobile", str9);
        }
        String str10 = this.l;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerPhone", "key");
        if (str10 != null) {
            createMapBuilder.put("uppCustomerPhone", str10);
        }
        String str11 = this.m;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppCustomerType", "key");
        if (str11 != null) {
            createMapBuilder.put("uppCustomerType", str11);
        }
        return MapsKt.build(createMapBuilder);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof K3)) {
            return false;
        }
        K3 k3 = (K3) obj;
        return Intrinsics.areEqual(this.a, k3.a) && Intrinsics.areEqual(this.b, k3.b) && Intrinsics.areEqual(this.c, k3.c) && Intrinsics.areEqual(this.d, k3.d) && Intrinsics.areEqual(this.e, k3.e) && Intrinsics.areEqual(this.f, k3.f) && Intrinsics.areEqual(this.g, k3.g) && Intrinsics.areEqual(this.h, k3.h) && Intrinsics.areEqual(this.i, k3.i) && Intrinsics.areEqual(this.j, k3.j) && Intrinsics.areEqual(this.k, k3.k) && Intrinsics.areEqual(this.l, k3.l) && Intrinsics.areEqual(this.m, k3.m);
    }

    public final int hashCode() {
        C0116c c0116c = this.a;
        int hashCode = (c0116c == null ? 0 : c0116c.hashCode()) * 31;
        C0158i4 c0158i4 = this.b;
        int hashCode2 = (hashCode + (c0158i4 == null ? 0 : c0158i4.hashCode())) * 31;
        String str = this.c;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.e;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f;
        int hashCode6 = (hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.g;
        int hashCode7 = (hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.h;
        int hashCode8 = (hashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.i;
        int hashCode9 = (hashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.j;
        int hashCode10 = (hashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.k;
        int hashCode11 = (hashCode10 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.l;
        int hashCode12 = (hashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.m;
        return hashCode12 + (str11 != null ? str11.hashCode() : 0);
    }

    public final String toString() {
        return "Customer(address=" + this.a + ", birthDate=" + this.b + ", cellPhone=" + this.c + ", companyRegisterNumber=" + this.d + ", email=" + this.e + ", fax=" + this.f + ", gender=" + this.g + ", id=" + this.h + ", ipAddress=" + this.i + ", language=" + this.j + ", mobile=" + this.k + ", phone=" + this.l + ", type=" + this.m + ')';
    }

    public final void a(PaymentMethodType paymentMethodType) {
        a(this.a, "address", paymentMethodType);
        C0116c c0116c = this.a;
        Intrinsics.checkNotNull(c0116c);
        c0116c.getClass();
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        switch (AbstractC0114b.a[paymentMethodType.ordinal()]) {
            case 1:
            case 2:
            case 3:
                C0116c.a(c0116c.d, "FirstName", paymentMethodType);
                C0116c.a(c0116c.f, "LastName", paymentMethodType);
                C0116c.a(c0116c.g, "Street", paymentMethodType);
                C0116c.a(c0116c.j, "City", paymentMethodType);
                C0116c.a(c0116c.i, "ZipCode", paymentMethodType);
                C0116c.a(c0116c.l, "Country", paymentMethodType);
                break;
            case 4:
                C0116c.a(c0116c.d, "FirstName", paymentMethodType);
                C0116c.a(c0116c.f, "LastName", paymentMethodType);
                C0116c.a(c0116c.g, "Street", paymentMethodType);
                C0116c.a(c0116c.i, "ZipCode", paymentMethodType);
                break;
            case 5:
            case 6:
                C0116c.a(c0116c.d, "FirstName", paymentMethodType);
                C0116c.a(c0116c.f, "LastName", paymentMethodType);
                C0116c.a(c0116c.g, "Street", paymentMethodType);
                C0116c.a(c0116c.i, "ZipCode", paymentMethodType);
                C0116c.a(c0116c.j, "City", paymentMethodType);
                break;
        }
    }

    public static void a(Object obj, String name, PaymentMethodType paymentMethod) {
        Intrinsics.checkNotNullExpressionValue("K3", "getSimpleName(...)");
        Intrinsics.checkNotNullParameter("K3", "fieldParentClass");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(paymentMethod, "paymentMethod");
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(("K3 field '" + name + "' must not be null for payment method " + paymentMethod.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String() + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
    }
}
