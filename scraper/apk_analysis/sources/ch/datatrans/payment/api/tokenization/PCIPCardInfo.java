package ch.datatrans.payment.api.tokenization;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import io.sentry.protocol.Device;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001BM\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000eJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000eJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u000eJ\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u000eJ\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u000eJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015Jb\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u000eJ\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u000eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010!\u001a\u0004\b$\u0010\u000eR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b%\u0010!\u001a\u0004\b&\u0010\u000eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b'\u0010!\u001a\u0004\b(\u0010\u000eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b)\u0010!\u001a\u0004\b*\u0010\u000eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b+\u0010!\u001a\u0004\b,\u0010\u000eR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u0010\u0015¨\u00060"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPCardInfo;", "", "", "maskedCardNumber", Device.JsonKeys.BRAND, "type", "usage", SpaySdk.EXTRA_COUNTRY_CODE, "issuer", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "cardExpiryDate", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "()Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lch/datatrans/payment/paymentmethods/CardExpiryDate;)Lch/datatrans/payment/api/tokenization/PCIPCardInfo;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getMaskedCardNumber", "b", "getBrand", "c", "getType", "d", "getUsage", "e", "getCountryCode", "f", "getIssuer", "g", "Lch/datatrans/payment/paymentmethods/CardExpiryDate;", "getCardExpiryDate", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class PCIPCardInfo {

    /* renamed from: a, reason: from kotlin metadata */
    public final String maskedCardNumber;

    /* renamed from: b, reason: from kotlin metadata */
    public final String brand;

    /* renamed from: c, reason: from kotlin metadata */
    public final String type;

    /* renamed from: d, reason: from kotlin metadata */
    public final String usage;

    /* renamed from: e, reason: from kotlin metadata */
    public final String countryCode;

    /* renamed from: f, reason: from kotlin metadata */
    public final String issuer;

    /* renamed from: g, reason: from kotlin metadata */
    public final CardExpiryDate cardExpiryDate;

    public PCIPCardInfo(String maskedCardNumber, String str, String str2, String str3, String str4, String str5, CardExpiryDate cardExpiryDate) {
        Intrinsics.checkNotNullParameter(maskedCardNumber, "maskedCardNumber");
        this.maskedCardNumber = maskedCardNumber;
        this.brand = str;
        this.type = str2;
        this.usage = str3;
        this.countryCode = str4;
        this.issuer = str5;
        this.cardExpiryDate = cardExpiryDate;
    }

    public static /* synthetic */ PCIPCardInfo copy$default(PCIPCardInfo pCIPCardInfo, String str, String str2, String str3, String str4, String str5, String str6, CardExpiryDate cardExpiryDate, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pCIPCardInfo.maskedCardNumber;
        }
        if ((i & 2) != 0) {
            str2 = pCIPCardInfo.brand;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = pCIPCardInfo.type;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = pCIPCardInfo.usage;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = pCIPCardInfo.countryCode;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = pCIPCardInfo.issuer;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            cardExpiryDate = pCIPCardInfo.cardExpiryDate;
        }
        return pCIPCardInfo.copy(str, str7, str8, str9, str10, str11, cardExpiryDate);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMaskedCardNumber() {
        return this.maskedCardNumber;
    }

    /* renamed from: component2, reason: from getter */
    public final String getBrand() {
        return this.brand;
    }

    /* renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUsage() {
        return this.usage;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component6, reason: from getter */
    public final String getIssuer() {
        return this.issuer;
    }

    /* renamed from: component7, reason: from getter */
    public final CardExpiryDate getCardExpiryDate() {
        return this.cardExpiryDate;
    }

    public final PCIPCardInfo copy(String maskedCardNumber, String brand, String type, String usage, String countryCode, String issuer, CardExpiryDate cardExpiryDate) {
        Intrinsics.checkNotNullParameter(maskedCardNumber, "maskedCardNumber");
        return new PCIPCardInfo(maskedCardNumber, brand, type, usage, countryCode, issuer, cardExpiryDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PCIPCardInfo)) {
            return false;
        }
        PCIPCardInfo pCIPCardInfo = (PCIPCardInfo) other;
        return Intrinsics.areEqual(this.maskedCardNumber, pCIPCardInfo.maskedCardNumber) && Intrinsics.areEqual(this.brand, pCIPCardInfo.brand) && Intrinsics.areEqual(this.type, pCIPCardInfo.type) && Intrinsics.areEqual(this.usage, pCIPCardInfo.usage) && Intrinsics.areEqual(this.countryCode, pCIPCardInfo.countryCode) && Intrinsics.areEqual(this.issuer, pCIPCardInfo.issuer) && Intrinsics.areEqual(this.cardExpiryDate, pCIPCardInfo.cardExpiryDate);
    }

    public final String getBrand() {
        return this.brand;
    }

    public final CardExpiryDate getCardExpiryDate() {
        return this.cardExpiryDate;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final String getIssuer() {
        return this.issuer;
    }

    public final String getMaskedCardNumber() {
        return this.maskedCardNumber;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUsage() {
        return this.usage;
    }

    public int hashCode() {
        int hashCode = this.maskedCardNumber.hashCode() * 31;
        String str = this.brand;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.type;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.usage;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.countryCode;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.issuer;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        CardExpiryDate cardExpiryDate = this.cardExpiryDate;
        return hashCode6 + (cardExpiryDate != null ? cardExpiryDate.hashCode() : 0);
    }

    public String toString() {
        return "PCIPCardInfo(maskedCardNumber=" + this.maskedCardNumber + ", brand=" + this.brand + ", type=" + this.type + ", usage=" + this.usage + ", countryCode=" + this.countryCode + ", issuer=" + this.issuer + ", cardExpiryDate=" + this.cardExpiryDate + ')';
    }
}
