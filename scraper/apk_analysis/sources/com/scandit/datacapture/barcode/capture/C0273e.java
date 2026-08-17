package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.capture.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0273e implements BarcodeCaptureDeserializerListener {
    public final WeakReference a;

    public C0273e(BarcodeCaptureDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerListener
    public final void onModeDeserializationFinished(BarcodeCaptureDeserializer deserializer, BarcodeCapture mode, JsonValue json) {
        BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = (BarcodeCaptureDeserializer) this.a.get();
        if (barcodeCaptureDeserializer == null || (barcodeCaptureDeserializerListener = barcodeCaptureDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCaptureDeserializerListener.onModeDeserializationFinished(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerListener
    public final void onModeDeserializationStarted(BarcodeCaptureDeserializer deserializer, BarcodeCapture mode, JsonValue json) {
        BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = (BarcodeCaptureDeserializer) this.a.get();
        if (barcodeCaptureDeserializer == null || (barcodeCaptureDeserializerListener = barcodeCaptureDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCaptureDeserializerListener.onModeDeserializationStarted(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerListener
    public final void onOverlayDeserializationFinished(BarcodeCaptureDeserializer deserializer, BarcodeCaptureOverlay overlay, JsonValue json) {
        BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = (BarcodeCaptureDeserializer) this.a.get();
        if (barcodeCaptureDeserializer == null || (barcodeCaptureDeserializerListener = barcodeCaptureDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCaptureDeserializerListener.onOverlayDeserializationFinished(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerListener
    public final void onOverlayDeserializationStarted(BarcodeCaptureDeserializer deserializer, BarcodeCaptureOverlay overlay, JsonValue json) {
        BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = (BarcodeCaptureDeserializer) this.a.get();
        if (barcodeCaptureDeserializer == null || (barcodeCaptureDeserializerListener = barcodeCaptureDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCaptureDeserializerListener.onOverlayDeserializationStarted(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerListener
    public final void onSettingsDeserializationFinished(BarcodeCaptureDeserializer deserializer, BarcodeCaptureSettings settings, JsonValue json) {
        BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = (BarcodeCaptureDeserializer) this.a.get();
        if (barcodeCaptureDeserializer == null || (barcodeCaptureDeserializerListener = barcodeCaptureDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCaptureDeserializerListener.onSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializerListener
    public final void onSettingsDeserializationStarted(BarcodeCaptureDeserializer deserializer, BarcodeCaptureSettings settings, JsonValue json) {
        BarcodeCaptureDeserializerListener barcodeCaptureDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCaptureDeserializer barcodeCaptureDeserializer = (BarcodeCaptureDeserializer) this.a.get();
        if (barcodeCaptureDeserializer == null || (barcodeCaptureDeserializerListener = barcodeCaptureDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCaptureDeserializerListener.onSettingsDeserializationStarted(deserializer, settings, json);
    }
}
