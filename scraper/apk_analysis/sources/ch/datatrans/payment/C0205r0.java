package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.r0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0205r0 {
    public String a;
    public int b;
    public boolean c;
    public boolean d;

    public C0205r0() {
        Intrinsics.checkNotNullParameter("", "cvv");
        this.a = "";
        this.b = 3;
        this.c = false;
        this.d = false;
    }

    public final boolean a() {
        return this.d || this.a.length() == this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0205r0)) {
            return false;
        }
        C0205r0 c0205r0 = (C0205r0) obj;
        return Intrinsics.areEqual(this.a, c0205r0.a) && this.b == c0205r0.b && this.c == c0205r0.c && this.d == c0205r0.d;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.d) + ((Boolean.hashCode(this.c) + ((Integer.hashCode(this.b) + (this.a.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "CreditCardCVVModel(cvv=" + this.a + ", cvvLength=" + this.b + ", isCvvOptional=" + this.c + ", cvvNotNeeded=" + this.d + ')';
    }
}
