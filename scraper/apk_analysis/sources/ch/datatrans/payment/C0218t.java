package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.t, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0218t {
    public final Integer a;
    public final Integer b;
    public final Integer c;
    public final Integer d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;

    public C0218t(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, String str3, String str4, String str5, String str6) {
        this.a = num;
        this.b = num2;
        this.c = num3;
        this.d = num4;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = str4;
        this.i = str5;
        this.j = str6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0218t)) {
            return false;
        }
        C0218t c0218t = (C0218t) obj;
        return Intrinsics.areEqual(this.a, c0218t.a) && Intrinsics.areEqual(this.b, c0218t.b) && Intrinsics.areEqual(this.c, c0218t.c) && Intrinsics.areEqual(this.d, c0218t.d) && Intrinsics.areEqual(this.e, c0218t.e) && Intrinsics.areEqual(this.f, c0218t.f) && Intrinsics.areEqual(this.g, c0218t.g) && Intrinsics.areEqual(this.h, c0218t.h) && Intrinsics.areEqual(this.i, c0218t.i) && Intrinsics.areEqual(this.j, c0218t.j);
    }

    public final int hashCode() {
        Integer num = this.a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.b;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.c;
        int hashCode3 = (hashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.d;
        int hashCode4 = (hashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        String str = this.e;
        int hashCode5 = (hashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f;
        int hashCode6 = (hashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.g;
        int hashCode7 = (hashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.h;
        int hashCode8 = (hashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.i;
        int hashCode9 = (hashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.j;
        return hashCode9 + (str6 != null ? str6.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BasketItem(priceGross=");
        sb.append(this.a);
        sb.append(", price=");
        sb.append(this.b);
        sb.append(", priceWithoutVat=");
        sb.append(this.c);
        sb.append(", quantity=");
        sb.append(this.d);
        sb.append(", type=");
        sb.append(this.e);
        sb.append(", articleId=");
        sb.append(this.f);
        sb.append(", description=");
        sb.append(this.g);
        sb.append(", name=");
        sb.append(this.h);
        sb.append(", tax=");
        sb.append(this.i);
        sb.append(", taxAmount=");
        return N7.a(sb, this.j, ')');
    }
}
