package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.internal.o;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class h {
    private final String a;
    private final String b;
    private final Date c;
    private final List<g> d;
    private final List<e> e;
    private String f;

    public h(String id, String key, Date date, List<g> list, List<e> outcomes, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(outcomes, "outcomes");
        this.a = id;
        this.b = key;
        this.c = date;
        this.d = list;
        this.e = outcomes;
        this.f = str;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final Date c() {
        return this.c;
    }

    public final List<g> d() {
        return this.d;
    }

    public final List<e> e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return Intrinsics.areEqual(this.a, hVar.a) && Intrinsics.areEqual(this.b, hVar.b) && Intrinsics.areEqual(this.c, hVar.c) && Intrinsics.areEqual(this.d, hVar.d) && Intrinsics.areEqual(this.e, hVar.e) && Intrinsics.areEqual(this.f, hVar.f);
    }

    public final String f() {
        return this.f;
    }

    public final String g() {
        return this.f;
    }

    public final String h() {
        return this.a;
    }

    public int hashCode() {
        int hashCode = ((this.a.hashCode() * 31) + this.b.hashCode()) * 31;
        Date date = this.c;
        int hashCode2 = (hashCode + (date == null ? 0 : date.hashCode())) * 31;
        List<g> list = this.d;
        int hashCode3 = (((hashCode2 + (list == null ? 0 : list.hashCode())) * 31) + this.e.hashCode()) * 31;
        String str = this.f;
        return hashCode3 + (str != null ? str.hashCode() : 0);
    }

    public final String i() {
        return this.b;
    }

    public final List<e> j() {
        return this.e;
    }

    public final List<g> k() {
        return this.d;
    }

    public final Date l() {
        return this.c;
    }

    public final JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("key", this.b);
        Date date = this.c;
        if (date != null) {
            jSONObject.put("startDateUtc", o.a(date));
        }
        List<g> list = this.d;
        if (list != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(((g) it.next()).k());
            }
            Unit unit = Unit.INSTANCE;
            jSONObject.put("rules", jSONArray);
        }
        JSONArray jSONArray2 = new JSONArray();
        Iterator<T> it2 = this.e.iterator();
        while (it2.hasNext()) {
            jSONArray2.put(((e) it2.next()).g());
        }
        Unit unit2 = Unit.INSTANCE;
        jSONObject.put("outcomes", jSONArray2);
        String str = this.f;
        if (str != null) {
            jSONObject.put("evalLogic", str);
        }
        return jSONObject;
    }

    public String toString() {
        return "Trigger(id=" + this.a + ", key=" + this.b + ", startDateUtc=" + this.c + ", rules=" + this.d + ", outcomes=" + this.e + ", evalLogic=" + this.f + ")";
    }

    public final h a(String id, String key, Date date, List<g> list, List<e> outcomes, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(outcomes, "outcomes");
        return new h(id, key, date, list, outcomes, str);
    }

    public static /* synthetic */ h a(h hVar, String str, String str2, Date date, List list, List list2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hVar.a;
        }
        if ((i & 2) != 0) {
            str2 = hVar.b;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            date = hVar.c;
        }
        Date date2 = date;
        if ((i & 8) != 0) {
            list = hVar.d;
        }
        List list3 = list;
        if ((i & 16) != 0) {
            list2 = hVar.e;
        }
        List list4 = list2;
        if ((i & 32) != 0) {
            str3 = hVar.f;
        }
        return hVar.a(str, str4, date2, list3, list4, str3);
    }

    public final void a(String str) {
        this.f = str;
    }

    public /* synthetic */ h(String str, String str2, Date date, List list, List list2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : date, (i & 8) != 0 ? null : list, list2, (i & 32) != 0 ? null : str3);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public h(org.json.JSONObject r15) {
        /*
            Method dump skipped, instructions count: 709
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.events.h.<init>(org.json.JSONObject):void");
    }
}
