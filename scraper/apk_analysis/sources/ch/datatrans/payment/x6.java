package ch.datatrans.payment;

import java.util.LinkedHashMap;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class x6 {
    public final LinkedHashMap a;

    public x6(LinkedHashMap data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.a = data;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof x6) && Intrinsics.areEqual(this.a, ((x6) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "KlarnaPaymentInfo(data=" + this.a + ')';
    }
}
