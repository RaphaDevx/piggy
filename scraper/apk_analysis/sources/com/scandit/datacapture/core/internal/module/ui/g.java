package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListener;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function1 {
    public final /* synthetic */ DataCaptureTextureView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(DataCaptureTextureView dataCaptureTextureView) {
        super(1);
        this.a = dataCaptureTextureView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureView dataCaptureView;
        NeedsRedrawListener listener = (NeedsRedrawListener) obj;
        Intrinsics.checkNotNullParameter(listener, "listener");
        dataCaptureView = this.a.a;
        dataCaptureView._setNeedsRedrawListener(listener);
        return Unit.INSTANCE;
    }
}
