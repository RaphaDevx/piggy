package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import android.view.View;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArStatusIconAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.responsive.BarcodeArResponsiveAnnotation;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArCircleHighlight;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArRectangleHighlight;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class f {
    public static final Class[] a = {BarcodeArRectangleHighlight.class, BarcodeArCircleHighlight.class};
    public static final Class[] b = {BarcodeArInfoAnnotation.class, BarcodeArPopoverAnnotation.class, BarcodeArStatusIconAnnotation.class, BarcodeArResponsiveAnnotation.class};

    public static final boolean a(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getParent() != null && view.isAttachedToWindow() && view.getWidth() > 0 && view.getHeight() > 0;
    }
}
