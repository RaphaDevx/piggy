package com.salesforce.marketingcloud.config;

import android.net.Uri;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class b {
    public static final a d = new a(null);
    private final String a;
    private final String b;
    private final Integer c;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final b a(String endpointIn) {
            Intrinsics.checkNotNullParameter(endpointIn, "endpointIn");
            return a(this, endpointIn, null, null, 6, null);
        }

        private a() {
        }

        public final b a(String endpointIn, String str) {
            Intrinsics.checkNotNullParameter(endpointIn, "endpointIn");
            return a(this, endpointIn, str, null, 4, null);
        }

        public static /* synthetic */ b a(a aVar, String str, String str2, Integer num, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                num = null;
            }
            return aVar.a(str, str2, num);
        }

        public final b a(String endpointIn, String str, Integer num) {
            String str2;
            Intrinsics.checkNotNullParameter(endpointIn, "endpointIn");
            String obj = StringsKt.trim((CharSequence) endpointIn).toString();
            if (obj.length() != 0 && ArraysKt.contains(EnumC0052b.values(), EnumC0052b.valueOf(obj))) {
                DefaultConstructorMarker defaultConstructorMarker = null;
                if (str == null || (str2 = StringsKt.trim((CharSequence) str).toString()) == null) {
                    str2 = null;
                } else if (str2.length() == 0 || !StringsKt.startsWith$default(str2, "/", false, 2, (Object) null) || !Intrinsics.areEqual(str2, Uri.parse(str2).getPath())) {
                    throw new IllegalArgumentException("Invalid 'path' for " + obj + " endpoint config.");
                }
                if (!(num == null ? true : new IntRange(10, Integer.MAX_VALUE).contains(num.intValue()))) {
                    throw new IllegalArgumentException("Invalid 'maxBatchSize' for " + obj + " endpoint config.");
                }
                if (str2 == null && num == null) {
                    throw new IllegalArgumentException("Empty endpoint config for " + obj + " is pointless.");
                }
                return new b(obj, str2, num, defaultConstructorMarker);
            }
            throw new IllegalArgumentException("Invalid 'endpoint' for endpoint config.");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* renamed from: com.salesforce.marketingcloud.config.b$b, reason: collision with other inner class name */
    public static final class EnumC0052b {
        public static final EnumC0052b b = new EnumC0052b("EVENTS", 0);
        private static final /* synthetic */ EnumC0052b[] c;
        private static final /* synthetic */ EnumEntries d;

        static {
            EnumC0052b[] a = a();
            c = a;
            d = EnumEntriesKt.enumEntries(a);
        }

        private EnumC0052b(String str, int i) {
        }

        private static final /* synthetic */ EnumC0052b[] a() {
            return new EnumC0052b[]{b};
        }

        public static EnumEntries<EnumC0052b> b() {
            return d;
        }

        public static EnumC0052b valueOf(String str) {
            return (EnumC0052b) Enum.valueOf(EnumC0052b.class, str);
        }

        public static EnumC0052b[] values() {
            return (EnumC0052b[]) c.clone();
        }
    }

    public /* synthetic */ b(String str, String str2, Integer num, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, num);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final Integer c() {
        return this.c;
    }

    public final String d() {
        return this.a;
    }

    public final Integer e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c);
    }

    public final String f() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.c;
        return hashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "EndpointConfig(endpoint=" + this.a + ", path=" + this.b + ", maxBatchSize=" + this.c + ")";
    }

    private b(String str, String str2, Integer num) {
        this.a = str;
        this.b = str2;
        this.c = num;
    }

    public final b a(String endpoint, String str, Integer num) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        return new b(endpoint, str, num);
    }

    public static /* synthetic */ b a(b bVar, String str, String str2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bVar.a;
        }
        if ((i & 2) != 0) {
            str2 = bVar.b;
        }
        if ((i & 4) != 0) {
            num = bVar.c;
        }
        return bVar.a(str, str2, num);
    }

    /* synthetic */ b(String str, String str2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num);
    }
}
