package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class zzac implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        CardRequirements cardRequirements = null;
        ShippingAddressRequirements shippingAddressRequirements = null;
        ArrayList<Integer> arrayList = null;
        PaymentMethodTokenizationParameters paymentMethodTokenizationParameters = null;
        TransactionInfo transactionInfo = null;
        String str = null;
        byte[] bArr = null;
        Bundle bundle = null;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 2:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 3:
                    cardRequirements = (CardRequirements) SafeParcelReader.createParcelable(parcel, readHeader, CardRequirements.CREATOR);
                    break;
                case 4:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 5:
                    shippingAddressRequirements = (ShippingAddressRequirements) SafeParcelReader.createParcelable(parcel, readHeader, ShippingAddressRequirements.CREATOR);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createIntegerList(parcel, readHeader);
                    break;
                case 7:
                    paymentMethodTokenizationParameters = (PaymentMethodTokenizationParameters) SafeParcelReader.createParcelable(parcel, readHeader, PaymentMethodTokenizationParameters.CREATOR);
                    break;
                case 8:
                    transactionInfo = (TransactionInfo) SafeParcelReader.createParcelable(parcel, readHeader, TransactionInfo.CREATOR);
                    break;
                case 9:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 11:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 12:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PaymentDataRequest(z2, z3, cardRequirements, z4, shippingAddressRequirements, arrayList, paymentMethodTokenizationParameters, transactionInfo, z, str, bArr, bundle);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PaymentDataRequest[i];
    }
}
