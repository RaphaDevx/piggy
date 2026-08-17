package com.salesforce.marketingcloud.push.buttons;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.push.data.Style;
import com.salesforce.marketingcloud.push.data.Template;
import com.salesforce.marketingcloud.push.data.a;
import com.salesforce.marketingcloud.push.data.d;
import com.salesforce.marketingcloud.push.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a implements Template {
    private final List<c> b;
    private final Style c;
    private final Template.Type d;
    private final String e;
    public static final C0074a f = new C0074a(null);
    public static final Parcelable.Creator<a> CREATOR = new b();

    /* renamed from: com.salesforce.marketingcloud.push.buttons.a$a, reason: collision with other inner class name */
    public static final class C0074a {
        public /* synthetic */ C0074a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final boolean a(a buttons) {
            Intrinsics.checkNotNullParameter(buttons, "buttons");
            for (c cVar : buttons.k()) {
                if (cVar.a() != null) {
                    return false;
                }
                com.salesforce.marketingcloud.push.data.c p = cVar.p();
                if ((p != null ? p.a() : null) != null || cVar.o() != null) {
                    return false;
                }
            }
            return true;
        }

        private C0074a() {
        }
    }

    public static final class b implements Parcelable.Creator<a> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final a createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i = 0; i != readInt; i++) {
                arrayList.add(c.CREATOR.createFromParcel(parcel));
            }
            return new a(arrayList, (Style) parcel.readParcelable(a.class.getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final a[] newArray(int i) {
            return new a[i];
        }
    }

    public static final class c implements Parcelable, d {
        public static final Parcelable.Creator<c> CREATOR = new C0075a();
        private final String b;
        private final com.salesforce.marketingcloud.push.data.c c;
        private final String d;
        private final Style e;
        private final List<com.salesforce.marketingcloud.push.data.a> f;

        /* renamed from: com.salesforce.marketingcloud.push.buttons.a$c$a, reason: collision with other inner class name */
        public static final class C0075a implements Parcelable.Creator<c> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String readString = parcel.readString();
                ArrayList arrayList = null;
                com.salesforce.marketingcloud.push.data.c createFromParcel = parcel.readInt() == 0 ? null : com.salesforce.marketingcloud.push.data.c.CREATOR.createFromParcel(parcel);
                String readString2 = parcel.readString();
                Style style = (Style) parcel.readParcelable(c.class.getClassLoader());
                if (parcel.readInt() != 0) {
                    int readInt = parcel.readInt();
                    arrayList = new ArrayList(readInt);
                    for (int i = 0; i != readInt; i++) {
                        arrayList.add(parcel.readParcelable(c.class.getClassLoader()));
                    }
                }
                return new c(readString, createFromParcel, readString2, style, arrayList);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final c[] newArray(int i) {
                return new c[i];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public c(String identifier, com.salesforce.marketingcloud.push.data.c cVar, String str, Style style, List<? extends com.salesforce.marketingcloud.push.data.a> list) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            this.b = identifier;
            this.c = cVar;
            this.d = str;
            this.e = style;
            this.f = list;
        }

        public final c a(String identifier, com.salesforce.marketingcloud.push.data.c cVar, String str, Style style, List<? extends com.salesforce.marketingcloud.push.data.a> list) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            return new c(identifier, cVar, str, style, list);
        }

        public final String d() {
            return this.b;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return Intrinsics.areEqual(this.b, cVar.b) && Intrinsics.areEqual(this.c, cVar.c) && Intrinsics.areEqual(this.d, cVar.d) && Intrinsics.areEqual(this.e, cVar.e) && Intrinsics.areEqual(this.f, cVar.f);
        }

        public int hashCode() {
            int hashCode = this.b.hashCode() * 31;
            com.salesforce.marketingcloud.push.data.c cVar = this.c;
            int hashCode2 = (hashCode + (cVar == null ? 0 : cVar.hashCode())) * 31;
            String str = this.d;
            int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Style style = this.e;
            int hashCode4 = (hashCode3 + (style == null ? 0 : style.hashCode())) * 31;
            List<com.salesforce.marketingcloud.push.data.a> list = this.f;
            return hashCode4 + (list != null ? list.hashCode() : 0);
        }

        @Override // com.salesforce.marketingcloud.push.data.d
        public List<com.salesforce.marketingcloud.push.data.a> i() {
            return this.f;
        }

        public final String j() {
            return this.b;
        }

        public final com.salesforce.marketingcloud.push.data.c k() {
            return this.c;
        }

        public final String l() {
            return this.d;
        }

        public final Style m() {
            return this.e;
        }

        public final List<com.salesforce.marketingcloud.push.data.a> n() {
            return this.f;
        }

        public final String o() {
            return this.d;
        }

        public final com.salesforce.marketingcloud.push.data.c p() {
            return this.c;
        }

        public final JSONObject q() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.b);
            com.salesforce.marketingcloud.push.data.c cVar = this.c;
            if (cVar != null) {
                jSONObject.put(g.n, com.salesforce.marketingcloud.push.data.c.e.a(cVar));
            }
            String str = this.d;
            if (str != null) {
                jSONObject.put(g.s, str);
            }
            Style a = a();
            if (a != null) {
                jSONObject.put("s", Style.a.a(a));
            }
            if (i() != null) {
                List<com.salesforce.marketingcloud.push.data.a> i = i();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(i, 10));
                Iterator<T> it = i.iterator();
                while (it.hasNext()) {
                    arrayList.add(((com.salesforce.marketingcloud.push.data.a) it.next()).j());
                }
                jSONObject.put(g.l, new JSONArray((Collection) arrayList));
            }
            return jSONObject;
        }

        public String toString() {
            return "RichButton(identifier=" + this.b + ", title=" + this.c + ", icon=" + this.d + ", style=" + a() + ", action=" + i() + ")";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int i) {
            Intrinsics.checkNotNullParameter(out, "out");
            out.writeString(this.b);
            com.salesforce.marketingcloud.push.data.c cVar = this.c;
            if (cVar == null) {
                out.writeInt(0);
            } else {
                out.writeInt(1);
                cVar.writeToParcel(out, i);
            }
            out.writeString(this.d);
            out.writeParcelable(this.e, i);
            List<com.salesforce.marketingcloud.push.data.a> list = this.f;
            if (list == null) {
                out.writeInt(0);
                return;
            }
            out.writeInt(1);
            out.writeInt(list.size());
            Iterator<com.salesforce.marketingcloud.push.data.a> it = list.iterator();
            while (it.hasNext()) {
                out.writeParcelable(it.next(), i);
            }
        }

        public static /* synthetic */ c a(c cVar, String str, com.salesforce.marketingcloud.push.data.c cVar2, String str2, Style style, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cVar.b;
            }
            if ((i & 2) != 0) {
                cVar2 = cVar.c;
            }
            com.salesforce.marketingcloud.push.data.c cVar3 = cVar2;
            if ((i & 4) != 0) {
                str2 = cVar.d;
            }
            String str3 = str2;
            if ((i & 8) != 0) {
                style = cVar.e;
            }
            Style style2 = style;
            if ((i & 16) != 0) {
                list = cVar.f;
            }
            return cVar.a(str, cVar3, str3, style2, list);
        }

        @Override // com.salesforce.marketingcloud.push.data.d
        public Style a() {
            return this.e;
        }

        public /* synthetic */ c(String str, com.salesforce.marketingcloud.push.data.c cVar, String str2, Style style, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : cVar, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : style, (i & 16) != 0 ? CollectionsKt.listOf(a.e.d) : list);
        }
    }

    public a(List<c> items, Style style) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.b = items;
        this.c = style;
        this.d = Template.Type.RichButtons;
    }

    public final a a(List<c> items, Style style) {
        Intrinsics.checkNotNullParameter(items, "items");
        return new a(items, style);
    }

    @Override // com.salesforce.marketingcloud.push.data.Template
    public String d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
    }

    @Override // com.salesforce.marketingcloud.push.data.Template
    public Template.Type f() {
        return this.d;
    }

    public int hashCode() {
        int hashCode = this.b.hashCode() * 31;
        Style style = this.c;
        return hashCode + (style == null ? 0 : style.hashCode());
    }

    public final List<c> i() {
        return this.b;
    }

    public final Style j() {
        return this.c;
    }

    public final List<c> k() {
        return this.b;
    }

    public String toString() {
        return "RichButtonTemplate(items=" + this.b + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        List<c> list = this.b;
        out.writeInt(list.size());
        Iterator<c> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(out, i);
        }
        out.writeParcelable(this.c, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ a a(a aVar, List list, Style style, int i, Object obj) {
        if ((i & 1) != 0) {
            list = aVar.b;
        }
        if ((i & 2) != 0) {
            style = aVar.c;
        }
        return aVar.a(list, style);
    }

    @JvmStatic
    public static final boolean a(a aVar) {
        return f.a(aVar);
    }

    @Override // com.salesforce.marketingcloud.push.data.Template
    public Style a() {
        return this.c;
    }

    public /* synthetic */ a(List list, Style style, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : style);
    }
}
