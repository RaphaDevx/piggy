package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000fR \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u000e\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "", "", "", "a", "Ljava/util/List;", "getAllowedCountryCodes$lib_release", "()Ljava/util/List;", "allowedCountryCodes", "", "b", "Z", "isPhoneNumberRequired$lib_release", "()Z", "isPhoneNumberRequired", "Builder", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class ShippingAddressParameters {

    /* renamed from: a, reason: from kotlin metadata */
    public final List allowedCountryCodes;

    /* renamed from: b, reason: from kotlin metadata */
    public final boolean isPhoneNumberRequired;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0000¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lch/datatrans/payment/paymentmethods/ShippingAddressParameters$Builder;", "", "<init>", "()V", "", "", "countryCodes", "allowedCountryCodes", "(Ljava/util/List;)Lch/datatrans/payment/paymentmethods/ShippingAddressParameters$Builder;", "returnPhoneNumber", "()Lch/datatrans/payment/paymentmethods/ShippingAddressParameters$Builder;", "Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "build", "()Lch/datatrans/payment/paymentmethods/ShippingAddressParameters;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        public List a = CollectionsKt.emptyList();
        public boolean b;

        public final Builder allowedCountryCodes(List<String> countryCodes) {
            Intrinsics.checkNotNullParameter(countryCodes, "countryCodes");
            this.a = countryCodes;
            return this;
        }

        public final ShippingAddressParameters build() {
            return new ShippingAddressParameters(this.a, this.b, null);
        }

        public final Builder returnPhoneNumber() {
            this.b = true;
            return this;
        }
    }

    public ShippingAddressParameters(List list, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this.allowedCountryCodes = list;
        this.isPhoneNumberRequired = z;
    }

    public final List<String> getAllowedCountryCodes$lib_release() {
        return this.allowedCountryCodes;
    }

    /* renamed from: isPhoneNumberRequired$lib_release, reason: from getter */
    public final boolean getIsPhoneNumberRequired() {
        return this.isPhoneNumberRequired;
    }
}
