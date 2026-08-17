package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes3.dex */
final class zzfq implements Runnable {
    final /* synthetic */ zzgu zza;
    final /* synthetic */ zzfr zzb;

    zzfq(zzfr zzfrVar, zzgu zzguVar) {
        this.zzb = zzfrVar;
        this.zza = zzguVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfr.zzA(this.zzb, this.zza);
        this.zzb.zzH(this.zza.zzg);
    }
}
