package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.graphics.RectF;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class U implements S {
    public final DataCaptureView b;
    public final RectF c = new RectF();

    public U(DataCaptureView dataCaptureView) {
        this.b = dataCaptureView;
    }

    public final V a(TrackedBarcode trackedBarcode) {
        Quadrilateral locationIgnoringLicense$scandit_barcode_capture;
        Point center;
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        int globalId$scandit_barcode_capture = trackedBarcode.getGlobalId$scandit_barcode_capture();
        DataCaptureView dataCaptureView = this.b;
        if (dataCaptureView == null || (locationIgnoringLicense$scandit_barcode_capture = dataCaptureView.mapFrameQuadrilateralToView(trackedBarcode.getLocationIgnoringLicense$scandit_barcode_capture())) == null) {
            locationIgnoringLicense$scandit_barcode_capture = trackedBarcode.getLocationIgnoringLicense$scandit_barcode_capture();
        }
        DataCaptureView dataCaptureView2 = this.b;
        if (dataCaptureView2 == null || (center = dataCaptureView2.mapFramePointToView(QuadrilateralUtilsKt.getCenter(trackedBarcode.getLocationIgnoringLicense$scandit_barcode_capture()))) == null) {
            center = QuadrilateralUtilsKt.getCenter(trackedBarcode.getLocationIgnoringLicense$scandit_barcode_capture());
        }
        return new V(globalId$scandit_barcode_capture, locationIgnoringLicense$scandit_barcode_capture, center, a(trackedBarcode.getLocationIgnoringLicense$scandit_barcode_capture()));
    }

    public final float a(Quadrilateral quadrilateral) {
        Quadrilateral mapFrameQuadrilateralToView;
        RectF rectF = this.c;
        DataCaptureView dataCaptureView = this.b;
        if (dataCaptureView != null && (mapFrameQuadrilateralToView = dataCaptureView.mapFrameQuadrilateralToView(quadrilateral)) != null) {
            quadrilateral = mapFrameQuadrilateralToView;
        }
        rectF.left = ComparisonsKt.minOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX());
        rectF.top = ComparisonsKt.minOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY());
        rectF.right = ComparisonsKt.maxOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX());
        rectF.bottom = ComparisonsKt.maxOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY());
        float min = Math.min(rectF.width(), rectF.height());
        S.a.getClass();
        return RangesKt.coerceIn(min / ((Number) Q.b.getValue()).floatValue(), 0.5f, 1.0f);
    }
}
