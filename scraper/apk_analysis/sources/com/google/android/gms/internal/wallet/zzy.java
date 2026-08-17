package com.google.android.gms.internal.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class zzy extends GmsClient {
    private final Context zze;
    private final int zzf;
    private final String zzg;
    private final int zzh;
    private final boolean zzi;

    public zzy(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, int i, int i2, boolean z) {
        super(context, looper, 4, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zze = context;
        this.zzf = i;
        Account account = clientSettings.getAccount();
        this.zzg = account != null ? account.name : null;
        this.zzh = i2;
        this.zzi = z;
    }

    private final Bundle zzu() {
        String packageName = this.zze.getPackageName();
        Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", this.zzf);
        bundle.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", this.zzi);
        bundle.putString("androidPackageName", packageName);
        String str = this.zzg;
        if (!TextUtils.isEmpty(str)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(str, AccountType.GOOGLE));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", this.zzh);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IOwService");
        return queryLocalInterface instanceof zzo ? (zzo) queryLocalInterface : new zzo(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return com.google.android.gms.wallet.zzk.zzi;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12600000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresAccount() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzp(CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        zzs zzsVar = new zzs((Activity) this.zze, i);
        try {
            ((zzo) getService()).zzd(createWalletObjectsRequest, zzu(), zzsVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", e);
            zzsVar.zzh(8, Bundle.EMPTY);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzq(CreateWalletObjectsRequest createWalletObjectsRequest, TaskCompletionSource taskCompletionSource) {
        Bundle zzu = zzu();
        zzu.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzv zzvVar = new zzv(taskCompletionSource);
        try {
            ((zzo) getService()).zzd(createWalletObjectsRequest, zzu, zzvVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException creating wallet objects", e);
            zzvVar.zzh(8, Bundle.EMPTY);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzr(PaymentCardRecognitionIntentRequest paymentCardRecognitionIntentRequest, TaskCompletionSource taskCompletionSource) {
        try {
            ((zzo) getService()).zze(paymentCardRecognitionIntentRequest, zzu(), new zzw(taskCompletionSource));
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during getPaymentCardRecognitionIntent", e);
            Status status = Status.RESULT_INTERNAL_ERROR;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzs(IsReadyToPayRequest isReadyToPayRequest, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzu zzuVar = new zzu(taskCompletionSource);
        try {
            ((zzo) getService()).zzf(isReadyToPayRequest, zzu(), zzuVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
            zzuVar.zzc(Status.RESULT_INTERNAL_ERROR, false, Bundle.EMPTY);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzt(PaymentDataRequest paymentDataRequest, TaskCompletionSource taskCompletionSource) {
        Bundle zzu = zzu();
        zzu.putBoolean("com.google.android.gms.wallet.EXTRA_USING_AUTO_RESOLVABLE_RESULT", true);
        zzx zzxVar = new zzx(taskCompletionSource);
        try {
            ((zzo) getService()).zzg(paymentDataRequest, zzu, zzxVar);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting payment data", e);
            zzxVar.zzf(Status.RESULT_INTERNAL_ERROR, null, Bundle.EMPTY);
        }
    }
}
