package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class V9 {
    public final String a;
    public final String b;

    public V9(String transactionId, String redirectUrl) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(redirectUrl, "redirectUrl");
        this.a = transactionId;
        this.b = redirectUrl;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof V9)) {
            return false;
        }
        V9 v9 = (V9) obj;
        return Intrinsics.areEqual(this.a, v9.a) && Intrinsics.areEqual(this.b, v9.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RedirectUrlResult(transactionId=");
        sb.append(this.a);
        sb.append(", redirectUrl=");
        return N7.a(sb, this.b, ')');
    }
}
