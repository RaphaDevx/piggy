package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class V {
    public final int a;
    public final Quadrilateral b;
    public final Point c;
    public final float d;

    public V(int i, Quadrilateral position, Point centerPosition, float f) {
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(centerPosition, "centerPosition");
        this.a = i;
        this.b = position;
        this.c = centerPosition;
        this.d = f;
    }
}
