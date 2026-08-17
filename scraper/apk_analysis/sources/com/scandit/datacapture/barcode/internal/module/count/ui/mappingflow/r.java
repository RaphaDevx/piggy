package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0353h;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.z1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* loaded from: classes2.dex */
public final class r implements n {
    public static final long e;
    public final BarcodeCountViewUiLayoutHelper a;
    public m b;
    public final z1 c;
    public final Runnable d;

    static {
        Duration.Companion companion = Duration.INSTANCE;
        e = DurationKt.toDuration(4, DurationUnit.SECONDS);
    }

    public r(FrameLayout container, C0353h layoutHelper) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        this.a = layoutHelper;
        this.c = new z1(container);
        this.d = new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.r$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                r.a(r.this);
            }
        };
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        int b = this.a.b();
        m mVar = this.b;
        if (mVar != null) {
            mVar.a(this.a.e(), b);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.c.b(this.b, new q(this));
    }

    public static final void a(r this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.a(this$0.b, o.a);
    }
}
