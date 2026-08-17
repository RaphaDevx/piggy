package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.CardNumberLength;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* renamed from: ch.datatrans.payment.o, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0189o {
    public final PaymentMethodType a;
    public final int b;
    public final int c;
    public final List d;
    public final CardNumberLength.IntList e;
    public final int f;
    public final int g;

    public C0189o(PaymentMethodType paymentMethodType, int i, int i2, List spaces, CardNumberLength.IntList length) {
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(spaces, "spaces");
        Intrinsics.checkNotNullParameter(length, "length");
        this.a = paymentMethodType;
        this.b = i;
        this.c = i2;
        this.d = spaces;
        this.e = length;
        int parseInt = Integer.parseInt(StringsKt.padEnd(String.valueOf(i), 9, '0'));
        this.f = parseInt;
        int parseInt2 = Integer.parseInt(StringsKt.padEnd(String.valueOf(i2), 9, '9'));
        this.g = parseInt2;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException(("Start and/or end are negative numbers: " + i + ", " + i2).toString());
        }
        if (String.valueOf(i).length() > 9) {
            throw new IllegalArgumentException(("Start too long: " + i).toString());
        }
        if (String.valueOf(i2).length() > 9) {
            throw new IllegalArgumentException(("End too long: " + i2).toString());
        }
        if (parseInt <= parseInt2) {
            return;
        }
        throw new IllegalArgumentException(("Invalid range, start: " + i + " > end: " + i2).toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Intrinsics.areEqual(C0189o.class, obj.getClass())) {
            C0189o c0189o = (C0189o) obj;
            if (this.f == c0189o.f && this.g == c0189o.g) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f), Integer.valueOf(this.g));
    }

    public final String toString() {
        return this.b + " - " + this.c;
    }
}
