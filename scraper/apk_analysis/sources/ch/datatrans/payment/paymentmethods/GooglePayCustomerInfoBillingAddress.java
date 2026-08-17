package ch.datatrans.payment.paymentmethods;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.N7;
import com.fasterxml.jackson.core.io.doubleparser.FastDoubleMath;
import com.google.gson.annotations.SerializedName;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b#\b\u0086\b\u0018\u00002\u00020\u0001B\u0081\u0001\b\u0000\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0010J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0010J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0010J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0010J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0010J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0010J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0010J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0010J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0010J\u0088\u0001\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0010J\u0010\u0010\u001e\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\"\u0010#R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0010\"\u0004\b'\u0010(R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b)\u0010%\u001a\u0004\b*\u0010\u0010\"\u0004\b+\u0010(R$\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b,\u0010%\u001a\u0004\b-\u0010\u0010\"\u0004\b.\u0010(R$\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b/\u0010%\u001a\u0004\b0\u0010\u0010\"\u0004\b1\u0010(R$\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b2\u0010%\u001a\u0004\b3\u0010\u0010\"\u0004\b4\u0010(R$\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b5\u0010%\u001a\u0004\b6\u0010\u0010\"\u0004\b7\u0010(R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b8\u0010%\u001a\u0004\b9\u0010\u0010\"\u0004\b:\u0010(R$\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b;\u0010%\u001a\u0004\b<\u0010\u0010\"\u0004\b=\u0010(R$\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b>\u0010%\u001a\u0004\b?\u0010\u0010\"\u0004\b@\u0010(R$\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bA\u0010%\u001a\u0004\bB\u0010\u0010\"\u0004\bC\u0010(¨\u0006D"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;", "", "", HintConstants.AUTOFILL_HINT_PHONE_NUMBER, "address3", "sortingCode", "address2", SpaySdk.EXTRA_COUNTRY_CODE, "address1", HintConstants.AUTOFILL_HINT_POSTAL_CODE, "name", "locality", "administrativeArea", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getPhoneNumber", "setPhoneNumber", "(Ljava/lang/String;)V", "b", "getAddress3", "setAddress3", "c", "getSortingCode", "setSortingCode", "d", "getAddress2", "setAddress2", "e", "getCountryCode", "setCountryCode", "f", "getAddress1", "setAddress1", "g", "getPostalCode", "setPostalCode", "h", "getName", "setName", "i", "getLocality", "setLocality", "j", "getAdministrativeArea", "setAdministrativeArea", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class GooglePayCustomerInfoBillingAddress {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName(HintConstants.AUTOFILL_HINT_PHONE_NUMBER)
    private String phoneNumber;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("address3")
    private String address3;

    /* renamed from: c, reason: from kotlin metadata */
    @SerializedName("sortingCode")
    private String sortingCode;

    /* renamed from: d, reason: from kotlin metadata */
    @SerializedName("address2")
    private String address2;

    /* renamed from: e, reason: from kotlin metadata */
    @SerializedName(SpaySdk.EXTRA_COUNTRY_CODE)
    private String countryCode;

    /* renamed from: f, reason: from kotlin metadata */
    @SerializedName("address1")
    private String address1;

    /* renamed from: g, reason: from kotlin metadata */
    @SerializedName(HintConstants.AUTOFILL_HINT_POSTAL_CODE)
    private String postalCode;

    /* renamed from: h, reason: from kotlin metadata */
    @SerializedName("name")
    private String name;

    /* renamed from: i, reason: from kotlin metadata */
    @SerializedName("locality")
    private String locality;

    /* renamed from: j, reason: from kotlin metadata */
    @SerializedName("administrativeArea")
    private String administrativeArea;

    public GooglePayCustomerInfoBillingAddress() {
        this(null, null, null, null, null, null, null, null, null, null, FastDoubleMath.DOUBLE_EXPONENT_BIAS, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    /* renamed from: component10, reason: from getter */
    public final String getAdministrativeArea() {
        return this.administrativeArea;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAddress3() {
        return this.address3;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSortingCode() {
        return this.sortingCode;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAddress2() {
        return this.address2;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component6, reason: from getter */
    public final String getAddress1() {
        return this.address1;
    }

    /* renamed from: component7, reason: from getter */
    public final String getPostalCode() {
        return this.postalCode;
    }

    /* renamed from: component8, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component9, reason: from getter */
    public final String getLocality() {
        return this.locality;
    }

    public final GooglePayCustomerInfoBillingAddress copy(String phoneNumber, String address3, String sortingCode, String address2, String countryCode, String address1, String postalCode, String name, String locality, String administrativeArea) {
        return new GooglePayCustomerInfoBillingAddress(phoneNumber, address3, sortingCode, address2, countryCode, address1, postalCode, name, locality, administrativeArea);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GooglePayCustomerInfoBillingAddress)) {
            return false;
        }
        GooglePayCustomerInfoBillingAddress googlePayCustomerInfoBillingAddress = (GooglePayCustomerInfoBillingAddress) other;
        return Intrinsics.areEqual(this.phoneNumber, googlePayCustomerInfoBillingAddress.phoneNumber) && Intrinsics.areEqual(this.address3, googlePayCustomerInfoBillingAddress.address3) && Intrinsics.areEqual(this.sortingCode, googlePayCustomerInfoBillingAddress.sortingCode) && Intrinsics.areEqual(this.address2, googlePayCustomerInfoBillingAddress.address2) && Intrinsics.areEqual(this.countryCode, googlePayCustomerInfoBillingAddress.countryCode) && Intrinsics.areEqual(this.address1, googlePayCustomerInfoBillingAddress.address1) && Intrinsics.areEqual(this.postalCode, googlePayCustomerInfoBillingAddress.postalCode) && Intrinsics.areEqual(this.name, googlePayCustomerInfoBillingAddress.name) && Intrinsics.areEqual(this.locality, googlePayCustomerInfoBillingAddress.locality) && Intrinsics.areEqual(this.administrativeArea, googlePayCustomerInfoBillingAddress.administrativeArea);
    }

    public final String getAddress1() {
        return this.address1;
    }

    public final String getAddress2() {
        return this.address2;
    }

    public final String getAddress3() {
        return this.address3;
    }

    public final String getAdministrativeArea() {
        return this.administrativeArea;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final String getLocality() {
        return this.locality;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final String getPostalCode() {
        return this.postalCode;
    }

    public final String getSortingCode() {
        return this.sortingCode;
    }

    public int hashCode() {
        String str = this.phoneNumber;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.address3;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.sortingCode;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.address2;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.countryCode;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.address1;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.postalCode;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.name;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.locality;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.administrativeArea;
        return hashCode9 + (str10 != null ? str10.hashCode() : 0);
    }

    public final void setAddress1(String str) {
        this.address1 = str;
    }

    public final void setAddress2(String str) {
        this.address2 = str;
    }

    public final void setAddress3(String str) {
        this.address3 = str;
    }

    public final void setAdministrativeArea(String str) {
        this.administrativeArea = str;
    }

    public final void setCountryCode(String str) {
        this.countryCode = str;
    }

    public final void setLocality(String str) {
        this.locality = str;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public final void setPostalCode(String str) {
        this.postalCode = str;
    }

    public final void setSortingCode(String str) {
        this.sortingCode = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GooglePayCustomerInfoBillingAddress(phoneNumber=");
        sb.append(this.phoneNumber);
        sb.append(", address3=");
        sb.append(this.address3);
        sb.append(", sortingCode=");
        sb.append(this.sortingCode);
        sb.append(", address2=");
        sb.append(this.address2);
        sb.append(", countryCode=");
        sb.append(this.countryCode);
        sb.append(", address1=");
        sb.append(this.address1);
        sb.append(", postalCode=");
        sb.append(this.postalCode);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", locality=");
        sb.append(this.locality);
        sb.append(", administrativeArea=");
        return N7.a(sb, this.administrativeArea, ')');
    }

    public GooglePayCustomerInfoBillingAddress(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.phoneNumber = str;
        this.address3 = str2;
        this.sortingCode = str3;
        this.address2 = str4;
        this.countryCode = str5;
        this.address1 = str6;
        this.postalCode = str7;
        this.name = str8;
        this.locality = str9;
        this.administrativeArea = str10;
    }

    public /* synthetic */ GooglePayCustomerInfoBillingAddress(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : str9, (i & 512) != 0 ? null : str10);
    }
}
