package ch.datatrans.payment;

import ch.datatrans.payment.api.DCCShowMode;
import com.google.gson.Gson;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class P3 {
    public final R9 a;
    public final DCCShowMode b;
    public final SimpleDateFormat c;

    public P3(R9 preferencesRepository, DCCShowMode showMode) {
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter(showMode, "showMode");
        this.a = preferencesRepository;
        this.b = showMode;
        this.c = new SimpleDateFormat("dd.MM.yyyy");
    }

    public static String b(String str) {
        StringBuilder sb = new StringBuilder("DTPL_DCC_JSON_");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String bigInteger = new BigInteger(1, messageDigest.digest(bytes)).toString(16);
        Intrinsics.checkNotNullExpressionValue(bigInteger, "toString(...)");
        sb.append(StringsKt.padStart(bigInteger, 32, '0'));
        return sb.toString();
    }

    public final N3 a(String str) {
        Gson gson = new Gson();
        R9 r9 = this.a;
        String key = b(str);
        Intrinsics.checkNotNullParameter(key, "key");
        N3 n3 = (N3) gson.fromJson(r9.a.getString(key, null), N3.class);
        if (n3 != null) {
            return n3;
        }
        SimpleDateFormat simpleDateFormat = this.c;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return new N3(format);
    }
}
