package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class kg {
    public final String a;

    public kg(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof kg) && Intrinsics.areEqual(this.a, ((kg) obj).a);
    }

    public final int hashCode() {
        String str = this.a;
        return Boolean.hashCode(true) + ((str == null ? 0 : str.hashCode()) * 31);
    }

    public final String toString() {
        return "PostFinancePayRequestConfig(appCallbackUrl=" + this.a + ", isAppInstalled=true)";
    }
}
