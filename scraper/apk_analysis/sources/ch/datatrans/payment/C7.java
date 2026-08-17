package ch.datatrans.payment;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import ch.datatrans.payment.exception.BackendException;
import ch.datatrans.payment.paymentmethods.Card;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.openid.appauth.ResponseTypeValues;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes3.dex */
public final class C7 {
    public final String a;
    public final E7 b;
    public final wd c;
    public final boolean d;

    public C7(String mobileToken, E7 urls, wd version, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(mobileToken, "mobileToken");
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(version, "version");
        this.a = mobileToken;
        this.b = urls;
        this.c = version;
        this.d = z2;
        try {
            if (z) {
                MapsKt.toMutableMap(version.b()).put("uppLibPinningEnabled", BooleanUtils.YES);
                C0207r5.b();
                return;
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager trustManager = trustManagerFactory.getTrustManagers()[0];
            Intrinsics.checkNotNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            C0207r5.a = new C0242vb(new TrustManager[]{(X509TrustManager) trustManager});
        } catch (Exception unused) {
            Log.e("DTPL", "Could not setup TLS capability");
        }
    }

    public static final String b(C7 c7, String str) {
        URL g = c7.b.g();
        Sa sa = new Sa();
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String str2 = (String) c7.a().get("1");
        String str3 = (String) c7.a().get(ExifInterface.GPS_MEASUREMENT_2D);
        String str4 = (String) c7.a().get(ExifInterface.GPS_MEASUREMENT_3D);
        String p4 = g.getHost();
        Intrinsics.checkNotNullExpressionValue(p4, "getHost(...)");
        String p5 = g.getPath();
        Intrinsics.checkNotNullExpressionValue(p5, "getPath(...)");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        sa.a = str2;
        sa.b = str3;
        sa.c = str4;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        sa.d = format;
        sa.e = p4;
        sa.f = p5;
        sa.g = bytes;
        return C0207r5.a(C0207r5.a(g, null, MediaType.APPLICATION_JSON, bytes, a(c7, sa), 0, 0, false, 448));
    }

    public final Map a() {
        wd wdVar = this.c;
        wdVar.getClass();
        Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("1", "android-sdk"), TuplesKt.to(ExifInterface.GPS_MEASUREMENT_2D, "3.9.2"));
        R9 preferencesRepository = new R9(wdVar.a);
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter("DTPL_INSTALLATION_ID", "key");
        String string = preferencesRepository.a.getString("DTPL_INSTALLATION_ID", null);
        if (string != null) {
            mutableMapOf.put(ExifInterface.GPS_MEASUREMENT_3D, string);
        }
        return mutableMapOf;
    }

