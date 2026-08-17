package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.nd, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0188nd {
    public final String a;
    public final boolean b;

    public C0188nd(String transactionId, boolean z) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        this.a = transactionId;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0188nd)) {
            return false;
        }
        C0188nd c0188nd = (C0188nd) obj;
        return Intrinsics.areEqual(this.a, c0188nd.a) && this.b == c0188nd.b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "TwoFAResult(transactionId=" + this.a + ", twoFARequired=" + this.b + ')';
    }
}
