package com.samsung.android.sdk.samsungpay.v2.card;

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
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.AppToAppConstants;
import com.samsung.android.sdk.samsungpay.v2.ErrorType;
import com.samsung.android.sdk.samsungpay.v2.ISStatusListener;
import com.samsung.android.sdk.samsungpay.v2.InternalConst;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.StatusListener;
import com.samsung.android.sdk.samsungpay.v2.StubBase;
import com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISCardManager;
import com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISInitiateSessionCallback;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class CardManager extends SpaySdk implements AppToAppConstants {
    public static final int ERROR_INVALID_PARAMETER = -504;
    private static final int OPT_ADD_CARD = 2;
    private static final int OPT_GET_ALL_CARDS = 1;
    private static final int OPT_REQUEST_PAY = 4;
    private static final int OPT_UPDATE_ADDITIONAL_SERVICE = 5;
    private static final int OPT_UPDATE_CARD = 6;
    private static final int OPT_VERIFY_CARD_IDV = 3;
    private static final String TAG = "SPAYSDK:CardManager";
    private StubBase<ISCardManager> mCardManagerStub;
    protected Handler mHandlerForInternalListener;
    private final Handler mHandlerForSessionCallback;

    public CardManager(Context context, PartnerInfo partnerInfo) {
        super(context, partnerInfo);
        this.mHandlerForInternalListener = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (message.obj instanceof GetCardListener) {
                        ((GetCardListener) message.obj).onSuccess(message.getData().getParcelableArrayList("KEY_FOR_MESSAGE"));
                        return;
                    }
                    if (message.obj instanceof StatusListener) {
                        ((StatusListener) message.obj).onSuccess(message.arg1, message.getData());
                        return;
                    }
                    if (message.obj instanceof AddCardListener) {
                        ((AddCardListener) message.obj).onSuccess(message.arg1, (Card) message.getData().getParcelable("KEY_FOR_MESSAGE"));
                        return;
                    } else if (message.obj instanceof CardListener) {
                        ((CardListener) message.obj).onSuccess(message.arg1, message.getData());
                        return;
                    } else {
                        Log.e(CardManager.TAG, "[onSuccess] Wrong listener was called");
                        return;
                    }
                }
                if (i != 1) {
                    if (i != 2) {
                        if (i != 4) {
                            Log.e(CardManager.TAG, "sdk can not catch listener from SPay.");
                            return;
                        }
                        return;
                    } else if (message.obj instanceof AddCardListener) {
                        ((AddCardListener) message.obj).onProgress(message.arg1, message.arg2, message.getData());
                        return;
                    } else {
                        Log.e(CardManager.TAG, "[onProgress] Wrong listener was called");
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
                    Log.e(CardManager.TAG, "[onFail] Wrong listener was called");
                }
            }
        };
        this.mHandlerForSessionCallback = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                UpdateServiceSessionCallback updateServiceSessionCallback = (UpdateServiceSessionCallback) message.obj;
                CardListener cardListener = (CardListener) updateServiceSessionCallback.cardListenerRef.get();
                int i = message.what;
                if (i == 0) {
                    if (cardListener != null) {
                        cardListener.onSuccess(message.arg1, message.getData());
                    }
                } else if (i != 1) {
                    if (i != 3) {
                        return;
                    }
                    CardManager.this.showUpdateServiceSheet(updateServiceSessionCallback, message.arg1);
                } else if (cardListener != null) {
                    cardListener.onFail(message.arg1, message.getData());
                }
            }
        };
        Log.d(TAG, "CardManager()");
        this.mCardManagerStub = new StubBase.Creator().createStub(context, InternalConst.SERVICE_ACTION_CARD, new StubBase.StubCaster() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.StubBase.StubCaster
            public final IInterface castStub(IBinder iBinder) {
                return ISCardManager.Stub.asInterface(iBinder);
            }
        });
    }

    public void addCard(final AddCardInfo addCardInfo, final AddCardListener addCardListener) {
        Log.d(TAG, "addCard() : SDK API Level = 1.2");
        assertNotNull(addCardListener);
        assertNotNull(addCardInfo);
        assertNotNull(addCardInfo.getTokenizationProvider());
        assertNotNull(addCardInfo.getCardDetail());
        assertNotNull(addCardInfo.getCardDetail().getString(AddCardInfo.EXTRA_PROVISION_PAYLOAD));
        final AddCardInfoListenerInternal addCardInfoListenerInternal = new AddCardInfoListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 2, addCardListener).setName("addCard").checkApiLevelFor(addCardInfo).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                CardManager.this.m3730xda70a626(addCardInfo, addCardInfoListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda3
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                AddCardListener.this.onFail(i, bundle);
            }
        }).create();
        addCardInfoListenerInternal.set(create);
        this.mCardManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_1_2);
    }

    /* renamed from: lambda$addCard$0$com-samsung-android-sdk-samsungpay-v2-card-CardManager, reason: not valid java name */
    /* synthetic */ void m3730xda70a626(AddCardInfo addCardInfo, AddCardInfoListenerInternal addCardInfoListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISCardManager) iInterface).addCard(getPartnerInfo(), addCardInfo, addCardInfoListenerInternal.getAddCardListener());
    }

    public void getAllCards(final Bundle bundle, final GetCardListener getCardListener) {
        Log.d(TAG, "getAllCards() : SDK API Level = 2.15");
        assertNotNull(getCardListener);
        if (bundle == null) {
            bundle = new Bundle();
        }
        final GetCardListenerInternal getCardListenerInternal = new GetCardListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 1, getCardListener).setName("getAllCards").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda6
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                CardManager.this.m3731x86e74add(bundle, getCardListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda7
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle2) {
                GetCardListener.this.onFail(i, bundle2);
            }
        }).create();
        getCardListenerInternal.set(create);
        this.mCardManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_15);
    }

    /* renamed from: lambda$getAllCards$2$com-samsung-android-sdk-samsungpay-v2-card-CardManager, reason: not valid java name */
    /* synthetic */ void m3731x86e74add(Bundle bundle, GetCardListenerInternal getCardListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISCardManager) iInterface).getAllCards(getPartnerInfo(), bundle, getCardListenerInternal.getCardListener());
    }

    public void verifyCardIdv(final IdvVerifyInfo idvVerifyInfo, final StatusListener statusListener) {
        Log.d(TAG, "verifyCardIdv() : SDK API Level = 1.1");
        assertNotNull(statusListener);
        assertNotNull(idvVerifyInfo);
        final StatusListenerInternal statusListenerInternal = new StatusListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 3, statusListener).setName("verifyCardIdv").checkApiLevelFor(idvVerifyInfo.getCardInfoData()).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda10
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                CardManager.this.m3734x9a2ab781(idvVerifyInfo, statusListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                StatusListener.this.onFail(i, bundle);
            }
        }).create();
        statusListenerInternal.set(create);
        this.mCardManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_1_1);
    }

    /* renamed from: lambda$verifyCardIdv$4$com-samsung-android-sdk-samsungpay-v2-card-CardManager, reason: not valid java name */
    /* synthetic */ void m3734x9a2ab781(IdvVerifyInfo idvVerifyInfo, StatusListenerInternal statusListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISCardManager) iInterface).verifyCardIdv(getPartnerInfo(), idvVerifyInfo, statusListenerInternal.getStatusListener());
    }

    public void updateAdditionalService(final Card card, final CardListener cardListener) {
        Log.d(TAG, "updateAdditionalService() : SDK API Level = 1.8");
        assertNotNull(cardListener);
        assertNotNull(card);
        StatusListenerInternal statusListenerInternal = new StatusListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 5, cardListener).setName("updateAdditionalService").checkDuplication(true).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda4
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                CardManager.this.m3732x6dc386c(card, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda5
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                CardListener.this.onFail(i, bundle);
            }
        }).create();
        statusListenerInternal.set(create);
        this.mCardManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_1_8);
    }

    /* renamed from: lambda$updateAdditionalService$6$com-samsung-android-sdk-samsungpay-v2-card-CardManager, reason: not valid java name */
    /* synthetic */ void m3732x6dc386c(Card card, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        if (getPartnerInfo().getData() == null) {
            getPartnerInfo().setData(new Bundle());
        }
        ((ISCardManager) iInterface).initiateSessionWithCardInfo(getPartnerInfo(), new UpdateServiceSessionCallback(partnerRequest).getInitiateSessionCallback(), card);
    }

    @Deprecated
    public void updateCard(final Card card, final CardListener cardListener) {
        Log.d(TAG, "updateCard() : SDK API Level = 2.2");
        assertNotNull(cardListener);
        assertNotNull(card);
        final StatusListenerInternal statusListenerInternal = new StatusListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 6, cardListener).setName("updateCard").checkDuplication(true).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda8
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                CardManager.this.m3733x974b0e5c(card, statusListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.card.CardManager$$ExternalSyntheticLambda9
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                CardListener.this.onFail(i, bundle);
            }
        }).create();
        statusListenerInternal.set(create);
        this.mCardManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_2);
    }

    /* renamed from: lambda$updateCard$8$com-samsung-android-sdk-samsungpay-v2-card-CardManager, reason: not valid java name */
    /* synthetic */ void m3733x974b0e5c(Card card, StatusListenerInternal statusListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISCardManager) iInterface).updateCard(getPartnerInfo(), card, statusListenerInternal.getStatusListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GetCardListenerInternal extends InternalListenerBase {
        private static final String TAG = "GetCardListenerInternal";
        private SpayCardListener mSpayGetCardListener;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private GetCardListenerInternal() {
            /*
                r1 = this;
                com.samsung.android.sdk.samsungpay.v2.card.CardManager.this = r2
                r2 = 0
                r1.<init>()
                com.samsung.android.sdk.samsungpay.v2.card.CardManager$GetCardListenerInternal$SpayCardListener r0 = new com.samsung.android.sdk.samsungpay.v2.card.CardManager$GetCardListenerInternal$SpayCardListener
                r0.<init>()
                r1.mSpayGetCardListener = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.samsungpay.v2.card.CardManager.GetCardListenerInternal.<init>(com.samsung.android.sdk.samsungpay.v2.card.CardManager):void");
        }

        private class SpayCardListener extends ISGetCardListener.Stub {
            private SpayCardListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
            public void onSuccess(PartnerInfo partnerInfo, List<Card> list) throws RemoteException {
                Log.d(GetCardListenerInternal.TAG, "onSuccess: ");
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("KEY_FOR_MESSAGE", (ArrayList) list);
                CardManager.this.sendMsgForGetCardListener(GetCardListenerInternal.this.pReqRef, 0, 0, bundle);
                CardManager.this.mCardManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
            public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                if (GetCardListenerInternal.this.isValidRequest("GetCardListenerInternal onFail")) {
                    Log.d(GetCardListenerInternal.TAG, "onFail: " + i);
                    CardManager.this.sendMsgForGetCardListener(GetCardListenerInternal.this.pReqRef, 1, i, bundle);
                    CardManager.this.mCardManagerStub.nextRequest();
                }
            }
        }

        protected ISGetCardListener getCardListener() {
            if (this.mSpayGetCardListener == null) {
                this.mSpayGetCardListener = new SpayCardListener();
            }
            return this.mSpayGetCardListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class StatusListenerInternal extends InternalListenerBase {
        private static final String TAG = "StatusListenerInternal";
        private SpayStatusListener spayStatusListener;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private StatusListenerInternal() {
            /*
                r1 = this;
                com.samsung.android.sdk.samsungpay.v2.card.CardManager.this = r2
                r2 = 0
                r1.<init>()
                com.samsung.android.sdk.samsungpay.v2.card.CardManager$StatusListenerInternal$SpayStatusListener r0 = new com.samsung.android.sdk.samsungpay.v2.card.CardManager$StatusListenerInternal$SpayStatusListener
                r0.<init>()
                r1.spayStatusListener = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.samsungpay.v2.card.CardManager.StatusListenerInternal.<init>(com.samsung.android.sdk.samsungpay.v2.card.CardManager):void");
        }

        private class SpayStatusListener extends ISStatusListener.Stub {
            private SpayStatusListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISStatusListener
            public void onSuccess(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                Log.d(StatusListenerInternal.TAG, "onSuccess: status: " + i);
                CardManager.this.sendMsgForStatusListener(StatusListenerInternal.this.pReqRef, 0, i, bundle);
                CardManager.this.mCardManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISStatusListener
            public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                if (StatusListenerInternal.this.isValidRequest("StatusListenerInternal onFail")) {
                    Log.e(StatusListenerInternal.TAG, "onFail: errorCode: " + i);
                    CardManager.this.sendMsgForStatusListener(StatusListenerInternal.this.pReqRef, 1, i, bundle);
                    CardManager.this.mCardManagerStub.nextRequest();
                }
            }
        }

        public ISStatusListener getStatusListener() {
            return this.spayStatusListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AddCardInfoListenerInternal extends InternalListenerBase {
        private static final String TAG = "AddCardInfoInternal";
        private SpayAddCardListener spayAddCardListener;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private AddCardInfoListenerInternal() {
            /*
                r1 = this;
                com.samsung.android.sdk.samsungpay.v2.card.CardManager.this = r2
                r2 = 0
                r1.<init>()
                com.samsung.android.sdk.samsungpay.v2.card.CardManager$AddCardInfoListenerInternal$SpayAddCardListener r0 = new com.samsung.android.sdk.samsungpay.v2.card.CardManager$AddCardInfoListenerInternal$SpayAddCardListener
                r0.<init>()
                r1.spayAddCardListener = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.samsungpay.v2.card.CardManager.AddCardInfoListenerInternal.<init>(com.samsung.android.sdk.samsungpay.v2.card.CardManager):void");
        }

        private class SpayAddCardListener extends ISAddCardListener.Stub {
            private SpayAddCardListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener
            public void onSuccess(int i, Card card) throws RemoteException {
                Log.d(AddCardInfoListenerInternal.TAG, "onSuccess: status: " + i);
                Bundle bundle = new Bundle();
                bundle.putParcelable("KEY_FOR_MESSAGE", card);
                CardManager.this.sendMsgForAddCardListener(AddCardInfoListenerInternal.this.pReqRef, 0, i, 0, bundle);
                CardManager.this.mCardManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener
            public void onFail(int i, Bundle bundle) throws RemoteException {
                if (AddCardInfoListenerInternal.this.isValidRequest("AddCardInfoInternal onFail")) {
                    Log.e(AddCardInfoListenerInternal.TAG, "onFail: errorCode: " + i);
                    CardManager.this.sendMsgForAddCardListener(AddCardInfoListenerInternal.this.pReqRef, 1, i, 0, bundle);
                    CardManager.this.mCardManagerStub.nextRequest();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener
            public void onProgress(int i, int i2, Bundle bundle) {
                if (AddCardInfoListenerInternal.this.isValidRequest("AddCardInfoInternal onProgress")) {
                    Log.e(AddCardInfoListenerInternal.TAG, "onProgress: currentCount: " + i + ", totalCount : " + i2);
                    CardManager.this.sendMsgForAddCardListener(AddCardInfoListenerInternal.this.pReqRef, 2, i, i2, bundle);
                }
            }
        }

        public ISAddCardListener getAddCardListener() {
            return this.spayAddCardListener;
        }
    }

    private static abstract class InternalListenerBase {
        protected WeakReference<PartnerRequest> pReqRef;

        private InternalListenerBase() {
            this.pReqRef = null;
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        public boolean isValidRequest(String str) {
            WeakReference<PartnerRequest> weakReference = this.pReqRef;
            if (weakReference != null && weakReference.get() != null && !this.pReqRef.get().isCancelled) {
                return true;
            }
            Log.e(CardManager.TAG, str + ": Request is cancelled or invalid");
            return false;
        }
    }

    protected void sendMsgForGetCardListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
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

    protected void sendMsgForStatusListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "sendMsgForStatusListener -  request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    protected void sendMsgForAddCardListener(WeakReference<PartnerRequest> weakReference, int i, int i2, int i3, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "sendMsgForAddCardListener - request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        if (i == 2) {
            obtain.arg2 = i3;
        }
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    private class UpdateServiceSessionCallback extends InternalListenerBase {
        private static final String TAG = "UpdateServiceCallback";
        private WeakReference<CardListener> cardListenerRef;
        private ComponentName compName;
        private InitiateSessionCallback initiateCallback;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public UpdateServiceSessionCallback(com.samsung.android.sdk.samsungpay.v2.PartnerRequest r3) {
            /*
                r1 = this;
                com.samsung.android.sdk.samsungpay.v2.card.CardManager.this = r2
                r2 = 0
                r1.<init>()
                r1.compName = r2
                com.samsung.android.sdk.samsungpay.v2.card.CardManager$UpdateServiceSessionCallback$InitiateSessionCallback r0 = new com.samsung.android.sdk.samsungpay.v2.card.CardManager$UpdateServiceSessionCallback$InitiateSessionCallback
                r0.<init>()
                r1.initiateCallback = r0
                r1.set(r3)
                java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
                java.lang.Object r3 = r3.callbackObj
                com.samsung.android.sdk.samsungpay.v2.card.CardListener r3 = (com.samsung.android.sdk.samsungpay.v2.card.CardListener) r3
                r2.<init>(r3)
                r1.cardListenerRef = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.samsungpay.v2.card.CardManager.UpdateServiceSessionCallback.<init>(com.samsung.android.sdk.samsungpay.v2.card.CardManager, com.samsung.android.sdk.samsungpay.v2.PartnerRequest):void");
        }

        public InitiateSessionCallback getInitiateSessionCallback() {
            return this.initiateCallback;
        }

        private class InitiateSessionCallback extends ISInitiateSessionCallback.Stub {
            private InitiateSessionCallback() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISInitiateSessionCallback
            public void onSessionCompleted(Bundle bundle) {
                Log.d(UpdateServiceSessionCallback.TAG, "onInitiateCompleted: ");
                String string = bundle.getString(InternalConst.EXTRA_PACKAGE_NAME);
                String string2 = bundle.getString(InternalConst.EXTRA_CLASS_NAME);
                UpdateServiceSessionCallback.this.compName = new ComponentName(string, string2);
                int i = bundle.getInt(InternalConst.EXTRA_CALLER_UID);
                Message message = new Message();
                message.what = 3;
                message.obj = UpdateServiceSessionCallback.this;
                message.arg1 = i;
                CardManager.this.mHandlerForSessionCallback.sendMessage(message);
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISInitiateSessionCallback
            public void onSessionFailed(int i, Bundle bundle) {
                if (UpdateServiceSessionCallback.this.isValidRequest("UpdateServiceCallback onSessionFailed")) {
                    Log.e(UpdateServiceSessionCallback.TAG, "onSessionFailed: errorCode " + i);
                    Message message = new Message();
                    message.what = 1;
                    Bundle bundle2 = new Bundle(bundle);
                    message.obj = UpdateServiceSessionCallback.this;
                    message.arg1 = i;
                    message.setData(bundle2);
                    CardManager.this.mHandlerForSessionCallback.sendMessage(message);
                }
                CardManager.this.mCardManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISInitiateSessionCallback
            public void onAuthenticationApproved(int i, Bundle bundle) {
                Log.e(UpdateServiceSessionCallback.TAG, "onAuthenticationApproved: errorData " + bundle);
                Message message = new Message();
                message.what = 0;
                Bundle bundle2 = new Bundle(bundle);
                message.obj = UpdateServiceSessionCallback.this;
                message.arg1 = i;
                message.setData(bundle2);
                CardManager.this.mHandlerForSessionCallback.sendMessage(message);
                CardManager.this.mCardManagerStub.nextRequest();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpdateServiceSheet(UpdateServiceSessionCallback updateServiceSessionCallback, int i) {
        Log.d(TAG, "showUpdateCardSheet()");
        Context context = this.contextRef.get();
        if (context != null) {
            try {
                Intent intent = new Intent();
                if (context instanceof Activity) {
                    Log.d(TAG, "Context is an instance of Activity");
                    intent.setFlags(536870912);
                } else {
                    Log.d(TAG, "Not Activity context");
                    intent.setFlags(268435456);
                }
                intent.setComponent(updateServiceSessionCallback.compName);
                intent.putExtra(InternalConst.EXTRA_CALLER_UID, i);
                intent.putExtra(InternalConst.EXTRA_SDK_VERSION, 2);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, "activity not found and return error");
                CardListener cardListener = (CardListener) updateServiceSessionCallback.cardListenerRef.get();
                if (cardListener != null) {
                    cardListener.onFail(-1, new Bundle());
                    return;
                }
                return;
            }
        }
        Log.e(TAG, "showUpdateServiceSheet - Context is destroyed");
    }

    protected void setServicePackage(String str) {
        this.mCardManagerStub.setServicePackage(str);
    }
}
