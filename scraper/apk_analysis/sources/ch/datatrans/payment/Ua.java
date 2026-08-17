package ch.datatrans.payment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* loaded from: classes3.dex */
public abstract class Ua {
    public static byte[] a(byte[] bArr, String stringToSign) {
        Intrinsics.checkNotNullParameter(stringToSign, "stringToSign");
        byte[] bytes = stringToSign.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            byte[] doFinal = mac.doFinal(bytes);
            Intrinsics.checkNotNull(doFinal);
            return doFinal;
        } catch (Exception e) {
            throw new RuntimeException("Unable to create HmacMD5 signature.", e);
        }
    }
}
