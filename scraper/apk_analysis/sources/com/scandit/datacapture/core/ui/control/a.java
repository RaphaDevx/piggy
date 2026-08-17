package com.scandit.datacapture.core.ui.control;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ CameraSwitchControl a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(CameraSwitchControl cameraSwitchControl) {
        super(0);
        this.a = cameraSwitchControl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        CameraSwitchControl.access$toggleCamera(this.a);
        return Unit.INSTANCE;
    }
}
