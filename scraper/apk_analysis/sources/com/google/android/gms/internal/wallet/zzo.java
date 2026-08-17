package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class zzo extends zza implements IInterface {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.wallet.internal.IOwService");
    }

    public final void zzd(CreateWalletObjectsRequest createWalletObjectsRequest, Bundle bundle, zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, createWalletObjectsRequest);
        zzc.zzc(zza, bundle);
        zzc.zzd(zza, zzqVar);
        zzc(6, zza);
    }

    public final void zze(PaymentCardRecognitionIntentRequest paymentCardRecognitionIntentRequest, Bundle bundle, zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, paymentCardRecognitionIntentRequest);
        zzc.zzc(zza, bundle);
        zzc.zzd(zza, zzqVar);
        zzc(24, zza);
    }

    public final void zzf(IsReadyToPayRequest isReadyToPayRequest, Bundle bundle, zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, isReadyToPayRequest);
        zzc.zzc(zza, bundle);
        zzc.zzd(zza, zzqVar);
        zzc(14, zza);
    }

    public final void zzg(PaymentDataRequest paymentDataRequest, Bundle bundle, zzq zzqVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, paymentDataRequest);
        zzc.zzc(zza, bundle);
        zzc.zzd(zza, zzqVar);
        zzc(19, zza);
    }
}
