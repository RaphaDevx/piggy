package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.Card;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class M3 {
    public final L3 a;
    public final Card b;

    public M3(L3 dccAuthorizationData, Card card) {
        Intrinsics.checkNotNullParameter(dccAuthorizationData, "dccAuthorizationData");
        Intrinsics.checkNotNullParameter(card, "card");
        this.a = dccAuthorizationData;
        this.b = card;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof M3)) {
            return false;
        }
        M3 m3 = (M3) obj;
        return Intrinsics.areEqual(this.a, m3.a) && Intrinsics.areEqual(this.b, m3.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "DCCCardData(dccAuthorizationData=" + this.a + ", card=" + this.b + ')';
    }
}
