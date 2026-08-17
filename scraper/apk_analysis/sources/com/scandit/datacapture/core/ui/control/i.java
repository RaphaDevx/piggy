package com.scandit.datacapture.core.ui.control;

import com.scandit.datacapture.core.source.Camera;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public final /* synthetic */ TorchSwitchControl a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(TorchSwitchControl torchSwitchControl) {
        super(0);
        this.a = torchSwitchControl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Camera camera = this.a.f;
        if (camera != null) {
            TorchSwitchControl.access$toggleTorchOnOff(this.a, camera);
        }
        return Unit.INSTANCE;
    }
}
