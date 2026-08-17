package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.content.Context;
import android.util.Size;
import android.view.View;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.y, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0321y implements InterfaceC0318v {
    public static final Lazy g = LazyKt.lazy(C0319w.a);
    public final BarcodeCountBasicOverlay a;
    public final BarcodeCountViewLayerManager b;
    public final S c;
    public NativeBarcodeCountNotInListStatus d;
    public TrackedBarcode e;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.popover.h f;

    public C0321y(Context context, BarcodeCountBasicOverlay overlay, com.scandit.datacapture.barcode.internal.module.count.ui.g layerManager, U trackedBarcodeHelper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(layerManager, "layerManager");
        Intrinsics.checkNotNullParameter(trackedBarcodeHelper, "trackedBarcodeHelper");
        this.a = overlay;
        this.b = layerManager;
        this.c = trackedBarcodeHelper;
        this.d = NativeBarcodeCountNotInListStatus.NONE;
        this.f = new com.scandit.datacapture.barcode.internal.module.count.ui.popover.h(context, layerManager.k, new C0320x(this));
        overlay.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.y$$ExternalSyntheticLambda0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                C0321y.a(C0321y.this, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        layerManager.k.setBackgroundColor(((Number) g.getValue()).intValue());
        layerManager.k.setVisibility(8);
        layerManager.l.setVisibility(8);
        layerManager.l.setImportantForAccessibility(2);
        layerManager.l.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.y$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0321y.a(C0321y.this, view);
            }
        });
    }

    public static final void a(C0321y this$0, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (view != null) {
            com.scandit.datacapture.barcode.internal.module.count.ui.popover.h hVar = this$0.f;
            Size size = new Size(view.getWidth(), view.getHeight());
            hVar.getClass();
            Intrinsics.checkNotNullParameter(size, "<set-?>");
            com.scandit.datacapture.barcode.internal.module.ui.popover.K k = (com.scandit.datacapture.barcode.internal.module.ui.popover.K) hVar.d;
            k.getClass();
            Intrinsics.checkNotNullParameter(size, "<set-?>");
            k.e = size;
        }
    }

    public static final void a(C0321y this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TrackedBarcode trackedBarcode = this$0.e;
        if (trackedBarcode != null) {
            this$0.a.didCancelNotInListBarcode(trackedBarcode);
        }
    }
}
