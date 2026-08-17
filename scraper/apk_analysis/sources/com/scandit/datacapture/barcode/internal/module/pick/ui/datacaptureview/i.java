package com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview;

import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public final /* synthetic */ j a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar) {
        super(0);
        this.a = jVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        j jVar = this.a;
        g gVar = jVar.a;
        BarcodePickViewSettings settings = jVar.b;
        h hVar = (h) gVar;
        hVar.getClass();
        Intrinsics.checkNotNullParameter(settings, "settings");
        DataCaptureView newInstance = DataCaptureView.INSTANCE.newInstance(hVar.a, null);
        newInstance.setZoomGesture(null);
        return newInstance;
    }
}
