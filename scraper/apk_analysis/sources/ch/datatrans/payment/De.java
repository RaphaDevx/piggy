package ch.datatrans.payment;

import android.util.Log;
import ch.coop.apidia.appGateway.cards.model.CardsGiftcardTransaction;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import io.sentry.rrweb.RRWebVideoEvent;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.apache.commons.lang3.BooleanUtils;

/* loaded from: classes3.dex */
public final class De {
    public final E7 a;
    public final C0156he b;

    public De(E7 urls, C0156he callbackUrls) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(callbackUrls, "callbackUrls");
        this.a = urls;
        this.b = callbackUrls;
    }

    public final String a(W8 w8, Nb nb) {
        LinkedHashMap parameters = a(w8, nb, null);
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        Intrinsics.checkNotNullParameter("UTF-8", RRWebVideoEvent.JsonKeys.ENCODING);
        StringBuilder sb = new StringBuilder();
        Iterator it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String encode = URLEncoder.encode((String) entry.getKey(), "UTF-8");
            Iterator it2 = ((List) entry.getValue()).iterator();
            while (it2.hasNext()) {
                String encode2 = URLEncoder.encode((String) it2.next(), "UTF-8");
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
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.a.b((nb.n == null || nb.i == PaymentMethodType.HALF_FARE_PLUS) ? false : true));
        sb3.append(sb2);
        return sb3.toString();
    }

    public final LinkedHashMap a(W8 w8, Nb nb, N9 n9) {
        V8 v8;
        Map a;
        PaymentMethodType paymentMethodType = nb.i;
        if (paymentMethodType != null) {
            HashMap hashMap = new HashMap(nb.h.k);
            Q5 q5 = nb.h;
            String str = nb.a;
            wd wdVar = null;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mobileToken");
                str = null;
            }
            try {
                String substring = str.substring(32);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                hashMap.put("mobileTokenTransactionId", String.valueOf(Long.parseLong(substring, CharsKt.checkRadix(16))));
            } catch (NumberFormatException e) {
                Log.e("DTPL", "Unparseable mobile token", e);
            }
            hashMap.put("testOnly", q5.v ? BooleanUtils.YES : BooleanUtils.NO);
            hashMap.put("merchantId", w8.c);
            hashMap.put("language", q5.j);
            if (w8.a > 0) {
                hashMap.put("useSplitMode", "true");
            }
            String str2 = nb.n;
            if (str2 != null) {
                hashMap.put("datatransTrxId", str2);
            }
            hashMap.put(CardsGiftcardTransaction.SERIALIZED_NAME_AMOUNT, String.valueOf(w8.a));
            hashMap.put("currency", w8.b);
            hashMap.put("refno", w8.d);
            String str3 = w8.e;
            if (str3 != null) {
                hashMap.put("refno2", str3);
            }
            String str4 = w8.f;
            if (str4 != null) {
                hashMap.put("refno3", str4);
            }
            hashMap.put("reqtype", nb.h.n.a);
            if (q5.l) {
                hashMap.put("useAlias", BooleanUtils.YES);
                if (paymentMethodType == PaymentMethodType.PAY_PAL) {
                    hashMap.put("uppCustomerDetails", "return");
                }
            }
            SavedPaymentMethod savedPaymentMethod = nb.l;
            if (savedPaymentMethod != null && (savedPaymentMethod.getType() == PaymentMethodType.HALF_FARE_PLUS || savedPaymentMethod.getType() == PaymentMethodType.POST_FINANCE_PAY)) {
                hashMap.put("aliasCC", savedPaymentMethod.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String());
            }
            hashMap.put("cancelUrl", this.b.a);
            hashMap.put("errorUrl", this.b.b);
            hashMap.put("successUrl", this.b.c);
            hashMap.put("uppWebResponseMethod", "GET");
            wd wdVar2 = r4.c;
            if (wdVar2 != null) {
                wdVar = wdVar2;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("version");
            }
            hashMap.putAll(wdVar.b());
            K3 k3 = q5.d;
            if (k3 != null && (a = k3.a()) != null) {
                hashMap.putAll(a);
            }
            K3 k32 = q5.d;
            if (k32 != null) {
                Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
                int i = J3.a[paymentMethodType.ordinal()];
                if (i == 1 || i == 2) {
                    k32.a(paymentMethodType);
                    K3.a(k32.b, "uppCustomerBirthDate", paymentMethodType);
                    K3.a(k32.e, "uppCustomerEmail", paymentMethodType);
                    K3.a(k32.l, "uppCustomerPhone", paymentMethodType);
                } else if (i == 3) {
                    k32.a(paymentMethodType);
                    K3.a(k32.b, "uppCustomerBirthDate", paymentMethodType);
                    K3.a(k32.g, "uppCustomerGender", paymentMethodType);
                    K3.a(k32.h, "uppCustomerId", paymentMethodType);
                    K3.a(k32.j, "uppCustomerLanguage", paymentMethodType);
                    K3.a(k32.e, "uppCustomerEmail", paymentMethodType);
                    K3.a(k32.m, "uppCustomerType", paymentMethodType);
                } else if (i == 4 || i == 5) {
                    k32.a(paymentMethodType);
                    K3.a(k32.g, "uppCustomerGender", paymentMethodType);
                }
            }
            int i2 = Ce.a[paymentMethodType.ordinal()];
            if (i2 == 1) {
                C0195p0 c0195p0 = q5.c;
                if (c0195p0 != null) {
                    hashMap.putAll(c0195p0.a());
                }
            } else if (i2 == 2) {
                a9 a9Var = q5.q;
                if (a9Var != null) {
                    hashMap.putAll(a9Var.a());
                }
            } else if (i2 == 3) {
                a9 a9Var2 = q5.q;
                if (a9Var2 != null) {
                    hashMap.putAll(a9Var2.a());
                }
            } else if (i2 == 4) {
                tb tbVar = q5.t;
                if (tbVar != null) {
                    hashMap.putAll(MapsKt.mapOf(TuplesKt.to("swisspassCard", tbVar.a), TuplesKt.to("swisspassZip", tbVar.b)));
                }
            } else if (i2 == 5 && (v8 = q5.x) != null) {
                hashMap.putAll(MapsKt.mapOf(TuplesKt.to("cardno", v8.a)));
            }
            if (paymentMethodType == PaymentMethodType.EASYPAY) {
                String str5 = q5.e;
                if (str5 != null) {
                    hashMap.put("easyDescription", str5);
                }
                String str6 = q5.f;
                if (str6 != null) {
                    hashMap.put("easyPaymentInfo", str6);
                }
                String str7 = q5.g;
                if (str7 != null) {
                    hashMap.put("easyTitle", str7);
                }
            }
            hashMap.put("uppReturnPfcExpiry", BooleanUtils.YES);
            if (paymentMethodType == PaymentMethodType.POST_FINANCE_CARD) {
                if (n9 != null) {
                    hashMap.put("pfAppInstalled", n9.a ? "true" : BooleanUtils.FALSE);
                    hashMap.put("pfDisplaySmartphone", n9.b ? "true" : BooleanUtils.FALSE);
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
            String str8 = q5.m;
            if (str8 != null) {
                hashMap.put("paysafecardMerchantClientId", str8);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : hashMap.entrySet()) {
                String str9 = (String) entry.getKey();
                String str10 = (String) entry.getValue();
                Object obj = linkedHashMap.get(str9);
                if (obj == null) {
                    obj = new ArrayList();
                    linkedHashMap.put(str9, obj);
                }
                ((List) obj).add(str10);
            }
            String str11 = paymentMethodType.getIo.sentry.protocol.ViewHierarchyNode.JsonKeys.IDENTIFIER java.lang.String();
            Object obj2 = linkedHashMap.get("paymentmethod");
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put("paymentmethod", obj2);
            }
            ((List) obj2).add(str11);
            return linkedHashMap;
        }
        throw new IllegalStateException("Required value was null.");
    }
}
