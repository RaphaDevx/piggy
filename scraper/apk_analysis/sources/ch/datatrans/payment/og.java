package ch.datatrans.payment;

import java.util.Collection;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class og {
    public static String a() {
        Map mutableMap = MapsKt.toMutableMap(Ta.a);
        mutableMap.putAll(Ta.b);
        Collection values = MapsKt.toSortedMap(MapsKt.toMap(mutableMap)).values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        return CollectionsKt.joinToString$default(values, "", null, null, 0, null, new Function1() { // from class: ch.datatrans.payment.og$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return og.a((String) obj);
            }
        }, 30, null);
    }

    public static final CharSequence a(String str) {
        Intrinsics.checkNotNull(str);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isLetter(charAt)) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
