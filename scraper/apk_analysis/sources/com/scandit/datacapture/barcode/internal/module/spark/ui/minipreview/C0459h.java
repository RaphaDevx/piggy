package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0459h {
    public x a;
    public final WeakReference b;
    public final C0458g c;

    public C0459h(RelativeLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.b = new WeakReference(container);
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        C0458g c0458g = new C0458g(context);
        c0458g.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.h$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0459h.a(C0459h.this, view);
            }
        });
        this.c = c0458g;
    }

    public static final void a(C0459h this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        x xVar = this$0.a;
        if (xVar != null) {
            ((SparkScanViewPresenter) xVar).t();
        }
    }
}
