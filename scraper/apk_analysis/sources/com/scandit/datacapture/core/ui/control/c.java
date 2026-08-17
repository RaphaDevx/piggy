package com.scandit.datacapture.core.ui.control;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureContext a;
    public final /* synthetic */ FrameSource b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(DataCaptureContext dataCaptureContext, FrameSource frameSource) {
        super(0);
        this.a = dataCaptureContext;
        this.b = frameSource;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        DataCaptureContext.setFrameSource$default(this.a, this.b, null, 2, null);
        FrameSource frameSource = this.b;
        if (frameSource != null) {
            FrameSource.DefaultImpls.switchToDesiredState$default(frameSource, FrameSourceState.ON, null, 2, null);
        }
        return Unit.INSTANCE;
    }
}
