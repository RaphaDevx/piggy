package com.samsung.android.sdk.samsungpay.v2;

import android.content.ActivityNotFoundException;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;

/* loaded from: classes2.dex */
public class PartnerRequest {
    private static Handler sMainThreadHandler = new Handler(Looper.getMainLooper());
    public Object callbackObj;
    public Object[] checkApiLevelParams;
    private ErrorCatcher mErrorCatcher;
    private RequestExecutor mRequestExecutor;
    public Object obj1;
    public Object obj2;
    public Object obj3;
    public int operation;
    public SpaySdk owner;
    private final String TAG = "SPAYSDK:PartnerRequest";
    public boolean needStub = true;
    public boolean checkDuplication = false;
    public String name = "unknown";
    public boolean isWatch = false;
    public SpaySdk.SdkApiLevel apiLevel = SpaySdk.SdkApiLevel.LEVEL_UNKNOWN;
    public boolean isCancelled = false;
    private PartnerRequestState mState = PartnerRequestState.NONE;

    public interface ErrorCatcher {
        void onError(ErrorType errorType, int i, Bundle bundle);
    }

    public enum PartnerRequestState {
        NONE,
        PROCESSING,
        SESSION
    }

    public interface RequestExecutor {
        void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException;
    }

    public PartnerRequest(int i, Object obj) {
        this.operation = i;
        this.callbackObj = obj;
    }

    public PartnerRequest(int i, Object obj, Object obj2) {
        this.operation = i;
        this.obj1 = obj;
        this.callbackObj = obj2;
    }

    public PartnerRequest(int i, Object obj, Object obj2, Object obj3) {
        this.operation = i;
        this.obj1 = obj;
        this.obj2 = obj2;
        this.callbackObj = obj3;
    }

    public PartnerRequest(int i, Object obj, Object obj2, Object obj3, Object obj4) {
        this.operation = i;
        this.obj1 = obj;
        this.obj2 = obj2;
        this.obj3 = obj3;
        this.callbackObj = obj4;
    }

    public void setState(PartnerRequestState partnerRequestState) {
        this.mState = partnerRequestState;
    }

    public PartnerRequestState getState() {
        return this.mState;
    }

    public boolean isPartnerRequestProcessing() {
        return this.mState == PartnerRequestState.PROCESSING;
    }

    public void execute(final StubBase stubBase) {
        if (this.mRequestExecutor != null) {
            Runnable runnable = new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.PartnerRequest$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PartnerRequest.this.m3723x2fcde0a9(stubBase);
                }
            };
            if (Looper.myLooper() == Looper.getMainLooper()) {
                runnable.run();
                return;
            } else {
                sMainThreadHandler.post(runnable);
                return;
            }
        }
        Log.e("SPAYSDK:PartnerRequest", "No request handler");
    }

    /* renamed from: lambda$execute$0$com-samsung-android-sdk-samsungpay-v2-PartnerRequest, reason: not valid java name */
    /* synthetic */ void m3723x2fcde0a9(StubBase stubBase) {
        try {
            Log.d("SPAYSDK:PartnerRequest", "Processing request:  " + this.name);
            IInterface stub = stubBase.getStub();
            if (stub == null && this.needStub) {
                Log.e("SPAYSDK:PartnerRequest", "Can't execute request because Stub is null");
                throw new RemoteException();
            }
            setDefinedApiLevelToPartnerInfo();
            this.mRequestExecutor.handleRequest(stub, this);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SPAYSDK:PartnerRequest", "NameNotFoundException while executing request: " + e.toString());
            catchError(ErrorType.NAME_NOT_FOUND_EXCEPTION, SpaySdk.ERROR_INITIATION_FAIL, new Bundle());
            stubBase.nextRequest();
        } catch (RemoteException e2) {
            Log.e("SPAYSDK:PartnerRequest", "RemoteException while executing request: " + e2.toString());
            catchError(ErrorType.REMOTE_EXCEPTION, SpaySdk.ERROR_INITIATION_FAIL, new Bundle());
            stubBase.nextRequest();
        } catch (Exception e3) {
            Log.e("SPAYSDK:PartnerRequest", "Unknown exception while executing request: ");
            e3.printStackTrace();
            catchError(ErrorType.REMOTE_EXCEPTION, SpaySdk.ERROR_INITIATION_FAIL, new Bundle());
            stubBase.nextRequest();
        }
    }

    public void catchError(final ErrorType errorType, final int i, final Bundle bundle) {
        printErrorLog(errorType, i);
        if (this.mErrorCatcher != null) {
            sMainThreadHandler.post(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.PartnerRequest$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PartnerRequest.this.m3722xd6929d96(errorType, i, bundle);
                }
            });
            return;
        }
        Log.e("SPAYSDK:PartnerRequest", "No error catcher: errorType: " + errorType + " - errorCode: " + i);
    }

    /* renamed from: lambda$catchError$1$com-samsung-android-sdk-samsungpay-v2-PartnerRequest, reason: not valid java name */
    /* synthetic */ void m3722xd6929d96(ErrorType errorType, int i, Bundle bundle) {
        this.mErrorCatcher.onError(errorType, i, bundle);
    }

    private void setDefinedApiLevelToPartnerInfo() {
        Bundle data = this.owner.partnerInfo.getData();
        if (data == null) {
            data = new Bundle();
            this.owner.partnerInfo.setData(data);
        }
        if (this.apiLevel != SpaySdk.SdkApiLevel.LEVEL_UNKNOWN) {
            data.putString(SpaySdk.PARTNER_SDK_API_LEVEL, this.apiLevel.getLevel());
            return;
        }
        throw new IllegalArgumentException("Api Level defined in manifest is empty");
    }

    private void printErrorLog(ErrorType errorType, int i) {
        Log.e("SPAYSDK:PartnerRequest", this.name + " - error: " + errorType + ", " + i);
    }

    public static class Builder {
        private PartnerRequest partnerRequest;

        public Builder(SpaySdk spaySdk, int i, Object obj) {
            PartnerRequest partnerRequest = new PartnerRequest(i, obj);
            this.partnerRequest = partnerRequest;
            partnerRequest.owner = spaySdk;
            if (spaySdk instanceof WatchManager) {
                this.partnerRequest.isWatch = true;
            }
        }

        public Builder setObj1(Object obj) {
            this.partnerRequest.obj1 = obj;
            return this;
        }

        public Builder setObj2(Object obj) {
            this.partnerRequest.obj2 = obj;
            return this;
        }

        public Builder setName(String str) {
            this.partnerRequest.name = str;
            return this;
        }

        public Builder checkApiLevelFor(Object... objArr) {
            this.partnerRequest.checkApiLevelParams = objArr;
            return this;
        }

        public Builder needStub(boolean z) {
            this.partnerRequest.needStub = z;
            return this;
        }

        public Builder checkDuplication(boolean z) {
            this.partnerRequest.checkDuplication = z;
            return this;
        }

        public Builder onError(ErrorCatcher errorCatcher) {
            this.partnerRequest.mErrorCatcher = errorCatcher;
            return this;
        }

        public Builder onExecute(RequestExecutor requestExecutor) {
            this.partnerRequest.mRequestExecutor = requestExecutor;
            return this;
        }

        public PartnerRequest create() {
            return this.partnerRequest;
        }
    }
}
