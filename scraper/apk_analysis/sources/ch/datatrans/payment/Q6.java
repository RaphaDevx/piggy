package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Q6 {
    public final boolean a;
    public final boolean b;
    public final C0189o c;

    public Q6(boolean z, boolean z2, C0189o originalRange) {
        Intrinsics.checkNotNullParameter(originalRange, "originalRange");
        this.a = z;
        this.b = z2;
        this.c = originalRange;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Q6)) {
            return false;
        }
        Q6 q6 = (Q6) obj;
        return this.a == q6.a && this.b == q6.b && Intrinsics.areEqual(this.c, q6.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((Boolean.hashCode(this.b) + (Boolean.hashCode(this.a) * 31)) * 31);
    }

    public final String toString() {
        return "MatchResult(match: " + this.a + ", complete: " + this.b + ", " + this.c + ')';
    }
}
