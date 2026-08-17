package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.TorchState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class I extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Callback b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(O o, Callback callback) {
        super(0);
        this.a = o;
        this.b = callback;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit;
        O o = this.a;
        TorchState torchState = TorchState.OFF;
        H h = new H(o, this.b);
        Camera camera = o.e;
        if (camera != null) {
            camera._switchToDesiredTorchState(torchState, h);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            h.invoke();
        }
        return Unit.INSTANCE;
    }
}
