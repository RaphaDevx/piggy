package com.google.android.gms.wallet.contract;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public class ApiTaskResult<T> {
    private final Object zza;
    private final Status zzb;

    public ApiTaskResult(Status status) {
        this(null, status);
    }

    public ApiTaskResult(T t, Status status) {
        this.zza = t;
        this.zzb = status;
    }

    public T getResult() {
        return (T) this.zza;
    }

    public Status getStatus() {
        return this.zzb;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzb).add("result", this.zza).toString();
    }
}
