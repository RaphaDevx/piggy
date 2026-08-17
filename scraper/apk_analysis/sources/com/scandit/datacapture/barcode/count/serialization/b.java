package com.scandit.datacapture.barcode.count.serialization;

import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.capture.BarcodeCountSettings;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements BarcodeCountDeserializerListener {
    public final WeakReference a;

    public b(BarcodeCountDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializerListener
    public final void onBasicOverlayDeserializationFinished(BarcodeCountDeserializer deserializer, BarcodeCountBasicOverlay overlay, JsonValue json) {
        BarcodeCountDeserializerListener barcodeCountDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.a.get();
        if (barcodeCountDeserializer == null || (barcodeCountDeserializerListener = barcodeCountDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountDeserializerListener.onBasicOverlayDeserializationFinished(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializerListener
    public final void onBasicOverlayDeserializationStarted(BarcodeCountDeserializer deserializer, BarcodeCountBasicOverlay overlay, JsonValue json) {
        BarcodeCountDeserializerListener barcodeCountDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.a.get();
        if (barcodeCountDeserializer == null || (barcodeCountDeserializerListener = barcodeCountDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountDeserializerListener.onBasicOverlayDeserializationStarted(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializerListener
    public final void onModeDeserializationFinished(BarcodeCountDeserializer deserializer, BarcodeCount mode, JsonValue json) {
        BarcodeCountDeserializerListener barcodeCountDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.a.get();
        if (barcodeCountDeserializer == null || (barcodeCountDeserializerListener = barcodeCountDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountDeserializerListener.onModeDeserializationFinished(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializerListener
    public final void onModeDeserializationStarted(BarcodeCountDeserializer deserializer, BarcodeCount mode, JsonValue json) {
        BarcodeCountDeserializerListener barcodeCountDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.a.get();
        if (barcodeCountDeserializer == null || (barcodeCountDeserializerListener = barcodeCountDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountDeserializerListener.onModeDeserializationStarted(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializerListener
    public final void onSettingsDeserializationFinished(BarcodeCountDeserializer deserializer, BarcodeCountSettings settings, JsonValue json) {
        BarcodeCountDeserializerListener barcodeCountDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.a.get();
        if (barcodeCountDeserializer == null || (barcodeCountDeserializerListener = barcodeCountDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountDeserializerListener.onSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializerListener
    public final void onSettingsDeserializationStarted(BarcodeCountDeserializer deserializer, BarcodeCountSettings settings, JsonValue json) {
        BarcodeCountDeserializerListener barcodeCountDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeCountDeserializer barcodeCountDeserializer = (BarcodeCountDeserializer) this.a.get();
        if (barcodeCountDeserializer == null || (barcodeCountDeserializerListener = barcodeCountDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountDeserializerListener.onSettingsDeserializationStarted(deserializer, settings, json);
    }
}
