package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class U1 {
    public String a;

    public U1() {
        Intrinsics.checkNotNullParameter("", "date");
        this.a = "";
    }

    public final boolean a() {
        int intValue;
        int intValue2;
        if (this.a.length() != 4) {
            return false;
        }
        Integer intOrNull = this.a.length() >= 2 ? StringsKt.toIntOrNull(StringsKt.take(this.a, 2)) : null;
        if (intOrNull == null || 1 > (intValue = intOrNull.intValue()) || intValue >= 13) {
            return false;
        }
        Integer intOrNull2 = this.a.length() == 4 ? StringsKt.toIntOrNull(StringsKt.takeLast(this.a, 2)) : null;
        return intOrNull2 != null && 25 <= (intValue2 = intOrNull2.intValue()) && intValue2 < 100;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof U1) && Intrinsics.areEqual(this.a, ((U1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return N7.a(new StringBuilder("CreditCardDateModel(date="), this.a, ')');
    }
}
