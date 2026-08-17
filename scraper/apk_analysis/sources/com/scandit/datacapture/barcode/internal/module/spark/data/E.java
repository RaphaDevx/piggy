package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.barcode.internal.module.spark.ui.S;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.source.Camera;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class E extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(O o, S s) {
        super(0);
        this.a = o;
        this.b = s;
    }

    public final void a() {
        O o = this.a;
        Camera camera = o.e;
        Camera camera2 = Intrinsics.areEqual(camera, o.f) ? this.a.g : Intrinsics.areEqual(camera, this.a.g) ? this.a.f : this.a.f;
        if (camera2 != null) {
            O o2 = this.a;
            Camera.applySettings$default(camera2, o2.a(o2.b.s(), this.a.c()), null, 2, null);
        }
        final O o3 = this.a;
        DataCaptureContext dataCaptureContext = o3.a;
        final Function0 function0 = this.b;
        dataCaptureContext.setFrameSource(camera2, new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.data.E$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                E.a(O.this, function0);
            }
        });
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }

    public static final void a(O this$0, Function0 onDone) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onDone, "$onDone");
        O.a(this$0, new D(onDone));
    }
}
