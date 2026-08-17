package com.scandit.datacapture.core.ui;

import com.scandit.datacapture.core.internal.sdk.ui.hint.HintPresenterV2;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(DataCaptureView dataCaptureView) {
        super(0);
        this.a = dataCaptureView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        HintPresenterV2 hintPresenterV2;
        hintPresenterV2 = this.a.hintPresenterV2;
        return hintPresenterV2;
    }
}
