package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AddressControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountBoxControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItemType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class CustomSheetPaymentInfo implements Parcelable {
    public static final Parcelable.Creator<CustomSheetPaymentInfo> CREATOR = new Parcelable.Creator<CustomSheetPaymentInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomSheetPaymentInfo createFromParcel(Parcel parcel) {
            return new CustomSheetPaymentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomSheetPaymentInfo[] newArray(int i) {
            return new CustomSheetPaymentInfo[i];
        }
    };
    private AddressInPaymentSheet addressInPaymentSheet;
    private List<SpaySdk.Brand> allowedCardBrand;
    private CardInfo cardInfo;
    private CustomSheet customSheet;
    private Bundle extraPaymentInfo;
    private boolean isCardHolderNameRequired;
    private boolean isRecurring;
    private String merchantCountryCode;
    private String merchantId;
    private String merchantName;
    private String orderNumber;
    private PaymentProtocol paymentProtocol;
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CustomSheetPaymentInfo() {
        this.addressInPaymentSheet = AddressInPaymentSheet.DO_NOT_SHOW;
        this.isCardHolderNameRequired = false;
        this.isRecurring = false;
    }

    private CustomSheetPaymentInfo(Builder builder) {
        this.addressInPaymentSheet = AddressInPaymentSheet.DO_NOT_SHOW;
        this.isCardHolderNameRequired = false;
        this.isRecurring = false;
        this.version = SpaySdk.getVersionName();
        this.merchantId = builder.merchantId;
        this.merchantName = builder.merchantName;
        this.orderNumber = builder.orderNumber;
        this.paymentProtocol = builder.paymentProtocol;
        this.addressInPaymentSheet = builder.addressInPaymentSheet != null ? builder.addressInPaymentSheet : AddressInPaymentSheet.DO_NOT_SHOW;
        this.allowedCardBrand = builder.allowedCardBrand;
        this.cardInfo = builder.cardInfo;
        this.isCardHolderNameRequired = builder.isCardHolderNameRequired;
        this.isRecurring = builder.isRecurring;
        this.merchantCountryCode = builder.merchantCountryCode;
        this.customSheet = builder.customSheet;
        this.extraPaymentInfo = builder.extraPaymentInfo;
    }

    public CustomSheetPaymentInfo(Parcel parcel) {
        this.addressInPaymentSheet = AddressInPaymentSheet.DO_NOT_SHOW;
        this.isCardHolderNameRequired = false;
        this.isRecurring = false;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.version = (String) parcel.readValue(String.class.getClassLoader());
        this.merchantId = (String) parcel.readValue(String.class.getClassLoader());
        this.merchantName = (String) parcel.readValue(String.class.getClassLoader());
        this.orderNumber = (String) parcel.readValue(String.class.getClassLoader());
        this.paymentProtocol = (PaymentProtocol) parcel.readValue(PaymentProtocol.class.getClassLoader());
        this.addressInPaymentSheet = (AddressInPaymentSheet) parcel.readValue(AddressInPaymentSheet.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        this.allowedCardBrand = arrayList;
        parcel.readTypedList(arrayList, SpaySdk.Brand.CREATOR);
        this.cardInfo = (CardInfo) parcel.readValue(CardInfo.class.getClassLoader());
        this.isCardHolderNameRequired = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.isRecurring = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.merchantCountryCode = (String) parcel.readValue(String.class.getClassLoader());
        this.customSheet = (CustomSheet) parcel.readParcelable(CustomSheet.class.getClassLoader());
        this.extraPaymentInfo = parcel.readBundle();
    }

    public String getVersion() {
        return this.version;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    @Deprecated
    public PaymentProtocol getPaymentProtocol() {
        return this.paymentProtocol;
    }

    public AddressInPaymentSheet getAddressInPaymentSheet() {
        return this.addressInPaymentSheet;
    }

    public List<SpaySdk.Brand> getAllowedCardBrands() {
        return this.allowedCardBrand;
    }

    public CardInfo getCardInfo() {
        return this.cardInfo;
    }

    public boolean getIsCardHolderNameRequired() {
        return this.isCardHolderNameRequired;
    }

    public boolean getIsRecurring() {
        return this.isRecurring;
    }

    public String getMerchantCountryCode() {
        return this.merchantCountryCode;
    }

    public Bundle getExtraPaymentInfo() {
        return this.extraPaymentInfo;
    }

    public CustomSheet getCustomSheet() {
        return this.customSheet;
    }

    public boolean isEnableEnforcePaymentSheet() {
        nullCheckExtraPaymentInfo();
        return this.extraPaymentInfo.getBoolean(PaymentManager.EXTRA_ENFORCE_PAYMENT_SHEET, false);
    }

    @Deprecated
    public boolean isFastCheckout() {
        nullCheckExtraPaymentInfo();
        return this.extraPaymentInfo.getBoolean(PaymentManager.EXTRA_FCO_STATUS, false);
    }

    public String getPaymentCardLast4DPAN() {
        nullCheckExtraPaymentInfo();
        return this.extraPaymentInfo.getString(SpaySdk.EXTRA_LAST4_DPAN, "");
    }

    public String getPaymentCardLast4FPAN() {
        nullCheckExtraPaymentInfo();
        return this.extraPaymentInfo.getString(SpaySdk.EXTRA_LAST4_FPAN, "");
    }

    public SpaySdk.Brand getPaymentCardBrand() {
        nullCheckExtraPaymentInfo();
        return SpaySdk.Brand.valueOf(this.extraPaymentInfo.getString(SpaySdk.EXTRA_CARD_BRAND, "UNKNOWN_CARD"));
    }

    public String getPaymentCurrencyCode() {
        CustomSheet customSheet = this.customSheet;
        if (customSheet != null) {
            for (SheetControl sheetControl : customSheet.getSheetControls()) {
                if (sheetControl.getControltype() == SheetControl.Controltype.AMOUNTBOX) {
                    return ((AmountBoxControl) sheetControl).getCurrencyCode();
                }
            }
            return "";
        }
        return "";
    }

    public Address getPaymentShippingAddress() {
        CustomSheet customSheet = this.customSheet;
        if (customSheet != null) {
            for (SheetControl sheetControl : customSheet.getSheetControls()) {
                if (sheetControl.getControltype() == SheetControl.Controltype.ADDRESS) {
                    AddressControl addressControl = (AddressControl) sheetControl;
                    if (addressControl.getAddressType() == SheetItemType.SHIPPING_ADDRESS) {
                        return addressControl.getAddress();
                    }
                }
            }
        }
        return new Address.Builder().build();
    }

    public String getPaymentShippingMethod() {
        nullCheckExtraPaymentInfo();
        return this.extraPaymentInfo.getString(PaymentManager.EXTRA_SHIPPING_METHOD, "");
    }

    private void nullCheckExtraPaymentInfo() {
        if (this.extraPaymentInfo == null) {
            this.extraPaymentInfo = new Bundle();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.version);
        parcel.writeValue(this.merchantId);
        parcel.writeValue(this.merchantName);
        parcel.writeValue(this.orderNumber);
        parcel.writeValue(this.paymentProtocol);
        parcel.writeValue(this.addressInPaymentSheet);
        parcel.writeTypedList(this.allowedCardBrand);
        parcel.writeValue(this.cardInfo);
        parcel.writeValue(Boolean.valueOf(this.isCardHolderNameRequired));
        parcel.writeValue(Boolean.valueOf(this.isRecurring));
        parcel.writeValue(this.merchantCountryCode);
        parcel.writeParcelable(this.customSheet, i);
        parcel.writeBundle(this.extraPaymentInfo);
    }

    public static class Builder {
        private AddressInPaymentSheet addressInPaymentSheet;
        private List<SpaySdk.Brand> allowedCardBrand = new ArrayList();
        private CardInfo cardInfo;
        private CustomSheet customSheet;
        private Bundle extraPaymentInfo;
        private boolean isCardHolderNameRequired;
        private boolean isRecurring;
        private String merchantCountryCode;
        private String merchantId;
        private String merchantName;
        private String orderNumber;
        private PaymentProtocol paymentProtocol;

        public Builder setMerchantId(String str) {
            this.merchantId = str;
            return this;
        }

        public Builder setMerchantName(String str) {
            this.merchantName = str;
            return this;
        }

        public Builder setOrderNumber(String str) {
            this.orderNumber = str;
            return this;
        }

        @Deprecated
        public Builder setPaymentProtocol(PaymentProtocol paymentProtocol) {
            this.paymentProtocol = paymentProtocol;
            return this;
        }

        public Builder setAddressInPaymentSheet(AddressInPaymentSheet addressInPaymentSheet) {
            this.addressInPaymentSheet = addressInPaymentSheet;
            return this;
        }

        public Builder setAllowedCardBrands(List<SpaySdk.Brand> list) {
            this.allowedCardBrand = list;
            return this;
        }

        public Builder setCardInfo(CardInfo cardInfo) {
            this.cardInfo = cardInfo;
            return this;
        }

        public Builder setCardHolderNameEnabled(boolean z) {
            this.isCardHolderNameRequired = z;
            return this;
        }

        public Builder setRecurringEnabled(boolean z) {
            this.isRecurring = z;
            return this;
        }

        public Builder setMerchantCountryCode(String str) {
            if (str != null && str.isEmpty()) {
                throw new IllegalArgumentException("merchant country code is empty.");
            }
            for (Locale locale : Locale.getAvailableLocales()) {
                if (locale.getCountry().equals(str)) {
                    this.merchantCountryCode = locale.getCountry();
                    return this;
                }
                continue;
            }
            throw new IllegalArgumentException(str + " is invalid merchant country code.");
        }

        public Builder setCustomSheet(CustomSheet customSheet) {
            this.customSheet = customSheet;
            return this;
        }

        public Builder setExtraPaymentInfo(Bundle bundle) {
            this.extraPaymentInfo = bundle;
            return this;
        }

        public Builder enableEnforcePaymentSheet() {
            nullCheckExtraPaymentInfo();
            this.extraPaymentInfo.putBoolean(PaymentManager.EXTRA_ENFORCE_PAYMENT_SHEET, true);
            return this;
        }

        @Deprecated
        public Builder setFastCheckoutStatus(boolean z) {
            nullCheckExtraPaymentInfo();
            this.extraPaymentInfo.putBoolean(PaymentManager.EXTRA_FCO_STATUS, z);
            return this;
        }

        public Builder setPaymentCardLast4DPAN(String str) {
            nullCheckExtraPaymentInfo();
            this.extraPaymentInfo.putString(SpaySdk.EXTRA_LAST4_DPAN, str);
            return this;
        }

        public Builder setPaymentCardLast4FPAN(String str) {
            nullCheckExtraPaymentInfo();
            this.extraPaymentInfo.putString(SpaySdk.EXTRA_LAST4_FPAN, str);
            return this;
        }

        public Builder setPaymentCardBrand(SpaySdk.Brand brand) {
            nullCheckExtraPaymentInfo();
            this.extraPaymentInfo.putString(SpaySdk.EXTRA_CARD_BRAND, brand.name());
            return this;
        }

        public Builder setPaymentShippingMethod(String str) {
            nullCheckExtraPaymentInfo();
            this.extraPaymentInfo.putString(PaymentManager.EXTRA_SHIPPING_METHOD, str);
            return this;
        }

        private void nullCheckExtraPaymentInfo() {
            if (this.extraPaymentInfo == null) {
                this.extraPaymentInfo = new Bundle();
            }
        }

        public CustomSheetPaymentInfo build() {
            return new CustomSheetPaymentInfo(this);
        }
    }

    public static class Address implements Parcelable {
        public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Address createFromParcel(Parcel parcel) {
                return new Address(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Address[] newArray(int i) {
                return new Address[i];
            }
        };
        protected static final String EXTRA_EMAIL_ADDRESS = "emailAddress";
        protected static final String KEY_ADDRESSEE = "addressee";
        protected static final String KEY_ADDRESS_LINE1 = "addressLine1";
        protected static final String KEY_ADDRESS_LINE2 = "addressLine2";
        protected static final String KEY_CITY = "city";
        protected static final String KEY_COUNTRY_CODE = "countryCode";
        protected static final String KEY_EXTRA_INFO = "extraAddressInfo";
        protected static final String KEY_PHONE_NUMBER = "phoneNumber";
        protected static final String KEY_POSTAL_CODE = "postalCode";
        protected static final String KEY_STATE = "state";
        protected static final String KEY_VERSION = "version";
        protected String addressLine1;
        protected String addressLine2;
        protected String addressee;
        protected String city;
        protected String countryCode;
        protected String email;
        protected Bundle extraAddressInfo;
        protected String phoneNumber;
        protected String postalCode;
        protected String state;
        protected String version;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected Address() {
        }

        private Address(Builder builder) {
            this.version = SpaySdk.getVersionName();
            this.addressee = builder.addressee;
            this.addressLine1 = builder.addressLine1;
            this.addressLine2 = builder.addressLine2;
            this.city = builder.city;
            this.state = builder.state;
            this.countryCode = builder.countryCode;
            this.postalCode = builder.postalCode;
            this.phoneNumber = builder.phoneNumber;
            this.extraAddressInfo = builder.extraAddressInfo;
            this.email = builder.email;
        }

        public Address(Parcel parcel) {
            this.version = (String) parcel.readValue(String.class.getClassLoader());
            this.addressee = (String) parcel.readValue(String.class.getClassLoader());
            this.addressLine1 = (String) parcel.readValue(String.class.getClassLoader());
            this.addressLine2 = (String) parcel.readValue(String.class.getClassLoader());
            this.city = (String) parcel.readValue(String.class.getClassLoader());
            this.state = (String) parcel.readValue(String.class.getClassLoader());
            this.countryCode = (String) parcel.readValue(String.class.getClassLoader());
            this.postalCode = (String) parcel.readValue(String.class.getClassLoader());
            this.phoneNumber = (String) parcel.readValue(String.class.getClassLoader());
            Bundle readBundle = parcel.readBundle();
            this.extraAddressInfo = readBundle;
            if (readBundle != null) {
                this.email = readBundle.getString("emailAddress");
            }
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("version", this.version);
            bundle.putString(KEY_ADDRESSEE, this.addressee);
            bundle.putString(KEY_ADDRESS_LINE1, this.addressLine1);
            bundle.putString(KEY_ADDRESS_LINE2, this.addressLine2);
            bundle.putString("city", this.city);
            bundle.putString("state", this.state);
            bundle.putString("countryCode", this.countryCode);
            bundle.putString("postalCode", this.postalCode);
            bundle.putString("phoneNumber", this.phoneNumber);
            if (this.extraAddressInfo == null) {
                this.extraAddressInfo = new Bundle();
            }
            this.extraAddressInfo.putString("emailAddress", this.email);
            bundle.putBundle(KEY_EXTRA_INFO, this.extraAddressInfo);
            return bundle;
        }

        public String getAddressee() {
            return this.addressee;
        }

        public String getAddressLine1() {
            return this.addressLine1;
        }

        public String getAddressLine2() {
            return this.addressLine2;
        }

        public String getCity() {
            return this.city;
        }

        public String getState() {
            return this.state;
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public String getPostalCode() {
            return this.postalCode;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public Bundle getExtraAddressInfo() {
            return this.extraAddressInfo;
        }

        public String getEmail() {
            return this.email;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(this.version);
            parcel.writeValue(this.addressee);
            parcel.writeValue(this.addressLine1);
            parcel.writeValue(this.addressLine2);
            parcel.writeValue(this.city);
            parcel.writeValue(this.state);
            parcel.writeValue(this.countryCode);
            parcel.writeValue(this.postalCode);
            parcel.writeValue(this.phoneNumber);
            parcel.writeBundle(this.extraAddressInfo);
        }

        public static class Builder {
            protected String addressLine1;
            protected String addressLine2;
            protected String addressee;
            protected String city;
            protected String countryCode;
            protected String email;
            protected Bundle extraAddressInfo;
            protected String phoneNumber;
            protected String postalCode;
            protected String state;

            public Builder() {
            }

            public Builder(Bundle bundle) {
                this.addressee = bundle.getString(Address.KEY_ADDRESSEE);
                this.addressLine1 = bundle.getString(Address.KEY_ADDRESS_LINE1);
                this.addressLine2 = bundle.getString(Address.KEY_ADDRESS_LINE2);
                this.city = bundle.getString("city");
                this.state = bundle.getString("state");
                this.countryCode = bundle.getString("countryCode");
                this.postalCode = bundle.getString("postalCode");
                this.phoneNumber = bundle.getString("phoneNumber");
                Bundle bundle2 = bundle.getBundle(Address.KEY_EXTRA_INFO);
                this.extraAddressInfo = bundle2;
                if (bundle2 != null) {
                    this.email = bundle2.getString("emailAddress");
                }
            }

            public Builder setAddressee(String str) {
                this.addressee = str;
                return this;
            }

            public Builder setAddressLine1(String str) {
                this.addressLine1 = str;
                return this;
            }

            public Builder setAddressLine2(String str) {
                this.addressLine2 = str;
                return this;
            }

            public Builder setCity(String str) {
                this.city = str;
                return this;
            }

            public Builder setState(String str) {
                this.state = str;
                return this;
            }

            public Builder setCountryCode(String str) {
                if (str == null) {
                    throw new NullPointerException("country3DigitCode is null");
                }
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("country3DigitCode is empty");
                }
                for (String str2 : Locale.getISOCountries()) {
                    if (new Locale("", str2).getISO3Country().equals(str)) {
                        this.countryCode = str;
                        return this;
                    }
                    continue;
                }
                throw new IllegalArgumentException(this.countryCode + "countryCode is invalid ");
            }

            public Builder setPostalCode(String str) {
                this.postalCode = str;
                return this;
            }

            public Builder setPhoneNumber(String str) {
                this.phoneNumber = str;
                return this;
            }

            public Builder setExtraAddressInfo(Bundle bundle) {
                this.extraAddressInfo = bundle;
                return this;
            }

            public Builder setEmail(String str) {
                this.email = str;
                if (this.extraAddressInfo == null) {
                    this.extraAddressInfo = new Bundle();
                }
                this.extraAddressInfo.putString("emailAddress", str);
                return this;
            }

            public Address build() {
                return new Address(this);
            }
        }
    }

    @Deprecated
    public enum PaymentProtocol implements Parcelable {
        PROTOCOL_3DS,
        PROTOCOL_EMV,
        PROTOCOL_COF,
        PROTOCOL_OTHER;

        public static final Parcelable.Creator<PaymentProtocol> CREATOR = new Parcelable.Creator<PaymentProtocol>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.PaymentProtocol.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PaymentProtocol createFromParcel(Parcel parcel) {
                return PaymentProtocol.values()[parcel.readInt()];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PaymentProtocol[] newArray(int i) {
                return new PaymentProtocol[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static PaymentProtocol convert(String str) {
            String upperCase = str.toUpperCase(Locale.US);
            if (upperCase.contains("3DS")) {
                return PROTOCOL_3DS;
            }
            if (upperCase.contains("EMV")) {
                return PROTOCOL_EMV;
            }
            return PROTOCOL_OTHER;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public enum AddressInPaymentSheet implements Parcelable {
        DO_NOT_SHOW,
        NEED_BILLING_SPAY,
        NEED_SHIPPING_SPAY,
        SEND_SHIPPING,
        NEED_BILLING_SEND_SHIPPING,
        NEED_BILLING_AND_SHIPPING;

        public static final Parcelable.Creator<AddressInPaymentSheet> CREATOR = new Parcelable.Creator<AddressInPaymentSheet>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.AddressInPaymentSheet.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AddressInPaymentSheet[] newArray(int i) {
                return null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AddressInPaymentSheet createFromParcel(Parcel parcel) {
                return AddressInPaymentSheet.values()[parcel.readInt()];
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
