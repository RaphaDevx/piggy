package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes3.dex */
final class zzeo implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzep zzb;

    zzeo(zzep zzepVar, boolean z) {
        this.zzb = zzepVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkt zzktVar;
        zzktVar = this.zzb.zzb;
        zzktVar.zzJ(this.zza);
    }
}
