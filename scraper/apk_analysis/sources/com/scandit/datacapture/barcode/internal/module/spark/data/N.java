package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.Camera;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class N extends Lambda implements Function0 {
    public final /* synthetic */ O a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public N(O o) {
        super(0);
        this.a = o;
    }

    public static final void b() {
        O.l.release();
    }

    public final void a() {
        Unit unit;
        O o = this.a;
        Camera camera = o.e;
        if (camera != null) {
            camera.applySettings(o.a(o.b.s(), this.a.c()), new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.data.N$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    N.b();
                }
            });
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            O.l.release();
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }
}
