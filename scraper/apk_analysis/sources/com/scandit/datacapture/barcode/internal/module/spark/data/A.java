package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.TorchState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class A extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ TorchState b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(O o, TorchState torchState, Function0 function0) {
        super(0);
        this.a = o;
        this.b = torchState;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit;
        O o = this.a;
        TorchState torchState = this.b;
        z zVar = new z(o, this.c);
        Camera camera = o.e;
        if (camera != null) {
            camera._switchToDesiredTorchState(torchState, zVar);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            zVar.invoke();
        }
        return Unit.INSTANCE;
    }
}
