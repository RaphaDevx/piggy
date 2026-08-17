package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.libs.identity.ClientIdentity;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
public final class zzaf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        WorkSource workSource = new WorkSource();
        ClientIdentity clientIdentity = null;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        long j = -1;
        float f = 0.0f;
        int i3 = Integer.MAX_VALUE;
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        long j5 = 600000;
        long j6 = 3600000;
        int i4 = 102;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    j6 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 3:
                    j5 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                case 14:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 5:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 6:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    j4 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 9:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 11:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 12:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 13:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 15:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 16:
                    workSource = (WorkSource) SafeParcelReader.createParcelable(parcel, readHeader, WorkSource.CREATOR);
                    break;
                case 17:
                    clientIdentity = (ClientIdentity) SafeParcelReader.createParcelable(parcel, readHeader, ClientIdentity.CREATOR);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LocationRequest(i4, j6, j5, j4, j2, j3, i3, f, z, j, i, i2, z2, workSource, clientIdentity);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new LocationRequest[i];
    }
}
