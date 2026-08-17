package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SheetItem implements Parcelable {
    public static final Parcelable.Creator<SheetItem> CREATOR = new Parcelable.Creator<SheetItem>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SheetItem createFromParcel(Parcel parcel) {
            return new SheetItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SheetItem[] newArray(int i) {
            return new SheetItem[i];
        }
    };
    private double dValue;
    private Bundle extraValue;
    private String id;
    private String sValue;
    private SheetItemType sheetItemType;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private SheetItem(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.sValue = builder.sValue;
        this.dValue = builder.dValue;
        this.sheetItemType = builder.sheetItemType;
        this.extraValue = builder.extraValue;
    }

    protected SheetItem(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.sValue = parcel.readString();
        this.dValue = parcel.readDouble();
        this.sheetItemType = (SheetItemType) parcel.readParcelable(SheetItemType.class.getClassLoader());
        this.extraValue = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.sValue);
        parcel.writeDouble(this.dValue);
        parcel.writeParcelable(this.sheetItemType, i);
        parcel.writeBundle(this.extraValue);
    }

    public SheetItemType getSheetItemType() {
        return this.sheetItemType;
    }

    public Bundle getExtraValue() {
        return this.extraValue;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSValue() {
        return this.sValue;
    }

    public double getDValue() {
        return this.dValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.id.equals(((SheetItem) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return "SheetItem{extraValue=" + this.extraValue + ", id='" + this.id + "', title='" + this.title + "', sValue='" + this.sValue + "', dValue='" + this.dValue + "', sheetItemType=" + this.sheetItemType + '}';
    }

    public static class Builder {
        private double dValue;
        private Bundle extraValue;
        private String id;
        private String sValue;
        private SheetItemType sheetItemType;
        private String title;

        public Builder setId(String str) {
            this.id = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder setSValue(String str) {
            this.sValue = str;
            return this;
        }

        public Builder setDValue(double d) {
            this.dValue = d;
            return this;
        }

        public Builder setSheetItemType(SheetItemType sheetItemType) {
            this.sheetItemType = sheetItemType;
            return this;
        }

        public Builder setExtraValue(Bundle bundle) {
            this.extraValue = bundle;
            return this;
        }

        public SheetItem build() {
            return new SheetItem(this);
        }
    }
}
