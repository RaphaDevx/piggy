package ch.datatrans.payment;

import com.google.gson.JsonObject;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class R5 {
    public static final String a(JsonObject jsonObject, String key) {
        Intrinsics.checkNotNullParameter(jsonObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (jsonObject.has(key)) {
            return jsonObject.get(key).getAsString();
        }
        return null;
    }

    public static final String a(String... parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        return "JSON string does not contain required field: " + ArraysKt.joinToString$default(parameters, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
