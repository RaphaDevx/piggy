package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class CustomSheet implements Parcelable {
    public static final Parcelable.Creator<CustomSheet> CREATOR = new Parcelable.Creator<CustomSheet>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomSheet createFromParcel(Parcel parcel) {
            return new CustomSheet(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomSheet[] newArray(int i) {
            return new CustomSheet[i];
        }
    };
    private static final String TAG = "CustomSheet";
    private List<SheetControl> sheetControls;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CustomSheet() {
        this.sheetControls = new ArrayList();
    }

    protected CustomSheet(Parcel parcel) {
        this.sheetControls = new ArrayList();
        this.sheetControls = parcel.createTypedArrayList(SheetControl.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.sheetControls);
    }

    public void addControl(SheetControl sheetControl) {
        addControl(this.sheetControls.size(), sheetControl);
    }

    public void addControl(int i, SheetControl sheetControl) {
        if (sheetControl == null) {
            throw new NullPointerException("addControl : You must set sheetControl.");
        }
        if (i < 0 || i > this.sheetControls.size()) {
            throw new IllegalArgumentException("addItem : there is abnormal location.");
        }
        if (getSheetControl(sheetControl.getControlId()) != null) {
            throw new IllegalArgumentException("addControl : same id is used.");
        }
        if (sheetControl.getControltype() == SheetControl.Controltype.AMOUNTBOX) {
            AmountBoxControl amountBoxControl = (AmountBoxControl) sheetControl;
            int size = amountBoxControl.getItems().size() - 1;
            if (size < 0) {
                throw new IllegalArgumentException("addControl : No data in AmountBoxControl.");
            }
            if (!amountBoxControl.getItems().get(size).getSheetItemType().equals(SheetItemType.AMOUNT_TOTAL)) {
                throw new IllegalArgumentException("AMOUNT_TOTAL type must be the last item in AmountBoxControl.");
            }
        }
        this.sheetControls.add(i, sheetControl);
    }

    public boolean updateControl(SheetControl sheetControl) {
        if (sheetControl == null) {
            throw new NullPointerException("updateControl : You must set sheetControl.");
        }
        if (this.sheetControls != null) {
            for (int i = 0; i < this.sheetControls.size(); i++) {
                if (sheetControl.equals(this.sheetControls.get(i))) {
                    this.sheetControls.set(i, sheetControl);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeControl(SheetControl sheetControl) {
        if (sheetControl == null) {
            throw new NullPointerException("removeControl : You must set sheetControl.");
        }
        if (sheetControl.getControltype() == SheetControl.Controltype.AMOUNTBOX) {
            throw new IllegalArgumentException("AmountBoxControl must not be deleted.");
        }
        if (sheetControl.getControltype() == SheetControl.Controltype.ADDRESS) {
            throw new IllegalArgumentException("AddressControl must not be deleted.");
        }
        if (this.sheetControls != null) {
            for (int i = 0; i < this.sheetControls.size(); i++) {
                if (sheetControl.equals(this.sheetControls.get(i))) {
                    this.sheetControls.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public SheetControl getSheetControl(String str) {
        if (str == null) {
            throw new NullPointerException("getSheetControl : You must set controlId.");
        }
        for (SheetControl sheetControl : this.sheetControls) {
            if (str.equals(sheetControl.getControlId())) {
                return sheetControl;
            }
        }
        return null;
    }

    public List<SheetControl> getSheetControls() {
        return this.sheetControls;
    }
}
