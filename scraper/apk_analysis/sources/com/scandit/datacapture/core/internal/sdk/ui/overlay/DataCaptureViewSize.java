package com.scandit.datacapture.core.internal.sdk.ui.overlay;

import androidx.constraintlayout.motion.widget.Key;
import com.scandit.datacapture.core.common.geometry.Size2;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/overlay/DataCaptureViewSize;", "", "Lcom/scandit/datacapture/core/common/geometry/Size2;", RRWebVideoEvent.JsonKeys.SIZE, "", Key.ROTATION, "<init>", "(Lcom/scandit/datacapture/core/common/geometry/Size2;I)V", "a", "Lcom/scandit/datacapture/core/common/geometry/Size2;", "getSize", "()Lcom/scandit/datacapture/core/common/geometry/Size2;", "b", "I", "getRotation", "()I", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class DataCaptureViewSize {

    /* renamed from: a, reason: from kotlin metadata */
    private final Size2 size;

    /* renamed from: b, reason: from kotlin metadata */
    private final int rotation;

    public DataCaptureViewSize(Size2 size, int i) {
        Intrinsics.checkNotNullParameter(size, "size");
        this.size = size;
        this.rotation = i;
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final Size2 getSize() {
        return this.size;
    }
}
