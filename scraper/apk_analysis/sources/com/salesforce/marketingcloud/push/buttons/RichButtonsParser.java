package com.salesforce.marketingcloud.push.buttons;

import com.salesforce.marketingcloud.push.buttons.a;
import com.salesforce.marketingcloud.push.data.Template;
import com.salesforce.marketingcloud.push.j;
import com.salesforce.marketingcloud.push.m;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public final class RichButtonsParser implements j<a> {
    @Override // com.salesforce.marketingcloud.push.j
    public String hydrate(Template obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        if (!(obj instanceof a)) {
            throw new m("obj is not a RichButtonTemplate");
        }
        List<a.c> k = ((a) obj).k();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(k, 10));
        Iterator<T> it = k.iterator();
        while (it.hasNext()) {
            arrayList.add(((a.c) it.next()).q());
        }
        String jSONArray = new JSONArray((Collection) arrayList).toString();
        Intrinsics.checkNotNullExpressionValue(jSONArray, "toString(...)");
        return jSONArray;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.salesforce.marketingcloud.push.j
    public a parse(String obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        List<a.c> a = b.a(new JSONArray(obj));
        if (a.isEmpty()) {
            throw new m("Button is empty");
        }
        return new a(a, null, 2, 0 == true ? 1 : 0);
    }
}
