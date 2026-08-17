package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.2.0 */
/* loaded from: classes3.dex */
final class zzls {
    private static final zzlr zza;
    private static final zzlr zzb;

    static {
        zzlr zzlrVar = null;
        try {
            zzlrVar = (zzlr) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        zza = zzlrVar;
        zzb = new zzlr();
    }

    static zzlr zza() {
        return zza;
    }

    static zzlr zzb() {
        return zzb;
    }
}
