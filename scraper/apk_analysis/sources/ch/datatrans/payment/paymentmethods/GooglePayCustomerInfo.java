package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.N7;
import com.google.gson.annotations.SerializedName;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001BE\b\u0000\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015JL\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0015J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010#R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b$\u0010 \u001a\u0004\b%\u0010\u000e\"\u0004\b&\u0010#R$\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010+R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\u0013\"\u0004\b/\u00100R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u0010\u0015\"\u0004\b4\u00105¨\u00066"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfo;", "", "", "apiVersionMinor", "apiVersion", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;", "paymentMethodData", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoShippingAddress;", "shippingAddress", "", "email", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoShippingAddress;Ljava/lang/String;)V", "component1", "()Ljava/lang/Integer;", "component2", "component3", "()Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;", "component4", "()Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoShippingAddress;", "component5", "()Ljava/lang/String;", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoShippingAddress;Ljava/lang/String;)Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfo;", "toString", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Integer;", "getApiVersionMinor", "setApiVersionMinor", "(Ljava/lang/Integer;)V", "b", "getApiVersion", "setApiVersion", "c", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;", "getPaymentMethodData", "setPaymentMethodData", "(Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;)V", "d", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoShippingAddress;", "getShippingAddress", "setShippingAddress", "(Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoShippingAddress;)V", "e", "Ljava/lang/String;", "getEmail", "setEmail", "(Ljava/lang/String;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class GooglePayCustomerInfo {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("apiVersionMinor")
    private Integer apiVersionMinor;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("apiVersion")
    private Integer apiVersion;

    /* renamed from: c, reason: from kotlin metadata */
    @SerializedName("paymentMethodData")
    private GooglePayCustomerInfoPaymentMethodData paymentMethodData;

    /* renamed from: d, reason: from kotlin metadata */
    @SerializedName("shippingAddress")
    private GooglePayCustomerInfoShippingAddress shippingAddress;

    /* renamed from: e, reason: from kotlin metadata */
    @SerializedName("email")
    private String email;

    public GooglePayCustomerInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ GooglePayCustomerInfo copy$default(GooglePayCustomerInfo googlePayCustomerInfo, Integer num, Integer num2, GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData, GooglePayCustomerInfoShippingAddress googlePayCustomerInfoShippingAddress, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = googlePayCustomerInfo.apiVersionMinor;
        }
        if ((i & 2) != 0) {
            num2 = googlePayCustomerInfo.apiVersion;
        }
        Integer num3 = num2;
        if ((i & 4) != 0) {
            googlePayCustomerInfoPaymentMethodData = googlePayCustomerInfo.paymentMethodData;
        }
        GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData2 = googlePayCustomerInfoPaymentMethodData;
        if ((i & 8) != 0) {
            googlePayCustomerInfoShippingAddress = googlePayCustomerInfo.shippingAddress;
        }
        GooglePayCustomerInfoShippingAddress googlePayCustomerInfoShippingAddress2 = googlePayCustomerInfoShippingAddress;
        if ((i & 16) != 0) {
            str = googlePayCustomerInfo.email;
        }
        return googlePayCustomerInfo.copy(num, num3, googlePayCustomerInfoPaymentMethodData2, googlePayCustomerInfoShippingAddress2, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getApiVersionMinor() {
        return this.apiVersionMinor;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getApiVersion() {
        return this.apiVersion;
    }

    /* renamed from: component3, reason: from getter */
    public final GooglePayCustomerInfoPaymentMethodData getPaymentMethodData() {
        return this.paymentMethodData;
    }

    /* renamed from: component4, reason: from getter */
    public final GooglePayCustomerInfoShippingAddress getShippingAddress() {
        return this.shippingAddress;
    }

    /* renamed from: component5, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final GooglePayCustomerInfo copy(Integer apiVersionMinor, Integer apiVersion, GooglePayCustomerInfoPaymentMethodData paymentMethodData, GooglePayCustomerInfoShippingAddress shippingAddress, String email) {
        return new GooglePayCustomerInfo(apiVersionMinor, apiVersion, paymentMethodData, shippingAddress, email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GooglePayCustomerInfo)) {
            return false;
        }
        GooglePayCustomerInfo googlePayCustomerInfo = (GooglePayCustomerInfo) other;
        return Intrinsics.areEqual(this.apiVersionMinor, googlePayCustomerInfo.apiVersionMinor) && Intrinsics.areEqual(this.apiVersion, googlePayCustomerInfo.apiVersion) && Intrinsics.areEqual(this.paymentMethodData, googlePayCustomerInfo.paymentMethodData) && Intrinsics.areEqual(this.shippingAddress, googlePayCustomerInfo.shippingAddress) && Intrinsics.areEqual(this.email, googlePayCustomerInfo.email);
    }

    public final Integer getApiVersion() {
        return this.apiVersion;
    }

    public final Integer getApiVersionMinor() {
        return this.apiVersionMinor;
    }

    public final String getEmail() {
        return this.email;
    }

    public final GooglePayCustomerInfoPaymentMethodData getPaymentMethodData() {
        return this.paymentMethodData;
    }

    public final GooglePayCustomerInfoShippingAddress getShippingAddress() {
        return this.shippingAddress;
    }

    public int hashCode() {
        Integer num = this.apiVersionMinor;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.apiVersion;
        int hashCode2 = (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData = this.paymentMethodData;
        int hashCode3 = (hashCode2 + (googlePayCustomerInfoPaymentMethodData == null ? 0 : googlePayCustomerInfoPaymentMethodData.hashCode())) * 31;
        GooglePayCustomerInfoShippingAddress googlePayCustomerInfoShippingAddress = this.shippingAddress;
        int hashCode4 = (hashCode3 + (googlePayCustomerInfoShippingAddress == null ? 0 : googlePayCustomerInfoShippingAddress.hashCode())) * 31;
        String str = this.email;
        return hashCode4 + (str != null ? str.hashCode() : 0);
    }

    public final void setApiVersion(Integer num) {
        this.apiVersion = num;
    }

    public final void setApiVersionMinor(Integer num) {
        this.apiVersionMinor = num;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final void setPaymentMethodData(GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData) {
        this.paymentMethodData = googlePayCustomerInfoPaymentMethodData;
    }

    public final void setShippingAddress(GooglePayCustomerInfoShippingAddress googlePayCustomerInfoShippingAddress) {
        this.shippingAddress = googlePayCustomerInfoShippingAddress;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GooglePayCustomerInfo(apiVersionMinor=");
        sb.append(this.apiVersionMinor);
        sb.append(", apiVersion=");
        sb.append(this.apiVersion);
        sb.append(", paymentMethodData=");
        sb.append(this.paymentMethodData);
        sb.append(", shippingAddress=");
        sb.append(this.shippingAddress);
        sb.append(", email=");
        return N7.a(sb, this.email, ')');
    }

    public GooglePayCustomerInfo(Integer num, Integer num2, GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData, GooglePayCustomerInfoShippingAddress googlePayCustomerInfoShippingAddress, String str) {
        this.apiVersionMinor = num;
        this.apiVersion = num2;
        this.paymentMethodData = googlePayCustomerInfoPaymentMethodData;
        this.shippingAddress = googlePayCustomerInfoShippingAddress;
        this.email = str;
    }

    public /* synthetic */ GooglePayCustomerInfo(Integer num, Integer num2, GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData, GooglePayCustomerInfoShippingAddress googlePayCustomerInfoShippingAddress, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : googlePayCustomerInfoPaymentMethodData, (i & 8) != 0 ? null : googlePayCustomerInfoShippingAddress, (i & 16) != 0 ? null : str);
    }
}
