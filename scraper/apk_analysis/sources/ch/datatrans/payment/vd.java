package ch.datatrans.payment;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes3.dex */
public abstract class vd {
    public static Map a(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Uri parse = Uri.parse(url);
        if (!parse.isHierarchical()) {
            parse = null;
        }
        if (parse == null) {
            return MapsKt.emptyMap();
        }
        Set<String> queryParameterNames = parse.getQueryParameterNames();
        Intrinsics.checkNotNullExpressionValue(queryParameterNames, "getQueryParameterNames(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(queryParameterNames, 10)), 16));
        for (Object obj : queryParameterNames) {
            String queryParameter = parse.getQueryParameter((String) obj);
            if (queryParameter == null) {
                queryParameter = "";
            }
            linkedHashMap.put(obj, queryParameter);
        }
        return linkedHashMap;
    }
}
