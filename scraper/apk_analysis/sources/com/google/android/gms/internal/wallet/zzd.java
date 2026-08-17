package com.google.android.gms.internal.wallet;

import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class zzd extends Handler {
    private final Looper zza;

    public zzd() {
        this.zza = Looper.getMainLooper();
    }

    public zzd(Looper looper) {
        super(looper);
        this.zza = Looper.getMainLooper();
    }
}
