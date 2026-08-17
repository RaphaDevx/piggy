package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class PaymentDataRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentDataRequest> CREATOR = new zzac();
    boolean zza;
    boolean zzb;
    CardRequirements zzc;
    boolean zzd;
    ShippingAddressRequirements zze;
    ArrayList zzf;
    PaymentMethodTokenizationParameters zzg;
    TransactionInfo zzh;
    boolean zzi;
    String zzj;
    byte[] zzk;
    Bundle zzl;

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    @Deprecated
    public final class Builder {
        /* synthetic */ Builder(zzab zzabVar) {
        }

        public Builder addAllowedPaymentMethod(int i) {
            PaymentDataRequest paymentDataRequest = PaymentDataRequest.this;
            if (paymentDataRequest.zzf == null) {
                paymentDataRequest.zzf = new ArrayList();
            }
            PaymentDataRequest.this.zzf.add(Integer.valueOf(i));
            return this;
        }

        public Builder addAllowedPaymentMethods(Collection<Integer> collection) {
            boolean z = false;
            if (collection != null && !collection.isEmpty()) {
                z = true;
            }
            Preconditions.checkArgument(z, "allowedPaymentMethods can't be null or empty!");
            PaymentDataRequest paymentDataRequest = PaymentDataRequest.this;
            if (paymentDataRequest.zzf == null) {
                paymentDataRequest.zzf = new ArrayList();
            }
            PaymentDataRequest.this.zzf.addAll(collection);
            return this;
        }

        public PaymentDataRequest build() {
            PaymentDataRequest paymentDataRequest = PaymentDataRequest.this;
            if (paymentDataRequest.zzj == null && paymentDataRequest.zzk == null) {
                Preconditions.checkNotNull(paymentDataRequest.zzf, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
                Preconditions.checkNotNull(PaymentDataRequest.this.zzc, "Card requirements must be set!");
                PaymentDataRequest paymentDataRequest2 = PaymentDataRequest.this;
                if (paymentDataRequest2.zzg != null) {
                    Preconditions.checkNotNull(paymentDataRequest2.zzh, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
                }
            }
            return PaymentDataRequest.this;
        }

        public Builder setCardRequirements(CardRequirements cardRequirements) {
            PaymentDataRequest.this.zzc = cardRequirements;
            return this;
        }

        public Builder setEmailRequired(boolean z) {
            PaymentDataRequest.this.zza = z;
            return this;
        }

        public Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters) {
            PaymentDataRequest.this.zzg = paymentMethodTokenizationParameters;
            return this;
        }

        public Builder setPhoneNumberRequired(boolean z) {
            PaymentDataRequest.this.zzb = z;
            return this;
        }

        public Builder setShippingAddressRequired(boolean z) {
            PaymentDataRequest.this.zzd = z;
            return this;
        }

        public Builder setShippingAddressRequirements(ShippingAddressRequirements shippingAddressRequirements) {
            PaymentDataRequest.this.zze = shippingAddressRequirements;
            return this;
        }

        public Builder setTransactionInfo(TransactionInfo transactionInfo) {
            PaymentDataRequest.this.zzh = transactionInfo;
            return this;
        }

        public Builder setUiRequired(boolean z) {
            PaymentDataRequest.this.zzi = z;
            return this;
        }
    }

    private PaymentDataRequest() {
        this.zzi = true;
    }

    public static PaymentDataRequest fromJson(String str) {
        Builder newBuilder = newBuilder();
        PaymentDataRequest.this.zzj = (String) Preconditions.checkNotNull(str, "paymentDataRequestJson cannot be null!");
        return newBuilder.build();
    }

    @Deprecated
    public static Builder newBuilder() {
        return new PaymentDataRequest().new Builder(null);
    }

    @Deprecated
    public ArrayList<Integer> getAllowedPaymentMethods() {
        return this.zzf;
    }

    @Deprecated
    public CardRequirements getCardRequirements() {
        return this.zzc;
    }

    @Deprecated
    public PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzg;
    }

    public Bundle getSavedState() {
        return this.zzl;
    }

    @Deprecated
    public ShippingAddressRequirements getShippingAddressRequirements() {
        return this.zze;
    }

    @Deprecated
    public TransactionInfo getTransactionInfo() {
        return this.zzh;
    }

    @Deprecated
    public boolean isEmailRequired() {
        return this.zza;
    }

    @Deprecated
    public boolean isPhoneNumberRequired() {
        return this.zzb;
    }

    @Deprecated
    public boolean isShippingAddressRequired() {
        return this.zzd;
    }

    @Deprecated
    public boolean isUiRequired() {
        return this.zzi;
    }

    public String toJson() {
        return this.zzj;
    }

    public PaymentDataRequest withSavedState(Bundle bundle) {
        this.zzl = bundle;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeIntegerList(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzi);
        SafeParcelWriter.writeString(parcel, 10, this.zzj, false);
        SafeParcelWriter.writeBundle(parcel, 11, this.zzl, false);
        SafeParcelWriter.writeByteArray(parcel, 12, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    PaymentDataRequest(boolean z, boolean z2, CardRequirements cardRequirements, boolean z3, ShippingAddressRequirements shippingAddressRequirements, ArrayList arrayList, PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, TransactionInfo transactionInfo, boolean z4, String str, byte[] bArr, Bundle bundle) {
        this.zza = z;
        this.zzb = z2;
        this.zzc = cardRequirements;
        this.zzd = z3;
        this.zze = shippingAddressRequirements;
        this.zzf = arrayList;
        this.zzg = paymentMethodTokenizationParameters;
        this.zzh = transactionInfo;
        this.zzi = z4;
        this.zzj = str;
        this.zzk = bArr;
        this.zzl = bundle;
    }
}
