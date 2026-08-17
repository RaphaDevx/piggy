package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eR\u001a\u0010\u0007\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\n\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006R\u001a\u0010\r\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000e8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0004\u001a\u0004\b\u0015\u0010\u0006R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u00178\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "", "", "a", "Z", "isEmailRequired$lib_release", "()Z", "isEmailRequired", "b", "getAreAssuranceDetailsRequired$lib_release", "areAssuranceDetailsRequired", "c", "isBillingAddressRequired$lib_release", "isBillingAddressRequired", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "d", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "getBillingAddressParameters$lib_release", "()Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "billingAddressParameters", "e", "isShippingAddressRequired$lib_release", "isShippingAddressRequired", "Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "f", "Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "getShippingAddressParameters$lib_release", "()Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "shippingAddressParameters", "Companion", "Builder", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class CustomerInfoParameters {

    /* renamed from: a, reason: from kotlin metadata */
    public final boolean isEmailRequired;

    /* renamed from: b, reason: from kotlin metadata */
    public final boolean areAssuranceDetailsRequired;

    /* renamed from: c, reason: from kotlin metadata */
    public final boolean isBillingAddressRequired;

    /* renamed from: d, reason: from kotlin metadata */
    public final BillingAddressParameters billingAddressParameters;

    /* renamed from: e, reason: from kotlin metadata */
    public final boolean isShippingAddressRequired;

    /* renamed from: f, reason: from kotlin metadata */
    public final ShippingAddressParameters shippingAddressParameters;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0005J\u0019\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lch/datatrans/payment/paymentmethods/CustomerInfoParameters$Builder;", "", "<init>", "()V", "returnEmail", "()Lch/datatrans/payment/paymentmethods/CustomerInfoParameters$Builder;", "returnAssuranceDetails", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "parameters", "returnBillingAddress", "(Lch/datatrans/payment/paymentmethods/BillingAddressParameters;)Lch/datatrans/payment/paymentmethods/CustomerInfoParameters$Builder;", "Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "returnShippingAddress", "(Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;)Lch/datatrans/payment/paymentmethods/CustomerInfoParameters$Builder;", "Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "build", "()Lch/datatrans/payment/paymentmethods/CustomerInfoParameters;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        public boolean a;
        public boolean b;
        public boolean c;
        public BillingAddressParameters d;
        public boolean e;
        public ShippingAddressParameters f;

        public static /* synthetic */ Builder returnBillingAddress$default(Builder builder, BillingAddressParameters billingAddressParameters, int i, Object obj) {
            if ((i & 1) != 0) {
                billingAddressParameters = null;
            }
            return builder.returnBillingAddress(billingAddressParameters);
        }

        public static /* synthetic */ Builder returnShippingAddress$default(Builder builder, ShippingAddressParameters shippingAddressParameters, int i, Object obj) {
            if ((i & 1) != 0) {
                shippingAddressParameters = null;
            }
            return builder.returnShippingAddress(shippingAddressParameters);
        }

        public final CustomerInfoParameters build() {
            return new CustomerInfoParameters(this.a, this.b, this.c, this.d, this.e, this.f, null);
        }

        public final Builder returnAssuranceDetails() {
            this.b = true;
            return this;
        }

        public final Builder returnBillingAddress(BillingAddressParameters parameters) {
            this.c = true;
            this.d = parameters;
            return this;
        }

        public final Builder returnEmail() {
            this.a = true;
            return this;
        }

        public final Builder returnShippingAddress(ShippingAddressParameters parameters) {
            this.e = true;
            this.f = parameters;
            return this;
        }
    }

    public CustomerInfoParameters(boolean z, boolean z2, boolean z3, BillingAddressParameters billingAddressParameters, boolean z4, ShippingAddressParameters shippingAddressParameters, DefaultConstructorMarker defaultConstructorMarker) {
        this.isEmailRequired = z;
        this.areAssuranceDetailsRequired = z2;
        this.isBillingAddressRequired = z3;
        this.billingAddressParameters = billingAddressParameters;
        this.isShippingAddressRequired = z4;
        this.shippingAddressParameters = shippingAddressParameters;
    }

    /* renamed from: getAreAssuranceDetailsRequired$lib_release, reason: from getter */
    public final boolean getAreAssuranceDetailsRequired() {
        return this.areAssuranceDetailsRequired;
    }

    /* renamed from: getBillingAddressParameters$lib_release, reason: from getter */
    public final BillingAddressParameters getBillingAddressParameters() {
        return this.billingAddressParameters;
    }

    /* renamed from: getShippingAddressParameters$lib_release, reason: from getter */
    public final ShippingAddressParameters getShippingAddressParameters() {
        return this.shippingAddressParameters;
    }

    /* renamed from: isBillingAddressRequired$lib_release, reason: from getter */
    public final boolean getIsBillingAddressRequired() {
        return this.isBillingAddressRequired;
    }

    /* renamed from: isEmailRequired$lib_release, reason: from getter */
    public final boolean getIsEmailRequired() {
        return this.isEmailRequired;
    }

    /* renamed from: isShippingAddressRequired$lib_release, reason: from getter */
    public final boolean getIsShippingAddressRequired() {
        return this.isShippingAddressRequired;
    }
}
