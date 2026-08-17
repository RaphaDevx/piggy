package com.scandit.datacapture.core.ui.control;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function0 {
    public final /* synthetic */ ZoomSwitchControl a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(ZoomSwitchControl zoomSwitchControl) {
        super(0);
        this.a = zoomSwitchControl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ZoomSwitchControl.access$toggleZoom(this.a);
        return Unit.INSTANCE;
    }
}
