package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.ae, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0113ae {
    public final String a;
    public final String b;

    public C0113ae(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0113ae)) {
            return false;
        }
        C0113ae c0113ae = (C0113ae) obj;
        return Intrinsics.areEqual(this.a, c0113ae.a) && Intrinsics.areEqual(this.b, c0113ae.b);
    }

    public final int hashCode() {
        String str = this.a;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.b;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("VippsInitialConfig(paymentText=");
        sb.append(this.a);
        sb.append(", customerMobileNumber=");
        return N7.a(sb, this.b, ')');
    }
}
