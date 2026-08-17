package com.scandit.datacapture.barcode.internal.module.count.ui.mode;

import com.scandit.datacapture.barcode.count.capture.map.BarcodeCountMappingFlowSettings;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements BarcodeCountInternalMode {
    public final BarcodeCountMappingFlowSettings a;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a b;

    public b(BarcodeCountMappingFlowSettings mappingFlowSettings, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a screen) {
        Intrinsics.checkNotNullParameter(mappingFlowSettings, "mappingFlowSettings");
        Intrinsics.checkNotNullParameter(screen, "screen");
        this.a = mappingFlowSettings;
        this.b = screen;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual(this.a, bVar.a) && this.b == bVar.b;
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "MappingFlow(mappingFlowSettings=" + this.a + ", screen=" + this.b + ')';
    }
}
