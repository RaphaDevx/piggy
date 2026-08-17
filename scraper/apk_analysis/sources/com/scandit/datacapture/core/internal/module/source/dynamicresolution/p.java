package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.source.FrameSource;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p implements q {
    public final FrameSource a;

    public p(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        this.a = frameSource;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.dynamicresolution.q
    public final FrameSource a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p) && Intrinsics.areEqual(this.a, ((p) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Stopped(frameSource=" + this.a + ')';
    }
}
