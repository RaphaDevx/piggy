package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;

/* renamed from: ch.datatrans.payment.c, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0116c {
    public final String a;
    public final String b;
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

    public C0116c(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = str9;
        this.j = str10;
        this.k = str11;
        this.l = str12;
    }

    public final void a(String prefix, Map arguments) {
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        x4.a(arguments, prefix.concat("Email"), this.a);
        String key = prefix.concat("PhoneNumber");
        String str = this.b;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (str != null) {
            arguments.put(key, str);
        }
        String key2 = prefix.concat("Title");
        String str2 = this.c;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key2, "key");
        if (str2 != null) {
            arguments.put(key2, str2);
        }
        String key3 = prefix.concat("FirstName");
        String str3 = this.d;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key3, "key");
        if (str3 != null) {
            arguments.put(key3, str3);
        }
        String key4 = prefix.concat("MiddleName");
        String str4 = this.e;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key4, "key");
        if (str4 != null) {
            arguments.put(key4, str4);
        }
        String key5 = prefix.concat("LastName");
        String str5 = this.f;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key5, "key");
        if (str5 != null) {
            arguments.put(key5, str5);
        }
        String key6 = prefix.concat("Street");
        String str6 = this.g;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key6, "key");
        if (str6 != null) {
            arguments.put(key6, str6);
        }
        String key7 = prefix.concat("ZipCode");
        String str7 = this.i;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key7, "key");
        if (str7 != null) {
            arguments.put(key7, str7);
        }
        String key8 = prefix.concat("Street2");
        String str8 = this.h;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key8, "key");
        if (str8 != null) {
            arguments.put(key8, str8);
        }
        String key9 = prefix.concat("City");
        String str9 = this.j;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key9, "key");
        if (str9 != null) {
            arguments.put(key9, str9);
        }
        String key10 = prefix.concat("State");
        String str10 = this.k;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key10, "key");
        if (str10 != null) {
            arguments.put(key10, str10);
        }
        String key11 = prefix.concat("Country");
        String str11 = this.l;
        Intrinsics.checkNotNullParameter(arguments, "<this>");
        Intrinsics.checkNotNullParameter(key11, "key");
        if (str11 != null) {
            arguments.put(key11, str11);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0116c)) {
            return false;
        }
        C0116c c0116c = (C0116c) obj;
        return Intrinsics.areEqual(this.a, c0116c.a) && Intrinsics.areEqual(this.b, c0116c.b) && Intrinsics.areEqual(this.c, c0116c.c) && Intrinsics.areEqual(this.d, c0116c.d) && Intrinsics.areEqual(this.e, c0116c.e) && Intrinsics.areEqual(this.f, c0116c.f) && Intrinsics.areEqual(this.g, c0116c.g) && Intrinsics.areEqual(this.h, c0116c.h) && Intrinsics.areEqual(this.i, c0116c.i) && Intrinsics.areEqual(this.j, c0116c.j) && Intrinsics.areEqual(this.k, c0116c.k) && Intrinsics.areEqual(this.l, c0116c.l);
    }

    public final int hashCode() {
        String str = this.a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.e;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.f;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.g;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.h;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.i;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.j;
        int hashCode10 = (hashCode9 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.k;
        int hashCode11 = (hashCode10 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.l;
        return hashCode11 + (str12 != null ? str12.hashCode() : 0);
    }

    public final String toString() {
        return "Address(email=" + this.a + ", phoneNumber=" + this.b + ", title=" + this.c + ", firstName=" + this.d + ", middleName=" + this.e + ", lastName=" + this.f + ", street=" + this.g + ", street2=" + this.h + ", zipCode=" + this.i + ", city=" + this.j + ", state=" + this.k + ", countryCode=" + this.l + ')';
    }

    public static void a(String str, String name, PaymentMethodType paymentMethod) {
        Intrinsics.checkNotNullExpressionValue("c", "getSimpleName(...)");
        Intrinsics.checkNotNullParameter("c", "fieldParentClass");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(paymentMethod, "paymentMethod");
        if (str != null) {
            return;
        }
        throw new IllegalArgumentException(("c field '" + name + "' must not be null for payment method " + paymentMethod.getIdentifier() + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
    }
}
