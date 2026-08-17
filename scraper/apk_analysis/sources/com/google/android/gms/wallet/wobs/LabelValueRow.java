package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class LabelValueRow extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LabelValueRow> CREATOR = new zzf();

    @Deprecated
    String zza;

    @Deprecated
    String zzb;
    final ArrayList zzc;

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public final class Builder {
        /* synthetic */ Builder(zze zzeVar) {
        }

        public Builder addColumn(LabelValue labelValue) {
            LabelValueRow.this.zzc.add(labelValue);
            return this;
        }

        public Builder addColumns(Collection<LabelValue> collection) {
            LabelValueRow.this.zzc.addAll(collection);
            return this;
        }

        public LabelValueRow build() {
            return LabelValueRow.this;
        }

        @Deprecated
        public Builder setHexBackgroundColor(String str) {
            LabelValueRow.this.zzb = str;
            return this;
        }

        @Deprecated
        public Builder setHexFontColor(String str) {
            LabelValueRow.this.zza = str;
            return this;
        }
    }

    LabelValueRow() {
        this.zzc = ArrayUtils.newArrayList();
    }

    public static Builder newBuilder() {
        return new LabelValueRow().new Builder(null);
    }

    public ArrayList<LabelValue> getColumns() {
        return this.zzc;
    }

    @Deprecated
    public String getHexBackgroundColor() {
        return this.zzb;
    }

    @Deprecated
    public String getHexFontColor() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    LabelValueRow(String str, String str2, ArrayList arrayList) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = arrayList;
    }
}
