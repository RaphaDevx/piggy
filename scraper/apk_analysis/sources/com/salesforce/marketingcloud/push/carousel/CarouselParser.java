package com.salesforce.marketingcloud.push.carousel;

import com.salesforce.marketingcloud.push.carousel.a;
import com.salesforce.marketingcloud.push.data.Style;
import com.salesforce.marketingcloud.push.data.Template;
import com.salesforce.marketingcloud.push.e;
import com.salesforce.marketingcloud.push.g;
import com.salesforce.marketingcloud.push.j;
import com.salesforce.marketingcloud.push.m;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class CarouselParser implements j<a> {
    @Override // com.salesforce.marketingcloud.push.j
    public String hydrate(Template obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        if (!(obj instanceof a)) {
            throw new m("Carousel is not a CarouselFullTemplate");
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(g.g, obj.f().getValue());
        Style a = obj.a();
        if (a != null) {
            jSONObject.put("s", Style.a.a(a));
        }
        List<a.C0076a> l = ((a) obj).l();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(l, 10));
        Iterator<T> it = l.iterator();
        while (it.hasNext()) {
            arrayList.add(((a.C0076a) it.next()).s());
        }
        jSONObject.put(g.h, new JSONArray((Collection) arrayList));
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.salesforce.marketingcloud.push.j
    public a parse(String obj) {
        Style.b bVar;
        Intrinsics.checkNotNullParameter(obj, "obj");
        JSONObject jSONObject = new JSONObject(obj);
        JSONArray optJSONArray = jSONObject.optJSONArray(g.h);
        if (optJSONArray == null) {
            throw new e(g.h);
        }
        List<a.C0076a> a = c.a(optJSONArray);
        if (a.isEmpty()) {
            throw new m("Carousel is empty");
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("s");
        if (optJSONObject != null) {
            Intrinsics.checkNotNull(optJSONObject);
            bVar = Style.a.a(optJSONObject);
        } else {
            bVar = null;
        }
        return new a(a, 0, bVar, 2, null);
    }
}
