package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.barcode.internal.module.spark.ui.d0;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.TorchState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class u extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(O o, d0 d0Var) {
        super(0);
        this.a = o;
        this.b = d0Var;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit;
        O o = this.a;
        TorchState torchState = TorchState.OFF;
        t tVar = new t(o, (d0) this.b);
        Camera camera = o.e;
        if (camera != null) {
            camera._switchToDesiredTorchState(torchState, tVar);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            tVar.invoke();
        }
        return Unit.INSTANCE;
    }
}
