package ch.datatrans.payment.api.tokenization;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u000f¨\u0006#"}, d2 = {"Lch/datatrans/payment/api/tokenization/PCIPTokenizationSuccess;", "", "", "tokenizationId", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "paymentMethodType", "Lch/datatrans/payment/api/tokenization/PCIPCardInfo;", "cardInfo", "<init>", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/PaymentMethodType;Lch/datatrans/payment/api/tokenization/PCIPCardInfo;)V", "component1", "()Ljava/lang/String;", "component2", "()Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "component3", "()Lch/datatrans/payment/api/tokenization/PCIPCardInfo;", "copy", "(Ljava/lang/String;Lch/datatrans/payment/paymentmethods/PaymentMethodType;Lch/datatrans/payment/api/tokenization/PCIPCardInfo;)Lch/datatrans/payment/api/tokenization/PCIPTokenizationSuccess;", "toString", "", "hashCode", "()I", Request.JsonKeys.OTHER, "", "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/String;", "getTokenizationId", "b", "Lch/datatrans/payment/paymentmethods/PaymentMethodType;", "getPaymentMethodType", "c", "Lch/datatrans/payment/api/tokenization/PCIPCardInfo;", "getCardInfo", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class PCIPTokenizationSuccess {

    /* renamed from: a, reason: from kotlin metadata */
    public final String tokenizationId;

    /* renamed from: b, reason: from kotlin metadata */
    public final PaymentMethodType paymentMethodType;

    /* renamed from: c, reason: from kotlin metadata */
    public final PCIPCardInfo cardInfo;

    public PCIPTokenizationSuccess(String tokenizationId, PaymentMethodType paymentMethodType, PCIPCardInfo cardInfo) {
        Intrinsics.checkNotNullParameter(tokenizationId, "tokenizationId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(cardInfo, "cardInfo");
        this.tokenizationId = tokenizationId;
        this.paymentMethodType = paymentMethodType;
        this.cardInfo = cardInfo;
    }

    public static /* synthetic */ PCIPTokenizationSuccess copy$default(PCIPTokenizationSuccess pCIPTokenizationSuccess, String str, PaymentMethodType paymentMethodType, PCIPCardInfo pCIPCardInfo, int i, Object obj) {
        if ((i & 1) != 0) {
            str = pCIPTokenizationSuccess.tokenizationId;
        }
        if ((i & 2) != 0) {
            paymentMethodType = pCIPTokenizationSuccess.paymentMethodType;
        }
        if ((i & 4) != 0) {
            pCIPCardInfo = pCIPTokenizationSuccess.cardInfo;
        }
        return pCIPTokenizationSuccess.copy(str, paymentMethodType, pCIPCardInfo);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTokenizationId() {
        return this.tokenizationId;
    }

    /* renamed from: component2, reason: from getter */
    public final PaymentMethodType getPaymentMethodType() {
        return this.paymentMethodType;
    }

    /* renamed from: component3, reason: from getter */
    public final PCIPCardInfo getCardInfo() {
        return this.cardInfo;
    }

    public final PCIPTokenizationSuccess copy(String tokenizationId, PaymentMethodType paymentMethodType, PCIPCardInfo cardInfo) {
        Intrinsics.checkNotNullParameter(tokenizationId, "tokenizationId");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(cardInfo, "cardInfo");
        return new PCIPTokenizationSuccess(tokenizationId, paymentMethodType, cardInfo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PCIPTokenizationSuccess)) {
            return false;
        }
        PCIPTokenizationSuccess pCIPTokenizationSuccess = (PCIPTokenizationSuccess) other;
        return Intrinsics.areEqual(this.tokenizationId, pCIPTokenizationSuccess.tokenizationId) && this.paymentMethodType == pCIPTokenizationSuccess.paymentMethodType && Intrinsics.areEqual(this.cardInfo, pCIPTokenizationSuccess.cardInfo);
    }

    public final PCIPCardInfo getCardInfo() {
        return this.cardInfo;
    }

    public final PaymentMethodType getPaymentMethodType() {
        return this.paymentMethodType;
    }

    public final String getTokenizationId() {
        return this.tokenizationId;
    }

    public int hashCode() {
        return this.cardInfo.hashCode() + ((this.paymentMethodType.hashCode() + (this.tokenizationId.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "PCIPTokenizationSuccess(tokenizationId=" + this.tokenizationId + ", paymentMethodType=" + this.paymentMethodType + ", cardInfo=" + this.cardInfo + ')';
    }
}
