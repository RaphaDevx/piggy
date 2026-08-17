package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* renamed from: ch.datatrans.payment.a, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0111a {
    public static C0116c a(JSONObject jsonObject, String prefix) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        String c = l7.c(jsonObject, prefix.concat("Email"));
        String c2 = l7.c(jsonObject, prefix.concat("PhoneNumber"));
        String c3 = l7.c(jsonObject, prefix.concat("Title"));
        String c4 = l7.c(jsonObject, prefix.concat("FirstName"));
        String c5 = l7.c(jsonObject, prefix.concat("MiddleName"));
        String c6 = l7.c(jsonObject, prefix.concat("LastName"));
        String c7 = l7.c(jsonObject, prefix.concat("Street"));
        String c8 = l7.c(jsonObject, prefix.concat("Street2"));
        String c9 = l7.c(jsonObject, prefix.concat("ZipCode"));
        String c10 = l7.c(jsonObject, prefix.concat("City"));
        String c11 = l7.c(jsonObject, prefix.concat("State"));
        String c12 = l7.c(jsonObject, prefix.concat("Country"));
        C0116c c0116c = new C0116c(c, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
        if (c == null && c2 == null && c3 == null && c4 == null && c5 == null && c6 == null && c7 == null && c8 == null && c9 == null && c10 == null && c11 == null && c12 == null) {
            return null;
        }
        return c0116c;
    }
}
