package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Deprecated
/* loaded from: classes2.dex */
public class PaymentInfo implements Parcelable {
    public static final Parcelable.Creator<PaymentInfo> CREATOR = new Parcelable.Creator<PaymentInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PaymentInfo createFromParcel(Parcel parcel) {
            return new PaymentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PaymentInfo[] newArray(int i) {
            return new PaymentInfo[i];
        }
    };
    private AddressInPaymentSheet addressInPaymentSheet;
    private List<SpaySdk.Brand> allowedCardBrand;
    private Amount amount;
    private Address billingAddress;
    private SpaySdk.Brand cardBrand;
    private Bundle extraPaymentInfo;
    private boolean isCardHolderNameRequired;
    private boolean isGiftCardPurchase;
    private boolean isRecurring;
    private String merchantCountryCode;
    private String merchantId;
    private String merchantName;
    private String orderNumber;
    private PaymentProtocol paymentProtocol;
    private Address shippingAddress;
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private PaymentInfo() {
        this.addressInPaymentSheet = AddressInPaymentSheet.DO_NOT_SHOW;
        this.isGiftCardPurchase = false;
        this.isCardHolderNameRequired = false;
        this.isRecurring = false;
    }

    private PaymentInfo(Builder builder) {
        this.addressInPaymentSheet = AddressInPaymentSheet.DO_NOT_SHOW;
        this.isGiftCardPurchase = false;
        this.isCardHolderNameRequired = false;
        this.isRecurring = false;
        this.version = SpaySdk.getVersionName();
        this.amount = builder.amount;
        this.shippingAddress = builder.shippingAddress;
        this.billingAddress = builder.billingAddress;
        this.merchantId = builder.merchantId;
        this.merchantName = builder.merchantName;
        this.orderNumber = builder.orderNumber;
        this.paymentProtocol = builder.paymentProtocol;
        this.addressInPaymentSheet = builder.addressInPaymentSheet != null ? builder.addressInPaymentSheet : AddressInPaymentSheet.DO_NOT_SHOW;
        this.allowedCardBrand = builder.allowedCardBrand;
        this.isGiftCardPurchase = builder.isGiftCardPurchase;
        this.cardBrand = builder.cardBrand;
        this.isCardHolderNameRequired = builder.isCardHolderNameRequired;
        this.isRecurring = builder.isRecurring;
        this.merchantCountryCode = builder.merchantCountryCode;
        this.extraPaymentInfo = builder.extraPaymentInfo;
    }

