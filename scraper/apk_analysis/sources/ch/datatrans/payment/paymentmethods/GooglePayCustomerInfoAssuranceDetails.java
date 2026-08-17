package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B!\b\u0000\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\bJ(\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0013\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\u0019R$\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\u0019¨\u0006\u001d"}, d2 = {"Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;", "", "", "cardHolderAuthenticated", "accountVerified", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "component1", "()Ljava/lang/Boolean;", "component2", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Lch/datatrans/payment/paymentmethods/GooglePayCustomerInfoAssuranceDetails;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", Request.JsonKeys.OTHER, "equals", "(Ljava/lang/Object;)Z", "a", "Ljava/lang/Boolean;", "getCardHolderAuthenticated", "setCardHolderAuthenticated", "(Ljava/lang/Boolean;)V", "b", "getAccountVerified", "setAccountVerified", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class GooglePayCustomerInfoAssuranceDetails {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("cardHolderAuthenticated")
    private Boolean cardHolderAuthenticated;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("accountVerified")
    private Boolean accountVerified;

    /* JADX WARN: Multi-variable type inference failed */
    public GooglePayCustomerInfoAssuranceDetails() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ GooglePayCustomerInfoAssuranceDetails copy$default(GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = googlePayCustomerInfoAssuranceDetails.cardHolderAuthenticated;
        }
        if ((i & 2) != 0) {
            bool2 = googlePayCustomerInfoAssuranceDetails.accountVerified;
        }
        return googlePayCustomerInfoAssuranceDetails.copy(bool, bool2);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getCardHolderAuthenticated() {
        return this.cardHolderAuthenticated;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getAccountVerified() {
        return this.accountVerified;
    }

    public final GooglePayCustomerInfoAssuranceDetails copy(Boolean cardHolderAuthenticated, Boolean accountVerified) {
        return new GooglePayCustomerInfoAssuranceDetails(cardHolderAuthenticated, accountVerified);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GooglePayCustomerInfoAssuranceDetails)) {
            return false;
        }
        GooglePayCustomerInfoAssuranceDetails googlePayCustomerInfoAssuranceDetails = (GooglePayCustomerInfoAssuranceDetails) other;
        return Intrinsics.areEqual(this.cardHolderAuthenticated, googlePayCustomerInfoAssuranceDetails.cardHolderAuthenticated) && Intrinsics.areEqual(this.accountVerified, googlePayCustomerInfoAssuranceDetails.accountVerified);
    }

    public final Boolean getAccountVerified() {
        return this.accountVerified;
    }

    public final Boolean getCardHolderAuthenticated() {
        return this.cardHolderAuthenticated;
    }

    public int hashCode() {
        Boolean bool = this.cardHolderAuthenticated;
        int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.accountVerified;
        return hashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final void setAccountVerified(Boolean bool) {
        this.accountVerified = bool;
    }

    public final void setCardHolderAuthenticated(Boolean bool) {
        this.cardHolderAuthenticated = bool;
    }

    public String toString() {
        return "GooglePayCustomerInfoAssuranceDetails(cardHolderAuthenticated=" + this.cardHolderAuthenticated + ", accountVerified=" + this.accountVerified + ')';
    }

    public GooglePayCustomerInfoAssuranceDetails(Boolean bool, Boolean bool2) {
        this.cardHolderAuthenticated = bool;
        this.accountVerified = bool2;
    }

    public /* synthetic */ GooglePayCustomerInfoAssuranceDetails(Boolean bool, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : bool2);
    }
}
