package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.scandit.datacapture.frameworks.core.events.EventForResult;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes3.dex */
final class zzjy {
    final /* synthetic */ zzkc zza;
    private zzjx zzb;

    zzjy(zzkc zzkcVar) {
        this.zza = zzkcVar;
    }

    final void zza(long j) {
        Handler handler;
        this.zzb = new zzjx(this, this.zza.zzt.zzav().currentTimeMillis(), j);
        handler = this.zza.zzd;
        handler.postDelayed(this.zzb, EventForResult.DEFAULT_TIMEOUT_MILLIS);
    }

    final void zzb() {
        Handler handler;
        this.zza.zzg();
        zzjx zzjxVar = this.zzb;
        if (zzjxVar != null) {
            handler = this.zza.zzd;
            handler.removeCallbacks(zzjxVar);
        }
        this.zza.zzt.zzm().zzm.zza(false);
    }
}
