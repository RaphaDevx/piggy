package com.samsung.android.sdk.samsungpay.v2.service;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.ErrorType;
import com.samsung.android.sdk.samsungpay.v2.InternalConst;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.StubBase;
import com.samsung.android.sdk.samsungpay.v2.service.ISServiceManager;
import com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback;
import com.samsung.android.sdk.samsungpay.v2.service.ServiceManager;

/* loaded from: classes2.dex */
public final class ServiceManager extends SpaySdk {
    private static final int OPT_GET_USER_INFO = 0;
    private static final int OPT_NOTIFY_USER_SIGNUP_RESULT = 1;
    private static final String TAG = "SPAYSDK:ServiceManager";
    private StubBase<ISServiceManager> mServiceManagerStub;

    public ServiceManager(Context context, PartnerInfo partnerInfo) {
        super(context, partnerInfo);
        this.mServiceManagerStub = new StubBase.Creator().createStub(context, InternalConst.SERVICE_ACTION_SOAPP, new StubBase.StubCaster() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.StubBase.StubCaster
            public final IInterface castStub(IBinder iBinder) {
                return ISServiceManager.Stub.asInterface(iBinder);
            }
        });
    }

    @Deprecated
    public void getUserInfo(RequestType[] requestTypeArr, final UserInfoListener userInfoListener) {
        assertNotNull(userInfoListener);
        final RequestType[] convertRequestAllType = convertRequestAllType(requestTypeArr);
        this.mServiceManagerStub.postRequest(new PartnerRequest.Builder(this, 0, null).setName("getUserInfo").checkApiLevelFor(convertRequestAllType).checkDuplication(true).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$$ExternalSyntheticLambda3
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                ServiceManager.this.m3744xbd281478(convertRequestAllType, userInfoListener, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$$ExternalSyntheticLambda4
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                UserInfoListener.this.onFail(i, bundle);
            }
        }).create(), SpaySdk.SdkApiLevel.LEVEL_2_6);
    }

    /* renamed from: lambda$getUserInfo$0$com-samsung-android-sdk-samsungpay-v2-service-ServiceManager, reason: not valid java name */
    /* synthetic */ void m3744xbd281478(RequestType[] requestTypeArr, UserInfoListener userInfoListener, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        doGetUserInfo((ISServiceManager) iInterface, requestTypeArr, userInfoListener);
    }

    @Deprecated
    public int notifyUserSignUpResult(final String str, final boolean z, final UserSignUpNotifyListener userSignUpNotifyListener) {
        this.mServiceManagerStub.postRequest(new PartnerRequest.Builder(this, 1, null).setName("notifyUserSignUpResult").onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                ServiceManager.this.m3745xe220e6a2(str, z, userSignUpNotifyListener, iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                UserSignUpNotifyListener.this.onFail(i, bundle);
            }
        }).create(), SpaySdk.SdkApiLevel.LEVEL_2_6);
        return 0;
    }

    /* renamed from: lambda$notifyUserSignUpResult$2$com-samsung-android-sdk-samsungpay-v2-service-ServiceManager, reason: not valid java name */
    /* synthetic */ void m3745xe220e6a2(String str, boolean z, UserSignUpNotifyListener userSignUpNotifyListener, IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException, ActivityNotFoundException, PackageManager.NameNotFoundException {
        doNotifyUserSignUpResult((ISServiceManager) iInterface, str, z, userSignUpNotifyListener);
    }

    /* renamed from: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$1, reason: invalid class name */
    class AnonymousClass1 extends ISUserInfoCallback.Stub {
        final /* synthetic */ UserInfoListener val$listener;

        AnonymousClass1(UserInfoListener userInfoListener) {
            this.val$listener = userInfoListener;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback
        public void onGetUserInfoInitiated(final Bundle bundle) {
            Log.d(ServiceManager.TAG, "onGetUserInfoInitiated: ");
            final ComponentName componentName = new ComponentName(bundle.getString(InternalConst.EXTRA_PACKAGE_NAME), bundle.getString(InternalConst.EXTRA_CLASS_NAME));
            ServiceManager serviceManager = ServiceManager.this;
            final UserInfoListener userInfoListener = this.val$listener;
            serviceManager.runOnMainThread(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ServiceManager.AnonymousClass1.this.m3746x8451dbdf(componentName, bundle, userInfoListener);
                }
            });
        }

        /* renamed from: lambda$onGetUserInfoInitiated$0$com-samsung-android-sdk-samsungpay-v2-service-ServiceManager$1, reason: not valid java name */
        /* synthetic */ void m3746x8451dbdf(ComponentName componentName, Bundle bundle, UserInfoListener userInfoListener) {
            ServiceManager.this.showUserInfoSheet(componentName, bundle, userInfoListener);
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback
        public void onReceived(final int i, final Bundle bundle) {
            if (i == 0) {
                final UserInfoCollection userInfoCollection = new UserInfoCollection(bundle);
                ServiceManager serviceManager = ServiceManager.this;
                final UserInfoListener userInfoListener = this.val$listener;
                serviceManager.runOnMainThread(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserInfoListener.this.onSuccess(userInfoCollection);
                    }
                });
            } else {
                ServiceManager serviceManager2 = ServiceManager.this;
                final UserInfoListener userInfoListener2 = this.val$listener;
                serviceManager2.runOnMainThread(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserInfoListener.this.onFail(i, bundle);
                    }
                });
            }
            ServiceManager.this.mServiceManagerStub.nextRequest();
        }
    }

    private void doGetUserInfo(ISServiceManager iSServiceManager, RequestType[] requestTypeArr, UserInfoListener userInfoListener) throws RemoteException {
        iSServiceManager.getUserInfo(this.partnerInfo, requestTypeArr, new AnonymousClass1(userInfoListener));
    }

    /* renamed from: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$2, reason: invalid class name */
    class AnonymousClass2 extends ISUserInfoCallback.Stub {
        final /* synthetic */ UserSignUpNotifyListener val$listener;

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback
        public void onGetUserInfoInitiated(Bundle bundle) {
        }

        AnonymousClass2(UserSignUpNotifyListener userSignUpNotifyListener) {
            this.val$listener = userSignUpNotifyListener;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback
        public void onReceived(final int i, final Bundle bundle) {
            if (i == 0) {
                ServiceManager serviceManager = ServiceManager.this;
                final UserSignUpNotifyListener userSignUpNotifyListener = this.val$listener;
                serviceManager.runOnMainThread(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserSignUpNotifyListener.this.onSuccess();
                    }
                });
            } else {
                ServiceManager serviceManager2 = ServiceManager.this;
                final UserSignUpNotifyListener userSignUpNotifyListener2 = this.val$listener;
                serviceManager2.runOnMainThread(new Runnable() { // from class: com.samsung.android.sdk.samsungpay.v2.service.ServiceManager$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserSignUpNotifyListener.this.onFail(i, bundle);
                    }
                });
            }
            ServiceManager.this.mServiceManagerStub.nextRequest();
        }
    }

    private void doNotifyUserSignUpResult(ISServiceManager iSServiceManager, String str, boolean z, UserSignUpNotifyListener userSignUpNotifyListener) throws RemoteException {
        iSServiceManager.notifyUserSignUpResult(this.partnerInfo, str, z, new AnonymousClass2(userSignUpNotifyListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUserInfoSheet(ComponentName componentName, Bundle bundle, UserInfoListener userInfoListener) {
        Log.d(TAG, "showUserInfoSheet()");
        try {
            Intent intent = new Intent();
            Context context = this.contextRef.get();
            if (context == null) {
                Log.e(TAG, "Context is destroyed");
                return;
            }
            if (context instanceof Activity) {
                Log.d(TAG, "Context is an instance of Activity");
                intent.setFlags(536870912);
            } else {
                Log.d(TAG, "Not Activity context");
                intent.setFlags(268435456);
            }
            intent.setComponent(componentName);
            intent.putExtra(InternalConst.EXTRA_CALLER_UID, bundle.getInt(InternalConst.EXTRA_CALLER_UID));
            intent.putExtra(InternalConst.EXTRA_SDK_VERSION, 2);
            intent.putExtra(InternalConst.EXTRA_NEXT_ORDER, bundle);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.e(TAG, "activity not found and return error");
            userInfoListener.onFail(-1, null);
            this.mServiceManagerStub.nextRequest();
        }
    }
}
