package com.samsung.android.sdk.samsungpay.v2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.ServiceHelper;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class StubBase<T extends IInterface> extends ServiceHelper implements ServiceStub {
    private static final String TAG = "SPAYSDK:StubBase";
    private static HashMap<String, WeakReference<StubBase>> mStubMap = new HashMap<>(5);
    private T mBoundStub;
    private Queue<StubConnector> mCallerStubConnectors;
    private HandlerThread mHandlerThread;
    private Handler mRequestHandler;
    private final Queue<PartnerRequest> mRequestList;
    private String mServiceAction;
    private ServiceHelper.ServiceCallback mServiceCallback;
    private StubCaster<T> mStubCaster;
    private final Object[] mSyncObj;
    private Semaphore mWaitForNewRequest;
    private Semaphore mWaitUntilRequestDone;
    private Handler.Callback onRequestHandler;

    public interface StubCaster<K extends IInterface> {
        K castStub(IBinder iBinder);
    }

    /* renamed from: lambda$new$0$com-samsung-android-sdk-samsungpay-v2-StubBase, reason: not valid java name */
    /* synthetic */ boolean m3727lambda$new$0$comsamsungandroidsdksamsungpayv2StubBase(Message message) {
        PartnerRequest peek;
        boolean isEmpty;
        synchronized (this.mSyncObj) {
            peek = this.mRequestList.peek();
        }
        if (peek == null) {
            Log.d(TAG, "handleMessage: No request in the list");
            return false;
        }
        this.mWaitUntilRequestDone.release(100);
        this.mWaitUntilRequestDone.drainPermits();
        peek.execute(this);
        try {
            this.mWaitUntilRequestDone.acquire();
            synchronized (this.mSyncObj) {
                if (!this.mRequestList.isEmpty() && this.mRequestList.peek() == peek) {
                    this.mRequestList.remove(peek);
                }
                isEmpty = this.mRequestList.isEmpty();
                if (isEmpty) {
                    this.mWaitForNewRequest.release(100);
                    this.mWaitForNewRequest.drainPermits();
                }
            }
            if (isEmpty) {
                this.mWaitForNewRequest.tryAcquire(5L, TimeUnit.SECONDS);
            }
            synchronized (this.mSyncObj) {
                if (this.mRequestList.isEmpty()) {
                    Log.d(TAG, "No pending request. Disconnect stub");
                    disConnectStub();
                } else {
                    this.mRequestHandler.sendEmptyMessage(0);
                }
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "onRequestHandler - " + e.toString());
        }
        return false;
    }

    private StubBase(Context context, String str, StubCaster<T> stubCaster) {
        super(context);
        this.mBoundStub = null;
        this.mCallerStubConnectors = new LinkedList();
        this.mRequestList = new LinkedList();
        this.mWaitUntilRequestDone = new Semaphore(0);
        this.mWaitForNewRequest = new Semaphore(0);
        this.mSyncObj = new Object[0];
        this.mServiceCallback = new ServiceHelper.ServiceCallback() { // from class: com.samsung.android.sdk.samsungpay.v2.StubBase.1
            @Override // com.samsung.android.sdk.samsungpay.v2.ServiceHelper.ServiceCallback
            public void onReceived(IBinder iBinder) {
                StubBase.this.createHandlerThread();
                StubBase.this.createStub(iBinder);
                synchronized (StubBase.this.mSyncObj) {
                    while (!StubBase.this.mCallerStubConnectors.isEmpty()) {
                        ((StubConnector) StubBase.this.mCallerStubConnectors.poll()).onReceivedStub(StubBase.this.mBoundStub);
                    }
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ServiceHelper.ServiceCallback
            public void onFailed(ServiceHelper.BindingResult bindingResult) {
                synchronized (StubBase.this.mSyncObj) {
                    while (!StubBase.this.mCallerStubConnectors.isEmpty()) {
                        ((StubConnector) StubBase.this.mCallerStubConnectors.poll()).onFailed(bindingResult);
                    }
                }
                StubBase.this.destroyHandlerThread();
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ServiceHelper.ServiceCallback
            public void onDisconnected() {
                Log.e(StubBase.TAG, "Service is disconnected");
                StubBase.this.destroyHandlerThread();
            }
        };
        this.onRequestHandler = new Handler.Callback() { // from class: com.samsung.android.sdk.samsungpay.v2.StubBase$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return StubBase.this.m3727lambda$new$0$comsamsungandroidsdksamsungpayv2StubBase(message);
            }
        };
        this.mServiceAction = str;
        this.mStubCaster = stubCaster;
    }

    public static class Creator<T extends IInterface> {
        public StubBase<T> createStub(Context context, String str, StubCaster<T> stubCaster) {
            WeakReference weakReference = (WeakReference) StubBase.mStubMap.get(str);
            if (weakReference == null || weakReference.get() == null) {
                StubBase<T> stubBase = new StubBase<>(context, str, stubCaster);
                StubBase.mStubMap.put(str, new WeakReference(stubBase));
                return stubBase;
            }
            Log.i(StubBase.TAG, "Found stub reference of " + str);
            StubBase<T> stubBase2 = (StubBase) weakReference.get();
            if (!TextUtils.equals(str, InternalConst.SERVICE_ACTION_CARD)) {
                return stubBase2;
            }
            synchronized (((StubBase) stubBase2).mSyncObj) {
                if (((StubBase) stubBase2).mRequestList.size() > 0) {
                    Log.i(StubBase.TAG, "Clear pending requests of " + str);
                    stubBase2.cancelledPendingTasks(false);
                }
            }
            return stubBase2;
        }
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.ServiceStub
    public void connectStub(StubConnector stubConnector) {
        synchronized (this.mSyncObj) {
            if (isValidStub()) {
                stubConnector.onReceivedStub(this.mBoundStub);
                return;
            }
            if (isValidServiceBinder()) {
                T createStub = createStub(getServiceBinder());
                this.mBoundStub = createStub;
                if (createStub != null) {
                    stubConnector.onReceivedStub(createStub);
                    return;
                }
                Log.e(TAG, "connectStub - bound stub is NULL. Retry to connect service");
            }
            this.mCallerStubConnectors.add(stubConnector);
            createService(this.mServiceCallback, this.mServiceAction);
        }
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.ServiceStub
    public void disConnectStub() {
        Log.d(TAG, "disConnectStub - service: " + this.mServiceAction);
        destroyHandlerThread();
        releaseStub();
        unbindService();
    }

    public boolean isValidStub() {
        boolean z;
        synchronized (this.mSyncObj) {
            z = this.mBoundStub != null;
        }
        return z;
    }

    private void releaseStub() {
        synchronized (this.mSyncObj) {
            this.mBoundStub = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public T createStub(IBinder iBinder) {
        synchronized (this.mSyncObj) {
            if (iBinder == null) {
                return null;
            }
            T castStub = this.mStubCaster.castStub(iBinder);
            this.mBoundStub = castStub;
            return castStub;
        }
    }

    public T getStub() {
        return this.mBoundStub;
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.ServiceHelper
    public void setServicePackage(String str) {
        super.setServicePackage(str);
    }

    public void postRequest(final PartnerRequest partnerRequest, SpaySdk.SdkApiLevel sdkApiLevel) {
        synchronized (this.mSyncObj) {
            Log.d(TAG, "postRequest - " + partnerRequest.name);
            if (verifyRequest(partnerRequest, sdkApiLevel)) {
                if (partnerRequest.needStub) {
                    connectStub(new StubConnector() { // from class: com.samsung.android.sdk.samsungpay.v2.StubBase.2
                        @Override // com.samsung.android.sdk.samsungpay.v2.StubConnector
                        public void onReceivedStub(Object obj) {
                            StubBase.this.addRequestToList(partnerRequest);
                        }

                        @Override // com.samsung.android.sdk.samsungpay.v2.StubConnector
                        public void onFailed(ServiceHelper.BindingResult bindingResult) {
                            partnerRequest.catchError(ErrorType.BINDING_FAIL, SpaySdk.ERROR_INITIATION_FAIL, new Bundle());
                        }
                    });
                } else {
                    addRequestToList(partnerRequest);
                }
                this.mWaitForNewRequest.release();
            }
        }
    }

    public void executeRequest(PartnerRequest partnerRequest, SpaySdk.SdkApiLevel sdkApiLevel) {
        if (verifyRequest(partnerRequest, sdkApiLevel)) {
            partnerRequest.execute(this);
        }
    }

    public void nextRequest() {
        Log.d(TAG, "nextRequest is called");
        this.mWaitUntilRequestDone.release();
    }

    private boolean verifyRequest(PartnerRequest partnerRequest, SpaySdk.SdkApiLevel sdkApiLevel) {
        if (partnerRequest.checkDuplication && isRequestInQueue(partnerRequest.operation)) {
            partnerRequest.catchError(ErrorType.DUPLICATED_REQUEST, SpaySdk.ERROR_DUPLICATED_SDK_API_CALLED, null);
            return false;
        }
        SpayValidity spayValidity = new SpayValidity(getContext(), partnerRequest.isWatch);
        int spayValidity2 = spayValidity.getSpayValidity(partnerRequest.owner.partnerInfo, sdkApiLevel, partnerRequest);
        Bundle bundle = new Bundle();
        bundle.putInt(SpaySdk.EXTRA_ERROR_REASON, spayValidity2);
        int errorCodeToReadyStatus = spayValidity.errorCodeToReadyStatus(spayValidity2);
        boolean z = errorCodeToReadyStatus == -99;
        boolean z2 = partnerRequest.needStub && 2 != errorCodeToReadyStatus;
        boolean z3 = !partnerRequest.needStub && errorCodeToReadyStatus == 0;
        if (z) {
            Log.w(TAG, "postRequest - partnerInfoInvalid");
            partnerRequest.catchError(ErrorType.PARTNER_INFO_INVALID, errorCodeToReadyStatus, bundle);
            return false;
        }
        if (z2 || z3) {
            Log.w(TAG, "postRequest - init error " + errorCodeToReadyStatus + " -  reason: " + spayValidity2);
            partnerRequest.catchError(ErrorType.SPAY_VALIDITY_FAIL, errorCodeToReadyStatus, bundle);
            return false;
        }
        partnerRequest.apiLevel = spayValidity.getSdkApiLevelInManifest();
        Bundle data = partnerRequest.owner.partnerInfo.getData();
        String string = data.getString(SpaySdk.PARTNER_SERVICE_TYPE);
        if (SpaySdk.ServiceType.WEB_PAYMENT.toString().equals(string) || SpaySdk.ServiceType.MOBILEWEB_PAYMENT.toString().equals(string)) {
            SpaySdk.SdkApiLevel findSdkApiLevel = SpaySdk.SdkApiLevel.findSdkApiLevel(data.getString(SpaySdk.WEB_CHECKOUT_API_LEVEL, ""));
            if (partnerRequest.apiLevel.compareTo(findSdkApiLevel) < 0) {
                Log.d(TAG, "web checkout api level is higher than manifest.");
                partnerRequest.apiLevel = findSdkApiLevel;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRequestToList(PartnerRequest partnerRequest) {
        synchronized (this.mSyncObj) {
            Log.d(TAG, "addRequestToList - request: " + partnerRequest.name);
            this.mRequestList.add(partnerRequest);
            if (this.mRequestHandler == null) {
                createHandlerThread();
            }
            this.mRequestHandler.sendEmptyMessage(0);
        }
    }

    private boolean isRequestInQueue(int i) {
        synchronized (this.mSyncObj) {
            Iterator<PartnerRequest> it = this.mRequestList.iterator();
            while (it.hasNext()) {
                if (i == it.next().operation) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createHandlerThread() {
        synchronized (this.mSyncObj) {
            if (this.mHandlerThread == null) {
                HandlerThread handlerThread = new HandlerThread(this.mServiceAction);
                this.mHandlerThread = handlerThread;
                handlerThread.start();
                this.mRequestHandler = new Handler(this.mHandlerThread.getLooper(), this.onRequestHandler);
                Log.i(TAG, "createStub HandlerThread - " + this.mHandlerThread.getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyHandlerThread() {
        synchronized (this.mSyncObj) {
            if (this.mHandlerThread != null) {
                Log.d(TAG, "destroy HandlerThread - " + this.mHandlerThread.getName());
                this.mRequestHandler.removeMessages(0);
                cancelledPendingTasks(true);
                try {
                    this.mHandlerThread.quit();
                } catch (Exception e) {
                    Log.e(TAG, "destroyHandlerThread - " + e.toString());
                }
                this.mRequestHandler = null;
                this.mHandlerThread = null;
            }
        }
    }

    public void cancelledPendingTasks(boolean z) {
        synchronized (this.mSyncObj) {
            if (this.mRequestList.size() > 0) {
                Log.d(TAG, "Dismiss " + this.mRequestList.size() + " pending request");
                while (!this.mRequestList.isEmpty()) {
                    PartnerRequest poll = this.mRequestList.poll();
                    poll.isCancelled = true;
                    if (z) {
                        poll.catchError(ErrorType.SERVICE_DISCONNECTED, -1, null);
                    }
                }
                this.mRequestList.clear();
            }
            this.mWaitUntilRequestDone.release();
            this.mWaitForNewRequest.release();
        }
    }
}
