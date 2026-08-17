package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItem;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class AmountBoxControl extends SheetControl implements Parcelable {
    public static final Parcelable.Creator<AmountBoxControl> CREATOR = new Parcelable.Creator<AmountBoxControl>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountBoxControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AmountBoxControl createFromParcel(Parcel parcel) {
            parcel.readParcelable(SheetControl.Controltype.class.getClassLoader());
            return new AmountBoxControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AmountBoxControl[] newArray(int i) {
            return new AmountBoxControl[i];
        }
    };
    private static final String TAG = "AmountBoxControl";
    private String currencyCode;
    private List<SheetItem> items;

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AmountBoxControl(String str, String str2) {
        super(SheetControl.Controltype.AMOUNTBOX);
        this.items = new ArrayList();
        setControlId(str);
        setCurrencyCode(str2);
    }

    protected AmountBoxControl(Parcel parcel) {
        super(parcel);
        this.items = new ArrayList();
        this.items = parcel.createTypedArrayList(SheetItem.CREATOR);
        this.currencyCode = parcel.readString();
        setControltype(SheetControl.Controltype.AMOUNTBOX);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.items);
        parcel.writeString(this.currencyCode);
    }

    public List<SheetItem> getItems() {
        return this.items;
    }

    public double getValue(String str) {
        if (str == null) {
            throw new NullPointerException("You must set itemId.");
        }
        int itemIndex = getItemIndex(str);
        if (itemIndex > -1) {
            return this.items.get(itemIndex).getDValue();
        }
        throw new IllegalArgumentException("id is invalid.");
    }

    private int getItemIndex(String str) {
        for (int i = 0; i < this.items.size(); i++) {
            if (str.equals(this.items.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    private int getAmountTotalIndex() {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getSheetItemType() == SheetItemType.AMOUNT_TOTAL) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasAmountTotal() {
        return getAmountTotalIndex() > -1;
    }

    public boolean existItem(String str) {
        if (str != null) {
            return getItemIndex(str) > -1;
        }
        throw new NullPointerException("You must set id.");
    }

    public void addItem(String str, String str2, double d, String str3) {
        if (hasAmountTotal()) {
            addItem(this.items.size() - 1, str, str2, d, str3);
        } else {
            addItem(this.items.size(), str, str2, d, str3);
        }
    }

    public void addItem(int i, String str, String str2, double d, String str3) {
        if (str == null) {
            throw new NullPointerException("addItem : You must set itemId.");
        }
        if (str2 == null) {
            throw new NullPointerException("addItem : You must set title.");
        }
        if (i < 0 || ((hasAmountTotal() && i >= this.items.size()) || (!hasAmountTotal() && i > this.items.size()))) {
            throw new IllegalArgumentException("addItem : there is abnormal location");
        }
        if (getItemIndex(str) > -1) {
            throw new IllegalArgumentException("addItem : same id is used.");
        }
        this.items.add(i, new SheetItem.Builder().setId(str).setTitle(str2).setDValue(d).setSValue(str3).setSheetItemType(SheetItemType.AMOUNT_ITEM).build());
    }

    public void setAmountTotal(double d, String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("setAmountTotal : You must set displayOption.");
        }
        int amountTotalIndex = getAmountTotalIndex();
        SheetItem build = new SheetItem.Builder().setId("").setTitle("").setDValue(d).setSValue(str).setSheetItemType(SheetItemType.AMOUNT_TOTAL).build();
        if (amountTotalIndex > -1) {
            this.items.set(amountTotalIndex, build);
        } else {
            this.items.add(build);
        }
    }

    public void removeItem(String str) {
        if (str == null) {
            throw new NullPointerException("removeItem : You must set itemId.");
        }
        int itemIndex = getItemIndex(str);
        if (itemIndex < 0) {
            throw new IllegalArgumentException("removeItem : there is no id.");
        }
        this.items.remove(itemIndex);
    }

    public void updateValue(String str, double d) {
        if (str == null) {
            throw new NullPointerException("updateValue : You must set id.");
        }
        int itemIndex = getItemIndex(str);
        if (itemIndex > -1) {
            this.items.set(itemIndex, new SheetItem.Builder().setId(str).setTitle(this.items.get(itemIndex).getTitle()).setDValue(d).setSValue("").setSheetItemType(this.items.get(itemIndex).getSheetItemType()).build());
            return;
        }
        throw new IllegalArgumentException("updateValue : there are no items associated with the id.");
    }

    public void updateValue(String str, double d, String str2) {
        if (str == null) {
            throw new NullPointerException("updateValue : You must set id.");
        }
        int itemIndex = getItemIndex(str);
        if (itemIndex > -1) {
            this.items.set(itemIndex, new SheetItem.Builder().setId(str).setTitle(this.items.get(itemIndex).getTitle()).setDValue(d).setSValue(str2).setSheetItemType(this.items.get(itemIndex).getSheetItemType()).build());
            return;
        }
        throw new IllegalArgumentException("updateValue : there are no items associated with the id.");
    }

    public void updateTitle(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("You must set itemId.");
        }
        int itemIndex = getItemIndex(str);
        if (itemIndex > -1) {
            this.items.set(itemIndex, new SheetItem.Builder().setId(str).setTitle(str2).setDValue(this.items.get(itemIndex).getDValue()).setSValue(this.items.get(itemIndex).getSValue()).setSheetItemType(this.items.get(itemIndex).getSheetItemType()).build());
            return;
        }
        throw new IllegalArgumentException("updateTitle : there are no items associated with the id.");
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        if (str == null) {
            throw new NullPointerException("currencyCode is null");
        }
        Iterator<Currency> it = Currency.getAvailableCurrencies().iterator();
        while (it.hasNext()) {
            if (it.next().getCurrencyCode().equals(str)) {
                this.currencyCode = str;
                return;
            }
            continue;
        }
        throw new IllegalArgumentException(str + " is invalid currencyCode.");
    }

    public String toString() {
        return "AmountBoxControl{, items=" + this.items + ", currencyCode='" + this.currencyCode + "'}";
    }
}
