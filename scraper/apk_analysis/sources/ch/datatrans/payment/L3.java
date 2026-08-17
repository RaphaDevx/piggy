package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class L3 {
    public final String a;

    public L3() {
        this.a = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof L3) && Intrinsics.areEqual(this.a, ((L3) obj).a);
    }

    public final int hashCode() {
        String str = this.a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return N7.a(new StringBuilder("DCCAuthorizationData(dccId="), this.a, ')');
    }

    public L3(String str) {
        this.a = str;
    }
}
