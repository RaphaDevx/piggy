package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B-\b\u0000\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ4\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\n\"\u0004\b\u001b\u0010\u001cR$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001e\u0010\n\"\u0004\b\u001f\u0010\u001cR$\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;", "", "", "description", "type", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;", "info", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;", "copy", "(Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;)Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoPaymentMethodData;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getDescription", "setDescription", "(Ljava/lang/String;)V", "b", "getType", "setType", "c", "Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;", "getInfo", "setInfo", "(Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoCardInfo;)V", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class GooglePayCustomerInfoPaymentMethodData {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("description")
    private String description;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("type")
    private String type;

    /* renamed from: c, reason: from kotlin metadata */
    @SerializedName("info")
    private GooglePayCustomerInfoCardInfo info;

    public GooglePayCustomerInfoPaymentMethodData() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ GooglePayCustomerInfoPaymentMethodData copy$default(GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData, String str, String str2, GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            str = googlePayCustomerInfoPaymentMethodData.description;
        }
        if ((i & 2) != 0) {
            str2 = googlePayCustomerInfoPaymentMethodData.type;
        }
        if ((i & 4) != 0) {
            googlePayCustomerInfoCardInfo = googlePayCustomerInfoPaymentMethodData.info;
        }
        return googlePayCustomerInfoPaymentMethodData.copy(str, str2, googlePayCustomerInfoCardInfo);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final GooglePayCustomerInfoCardInfo getInfo() {
        return this.info;
    }

    public final GooglePayCustomerInfoPaymentMethodData copy(String description, String type, GooglePayCustomerInfoCardInfo info) {
        return new GooglePayCustomerInfoPaymentMethodData(description, type, info);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GooglePayCustomerInfoPaymentMethodData)) {
            return false;
        }
        GooglePayCustomerInfoPaymentMethodData googlePayCustomerInfoPaymentMethodData = (GooglePayCustomerInfoPaymentMethodData) other;
        return Intrinsics.areEqual(this.description, googlePayCustomerInfoPaymentMethodData.description) && Intrinsics.areEqual(this.type, googlePayCustomerInfoPaymentMethodData.type) && Intrinsics.areEqual(this.info, googlePayCustomerInfoPaymentMethodData.info);
    }

    public final String getDescription() {
        return this.description;
    }

    public final GooglePayCustomerInfoCardInfo getInfo() {
        return this.info;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.description;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.type;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo = this.info;
        return hashCode2 + (googlePayCustomerInfoCardInfo != null ? googlePayCustomerInfoCardInfo.hashCode() : 0);
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final void setInfo(GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo) {
        this.info = googlePayCustomerInfoCardInfo;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public String toString() {
        return "GooglePayCustomerInfoPaymentMethodData(description=" + this.description + ", type=" + this.type + ", info=" + this.info + ')';
    }

    public GooglePayCustomerInfoPaymentMethodData(String str, String str2, GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo) {
        this.description = str;
        this.type = str2;
        this.info = googlePayCustomerInfoCardInfo;
    }

    public /* synthetic */ GooglePayCustomerInfoPaymentMethodData(String str, String str2, GooglePayCustomerInfoCardInfo googlePayCustomerInfoCardInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : googlePayCustomerInfoCardInfo);
    }
}
