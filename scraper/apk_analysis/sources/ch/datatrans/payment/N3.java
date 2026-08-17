package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class N3 {
    public int a;
    public String b;

    public N3(String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.a = 1;
        this.b = date;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof N3)) {
            return false;
        }
        N3 n3 = (N3) obj;
        return this.a == n3.a && Intrinsics.areEqual(this.b, n3.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (Integer.hashCode(this.a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Data(counter=");
        sb.append(this.a);
        sb.append(", date=");
        return N7.a(sb, this.b, ')');
    }
}
