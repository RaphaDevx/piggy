package com.google.android.gms.wallet.button;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
/* loaded from: classes3.dex */
public final class ButtonOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ButtonOptions> CREATOR = new zzb();
    int zza;
    int zzb;
    int zzc;
    String zzd;
    boolean zze = false;

    /* compiled from: com.google.android.gms:play-services-wallet@@19.3.0 */
    public final class Builder {
        /* synthetic */ Builder(zza zzaVar) {
        }

        public ButtonOptions build() {
            return ButtonOptions.this;
        }

        public Builder setAllowedPaymentMethods(String str) {
            ButtonOptions.this.zzd = str;
            return this;
        }

        public Builder setButtonTheme(int i) {
            ButtonOptions.this.zzb = i;
            return this;
        }

        public Builder setButtonType(int i) {
            ButtonOptions.this.zza = i;
            return this;
        }

        public Builder setCornerRadius(int i) {
            ButtonOptions buttonOptions = ButtonOptions.this;
            buttonOptions.zzc = i;
            buttonOptions.zze = true;
            return this;
        }
    }

    private ButtonOptions() {
    }

    public static Builder newBuilder() {
        return new ButtonOptions().new Builder(null);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ButtonOptions) {
            ButtonOptions buttonOptions = (ButtonOptions) obj;
            if (Objects.equal(Integer.valueOf(this.zza), Integer.valueOf(buttonOptions.zza)) && Objects.equal(Integer.valueOf(this.zzb), Integer.valueOf(buttonOptions.zzb)) && Objects.equal(Integer.valueOf(this.zzc), Integer.valueOf(buttonOptions.zzc)) && Objects.equal(this.zzd, buttonOptions.zzd)) {
                return true;
            }
        }
        return false;
    }

    public String getAllowedPaymentMethods() {
        return this.zzd;
    }

    public int getButtonTheme() {
        return this.zzb;
    }

    public int getButtonType() {
        return this.zza;
    }

    public int getCornerRadius() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getButtonType());
        SafeParcelWriter.writeInt(parcel, 2, getButtonTheme());
        SafeParcelWriter.writeInt(parcel, 3, getCornerRadius());
        SafeParcelWriter.writeString(parcel, 4, getAllowedPaymentMethods(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    ButtonOptions(int i, int i2, int i3, String str) {
        this.zza = ((Integer) Preconditions.checkNotNull(Integer.valueOf(i))).intValue();
        this.zzb = ((Integer) Preconditions.checkNotNull(Integer.valueOf(i2))).intValue();
        this.zzc = ((Integer) Preconditions.checkNotNull(Integer.valueOf(i3))).intValue();
        this.zzd = (String) Preconditions.checkNotNull(str);
    }
}
