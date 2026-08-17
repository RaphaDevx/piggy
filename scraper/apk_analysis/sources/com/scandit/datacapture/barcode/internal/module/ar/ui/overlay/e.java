package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements b {
    public final Function2 a;
    public final Function2 b;
    public final Function1 c;

    public e(com.scandit.datacapture.barcode.ar.ui.l highlightRequestor, com.scandit.datacapture.barcode.ar.ui.m annotationRequestor, com.scandit.datacapture.barcode.ar.ui.n quadrilateralMapper) {
        Intrinsics.checkNotNullParameter(highlightRequestor, "highlightRequestor");
        Intrinsics.checkNotNullParameter(annotationRequestor, "annotationRequestor");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        this.a = highlightRequestor;
        this.b = annotationRequestor;
        this.c = quadrilateralMapper;
    }
}
