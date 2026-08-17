package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.extensions.LambdaExtensionsKt;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSourceState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(O o, Function0 function0) {
        super(0);
        this.a = o;
        this.b = function0;
    }

    public static final void a(O this$0, Function0 whenDone) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(whenDone, "$whenDone");
        Camera camera = this$0.e;
        if (camera != null) {
            camera.switchToDesiredState(FrameSourceState.STANDBY, LambdaExtensionsKt.Callback(new B(this$0, whenDone)));
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }

    public final void a() {
        Unit unit;
        O o = this.a;
        Camera camera = o.e;
        if (camera != null) {
            CameraSettings a = o.a(o.b.s(), this.a.c());
            final O o2 = this.a;
            final Function0 function0 = this.b;
            camera.applySettings(a, new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.data.C$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    C.a(O.this, function0);
                }
            });
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            O.a(this.a, this.b);
        }
    }
}
