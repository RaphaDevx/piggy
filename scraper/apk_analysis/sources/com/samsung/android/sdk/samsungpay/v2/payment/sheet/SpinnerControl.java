package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SpinnerControl extends SheetControl implements Parcelable {
    public static final Parcelable.Creator<SpinnerControl> CREATOR = new Parcelable.Creator<SpinnerControl>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.SpinnerControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpinnerControl createFromParcel(Parcel parcel) {
            parcel.readParcelable(SheetControl.Controltype.class.getClassLoader());
            return new SpinnerControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpinnerControl[] newArray(int i) {
            return new SpinnerControl[i];
        }
    };
    private static final String TAG = "SpinnerControl";
    private List<SheetItem> items;
    private String selectedItemId;
    private SheetUpdatedListener sheetUpdatedListener;

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SpinnerControl(String str, String str2, SheetItemType sheetItemType) {
        super(SheetControl.Controltype.SPINNER);
        this.items = new ArrayList();
        setControlId(str);
        if (str2 == null) {
            throw new NullPointerException("SpinnerControl : You must set title.");
        }
        if (sheetItemType != SheetItemType.SHIPPING_METHOD_SPINNER && sheetItemType != SheetItemType.INSTALLMENT_SPINNER) {
            throw new IllegalArgumentException("SpinnerControl : sheetItemType must be either SHIPPING_METHOD_SPINNER or INSTALLMENT_SPINNER.");
        }
        this.items.add(new SheetItem.Builder().setId("").setTitle(str2).setSheetItemType(sheetItemType).build());
    }

    protected SpinnerControl(Parcel parcel) {
        super(parcel);
        this.items = new ArrayList();
        this.items = parcel.createTypedArrayList(SheetItem.CREATOR);
        this.selectedItemId = parcel.readString();
        setControltype(SheetControl.Controltype.SPINNER);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.items);
        parcel.writeString(this.selectedItemId);
    }

    public SheetUpdatedListener getSheetUpdatedListener() {
        return this.sheetUpdatedListener;
    }

    public void setSheetUpdatedListener(SheetUpdatedListener sheetUpdatedListener) {
        this.sheetUpdatedListener = sheetUpdatedListener;
    }

    public String getTitle() {
        return this.items.get(0).getTitle();
    }

    public void setTitle(String str) {
        if (str == null) {
            throw new NullPointerException("setTitle : You must set spinner title");
        }
        this.items.set(0, new SheetItem.Builder().setId("").setTitle(str).setSheetItemType(this.items.get(0).getSheetItemType()).build());
    }

    public String getSelectedItemId() {
        return this.selectedItemId;
    }

    public void setSelectedItemId(String str) {
        if (!existItem(str)) {
            throw new IllegalArgumentException("setSelectedItemId : There is no item associated with the ID.");
        }
        this.selectedItemId = str;
    }

    public List<SheetItem> getItems() {
        return this.items;
    }

    public void addItem(String str, String str2) {
        addItem(this.items.size() - 1, str, str2);
    }

    public void addItem(int i, String str, String str2) {
        if (existItem(str)) {
            throw new IllegalArgumentException("addItem : same ID is used.");
        }
        if (str2 == null) {
            throw new NullPointerException("addItem : You must set value.");
        }
        if (i < 0 || i > this.items.size() - 1) {
            throw new IllegalArgumentException("addItem : location is abnormal.");
        }
        this.items.add(i + 1, new SheetItem.Builder().setId(str).setSValue(str2).build());
    }

    public void removeItem(String str) {
        if (str == null) {
            throw new NullPointerException("removeItem : You must set id.");
        }
        int index = getIndex(1, str);
        if (index < 0) {
            throw new IllegalArgumentException("removeItem : There is no item associated with the ID.");
        }
        this.items.remove(index);
    }

    public void updateItem(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("updateItem : You must set ID.");
        }
        if (str2 == null) {
            throw new NullPointerException("addItem : You must set value.");
        }
        int index = getIndex(1, str);
        if (index > -1) {
            this.items.set(index, new SheetItem.Builder().setId(str).setSValue(str2).build());
            return;
        }
        throw new IllegalArgumentException("updateItem : There is no item associated with the ID.");
    }

    public boolean existItem(String str) {
        if (str != null) {
            return getIndex(1, str) > -1;
        }
        throw new NullPointerException("existItem : You must set ID.");
    }

    private int getIndex(int i, String str) {
        while (i < this.items.size()) {
            if (str.equals(this.items.get(i).getId())) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
