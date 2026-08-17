package ch.datatrans.payment;

import android.content.res.Resources;
import android.util.Log;
import androidx.core.os.ConfigurationCompat;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTransaction;
import ch.datatrans.payment.exception.InitializationException;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import io.sentry.SentryBaseEvent;
import io.sentry.Session;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class E5 {
    public final JSONObject a;
    public final JSONObject b;
    public final Lazy c;
    public final Lazy d;
    public final Lazy e;
    public final Lazy f;
    public final Lazy g;
    public final Lazy h;
    public final Lazy i;
    public final Lazy j;
    public final Lazy k;
    public final Lazy l;
    public final JSONObject m;

    public E5(String jsonString, String mobileToken, final boolean z) {
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        JSONObject jSONObject = new JSONObject(jsonString);
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter("error", "name");
        JSONObject jSONObject2 = jSONObject.has("error") ? jSONObject.getJSONObject("error") : null;
        if (jSONObject2 != null) {
            String c = l7.c(jSONObject2, "code");
            if (Intrinsics.areEqual(c, "TRANSACTION_NOT_FOUND")) {
                throw new InitializationException("Unknown mobile token: " + mobileToken, null, 2, null);
            }
            if (c != null && !StringsKt.isBlank(c)) {
                throw new InitializationException("An error has occurred with code: ".concat(c), null, 2, null);
            }
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject("detail").getJSONObject(Session.JsonKeys.INIT);
        JSONObject jSONObject4 = jSONObject.getJSONObject("dcc");
        Intrinsics.checkNotNullExpressionValue(jSONObject4, "getJSONObject(...)");
        this.b = jSONObject4;
        String name = PaymentMethodType.POST_FINANCE_PAY.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String();
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        this.m = jSONObject.has(name) ? jSONObject.getJSONObject(name) : null;
        JSONObject jSONObject5 = jSONObject3.getJSONObject(SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullExpressionValue(jSONObject5, "getJSONObject(...)");
        this.a = jSONObject5;
        this.c = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(E5.a(E5.this, z));
            }
        });
        this.d = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.a(E5.this);
            }
        });
        this.e = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.f(E5.this);
            }
        });
        this.f = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.e(E5.this);
            }
        });
        this.g = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.c(E5.this);
            }
        });
        this.l = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.d(E5.this);
            }
        });
        this.h = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.i(E5.this);
            }
        });
        this.i = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return E5.g(E5.this);
            }
        });
        this.j = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(E5.h(E5.this));
            }
        });
        this.k = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.E5$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(E5.b(E5.this));
            }
        });
    }

    public static final boolean a(E5 e5, boolean z) {
        int i = e5.a.getInt(CardsGiftcardTransaction.SERIALIZED_NAME_AMOUNT);
        Boolean a = l7.a(e5.a, "useAlias");
        String c = l7.c(e5.a, "aliasCC");
        if (i > 0 || Intrinsics.areEqual(a, Boolean.FALSE)) {
            return true;
        }
        if (a == null) {
            return c != null || z;
        }
        return false;
    }

    public static final boolean b(E5 e5) {
        Boolean a = l7.a(e5.a, "useSplitMode");
        if (a != null) {
            return a.booleanValue();
        }
        return false;
    }

    public static final List c(E5 e5) {
        List createListBuilder = CollectionsKt.createListBuilder();
        JSONObject jSONObject = e5.b;
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter("enabledPaymentMethods", "name");
        JSONArray jSONArray = jSONObject.has("enabledPaymentMethods") ? jSONObject.getJSONArray("enabledPaymentMethods") : null;
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String string = jSONArray.getString(i);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                createListBuilder.add(string);
            }
        }
        return CollectionsKt.build(createListBuilder);
    }

    public static final List d(E5 e5) {
        List createListBuilder = CollectionsKt.createListBuilder();
        JSONObject jSONObject = e5.b;
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter("preselectionEnabledPaymentMethods", "name");
        JSONArray jSONArray = jSONObject.has("preselectionEnabledPaymentMethods") ? jSONObject.getJSONArray("preselectionEnabledPaymentMethods") : null;
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String string = jSONArray.getString(i);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                createListBuilder.add(string);
            }
        }
        return CollectionsKt.build(createListBuilder);
    }

    public static final List e(E5 e5) {
        List createListBuilder = CollectionsKt.createListBuilder();
        JSONArray jSONArray = e5.a.getJSONArray("paymentmethod");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            PaymentMethodType.Companion companion = PaymentMethodType.INSTANCE;
            String string = jSONArray.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            PaymentMethodType fromIdentifier = companion.fromIdentifier(string);
            if (fromIdentifier != null) {
                createListBuilder.add(fromIdentifier);
            }
        }
        return CollectionsKt.build(createListBuilder);
    }

    public static final W8 f(E5 e5) {
        int i;
        int i2;
        String string = e5.a.getString("merchantId");
        String string2 = e5.a.getString("refno");
        String c = l7.c(e5.a, "refno2");
        String c2 = l7.c(e5.a, "refno3");
        String currency = e5.a.getString("currency");
        int i3 = e5.a.getInt(CardsGiftcardTransaction.SERIALIZED_NAME_AMOUNT);
        Intrinsics.checkNotNull(currency);
        Intrinsics.checkNotNullParameter(currency, "currency");
        int i4 = 2;
        Map mapOf = MapsKt.mapOf(TuplesKt.to("BIF", 0), TuplesKt.to("BYR", 0), TuplesKt.to("CLF", 0), TuplesKt.to("CLP", 0), TuplesKt.to("DJF", 0), TuplesKt.to("GNF", 0), TuplesKt.to("ISK", 0), TuplesKt.to("JPY", 0), TuplesKt.to("KMF", 0), TuplesKt.to("KRW", 0), TuplesKt.to("PYG", 0), TuplesKt.to("RWF", 0), TuplesKt.to("UGX", 0), TuplesKt.to("UYI", 0), TuplesKt.to("VND", 0), TuplesKt.to("VUV", 0), TuplesKt.to("XAF", 0), TuplesKt.to("XOF", 0), TuplesKt.to("XPF", 0), TuplesKt.to("BHD", 3), TuplesKt.to("IQD", 3), TuplesKt.to("JOD", 3), TuplesKt.to("KWD", 3), TuplesKt.to("LYD", 3), TuplesKt.to("OMR", 3), TuplesKt.to("TND", 3));
        BigDecimal valueOf = BigDecimal.valueOf(i3);
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
            i4 = ((Number) obj3).intValue();
        }
        currencyInstance.setMaximumFractionDigits(i4);
        String format = currencyInstance.format(parseDouble);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNull(string2);
        return new W8(i3, currency, string, string2, c, c2, format);
    }

    public static final X9 g(E5 e5) {
        X9 x9;
        String key = l7.c(e5.a, "reqtype");
        if (key == null) {
            return X9.c;
        }
        X9.b.getClass();
        Intrinsics.checkNotNullParameter(key, "key");
        X9[] values = X9.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                x9 = null;
                break;
            }
            x9 = values[i];
            if (Intrinsics.areEqual(x9.a, key)) {
                break;
            }
            i++;
        }
        if (x9 != null) {
            return x9;
        }
        X9 x92 = X9.c;
        Log.e("DTPL", "Unsupported reqtype: " + key + ", using: NOA");
        return x92;
    }

    public static final boolean h(E5 e5) {
        return Intrinsics.areEqual(l7.a(e5.a, "useAlias"), Boolean.TRUE) || !((Boolean) e5.c.getValue()).booleanValue();
    }

    public static final SavedPaymentMethod i(E5 e5) {
        if (!e5.a.has("aliasCC")) {
            return null;
        }
        if (((List) e5.f.getValue()).size() != 1) {
            throw new IllegalStateException("More than one payment method given for alias payment");
        }
        SavedPaymentMethod.Companion companion = SavedPaymentMethod.INSTANCE;
        String jSONObject = e5.a.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return companion.create(jSONObject);
    }

    public static final C0131e a(E5 e5) {
        String string = e5.a.getString("merchantId");
        String string2 = e5.a.getString("currency");
        String string3 = e5.a.getString("refno");
        String c = l7.c(e5.a, "refno2");
        String c2 = l7.c(e5.a, "refno3");
        Intrinsics.checkNotNull(string);
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNull(string3);
        return new C0131e(string, string2, string3, c, c2);
    }
}
