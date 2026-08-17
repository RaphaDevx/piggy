package ch.datatrans.payment;

import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class b1 extends ViewModel {
    public final MutableLiveData a = new MutableLiveData();
    public final MutableLiveData b = new MutableLiveData();
    public final MutableLiveData c = new MutableLiveData();
    public final MutableLiveData d = new MutableLiveData();
    public final MutableLiveData e = new MutableLiveData();
    public final MutableLiveData f = new MutableLiveData();
    public final MutableLiveData g = new MutableLiveData();
    public final MutableLiveData h = new MutableLiveData();
    public C0211s0 i;
    public C0211s0 j;
    public O2 k;
    public W0 l;

    public final void a(boolean z) {
        int i;
        int i2;
        C0211s0 c0211s0 = this.i;
        C0211s0 c0211s02 = null;
        if (c0211s0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("merchantDCCModel");
            c0211s0 = null;
        }
        c0211s0.c = Boolean.valueOf(!z);
        C0211s0 c0211s03 = this.j;
        if (c0211s03 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardDCCModel");
            c0211s03 = null;
        }
        c0211s03.c = Boolean.valueOf(z);
        this.f.postValue(Boolean.valueOf(z));
        if (z) {
            C0211s0 c0211s04 = this.j;
            if (c0211s04 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cardDCCModel");
            } else {
                c0211s02 = c0211s04;
            }
            String currency = c0211s02.b;
            long j = c0211s02.a;
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
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
            Currency currency2 = Currency.getInstance(currency);
            currencyInstance.setCurrency(currency2);
            int defaultFractionDigits = currency2.getDefaultFractionDigits();
            if (mapOf.containsKey(currency)) {
                Object obj2 = mapOf.get(currency);
                Intrinsics.checkNotNull(obj2);
                i2 = ((Number) obj2).intValue();
            } else {
                i2 = 2;
            }
            currencyInstance.setMinimumFractionDigits(Math.min(defaultFractionDigits, i2));
            if (mapOf.containsKey(currency)) {
                Object obj3 = mapOf.get(currency);
                Intrinsics.checkNotNull(obj3);
                i3 = ((Number) obj3).intValue();
            }
            currencyInstance.setMaximumFractionDigits(i3);
            String format = currencyInstance.format(parseDouble);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            this.a.postValue(format);
            this.b.postValue(Boolean.TRUE);
        }
    }

    public final void b(boolean z) {
        int i;
        int i2;
        C0211s0 c0211s0 = this.i;
        C0211s0 c0211s02 = null;
        if (c0211s0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("merchantDCCModel");
            c0211s0 = null;
        }
        c0211s0.c = Boolean.valueOf(z);
        C0211s0 c0211s03 = this.j;
        if (c0211s03 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardDCCModel");
            c0211s03 = null;
        }
        c0211s03.c = Boolean.valueOf(!z);
        this.g.postValue(Boolean.valueOf(z));
        if (z) {
            C0211s0 c0211s04 = this.i;
            if (c0211s04 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("merchantDCCModel");
            } else {
                c0211s02 = c0211s04;
            }
            String currency = c0211s02.b;
            long j = c0211s02.a;
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
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
            Currency currency2 = Currency.getInstance(currency);
            currencyInstance.setCurrency(currency2);
            int defaultFractionDigits = currency2.getDefaultFractionDigits();
            if (mapOf.containsKey(currency)) {
                Object obj2 = mapOf.get(currency);
                Intrinsics.checkNotNull(obj2);
                i2 = ((Number) obj2).intValue();
            } else {
                i2 = 2;
            }
            currencyInstance.setMinimumFractionDigits(Math.min(defaultFractionDigits, i2));
            if (mapOf.containsKey(currency)) {
                Object obj3 = mapOf.get(currency);
                Intrinsics.checkNotNull(obj3);
                i3 = ((Number) obj3).intValue();
            }
            currencyInstance.setMaximumFractionDigits(i3);
            String format = currencyInstance.format(parseDouble);
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            this.a.postValue(format);
            this.b.postValue(Boolean.TRUE);
        }
    }
}
