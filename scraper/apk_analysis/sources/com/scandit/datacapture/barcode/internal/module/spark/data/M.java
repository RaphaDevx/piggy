package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.extensions.LambdaExtensionsKt;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.FrameSourceState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class M extends Lambda implements Function0 {
    public final /* synthetic */ O a;
    public final /* synthetic */ Callback b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M(O o, Callback callback) {
        super(0);
        this.a = o;
        this.b = callback;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit;
        InterfaceC0429h interfaceC0429h;
        O o = this.a;
        Camera camera = o.e;
        if (camera != null) {
            Callback callback = this.b;
            FrameSourceState currentState = camera.getCurrentState();
            FrameSourceState frameSourceState = FrameSourceState.ON;
            if (currentState != frameSourceState && (interfaceC0429h = o.h) != null) {
                interfaceC0429h.onCameraOnStart();
            }
            camera.addListener(new r(new J(o)));
            camera.switchToDesiredState(frameSourceState, LambdaExtensionsKt.Callback(new w(o, new K(callback))));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            O.a(this.a, new L(this.b));
        }
        return Unit.INSTANCE;
    }
}
