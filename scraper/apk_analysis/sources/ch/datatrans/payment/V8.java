package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class V8 {
    public final String a;

    public V8(String cardno) {
        Intrinsics.checkNotNullParameter(cardno, "cardno");
        this.a = cardno;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof V8) && Intrinsics.areEqual(this.a, ((V8) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return N7.a(new StringBuilder("PaycardPaymentInfo(cardno="), this.a, ')');
    }
}
