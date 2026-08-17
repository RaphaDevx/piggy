package ch.datatrans.payment.paymentmethods;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002\u000e\u000fR\u001a\u0010\u0007\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\r\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Format;", "a", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Format;", "getFormat$lib_release", "()Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Format;", "format", "", "b", "Z", "isPhoneNumberRequired$lib_release", "()Z", "isPhoneNumberRequired", "Format", "Builder", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class BillingAddressParameters {

    /* renamed from: a, reason: from kotlin metadata */
    public final Format format;

    /* renamed from: b, reason: from kotlin metadata */
    public final boolean isPhoneNumberRequired;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Builder;", "", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Format;", "format", "<init>", "(Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Format;)V", "returnPhoneNumber", "()Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Builder;", "Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "build", "()Lch/datatrans/payment/paymentmethods/BillingAddressParameters;", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        public final Format a;
        public boolean b;

        public Builder(Format format) {
            Intrinsics.checkNotNullParameter(format, "format");
            this.a = format;
        }

        public final BillingAddressParameters build() {
            return new BillingAddressParameters(this.a, this.b, null);
        }

        public final Builder returnPhoneNumber() {
            this.b = true;
            return this;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003¨\u0006\u0004"}, d2 = {"Lch/datatrans/payment/paymentmethods/BillingAddressParameters$Format;", "", "MIN", "FULL", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Format {
        public static final Format FULL;
        public static final Format MIN;
        public static final /* synthetic */ Format[] a;
        public static final /* synthetic */ EnumEntries b;

        static {
            Format format = new Format("MIN", 0);
            MIN = format;
            Format format2 = new Format("FULL", 1);
            FULL = format2;
            Format[] formatArr = {format, format2};
            a = formatArr;
            b = EnumEntriesKt.enumEntries(formatArr);
        }

        public Format(String str, int i) {
        }

        public static EnumEntries<Format> getEntries() {
            return b;
        }

        public static Format valueOf(String str) {
            return (Format) Enum.valueOf(Format.class, str);
        }

        public static Format[] values() {
            return (Format[]) a.clone();
        }
    }

    public BillingAddressParameters(Format format, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this.format = format;
        this.isPhoneNumberRequired = z;
    }

    /* renamed from: getFormat$lib_release, reason: from getter */
    public final Format getFormat() {
        return this.format;
    }

    /* renamed from: isPhoneNumberRequired$lib_release, reason: from getter */
    public final boolean getIsPhoneNumberRequired() {
        return this.isPhoneNumberRequired;
    }
}
