package com.salesforce.marketingcloud.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.push.e;
import com.salesforce.marketingcloud.push.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class b implements d, Parcelable {
    private final String b;
    private final c c;
    private final Style d;
    private final List<com.salesforce.marketingcloud.push.data.a> e;
    public static final a f = new a(null);
    public static final Parcelable.Creator<b> CREATOR = new C0085b();

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final b a(JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            String optString = json.optString(g.m);
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String b = o.b(optString);
            if (b == null) {
                throw new e(g.m);
            }
            JSONObject optJSONObject = json.optJSONObject(g.p);
            c a = optJSONObject != null ? c.e.a(optJSONObject) : null;
            JSONObject optJSONObject2 = json.optJSONObject("s");
            return new b(b, a, optJSONObject2 != null ? Style.a.a(optJSONObject2) : null, null, 8, null);
        }

        private a() {
        }

        public final JSONObject a(b media) {
            Intrinsics.checkNotNullParameter(media, "media");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(g.m, media.o());
            c n = media.n();
            if (n != null) {
                jSONObject.put(g.p, c.e.a(n));
            }
            Style a = media.a();
            jSONObject.put("s", a != null ? Style.a.a(a) : null);
            return jSONObject;
        }
    }

    /* renamed from: com.salesforce.marketingcloud.push.data.b$b, reason: collision with other inner class name */
    public static final class C0085b implements Parcelable.Creator<b> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            ArrayList arrayList = null;
            c createFromParcel = parcel.readInt() == 0 ? null : c.CREATOR.createFromParcel(parcel);
            Style style = (Style) parcel.readParcelable(b.class.getClassLoader());
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                ArrayList arrayList2 = new ArrayList(readInt);
                for (int i = 0; i != readInt; i++) {
                    arrayList2.add(parcel.readParcelable(b.class.getClassLoader()));
                }
                arrayList = arrayList2;
            }
            return new b(readString, createFromParcel, style, arrayList);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b[] newArray(int i) {
            return new b[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(String url, c cVar, Style style, List<? extends com.salesforce.marketingcloud.push.data.a> list) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.b = url;
        this.c = cVar;
        this.d = style;
        this.e = list;
    }

    public final b a(String url, c cVar, Style style, List<? extends com.salesforce.marketingcloud.push.data.a> list) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new b(url, cVar, style, list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c) && Intrinsics.areEqual(this.d, bVar.d) && Intrinsics.areEqual(this.e, bVar.e);
    }

    public int hashCode() {
        int hashCode = this.b.hashCode() * 31;
        c cVar = this.c;
        int hashCode2 = (hashCode + (cVar == null ? 0 : cVar.hashCode())) * 31;
        Style style = this.d;
        int hashCode3 = (hashCode2 + (style == null ? 0 : style.hashCode())) * 31;
        List<com.salesforce.marketingcloud.push.data.a> list = this.e;
        return hashCode3 + (list != null ? list.hashCode() : 0);
    }

    @Override // com.salesforce.marketingcloud.push.data.d
    public List<com.salesforce.marketingcloud.push.data.a> i() {
        return this.e;
    }

    public final String j() {
        return this.b;
    }

    public final c k() {
        return this.c;
    }

    public final Style l() {
        return this.d;
    }

    public final List<com.salesforce.marketingcloud.push.data.a> m() {
        return this.e;
    }

    public final c n() {
        return this.c;
    }

    public final String o() {
        return this.b;
    }

    public String toString() {
        return "Media(url=" + this.b + ", altText=" + this.c + ", style=" + a() + ", action=" + i() + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.b);
        c cVar = this.c;
        if (cVar == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            cVar.writeToParcel(out, i);
        }
        out.writeParcelable(this.d, i);
        List<com.salesforce.marketingcloud.push.data.a> list = this.e;
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

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ b a(b bVar, String str, c cVar, Style style, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bVar.b;
        }
        if ((i & 2) != 0) {
            cVar = bVar.c;
        }
        if ((i & 4) != 0) {
            style = bVar.d;
        }
        if ((i & 8) != 0) {
            list = bVar.e;
        }
        return bVar.a(str, cVar, style, list);
    }

    @Override // com.salesforce.marketingcloud.push.data.d
    public Style a() {
        return this.d;
    }

    public /* synthetic */ b(String str, c cVar, Style style, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : cVar, (i & 4) != 0 ? null : style, (i & 8) != 0 ? CollectionsKt.emptyList() : list);
    }
}