    public final Object a(final String str, final String str2, M5 m5) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, str, str2);
            }
        }, "Unable to request initial transaction request.", m5);
    }

    public static final String a(C7 c7, String str, String str2) {
        URL url = c7.b.f();
        Map headers = MapsKt.mapOf(TuplesKt.to(HttpHeaders.AUTHORIZATION, "Bearer " + str), TuplesKt.to("Idempotency-Key", str2), TuplesKt.to("Client-Info", c7.c.c()));
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        C0207r5.a();
        URLConnection openConnection = url.openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        C0242vb c0242vb = C0207r5.a;
        if (c0242vb != null) {
            httpsURLConnection.setSSLSocketFactory(c0242vb);
        }
        C0207r5.a();
        for (Map.Entry entry : headers.entrySet()) {
            httpsURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        httpsURLConnection.setConnectTimeout(30000);
        httpsURLConnection.setReadTimeout(30000);
        C0207r5.a();
        C0207r5.a(httpsURLConnection, false);
        InputStream inputStream = httpsURLConnection.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
        return C0207r5.a(inputStream);
    }

    public final Object a(final String str, ContinuationImpl continuationImpl) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.b(C7.this, str);
            }
        }, "Unable to tokenize.", continuationImpl);
    }

    public final Object a(final String str, final Card card, final LinkedHashMap linkedHashMap, C0252x2 c0252x2) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, str, card, linkedHashMap);
            }
        }, "Unable to request card alias.", c0252x2);
    }

    public static final I a(C7 c7, String str, Card card, Map map) {
        String xmlData = a(c7, c7.b.a(), Lf.a(MapsKt.toMutableMap(c7.c.b()), str, card, map), MediaType.TEXT_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "aliasCC", false);
            String a2 = He.a(xmlData, "maskedCC", false);
            Intrinsics.checkNotNull(a);
            Intrinsics.checkNotNull(a2);
            return new I(a, a2);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The card alias response cannot be parsed.", e3);
        }
    }

    public final Object a(final String str, final PaymentMethodType paymentMethodType, final String str2, final String str3, U0 u0) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, str, paymentMethodType, str2, str3);
            }
        }, "Unable to request card dcc service request.", u0);
    }

    public static final X0 a(C7 c7, String str, PaymentMethodType paymentMethodType, String str2, String str3) {
        String replace$default;
        String xmlData = a(c7, c7.b.i(), Lf.a(MapsKt.toMutableMap(c7.c.b()), str, paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String(), str2, str3), MediaType.TEXT_XML, false, 72);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "dccId", false);
            String a2 = He.a(xmlData, "dccCurrency", true);
            String a3 = He.a(xmlData, "dccAmount", true);
            String a4 = He.a(xmlData, "dccRate", true);
            String a5 = He.a(xmlData, "dccMarkupPercentage", true);
            Intrinsics.checkNotNull(a);
            return new X0(a, a2, (a3 == null || (replace$default = StringsKt.replace$default(a3, ".", "", false, 4, (Object) null)) == null) ? null : Long.valueOf(Long.parseLong(replace$default)), a4, a5);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The card dcc response cannot be parsed.", e3);
        }
    }

    public final Object a(final URL url, final String str, final boolean z, C0157i c0157i) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, url, str, z);
            }
        }, "Unable to authorize.", c0157i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x006e, code lost:
    
        r9 = java.lang.Integer.parseInt(r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final ch.datatrans.payment.C0143g a(ch.datatrans.payment.C7 r9, java.net.URL r10, java.lang.String r11, boolean r12) {
        /*
            java.lang.String r0 = "uppTransactionId"
            java.lang.String r1 = "acqErrorCode"
            java.lang.String r5 = "text/xml"
            r7 = 56
            r2 = r9
            r3 = r10
            r4 = r11
            r6 = r12
            java.lang.String r9 = a(r2, r3, r4, r5, r6, r7)
            java.lang.String r10 = "xmlData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r10)
            java.lang.String r10 = "errorCode"
            r11 = 1
            java.lang.String r10 = ch.datatrans.payment.He.a(r9, r10, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r12 = ch.datatrans.payment.He.a(r9, r1, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r2 = "errorMessage"
            java.lang.String r4 = ch.datatrans.payment.He.a(r9, r2, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r2 = "errorDetail"
            java.lang.String r5 = ch.datatrans.payment.He.a(r9, r2, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r7 = ch.datatrans.payment.He.a(r9, r0, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            if (r10 != 0) goto L6c
            if (r4 != 0) goto L6c
            if (r5 == 0) goto L37
            goto L6c
        L37:
            java.lang.String r10 = "responseCode"
            r12 = 0
            java.lang.String r10 = ch.datatrans.payment.He.a(r9, r10, r12)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.Integer.parseInt(r10)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r10 = "aliasCC"
            java.lang.String r10 = ch.datatrans.payment.He.a(r9, r10, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r12 = "maskedCC"
            ch.datatrans.payment.He.a(r9, r12, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r11 = ch.datatrans.payment.He.a(r9, r0, r11)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r12 = "request"
            java.util.Map r12 = ch.datatrans.payment.He.a(r12, r9)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.lang.String r0 = "response"
            java.util.Map r9 = ch.datatrans.payment.He.a(r0, r9)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            java.util.Map r9 = kotlin.collections.MapsKt.toMutableMap(r9)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            r9.putAll(r12)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            ch.datatrans.payment.g r12 = new ch.datatrans.payment.g     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            r12.<init>(r10, r11, r9)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            return r12
        L6c:
            if (r10 == 0) goto L73
            int r9 = java.lang.Integer.parseInt(r10)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            goto L74
        L73:
            r9 = -1
        L74:
            r6 = r9
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            r8.<init>()     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            if (r12 == 0) goto L7f
            r8.put(r1, r12)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
        L7f:
            ch.datatrans.payment.exception.BackendException r9 = new ch.datatrans.payment.exception.BackendException     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            r3 = r9
            r3.<init>(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
            throw r9     // Catch: java.lang.Exception -> L86 ch.datatrans.payment.Ie -> L8f ch.datatrans.payment.exception.BackendException -> L91
        L86:
            r9 = move-exception
            ch.datatrans.payment.Ie r10 = new ch.datatrans.payment.Ie
            java.lang.String r11 = "The authorization response cannot be parsed."
            r10.<init>(r11, r9)
            throw r10
        L8f:
            r9 = move-exception
            throw r9
        L91:
            r9 = move-exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C7.a(ch.datatrans.payment.C7, java.net.URL, java.lang.String, boolean):ch.datatrans.payment.g");
    }

    public final Object a(final W8 w8, final PaymentMethodType paymentMethodType, final Q5 q5, SuspendLambda suspendLambda) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, w8, paymentMethodType, q5);
            }
        }, "Unable to request Token.", suspendLambda);
    }

    public static final Lb a(C7 c7, W8 w8, PaymentMethodType paymentMethod, Q5 q5) {
        String xmlData = a(c7, c7.b.e(), Lf.a(MapsKt.toMutableMap(c7.c.b()), Z8.a(w8, null), paymentMethod, q5, (Object) null), MediaType.TEXT_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        Intrinsics.checkNotNullParameter(paymentMethod, "paymentMethod");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "uppTransactionId", false);
            Intrinsics.checkNotNull(a);
            String a2 = He.a(xmlData, ResponseTypeValues.TOKEN, false);
            Intrinsics.checkNotNull(a2);
            return new Lb(a, a2, paymentMethod == PaymentMethodType.KLARNA ? He.a(xmlData, "paymentMethodCategories", true) : null);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The status response cannot be parsed.", e3);
        }
    }

    public final Object a(final W8 w8, final PaymentMethodType paymentMethodType, final Q5 q5, final String str, od odVar) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, w8, paymentMethodType, q5, str);
            }
        }, "Unable to request Token.", odVar);
    }

    public static final C0188nd a(C7 c7, W8 w8, PaymentMethodType paymentMethodType, Q5 q5, String str) {
        String xmlData = a(c7, c7.b.e(), Lf.a(MapsKt.toMutableMap(c7.c.b()), w8, paymentMethodType, q5, str), MediaType.TEXT_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            boolean parseBoolean = Boolean.parseBoolean(He.a(xmlData, "requires2FAConfirmation", true));
            String a = He.a(xmlData, "uppTransactionId", false);
            Intrinsics.checkNotNull(a);
            return new C0188nd(a, parseBoolean);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The status response cannot be parsed.", e3);
        }
    }

    public final Object a(final Y8 y8, final PaymentMethodType paymentMethodType, final Q5 q5, final Object obj, T9 t9) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, y8, paymentMethodType, q5, obj);
            }
        }, "Unable to request Redirect Url.", t9);
    }

    public static final V9 a(C7 c7, Y8 y8, PaymentMethodType paymentMethodType, Q5 q5, Object obj) {
        String xmlData = a(c7, c7.b.e(), Lf.a(MapsKt.toMutableMap(c7.c.b()), y8, paymentMethodType, q5, obj), MediaType.TEXT_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "uppTransactionId", false);
            Intrinsics.checkNotNull(a);
            String a2 = He.a(xmlData, "redirectUrl", false);
            Intrinsics.checkNotNull(a2);
            return new V9(a, a2);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The status response cannot be parsed.", e3);
        }
    }

    public final Object a(final C0142fe c0142fe, final C0130de c0130de, final PaymentMethodType paymentMethodType, SuspendLambda suspendLambda) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, c0130de, c0142fe, paymentMethodType);
            }
        }, "Unable to start wallet service.", suspendLambda);
    }

    public static final C0137ee a(C7 c7, C0130de c0130de, C0142fe c0142fe, PaymentMethodType paymentMethodType) {
        String xmlData = a(c7, c7.b.j(), Lf.a(MapsKt.toMutableMap(c7.c.b()), c0130de, c0142fe, paymentMethodType), MediaType.TEXT_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "uppTransactionId", false);
            boolean parseBoolean = Boolean.parseBoolean(He.a(xmlData, "isTokenized", true));
            String a2 = He.a(xmlData, "expm", true);
            Integer valueOf = a2 != null ? Integer.valueOf(Integer.parseInt(a2)) : null;
            String a3 = He.a(xmlData, "expy", true);
            Integer valueOf2 = a3 != null ? Integer.valueOf(Integer.parseInt(a3)) : null;
            String a4 = He.a(xmlData, "last4", true);
            CardExpiryDate cardExpiryDate = (valueOf == null || valueOf2 == null) ? null : new CardExpiryDate(valueOf.intValue(), valueOf2.intValue());
            String a5 = He.a(xmlData, "pmethod", true);
            PaymentMethodType fromIdentifier = a5 != null ? PaymentMethodType.INSTANCE.fromIdentifier(a5) : null;
            String a6 = He.a(xmlData, "aliasCC", true);
            String a7 = He.a(xmlData, "maskedCC", true);
            Intrinsics.checkNotNull(a);
            return new C0137ee(a, Boolean.valueOf(parseBoolean), cardExpiryDate, fromIdentifier, a6, a7, a4);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The wallet service response cannot be parsed.", e3);
        }
    }

    public final Object a(final C0131e c0131e, final LinkedHashMap linkedHashMap, C0170kd c0170kd) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, c0131e, linkedHashMap);
            }
        }, "Unable to start TWINT registration.", c0170kd);
    }

    public static final Lb a(C7 c7, C0131e c0131e, Map map) {
        String xmlData = a(c7, c7.b.b(), Lf.a(MapsKt.toMutableMap(c7.c.b()), c0131e, map), MediaType.APPLICATION_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "transactionId", false);
            String a2 = He.a(xmlData, ResponseTypeValues.TOKEN, false);
            Intrinsics.checkNotNull(a);
            Intrinsics.checkNotNull(a2);
            return new Lb(a, a2, null);
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The status response cannot be parsed.", e3);
        }
    }

    public final Object a(final C0131e c0131e, final String str, final LinkedHashMap linkedHashMap, C0166jd c0166jd) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, c0131e, str, linkedHashMap);
            }
        }, "Unable to acquire TWINT alias.", c0166jd);
    }

    public static final C0249wb a(C7 c7, C0131e c0131e, String str, Map map) {
        String xmlData = a(c7, c7.b.l(), Lf.a(MapsKt.toMutableMap(c7.c.b()), c0131e, str, map), MediaType.APPLICATION_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "status", false);
            if (Intrinsics.areEqual("CONFIRMED", a)) {
                String a2 = He.a(xmlData, SavedPaymentMethod.ALIAS_KEY, false);
                Intrinsics.checkNotNull(a2);
                return new C0249wb(a2);
            }
            throw new D7("Registration has not been completed (status: " + a + ')');
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The status response cannot be parsed.", e3);
        }
    }

    public final Object a(final H h, C0155hd c0155hd) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, h);
            }
        }, "Unable to request a transaction cancel.", c0155hd);
    }

    public static final String a(C7 c7, H h) {
        String xmlData = a(c7, c7.b.d(), Lf.a(h, MapsKt.toMutableMap(c7.c.b())), MediaType.APPLICATION_XML, false, 120);
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        try {
            Lf.a(xmlData);
            String a = He.a(xmlData, "responseCode", true);
            Intrinsics.checkNotNull(a);
            return a;
        } catch (Ie e) {
            throw e;
        } catch (BackendException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new Ie("The status response cannot be parsed.", e3);
        }
    }

    public final Object a(final String str, C0198q c0198q) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, str);
            }
        }, "Unable to request bin ranges file", c0198q);
    }

    public static final p4 a(C7 c7, String etag) {
        URL url = c7.b.c();
        Sa sa = new Sa();
        String str = (String) c7.a().get("1");
        String str2 = (String) c7.a().get(ExifInterface.GPS_MEASUREMENT_2D);
        String str3 = (String) c7.a().get(ExifInterface.GPS_MEASUREMENT_3D);
        String p4 = url.getHost();
        Intrinsics.checkNotNullExpressionValue(p4, "getHost(...)");
        String p5 = url.getPath();
        Intrinsics.checkNotNullExpressionValue(p5, "getPath(...)");
        Intrinsics.checkNotNullParameter(p4, "p4");
        Intrinsics.checkNotNullParameter(p5, "p5");
        sa.a = str;
        sa.b = str2;
        sa.c = str3;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        sa.d = format;
        sa.e = p4;
        sa.f = p5;
        sa.g = null;
        Map a = a(c7, sa);
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(etag, "etag");
        C0207r5.a();
        URLConnection openConnection = url.openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        C0207r5.a();
        if (a != null) {
            for (Map.Entry entry : a.entrySet()) {
                httpsURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        httpsURLConnection.setRequestProperty(HttpHeaders.IF_NONE_MATCH, etag);
        httpsURLConnection.setConnectTimeout(30000);
        httpsURLConnection.setReadTimeout(30000);
        C0207r5.a();
        if (httpsURLConnection.getResponseCode() != 304) {
            C0207r5.a(httpsURLConnection, false);
            Pair pair = new Pair(httpsURLConnection.getInputStream(), httpsURLConnection.getHeaderField("etag"));
            return new p4(C0207r5.a((InputStream) pair.getFirst()), (String) pair.getSecond());
        }
        throw new BackendException("etag matches the one on server", "", 304, "", null, 16, null);
    }

    public final Object a(final String str, final boolean z, SuspendLambda suspendLambda) {
        return a(new Function0() { // from class: ch.datatrans.payment.C7$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C7.a(C7.this, z, str);
            }
        }, "Unable to request 3-D status.", suspendLambda);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0058, code lost:
    
        r10 = java.lang.Integer.parseInt(r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final ch.datatrans.payment.Ya a(ch.datatrans.payment.C7 r10, boolean r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "uppTransactionId"
            java.lang.String r1 = "acqErrorCode"
            ch.datatrans.payment.E7 r2 = r10.b
            java.net.URL r4 = r2.a(r11)
            r7 = 0
            r8 = 120(0x78, float:1.68E-43)
            java.lang.String r6 = "text/xml"
            r3 = r10
            r5 = r12
            java.lang.String r10 = a(r3, r4, r5, r6, r7, r8)
            java.lang.String r11 = "xmlData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r11)
            java.lang.String r11 = "errorCode"
            r12 = 1
            java.lang.String r11 = ch.datatrans.payment.He.a(r10, r11, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            java.lang.String r2 = ch.datatrans.payment.He.a(r10, r1, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            java.lang.String r3 = "errorMessage"
            java.lang.String r5 = ch.datatrans.payment.He.a(r10, r3, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            java.lang.String r3 = "errorDetail"
            java.lang.String r6 = ch.datatrans.payment.He.a(r10, r3, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            java.lang.String r8 = ch.datatrans.payment.He.a(r10, r0, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            if (r11 != 0) goto L56
            if (r5 != 0) goto L56
            if (r6 == 0) goto L3c
            goto L56
        L3c:
            java.lang.String r11 = "enrolled"
            r12 = 0
            java.lang.String r11 = ch.datatrans.payment.He.a(r10, r11, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            java.lang.String r1 = "Y"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r11)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            java.lang.String r10 = ch.datatrans.payment.He.a(r10, r0, r12)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            ch.datatrans.payment.Ya r12 = new ch.datatrans.payment.Ya     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            r12.<init>(r10, r11)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            return r12
        L56:
            if (r11 == 0) goto L5d
            int r10 = java.lang.Integer.parseInt(r11)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            goto L5e
        L5d:
            r10 = -1
        L5e:
            r7 = r10
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            r9.<init>()     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            if (r2 == 0) goto L69
            r9.put(r1, r2)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
        L69:
            ch.datatrans.payment.exception.BackendException r10 = new ch.datatrans.payment.exception.BackendException     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            r4 = r10
            r4.<init>(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
            throw r10     // Catch: java.lang.Exception -> L70 ch.datatrans.payment.Ie -> L79 ch.datatrans.payment.exception.BackendException -> L7b
        L70:
            r10 = move-exception
            ch.datatrans.payment.Ie r11 = new ch.datatrans.payment.Ie
            java.lang.String r12 = "The status response cannot be parsed."
            r11.<init>(r12, r10)
            throw r11
        L79:
            r10 = move-exception
            throw r10
        L7b:
            r10 = move-exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C7.a(ch.datatrans.payment.C7, boolean, java.lang.String):ch.datatrans.payment.Ya");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.jvm.functions.Function0 r5, java.lang.String r6, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof ch.datatrans.payment.y7
            if (r0 == 0) goto L13
            r0 = r7
            ch.datatrans.payment.y7 r0 = (ch.datatrans.payment.y7) r0
            int r1 = r0.d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.d = r1
            goto L18
        L13:
            ch.datatrans.payment.y7 r0 = new ch.datatrans.payment.y7
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r4 = r0.b
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.d
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.String r6 = r0.e
            java.lang.Object r5 = r0.a
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            kotlin.ResultKt.throwOnFailure(r4)     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            return r4
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            kotlin.ResultKt.throwOnFailure(r4)
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            ch.datatrans.payment.z7 r1 = new ch.datatrans.payment.z7     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            r3 = 0
            r1.<init>(r5, r3)     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            r0.a = r5     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            r0.e = r6     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            r0.d = r2     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt.withContext(r4, r1, r0)     // Catch: java.lang.Exception -> L56 java.io.IOException -> L58 javax.net.ssl.SSLException -> L5f
            if (r4 != r7) goto L55
            return r7
        L55:
            return r4
        L56:
            r4 = move-exception
            throw r4
        L58:
            r4 = move-exception
            ch.datatrans.payment.D7 r5 = new ch.datatrans.payment.D7
            r5.<init>(r6, r4)
            throw r5
        L5f:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C7.a(kotlin.jvm.functions.Function0, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static String a(C7 c7, URL url, String str, String str2, boolean z, int i) {
        int i2 = (i & 16) != 0 ? 30000 : 15000;
        int i3 = (i & 32) != 0 ? 30000 : 15000;
        boolean z2 = (i & 64) != 0 ? false : z;
        c7.getClass();
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        String str3 = c7.a;
        String str4 = str2 + "; charset=" + UTF_8.name();
        byte[] bytes = str.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return C0207r5.a(C0207r5.a(url, str3, str4, bytes, null, i2, i3, z2, 32));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Map a(C7 c7, Sa sa) {
        byte[] key;
        List<Byte> emptyList;
        Map emptyMap = MapsKt.emptyMap();
        byte[] info = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{sa.a, sa.b, sa.c, sa.d, sa.e, sa.f, null}), "", null, null, 0, null, null, 62, null).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(info, "getBytes(...)");
        byte[] bArr = sa.g;
        boolean z = c7.d;
        Intrinsics.checkNotNullParameter(info, "info");
        Qa qa = new Qa();
        if (z) {
            key = x4.a(Sa.h, Sa.i);
        } else {
            key = x4.a(Sa.i, Sa.h);
        }
        List<Byte> mutableList = ArraysKt.toMutableList(info);
        if (bArr == null || (emptyList = ArraysKt.toList(bArr)) == null) {
            emptyList = CollectionsKt.emptyList();
        }
        mutableList.addAll(emptyList);
        Unit unit = Unit.INSTANCE;
        byte[] message = CollectionsKt.toByteArray(mutableList);
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(key, "<this>");
        List<List> chunked = CollectionsKt.chunked(ArraysKt.toList(key), 2);
        ArrayList arrayList = new ArrayList();
        for (List list : chunked) {
            CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new Byte[]{list.get(1), list.get(0)}));
        }
        byte[] byteArray = CollectionsKt.toByteArray(arrayList);
        if (byteArray.length == 64) {
            byte[] bArr2 = new byte[64];
            for (int i = 0; i < 64; i++) {
                bArr2[i] = 92;
            }
            byte[] bArr3 = new byte[64];
            for (int i2 = 0; i2 < 64; i2++) {
                bArr3[i2] = 54;
            }
            for (int i3 = 0; i3 < 64; i3++) {
                bArr2[i3] = (byte) (bArr2[i3] ^ byteArray[i3]);
                bArr3[i3] = (byte) (bArr3[i3] ^ byteArray[i3]);
            }
            byte[] bArr4 = new byte[message.length + 64];
            for (int i4 = 0; i4 < 64; i4++) {
                bArr4[i4] = bArr3[i4];
            }
            int length = message.length;
            for (int i5 = 0; i5 < length; i5++) {
                bArr4[64 + i5] = message[i5];
            }
            byte[] a = qa.a(bArr4);
            byte[] bArr5 = new byte[96];
            for (int i6 = 0; i6 < 64; i6++) {
                bArr5[i6] = bArr2[i6];
            }
            for (int i7 = 0; i7 < 32; i7++) {
                bArr5[64 + i7] = a[i7];
            }
            byte[] a2 = qa.a(bArr5);
            String a3 = og.a();
            String lowerCase = CollectionsKt.joinToString$default(Ta.c, "", null, null, 0, null, null, 62, null).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            StringBuilder sb = new StringBuilder();
            int length2 = lowerCase.length();
            for (int i8 = 0; i8 < length2; i8++) {
                char charAt = lowerCase.charAt(i8);
                if (Character.isLetter(charAt)) {
                    sb.append(charAt);
                }
            }
            String sb2 = sb.toString();
            Pair pair = TuplesKt.to("Client-Info", c7.c.c());
            Pair pair2 = TuplesKt.to(HttpHeaders.DATE, sa.d);
            if (a3.length() > 0) {
                StringBuilder sb3 = new StringBuilder();
                String valueOf = String.valueOf(a3.charAt(0));
                Intrinsics.checkNotNull(valueOf, "null cannot be cast to non-null type java.lang.String");
                String upperCase = valueOf.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                sb3.append((Object) upperCase);
                String substring = a3.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                sb3.append(substring);
                a3 = sb3.toString();
            }
            Map mutableMapOf = MapsKt.mutableMapOf(pair, pair2, TuplesKt.to(a3, sb2 + ' ' + x4.a(a2)));
            mutableMapOf.putAll(emptyMap);
            return MapsKt.toMap(mutableMapOf);
        }
        throw new IllegalArgumentException("invalid length");
    }
}
