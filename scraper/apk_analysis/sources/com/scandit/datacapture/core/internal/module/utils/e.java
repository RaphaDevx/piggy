package com.scandit.datacapture.core.internal.module.utils;

/* loaded from: classes2.dex */
public final class e {
    public final long a;

    public final boolean equals(Object obj) {
        return (obj instanceof e) && this.a == ((e) obj).a;
    }

    public final int hashCode() {
        return Long.hashCode(this.a);
    }

    public final String toString() {
        return "Timestamp(value=" + this.a + ')';
    }
}
