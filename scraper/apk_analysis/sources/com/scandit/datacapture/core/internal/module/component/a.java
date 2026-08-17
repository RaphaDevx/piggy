package com.scandit.datacapture.core.internal.module.component;

import com.scandit.datacapture.core.component.DataCaptureComponent;
import com.scandit.datacapture.core.internal.sdk.component.NativeDataCaptureComponent;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements DataCaptureComponent {
    public final NativeDataCaptureComponent a;
    public final String b;

    public a(NativeDataCaptureComponent impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = impl;
        String identifier = impl.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
        this.b = identifier;
    }

    @Override // com.scandit.datacapture.core.component.DataCaptureComponent, com.scandit.datacapture.barcode.generator.BarcodeGeneratorProxy
    public final NativeDataCaptureComponent _dataCaptureComponentImpl() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.component.DataCaptureComponent, com.scandit.datacapture.barcode.generator.BarcodeGeneratorProxy
    public final String getId() {
        return this.b;
    }
}
