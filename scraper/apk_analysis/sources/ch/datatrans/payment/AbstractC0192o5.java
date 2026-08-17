package ch.datatrans.payment;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* renamed from: ch.datatrans.payment.o5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0192o5 {
    public static byte[] a(String encoding, Map parameters) {
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        StringBuilder sb = new StringBuilder();
        Iterator it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String encode = URLEncoder.encode((String) entry.getKey(), encoding);
            Iterator it2 = ((List) entry.getValue()).iterator();
            while (it2.hasNext()) {
                String encode2 = URLEncoder.encode((String) it2.next(), encoding);
                sb.append(encode);
                sb.append("=");
                sb.append(encode2);
                if (it2.hasNext()) {
                    sb.append("&");
                }
            }
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        byte[] bytes = sb2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }
}
