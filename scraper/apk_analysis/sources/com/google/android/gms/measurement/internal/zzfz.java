package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes3.dex */
final class zzfz implements Runnable {
    final /* synthetic */ zzq zza;
    final /* synthetic */ zzgj zzb;

    zzfz(zzgj zzgjVar, zzq zzqVar) {
        this.zzb = zzgjVar;
        this.zza = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkt zzktVar;
        zzkt zzktVar2;
        zzktVar = this.zzb.zza;
        zzktVar.zzA();
        zzktVar2 = this.zzb.zza;
        zzktVar2.zzQ(this.zza);
    }
}
