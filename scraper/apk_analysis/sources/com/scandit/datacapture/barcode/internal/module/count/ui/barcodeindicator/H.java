package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.graphics.Path;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class H {
    public final Path a;
    public final Brush b;

    public H(Path path, Brush brush) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.a = path;
        this.b = brush;
    }
}
