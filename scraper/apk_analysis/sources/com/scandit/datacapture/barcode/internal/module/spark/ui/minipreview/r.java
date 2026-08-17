package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class r {
    public x a;
    public final WeakReference b;
    public final q c;

    public r(RelativeLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.b = new WeakReference(container);
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        q qVar = new q(context);
        qVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.r$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r.a(r.this, view);
            }
        });
        this.c = qVar;
    }

    public static final void a(r this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        x xVar = this$0.a;
        if (xVar != null) {
            ((SparkScanViewPresenter) xVar).v();
        }
    }
}
