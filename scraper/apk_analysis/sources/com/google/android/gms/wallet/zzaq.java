package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public abstract class zzaq extends BaseImplementation.ApiMethodImpl {
    public zzaq(GoogleApiClient googleApiClient) {
        super(Wallet.API, googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder
    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((zzaq) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public abstract void doExecute(com.google.android.gms.internal.wallet.zzy zzyVar) throws RemoteException;
}
