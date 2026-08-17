package ch.coop.capacitor.datatrans;

import ch.datatrans.payment.api.Transaction;
import ch.datatrans.payment.api.TransactionListener;
import ch.datatrans.payment.api.TransactionRegistry;
import ch.datatrans.payment.api.TransactionSuccess;
import ch.datatrans.payment.exception.TransactionException;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

@CapacitorPlugin(name = "Datatrans")
/* loaded from: classes3.dex */
public class DatatransPlugin extends Plugin implements TransactionListener {
    static final String TAG = "DatatransPlugin";
    private PluginCall pendingCall;
    public TransactionFactory transactionFactory = new TransactionFactory() { // from class: ch.coop.capacitor.datatrans.DatatransPlugin$$ExternalSyntheticLambda0
        @Override // ch.coop.capacitor.datatrans.DatatransPlugin.TransactionFactory
        public final Transaction createTransaction(String str) {
            return DatatransPlugin.$r8$lambda$bIaa6zhnvIVXRMPhm4RFIdYWP1Y(str);
        }
    };
    public TransactionRegistry transactionRegistry = TransactionRegistry.INSTANCE;

    @FunctionalInterface
    public interface TransactionFactory {
        Transaction createTransaction(String str);
    }

    public static /* synthetic */ Transaction $r8$lambda$bIaa6zhnvIVXRMPhm4RFIdYWP1Y(String str) {
        return new Transaction(str);
    }

    public synchronized PluginCall getPendingCall() {
        return this.pendingCall;
    }

    @PluginMethod
    public void startTransaction(PluginCall pluginCall) {
        synchronized (this) {
            if (this.pendingCall != null) {
                pluginCall.reject("There is already a pending transaction.");
            }
            this.pendingCall = pluginCall;
        }
        try {
            startTransactionInternal(pluginCall);
        } catch (IllegalArgumentException e) {
            synchronized (this) {
                this.pendingCall = null;
                pluginCall.reject(e.getMessage());
            }
        }
    }

    private void startTransactionInternal(PluginCall pluginCall) {
        String string = pluginCall.getString("mobileToken");
        if (string == null) {
            throw new IllegalArgumentException("No 'mobileToken' provided");
        }
        Transaction createTransaction = this.transactionFactory.createTransaction(string);
        createTransaction.setListener(this);
        JSObject object = pluginCall.getObject(RRWebOptionsEvent.EVENT_TAG, null);
        if (object != null) {
            createTransaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().setAppCallbackScheme(object.getString("appCallbackScheme"));
            JSObject jSObject = object.getJSObject("googlePayConfig");
            if (jSObject != null) {
                createTransaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().setGooglePayConfig(readGooglePayConfig(jSObject));
            }
            JSObject jSObject2 = object.getJSObject("samsungPayConfig");
            if (jSObject2 != null) {
                createTransaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().setSamsungPayConfig(readSamsungPayConfig(jSObject2));
            }
            createTransaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().setSuppressCriticalErrorDialog(object.getBoolean("suppressCriticalErrorDialog", false).booleanValue());
            createTransaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().setTesting(object.getBoolean("testing", false).booleanValue());
            createTransaction.getIo.sentry.rrweb.RRWebOptionsEvent.EVENT_TAG java.lang.String().setUseCertificatePinning(object.getBoolean("useCertificatePinning", false).booleanValue());
        }
        this.transactionRegistry.startTransaction(getActivity(), createTransaction);
    }

