package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItem;

/* loaded from: classes2.dex */
public class AddressControl extends SheetControl implements Parcelable {
    public static final Parcelable.Creator<AddressControl> CREATOR = new Parcelable.Creator<AddressControl>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.AddressControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressControl createFromParcel(Parcel parcel) {
            parcel.readParcelable(SheetControl.Controltype.class.getClassLoader());
            return new AddressControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddressControl[] newArray(int i) {
            return new AddressControl[i];
        }
    };
    private static final String EXTRA_DISPLAY_OPTION = "extra_display_option";
    private static final String TAG = "AddressControl";
    private CustomSheetPaymentInfo.Address address;
    private int displayOption;
    private int errorCode;
    private SheetItem sheetItem;
    private SheetUpdatedListener sheetUpdatedListener;

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AddressControl(String str, SheetItemType sheetItemType) {
        super(SheetControl.Controltype.ADDRESS);
        setControlId(str);
        if (sheetItemType != null && (sheetItemType.equals(SheetItemType.BILLING_ADDRESS) || sheetItemType.equals(SheetItemType.SHIPPING_ADDRESS))) {
            this.sheetItem = new SheetItem.Builder().setSheetItemType(sheetItemType).setExtraValue(new Bundle()).build();
            return;
        }
        throw new IllegalArgumentException("AddressControl : sheetItemType must be either BILLING_ADDRESS or SHIPPING_ADDRESS.");
    }

    protected AddressControl(Parcel parcel) {
        super(parcel);
        this.errorCode = parcel.readInt();
        this.sheetItem = (SheetItem) parcel.readParcelable(SheetItem.class.getClassLoader());
        this.address = (CustomSheetPaymentInfo.Address) parcel.readParcelable(CustomSheetPaymentInfo.Address.class.getClassLoader());
        if (this.sheetItem.getExtraValue() != null) {
            this.displayOption = this.sheetItem.getExtraValue().getInt(EXTRA_DISPLAY_OPTION);
        }
        setControltype(SheetControl.Controltype.ADDRESS);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.errorCode);
        parcel.writeParcelable(this.sheetItem, i);
        parcel.writeParcelable(this.address, i);
    }

    public SheetUpdatedListener getSheetUpdatedListener() {
        return this.sheetUpdatedListener;
    }

    public void setSheetUpdatedListener(SheetUpdatedListener sheetUpdatedListener) {
        this.sheetUpdatedListener = sheetUpdatedListener;
    }

    public SheetItem getSheetItem() {
        return this.sheetItem;
    }

    public CustomSheetPaymentInfo.Address getAddress() {
        return this.address;
    }

    public void setAddress(CustomSheetPaymentInfo.Address address) {
        this.address = address;
    }

    public SheetItemType getAddressType() {
        return this.sheetItem.getSheetItemType();
    }

    public void setAddressTitle(String str) {
        if (str == null) {
            throw new NullPointerException("setAddressTitle : You must set title");
        }
        SheetItem sheetItem = this.sheetItem;
        this.sheetItem = new SheetItem.Builder().setId(sheetItem.getId()).setTitle(str).setSheetItemType(sheetItem.getSheetItemType()).setExtraValue(sheetItem.getExtraValue()).build();
    }

    public String getAddressTitle() {
        return this.sheetItem.getTitle();
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setDisplayOption(int i) {
        if (this.sheetItem.getSheetItemType() != SheetItemType.SHIPPING_ADDRESS) {
            throw new IllegalArgumentException("setDisplayOption : sheetItemType must be either SHIPPING_ADDRESS.");
        }
        this.displayOption = i;
        SheetItem sheetItem = this.sheetItem;
        Bundle bundle = new Bundle(sheetItem.getExtraValue());
        bundle.putInt(EXTRA_DISPLAY_OPTION, i);
        this.sheetItem = new SheetItem.Builder().setId(sheetItem.getId()).setTitle(sheetItem.getTitle()).setSheetItemType(sheetItem.getSheetItemType()).setExtraValue(bundle).build();
    }

    public int getDisplayOption() {
        return this.displayOption;
    }
}
