package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.ce, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0127ce {
    public final String a;
    public final boolean b;

    public C0127ce(String appCallbackUrl, boolean z) {
        Intrinsics.checkNotNullParameter(appCallbackUrl, "appCallbackUrl");
        this.a = appCallbackUrl;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0127ce)) {
            return false;
        }
        C0127ce c0127ce = (C0127ce) obj;
        return Intrinsics.areEqual(this.a, c0127ce.a) && this.b == c0127ce.b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "VippsRequestConfig(appCallbackUrl=" + this.a + ", isAppInstalled=" + this.b + ')';
    }
}
