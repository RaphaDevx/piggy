package com.google.android.gms.wallet;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class PaymentCardRecognitionResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentCardRecognitionResult> CREATOR = new zzw();
    String zza;
    CreditCardExpirationDate zzb;

    PaymentCardRecognitionResult() {
    }

    public static PaymentCardRecognitionResult getFromIntent(Intent intent) {
        return (PaymentCardRecognitionResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.wallet.PaymentCardRecognitionResult", CREATOR);
    }

    @Pure
    public CreditCardExpirationDate getCreditCardExpirationDate() {
        return this.zzb;
    }

    public String getPan() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    PaymentCardRecognitionResult(String str, CreditCardExpirationDate creditCardExpirationDate) {
        this.zza = str;
        this.zzb = creditCardExpirationDate;
    }
}
