package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z {
    public x a;
    public final WeakReference b;
    public final y c;

    public z(RelativeLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.b = new WeakReference(container);
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        y yVar = new y(context);
        yVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.z$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                z.a(z.this, view);
            }
        });
        this.c = yVar;
    }

    public static final void a(z this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        x xVar = this$0.a;
        if (xVar != null) {
            ((SparkScanViewPresenter) xVar).w();
        }
    }
}
