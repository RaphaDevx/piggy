package com.scandit.datacapture.core.internal.module.https.scandit;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0533e implements InterfaceC0532d {
    public C0533e() {
        Intrinsics.checkNotNullParameter(", ", "separator");
    }

    public final HashMap a(Map headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : headers.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (str != null) {
                String lowerCase = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                hashMap.put(lowerCase, CollectionsKt.joinToString$default(list, ", ", null, null, 0, null, null, 62, null));
            }
        }
        return hashMap;
    }
}
