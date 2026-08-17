package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class w implements y {
    public final m a;

    public w(m settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a = settings;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.dynamicresolution.y
    public final m a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof w) && Intrinsics.areEqual(this.a, ((w) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Enabled(settings=" + this.a + ')';
    }
}
