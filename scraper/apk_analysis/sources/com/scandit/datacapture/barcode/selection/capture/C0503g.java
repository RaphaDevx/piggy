package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeSelectionFeedbackDeserializer;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlay;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayStyle;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.viewfinder.AimerViewfinder;
import com.scandit.datacapture.core.ui.viewfinder.Viewfinder;
import io.sentry.protocol.Feedback;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.selection.capture.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0503g implements BarcodeSelectionDeserializerHelper, DataCaptureDeserializerHelper {
    public WeakReference a = new WeakReference(null);
    public BarcodeSelectionType b;
    public BarcodeSelectionStrategy c;

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final void applySettings(BarcodeSelection mode, BarcodeSelectionSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        BarcodeSelection.applySettings$default(mode, settings, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final void changeBasicOverlayAddedToView(BarcodeSelectionBasicOverlay overlay, DataCaptureView view, boolean z) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(view, "view");
        if (z) {
            view.addOverlay(overlay);
        } else {
            view.removeOverlay(overlay);
        }
    }

    @Override // com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper
    public final void clear() {
        this.b = null;
        this.c = null;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelectionAimerSelection createAimerSelection() {
        BarcodeSelectionAimerSelection barcodeSelectionAimerSelection = new BarcodeSelectionAimerSelection();
        this.b = barcodeSelectionAimerSelection;
        return barcodeSelectionAimerSelection;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelectionAutoSelectionStrategy createAutoSelectionStrategy() {
        BarcodeSelectionAutoSelectionStrategy barcodeSelectionAutoSelectionStrategy = new BarcodeSelectionAutoSelectionStrategy();
        this.c = barcodeSelectionAutoSelectionStrategy;
        return barcodeSelectionAutoSelectionStrategy;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelectionBasicOverlay createBasicOverlay(BarcodeSelection mode, BarcodeSelectionBasicOverlayStyle style) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(style, "style");
        Object obj = this.a.get();
        Intrinsics.checkNotNull(obj);
        Viewfinder _getAndResetLastViewfinder = ((BarcodeSelectionDeserializer) obj).getViewfinderDeserializer()._getAndResetLastViewfinder();
        BarcodeSelectionBasicOverlay newInstance = BarcodeSelectionBasicOverlay.INSTANCE.newInstance(mode, null, style);
        if ((_getAndResetLastViewfinder instanceof AimerViewfinder) && (newInstance.getViewfinder() instanceof AimerViewfinder)) {
            AimerViewfinder aimerViewfinder = (AimerViewfinder) _getAndResetLastViewfinder;
            ((AimerViewfinder) newInstance.getViewfinder()).setDotColor(aimerViewfinder.getDotColor());
            ((AimerViewfinder) newInstance.getViewfinder()).setFrameColor(aimerViewfinder.getFrameColor());
        }
        return newInstance;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelectionManualSelectionStrategy createManualSelectionStrategy() {
        BarcodeSelectionManualSelectionStrategy barcodeSelectionManualSelectionStrategy = new BarcodeSelectionManualSelectionStrategy();
        this.c = barcodeSelectionManualSelectionStrategy;
        return barcodeSelectionManualSelectionStrategy;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelection createMode(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        return BarcodeSelection.INSTANCE.forDataCaptureContext(dataCaptureContext, new BarcodeSelectionSettings());
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final CameraSettings createRecommendedCameraSettings() {
        return BarcodeSelection.INSTANCE.createRecommendedCameraSettings();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelectionSettings createSettings() {
        return new BarcodeSelectionSettings();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final BarcodeSelectionTapSelection createTapSelection() {
        BarcodeSelectionTapSelection barcodeSelectionTapSelection = new BarcodeSelectionTapSelection();
        this.b = barcodeSelectionTapSelection;
        return barcodeSelectionTapSelection;
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final void updateBasicOverlayFromJson(BarcodeSelectionBasicOverlay overlay, JsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        Object obj = this.a.get();
        Intrinsics.checkNotNull(obj);
        Viewfinder _getAndResetLastViewfinder = ((BarcodeSelectionDeserializer) obj).getViewfinderDeserializer()._getAndResetLastViewfinder();
        Viewfinder viewfinder = overlay.getViewfinder();
        if ((_getAndResetLastViewfinder instanceof AimerViewfinder) && (viewfinder instanceof AimerViewfinder)) {
            AimerViewfinder aimerViewfinder = (AimerViewfinder) viewfinder;
            AimerViewfinder aimerViewfinder2 = (AimerViewfinder) _getAndResetLastViewfinder;
            aimerViewfinder.setDotColor(aimerViewfinder2.getDotColor());
            aimerViewfinder.setFrameColor(aimerViewfinder2.getFrameColor());
        }
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final void updateModeFromJson(BarcodeSelection mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonValue byKeyAsObject = json.getByKeyAsObject(Feedback.TYPE, null);
        if (byKeyAsObject != null) {
            mode.setFeedback(BarcodeSelectionFeedbackDeserializer.fromJson(byKeyAsObject));
        }
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerHelper
    public final void updateSettingsFromJson(BarcodeSelectionSettings settings, JsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionType barcodeSelectionType = this.b;
        if (barcodeSelectionType != null) {
            BarcodeSelectionStrategy barcodeSelectionStrategy = this.c;
            if (barcodeSelectionStrategy != null) {
                BarcodeSelectionAimerSelection barcodeSelectionAimerSelection = barcodeSelectionType instanceof BarcodeSelectionAimerSelection ? (BarcodeSelectionAimerSelection) barcodeSelectionType : null;
                if (barcodeSelectionAimerSelection != null) {
                    barcodeSelectionAimerSelection.setSelectionStrategy(barcodeSelectionStrategy);
                }
            }
            settings.setSelectionType(barcodeSelectionType);
        }
    }
}
