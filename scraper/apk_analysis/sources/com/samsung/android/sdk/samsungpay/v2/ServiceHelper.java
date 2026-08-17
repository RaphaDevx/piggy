package com.samsung.android.sdk.samsungpay.v2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.ISPartnerAppDeathDetector;

/* loaded from: classes2.dex */
public class ServiceHelper {
    private static final String TAG = "SPAYSDK:ServiceHelper";
    protected BindRetry bindRetry;
    private Context context;
    private ServiceCallback serviceCallback;
    private String servicePackage = "";
    private IBinder serviceBinder = null;
    private String serviceAction = "";
    private long bindTimeOut = 0;
    private ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.samsung.android.sdk.samsungpay.v2.ServiceHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (!ServiceHelper.this.bindRetry.isOverCounter()) {
                Log.i(ServiceHelper.TAG, "service connected : " + iBinder.toString());
                ServiceHelper.this.setServiceBinder(iBinder);
                ServiceHelper.this.serviceCallback.onReceived(iBinder);
            } else {
                Log.e(ServiceHelper.TAG, "service timeouted");
            }
            ServiceHelper.this.bindRetry.releaseBindTimerTask();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(ServiceHelper.TAG, "service disconnected");
            ServiceHelper.this.releaseServiceBinder();
            ServiceHelper.this.bindRetry.releaseBindTimerTask();
            ServiceHelper.this.serviceCallback.onDisconnected();
        }
    };

    public enum BindingResult {
        EXIST_BINDER,
        BINDING_SERVICE,
        BINDING_SERVICE_ALREADY,
        CANNOT_BIND,
        DISCONNECTED_SERVICE
    }

    public interface ServiceCallback {
        void onDisconnected();

        void onFailed(BindingResult bindingResult);

        void onReceived(IBinder iBinder);
    }

    protected ServiceHelper(Context context) {
        init(context);
    }

    protected void init(Context context) {
        this.context = context.getApplicationContext();
        this.servicePackage = InternalConst.SERVICE_PACKAGE;
    }

    public IBinder getServiceBinder() {
        return this.serviceBinder;
    }

    public void setServiceBinder(IBinder iBinder) {
        this.serviceBinder = iBinder;
    }

    protected void setServicePackage(String str) {
        this.servicePackage = str;
    }

    protected void createService(ServiceCallback serviceCallback, String str) {
        this.serviceCallback = serviceCallback;
        this.serviceAction = str;
        this.bindRetry = new BindRetry(this.bindTimeOut) { // from class: com.samsung.android.sdk.samsungpay.v2.ServiceHelper.2
            @Override // com.samsung.android.sdk.samsungpay.v2.BindRetry
            void runTimer() {
                if (isOverCounter()) {
                    Log.i(ServiceHelper.TAG, "Samsung Pay bind timeout and max retry is reached. Quit");
                    ServiceHelper.this.serviceCallback.onFailed(BindingResult.CANNOT_BIND);
                } else {
                    Log.i(ServiceHelper.TAG, "Samsung Pay bind timeout. Binder not available. trying again.");
                    BindingResult bindService = ServiceHelper.this.bindService();
                    ServiceHelper serviceHelper = ServiceHelper.this;
                    serviceHelper.callbackBindingResult(serviceHelper.serviceCallback, bindService);
                }
            }
        };
        callbackBindingResult(this.serviceCallback, bindService());
    }

    /* renamed from: com.samsung.android.sdk.samsungpay.v2.ServiceHelper$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$samsungpay$v2$ServiceHelper$BindingResult;

        static {
            int[] iArr = new int[BindingResult.values().length];
            $SwitchMap$com$samsung$android$sdk$samsungpay$v2$ServiceHelper$BindingResult = iArr;
            try {
                iArr[BindingResult.BINDING_SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$ServiceHelper$BindingResult[BindingResult.BINDING_SERVICE_ALREADY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$ServiceHelper$BindingResult[BindingResult.CANNOT_BIND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$ServiceHelper$BindingResult[BindingResult.EXIST_BINDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    protected void callbackBindingResult(ServiceCallback serviceCallback, BindingResult bindingResult) {
        int i = AnonymousClass4.$SwitchMap$com$samsung$android$sdk$samsungpay$v2$ServiceHelper$BindingResult[bindingResult.ordinal()];
        if (i == 1) {
            Log.d(TAG, "request to bind");
            return;
        }
        if (i == 2) {
            Log.i(TAG, "request to bind already");
            return;
        }
        if (i == 3) {
            Log.e(TAG, "pay app service is not available");
            serviceCallback.onFailed(bindingResult);
        } else if (i == 4) {
            Log.i(TAG, "exist binder.");
            serviceCallback.onReceived(getServiceBinder());
        } else {
            Log.e(TAG, "must not come into here.");
            serviceCallback.onFailed(bindingResult);
        }
    }

    protected synchronized boolean isValidServiceBinder() {
        IBinder serviceBinder = getServiceBinder();
        if (serviceBinder != null && serviceBinder.isBinderAlive()) {
            return true;
        }
        releaseServiceBinder();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized BindingResult bindService() {
        if (isValidServiceBinder()) {
            this.bindRetry.releaseBindTimerTask();
            return BindingResult.EXIST_BINDER;
        }
        if (this.bindRetry.isScheduled()) {
            Log.d(TAG, "bindService already attempted, waiting.");
            return BindingResult.BINDING_SERVICE_ALREADY;
        }
        if (isValidAction()) {
            Log.w(TAG, "action is null, need to call createService before it.");
            return BindingResult.CANNOT_BIND;
        }
        Intent intent = new Intent(this.serviceAction);
        intent.setPackage(this.servicePackage);
        ISPartnerAppDeathDetector.Stub stub = new ISPartnerAppDeathDetector.Stub() { // from class: com.samsung.android.sdk.samsungpay.v2.ServiceHelper.3
        };
        Bundle bundle = new Bundle();
        bundle.putBinder("deathDetectorBinder", stub);
        intent.putExtras(bundle);
        if (this.bindRetry.scheduleBindTimer()) {
            if (this.context.bindService(intent, this.serviceConnection, 65)) {
                Log.d(TAG, "Service bind attempted and waiting for onConnect, from " + this.context);
                return BindingResult.BINDING_SERVICE;
            }
            this.bindRetry.releaseBindTimerTask();
        }
        return BindingResult.CANNOT_BIND;
    }

    protected synchronized void unbindService() {
        if (isValidServiceBinder()) {
            Log.d(TAG, "! unbindService from " + this.context);
            try {
                this.context.unbindService(this.serviceConnection);
            } catch (Exception e) {
                Log.e(TAG, "unbindService - e : " + e.getMessage());
            }
        }
        releaseServiceBinder();
    }

    protected void releaseServiceBinder() {
        this.serviceBinder = null;
    }

    private boolean isValidAction() {
        return "".equals(this.serviceAction);
    }

    protected void setBindTimeOut(long j) {
        Log.w(TAG, "setBindTimeOut is for instrument testcases");
        this.bindTimeOut = j;
    }

    public Context getContext() {
        return this.context;
    }
}
