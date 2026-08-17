package ch.datatrans.payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.i4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0158i4 {
    public final String a;

    public C0158i4(String dateString) {
        Date parse;
        Intrinsics.checkNotNullParameter(dateString, "dateString");
        try {
            parse = new SimpleDateFormat("dd.MM.yyyy", Locale.US).parse(dateString);
            Intrinsics.checkNotNull(parse);
        } catch (ParseException unused) {
            try {
                parse = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(dateString);
                Intrinsics.checkNotNull(parse);
            } catch (ParseException unused2) {
                throw new IllegalArgumentException("Illegal date format, must be dd.MM.yyyy or yyyy-MM-dd.");
            }
        }
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(parse);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        this.a = format;
    }
}
