package com.scandit.datacapture.barcode.pick.serialization;

import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements BarcodePickDeserializerListener {
    public final WeakReference a;

    public b(BarcodePickDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerListener
    public final void onModeDeserializationFinished(BarcodePickDeserializer deserializer, BarcodePick mode, JsonValue json) {
        BarcodePickDeserializerListener barcodePickDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePickDeserializer barcodePickDeserializer = (BarcodePickDeserializer) this.a.get();
        if (barcodePickDeserializer == null || (barcodePickDeserializerListener = barcodePickDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodePickDeserializerListener.onModeDeserializationFinished(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerListener
    public final void onModeDeserializationStarted(BarcodePickDeserializer deserializer, BarcodePick mode, JsonValue json) {
        BarcodePickDeserializerListener barcodePickDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePickDeserializer barcodePickDeserializer = (BarcodePickDeserializer) this.a.get();
        if (barcodePickDeserializer == null || (barcodePickDeserializerListener = barcodePickDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodePickDeserializerListener.onModeDeserializationStarted(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerListener
    public final void onSettingsDeserializationFinished(BarcodePickDeserializer deserializer, BarcodePickSettings settings, JsonValue json) {
        BarcodePickDeserializerListener barcodePickDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePickDeserializer barcodePickDeserializer = (BarcodePickDeserializer) this.a.get();
        if (barcodePickDeserializer == null || (barcodePickDeserializerListener = barcodePickDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodePickDeserializerListener.onSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializerListener
    public final void onSettingsDeserializationStarted(BarcodePickDeserializer deserializer, BarcodePickSettings settings, JsonValue json) {
        BarcodePickDeserializerListener barcodePickDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodePickDeserializer barcodePickDeserializer = (BarcodePickDeserializer) this.a.get();
        if (barcodePickDeserializer == null || (barcodePickDeserializerListener = barcodePickDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodePickDeserializerListener.onSettingsDeserializationStarted(deserializer, settings, json);
    }
}
