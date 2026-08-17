package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlay;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.selection.capture.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0502f implements BarcodeSelectionDeserializerListener {
    public final WeakReference a;

    public C0502f(BarcodeSelectionDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public final void onBasicOverlayDeserializationFinished(BarcodeSelectionDeserializer deserializer, BarcodeSelectionBasicOverlay overlay, JsonValue json) {
        BarcodeSelectionDeserializerListener barcodeSelectionDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = (BarcodeSelectionDeserializer) this.a.get();
        if (barcodeSelectionDeserializer == null || (barcodeSelectionDeserializerListener = barcodeSelectionDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeSelectionDeserializerListener.onBasicOverlayDeserializationFinished(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public final void onBasicOverlayDeserializationStarted(BarcodeSelectionDeserializer deserializer, BarcodeSelectionBasicOverlay overlay, JsonValue json) {
        BarcodeSelectionDeserializerListener barcodeSelectionDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = (BarcodeSelectionDeserializer) this.a.get();
        if (barcodeSelectionDeserializer == null || (barcodeSelectionDeserializerListener = barcodeSelectionDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeSelectionDeserializerListener.onBasicOverlayDeserializationStarted(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public final void onModeDeserializationFinished(BarcodeSelectionDeserializer deserializer, BarcodeSelection mode, JsonValue json) {
        BarcodeSelectionDeserializerListener barcodeSelectionDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = (BarcodeSelectionDeserializer) this.a.get();
        if (barcodeSelectionDeserializer == null || (barcodeSelectionDeserializerListener = barcodeSelectionDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeSelectionDeserializerListener.onModeDeserializationFinished(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public final void onModeDeserializationStarted(BarcodeSelectionDeserializer deserializer, BarcodeSelection mode, JsonValue json) {
        BarcodeSelectionDeserializerListener barcodeSelectionDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = (BarcodeSelectionDeserializer) this.a.get();
        if (barcodeSelectionDeserializer == null || (barcodeSelectionDeserializerListener = barcodeSelectionDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeSelectionDeserializerListener.onModeDeserializationStarted(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public final void onSettingsDeserializationFinished(BarcodeSelectionDeserializer deserializer, BarcodeSelectionSettings settings, JsonValue json) {
        BarcodeSelectionDeserializerListener barcodeSelectionDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = (BarcodeSelectionDeserializer) this.a.get();
        if (barcodeSelectionDeserializer == null || (barcodeSelectionDeserializerListener = barcodeSelectionDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeSelectionDeserializerListener.onSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public final void onSettingsDeserializationStarted(BarcodeSelectionDeserializer deserializer, BarcodeSelectionSettings settings, JsonValue json) {
        BarcodeSelectionDeserializerListener barcodeSelectionDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeSelectionDeserializer barcodeSelectionDeserializer = (BarcodeSelectionDeserializer) this.a.get();
        if (barcodeSelectionDeserializer == null || (barcodeSelectionDeserializerListener = barcodeSelectionDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeSelectionDeserializerListener.onSettingsDeserializationStarted(deserializer, settings, json);
    }
}
