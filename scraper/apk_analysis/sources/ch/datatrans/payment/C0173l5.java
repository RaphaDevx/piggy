package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.ShippingAddressParameters;
import java.util.Collection;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: ch.datatrans.payment.l5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0173l5 {
    public final GooglePayConfig a;

    public C0173l5(GooglePayConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.a = config;
    }

    public final JSONObject a(Y8 paymentData) {
        Intrinsics.checkNotNullParameter(paymentData, "paymentData");
        JSONObject put = new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
        Intrinsics.checkNotNullExpressionValue(put, "put(...)");
        JSONArray jSONArray = new JSONArray();
        String str = paymentData.a;
        JSONObject a = a();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "PAYMENT_GATEWAY");
        jSONObject.put("parameters", new JSONObject().put("gateway", "datatrans").put("gatewayMerchantId", str));
        a.put("tokenizationSpecification", jSONObject);
        put.put("allowedPaymentMethods", jSONArray.put(a));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("totalPrice", String.valueOf(paymentData.b / 100.0f));
        jSONObject2.put("totalPriceStatus", "FINAL");
        jSONObject2.put("currencyCode", paymentData.c);
        String customLabel = this.a.getCustomLabel();
        if (customLabel != null) {
            jSONObject2.put("totalPriceLabel", customLabel);
        }
        put.put("transactionInfo", jSONObject2);
        JSONObject put2 = new JSONObject().put("merchantName", this.a.getMerchantName());
        Intrinsics.checkNotNullExpressionValue(put2, "put(...)");
        put.put("merchantInfo", put2);
        if (this.a.getCustomerInfoParameters().getIsEmailRequired()) {
            put.put("emailRequired", this.a.getCustomerInfoParameters().getIsEmailRequired());
        }
        if (this.a.getCustomerInfoParameters().getIsShippingAddressRequired()) {
            put.put("shippingAddressRequired", this.a.getCustomerInfoParameters().getIsShippingAddressRequired());
            ShippingAddressParameters shippingAddressParameters = this.a.getCustomerInfoParameters().getShippingAddressParameters();
            if (shippingAddressParameters != null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("allowedCountryCodes", new JSONArray((Collection) shippingAddressParameters.getAllowedCountryCodes$lib_release()));
                jSONObject3.put("phoneNumberRequired", shippingAddressParameters.getIsPhoneNumberRequired());
                Unit unit = Unit.INSTANCE;
                put.put("shippingAddressParameters", jSONObject3);
            }
        }
        return put;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0112, code lost:
    
        if (r2.put("phoneNumberRequired", r8.getIsPhoneNumberRequired()) == null) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONObject a() {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0173l5.a():org.json.JSONObject");
    }
}
