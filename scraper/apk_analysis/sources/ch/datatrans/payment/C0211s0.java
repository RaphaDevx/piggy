package ch.datatrans.payment;

import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.s0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0211s0 implements Serializable {
    public final long a;
    public final String b;
    public Boolean c;

    public C0211s0(long j, String currency) {
        Intrinsics.checkNotNullParameter(currency, "currency");
        this.a = j;
        this.b = currency;
    }

    public final String a() {
        int i;
        int i2;
        String currency = this.b;
        long j = this.a;
        Intrinsics.checkNotNullParameter(currency, "currency");
        int i3 = 2;
        Map mapOf = MapsKt.mapOf(TuplesKt.to("BIF", 0), TuplesKt.to("BYR", 0), TuplesKt.to("CLF", 0), TuplesKt.to("CLP", 0), TuplesKt.to("DJF", 0), TuplesKt.to("GNF", 0), TuplesKt.to("ISK", 0), TuplesKt.to("JPY", 0), TuplesKt.to("KMF", 0), TuplesKt.to("KRW", 0), TuplesKt.to("PYG", 0), TuplesKt.to("RWF", 0), TuplesKt.to("UGX", 0), TuplesKt.to("UYI", 0), TuplesKt.to("VND", 0), TuplesKt.to("VUV", 0), TuplesKt.to("XAF", 0), TuplesKt.to("XOF", 0), TuplesKt.to("XPF", 0), TuplesKt.to("BHD", 3), TuplesKt.to("IQD", 3), TuplesKt.to("JOD", 3), TuplesKt.to("KWD", 3), TuplesKt.to("LYD", 3), TuplesKt.to("OMR", 3), TuplesKt.to("TND", 3));
        BigDecimal valueOf = BigDecimal.valueOf(j);
        if (mapOf.containsKey(currency)) {
            Object obj = mapOf.get(currency);
            Intrinsics.checkNotNull(obj);
            i = ((Number) obj).intValue();
        } else {
            i = 2;
        }
        String bigDecimal = valueOf.movePointLeft(i).toString();
        Intrinsics.checkNotNullExpressionValue(bigDecimal, "toString(...)");
        double parseDouble = Double.parseDouble(bigDecimal);
        Locale locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        if (locale == null) {
            locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        }
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        int defaultFractionDigits = Currency.getInstance(currency).getDefaultFractionDigits();
        if (mapOf.containsKey(currency)) {
            Object obj2 = mapOf.get(currency);
            Intrinsics.checkNotNull(obj2);
            i2 = ((Number) obj2).intValue();
        } else {
            i2 = 2;
        }
        numberFormat.setMinimumFractionDigits(Math.min(defaultFractionDigits, i2));
        if (mapOf.containsKey(currency)) {
            Object obj3 = mapOf.get(currency);
            Intrinsics.checkNotNull(obj3);
            i3 = ((Number) obj3).intValue();
        }
        numberFormat.setMaximumFractionDigits(i3);
        String format = numberFormat.format(parseDouble);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0211s0)) {
            return false;
        }
        C0211s0 c0211s0 = (C0211s0) obj;
        return this.a == c0211s0.a && Intrinsics.areEqual(this.b, c0211s0.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (Long.hashCode(this.a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CreditCardDCCCurrencyModel(amount=");
        sb.append(this.a);
        sb.append(", currency=");
        return N7.a(sb, this.b, ')');
    }
}
