package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class CreditCardExpirationDate extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CreditCardExpirationDate> CREATOR = new zzj();
    int zza;
    int zzb;

    CreditCardExpirationDate() {
    }

    public int getMonth() {
        return this.zza;
    }

    public int getYear() {
        return this.zzb;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    CreditCardExpirationDate(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }
}
