package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import android.view.View;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class BarcodeArAugmentation$Highlight {
    private final BarcodeArHighlight a;
    private final View b;

    public BarcodeArAugmentation$Highlight(BarcodeArHighlight highlight, View view) {
        Intrinsics.checkNotNullParameter(highlight, "highlight");
        Intrinsics.checkNotNullParameter(view, "view");
        this.a = highlight;
        this.b = view;
    }

    public final BarcodeArHighlight a() {
        return this.a;
    }

    public final View b() {
        return this.b;
    }

    public final Quadrilateral c() {
        if (!f.a(this.b)) {
            return null;
        }
        float x = this.b.getX() + this.b.getLeft();
        float x2 = this.b.getX() + this.b.getRight();
        float y = this.b.getY() + this.b.getTop();
        float y2 = this.b.getY() + this.b.getBottom();
        Quadrilateral quadrilateral = new Quadrilateral(new Point(x, y), new Point(x2, y), new Point(x2, y2), new Point(x, y2));
        return QuadrilateralUtilsKt.rotatedRadians(quadrilateral, QuadrilateralUtilsKt.getCenter(quadrilateral), Math.toRadians(this.b.getRotation()));
    }

    public final boolean d() {
        String name;
        Class<?> cls = this.a.getClass();
        if (ArraysKt.contains((Class<?>[]) f.a, cls)) {
            return true;
        }
        Package r0 = cls.getPackage();
        if (r0 == null || (name = r0.getName()) == null || !StringsKt.startsWith$default(name, "com.scandit.datacapture.barcode.ar", false, 2, (Object) null)) {
            return false;
        }
        throw new IllegalStateException(("`" + cls.getSimpleName() + "` class with package `" + name + "` should be registered in `BARCODE_AR_HIGHLIGHT_CLASSES`").toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BarcodeArAugmentation$Highlight)) {
            return false;
        }
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = (BarcodeArAugmentation$Highlight) obj;
        return Intrinsics.areEqual(this.a, barcodeArAugmentation$Highlight.a) && Intrinsics.areEqual(this.b, barcodeArAugmentation$Highlight.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Highlight(highlight=" + this.a + ", view=" + this.b + ')';
    }
}
