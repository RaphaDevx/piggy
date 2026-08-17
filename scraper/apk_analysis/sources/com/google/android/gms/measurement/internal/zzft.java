package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes3.dex */
final class zzft implements Runnable {
    final /* synthetic */ zzac zza;
    final /* synthetic */ zzq zzb;
    final /* synthetic */ zzgj zzc;

    zzft(zzgj zzgjVar, zzac zzacVar, zzq zzqVar) {
        this.zzc = zzgjVar;
        this.zza = zzacVar;
        this.zzb = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkt zzktVar;
        zzkt zzktVar2;
        zzkt zzktVar3;
        zzktVar = this.zzc.zza;
        zzktVar.zzA();
        if (this.zza.zzc.zza() == null) {
            zzktVar3 = this.zzc.zza;
            zzktVar3.zzO(this.zza, this.zzb);
        } else {
            zzktVar2 = this.zzc.zza;
            zzktVar2.zzU(this.zza, this.zzb);
        }
    }
}
