package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.control.Control;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0339a implements BarcodeCountDataCaptureViewHolder {
    public final DataCaptureView a;

    public C0339a(DataCaptureView dataCaptureView) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        this.a = dataCaptureView;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void a(BarcodeCountBasicOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.a.removeOverlay(overlay);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void b(BarcodeCountBasicOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        this.a.addOverlay(overlay);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void a(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        ViewParent parent = this.a.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(this.a);
        }
        container.addView(this.a);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void a(RelativeLayout.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        this.a.setLayoutParams(layoutParams);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void a(HintHolderV2 hintHolder) {
        Intrinsics.checkNotNullParameter(hintHolder, "hintHolder");
        this.a._overrideHintHolderV2(hintHolder);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void a(Control control, Anchor anchor, PointWithUnit offset) {
        Intrinsics.checkNotNullParameter(control, "control");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(offset, "offset");
        this.a.addControl(control, anchor, offset);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountDataCaptureViewHolder
    public final void a(Control control) {
        Intrinsics.checkNotNullParameter(control, "control");
        this.a.removeControl(control);
    }
}
