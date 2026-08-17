package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes3.dex */
final class zzix implements Runnable {
    final /* synthetic */ zzaw zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zzc;
    final /* synthetic */ zzjm zzd;

    zzix(zzjm zzjmVar, zzaw zzawVar, String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.zzd = zzjmVar;
        this.zza = zzawVar;
        this.zzb = str;
        this.zzc = zzcfVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [com.google.android.gms.internal.measurement.zzcf] */
    @Override // java.lang.Runnable
    public final void run() {
        zzfr zzfrVar;
        zzdx zzdxVar;
        byte[] bArr = null;
        try {
            try {
                zzjm zzjmVar = this.zzd;
                zzdxVar = zzjmVar.zzb;
                if (zzdxVar == null) {
                    zzjmVar.zzt.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                    zzfrVar = this.zzd.zzt;
                } else {
                    bArr = zzdxVar.zzu(this.zza, this.zzb);
                    this.zzd.zzQ();
                    zzfrVar = this.zzd.zzt;
                }
            } catch (RemoteException e) {
                this.zzd.zzt.zzay().zzd().zzb("Failed to send event to the service to bundle", e);
                zzfrVar = this.zzd.zzt;
            }
            zzlb zzv = zzfrVar.zzv();
            this = this.zzc;
            zzv.zzS(this, bArr);
        } catch (Throwable th) {
            this.zzd.zzt.zzv().zzS(this.zzc, bArr);
            throw th;
        }
    }
}
