package com.salesforce.marketingcloud.push.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.push.data.Style;
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
public final class c implements d, Parcelable {
    private final String b;
    private final Style.b c;
    private final List<com.salesforce.marketingcloud.push.data.a> d;
    public static final a e = new a(null);
    public static final Parcelable.Creator<c> CREATOR = new b();

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final c a(JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            String optString = json.optString(g.q);
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String b = o.b(optString);
            if (b == null) {
                throw new e(g.q);
            }
            JSONObject optJSONObject = json.optJSONObject("s");
            return new c(b, optJSONObject != null ? Style.a.a(optJSONObject) : null, null, 4, null);
        }

        private a() {
        }

        public final JSONObject a(c text) {
            Intrinsics.checkNotNullParameter(text, "text");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(g.q, text.n());
            Style.b a = text.a();
            jSONObject.put("s", a != null ? Style.a.a(a) : null);
            return jSONObject;
        }
    }

    public static final class b implements Parcelable.Creator<c> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String readString = parcel.readString();
            ArrayList arrayList = null;
            Style.b createFromParcel = parcel.readInt() == 0 ? null : Style.b.CREATOR.createFromParcel(parcel);
            if (parcel.readInt() != 0) {
                int readInt = parcel.readInt();
                ArrayList arrayList2 = new ArrayList(readInt);
                for (int i = 0; i != readInt; i++) {
                    arrayList2.add(parcel.readParcelable(c.class.getClassLoader()));
                }
                arrayList = arrayList2;
            }
            return new c(readString, createFromParcel, arrayList);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c[] newArray(int i) {
            return new c[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c(String text, Style.b bVar, List<? extends com.salesforce.marketingcloud.push.data.a> list) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.b = text;
        this.c = bVar;
        this.d = list;
    }

    public final c a(String text, Style.b bVar, List<? extends com.salesforce.marketingcloud.push.data.a> list) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new c(text, bVar, list);
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
        return Intrinsics.areEqual(this.b, cVar.b) && Intrinsics.areEqual(this.c, cVar.c) && Intrinsics.areEqual(this.d, cVar.d);
    }

    public int hashCode() {
        int hashCode = this.b.hashCode() * 31;
        Style.b bVar = this.c;
        int hashCode2 = (hashCode + (bVar == null ? 0 : bVar.hashCode())) * 31;
        List<com.salesforce.marketingcloud.push.data.a> list = this.d;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    @Override // com.salesforce.marketingcloud.push.data.d
    public List<com.salesforce.marketingcloud.push.data.a> i() {
        return this.d;
    }

    public final String j() {
        return this.b;
    }

    public final Style.b k() {
        return this.c;
    }

    public final List<com.salesforce.marketingcloud.push.data.a> l() {
        return this.d;
    }

    @Override // com.salesforce.marketingcloud.push.data.d
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public Style.b a() {
        return this.c;
    }

    public final String n() {
        return this.b;
    }

    public String toString() {
        return "Text(text=" + this.b + ", style=" + a() + ", action=" + i() + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int i) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.b);
        Style.b bVar = this.c;
        if (bVar == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            bVar.writeToParcel(out, i);
        }
        List<com.salesforce.marketingcloud.push.data.a> list = this.d;
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
    public static /* synthetic */ c a(c cVar, String str, Style.b bVar, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cVar.b;
        }
        if ((i & 2) != 0) {
            bVar = cVar.c;
        }
        if ((i & 4) != 0) {
            list = cVar.d;
        }
        return cVar.a(str, bVar, list);
    }

    public /* synthetic */ c(String str, Style.b bVar, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : bVar, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
    }
}
