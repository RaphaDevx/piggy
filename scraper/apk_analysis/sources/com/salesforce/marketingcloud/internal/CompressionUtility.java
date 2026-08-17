package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.internal.a;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class CompressionUtility {
    public static final CompressionUtility INSTANCE = new CompressionUtility();
    private static final a compressionStrategy = a.C0059a.a;

    private CompressionUtility() {
    }

    public final Map<String, String> decompress(Map<String, String> input) throws com.salesforce.marketingcloud.push.c {
        String str;
        Intrinsics.checkNotNullParameter(input, "input");
        Set<Map.Entry<String, String>> entrySet = input.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(entrySet, 10)), 16));
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (StringsKt.startsWith(str2, "$", true)) {
                str = str2.substring(1);
                Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
            } else {
                str = str2;
            }
            if (StringsKt.startsWith(str2, "$", true)) {
                str3 = compressionStrategy.a(str3);
            }
            Pair pair = TuplesKt.to(str, str3);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }
}
