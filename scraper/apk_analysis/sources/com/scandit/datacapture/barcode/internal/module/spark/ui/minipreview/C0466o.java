package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.o, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0466o {
    public x a;
    public final WeakReference b;
    public final WeakReference c;
    public WeakReference d;
    public SparkScanMiniPreviewSize e;
    public final C0464m f;

    public C0466o(ViewGroup container, H toolbarInfo) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(toolbarInfo, "toolbarInfo");
        this.b = new WeakReference(container);
        this.c = new WeakReference(toolbarInfo);
        this.d = new WeakReference(null);
        this.e = SparkScanMiniPreviewSize.REGULAR;
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        C0464m c0464m = new C0464m(context);
        c0464m.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.o$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0466o.a(C0466o.this, view);
            }
        });
        this.f = c0464m;
    }

    public static final void a(C0466o this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        x xVar = this$0.a;
        if (xVar != null) {
            ((SparkScanViewPresenter) xVar).u();
        }
    }

    public final Pair b(ViewGroup viewGroup, boolean z) {
        int min = Integer.min(viewGroup.getWidth(), viewGroup.getHeight());
        int max = Math.max(viewGroup.getWidth(), viewGroup.getHeight());
        float f = min;
        H h = (H) this.c.get();
        int i = (int) (f * ((h == null || !h.w()) ? 0.5f : 0.6f));
        int intValue = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.f.getValue()).intValue();
        int intValue2 = ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.e.getValue()).intValue();
        if (i <= 0) {
            return TuplesKt.to(Integer.valueOf(intValue), Integer.valueOf(intValue));
        }
        if (z) {
            return TuplesKt.to(Integer.valueOf(RangesKt.coerceIn(i - ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.i.getValue()).intValue(), intValue2, intValue)), Integer.valueOf(RangesKt.coerceIn(i - ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.h.getValue()).intValue(), intValue2, intValue)));
        }
        int coerceIn = RangesKt.coerceIn(i, intValue2, RangesKt.coerceAtLeast(Integer.min((max / 2) - ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.h.getValue()).intValue(), intValue), intValue2));
        return TuplesKt.to(Integer.valueOf(coerceIn), Integer.valueOf(coerceIn));
    }

    public static Pair a(ViewGroup viewGroup, boolean z) {
        Size correctedViewSize = ViewExtensionsKt.getCorrectedViewSize(viewGroup, z);
        int width = correctedViewSize.getWidth();
        int height = correctedViewSize.getHeight();
        if (z) {
            int intValue = (height / 2) - ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.h.getValue()).intValue();
            int intValue2 = width - (((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.i.getValue()).intValue() * 2);
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.g;
            return TuplesKt.to(Integer.valueOf(RangesKt.coerceAtMost(intValue2, ((Number) lazy.getValue()).intValue())), Integer.valueOf(RangesKt.coerceAtMost(intValue, ((Number) lazy.getValue()).intValue())));
        }
        Lazy lazy2 = com.scandit.datacapture.barcode.internal.module.spark.ui.E.g;
        int min = Integer.min(height, ((Number) lazy2.getValue()).intValue());
        Lazy lazy3 = com.scandit.datacapture.barcode.internal.module.spark.ui.E.i;
        return TuplesKt.to(Integer.valueOf(Integer.min(width / 2, ((Number) lazy2.getValue()).intValue()) - (((Number) lazy3.getValue()).intValue() * 2)), Integer.valueOf(min - (((Number) lazy3.getValue()).intValue() * 2)));
    }
}
