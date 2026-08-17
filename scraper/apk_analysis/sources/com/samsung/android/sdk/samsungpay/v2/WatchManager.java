package com.samsung.android.sdk.samsungpay.v2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.ISWatchManager;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SamsungPayBase;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.StubBase;
import com.samsung.android.sdk.samsungpay.v2.card.AddCardInfo;
import com.samsung.android.sdk.samsungpay.v2.card.AddCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.Card;
import com.samsung.android.sdk.samsungpay.v2.card.CardListener;
import com.samsung.android.sdk.samsungpay.v2.card.GetCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes2.dex */
public final class WatchManager extends SamsungPayBase implements AppToAppConstants {

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_5)
    public static final String DEVICE_SERIAL_NUM = "deviceSerialNum";
    public static final int ERROR_INVALID_PARAMETER = -504;
    public static final int ERROR_SPAY_PIN_LOCK_SETUP_CANCELED = -701;
    public static final int ERROR_SPAY_WATCH_CONNECTION = -705;
    public static final int ERROR_SPAY_WATCH_PAY_PROGRESS = -704;
    public static final int ERROR_SPAY_WATCH_PIN_LOCK_SETUP_CANCELED = -701;
    public static final int SPAY_WATCH_TAKING_LOG_FOR_REPORT = -703;
    public static final int SPAY_WATCH_UPDATE_IS_ONGOING = -702;
    private Card mCard;
    private List<Card> mCards;
    private Handler mHandlerForInternalListener;

    public WatchManager(Context context, PartnerInfo partnerInfo) {
        super(context, partnerInfo, "com.samsung.android.samsungpay.gear");
        this.mHandlerForInternalListener = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.WatchManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (message.obj instanceof GetCardListener) {
                        ((GetCardListener) message.obj).onSuccess(WatchManager.this.mCards);
                        return;
                    }
                    if (message.obj instanceof StatusListener) {
                        ((StatusListener) message.obj).onSuccess(message.arg1, message.getData());
                        return;
                    }
                    if (message.obj instanceof AddCardListener) {
                        ((AddCardListener) message.obj).onSuccess(message.arg1, WatchManager.this.mCard);
                        return;
                    } else if (message.obj instanceof CardListener) {
                        ((CardListener) message.obj).onSuccess(message.arg1, message.getData());
                        return;
                    } else {
                        Log.e(SamsungPayBase.TAG, "[onSuccess] Wrong listener was called");
                        return;
                    }
                }
                if (i != 1) {
                    if (i != 2) {
                        if (i != 4) {
                            Log.e(SamsungPayBase.TAG, "sdk can not catch listener from SPay.");
                            return;
                        }
                        return;
                    } else if (message.obj instanceof AddCardListener) {
                        ((AddCardListener) message.obj).onProgress(message.arg1, message.arg2, message.getData());
                        return;
                    } else {
                        Log.e(SamsungPayBase.TAG, "[onProgress] Wrong listener was called");
                        return;
                    }
                }
                if (message.obj instanceof GetCardListener) {
                    ((GetCardListener) message.obj).onFail(message.arg1, message.getData());
                    return;
                }
                if (message.obj instanceof StatusListener) {
                    ((StatusListener) message.obj).onFail(message.arg1, message.getData());
                    return;
                }
                if (message.obj instanceof AddCardListener) {
                    ((AddCardListener) message.obj).onFail(message.arg1, message.getData());
                } else if (message.obj instanceof CardListener) {
                    ((CardListener) message.obj).onFail(message.arg1, message.getData());
                } else {
                    Log.e(SamsungPayBase.TAG, "[onFail] Wrong listener was called");
                }
            }
        };
        TAG = "SPAYSDK:WatchManager";
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected StubBase<IInterface> createStub(Context context) {
        StubBase<IInterface> createStub = new StubBase.Creator().createStub(context, "com.samsung.android.samsungpay.gear.sdk.v2.service.WatchService", new StubBase.StubCaster() { // from class: com.samsung.android.sdk.samsungpay.v2.WatchManager$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.StubBase.StubCaster
            public final IInterface castStub(IBinder iBinder) {
                return ISWatchManager.Stub.asInterface(iBinder);
            }
        });
        createStub.setServicePackage("com.samsung.android.samsungpay.gear");
        return createStub;
    }

    public void getSamsungPayStatus(StatusListener statusListener) {
        super.getSamsungPayStatus(statusListener, SpaySdk.SdkApiLevel.LEVEL_2_5);
    }

    public void goToUpdatePage() {
        super.goToUpdatePage(SpaySdk.SdkApiLevel.LEVEL_2_5);
    }

    public void activateSamsungPay() {
        super.activateSamsungPay(SpaySdk.SdkApiLevel.LEVEL_2_5);
    }

    public void getWalletInfo(List<String> list, StatusListener statusListener) {
        super.getWalletInfo(list, statusListener, SpaySdk.SdkApiLevel.LEVEL_2_5);
    }

    public void addCard(final AddCardInfo addCardInfo, final AddCardListener addCardListener) {
        Log.d(TAG, "addCard()");
        assertNotNull(addCardListener);
        assertNotNull(addCardInfo);
        assertNotNull(addCardInfo.getTokenizationProvider());
        assertNotNull(addCardInfo.getCardDetail());
        assertNotNull(addCardInfo.getCardDetail().getString(AddCardInfo.EXTRA_PROVISION_PAYLOAD));
        final AddCardInfoListenerInternal addCardInfoListenerInternal = new AddCardInfoListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 11, addCardListener).setName("WatchManager addCard").checkApiLevelFor(addCardInfo).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.WatchManager$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                WatchManager.this.m3728x1f066204(addCardInfo, addCardInfoListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.WatchManager$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                AddCardListener.this.onFail(i, bundle);
            }
        }).create();
        addCardInfoListenerInternal.set(create);
        this.mSamsungPayBaseStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_17);
    }

    /* renamed from: lambda$addCard$0$com-samsung-android-sdk-samsungpay-v2-WatchManager, reason: not valid java name */
    /* synthetic */ void m3728x1f066204(AddCardInfo addCardInfo, AddCardInfoListenerInternal addCardInfoListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISWatchManager) iInterface).addCard(getPartnerInfo(), addCardInfo, addCardInfoListenerInternal.getAddCardListener());
    }

    public void getAllCards(final Bundle bundle, final GetCardListener getCardListener) {
        Log.d(TAG, "getAllCards() : SDK API Level = 2.13");
        assertNotNull(getCardListener);
        if (bundle == null) {
            bundle = new Bundle();
        }
        final GetCardListenerInternal getCardListenerInternal = new GetCardListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 10, getCardListener).setName("WatchManager getAllCards").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.WatchManager$$ExternalSyntheticLambda3
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                WatchManager.this.m3729x6001fa3b(bundle, getCardListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.WatchManager$$ExternalSyntheticLambda4
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle2) {
                GetCardListener.this.onFail(i, bundle2);
            }
        }).create();
        getCardListenerInternal.set(create);
        this.mSamsungPayBaseStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_13);
    }

    /* renamed from: lambda$getAllCards$2$com-samsung-android-sdk-samsungpay-v2-WatchManager, reason: not valid java name */
    /* synthetic */ void m3729x6001fa3b(Bundle bundle, GetCardListenerInternal getCardListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISWatchManager) iInterface).getAllCards(getPartnerInfo(), bundle, getCardListenerInternal.getCardListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GetCardListenerInternal {
        private final String TAG;
        private SpayCardListener mSpayGetCardListener;
        private WeakReference<PartnerRequest> pReqRef;

        private GetCardListenerInternal() {
            this.mSpayGetCardListener = new SpayCardListener();
            this.pReqRef = null;
            this.TAG = GetCardListenerInternal.class.getSimpleName();
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayCardListener extends ISGetCardListener.Stub {
            private SpayCardListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
            public void onSuccess(PartnerInfo partnerInfo, List<Card> list) throws RemoteException {
                Log.d(GetCardListenerInternal.this.TAG, "onSuccess: ");
                WatchManager.this.mCards = list;
                WatchManager.this.sendMsgForGetCardListener(GetCardListenerInternal.this.pReqRef, 0, 0, null);
                WatchManager.this.mSamsungPayBaseStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
            public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                Log.d(GetCardListenerInternal.this.TAG, "onFail: " + i);
                WatchManager.this.sendMsgForGetCardListener(GetCardListenerInternal.this.pReqRef, 1, i, bundle);
                WatchManager.this.mSamsungPayBaseStub.nextRequest();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ISGetCardListener getCardListener() {
            if (this.mSpayGetCardListener == null) {
                this.mSpayGetCardListener = new SpayCardListener();
            }
            return this.mSpayGetCardListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AddCardInfoListenerInternal {
        private final String TAG;
        private WeakReference<PartnerRequest> pReqRef;
        private SpayAddCardListener spayAddCardListener;

        private AddCardInfoListenerInternal() {
            this.TAG = AddCardInfoListenerInternal.class.getSimpleName();
            this.pReqRef = null;
            this.spayAddCardListener = new SpayAddCardListener();
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayAddCardListener extends ISAddCardListener.Stub {
            private SpayAddCardListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener
            public void onSuccess(int i, Card card) throws RemoteException {
                Log.d(AddCardInfoListenerInternal.this.TAG, "onSuccess: status: " + i);
                WatchManager.this.mCard = card;
                WatchManager.this.sendMsgForAddCardListener(AddCardInfoListenerInternal.this.pReqRef, 0, i, 0, null);
                WatchManager.this.mSamsungPayBaseStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener
            public void onFail(int i, Bundle bundle) throws RemoteException {
                Log.e(AddCardInfoListenerInternal.this.TAG, "onFail: errorCode: " + i);
                WatchManager.this.sendMsgForAddCardListener(AddCardInfoListenerInternal.this.pReqRef, 1, i, 0, bundle);
                WatchManager.this.mSamsungPayBaseStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener
            public void onProgress(int i, int i2, Bundle bundle) {
                Log.e(AddCardInfoListenerInternal.this.TAG, "onNotifyProgress: currentCount: " + i + ", totalCount : " + i2);
                WatchManager.this.sendMsgForAddCardListener(AddCardInfoListenerInternal.this.pReqRef, 2, i, i2, bundle);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ISAddCardListener getAddCardListener() {
            return this.spayAddCardListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgForGetCardListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        if (i == 1) {
            obtain.arg1 = i2;
            obtain.setData(bundle);
        }
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgForAddCardListener(WeakReference<PartnerRequest> weakReference, int i, int i2, int i3, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "sendMsgForAddCardListener - request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        if (i == 1) {
            obtain.setData(bundle);
        } else if (i == 2) {
            obtain.arg2 = i3;
            obtain.setData(bundle);
        }
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected void doGetWalletInfo(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException {
        SamsungPayBase.StatusListenerInternal statusListenerInternal = (SamsungPayBase.StatusListenerInternal) partnerRequest.obj2;
        ((ISWatchManager) iInterface).getWalletInfo(getPartnerInfo(), (List) partnerRequest.obj1, statusListenerInternal.getStatusListener());
        this.mSamsungPayBaseStub.nextRequest();
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    /* renamed from: doGetSamsungPayStatus */
    protected void m3725x6794b1c0(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException {
        Status samsungPayStatus = ((ISWatchManager) iInterface).getSamsungPayStatus(getPartnerInfo());
        if (samsungPayStatus != null) {
            int status = samsungPayStatus.getStatus();
            if (status == 0 || status == 1 || status == 2) {
                ((StatusListener) partnerRequest.callbackObj).onSuccess(status, samsungPayStatus.getData());
            } else {
                ((StatusListener) partnerRequest.callbackObj).onFail(status, samsungPayStatus.getData());
            }
        }
        this.mSamsungPayBaseStub.nextRequest();
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected void doGoToUpdatePage() {
        Log.d(TAG, "goToUpdatePage");
        Context context = this.contextRef.get();
        if (context != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("gearsamsungpay://launch?action=settings&menu=about_samsung_pay"));
            intent.addFlags(268468224);
            context.startActivity(intent);
        } else {
            Log.e(TAG, "doGoToUpdatePage - Context is null");
        }
        this.mSamsungPayBaseStub.nextRequest();
    }
}
