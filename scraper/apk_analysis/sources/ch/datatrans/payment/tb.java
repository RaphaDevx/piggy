package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class tb {
    public final String a;
    public final String b;

    public tb(String swissPassCardNumber, String swissPassZipCode) {
        Intrinsics.checkNotNullParameter(swissPassCardNumber, "swissPassCardNumber");
        Intrinsics.checkNotNullParameter(swissPassZipCode, "swissPassZipCode");
        this.a = swissPassCardNumber;
        this.b = swissPassZipCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tb)) {
            return false;
        }
        tb tbVar = (tb) obj;
        return Intrinsics.areEqual(this.a, tbVar.a) && Intrinsics.areEqual(this.b, tbVar.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SwissPassPaymentInfo(swissPassCardNumber=");
        sb.append(this.a);
        sb.append(", swissPassZipCode=");
        return N7.a(sb, this.b, ')');
    }
}