    public PaymentInfo(Parcel parcel) {
        this.addressInPaymentSheet = AddressInPaymentSheet.DO_NOT_SHOW;
        this.isGiftCardPurchase = false;
        this.isCardHolderNameRequired = false;
        this.isRecurring = false;
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.version = (String) parcel.readValue(String.class.getClassLoader());
        this.amount = (Amount) parcel.readParcelable(Amount.class.getClassLoader());
        this.shippingAddress = (Address) parcel.readParcelable(Address.class.getClassLoader());
        this.billingAddress = (Address) parcel.readParcelable(Address.class.getClassLoader());
        this.merchantId = (String) parcel.readValue(String.class.getClassLoader());
        this.merchantName = (String) parcel.readValue(String.class.getClassLoader());
        this.orderNumber = (String) parcel.readValue(String.class.getClassLoader());
        this.paymentProtocol = (PaymentProtocol) parcel.readValue(PaymentProtocol.class.getClassLoader());
        this.addressInPaymentSheet = (AddressInPaymentSheet) parcel.readValue(AddressInPaymentSheet.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        this.allowedCardBrand = arrayList;
        parcel.readTypedList(arrayList, SpaySdk.Brand.CREATOR);
        this.isGiftCardPurchase = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.cardBrand = (SpaySdk.Brand) parcel.readValue(SpaySdk.Brand.class.getClassLoader());
        this.isCardHolderNameRequired = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.isRecurring = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.merchantCountryCode = (String) parcel.readValue(String.class.getClassLoader());
        this.extraPaymentInfo = parcel.readBundle();
    }

    public Amount getAmount() {
        return this.amount;
    }

    public Address getShippingAddress() {
        return this.shippingAddress;
    }

    public Address getBillingAddress() {
        return this.billingAddress;
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

    public PaymentProtocol getPaymentProtocol() {
        return this.paymentProtocol;
    }

    public AddressInPaymentSheet getAddressInPaymentSheet() {
        return this.addressInPaymentSheet;
    }

    public List<SpaySdk.Brand> getAllowedCardBrands() {
        return this.allowedCardBrand;
    }

    @Deprecated
    public SpaySdk.Brand getCardBrand() {
        return this.cardBrand;
    }

    public boolean getIsGiftCardPurchase() {
        return this.isGiftCardPurchase;
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
        return this.amount.currencyCode;
    }

    public Address getPaymentShippingAddress() {
        return this.shippingAddress;
    }

    private void nullCheckExtraPaymentInfo() {
        if (this.extraPaymentInfo == null) {
            this.extraPaymentInfo = new Bundle();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.version);
        parcel.writeParcelable(this.amount, 0);
        parcel.writeParcelable(this.shippingAddress, 0);
        parcel.writeParcelable(this.billingAddress, 0);
        parcel.writeValue(this.merchantId);
        parcel.writeValue(this.merchantName);
        parcel.writeValue(this.orderNumber);
        parcel.writeValue(this.paymentProtocol);
        parcel.writeValue(this.addressInPaymentSheet);
        parcel.writeTypedList(this.allowedCardBrand);
        parcel.writeValue(Boolean.valueOf(this.isGiftCardPurchase));
        parcel.writeValue(this.cardBrand);
        parcel.writeValue(Boolean.valueOf(this.isCardHolderNameRequired));
        parcel.writeValue(Boolean.valueOf(this.isRecurring));
        parcel.writeValue(this.merchantCountryCode);
        parcel.writeBundle(this.extraPaymentInfo);
    }

    @Deprecated
    public static class Builder {
        private AddressInPaymentSheet addressInPaymentSheet;
        private List<SpaySdk.Brand> allowedCardBrand = new ArrayList();
        private Amount amount;
        private Address billingAddress;
        private SpaySdk.Brand cardBrand;
        private Bundle extraPaymentInfo;
        private boolean isCardHolderNameRequired;
        private boolean isGiftCardPurchase;
        private boolean isRecurring;
        private String merchantCountryCode;
        private String merchantId;
        private String merchantName;
        private String orderNumber;
        private PaymentProtocol paymentProtocol;
        private Address shippingAddress;

        public Builder setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Builder setShippingAddress(Address address) {
            this.shippingAddress = address;
            return this;
        }

        public Builder setBillingAddress(Address address) {
            this.billingAddress = address;
            return this;
        }

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

        public Builder setIsGiftCardPurchase(boolean z) {
            this.isGiftCardPurchase = z;
            return this;
        }

        @Deprecated
        public Builder cardBrand(SpaySdk.Brand brand) {
            this.cardBrand = brand;
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

        private void nullCheckExtraPaymentInfo() {
            if (this.extraPaymentInfo == null) {
                this.extraPaymentInfo = new Bundle();
            }
        }

        public PaymentInfo build() {
            return new PaymentInfo(this);
        }
    }

    @Deprecated
    public static class Address implements Parcelable {
        public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo.Address.1
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
        private String addressLine1;
        private String addressLine2;
        private String addressee;
        private String city;
        private String countryCode;
        private String postalCode;
        private String state;
        private String version;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private Address() {
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
        }

        @Deprecated
        public static class Builder {
            private String addressLine1;
            private String addressLine2;
            private String addressee;
            private String city;
            private String countryCode;
            private String postalCode;
            private String state;

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
                    throw new NullPointerException("countryCode is null");
                }
                if (str.isEmpty()) {
                    throw new IllegalArgumentException("countryCode is empty");
                }
                for (Locale locale : Locale.getAvailableLocales()) {
                    if (locale.getDisplayCountry().equals(str)) {
                        this.countryCode = str;
                        return this;
                    }
                    continue;
                }
                throw new IllegalArgumentException(str + "countryCode is invalid ");
            }

            public Builder setPostalCode(String str) {
                this.postalCode = str;
                return this;
            }

            public Address build() {
                return new Address(this);
            }
        }
    }

    @Deprecated
    public static class Amount implements Parcelable {
        public static final Parcelable.Creator<Amount> CREATOR = new Parcelable.Creator<Amount>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo.Amount.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Amount createFromParcel(Parcel parcel) {
                return new Amount(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Amount[] newArray(int i) {
                return new Amount[i];
            }
        };
        private String currencyCode;
        private String itemTotalPrice;
        private String shippingPrice;
        private String tax;
        private String totalPrice;
        private String version;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private Amount() {
        }

        private Amount(Builder builder) {
            this.version = SpaySdk.getVersionName();
            this.currencyCode = builder.currencyCode;
            this.itemTotalPrice = builder.itemTotalPrice;
            this.tax = builder.tax;
            this.shippingPrice = builder.shippingPrice;
            this.totalPrice = builder.totalPrice;
        }

        public Amount(Parcel parcel) {
            this.version = (String) parcel.readValue(String.class.getClassLoader());
            this.currencyCode = (String) parcel.readValue(String.class.getClassLoader());
            this.itemTotalPrice = (String) parcel.readValue(String.class.getClassLoader());
            this.tax = (String) parcel.readValue(String.class.getClassLoader());
            this.shippingPrice = (String) parcel.readValue(String.class.getClassLoader());
            this.totalPrice = (String) parcel.readValue(String.class.getClassLoader());
        }

        public String getCurrencyCode() {
            return this.currencyCode;
        }

        public String getItemTotalPrice() {
            return this.itemTotalPrice;
        }

        public String getTax() {
            return this.tax;
        }

        public String getShippingPrice() {
            return this.shippingPrice;
        }

        public String getTotalPrice() {
            return this.totalPrice;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeValue(this.version);
            parcel.writeValue(this.currencyCode);
            parcel.writeValue(this.itemTotalPrice);
            parcel.writeValue(this.tax);
            parcel.writeValue(this.shippingPrice);
            parcel.writeValue(this.totalPrice);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isValid(String str) {
            if (str == null) {
                return false;
            }
            try {
                return Double.parseDouble(str) >= 0.0d;
            } catch (NumberFormatException unused) {
                return false;
            }
        }

        @Deprecated
        public static class Builder {
            private String currencyCode;
            private String itemTotalPrice;
            private String shippingPrice;
            private String tax;
            private String totalPrice;

            public Builder setCurrencyCode(String str) {
                if (str == null) {
                    throw new NullPointerException("currencyCode is null");
                }
                Iterator<Currency> it = Currency.getAvailableCurrencies().iterator();
                while (it.hasNext()) {
                    if (it.next().getCurrencyCode().equals(str)) {
                        this.currencyCode = str;
                        return this;
                    }
                    continue;
                }
                throw new IllegalArgumentException(str + " is invalid currencyCode.");
            }

            public Builder setItemTotalPrice(String str) {
                if (Amount.isValid(str)) {
                    this.itemTotalPrice = str;
                    return this;
                }
                throw new IllegalArgumentException(str + " is invalid itemTotalPrice");
            }

            public Builder setTax(String str) {
                if (Amount.isValid(str)) {
                    this.tax = str;
                    return this;
                }
                throw new IllegalArgumentException(str + " is invalid tax");
            }

            public Builder setShippingPrice(String str) {
                if (Amount.isValid(str)) {
                    this.shippingPrice = str;
                    return this;
                }
                throw new IllegalArgumentException(str + " is invalid shippingPrice");
            }

            public Builder setTotalPrice(String str) {
                if (!Amount.isValid(str)) {
                    throw new IllegalArgumentException(str + " is invalid totalPrice");
                }
                this.totalPrice = str;
                return this;
            }

            public Amount build() {
                return new Amount(this);
            }
        }
    }

    @Deprecated
    public enum PaymentProtocol implements Parcelable {
        PROTOCOL_3DS,
        PROTOCOL_EMV,
        PROTOCOL_COF,
        PROTOCOL_OTHER;

        public static final Parcelable.Creator<PaymentProtocol> CREATOR = new Parcelable.Creator<PaymentProtocol>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo.PaymentProtocol.1
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

    @Deprecated
    public enum AddressInPaymentSheet implements Parcelable {
        DO_NOT_SHOW,
        NEED_BILLING_SPAY,
        NEED_SHIPPING_SPAY,
        SEND_SHIPPING,
        NEED_BILLING_SEND_SHIPPING,
        NEED_BILLING_AND_SHIPPING;

        public static final Parcelable.Creator<AddressInPaymentSheet> CREATOR = new Parcelable.Creator<AddressInPaymentSheet>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo.AddressInPaymentSheet.1
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
