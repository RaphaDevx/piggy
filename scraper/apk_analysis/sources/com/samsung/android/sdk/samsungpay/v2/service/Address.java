package com.samsung.android.sdk.samsungpay.v2.service;

import android.os.Bundle;
import android.os.Parcel;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo;

/* loaded from: classes2.dex */
public class Address extends CustomSheetPaymentInfo.Address {
    public Address(Parcel parcel) {
        super(parcel);
    }

    protected Address() {
    }

    public static class Builder extends CustomSheetPaymentInfo.Address.Builder {
        public Builder() {
        }

        public Builder(Bundle bundle) {
            super(bundle);
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setAddressee(String str) {
            super.setAddressee(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setAddressLine1(String str) {
            super.setAddressLine1(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setAddressLine2(String str) {
            super.setAddressLine2(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setCity(String str) {
            super.setCity(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setState(String str) {
            super.setState(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setCountryCode(String str) {
            super.setCountryCode(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setPostalCode(String str) {
            super.setPostalCode(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setPhoneNumber(String str) {
            super.setPhoneNumber(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setExtraAddressInfo(Bundle bundle) {
            super.setExtraAddressInfo(bundle);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Builder setEmail(String str) {
            super.setEmail(str);
            return this;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.CustomSheetPaymentInfo.Address.Builder
        public Address build() {
            Address address = new Address();
            address.version = SpaySdk.getVersionName();
            address.addressee = this.addressee;
            address.addressLine1 = this.addressLine1;
            address.addressLine2 = this.addressLine2;
            address.city = this.city;
            address.state = this.state;
            address.countryCode = this.countryCode;
            address.postalCode = this.postalCode;
            address.phoneNumber = this.phoneNumber;
            address.extraAddressInfo = this.extraAddressInfo;
            address.email = this.email;
            return address;
        }
    }
}
