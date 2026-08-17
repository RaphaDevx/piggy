package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes3.dex */
final class zzju {
    private static final zzjs zza = new zzjt();
    private static final zzjs zzb;

    static {
        zzjs zzjsVar = null;
        try {
            zzjsVar = (zzjs) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zzb = zzjsVar;
    }

    static zzjs zza() {
        zzjs zzjsVar = zzb;
        if (zzjsVar != null) {
            return zzjsVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzjs zzb() {
        return zza;
    }
}
