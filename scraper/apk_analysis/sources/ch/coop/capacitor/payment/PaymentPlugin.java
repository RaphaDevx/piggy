package ch.coop.capacitor.payment;

import android.os.Bundle;
import ch.coop.capacitor.payment.PaymentPlugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.SamsungPay;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.StatusListener;
import io.sentry.IScope;
import io.sentry.ScopeCallback;
import io.sentry.Sentry;
import java.util.Arrays;

@CapacitorPlugin(name = "Payment")
/* loaded from: classes3.dex */
public class PaymentPlugin extends Plugin {
    static final String TAG = "PaymentPlugin";

    @PluginMethod
    public void canMakePaymentsWithApplePay(PluginCall pluginCall) {
        JSObject jSObject = new JSObject();
        jSObject.put("canMakePayments", false);
        pluginCall.resolve(jSObject);
    }

    @PluginMethod
    public void canMakePaymentsWithGooglePay(final PluginCall pluginCall) {
        int i;
        String string = pluginCall.getString("environment");
        if (string == null) {
            pluginCall.reject("No 'environment' provided.");
            return;
        }
        if ("PRODUCTION".equals(string)) {
            i = 1;
        } else {
            if (!"TEST".equals(string)) {
                pluginCall.reject("Unknown 'environment' value: " + string);
                return;
            }
            i = 3;
        }
        JSObject object = pluginCall.getObject("isReadyToPayRequest");
        if (object == null) {
            pluginCall.reject("No 'isReadyToPayRequest' provided.");
            return;
        }
        String jSObject = object.toString();
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext()) != 0) {
            JSObject jSObject2 = new JSObject();
            jSObject2.put("canMakePayments", false);
            pluginCall.resolve(jSObject2);
        } else {
            Task<Boolean> isReadyToPay = Wallet.getPaymentsClient(getContext(), new Wallet.WalletOptions.Builder().setEnvironment(i).build()).isReadyToPay(IsReadyToPayRequest.fromJson(jSObject));
            isReadyToPay.addOnSuccessListener(new OnSuccessListener() { // from class: ch.coop.capacitor.payment.PaymentPlugin$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    PaymentPlugin.lambda$canMakePaymentsWithGooglePay$0(PluginCall.this, (Boolean) obj);
                }
            });
            isReadyToPay.addOnFailureListener(new OnFailureListener() { // from class: ch.coop.capacitor.payment.PaymentPlugin$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    PaymentPlugin.lambda$canMakePaymentsWithGooglePay$1(PluginCall.this, exc);
                }
            });
        }
    }

    static /* synthetic */ void lambda$canMakePaymentsWithGooglePay$0(PluginCall pluginCall, Boolean bool) {
        JSObject jSObject = new JSObject();
        jSObject.put("canMakePayments", (Object) bool);
        pluginCall.resolve(jSObject);
    }

    static /* synthetic */ void lambda$canMakePaymentsWithGooglePay$1(PluginCall pluginCall, Exception exc) {
        Sentry.captureException(exc);
        pluginCall.reject("isReadyToPay failure with exception: " + exc.getMessage());
    }

    @PluginMethod
    public void canMakePaymentsWithSamsungPay(PluginCall pluginCall) {
        String string = pluginCall.getString("serviceId");
        if (string == null) {
            pluginCall.reject("No 'serviceId' provided.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(SpaySdk.PARTNER_SERVICE_TYPE, SpaySdk.ServiceType.INAPP_PAYMENT.toString());
        new SamsungPay(getContext(), new PartnerInfo(string, bundle)).getSamsungPayStatus(new AnonymousClass1(this, pluginCall));
    }

    /* renamed from: ch.coop.capacitor.payment.PaymentPlugin$1, reason: invalid class name */
    class AnonymousClass1 implements StatusListener {
        final /* synthetic */ PluginCall val$call;

        AnonymousClass1(PaymentPlugin paymentPlugin, PluginCall pluginCall) {
            this.val$call = pluginCall;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.StatusListener
        public void onSuccess(int i, Bundle bundle) {
            JSObject jSObject = new JSObject();
            jSObject.put("canMakePayments", i == 2);
            this.val$call.resolve(jSObject);
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.StatusListener
        public void onFail(final int i, Bundle bundle) {
            Sentry.withScope(new ScopeCallback() { // from class: ch.coop.capacitor.payment.PaymentPlugin$1$$ExternalSyntheticLambda0
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    PaymentPlugin.AnonymousClass1.lambda$onFail$0(i, iScope);
                }
            });
            this.val$call.reject("getSamsungPayStatus failure with errorCode: " + i);
        }

        static /* synthetic */ void lambda$onFail$0(int i, IScope iScope) {
            iScope.setFingerprint(Arrays.asList(PaymentPlugin.TAG, "SamsungPayError", "getSamsungPayStatus", String.valueOf(i)));
            iScope.setTag("error.type", "SamsungPayError");
            iScope.setTag("error.code", String.valueOf(i));
            iScope.setTag("error.description", "getSamsungPayStatus failed");
            Sentry.captureException(new SamsungPayError());
        }
    }
}
