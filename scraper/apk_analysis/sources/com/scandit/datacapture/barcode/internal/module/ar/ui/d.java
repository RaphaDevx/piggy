package com.scandit.datacapture.barcode.internal.module.ar.ui;

import android.content.Context;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.internal.sdk.extensions.AnchorExtensionsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.control.Control;
import com.scandit.datacapture.core.ui.control.LinearControlGroup;
import com.scandit.datacapture.core.ui.control.LinearControlGroupOrientation;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d {
    public final NativeBarcodeArView a;
    public final DataCaptureView b;
    public final Lazy c;
    public final Lazy d;
    public final Lazy e;

    public d(NativeBarcodeArView impl, DataCaptureView dataCaptureView, com.scandit.datacapture.barcode.ar.ui.a cameraSwitchControlBuilder) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        Intrinsics.checkNotNullParameter(cameraSwitchControlBuilder, "cameraSwitchControlBuilder");
        this.a = impl;
        this.b = dataCaptureView;
        this.c = LazyKt.lazy(new b(this));
        this.d = LazyKt.lazy(new c(this));
        this.e = LazyKt.lazy(new a(cameraSwitchControlBuilder, this));
    }

    public final void a() {
        Control control = (Control) this.e.getValue();
        if (control != null) {
            this.b.removeControl(control);
            if (this.a.getShouldShowCameraSwitchControl()) {
                Anchor cameraSwitchControlPosition = this.a.getCameraSwitchControlPosition();
                Intrinsics.checkNotNullExpressionValue(cameraSwitchControlPosition, "getCameraSwitchControlPosition(...)");
                a(cameraSwitchControlPosition);
            }
        }
    }

    public final void b() {
        this.b.removeControl((Control) this.c.getValue());
        if (this.a.getShouldShowTorchControl()) {
            Anchor torchControlPosition = this.a.getTorchControlPosition();
            Intrinsics.checkNotNullExpressionValue(torchControlPosition, "getTorchControlPosition(...)");
            a(torchControlPosition);
        }
    }

    public final void c() {
        this.b.removeControl((Control) this.d.getValue());
        if (this.a.getShouldShowZoomControl()) {
            Anchor zoomControlPosition = this.a.getZoomControlPosition();
            Intrinsics.checkNotNullExpressionValue(zoomControlPosition, "getZoomControlPosition(...)");
            a(zoomControlPosition);
        }
    }

    public final void a(Anchor anchor) {
        ArrayList arrayList = new ArrayList();
        if (this.a.getShouldShowTorchControl()) {
            Anchor torchControlPosition = this.a.getTorchControlPosition();
            Intrinsics.checkNotNullExpressionValue(torchControlPosition, "getTorchControlPosition(...)");
            if (torchControlPosition == anchor) {
                arrayList.add((Control) this.c.getValue());
            }
        }
        if (this.a.getShouldShowZoomControl()) {
            Anchor zoomControlPosition = this.a.getZoomControlPosition();
            Intrinsics.checkNotNullExpressionValue(zoomControlPosition, "getZoomControlPosition(...)");
            if (zoomControlPosition == anchor) {
                arrayList.add((Control) this.d.getValue());
            }
        }
        Control control = (Control) this.e.getValue();
        if (control != null && this.a.getShouldShowCameraSwitchControl()) {
            Anchor cameraSwitchControlPosition = this.a.getCameraSwitchControlPosition();
            Intrinsics.checkNotNullExpressionValue(cameraSwitchControlPosition, "getCameraSwitchControlPosition(...)");
            if (cameraSwitchControlPosition == anchor) {
                arrayList.add(control);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            return;
        }
        DataCaptureView dataCaptureView = this.b;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            dataCaptureView.removeControl((Control) it.next());
        }
        DataCaptureView dataCaptureView2 = this.b;
        LinearControlGroup.Companion companion = LinearControlGroup.INSTANCE;
        Context context = dataCaptureView2.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        dataCaptureView2.addControl(companion.builder(context).setControls(arrayList).setOrientation(LinearControlGroupOrientation.VERTICAL).setSpacing(new FloatWithUnit(12.0f, MeasureUnit.DIP)).build(), anchor, AnchorExtensionsKt.getDefaultOffset(anchor));
    }
}
