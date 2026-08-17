package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SheetControl implements Parcelable {
    public static final Parcelable.Creator<SheetControl> CREATOR = new Parcelable.Creator<SheetControl>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SheetControl createFromParcel(Parcel parcel) {
            int i = AnonymousClass2.$SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype[((Controltype) parcel.readParcelable(Controltype.class.getClassLoader())).ordinal()];
            if (i == 1) {
                return new PlainTextControl(parcel);
            }
            if (i == 2) {
                return new AmountBoxControl(parcel);
            }
            if (i == 3) {
                return new AddressControl(parcel);
            }
            if (i == 4) {
                return new SpinnerControl(parcel);
            }
            return new SheetControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SheetControl[] newArray(int i) {
            return new SheetControl[i];
        }
    };
    private String controlId;
    private Controltype controltype;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SheetControl(Parcel parcel) {
        this.controlId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.controltype, i);
        parcel.writeString(this.controlId);
    }

    /* renamed from: com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype;

        static {
            int[] iArr = new int[Controltype.values().length];
            $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype = iArr;
            try {
                iArr[Controltype.PLAINTEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype[Controltype.AMOUNTBOX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype[Controltype.ADDRESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$samsung$android$sdk$samsungpay$v2$payment$sheet$SheetControl$Controltype[Controltype.SPINNER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public SheetControl(Controltype controltype) {
        this.controltype = controltype;
    }

    public String getControlId() {
        return this.controlId;
    }

    protected void setControlId(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("setControlId : controlId is invalid.");
        }
        this.controlId = str;
    }

    public Controltype getControltype() {
        return this.controltype;
    }

    public void setControltype(Controltype controltype) {
        this.controltype = controltype;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SheetControl sheetControl = (SheetControl) obj;
        String str = this.controlId;
        if (str == null ? sheetControl.controlId == null : str.equals(sheetControl.controlId)) {
            return this.controltype == sheetControl.controltype;
        }
        return false;
    }

    public int hashCode() {
        String str = this.controlId;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Controltype controltype = this.controltype;
        return hashCode + (controltype != null ? controltype.hashCode() : 0);
    }

    public enum Controltype implements Parcelable {
        PLAINTEXT,
        AMOUNTBOX,
        ADDRESS,
        SPINNER;

        public static final Parcelable.Creator<Controltype> CREATOR = new Parcelable.Creator<Controltype>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl.Controltype.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Controltype createFromParcel(Parcel parcel) {
                return Controltype.values()[parcel.readInt()];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Controltype[] newArray(int i) {
                return new Controltype[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }
}
