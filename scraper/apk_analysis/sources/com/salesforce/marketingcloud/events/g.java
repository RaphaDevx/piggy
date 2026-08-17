package com.salesforce.marketingcloud.events;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.samsung.android.sdk.samsungpay.v2.card.AddCardInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class g {
    private final int a;
    private final String b;
    private final a c;
    private final b d;
    private final String e;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class a {
        public static final a b = new a("EQ", 0);
        public static final a c = new a("NEQ", 1);
        public static final a d = new a("LT", 2);
        public static final a e = new a(AddCardInfo.PROVIDER_GEMALTO, 3);
        public static final a f = new a("LTEQ", 4);
        public static final a g = new a("GTEQ", 5);
        public static final a h = new a("REGEX", 6);
        private static final /* synthetic */ a[] i;
        private static final /* synthetic */ EnumEntries j;

        static {
            a[] a = a();
            i = a;
            j = EnumEntriesKt.enumEntries(a);
        }

        private a(String str, int i2) {
        }

        private static final /* synthetic */ a[] a() {
            return new a[]{b, c, d, e, f, g, h};
        }

        public static EnumEntries<a> b() {
            return j;
        }

        public static a valueOf(String str) {
            return (a) Enum.valueOf(a.class, str);
        }

        public static a[] values() {
            return (a[]) i.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class b {
        public static final b b = new b("INT", 0);
        public static final b c = new b("DOUBLE", 1);
        public static final b d = new b("BOOL", 2);
        public static final b e = new b("STRING", 3);
        private static final /* synthetic */ b[] f;
        private static final /* synthetic */ EnumEntries g;

        static {
            b[] a = a();
            f = a;
            g = EnumEntriesKt.enumEntries(a);
        }

        private b(String str, int i) {
        }

        private static final /* synthetic */ b[] a() {
            return new b[]{b, c, d, e};
        }

        public static EnumEntries<b> b() {
            return g;
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) f.clone();
        }
    }

    public g(int i, String key, a operator, b valueType, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(operator, "operator");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        Intrinsics.checkNotNullParameter(value, "value");
        this.a = i;
        this.b = key;
        this.c = operator;
        this.d = valueType;
        this.e = value;
    }

    public final int a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final a c() {
        return this.c;
    }

    public final b d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.a == gVar.a && Intrinsics.areEqual(this.b, gVar.b) && this.c == gVar.c && this.d == gVar.d && Intrinsics.areEqual(this.e, gVar.e);
    }

    public final int f() {
        return this.a;
    }

    public final String g() {
        return this.b;
    }

    public final a h() {
        return this.c;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }

    public final String i() {
        return this.e;
    }

    public final b j() {
        return this.d;
    }

    public final JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(FirebaseAnalytics.Param.INDEX, this.a);
        jSONObject.put("key", this.b);
        jSONObject.put("operator", this.c.name());
        jSONObject.put("valueType", this.d.name());
        jSONObject.put("value", this.e);
        return jSONObject;
    }

    public String toString() {
        return "Rule(index=" + this.a + ", key=" + this.b + ", operator=" + this.c + ", valueType=" + this.d + ", value=" + this.e + ")";
    }

    public final g a(int i, String key, a operator, b valueType, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(operator, "operator");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        Intrinsics.checkNotNullParameter(value, "value");
        return new g(i, key, operator, valueType, value);
    }

    public static /* synthetic */ g a(g gVar, int i, String str, a aVar, b bVar, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = gVar.a;
        }
        if ((i2 & 2) != 0) {
            str = gVar.b;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            aVar = gVar.c;
        }
        a aVar2 = aVar;
        if ((i2 & 8) != 0) {
            bVar = gVar.d;
        }
        b bVar2 = bVar;
        if ((i2 & 16) != 0) {
            str2 = gVar.e;
        }
        return gVar.a(i, str3, aVar2, bVar2, str2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public g(org.json.JSONObject r9) {
        /*
            r8 = this;
            java.lang.String r0 = "json"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "index"
            r1 = 0
            int r3 = r9.optInt(r0, r1)
            java.lang.String r0 = "key"
            java.lang.String r4 = r9.getString(r0)
            java.lang.String r0 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
            java.lang.String r1 = "operator"
            java.lang.String r1 = r9.getString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            com.salesforce.marketingcloud.events.g$a r5 = com.salesforce.marketingcloud.events.g.a.valueOf(r1)
            java.lang.String r1 = "valueType"
            java.lang.String r1 = r9.getString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            com.salesforce.marketingcloud.events.g$b r6 = com.salesforce.marketingcloud.events.g.b.valueOf(r1)
            java.lang.String r1 = "value"
            java.lang.String r7 = r9.getString(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.g.<init>(org.json.JSONObject):void");
    }
}
