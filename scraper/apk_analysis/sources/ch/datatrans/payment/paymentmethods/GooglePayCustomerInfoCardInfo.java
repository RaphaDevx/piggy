package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B9\b\u0000\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\fJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J@\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0014\u0010\fJ\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010 R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b!\u0010\u001d\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010 R$\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u000f\"\u0004\b'\u0010(R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010\u0011\"\u0004\b,\u0010-¨\u0006."}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;", "", "", "cardNetwork", "cardDetails", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;", "billingAddress", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;", "assuranceDetails", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;", "component4", "()Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;", "copy", "(Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;)Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getCardNetwork", "setCardNetwork", "(Ljava/lang/String;)V", "b", "getCardDetails", "setCardDetails", "c", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;", "getBillingAddress", "setBillingAddress", "(Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoBillingAddress;)V", "d", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;", "getAssuranceDetails", "setAssuranceDetails", "(Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class GooglePayCustomerInfoCardInfo {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("cardNetwork")
    private String cardNetwork;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("cardDetails")
    private String cardDetails;

    /* renamed from: c, reason: from kotlin metadata */
    @SerializedName("billingAddress")
    private GooglePayCustomerInfoBillingAddress billingAddress;

    /* renamed from: d, reason: from kotlin metadata */
    @SerializedName("assuranceDetails")
    private GooglePayCustomerInfoAssuranceDetails assuranceDetails;

    public GooglePayCustomerInfoCardInfo() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ GooglePayCustomerInfoCardInfo copy$default(GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo, String str, String str2, GooglePayCustomerInfoBillingAddress googlePayCustomerInfoBillingAddress, GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails, int i, Object obj) {
        if ((i & 1) != 0) {
            str = googlePayCustomerInfoCardInfo.cardNetwork;
        }
        if ((i & 2) != 0) {
            str2 = googlePayCustomerInfoCardInfo.cardDetails;
        }
        if ((i & 4) != 0) {
            googlePayCustomerInfoBillingAddress = googlePayCustomerInfoCardInfo.billingAddress;
        }
        if ((i & 8) != 0) {
            googlePayCustomerInfoAssuranceDetails = googlePayCustomerInfoCardInfo.assuranceDetails;
        }
        return googlePayCustomerInfoCardInfo.copy(str, str2, googlePayCustomerInfoBillingAddress, googlePayCustomerInfoAssuranceDetails);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCardNetwork() {
        return this.cardNetwork;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCardDetails() {
        return this.cardDetails;
    }

    /* renamed from: component3, reason: from getter */
    public final GooglePayCustomerInfoBillingAddress getBillingAddress() {
        return this.billingAddress;
    }

    /* renamed from: component4, reason: from getter */
    public final GooglePayCustomerInfoAssuranceDetails getAssuranceDetails() {
        return this.assuranceDetails;
    }

    public final GooglePayCustomerInfoCardInfo copy(String cardNetwork, String cardDetails, GooglePayCustomerInfoBillingAddress billingAddress, GooglePayCustomerInfoAssuranceDetails assuranceDetails) {
        return new GooglePayCustomerInfoCardInfo(cardNetwork, cardDetails, billingAddress, assuranceDetails);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GooglePayCustomerInfoCardInfo)) {
            return false;
        }
        GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo = (GooglePayCustomerInfoCardInfo) other;
        return Intrinsics.areEqual(this.cardNetwork, googlePayCustomerInfoCardInfo.cardNetwork) && Intrinsics.areEqual(this.cardDetails, googlePayCustomerInfoCardInfo.cardDetails) && Intrinsics.areEqual(this.billingAddress, googlePayCustomerInfoCardInfo.billingAddress) && Intrinsics.areEqual(this.assuranceDetails, googlePayCustomerInfoCardInfo.assuranceDetails);
    }

    public final GooglePayCustomerInfoAssuranceDetails getAssuranceDetails() {
        return this.assuranceDetails;
    }

    public final GooglePayCustomerInfoBillingAddress getBillingAddress() {
        return this.billingAddress;
    }

    public final String getCardDetails() {
        return this.cardDetails;
    }

    public final String getCardNetwork() {
        return this.cardNetwork;
    }

    public int hashCode() {
        String str = this.cardNetwork;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.cardDetails;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        GooglePayCustomerInfoBillingAddress googlePayCustomerInfoBillingAddress = this.billingAddress;
        int hashCode3 = (hashCode2 + (googlePayCustomerInfoBillingAddress == null ? 0 : googlePayCustomerInfoBillingAddress.hashCode())) * 31;
        GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails = this.assuranceDetails;
        return hashCode3 + (googlePayCustomerInfoAssuranceDetails != null ? googlePayCustomerInfoAssuranceDetails.hashCode() : 0);
    }

    public final void setAssuranceDetails(GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails) {
        this.assuranceDetails = googlePayCustomerInfoAssuranceDetails;
    }

    public final void setBillingAddress(GooglePayCustomerInfoBillingAddress googlePayCustomerInfoBillingAddress) {
        this.billingAddress = googlePayCustomerInfoBillingAddress;
    }

    public final void setCardDetails(String str) {
        this.cardDetails = str;
    }

    public final void setCardNetwork(String str) {
        this.cardNetwork = str;
    }

    public String toString() {
        return "GooglePayCustomerInfoCardInfo(cardNetwork=" + this.cardNetwork + ", cardDetails=" + this.cardDetails + ", billingAddress=" + this.billingAddress + ", assuranceDetails=" + this.assuranceDetails + ')';
    }

    public GooglePayCustomerInfoCardInfo(String str, String str2, GooglePayCustomerInfoBillingAddress googlePayCustomerInfoBillingAddress, GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails) {
        this.cardNetwork = str;
        this.cardDetails = str2;
        this.billingAddress = googlePayCustomerInfoBillingAddress;
        this.assuranceDetails = googlePayCustomerInfoAssuranceDetails;
    }

    public /* synthetic */ GooglePayCustomerInfoCardInfo(String str, String str2, GooglePayCustomerInfoBillingAddress googlePayCustomerInfoBillingAddress, GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : googlePayCustomerInfoBillingAddress, (i & 8) != 0 ? null : googlePayCustomerInfoAssuranceDetails);
    }
}
