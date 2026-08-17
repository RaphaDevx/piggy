package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.ui.DataCaptureView;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureTextureView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(DataCaptureTextureView dataCaptureTextureView) {
        super(0);
        this.a = dataCaptureTextureView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        DataCaptureView dataCaptureView;
        AtomicBoolean atomicBoolean;
        dataCaptureView = this.a.a;
        dataCaptureView._setNeedsRedraw();
        atomicBoolean = this.a.g;
        atomicBoolean.set(true);
        return Unit.INSTANCE;
    }
}
