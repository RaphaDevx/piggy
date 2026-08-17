package com.samsung.android.sdk.samsungpay.v2;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.ISStatusListener;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class SamsungPayBase extends SpaySdk {
    private static final int OPT_ACTIVATE_SPAY = 1;
    static final int OPT_ADD_CARD = 11;
    static final int OPT_GET_ALL_CARDS = 10;
    private static final int OPT_GET_SPAY_STATUS = 0;
    static final int OPT_GET_TRANSIT_STATUS = 21;
    private static final int OPT_GET_WALLET_INFO = 2;
    private static final int OPT_GO_TO_UPDATE_PAGE = 3;
    protected static String TAG = "SPAYSDK:SamsungPayBase";
    private Handler mHandlerForInternalListener;
    protected String mPackageName;
    protected StubBase<IInterface> mSamsungPayBaseStub;

    protected abstract StubBase<IInterface> createStub(Context context);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: doGetSamsungPayStatus, reason: merged with bridge method [inline-methods] */
    public abstract void m3725x6794b1c0(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException;

    protected abstract void doGetWalletInfo(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException;

    protected abstract void doGoToUpdatePage() throws PackageManager.NameNotFoundException;

    protected SamsungPayBase(Context context, PartnerInfo partnerInfo, String str) {
        super(context, partnerInfo);
        this.mHandlerForInternalListener = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    ((StatusListener) message.obj).onSuccess(message.arg1, message.getData());
                    return;
                }
                if (i == 1) {
                    ((StatusListener) message.obj).onFail(message.arg1, message.getData());
                } else {
                    if (i == 2 || i == 4) {
                        return;
                    }
                    Log.e(SamsungPayBase.TAG, "sdk can not catch listener from SPay.");
                }
            }
        };
        Log.d(TAG, "Partner SDK version : " + getVersionName());
        if (!isValidContextAndServiceId(partnerInfo)) {
            throw new NullPointerException("Context and PartnerInfo.serviceId have to be set.");
        }
        this.mSamsungPayBaseStub = createStub(context);
        this.mPackageName = str;
    }

    protected void getSamsungPayStatus(final StatusListener statusListener, SpaySdk.SdkApiLevel sdkApiLevel) {
        Log.d(TAG, "getSamsungPayStatus()");
        assertNotNull(statusListener);
        this.mSamsungPayBaseStub.postRequest(new PartnerRequest.Builder(this, 0, statusListener).setName("getSamsungPayStatus").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                SamsungPayBase.this.m3725x6794b1c0(iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                SamsungPayBase.lambda$getSamsungPayStatus$1(StatusListener.this, errorType, i, bundle);
            }
        }).create(), sdkApiLevel);
    }

    static /* synthetic */ void lambda$getSamsungPayStatus$1(StatusListener statusListener, ErrorType errorType, int i, Bundle bundle) {
        if (errorType == ErrorType.SPAY_VALIDITY_FAIL) {
            statusListener.onSuccess(i, bundle);
        } else if (errorType == ErrorType.PARTNER_INFO_INVALID) {
            statusListener.onFail(-99, bundle);
        } else {
            statusListener.onFail(SpaySdk.ERROR_INITIATION_FAIL, bundle);
        }
    }

    protected void goToUpdatePage(SpaySdk.SdkApiLevel sdkApiLevel) {
        Log.d(TAG, "goToUpdatePage()");
        this.mSamsungPayBaseStub.postRequest(new PartnerRequest.Builder(this, 3, null).setName("goToUpdatePage").needStub(false).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase$$ExternalSyntheticLambda3
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                SamsungPayBase.this.m3726x4f6163bf(iInterface, partnerRequest);
            }
        }).create(), sdkApiLevel);
    }

    /* renamed from: lambda$goToUpdatePage$2$com-samsung-android-sdk-samsungpay-v2-SamsungPayBase, reason: not valid java name */
    /* synthetic */ void m3726x4f6163bf(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        doGoToUpdatePage();
    }

    protected void activateSamsungPay(SpaySdk.SdkApiLevel sdkApiLevel) {
        Log.d(TAG, "activateSamsungPay()");
        this.mSamsungPayBaseStub.postRequest(new PartnerRequest.Builder(this, 1, null).setName("activateSamsungPay").needStub(false).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                SamsungPayBase.this.m3724x31050b58(iInterface, partnerRequest);
            }
        }).create(), sdkApiLevel);
    }

    /* renamed from: lambda$activateSamsungPay$3$com-samsung-android-sdk-samsungpay-v2-SamsungPayBase, reason: not valid java name */
    /* synthetic */ void m3724x31050b58(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        doActivateSamsungPay();
    }

    protected void getWalletInfo(List<String> list, final StatusListener statusListener, SpaySdk.SdkApiLevel sdkApiLevel) {
        Log.d(TAG, "getWalletInfo()");
        assertNotNull(statusListener);
        assertNotNull(list);
        StatusListenerInternal statusListenerInternal = new StatusListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 2, statusListener).setName("getWalletInfo").setObj1(list).setObj2(statusListenerInternal).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase$$ExternalSyntheticLambda4
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                SamsungPayBase.this.doGetWalletInfo(iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPayBase$$ExternalSyntheticLambda5
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                StatusListener.this.onFail(i, bundle);
            }
        }).create();
        this.mSamsungPayBaseStub.postRequest(create, sdkApiLevel);
        statusListenerInternal.set(create);
    }

    protected class StatusListenerInternal {
        private static final String TAG = "StatusListenerInternal";
        private WeakReference<PartnerRequest> pReqRef = null;
        private SpayStatusListener spayStatusListener = new SpayStatusListener();

        protected StatusListenerInternal() {
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayStatusListener extends ISStatusListener.Stub {
            private SpayStatusListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISStatusListener
            public void onSuccess(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                Log.d(StatusListenerInternal.TAG, "onSuccess: status: " + i);
                SamsungPayBase.this.sendMsgForStatusListener(StatusListenerInternal.this.pReqRef, 0, i, bundle);
                SamsungPayBase.this.mSamsungPayBaseStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISStatusListener
            public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                Log.e(StatusListenerInternal.TAG, "onFail: errorCode: " + i);
                SamsungPayBase.this.sendMsgForStatusListener(StatusListenerInternal.this.pReqRef, 1, i, bundle);
                SamsungPayBase.this.mSamsungPayBaseStub.nextRequest();
            }
        }

        public ISStatusListener getStatusListener() {
            return this.spayStatusListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgForStatusListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.e(TAG, "sendMsgForStatusListener - request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    private void doActivateSamsungPay() {
        Intent intent = new Intent();
        Context context = this.contextRef.get();
        if (context != null) {
            intent.setComponent(new ComponentName(this.mPackageName, context.getPackageManager().getLaunchIntentForPackage(this.mPackageName).getComponent().getClassName()));
            intent.setFlags(268435456);
            intent.putExtra("activate_sp_from_sdk", true);
            Log.d(TAG, "ACTIVATE_SP_FROM_SDK");
            context.startActivity(intent);
        } else {
            Log.e(TAG, "doActivateSamsungPay - Context is null");
        }
        this.mSamsungPayBaseStub.nextRequest();
    }

    protected void setServicePackage(String str) {
        this.mSamsungPayBaseStub.setServicePackage(str);
    }

    protected void checkValidListener(StatusListener statusListener) {
        if (statusListener != null) {
            return;
        }
        Log.e(TAG, "checkValidListener: Cb is null.You should set first.");
        throw new NullPointerException("Cb is null.You should set first.");
    }
}
