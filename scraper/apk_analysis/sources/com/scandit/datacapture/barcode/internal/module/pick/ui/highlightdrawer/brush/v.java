package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.graphics.Path;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;

/* loaded from: classes2.dex */
public abstract class v {
    public final Quadrilateral a;
    public final Path b;

    public v(Quadrilateral quadrilateral, Path path) {
        this.a = quadrilateral;
        this.b = path;
    }
}
