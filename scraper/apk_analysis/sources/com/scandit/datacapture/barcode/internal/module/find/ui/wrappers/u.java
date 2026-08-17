package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler;
import com.scandit.datacapture.core.ui.DataCaptureView;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class u {
    public final PreviewResolutionRatioHandler a;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f b;
    public final List c;
    public boolean d;
    public boolean e;
    public boolean f;

    public u(PreviewResolutionRatioHandler resolutionRatioHandler, com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f dataCaptureViewWrapper, List dependantViewWrappers) {
        Intrinsics.checkNotNullParameter(resolutionRatioHandler, "resolutionRatioHandler");
        Intrinsics.checkNotNullParameter(dataCaptureViewWrapper, "dataCaptureViewWrapper");
        Intrinsics.checkNotNullParameter(dependantViewWrappers, "dependantViewWrappers");
        this.a = resolutionRatioHandler;
        this.b = dataCaptureViewWrapper;
        this.c = dependantViewWrappers;
        if (resolutionRatioHandler.getRatio() == -1.0f) {
            resolutionRatioHandler.setListener(new t(this));
        } else {
            a();
        }
    }

    public final void a() {
        if (this.d) {
            return;
        }
        this.d = true;
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar = this.b;
        fVar.a.a(fVar.b(), fVar.a());
        ((DataCaptureView) this.b.b()).post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.u$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                u.a(u.this);
            }
        });
    }

    public final void b() {
        if (this.d && this.e && this.f) {
            com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar = this.b;
            fVar.a.a(fVar.b(), fVar.a());
            for (com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar2 : this.c) {
                fVar2.a.a(fVar2.b(), fVar2.a());
            }
        }
    }

    public static final void a(u this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.f) {
            return;
        }
        this$0.f = true;
        if (this$0.d && this$0.e) {
            this$0.b.b().setVisibility(0);
            Iterator it = this$0.c.iterator();
            while (it.hasNext()) {
                ((com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) it.next()).a(this$0.b.a());
            }
        }
    }
}
