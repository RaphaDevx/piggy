package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.DataCaptureViewSizeListener;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class Z implements DataCaptureViewSizeListener {
    public final /* synthetic */ u0 a;

    public Z(u0 u0Var) {
        this.a = u0Var;
    }

    public static final void a(u0 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.k.b();
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewSizeListener
    public final void onSizeChanged(DataCaptureView view, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(view, "view");
        final u0 u0Var = this.a;
        u0Var.a.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.Z$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Z.a(u0.this);
            }
        });
    }
}