    private static GooglePayConfig readGooglePayConfig(JSObject jSObject) {
        String string = jSObject.getString("merchantName");
        if (string == null) {
            throw new IllegalArgumentException("No 'googlePayConfig.merchantName' provided");
        }
        GooglePayConfig.Builder builder = new GooglePayConfig.Builder(readSupportedNetworks(jSObject, "googlePayConfig"), string);
        JSONArray optJSONArray = jSObject.optJSONArray("allowedAuthenticationMethods");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    String string2 = optJSONArray.getString(i);
                    if ("PAN_ONLY".equals(string2)) {
                        arrayList.add(GooglePayConfig.AuthenticationMethodType.PAN_ONLY);
                    } else if ("CRYPTOGRAM_3DS".equals(string2)) {
                        arrayList.add(GooglePayConfig.AuthenticationMethodType.CRYPTOGRAM_3DS);
                    } else {
                        throw new IllegalArgumentException("'googlePayConfig.allowedAuthenticationMethods' contains unknown method '" + string2 + "'.");
                    }
                } catch (JSONException unused) {
                    throw new IllegalArgumentException("error while reading 'googlePayConfig.allowedAuthenticationMethods' value at position " + i + ".");
                }
            }
            builder.allowedAuthenticationMethods(arrayList);
        }
        if (jSObject.optBoolean("disableCreditCards", false)) {
            builder.disableCreditCards();
        }
        if (jSObject.optBoolean("disablePrepaidCards", false)) {
            builder.disablePrepaidCards();
        }
        if (jSObject.optBoolean("requireExistingPaymentMethod", false)) {
            builder.requireExistingPaymentMethod();
        }
        String optString = jSObject.optString("showCustomLabel");
        if (!optString.isEmpty()) {
            builder.showCustomLabel(optString);
        }
        if (jSObject.optBoolean("showInSavedPaymentMethods", false)) {
            builder.showInSavedPaymentMethods();
        }
        if (jSObject.optBoolean("showLargeButton", false)) {
            builder.showLargeButton();
        }
        return builder.build();
    }

    private static SamsungPayConfig readSamsungPayConfig(JSObject jSObject) {
        String string = jSObject.getString("merchantName");
        if (string == null) {
            throw new IllegalArgumentException("No 'samsungPayConfig.merchantName' provided");
        }
        return new SamsungPayConfig(readSupportedNetworks(jSObject, "samsungPayConfig"), string, jSObject.optBoolean("showSamsungPayAsSavedPaymentMethod", false));
    }

    private static List<PaymentMethodType> readSupportedNetworks(JSObject jSObject, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSObject.getJSONArray("supportedNetworks");
            for (int i = 0; i < jSONArray.length(); i++) {
                PaymentMethodType mapNetwork = mapNetwork(jSONArray.getString(i));
                if (mapNetwork != null) {
                    arrayList.add(mapNetwork);
                }
            }
            return arrayList;
        } catch (JSONException unused) {
            throw new IllegalArgumentException("No or invalid '" + str + ".supportedNetworks' provided");
        }
    }

    @Override // ch.datatrans.payment.api.TransactionListener
    public synchronized void onTransactionSuccess(TransactionSuccess transactionSuccess) {
        if (this.pendingCall != null) {
            JSObject jSObject = new JSObject();
            jSObject.put("status", "SUCCESS");
            jSObject.put("transactionId", transactionSuccess.getTransactionId());
            this.pendingCall.resolve(jSObject);
            this.pendingCall = null;
        }
    }

    @Override // ch.datatrans.payment.api.TransactionListener
    public synchronized void onTransactionError(TransactionException transactionException) {
        if (this.pendingCall != null) {
            JSObject jSObject = new JSObject();
            jSObject.put("status", "TRANSACTION_ERROR");
            this.pendingCall.resolve(jSObject);
            this.pendingCall = null;
        }
    }

    @Override // ch.datatrans.payment.api.TransactionListener
    public synchronized void onTransactionCancel(String str) {
        if (this.pendingCall != null) {
            JSObject jSObject = new JSObject();
            jSObject.put("status", "TRANSACTION_CANCEL");
            this.pendingCall.resolve(jSObject);
            this.pendingCall = null;
        }
    }

    public static PaymentMethodType mapNetwork(String str) {
        str.hashCode();
        switch (str) {
            case "DANKORT":
                return PaymentMethodType.DANKORT;
            case "PAYPAL":
                return PaymentMethodType.PAY_PAL;
            case "MASTERCARD":
                return PaymentMethodType.MASTER_CARD;
            case "DINERSCLUB":
                return PaymentMethodType.DINERS_CLUB;
            case "ELO":
                return PaymentMethodType.ELO_CARD;
            case "JCB":
                return PaymentMethodType.JCB;
            case "AMEX":
                return PaymentMethodType.AMERICAN_EXPRESS;
            case "VISA":
                return PaymentMethodType.VISA;
            case "POSTFINANCE":
                return PaymentMethodType.POST_FINANCE_CARD;
            case "DISCOVER":
                return PaymentMethodType.DISCOVER;
            case "MAESTRO":
                return PaymentMethodType.MAESTRO;
            default:
                return null;
        }
    }
}
