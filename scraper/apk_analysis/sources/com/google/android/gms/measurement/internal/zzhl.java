package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.2.0 */
/* loaded from: classes3.dex */
final class zzhl implements zzla {
    final /* synthetic */ zzhx zza;

    zzhl(zzhx zzhxVar) {
        this.zza = zzhxVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzla
    public final void zza(String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            this.zza.zzD("auto", "_err", bundle);
        } else {
            this.zza.zzF("auto", "_err", bundle, str);
        }
    }
}
