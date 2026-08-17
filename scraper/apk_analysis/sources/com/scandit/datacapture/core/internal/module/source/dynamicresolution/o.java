package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.source.FrameSource;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o implements q {
    public final FrameSource a;

    public o(FrameSource frameSource) {
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
        return (obj instanceof o) && Intrinsics.areEqual(this.a, ((o) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Started(frameSource=" + this.a + ')';
    }
}
