package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeCaptureFeedbackDeserializer;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlayStyle;
import com.scandit.datacapture.core.area.LocationSelection;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.serialization.DataCaptureDeserializerHelper;
import com.scandit.datacapture.core.internal.sdk.area.NoLocationSelection;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NoViewfinder;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.viewfinder.Viewfinder;
import io.sentry.protocol.Feedback;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.capture.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0274f implements BarcodeCaptureDeserializerHelper, DataCaptureDeserializerHelper {
    public WeakReference a = new WeakReference(null);

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final void applySettings(BarcodeCapture mode, BarcodeCaptureSettings settings) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(settings, "settings");
        BarcodeCapture.applySettings$default(mode, settings, null, 2, null);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final void changeOverlayAddedToView(BarcodeCaptureOverlay overlay, DataCaptureView view, boolean z) {
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
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final BarcodeCapture createMode(DataCaptureContext dataCaptureContext) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        return BarcodeCapture.INSTANCE.forDataCaptureContext(dataCaptureContext, new BarcodeCaptureSettings());
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final BarcodeCaptureOverlay createOverlay(BarcodeCapture mode, BarcodeCaptureOverlayStyle style) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(style, "style");
        Object obj = this.a.get();
        Intrinsics.checkNotNull(obj);
        ((BarcodeCaptureDeserializer) obj).getViewfinderDeserializer()._getAndResetLastViewfinder();
        return BarcodeCaptureOverlay.INSTANCE.newInstance(mode, null, style);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final CameraSettings createRecommendedCameraSettings() {
        return BarcodeCapture.INSTANCE.createRecommendedCameraSettings();
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final BarcodeCaptureSettings createSettings() {
        Object obj = this.a.get();
        Intrinsics.checkNotNull(obj);
        ((BarcodeCaptureDeserializer) obj).getLocationSelectionDeserializer()._getAndResetLastLocationSelection();
        return new BarcodeCaptureSettings();
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final void updateModeFromJson(BarcodeCapture mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonValue byKeyAsObject = json.getByKeyAsObject(Feedback.TYPE, null);
        if (byKeyAsObject != null) {
            mode.setFeedback(BarcodeCaptureFeedbackDeserializer.fromJson(byKeyAsObject));
        }
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final void updateOverlayFromJson(BarcodeCaptureOverlay overlay, JsonValue json) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        Object obj = this.a.get();
        Intrinsics.checkNotNull(obj);
        Viewfinder _getAndResetLastViewfinder = ((BarcodeCaptureDeserializer) obj).getViewfinderDeserializer()._getAndResetLastViewfinder();
        if (_getAndResetLastViewfinder != null) {
            if (_getAndResetLastViewfinder instanceof NoViewfinder) {
                _getAndResetLastViewfinder = null;
            }
            overlay.setViewfinder(_getAndResetLastViewfinder);
        }
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerHelper
    public final void updateSettingsFromJson(BarcodeCaptureSettings settings, JsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        Object obj = this.a.get();
        Intrinsics.checkNotNull(obj);
        LocationSelection _getAndResetLastLocationSelection = ((BarcodeCaptureDeserializer) obj).getLocationSelectionDeserializer()._getAndResetLastLocationSelection();
        if (_getAndResetLastLocationSelection != null) {
            if (_getAndResetLastLocationSelection instanceof NoLocationSelection) {
                _getAndResetLastLocationSelection = null;
            }
            settings.setLocationSelection(_getAndResetLastLocationSelection);
        }
    }
}
