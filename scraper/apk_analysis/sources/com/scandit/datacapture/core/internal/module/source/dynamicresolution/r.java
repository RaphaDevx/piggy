package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class r implements u {
    public final DataCaptureContext a;

    public r(DataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.dynamicresolution.u
    public final DataCaptureContext a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof r) && Intrinsics.areEqual(this.a, ((r) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Attached(context=" + this.a + ')';
    }
}
