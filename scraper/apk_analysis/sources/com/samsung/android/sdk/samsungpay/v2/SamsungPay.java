package com.samsung.android.sdk.samsungpay.v2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import com.samsung.android.sdk.samsungpay.v2.ISSamsungPay;
import com.samsung.android.sdk.samsungpay.v2.PartnerRequest;
import com.samsung.android.sdk.samsungpay.v2.SamsungPayBase;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.StubBase;
import java.util.List;

/* loaded from: classes2.dex */
public final class SamsungPay extends SamsungPayBase {
    public SamsungPay(Context context, PartnerInfo partnerInfo) {
        super(context, partnerInfo, InternalConst.SERVICE_PACKAGE);
        TAG = "SPAYSDK:SamsungPay";
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected StubBase<IInterface> createStub(Context context) {
        return new StubBase.Creator().createStub(context, InternalConst.SERVICE_ACTION_COMMON, new StubBase.StubCaster() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPay$$ExternalSyntheticLambda0
            @Override // com.samsung.android.sdk.samsungpay.v2.StubBase.StubCaster
            public final IInterface castStub(IBinder iBinder) {
                return ISSamsungPay.Stub.asInterface(iBinder);
            }
        });
    }

    public void getSamsungPayStatus(StatusListener statusListener) {
        super.getSamsungPayStatus(statusListener, MANDATORY_SDK_API_LEVEL);
    }

    public void goToUpdatePage() {
        super.goToUpdatePage(SpaySdk.SdkApiLevel.LEVEL_1_2);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected void checkValidListener(StatusListener statusListener) {
        if (statusListener != null) {
            return;
        }
        Log.e(TAG, "checkValidListener: Cb is null.You should set first.");
        throw new NullPointerException("Cb is null.You should set first.");
    }

    public void activateSamsungPay() {
        super.activateSamsungPay(SpaySdk.SdkApiLevel.LEVEL_1_1);
    }

    public void getWalletInfo(List<String> list, StatusListener statusListener) {
        super.getWalletInfo(list, statusListener, SpaySdk.SdkApiLevel.LEVEL_1_2);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected void doGetWalletInfo(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException {
        SamsungPayBase.StatusListenerInternal statusListenerInternal = (SamsungPayBase.StatusListenerInternal) partnerRequest.obj2;
        ((ISSamsungPay) iInterface).getWalletInfo(getPartnerInfo(), (List) partnerRequest.obj1, statusListenerInternal.getStatusListener());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    /* renamed from: doGetSamsungPayStatus */
    public void m3725x6794b1c0(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException {
        Status samsungPayStatus = ((ISSamsungPay) iInterface).getSamsungPayStatus(getPartnerInfo());
        if (samsungPayStatus != null) {
            int status = samsungPayStatus.getStatus();
            if (status == 0 || status == 1 || status == 2 || status == 3) {
                ((StatusListener) partnerRequest.callbackObj).onSuccess(status, samsungPayStatus.getData());
            } else {
                ((StatusListener) partnerRequest.callbackObj).onFail(status, samsungPayStatus.getData());
            }
        }
        this.mSamsungPayBaseStub.nextRequest();
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.SamsungPayBase
    protected void doGoToUpdatePage() throws PackageManager.NameNotFoundException {
        Log.d(TAG, "goToUpdatePage");
        Context context = this.contextRef.get();
        if (context != null) {
            int i = context.getPackageManager().getPackageInfo(this.mPackageName, 0).versionCode / AndroidComposeViewAccessibilityDelegateCompat.ParcelSafeTextLength;
            Intent intent = new Intent();
            if (i >= 2100) {
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse("samsungpay://launch?action=aboutsamsungpay"));
            } else {
                intent.setComponent(new ComponentName(this.mPackageName, context.getPackageManager().getLaunchIntentForPackage(this.mPackageName).getComponent().getClassName()));
            }
            intent.addFlags(268468224);
            context.startActivity(intent);
        } else {
            Log.e(TAG, "doGoToUpdatePage - Context is null");
        }
        this.mSamsungPayBaseStub.nextRequest();
    }

    public void getSamsungPayTransitStatus(final StatusListener statusListener) {
        Log.d(TAG, "getSamsungPayTransitStatus()");
        assertNotNull(statusListener);
        SamsungPayBase.StatusListenerInternal statusListenerInternal = new SamsungPayBase.StatusListenerInternal();
        PartnerRequest create = new PartnerRequest.Builder(this, 21, statusListener).setName("getSamsungPayTransitStatus").setObj1(statusListenerInternal).onExecute(new PartnerRequest.RequestExecutor() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPay$$ExternalSyntheticLambda1
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.RequestExecutor
            public final void handleRequest(IInterface iInterface, PartnerRequest partnerRequest) {
                SamsungPay.this.doGetSamsungPayTransitStatus(iInterface, partnerRequest);
            }
        }).onError(new PartnerRequest.ErrorCatcher() { // from class: com.samsung.android.sdk.samsungpay.v2.SamsungPay$$ExternalSyntheticLambda2
            @Override // com.samsung.android.sdk.samsungpay.v2.PartnerRequest.ErrorCatcher
            public final void onError(ErrorType errorType, int i, Bundle bundle) {
                StatusListener.this.onFail(i, bundle);
            }
        }).create();
        this.mSamsungPayBaseStub.postRequest(create, SpaySdk.SdkApiLevel.LEVEL_2_8);
        statusListenerInternal.set(create);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doGetSamsungPayTransitStatus(IInterface iInterface, PartnerRequest partnerRequest) throws RemoteException {
        ((ISSamsungPay) iInterface).getSamsungPayTransitStatus(getPartnerInfo(), ((SamsungPayBase.StatusListenerInternal) partnerRequest.obj1).getStatusListener());
    }
}
