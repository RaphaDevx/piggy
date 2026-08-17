package com.google.android.gms.identity.intents;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-identity@@17.0.1 */
/* loaded from: classes3.dex */
final class zzb extends zzc {
    final /* synthetic */ UserAddressRequest zza;
    final /* synthetic */ int zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzb(GoogleApiClient googleApiClient, UserAddressRequest userAddressRequest, int i) {
        super(googleApiClient);
        this.zza = userAddressRequest;
        this.zzb = i;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(com.google.android.gms.internal.identity.zze zzeVar) throws RemoteException {
        zzeVar.zzp(this.zza, this.zzb);
        setResult((zzb) Status.RESULT_SUCCESS);
    }
}
