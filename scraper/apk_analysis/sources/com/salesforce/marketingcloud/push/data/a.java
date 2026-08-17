package com.salesforce.marketingcloud.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.internal.o;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public abstract class a implements Parcelable {
    public static final b c = new b(null);
    private final int b;

    /* renamed from: com.salesforce.marketingcloud.push.data.a$a, reason: collision with other inner class name */
    public static final class C0079a extends a {
        public static final Parcelable.Creator<C0079a> CREATOR = new C0080a();
        private final String d;

        /* renamed from: com.salesforce.marketingcloud.push.data.a$a$a, reason: collision with other inner class name */
        public static final class C0080a implements Parcelable.Creator<C0079a> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final C0079a createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new C0079a(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final C0079a[] newArray(int i) {
                return new C0079a[i];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0079a(String url) {
            super(f.e.ordinal(), null);
            Intrinsics.checkNotNullParameter(url, "url");
            this.d = url;
        }

        public final C0079a a(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new C0079a(url);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof C0079a) && Intrinsics.areEqual(this.d, ((C0079a) obj).d);
        }

        public int hashCode() {
            return this.d.hashCode();
        }

        public final String k() {
            return this.d;
        }

        public final String l() {
            return this.d;
        }

        public String toString() {
            return "CloudPage(url=" + this.d + ")";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeString(this.d);
        }

        public static /* synthetic */ C0079a a(C0079a c0079a, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = c0079a.d;
            }
            return c0079a.a(str);
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final a a(JSONObject json) {
            a c0079a;
            Intrinsics.checkNotNullParameter(json, "json");
            Integer valueOf = Integer.valueOf(json.optString(com.salesforce.marketingcloud.push.g.g));
            int ordinal = f.b.ordinal();
            if (valueOf != null && valueOf.intValue() == ordinal) {
                return e.d;
            }
            int ordinal2 = f.c.ordinal();
            if (valueOf != null && valueOf.intValue() == ordinal2) {
                String optString = json.optString(com.salesforce.marketingcloud.push.g.m);
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                String b = o.b(optString);
                if (b == null) {
                    return e.d;
                }
                c0079a = new c(b);
            } else {
                int ordinal3 = f.d.ordinal();
                if (valueOf != null && valueOf.intValue() == ordinal3) {
                    String optString2 = json.optString(com.salesforce.marketingcloud.push.g.m);
                    Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                    String b2 = o.b(optString2);
                    if (b2 == null) {
                        return e.d;
                    }
                    c0079a = new g(b2);
                } else {
                    int ordinal4 = f.e.ordinal();
                    if (valueOf == null || valueOf.intValue() != ordinal4) {
                        int ordinal5 = f.f.ordinal();
                        if (valueOf != null && valueOf.intValue() == ordinal5) {
                            return d.d;
                        }
                        return null;
                    }
                    String optString3 = json.optString(com.salesforce.marketingcloud.push.g.m);
                    Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                    String b3 = o.b(optString3);
                    if (b3 == null) {
                        return e.d;
                    }
                    c0079a = new C0079a(b3);
                }
            }
            return c0079a;
        }

        private b() {
        }
    }

    public static final class c extends a {
        public static final Parcelable.Creator<c> CREATOR = new C0081a();
        private final String d;

        /* renamed from: com.salesforce.marketingcloud.push.data.a$c$a, reason: collision with other inner class name */
        public static final class C0081a implements Parcelable.Creator<c> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new c(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c[] newArray(int i) {
                return new c[i];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String url) {
            super(f.c.ordinal(), null);
            Intrinsics.checkNotNullParameter(url, "url");
            this.d = url;
        }

        public final c a(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new c(url);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof c) && Intrinsics.areEqual(this.d, ((c) obj).d);
        }

        public int hashCode() {
            return this.d.hashCode();
        }

        public final String k() {
            return this.d;
        }

        public final String l() {
            return this.d;
        }

        public String toString() {
            return "Deeplink(url=" + this.d + ")";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeString(this.d);
        }

        public static /* synthetic */ c a(c cVar, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cVar.d;
            }
            return cVar.a(str);
        }
    }

    public static final class d extends a {
        public static final d d = new d();
        public static final Parcelable.Creator<d> CREATOR = new C0082a();

        /* renamed from: com.salesforce.marketingcloud.push.data.a$d$a, reason: collision with other inner class name */
        public static final class C0082a implements Parcelable.Creator<d> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final d createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return d.d;
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final d[] newArray(int i) {
                return new d[i];
            }
        }

        private d() {
            super(f.f.ordinal(), null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeInt(1);
        }
    }

    public static final class e extends a {
        public static final e d = new e();
        public static final Parcelable.Creator<e> CREATOR = new C0083a();

        /* renamed from: com.salesforce.marketingcloud.push.data.a$e$a, reason: collision with other inner class name */
        public static final class C0083a implements Parcelable.Creator<e> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final e createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return e.d;
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final e[] newArray(int i) {
                return new e[i];
            }
        }

        private e() {
            super(f.b.ordinal(), null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeInt(1);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class f {
        public static final f b = new f("OpenApp", 0);
        public static final f c = new f("Deeplink", 1);
        public static final f d = new f("Url", 2);
        public static final f e = new f("CloudPage", 3);
        public static final f f = new f("Dismiss", 4);
        private static final /* synthetic */ f[] g;
        private static final /* synthetic */ EnumEntries h;

        static {
            f[] a = a();
            g = a;
            h = EnumEntriesKt.enumEntries(a);
        }

        private f(String str, int i) {
        }

        private static final /* synthetic */ f[] a() {
            return new f[]{b, c, d, e, f};
        }

        public static EnumEntries<f> b() {
            return h;
        }

        public static f valueOf(String str) {
            return (f) Enum.valueOf(f.class, str);
        }

        public static f[] values() {
            return (f[]) g.clone();
        }
    }

    public static final class g extends a {
        public static final Parcelable.Creator<g> CREATOR = new C0084a();
        private final String d;

        /* renamed from: com.salesforce.marketingcloud.push.data.a$g$a, reason: collision with other inner class name */
        public static final class C0084a implements Parcelable.Creator<g> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final g createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new g(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final g[] newArray(int i) {
                return new g[i];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String url) {
            super(f.d.ordinal(), null);
            Intrinsics.checkNotNullParameter(url, "url");
            this.d = url;
        }

        public final g a(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new g(url);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof g) && Intrinsics.areEqual(this.d, ((g) obj).d);
        }

        public int hashCode() {
            return this.d.hashCode();
        }

        public final String k() {
            return this.d;
        }

        public final String l() {
            return this.d;
        }

        public String toString() {
            return "Url(url=" + this.d + ")";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeString(this.d);
        }

        public static /* synthetic */ g a(g gVar, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = gVar.d;
            }
            return gVar.a(str);
        }
    }

    public /* synthetic */ a(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int i() {
        return this.b;
    }

    public final JSONObject j() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(com.salesforce.marketingcloud.push.g.g, this.b);
        if (this instanceof c) {
            jSONObject.put(com.salesforce.marketingcloud.push.g.m, ((c) this).l());
        } else if (this instanceof g) {
            jSONObject.put(com.salesforce.marketingcloud.push.g.m, ((g) this).l());
        } else if (this instanceof C0079a) {
            jSONObject.put(com.salesforce.marketingcloud.push.g.m, ((C0079a) this).l());
        }
        return jSONObject;
    }

    private a(int i) {
        this.b = i;
    }
}
