package ch.datatrans.payment;

import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class a9 {
    public final List a;
    public final C0116c b;
    public final String c;
    public final String d;
    public final String e;

    public a9(List basket, C0116c c0116c, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(basket, "basket");
        this.a = basket;
        this.b = c0116c;
        this.c = str;
        this.d = str2;
        this.e = str3;
    }

    public final Map a() {
        Map createMapBuilder = MapsKt.createMapBuilder();
        x4.a(createMapBuilder, "uppTaxAmount", this.c);
        String str = this.d;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppDiscountAmount", "key");
        if (str != null) {
            createMapBuilder.put("uppDiscountAmount", str);
        }
        String str2 = this.e;
        Intrinsics.checkNotNullParameter(createMapBuilder, "<this>");
        Intrinsics.checkNotNullParameter("uppShippingAmount", "key");
        if (str2 != null) {
            createMapBuilder.put("uppShippingAmount", str2);
        }
        if (this.b != null) {
            createMapBuilder.put("uppShippingDetails", "true");
            this.b.a("uppShipping", createMapBuilder);
        }
        int i = 0;
        for (Object obj : this.a) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            C0218t c0218t = (C0218t) obj;
            c0218t.getClass();
            Map createMapBuilder2 = MapsKt.createMapBuilder();
            String str3 = "uppArticle_" + i2 + '_';
            String str4 = str3 + "PriceGross";
            Integer num = c0218t.a;
            x4.a(createMapBuilder2, str4, num != null ? num.toString() : null);
            String key = str3 + "PriceWithoutVat";
            Integer num2 = c0218t.c;
            String num3 = num2 != null ? num2.toString() : null;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key, "key");
            if (num3 != null) {
                createMapBuilder2.put(key, num3);
            }
            String key2 = str3 + "Price";
            Integer num4 = c0218t.b;
            String num5 = num4 != null ? num4.toString() : null;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key2, "key");
            if (num5 != null) {
                createMapBuilder2.put(key2, num5);
            }
            String key3 = str3 + "Quantity";
            Integer num6 = c0218t.d;
            String num7 = num6 != null ? num6.toString() : null;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key3, "key");
            if (num7 != null) {
                createMapBuilder2.put(key3, num7);
            }
            String key4 = str3 + "Id";
            String str5 = c0218t.f;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key4, "key");
            if (str5 != null) {
                createMapBuilder2.put(key4, str5);
            }
            String key5 = str3 + "Description";
            String str6 = c0218t.g;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key5, "key");
            if (str6 != null) {
                createMapBuilder2.put(key5, str6);
            }
            String key6 = str3 + "Name";
            String str7 = c0218t.h;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key6, "key");
            if (str7 != null) {
                createMapBuilder2.put(key6, str7);
            }
            String key7 = str3 + "Tax";
            String str8 = c0218t.i;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key7, "key");
            if (str8 != null) {
                createMapBuilder2.put(key7, str8);
            }
            String key8 = str3 + "TaxAmount";
            String str9 = c0218t.j;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key8, "key");
            if (str9 != null) {
                createMapBuilder2.put(key8, str9);
            }
            String key9 = str3 + "Type";
            String str10 = c0218t.e;
            Intrinsics.checkNotNullParameter(createMapBuilder2, "<this>");
            Intrinsics.checkNotNullParameter(key9, "key");
            if (str10 != null) {
                createMapBuilder2.put(key9, str10);
            }
            createMapBuilder.putAll(MapsKt.build(createMapBuilder2));
            i = i2;
        }
        return MapsKt.build(createMapBuilder);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a9)) {
            return false;
        }
        a9 a9Var = (a9) obj;
        return Intrinsics.areEqual(this.a, a9Var.a) && Intrinsics.areEqual(this.b, a9Var.b) && Intrinsics.areEqual(this.c, a9Var.c) && Intrinsics.areEqual(this.d, a9Var.d) && Intrinsics.areEqual(this.e, a9Var.e);
    }

    public final int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        C0116c c0116c = this.b;
        int hashCode2 = (hashCode + (c0116c == null ? 0 : c0116c.hashCode())) * 31;
        String str = this.c;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.e;
        return hashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PaymentInfo(basket=");
        sb.append(this.a);
        sb.append(", shippingAddress=");
        sb.append(this.b);
        sb.append(", taxAmount=");
        sb.append(this.c);
        sb.append(", discountAmount=");
        sb.append(this.d);
        sb.append(", shippingAmount=");
        return N7.a(sb, this.e, ')');
    }
}
