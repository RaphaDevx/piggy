package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindSettings;
import com.scandit.datacapture.barcode.find.feedback.BarcodeFindFeedback;
import com.scandit.datacapture.core.common.feedback.Feedback;
import com.scandit.datacapture.core.internal.sdk.common.feedback.FeedbackDeserializer;
import com.scandit.datacapture.core.json.JsonValue;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodeFindDeserializerHelper {
    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.BarcodeFindDeserializerHelper
    public final void a(BarcodeFindSettings settings, JsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.BarcodeFindDeserializerHelper
    public final BarcodeFind b() {
        return new BarcodeFind(new BarcodeFindSettings());
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper
    public final void clear() {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.BarcodeFindDeserializerHelper
    public final BarcodeFindSettings createSettings() {
        return new BarcodeFindSettings();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.BarcodeFindDeserializerHelper
    public final void a(BarcodeFind mode, BarcodeFindSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        mode.applySettings(settings);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.BarcodeFindDeserializerHelper
    public final void a(BarcodeFind mode, JsonValue json) {
        Feedback found;
        Feedback itemListUpdated;
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        if (json.contains(io.sentry.protocol.Feedback.TYPE)) {
            JsonValue json2 = json.requireByKey(io.sentry.protocol.Feedback.TYPE);
            Intrinsics.checkNotNullParameter(json2, "json");
            BarcodeFindFeedback defaultFeedback = BarcodeFindFeedback.INSTANCE.defaultFeedback();
            if (json2.contains("found")) {
                found = FeedbackDeserializer.fromJson(json2.requireByKey("found"));
            } else {
                found = defaultFeedback.getFound();
            }
            if (json2.contains("itemListUpdated")) {
                itemListUpdated = FeedbackDeserializer.fromJson(json2.requireByKey("itemListUpdated"));
            } else {
                itemListUpdated = defaultFeedback.getItemListUpdated();
            }
            BarcodeFindFeedback barcodeFindFeedback = new BarcodeFindFeedback();
            barcodeFindFeedback.setFound(found);
            barcodeFindFeedback.setItemListUpdated(itemListUpdated);
            mode.setFeedback(barcodeFindFeedback);
        }
    }
}
