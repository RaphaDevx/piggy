package com.samsung.android.sdk.samsungpay.v2.payment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.samsung.android.sdk.samsungpay.v2.ErrorType;
import com.samsung.android.sdk.samsungpay.v2.InternalConst;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.StubBase;
import com.samsung.android.sdk.samsungpay.v2.payment.ISEncryptionKeyListener;
import com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager;
import com.samsung.android.sdk.samsungpay.v2.payment.ISMstPaymentListener;
import com.samsung.android.sdk.samsungpay.v2.payment.ISTransactionResultListener;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class MstManager extends SpaySdk {
    public static final int ERROR_SPAY_MST_NOT_PREPARED = -602;
    public static final int ERROR_SPAY_MST_OVERSEAS_NOT_SUPPORTED = -601;
    public static final int ERROR_SPAY_USIM_CHANGED = -603;
    private static final int OPT_GET_TOKEN_ENCRYPTION_KEY = 1;
    private static final int OPT_SEND_TRANSACTION = 4;
    private static final int OPT_START_MST = 2;
    private static final int OPT_STOP_MST = 3;
    private static final String TAG = "SPAYSDK:MstManager";
    private SoftReference<Activity> mActivityReference;
    protected Handler mHandlerForInternalListener;
    private StubBase<ISMstManager> mMstManagerStub;
    private SpayPublicKey publicKey;
    private SpayResponseInfo spayResponseInfo;

    public MstManager(Context context, PartnerInfo partnerInfo) {
        super(context, partnerInfo);
        this.mHandlerForInternalListener = new Handler(Looper.getMainLooper()) { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (message.obj instanceof EncryptionKeyListener) {
                        ((EncryptionKeyListener) message.obj).onSuccess(MstManager.this.publicKey);
                        return;
                    }
                    if (message.obj instanceof MstPaymentListener) {
                        ((MstPaymentListener) message.obj).onSuccess(MstManager.this.spayResponseInfo);
                        return;
                    } else if (message.obj instanceof TransactionResultListener) {
                        ((TransactionResultListener) message.obj).onSuccess(message.getData());
                        return;
                    } else {
                        Log.e(MstManager.TAG, "[onSuccess] Wrong listener was called");
                        return;
                    }
                }
                if (i != 1) {
                    if (i != 4) {
                        Log.e(MstManager.TAG, "sdk can not catch listener from SPay.");
                    }
                } else {
                    if (message.obj instanceof EncryptionKeyListener) {
                        ((EncryptionKeyListener) message.obj).onFail(message.arg1, message.getData());
                        return;
                    }
                    if (message.obj instanceof MstPaymentListener) {
                        ((MstPaymentListener) message.obj).onFail(message.arg1, message.getData());
                    } else if (message.obj instanceof TransactionResultListener) {
                        ((TransactionResultListener) message.obj).onFail(message.arg1, message.getData());
                    } else {
                        Log.e(MstManager.TAG, "[onFail] Wrong listener was called");
                    }
                }
            }
        };
        Log.d(TAG, "MstManager()");
        this.mMstManagerStub = new StubBase.Creator().createStub(context, InternalConst.SERVICE_ACTION_MST, new StubBase.StubCaster() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.StubBase.StubCaster
            public final IInterface castStub(IBinder iBinder) {
                return ISMstManager.Stub.asInterface(iBinder);
            }
        });
    }

    public void getTokenEncryptionKey(final EncryptionKeyListener encryptionKeyListener) {
        Log.d(TAG, "getTokenEncryptionKey() : SDK API Level = 2.1");
        assertNotNull(encryptionKeyListener);
        final EncryptionKeyListenerInternal encryptionKeyListenerInternal = new EncryptionKeyListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 1, encryptionKeyListener).setName("getTokenEncryptionKey").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda8
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                MstManager.this.m3735xb1b95344(encryptionKeyListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda9
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                EncryptionKeyListener.this.onFail(i, bundle);
            }
        }).create();
        encryptionKeyListenerInternal.set(create);
        this.mMstManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_1);
    }

    /* renamed from: lambda$getTokenEncryptionKey$0$com-samsung-android-sdk-samsungpay-v2-payment-MstManager, reason: not valid java name */
    /* synthetic */ void m3735xb1b95344(EncryptionKeyListenerInternal encryptionKeyListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISMstManager) iInterface).getTokenEncryptionKey(getPartnerInfo(), encryptionKeyListenerInternal.getEncryptionKeyListener());
    }

    public void startMST(Activity activity, final MstPaymentInfo mstPaymentInfo, final MstPaymentListener mstPaymentListener) {
        Log.d(TAG, "startMST() : SDK API Level = 2.1");
        assertNotNull(mstPaymentListener);
        assertNotNull(mstPaymentInfo);
        this.mActivityReference = new SoftReference<>(activity);
        final MstPaymentListenerInternal mstPaymentListenerInternal = new MstPaymentListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 2, mstPaymentListener).setName("startMST").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda3
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                MstManager.this.m3737x58a59c95(mstPaymentInfo, mstPaymentListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda4
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                MstPaymentListener.this.onFail(i, bundle);
            }
        }).create();
        mstPaymentListenerInternal.set(create);
        this.mMstManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_1);
    }

    /* renamed from: lambda$startMST$2$com-samsung-android-sdk-samsungpay-v2-payment-MstManager, reason: not valid java name */
    /* synthetic */ void m3737x58a59c95(MstPaymentInfo mstPaymentInfo, MstPaymentListenerInternal mstPaymentListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISMstManager) iInterface).startMST(getPartnerInfo(), mstPaymentInfo, mstPaymentListenerInternal.getMstPaymentListener());
        keepScreenOn(true);
    }

    @Deprecated
    public void stopMST() {
        Log.d(TAG, "stopMST() : SDK API Level = 2.1");
        stopMST(null);
    }

    public void stopMST(final MstPaymentListener mstPaymentListener) {
        Log.d(TAG, "stopMST() : SDK API Level = 2.3");
        PartnerRequest create = new PartnerRequest.Builder(this, 3, mstPaymentListener).setName("stopMST").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                MstManager.this.m3738x6912facd(mstPaymentListener, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                MstManager.lambda$stopMST$5(MstPaymentListener.this, errorType, i, bundle);
            }
        }).create();
        this.mMstManagerStub.cancelledPendingTasks(false);
        this.mMstManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_3);
    }

    /* renamed from: lambda$stopMST$4$com-samsung-android-sdk-samsungpay-v2-payment-MstManager, reason: not valid java name */
    /* synthetic */ void m3738x6912facd(MstPaymentListener mstPaymentListener, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISMstManager) iInterface).stopMST(getPartnerInfo(), createStopMstListener(mstPaymentListener));
        keepScreenOn(false);
        this.mMstManagerStub.nextRequest();
    }

    static /* synthetic */ void lambda$stopMST$5(MstPaymentListener mstPaymentListener, ErrorType errorType, int i, Bundle bundle) {
        if (mstPaymentListener != null) {
            mstPaymentListener.onFail(i, bundle);
        }
    }

    public void sendTransactionResultInfo(final TransactionResultInfo transactionResultInfo, final TransactionResultListener transactionResultListener) {
        Log.d(TAG, "sendTransactionResultInfo() : SDK API Level = 2.1");
        assertNotNull(transactionResultListener);
        assertNotNull(transactionResultInfo);
        final MstTransactionListenerInternal mstTransactionListenerInternal = new MstTransactionListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 4, transactionResultListener).setName("sendTransactionResultInfo").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda5
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                MstManager.this.m3736x79e09d96(transactionResultInfo, mstTransactionListenerInternal, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda6
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                TransactionResultListener.this.onFail(i, bundle);
            }
        }).create();
        mstTransactionListenerInternal.set(create);
        this.mMstManagerStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_1);
    }

    /* renamed from: lambda$sendTransactionResultInfo$6$com-samsung-android-sdk-samsungpay-v2-payment-MstManager, reason: not valid java name */
    /* synthetic */ void m3736x79e09d96(TransactionResultInfo transactionResultInfo, MstTransactionListenerInternal mstTransactionListenerInternal, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        ((ISMstManager) iInterface).sendTransactionResultInfo(getPartnerInfo(), transactionResultInfo, mstTransactionListenerInternal.getTransactionResultListener());
    }

    protected void sendMsgForEncryptionKeyListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "sendMsgForEncryptionKeyListener -  request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    protected void sendMsgForMstPaymentListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "sendMsgForMstPaymentListener -  request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    protected void sendMsgForMstTransactionListener(WeakReference<PartnerRequest> weakReference, int i, int i2, Bundle bundle) {
        PartnerRequest partnerRequest;
        if (weakReference == null || (partnerRequest = weakReference.get()) == null) {
            Log.d(TAG, "sendMsgForMstTransactionListener -  request is NULL");
            return;
        }
        Message obtain = Message.obtain(this.mHandlerForInternalListener);
        obtain.obj = partnerRequest.callbackObj;
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.setData(bundle);
        this.mHandlerForInternalListener.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keepScreenOn(final boolean z) {
        final Activity activity;
        Log.d(TAG, "keepScreenOn - enabled = " + z);
        SoftReference<Activity> softReference = this.mActivityReference;
        if (softReference != null && (activity = softReference.get()) != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    MstManager.lambda$keepScreenOn$8(activity, z);
                }
            });
        } else {
            Log.e(TAG, "keepScreenOn - Activity is NULL");
        }
    }

    static /* synthetic */ void lambda$keepScreenOn$8(Activity activity, boolean z) {
        if (!activity.isDestroyed() && !activity.isFinishing()) {
            Window window = activity.getWindow();
            if (z) {
                window.addFlags(524288);
                window.addFlags(128);
            } else {
                window.clearFlags(524288);
                window.clearFlags(128);
            }
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Class.forName("android.view.WindowManager$LayoutParams").getField("coverMode").setInt(attributes, z ? 1 : 0);
                window.setAttributes(attributes);
                return;
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "keepScreenOn - Activity is finishing or destroyed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    class EncryptionKeyListenerInternal {
        private static final String TAG = "EncryptionKeyListener";
        private WeakReference<PartnerRequest> pReqRef;
        private SpayEncryptionKeyListener spayEncryptionKeyListener;

        private EncryptionKeyListenerInternal() {
            this.pReqRef = null;
            this.spayEncryptionKeyListener = new SpayEncryptionKeyListener();
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayEncryptionKeyListener extends ISEncryptionKeyListener.Stub {
            private SpayEncryptionKeyListener() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISEncryptionKeyListener
            public void onSuccess(SpayPublicKey spayPublicKey) throws RemoteException {
                Log.d(EncryptionKeyListenerInternal.TAG, "onSuccess: public key : " + spayPublicKey);
                MstManager.this.mMstManagerStub.nextRequest();
                MstManager.this.publicKey = spayPublicKey;
                MstManager.this.sendMsgForEncryptionKeyListener(EncryptionKeyListenerInternal.this.pReqRef, 0, 0, new Bundle());
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISEncryptionKeyListener
            public void onFail(int i, Bundle bundle) throws RemoteException {
                Log.e(EncryptionKeyListenerInternal.TAG, "onFail: error: " + i);
                MstManager.this.sendMsgForEncryptionKeyListener(EncryptionKeyListenerInternal.this.pReqRef, 1, i, bundle);
                MstManager.this.mMstManagerStub.nextRequest();
            }
        }

        public ISEncryptionKeyListener getEncryptionKeyListener() {
            return this.spayEncryptionKeyListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class MstPaymentListenerInternal {
        private static final String TAG = "MstPaymentListener";
        private WeakReference<PartnerRequest> pReqRef;
        private SpayMSTPaymentListenerInternal spayMstPaymentListener;

        private MstPaymentListenerInternal() {
            this.pReqRef = null;
            this.spayMstPaymentListener = new SpayMSTPaymentListenerInternal();
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayMSTPaymentListenerInternal extends ISMstPaymentListener.Stub {
            private SpayMSTPaymentListenerInternal() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstPaymentListener
            public void onSuccess(SpayResponseInfo spayResponseInfo) throws RemoteException {
                Log.d(MstPaymentListenerInternal.TAG, "onSuccess: Status : " + spayResponseInfo.getPayMstStatus());
                MstManager.this.spayResponseInfo = spayResponseInfo;
                int payMstStatus = spayResponseInfo.getPayMstStatus();
                if (payMstStatus == 27 || payMstStatus == 21 || payMstStatus == 0) {
                    MstManager.this.mMstManagerStub.nextRequest();
                    MstManager.this.keepScreenOn(false);
                }
                if (payMstStatus == 23 || payMstStatus == 0) {
                    MstManager.this.sendMsgForMstPaymentListener(MstPaymentListenerInternal.this.pReqRef, 0, 0, new Bundle());
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstPaymentListener
            public void onFail(int i, Bundle bundle) throws RemoteException {
                Log.e(MstPaymentListenerInternal.TAG, "onFail: error: " + i);
                MstManager.this.keepScreenOn(false);
                MstManager.this.sendMsgForMstPaymentListener(MstPaymentListenerInternal.this.pReqRef, 1, i, bundle);
                MstManager.this.mMstManagerStub.nextRequest();
            }
        }

        public ISMstPaymentListener getMstPaymentListener() {
            return this.spayMstPaymentListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class MstTransactionListenerInternal {
        private static final String TAG = "TransactionResult";
        private WeakReference<PartnerRequest> pReqRef;
        private SpayMstTransactionListenerInternal spayMstTransactionListener;

        private MstTransactionListenerInternal() {
            this.pReqRef = null;
            this.spayMstTransactionListener = new SpayMstTransactionListenerInternal();
        }

        public void set(PartnerRequest partnerRequest) {
            this.pReqRef = new WeakReference<>(partnerRequest);
        }

        private class SpayMstTransactionListenerInternal extends ISTransactionResultListener.Stub {
            private SpayMstTransactionListenerInternal() {
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISTransactionResultListener
            public void onSuccess(Bundle bundle) throws RemoteException {
                Log.d(MstTransactionListenerInternal.TAG, "onSuccess: data : " + bundle);
                MstManager.this.sendMsgForMstTransactionListener(MstTransactionListenerInternal.this.pReqRef, 0, 0, bundle);
                MstManager.this.mMstManagerStub.nextRequest();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISTransactionResultListener
            public void onFail(int i, Bundle bundle) throws RemoteException {
                Log.e(MstTransactionListenerInternal.TAG, "onFail: error: " + i);
                MstManager.this.sendMsgForMstTransactionListener(MstTransactionListenerInternal.this.pReqRef, 1, i, bundle);
                MstManager.this.mMstManagerStub.nextRequest();
            }
        }

        public ISTransactionResultListener getTransactionResultListener() {
            return this.spayMstTransactionListener;
        }
    }

    private ISMstPaymentListener createStopMstListener(final MstPaymentListener mstPaymentListener) {
        return new ISMstPaymentListener.Stub() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstManager.2
            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstPaymentListener
            public void onSuccess(SpayResponseInfo spayResponseInfo) {
                MstPaymentListener mstPaymentListener2 = mstPaymentListener;
                if (mstPaymentListener2 != null) {
                    mstPaymentListener2.onSuccess(null);
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstPaymentListener
            public void onFail(int i, Bundle bundle) {
                MstPaymentListener mstPaymentListener2 = mstPaymentListener;
                if (mstPaymentListener2 != null) {
                    mstPaymentListener2.onFail(i, bundle);
                }
            }
        };
    }
}
