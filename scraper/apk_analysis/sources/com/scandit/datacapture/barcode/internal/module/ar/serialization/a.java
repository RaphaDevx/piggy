package com.scandit.datacapture.barcode.internal.module.ar.serialization;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSettings;
import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeArFeedbackDeserializer;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import io.sentry.protocol.Feedback;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodeArDeserializerHelper {
    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.BarcodeArDeserializerHelper
    public final void a(BarcodeArSettings settings, JsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper
    public final void clear() {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.BarcodeArDeserializerHelper
    public final BarcodeAr createMode(DataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BarcodeAr(context, new BarcodeArSettings());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.BarcodeArDeserializerHelper
    public final BarcodeArSettings createSettings() {
        return new BarcodeArSettings();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.BarcodeArDeserializerHelper
    public final void a(BarcodeAr mode, BarcodeArSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        BarcodeAr.applySettings$default(mode, settings, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ar.serialization.BarcodeArDeserializerHelper
    public final void a(BarcodeAr mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        if (json.contains(Feedback.TYPE)) {
            mode.setFeedback(BarcodeArFeedbackDeserializer.fromJson(json.requireByKey(Feedback.TYPE)));
        }
    }
}
