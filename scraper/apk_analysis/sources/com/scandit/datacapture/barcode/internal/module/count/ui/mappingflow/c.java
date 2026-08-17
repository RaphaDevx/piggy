package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.graphics.Rect;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c {
    public final List a;
    public final Rect b;

    public c(List anchors, Rect margins) {
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        Intrinsics.checkNotNullParameter(margins, "margins");
        this.a = anchors;
        this.b = margins;
    }
}
