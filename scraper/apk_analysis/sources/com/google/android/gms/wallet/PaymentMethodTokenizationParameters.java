package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class PaymentMethodTokenizationParameters extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentMethodTokenizationParameters> CREATOR = new zzaf();
    int zza;
    final Bundle zzb;

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public final class Builder {
        /* synthetic */ Builder(zzae zzaeVar) {
        }

        public Builder addParameter(String str, String str2) {
            Preconditions.checkNotEmpty(str, "Tokenization parameter name must not be empty");
            Preconditions.checkNotEmpty(str2, "Tokenization parameter value must not be empty");
            PaymentMethodTokenizationParameters.this.zzb.putString(str, str2);
            return this;
        }

        public PaymentMethodTokenizationParameters build() {
            return PaymentMethodTokenizationParameters.this;
        }

        public Builder setPaymentMethodTokenizationType(int i) {
            PaymentMethodTokenizationParameters.this.zza = i;
            return this;
        }
    }

    private PaymentMethodTokenizationParameters() {
        this.zzb = new Bundle();
    }

    public static Builder newBuilder() {
        return new PaymentMethodTokenizationParameters().new Builder(null);
    }

    public Bundle getParameters() {
        return new Bundle(this.zzb);
    }

    public int getPaymentMethodTokenizationType() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    PaymentMethodTokenizationParameters(int i, Bundle bundle) {
        new Bundle();
        this.zza = i;
        this.zzb = bundle;
    }
}
