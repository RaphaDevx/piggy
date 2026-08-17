package com.samsung.android.sdk.samsungpay.v2.payment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.ErrorType;
import com.samsung.android.sdk.samsungpay.v2.ISStatusListener;
import com.samsung.android.sdk.samsungpay.v2.InternalConst;
import com.samsung.android.sdk.samsungpay.v2.ParamInfo;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.SpayValidity;
import com.samsung.android.sdk.samsungpay.v2.StatusListener;
import com.samsung.android.sdk.samsungpay.v2.StubBase;
import com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.ISGetCardBrandListener;
import com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback;
import com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AddressControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItemType;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetUpdatedListener;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SpinnerControl;
import com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback;
import com.samsung.android.sdk.samsungpay.v2.service.RequestType;
import com.samsung.android.sdk.samsungpay.v2.service.UserInfoCollection;
import com.samsung.android.sdk.samsungpay.v2.service.UserInfoListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class PaymentManager extends SpaySdk {
    private static final int CMD_CARD_TYPE = 201;
    private static final int CMD_EXECUTE_PENDING_METHODS = 1000;
    private static final int CMD_FAILED_BIND_STUB = 1001;
    private static final int CMD_GET_FCO_STATUS = 205;
    private static final int CMD_SEND_PAYMENT_RESULT_INFO = 206;
    private static final int CMD_SETUP_INFO = 202;
    private static final int CMD_START = 200;
    private static final int CMD_START_SIMPLE_PAY = 203;
    private static final int CMD_START_WITH_CUSTOM_SHEET = 204;
    private static final int CMD_UPDATE_AMOUNT = 207;
    private static final int CMD_UPDATE_AMOUNT_FAIL = 208;
    private static final int CMD_UPDATE_SHEET = 209;
    public static final int CUSTOM_MESSAGE = -220;
    private static final int CUSTOM_SHEET_VER_1 = 1;
    public static final int ERROR_ADDRESS_UPDATED_TIME_OUT = -113;
    public static final int ERROR_BILLING_ADDRESS_INVALID = -204;
    public static final int ERROR_BILLING_ADDRESS_NOT_EXIST = -205;

    @Deprecated
    public static final int ERROR_CARD_NOT_SUPPORTED_IN_LATEST_SPAY = -402;
    public static final int ERROR_CARD_NOT_SUPPORTED_ONLINE_PAY = -402;
    public static final int ERROR_INTERNAL_ADDRESS_UPDATED = -114;
    public static final int ERROR_MAKING_SHEET_FAILED = -115;
    public static final int ERROR_NOT_READY_PAYMENT = -108;

    @Deprecated
    public static final int ERROR_PAYMENT_PROTOCOL_NOT_SUPPORTED = -401;
    public static final int ERROR_SESSION_LOCKED = -109;
    public static final int ERROR_SESSION_TIMED_OUT = -110;
    public static final int ERROR_SHIPPING_ADDRESS_INVALID = -201;
    public static final int ERROR_SHIPPING_ADDRESS_NOT_EXIST = -203;
    public static final int ERROR_SHIPPING_ADDRESS_UNABLE_TO_SHIP = -202;
    public static final int ERROR_SPAY_RESET = -116;
    public static final int ERROR_TRANSACTION_CLOSED = -112;
    public static final int ERROR_TRANSACTION_TIMED_OUT = -111;

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_7)
    public static final String EXTRA_ENFORCE_PAYMENT_SHEET = "enforcePaymentSheet";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_7)
    @Deprecated
    public static final String EXTRA_FCO_STATUS = "fastCheckOutStatus";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_KEY_ADDITIONAL_DATA = "additionalData";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_7)
    public static final String EXTRA_KEY_CARD_BRAND_FILTER = "card_brand_filter";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_3)
    public static final String EXTRA_KEY_TEST_MODE = "testMode";
    public static final String EXTRA_LOGO_IMAGE_URL = "logoImageUrl";
    public static final String EXTRA_PAY_OPERATION_TYPE = "payOperationType";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_7)
    public static final String EXTRA_SHIPPING_METHOD = "shippingMethod";

    @Deprecated
    public static final String EXTRA_TOP_BACKGROUND_COLOR = "topBackgroundColor";
    public static final String EXTRA_TRANSACTION_DATE = "transactionDate";
    public static final String EXTRA_TRANSACTION_NUMBER = "transactionNumber";
    public static final String EXTRA_TRANSACTION_TYPE = "transactionType";

    @Deprecated
    public static final int FCO_DISABLED = 0;

    @Deprecated
    public static final int FCO_ENABLED = 1;
    private static final String KEY_CUSTOM_SHEET = "customSheet";
    private static final String KEY_CUSTOM_SHEET_PAYMENT_NFO = "customSheetPaymentInfo";
    private static final String KEY_ENCRYPTED_DATA = "EncryptedData";
    private static final String KEY_EXTRA_DATA = "extraData";
    private static final String KEY_PAYMENT_INFO = "PaymentInfo";
    private static final String KEY_UPDATED_CONTROL_ID = "updatedControlId";
    private static final String KEY_UPDATE_CARD = "updatedCard";
    private static final int MSG_APPROVE_PAYMENT = 101;
    private static final int MSG_APPROVE_PAYMENT_WITH_SHEET = 107;
    private static final int MSG_CUSTOM_SHEET_APPROVE_PAYMENT = 109;
    private static final int MSG_CUSTOM_SHEET_CARD_INFO_UPDATED = 108;
    private static final int MSG_CUSTOM_SHEET_FAIL_PAYMENT = 110;
    private static final int MSG_FAIL_PAYMENT = 102;
    private static final int MSG_PAYMENT_ADDRESS_CHANGED = 103;
    private static final int MSG_PAYMENT_CARD_CHANGED = 104;
    private static final int MSG_PAYMENT_INFO_CHANGED = 100;
    private static final int MSG_PAYMENT_SHEET_UPDATED = 106;
    private static final int MSG_SHOW_PAYMENT_ACTIVITY = 105;
    public static final String PAY_OPERATION_TYPE_PAYMENT = "payment";
    public static final String PAY_OPERATION_TYPE_REFUND = "refund";
    private static final String TAG = "SPAYSDK:PaymentManager";
    public static final int TRANSACTION_TYPE_MST = 1;
    public static final int TRANSACTION_TYPE_NFC = 2;
    private final Handler mEventHandler;
    protected Handler mHandlerForInternalListener;
    private boolean mIsAsyncResponse;
    private StubBase<ISPaymentManager> mPaymentManagerStub;

    public interface CardInfoListener {
        void onFailure(int i, Bundle bundle);

        void onResult(List<CardInfo> list);
    }

    public interface CustomSheetTransactionInfoListener {
        void onCardInfoUpdated(CardInfo cardInfo, CustomSheet customSheet);

        void onFailure(int i, Bundle bundle);

        void onSuccess(CustomSheetPaymentInfo customSheetPaymentInfo, String str, Bundle bundle);
    }

    @Deprecated
    public interface TransactionInfoListener {
        void onAddressUpdated(PaymentInfo paymentInfo);

        void onCardInfoUpdated(CardInfo cardInfo);

        void onFailure(int i, Bundle bundle);

        void onSuccess(PaymentInfo paymentInfo, String str, Bundle bundle);
    }

    private static boolean isParsable(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Double.parseDouble(str) >= 0.0d;
        } catch (NumberFormatException unused) {
            Log.d(TAG, "NumberFormat Exception");
            return false;
        }
    }

    public PaymentManager(Context context, PartnerInfo partnerInfo) {
        super(context, partnerInfo);
        this.mEventHandler = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                SheetUpdatedListener sheetUpdatedListener;
                SheetUpdatedListener sheetUpdatedListener2;
                PartnerRequest partnerRequest = (PartnerRequest) message.obj;
                switch (message.what) {
                    case 101:
                        Bundle data = message.getData();
                        ((TransactionInfoListener) partnerRequest.callbackObj).onSuccess((PaymentInfo) data.get(PaymentManager.KEY_PAYMENT_INFO), data.getString(PaymentManager.KEY_ENCRYPTED_DATA), null);
                        PaymentManager.this.nextRequest();
                        break;
                    case 102:
                    case 110:
                        PaymentManager.this.onFailure(message);
                        PaymentManager.this.nextRequest();
                        break;
                    case 103:
                        ((TransactionInfoListener) partnerRequest.callbackObj).onAddressUpdated((PaymentInfo) message.getData().get(PaymentManager.KEY_PAYMENT_INFO));
                        break;
                    case 104:
                        ((TransactionInfoListener) partnerRequest.callbackObj).onCardInfoUpdated((CardInfo) partnerRequest.obj3);
                        break;
                    case 105:
                        PaymentManager.this.showPaymentSheet((PartnerRequest) message.obj, message.arg1);
                        break;
                    case 106:
                        Bundle data2 = message.getData();
                        String string = data2.getString(PaymentManager.KEY_UPDATED_CONTROL_ID);
                        CustomSheet customSheet = (CustomSheet) data2.get(PaymentManager.KEY_CUSTOM_SHEET);
                        SheetControl sheetControl = customSheet.getSheetControl(string);
                        CustomSheetPaymentInfo customSheetPaymentInfo = (CustomSheetPaymentInfo) partnerRequest.obj1;
                        if (sheetControl != null) {
                            int i = AnonymousClass3.$SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype[sheetControl.getControltype().ordinal()];
                            if (i == 1) {
                                AddressControl addressControl = (AddressControl) customSheetPaymentInfo.getCustomSheet().getSheetControl(string);
                                if (addressControl != null && (sheetUpdatedListener = addressControl.getSheetUpdatedListener()) != null) {
                                    sheetUpdatedListener.onResult(string, customSheet);
                                    break;
                                }
                            } else if (i == 2) {
                                SpinnerControl spinnerControl = (SpinnerControl) customSheetPaymentInfo.getCustomSheet().getSheetControl(string);
                                if (spinnerControl != null && (sheetUpdatedListener2 = spinnerControl.getSheetUpdatedListener()) != null) {
                                    sheetUpdatedListener2.onResult(string, customSheet);
                                    break;
                                }
                            } else {
                                Log.e(PaymentManager.TAG, "MSG_PAYMENT_SHEET_UPDATED : controlType is not matched.");
                                break;
                            }
                        }
                        break;
                    case 108:
                        Bundle data3 = message.getData();
                        ((CustomSheetTransactionInfoListener) partnerRequest.callbackObj).onCardInfoUpdated((CardInfo) data3.get(PaymentManager.KEY_UPDATE_CARD), (CustomSheet) data3.get(PaymentManager.KEY_CUSTOM_SHEET));
                        break;
                    case 109:
                        Bundle data4 = message.getData();
                        ((CustomSheetTransactionInfoListener) partnerRequest.callbackObj).onSuccess((CustomSheetPaymentInfo) data4.get(PaymentManager.KEY_CUSTOM_SHEET_PAYMENT_NFO), data4.getString(PaymentManager.KEY_ENCRYPTED_DATA), (Bundle) data4.get(PaymentManager.KEY_EXTRA_DATA));
                        PaymentManager.this.nextRequest();
                        break;
                }
            }
        };
        this.mIsAsyncResponse = false;
        this.mHandlerForInternalListener = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (message.obj instanceof CardInfoListener) {
                        ((CardInfoListener) message.obj).onResult(message.getData().getParcelableArrayList("KEY_FOR_MESSAGE"));
                        return;
                    } else {
                        ((StatusListener) message.obj).onSuccess(message.arg1, message.getData());
                        return;
                    }
                }
                if (i != 1) {
                    if (i != 2) {
                        Log.e(PaymentManager.TAG, "sdk can not catch listener from SPay.");
                    }
                } else if (message.obj instanceof CardInfoListener) {
                    ((CardInfoListener) message.obj).onFailure(message.arg1, message.getData());
                } else {
                    ((StatusListener) message.obj).onFail(message.arg1, message.getData());
                }
            }
        };
        Log.d(TAG, "PaymentManager()");
        this.mPaymentManagerStub = new StubBase.Creator().createStub(context, InternalConst.SERVICE_ACTION_PAYMENT, new StubBase.StubCaster() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda15
            @Override // com.samsung.android.sdk.samsungpay.v2.StubBase.StubCaster
            public final IInterface castStub(IBinder iBinder) {
                return ISPaymentManager.Stub.asInterface(iBinder);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String checkPaymentInfoValidity(com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo r15) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.checkPaymentInfoValidity(com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo):java.lang.String");
    }

    private String checkPaymentInfoValidity(PaymentInfo paymentInfo) {
        if (paymentInfo.getAmount() == null) {
            return "You must set Amount.";
        }
        if (paymentInfo.getAddressInPaymentSheet() == PaymentInfo.AddressInPaymentSheet.SEND_SHIPPING || paymentInfo.getAddressInPaymentSheet() == PaymentInfo.AddressInPaymentSheet.NEED_BILLING_SEND_SHIPPING) {
            if (paymentInfo.getShippingAddress() == null) {
                return "You must set address to display on the payment sheet.";
            }
            String invalidAddressFields = getInvalidAddressFields(paymentInfo.getShippingAddress());
            if (!invalidAddressFields.trim().isEmpty()) {
                return "You must set mandatory address fields.\n Following fields cannot be null or empty : ( " + invalidAddressFields + ")";
            }
        } else {
            if (!paymentInfo.getIsGiftCardPurchase()) {
                return (paymentInfo.getMerchantName() == null || paymentInfo.getMerchantName().isEmpty()) ? "You must set merchant name." : (isParsable(paymentInfo.getAmount().getShippingPrice()) && isParsable(paymentInfo.getAmount().getTotalPrice()) && isParsable(paymentInfo.getAmount().getItemTotalPrice()) && isParsable(paymentInfo.getAmount().getTax())) ? "" : "You must set all Amount fields with valid values.";
            }
            if (!isParsable(paymentInfo.getAmount().getTotalPrice())) {
                return "You must set Gift Card Amount.";
            }
        }
        return "";
    }

    private String getInvalidAddressFields(PaymentInfo.Address address) {
        String str = (address.getAddressLine1() == null || address.getAddressLine1().trim().isEmpty()) ? "AddressLine1 " : "";
        if (address.getCity() == null || address.getCity().trim().isEmpty()) {
            str = str + "City ";
        }
        if (address.getState() == null || address.getState().trim().isEmpty()) {
            str = str + "State ";
        }
        if (address.getPostalCode() == null || address.getPostalCode().trim().isEmpty()) {
            str = str + "ZipCode ";
        }
        if (address.getCountryCode() != null && !address.getCountryCode().trim().isEmpty()) {
            return str;
        }
        return str + "Country ";
    }

    public void requestCardInfo(Bundle bundle, final CardInfoListener cardInfoListener) {
        Log.d(TAG, "requestCardInfo : SDK API Level = 1.1");
        this.mIsAsyncResponse = false;
        assertNotNull(cardInfoListener);
        final GetCardBrandListenerInternal getCardBrandListenerInternal = new GetCardBrandListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 201, cardInfoListener).setName("requestCardInfo").setObj1(bundle).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda4
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.this.m3740x618db758(getCardBrandListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda5
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle2) {
                PaymentManager.CardInfoListener.this.onFailure(i, bundle2);
            }
        }).create();
        getCardBrandListenerInternal.set(create);
        this.mPaymentManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_1_1);
    }

    @Deprecated
    public void updateAmount(final PaymentInfo.Amount amount) {
        Log.d(TAG, "updateAmount() : SDK API Level = 1.1");
        if (!this.mPaymentManagerStub.isValidStub()) {
            throw new IllegalStateException("Service is disconnected. Please try to call startInAppPay() before.");
        }
        this.mPaymentManagerStub.executeRequest(new PartnerRequest.Builder(this, 207, null).setName("updateAmount").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.lambda$updateAmount$2(PaymentInfo.Amount.this, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda3
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                PaymentManager.lambda$updateAmount$3(errorType, i, bundle);
            }
        }).create(), SpaySdk.SdkApiLevel.LEVEL_1_1);
    }

    static /* synthetic */ void lambda$updateAmount$2(PaymentInfo.Amount amount, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        int updateAmount = ((ISPaymentManager) iInterface).updateAmount(amount);
        if (updateAmount == -108) {
            throw new IllegalStateException("You should call startInAppPay() before updateTransactionDetails");
        }
        if (updateAmount == -109) {
            throw new IllegalStateException("Samsung Pay Service is locked by other application.");
        }
    }

    static /* synthetic */ void lambda$updateAmount$3(ErrorType errorType, int i, Bundle bundle) {
        throw new IllegalStateException("Exception: " + i);
    }

    @Deprecated
    public void updateAmountFailed(final int i) {
        Log.d(TAG, "updateAmountFailed() : SDK API Level = 1.1");
        if (!this.mPaymentManagerStub.isValidStub()) {
            throw new IllegalStateException("Service is disconnected. Please try to call startInAppPay() before.");
        }
        this.mPaymentManagerStub.executeRequest(new PartnerRequest.Builder(this, CMD_UPDATE_AMOUNT_FAIL, null).setName("updateAmountFailed").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda11
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.lambda$updateAmountFailed$4(i, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda12
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i2, Bundle bundle) {
                PaymentManager.lambda$updateAmountFailed$5(errorType, i2, bundle);
            }
        }).create(), SpaySdk.SdkApiLevel.LEVEL_1_1);
    }

    static /* synthetic */ void lambda$updateAmountFailed$4(int i, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        int invalidAddress = ((ISPaymentManager) iInterface).invalidAddress(i);
        if (invalidAddress == -108) {
            throw new IllegalStateException("You should call startInAppPay() before updateTransactionDetails");
        }
        if (invalidAddress == -109) {
            throw new IllegalStateException("Samsung Pay Service is locked by other application.");
        }
    }

    static /* synthetic */ void lambda$updateAmountFailed$5(ErrorType errorType, int i, Bundle bundle) {
        throw new IllegalStateException("Exception: " + i);
    }

    public void updateSheet(final CustomSheet customSheet) {
        Log.d(TAG, "updateSheet() : SDK API Level = 1.3");
        if (!this.mPaymentManagerStub.isValidStub()) {
            throw new IllegalStateException("Service is disconnected. Please try to call startInAppPay() before.");
        }
        assertNotNull(customSheet);
        this.mPaymentManagerStub.executeRequest(new PartnerRequest.Builder(this, CMD_UPDATE_SHEET, null).setName("updateSheet").checkApiLevelFor(customSheet).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda18
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.lambda$updateSheet$6(CustomSheet.this, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                PaymentManager.lambda$updateSheet$7(errorType, i, bundle);
            }
        }).create(), SpaySdk.SdkApiLevel.LEVEL_1_3);
    }

    static /* synthetic */ void lambda$updateSheet$6(CustomSheet customSheet, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        int updateSheet = ((ISPaymentManager) iInterface).updateSheet(customSheet);
        if (updateSheet == -108) {
            throw new IllegalStateException("You should call startInAppPay() before updateTransactionDetails");
        }
        if (updateSheet == -109) {
            throw new IllegalStateException("Samsung Pay Service is locked by other application.");
        }
    }

    static /* synthetic */ void lambda$updateSheet$7(ErrorType errorType, int i, Bundle bundle) {
        throw new IllegalStateException("Exception: " + i);
    }

    public void updateSheet(final CustomSheet customSheet, final int i, final String str) {
        Log.d(TAG, "updateSheet() with custom error code & message : SDK API Level = 1.5");
        if (!this.mPaymentManagerStub.isValidStub()) {
            throw new IllegalStateException("Service is disconnected. Please try to call startInAppPay() before.");
        }
        assertNotNull(customSheet);
        this.mPaymentManagerStub.executeRequest(new PartnerRequest.Builder(this, CMD_UPDATE_SHEET, null).setName("updateSheet").checkApiLevelFor(customSheet).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda6
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.lambda$updateSheet$8(CustomSheet.this, i, str, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda7
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i2, Bundle bundle) {
                PaymentManager.lambda$updateSheet$9(errorType, i2, bundle);
            }
        }).create(), SpaySdk.SdkApiLevel.LEVEL_1_5);
    }

    static /* synthetic */ void lambda$updateSheet$8(CustomSheet customSheet, int i, String str, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        int updateSheetWithCustomMessage = ((ISPaymentManager) iInterface).updateSheetWithCustomMessage(customSheet, i, str);
        if (updateSheetWithCustomMessage == -108) {
            throw new IllegalStateException("You should call startInAppPayWithCustomSheet() before updateControl");
        }
        if (updateSheetWithCustomMessage == -109) {
            throw new IllegalStateException("Samsung Pay Service is locked by other application.");
        }
    }

    static /* synthetic */ void lambda$updateSheet$9(ErrorType errorType, int i, Bundle bundle) {
        throw new IllegalStateException("Exception: " + i);
    }

    /* renamed from: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$CustomSheetPaymentInfo$AddressInPaymentSheet;
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype;
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetItemType;

        static {
            int[] iArr = new int[SheetControl.Controltype.values().length];
            $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype = iArr;
            try {
                iArr[SheetControl.Controltype.ADDRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype[SheetControl.Controltype.SPINNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SheetItemType.values().length];
            $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetItemType = iArr2;
            try {
                iArr2[SheetItemType.SHIPPING_ADDRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetItemType[SheetItemType.BILLING_ADDRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[CustomSheetPaymentInfo.AddressInPaymentSheet.values().length];
            $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$CustomSheetPaymentInfo$AddressInPaymentSheet = iArr3;
            try {
                iArr3[CustomSheetPaymentInfo.AddressInPaymentSheet.SEND_SHIPPING.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$CustomSheetPaymentInfo$AddressInPaymentSheet[CustomSheetPaymentInfo.AddressInPaymentSheet.NEED_BILLING_SEND_SHIPPING.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$CustomSheetPaymentInfo$AddressInPaymentSheet[CustomSheetPaymentInfo.AddressInPaymentSheet.NEED_SHIPPING_SPAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$CustomSheetPaymentInfo$AddressInPaymentSheet[CustomSheetPaymentInfo.AddressInPaymentSheet.NEED_BILLING_AND_SHIPPING.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$CustomSheetPaymentInfo$AddressInPaymentSheet[CustomSheetPaymentInfo.AddressInPaymentSheet.NEED_BILLING_SPAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public void startSimplePay(CardInfo cardInfo, final StatusListener statusListener) {
        Log.d(TAG, "startSimplePay : SDK API Level = 1.1");
        assertNotNull(cardInfo);
        assertNotNull(cardInfo.getCardId());
        assertNotNull(statusListener);
        Bundle cardMetaData = cardInfo.getCardMetaData();
        if (cardMetaData == null || cardMetaData.get(EXTRA_PAY_OPERATION_TYPE) == null || cardMetaData.get(EXTRA_TRANSACTION_TYPE) == null) {
            throw new IllegalArgumentException("PaymentManager.EXTRA_PAY_OPERATION_TYPE and PaymentManager.EXTRA_TRANSACTION_TYPE are must be set.");
        }
        SpayStatusListener spayStatusListener = new SpayStatusListener();
        PartnerRequest create = new PartnerRequest.Builder(this, 203, statusListener).setName("startSimplePay").setObj1(spayStatusListener).setObj2(cardInfo).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda16
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.this.m3743x25e0b682(iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda17
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                StatusListener.this.onFail(i, bundle);
            }
        }).create();
        spayStatusListener.setPartnerListenerRef(statusListener);
        this.mPaymentManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_1_1);
    }

    @Deprecated
    public void startInAppPay(PaymentInfo paymentInfo, final TransactionInfoListener transactionInfoListener) {
        Log.d(TAG, "startInAppPay : SDK API Level = 1.1");
        assertNotNull(paymentInfo);
        assertNotNull(transactionInfoListener);
        if (SpayValidity.readPartnerApiLevelInManifest(this.contextRef.get()).compareTo(SpaySdk.SdkApiLevel.LEVEL_2_8) >= 0) {
            throw new IllegalStateException("startInAppPay is deprecated from SDK version 2.0. please do not use this api anymore");
        }
        String checkPaymentInfoValidity = checkPaymentInfoValidity(paymentInfo);
        if (checkPaymentInfoValidity.isEmpty()) {
            this.mPaymentManagerStub.postRequest(new PartnerRequest.Builder(this, 200, transactionInfoListener).checkApiLevelFor(paymentInfo.getExtraPaymentInfo()).setName("startInAppPay").setObj1(paymentInfo).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda8
                @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
                public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                    PaymentManager.this.m3741x5f4a58a0(iInterface, partnerRequest);
                }
            }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda9
                @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
                public final void onError(ErrorType errorType, int i, Bundle bundle) {
                    PaymentManager.TransactionInfoListener.this.onFailure(i, bundle);
                }
            }).create(), SpaySdk.SdkApiLevel.LEVEL_1_1);
            return;
        }
        throw new IllegalArgumentException(checkPaymentInfoValidity);
    }

    public void startInAppPayWithCustomSheet(CustomSheetPaymentInfo customSheetPaymentInfo, CustomSheetTransactionInfoListener customSheetTransactionInfoListener) {
        Log.d(TAG, "startInAppPayWithCustomSheet : SDK API Level = 1.3");
        startInAppPayCommon(customSheetPaymentInfo, customSheetTransactionInfoListener, null, null, SpaySdk.SdkApiLevel.LEVEL_1_3);
    }

    @Deprecated
    public void startInAppPayWithUserInfo(CustomSheetPaymentInfo customSheetPaymentInfo, CustomSheetTransactionInfoListener customSheetTransactionInfoListener, RequestType[] requestTypeArr, UserInfoListener userInfoListener) {
        Log.d(TAG, "startInAppPayWithUserInfo : SDK API Level = 2.6");
        assertNotNull(userInfoListener);
        verifyAddressControls(customSheetPaymentInfo, requestTypeArr);
        startInAppPayCommon(customSheetPaymentInfo, customSheetTransactionInfoListener, assertContainsPaymentCardType(requestTypeArr), userInfoListener, SpaySdk.SdkApiLevel.LEVEL_2_6);
    }

    private void startInAppPayCommon(CustomSheetPaymentInfo customSheetPaymentInfo, final CustomSheetTransactionInfoListener customSheetTransactionInfoListener, RequestType[] requestTypeArr, final UserInfoListener userInfoListener, SpaySdk.SdkApiLevel sdkApiLevel) {
        assertNotNull(customSheetPaymentInfo);
        assertNotNull(customSheetTransactionInfoListener);
        verifyOrderNumberForVISA(customSheetPaymentInfo);
        String checkPaymentInfoValidity = checkPaymentInfoValidity(customSheetPaymentInfo);
        if (checkPaymentInfoValidity.isEmpty()) {
            final RequestType[] convertRequestAllType = userInfoListener != null ? convertRequestAllType(requestTypeArr) : null;
            this.mPaymentManagerStub.postRequest(new PartnerRequest.Builder(this, 204, customSheetTransactionInfoListener).setName("startInAppPayCommon").checkApiLevelFor(convertRequestAllType, getPartnerInfo().getData(), customSheetPaymentInfo.getCustomSheet(), customSheetPaymentInfo.getExtraPaymentInfo()).setObj1(customSheetPaymentInfo).setObj2(1).checkDuplication(true).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda13
                @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
                public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                    PaymentManager.this.m3742x5e836d29(convertRequestAllType, userInfoListener, iInterface, partnerRequest);
                }
            }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda14
                @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
                public final void onError(ErrorType errorType, int i, Bundle bundle) {
                    PaymentManager.CustomSheetTransactionInfoListener.this.onFailure(i, bundle);
                }
            }).create(), sdkApiLevel);
            return;
        }
        throw new IllegalArgumentException(checkPaymentInfoValidity);
    }

    private RequestType[] assertContainsPaymentCardType(RequestType[] requestTypeArr) {
        if (requestTypeArr == null) {
            return requestTypeArr;
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, requestTypeArr);
        if (arrayList.contains(RequestType.PAYMENT_CARD)) {
            return requestTypeArr;
        }
        arrayList.add(RequestType.PAYMENT_CARD);
        return (RequestType[]) arrayList.toArray(new RequestType[arrayList.size()]);
    }

    private void verifyAddressControls(CustomSheetPaymentInfo customSheetPaymentInfo, RequestType[] requestTypeArr) {
        if (requestTypeArr == null || requestTypeArr.length == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (requestTypeArr[0] == RequestType.ALL) {
            arrayList.addAll(Arrays.asList(RequestType.values()));
        } else {
            arrayList.addAll(Arrays.asList(requestTypeArr));
        }
        Iterator it = arrayList.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            RequestType requestType = (RequestType) it.next();
            if (requestType == RequestType.BILLING_ADDRESS) {
                z = true;
            } else if (requestType == RequestType.SHIPPING_ADDRESS) {
                z2 = true;
            }
        }
        if (z || z2) {
            for (SheetControl sheetControl : customSheetPaymentInfo.getCustomSheet().getSheetControls()) {
                if (sheetControl instanceof AddressControl) {
                    SheetItemType sheetItemType = ((AddressControl) sheetControl).getSheetItem().getSheetItemType();
                    if (sheetItemType == SheetItemType.SHIPPING_ADDRESS) {
                        z2 = false;
                    } else if (sheetItemType == SheetItemType.BILLING_ADDRESS) {
                        z = false;
                    }
                }
            }
            if (z2) {
                throw new IllegalArgumentException("Shipping address is requested in RequestTypes but CustomSheet doesn't contain a shipping address control. Add a shipping address control to CustomSheet");
            }
            if (z) {
                throw new IllegalArgumentException("Billing address is requested in RequestTypes but CustomSheet doesn't contain a billing address control. Add a billing address control to CustomSheet");
            }
        }
    }

    private void verifyOrderNumberForVISA(CustomSheetPaymentInfo customSheetPaymentInfo) {
        List<SpaySdk.Brand> allowedCardBrands = customSheetPaymentInfo.getAllowedCardBrands();
        String orderNumber = customSheetPaymentInfo.getOrderNumber();
        if (!TextUtils.isEmpty(orderNumber)) {
            if (allowedCardBrands == null || allowedCardBrands.size() == 0 || allowedCardBrands.contains(SpaySdk.Brand.VISA)) {
                if (!Pattern.compile("[a-zA-Z0-9-]*").matcher(orderNumber).matches()) {
                    throw new IllegalArgumentException("Order number contains non-allowed character. Alphanumeric and hyphens(-) are allowed.");
                }
                if (orderNumber.length() > 36) {
                    throw new IllegalArgumentException("Order number is more than 36 characters");
                }
                return;
            }
            return;
        }
        if (allowedCardBrands == null || allowedCardBrands.size() == 0) {
            Log.w(TAG, "Order number is empty. The request would be failed if user selects VISA for payment. Consider to provide an order number or exclude VISA from allowed card brands");
        } else if (allowedCardBrands.contains(SpaySdk.Brand.VISA)) {
            throw new IllegalArgumentException("Order number is mandatory for VISA");
        }
    }

    @Deprecated
    public void getFastCheckoutStatus(final StatusListener statusListener) {
        Log.d(TAG, "getFastCheckoutStatus() : SDK API Level = 1.9");
        SpayStatusListener spayStatusListener = new SpayStatusListener();
        PartnerRequest create = new PartnerRequest.Builder(this, CMD_GET_FCO_STATUS, statusListener).setName("getFastCheckoutStatus").setObj1(spayStatusListener).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                PaymentManager.this.m3739x279d6798(iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager$$ExternalSyntheticLambda10
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                StatusListener.this.onFail(i, bundle);
            }
        }).create();
        spayStatusListener.setPartnerListenerRef(statusListener);
        this.mPaymentManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_1_9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextRequest() {
        this.mPaymentManagerStub.nextRequest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFailMessage(int i, PartnerRequest partnerRequest) {
        Message message = new Message();
        message.what = 102;
        message.arg1 = i;
        message.obj = partnerRequest;
        this.mEventHandler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFailMessageForCustomSheet(int i, Bundle bundle, PartnerRequest partnerRequest) {
        Message message = new Message();
        message.what = 110;
        message.arg1 = i;
        message.obj = partnerRequest;
        message.setData(bundle);
        this.mEventHandler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doStart, reason: merged with bridge method [inline-methods] */
    public void m3741x5f4a58a0(ISPaymentManager iSPaymentManager, PartnerRequest partnerRequest) throws RemoteException {
        PaymentInfo paymentInfo = (PaymentInfo) partnerRequest.obj1;
        if (getPartnerInfo().getData() == null) {
            getPartnerInfo().setData(new Bundle());
        }
        InAppPaymentCallback inAppPaymentCallback = new InAppPaymentCallback(partnerRequest);
        Log.d(TAG, getPartnerInfo().toString());
        iSPaymentManager.initiateSession(getPartnerInfo(), paymentInfo, inAppPaymentCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doStartWithCustomSheet, reason: merged with bridge method [inline-methods] */
    public void m3742x5e836d29(ISPaymentManager iSPaymentManager, PartnerRequest partnerRequest, RequestType[] requestTypeArr, UserInfoListener userInfoListener) throws RemoteException {
        CustomSheetPaymentInfo customSheetPaymentInfo = (CustomSheetPaymentInfo) partnerRequest.obj1;
        if (getPartnerInfo().getData() == null) {
            getPartnerInfo().setData(new Bundle());
        }
        InAppPaymentCallback inAppPaymentCallback = new InAppPaymentCallback(partnerRequest);
        Log.d(TAG, getPartnerInfo().toString());
        iSPaymentManager.initiateSessionWithSheetInfo(getPartnerInfo(), inAppPaymentCallback, customSheetPaymentInfo, requestTypeArr, userInfoListener != null ? new InAppUserInfoCallback(userInfoListener) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doRequestCardInfo, reason: merged with bridge method [inline-methods] */
    public void m3740x618db758(ISPaymentManager iSPaymentManager, PartnerRequest partnerRequest, GetCardBrandListenerInternal getCardBrandListenerInternal) throws RemoteException {
        List<CardInfo> requestCardType = iSPaymentManager.requestCardType((Bundle) partnerRequest.obj1, getPartnerInfo(), getCardBrandListenerInternal.getBrandListener());
        if (this.mIsAsyncResponse) {
            return;
        }
        ((CardInfoListener) partnerRequest.callbackObj).onResult(requestCardType);
        this.mPaymentManagerStub.nextRequest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doStartSimplePay, reason: merged with bridge method [inline-methods] */
    public void m3743x25e0b682(ISPaymentManager iSPaymentManager, PartnerRequest partnerRequest) throws RemoteException {
        iSPaymentManager.startSimplePay(getPartnerInfo(), (CardInfo) partnerRequest.obj2, (SpayStatusListener) partnerRequest.obj1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doGetFCOStatus, reason: merged with bridge method [inline-methods] */
    public void m3739x279d6798(ISPaymentManager iSPaymentManager, PartnerRequest partnerRequest) throws RemoteException {
        iSPaymentManager.getFastCheckoutStatus(getPartnerInfo(), (SpayStatusListener) partnerRequest.obj1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPaymentSheet(PartnerRequest partnerRequest, int i) {
        Log.d(TAG, "showPaymentSheet()");
        try {
            Intent intent = new Intent();
            Context context = this.contextRef.get();
            if (context == null) {
                throw new Exception("Context is destroyed");
            }
            if (context instanceof Activity) {
                Log.d(TAG, "Context is an instance of Activity");
                intent.setFlags(536870912);
            } else {
                Log.d(TAG, "Not Activity context");
                intent.setFlags(268435456);
            }
            intent.setComponent((ComponentName) partnerRequest.obj3);
            intent.putExtra(InternalConst.EXTRA_CALLER_UID, i);
            intent.putExtra(InternalConst.EXTRA_SDK_VERSION, 2);
            intent.putExtra(InternalConst.EXTRA_USE_BENDED_API, true);
            Object obj = partnerRequest.obj2;
            if (obj != null) {
                int intValue = ((Integer) obj).intValue();
                intent.putExtra(InternalConst.EXTRA_CUSTOM_SHEET_VERSION, intValue);
                Log.i(TAG, "customSheetVersion : " + intValue);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "showPaymentSheet - Error: " + e.toString());
            ((TransactionInfoListener) partnerRequest.callbackObj).onFailure(-1, null);
            nextRequest();
        }
    }

    public class InAppUserInfoCallback extends ISUserInfoCallback.Stub {
        private WeakReference<UserInfoListener> listenerRef;

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback
        public void onGetUserInfoInitiated(Bundle bundle) {
        }

        public InAppUserInfoCallback(UserInfoListener userInfoListener) {
            this.listenerRef = new WeakReference<>(userInfoListener);
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback
        public void onReceived(int i, Bundle bundle) {
            UserInfoListener userInfoListener = this.listenerRef.get();
            if (userInfoListener != null) {
                if (i == 0) {
                    userInfoListener.onSuccess(new UserInfoCollection(bundle));
                } else {
                    userInfoListener.onFail(i, bundle);
                }
            }
        }
    }

    public class InAppPaymentCallback extends ISPaymentCallback.Stub {
        private WeakReference<PartnerRequest> pReqRef;

        public InAppPaymentCallback(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private PartnerRequest getRequest() {
            PartnerRequest partnerRequest = this.pReqRef.get();
            if (partnerRequest == null) {
                Log.e(PaymentManager.TAG, "InAppPaymentCallback - request is NULL");
            }
            return partnerRequest;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onInitiateCompleted(Bundle bundle) {
            Log.d(PaymentManager.TAG, "onInitiateCompleted: ");
            PartnerRequest request = getRequest();
            if (request != null) {
                request.obj3 = new ComponentName(bundle.getString(InternalConst.EXTRA_PACKAGE_NAME), bundle.getString(InternalConst.EXTRA_CLASS_NAME));
                int i = bundle.getInt(InternalConst.EXTRA_CALLER_UID);
                Message message = new Message();
                message.what = 105;
                message.obj = request;
                message.arg1 = i;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onFailPayment(int i, String str) {
            Log.e(PaymentManager.TAG, "onFailPayment: errCode " + i);
            PartnerRequest request = getRequest();
            if (request != null) {
                PaymentManager.this.sendFailMessage(i, request);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onFailPaymentForCustomSheet(int i, Bundle bundle) {
            Log.e(PaymentManager.TAG, "onFailPaymentForCustomSheet: errCode " + i);
            PartnerRequest request = getRequest();
            if (request != null) {
                PaymentManager.this.sendFailMessageForCustomSheet(i, bundle, request);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onAddressInfoChanged(PaymentInfo paymentInfo) {
            Log.d(PaymentManager.TAG, "onAddressInfoChanged ");
            PartnerRequest request = getRequest();
            if (request != null) {
                Message message = new Message();
                message.what = 103;
                Bundle bundle = new Bundle();
                bundle.putParcelable(PaymentManager.KEY_PAYMENT_INFO, paymentInfo);
                message.setData(bundle);
                message.obj = request;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onCardInfoChanged(CardInfo cardInfo) {
            Log.d(PaymentManager.TAG, "onCardInfoChanged ");
            PartnerRequest request = getRequest();
            if (request != null) {
                request.obj3 = cardInfo;
                Message message = new Message();
                message.what = 104;
                message.obj = request;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onApproveTransaction(PaymentInfo paymentInfo, String str) {
            Log.d(PaymentManager.TAG, "onApproveTransaction ");
            PartnerRequest request = getRequest();
            if (request != null) {
                Message message = new Message();
                message.what = 101;
                Bundle bundle = new Bundle();
                bundle.putParcelable(PaymentManager.KEY_PAYMENT_INFO, paymentInfo);
                bundle.putString(PaymentManager.KEY_ENCRYPTED_DATA, str);
                message.setData(bundle);
                message.obj = request;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onCustomSheetUpdated(String str, CustomSheet customSheet) {
            Log.d(PaymentManager.TAG, "onCustomSheetUpdated ");
            PartnerRequest request = getRequest();
            if (request != null) {
                Message message = new Message();
                message.what = 106;
                Bundle bundle = new Bundle();
                bundle.putString(PaymentManager.KEY_UPDATED_CONTROL_ID, str);
                bundle.putParcelable(PaymentManager.KEY_CUSTOM_SHEET, customSheet);
                message.setData(bundle);
                message.obj = request;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onCustomSheetTransactionApproved(String str, Bundle bundle, CustomSheetPaymentInfo customSheetPaymentInfo) {
            Log.d(PaymentManager.TAG, "onCustomSheetTransactionApproved ");
            PartnerRequest request = getRequest();
            if (request != null) {
                Message message = new Message();
                message.what = 109;
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable(PaymentManager.KEY_CUSTOM_SHEET_PAYMENT_NFO, customSheetPaymentInfo);
                bundle2.putString(PaymentManager.KEY_ENCRYPTED_DATA, str);
                bundle2.putParcelable(PaymentManager.KEY_EXTRA_DATA, bundle);
                message.setData(bundle2);
                message.obj = request;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback
        public void onCustomSheetCardInfoUpdated(CardInfo cardInfo, CustomSheet customSheet) {
            Log.d(PaymentManager.TAG, "onCustomSheetCardInfoUpdated ");
            PartnerRequest request = getRequest();
            if (request != null) {
                Message message = new Message();
                message.what = 108;
                Bundle bundle = new Bundle();
                bundle.putParcelable(PaymentManager.KEY_UPDATE_CARD, cardInfo);
                bundle.putParcelable(PaymentManager.KEY_CUSTOM_SHEET, customSheet);
                message.setData(bundle);
                message.obj = request;
                PaymentManager.this.mEventHandler.sendMessage(message);
            }
        }
    }

    private class SpayStatusListener extends ISStatusListener.Stub {
        private static final String TAG = "SpayStatusListener";
        private WeakReference<StatusListener> partnerListenerRef;

        private SpayStatusListener() {
            this.partnerListenerRef = null;
        }

        public void setPartnerListenerRef(StatusListener statusListener) {
            this.partnerListenerRef = new WeakReference<>(statusListener);
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.ISStatusListener
        public void onSuccess(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
            Log.d(TAG, "onSuccess: status: " + i);
            PaymentManager.this.sendMsgForStatusListener(this.partnerListenerRef, 0, i, bundle);
            Log.d(TAG, "calling to clean up from queue");
            PaymentManager.this.nextRequest();
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.ISStatusListener
        public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
            Log.e(TAG, "onFail: errorCode: " + i);
            PaymentManager.this.sendMsgForStatusListener(this.partnerListenerRef, 1, i, bundle);
            Log.d(TAG, "calling to clean up from queue");
            PaymentManager.this.nextRequest();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GetCardBrandListenerInternal {
        private static final String TAG = "BrandListenerInternal";
        private SpayBrandListener mSpayGetBrandListener;
        private WeakReference<PartnerRequest> pReqRef;

        private GetCardBrandListenerInternal() {
            this.mSpayGetBrandListener = new SpayBrandListener();
            this.pReqRef = null;
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayBrandListener extends ISGetCardBrandListener.Stub {
            private SpayBrandListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISGetCardBrandListener
            public void onSuccess(List<CardInfo> list) throws RemoteException {
                Log.d(GetCardBrandListenerInternal.TAG, "onSuccess: ");
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("KEY_FOR_MESSAGE", (ArrayList) list);
                PaymentManager.this.sendMsgForGetCardBrandListener(GetCardBrandListenerInternal.this.pReqRef, 0, 0, bundle);
                PaymentManager.this.mPaymentManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISGetCardBrandListener
            public void onFail(int i, Bundle bundle) throws RemoteException {
                Log.d(GetCardBrandListenerInternal.TAG, "onFail: " + i);
                PaymentManager.this.sendMsgForGetCardBrandListener(GetCardBrandListenerInternal.this.pReqRef, 1, i, bundle);
                PaymentManager.this.mPaymentManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISGetCardBrandListener
            public void onProcess() {
                PaymentManager.this.mIsAsyncResponse = true;
                Log.d(GetCardBrandListenerInternal.TAG, "SpayBrandListener - requestCardType onProcess()");
            }
        }

        protected ISGetCardBrandListener getBrandListener() {
            if (this.mSpayGetBrandListener == null) {
                this.mSpayGetBrandListener = new SpayBrandListener();
            }
            return this.mSpayGetBrandListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgForGetCardBrandListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.setData(bundle);
        if (i == 1) {
            obtain.arg1 = i2;
        }
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    protected void sendMsgForStatusListener(WeakReference<StatusListener> weakReference, int i, int i2, Bundle bundle) {
        StatusListener statusListener;
        if (weakReference == null || (statusListener = weakReference.get()) == null) {
            Log.e(TAG, "sendMsgForStatusListener - Listener is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = statusListener;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    protected void setServicePackage(String str) {
        this.mPaymentManagerStub.setServicePackage(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFailure(Message message) {
        PartnerRequest partnerRequest = (PartnerRequest) message.obj;
        if (partnerRequest.callbackObj instanceof TransactionInfoListener) {
            ((TransactionInfoListener) partnerRequest.callbackObj).onFailure(message.arg1, message.getData());
        } else if (partnerRequest.callbackObj instanceof CustomSheetTransactionInfoListener) {
            ((CustomSheetTransactionInfoListener) partnerRequest.callbackObj).onFailure(message.arg1, message.getData());
        }
    }
}
