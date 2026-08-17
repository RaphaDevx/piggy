package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

/* loaded from: classes2.dex */
public final class A {
    public final boolean a;
    public final boolean b;

    public A(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof A)) {
            return false;
        }
        A a = (A) obj;
        return this.a == a.a && this.b == a.b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.b) + (Boolean.hashCode(this.a) * 31);
    }

    public final String toString() {
        return "ClusterLoadingStatus(isLoading=" + this.a + ", animate=" + this.b + ')';
    }
}
