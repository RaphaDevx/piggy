package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.Camera;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class y extends Lambda implements Function0 {
    public final /* synthetic */ O a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(O o) {
        super(0);
        this.a = o;
    }

    public static final void b() {
        O.l.release();
    }

    public final void a() {
        Camera camera = x.a[this.a.b.a().ordinal()] == 1 ? this.a.g : this.a.f;
        O o = this.a;
        Runnable runnable = new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.data.y$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                y.b();
            }
        };
        o.onFrameSourceChanged(o.a, camera);
        o.a.setFrameSource(camera, runnable);
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }
}
