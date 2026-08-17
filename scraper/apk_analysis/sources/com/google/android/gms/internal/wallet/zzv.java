package com.google.android.gms.internal.wallet;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolvableVoidResult;
import com.google.android.gms.wallet.AutoResolveHelper;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
final class zzv extends zzt {
    private final TaskCompletionSource zza;

    public zzv(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.wallet.zzt, com.google.android.gms.internal.wallet.zzq
    public final void zzh(int i, Bundle bundle) {
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        AutoResolveHelper.zzd((pendingIntent == null || i != 6) ? new Status(i) : new Status(6, "Need to resolve PendingIntent", pendingIntent), new AutoResolvableVoidResult(), this.zza);
    }
}
