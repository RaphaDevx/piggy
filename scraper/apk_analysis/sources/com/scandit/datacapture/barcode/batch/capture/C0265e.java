package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlay;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.batch.capture.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0265e implements BarcodeBatchDeserializerListener {
    public final WeakReference a;

    public C0265e(BarcodeBatchDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onAdvancedOverlayDeserializationFinished(BarcodeBatchDeserializer deserializer, BarcodeBatchAdvancedOverlay overlay, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onAdvancedOverlayDeserializationFinished(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onAdvancedOverlayDeserializationStarted(BarcodeBatchDeserializer deserializer, BarcodeBatchAdvancedOverlay overlay, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onAdvancedOverlayDeserializationStarted(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onBasicOverlayDeserializationFinished(BarcodeBatchDeserializer deserializer, BarcodeBatchBasicOverlay overlay, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onBasicOverlayDeserializationFinished(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onBasicOverlayDeserializationStarted(BarcodeBatchDeserializer deserializer, BarcodeBatchBasicOverlay overlay, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onBasicOverlayDeserializationStarted(deserializer, overlay, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onModeDeserializationFinished(BarcodeBatchDeserializer deserializer, BarcodeBatch mode, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onModeDeserializationFinished(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onModeDeserializationStarted(BarcodeBatchDeserializer deserializer, BarcodeBatch mode, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onModeDeserializationStarted(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onSettingsDeserializationFinished(BarcodeBatchDeserializer deserializer, BarcodeBatchSettings settings, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchDeserializerListener
    public final void onSettingsDeserializationStarted(BarcodeBatchDeserializer deserializer, BarcodeBatchSettings settings, JsonValue json) {
        BarcodeBatchDeserializerListener barcodeBatchDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeBatchDeserializer barcodeBatchDeserializer = (BarcodeBatchDeserializer) this.a.get();
        if (barcodeBatchDeserializer == null || (barcodeBatchDeserializerListener = barcodeBatchDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchDeserializerListener.onSettingsDeserializationStarted(deserializer, settings, json);
    }
}
