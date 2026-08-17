package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer;

import android.graphics.Paint;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class m {
    public final Paint a;
    public final Paint b;
    public final List c;

    public m(Paint fill, Paint stroke, List shadows) {
        Intrinsics.checkNotNullParameter(fill, "fill");
        Intrinsics.checkNotNullParameter(stroke, "stroke");
        Intrinsics.checkNotNullParameter(shadows, "shadows");
        this.a = fill;
        this.b = stroke;
        this.c = shadows;
    }
}
