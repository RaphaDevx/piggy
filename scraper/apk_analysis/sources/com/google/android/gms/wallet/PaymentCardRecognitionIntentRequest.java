package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public class PaymentCardRecognitionIntentRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentCardRecognitionIntentRequest> CREATOR = new zzy();
    int zza;

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public final class Builder {
        /* synthetic */ Builder(zzx zzxVar) {
        }

        public PaymentCardRecognitionIntentRequest build() {
            Preconditions.checkArgument(PaymentCardRecognitionIntentRequest.this.zza != 0, "The paymentCardRecognitionType is required when creating a PaymentCardRecognitionIntentRequest.");
            return PaymentCardRecognitionIntentRequest.this;
        }
    }

    PaymentCardRecognitionIntentRequest() {
    }

    public static Builder builder() {
        return new PaymentCardRecognitionIntentRequest().new Builder(null);
    }

    public static PaymentCardRecognitionIntentRequest getDefaultInstance() {
        Builder builder = new PaymentCardRecognitionIntentRequest().new Builder(null);
        PaymentCardRecognitionIntentRequest.this.zza = 1;
        return builder.build();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    PaymentCardRecognitionIntentRequest(int i) {
        this.zza = i;
    }
}
