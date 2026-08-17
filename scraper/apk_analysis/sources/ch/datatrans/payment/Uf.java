package ch.datatrans.payment;

import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class Uf {
    public static x6 a(JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Iterator<String> keys = jsonObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
        Sequence<String> filter = SequencesKt.filter(SequencesKt.asSequence(keys), new Function1() { // from class: ch.datatrans.payment.Uf$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Uf.a((String) obj));
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : filter) {
            Intrinsics.checkNotNull(str);
            Pair pair = TuplesKt.to(StringsKt.replace$default(StringsKt.replace$default(str, "].", "_", false, 4, (Object) null), "[", "_", false, 4, (Object) null), jsonObject.optString(str));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        x6 x6Var = new x6(linkedHashMap);
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return x6Var;
    }

    public static final boolean a(String str) {
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith$default(str, "klarna", false, 2, (Object) null);
    }
}
