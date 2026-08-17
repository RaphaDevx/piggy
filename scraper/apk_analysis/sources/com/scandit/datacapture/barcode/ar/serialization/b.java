package com.scandit.datacapture.barcode.ar.serialization;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSettings;
import com.scandit.datacapture.core.json.JsonValue;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements BarcodeArDeserializerListener {
    public final WeakReference a;

    public b(BarcodeArDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.ar.serialization.BarcodeArDeserializerListener
    public final void onModeDeserializationFinished(BarcodeArDeserializer deserializer, BarcodeAr mode, JsonValue json) {
        BarcodeArDeserializerListener barcodeArDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeArDeserializer barcodeArDeserializer = (BarcodeArDeserializer) this.a.get();
        if (barcodeArDeserializer == null || (barcodeArDeserializerListener = barcodeArDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeArDeserializerListener.onModeDeserializationFinished(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.ar.serialization.BarcodeArDeserializerListener
    public final void onModeDeserializationStarted(BarcodeArDeserializer deserializer, BarcodeAr mode, JsonValue json) {
        BarcodeArDeserializerListener barcodeArDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeArDeserializer barcodeArDeserializer = (BarcodeArDeserializer) this.a.get();
        if (barcodeArDeserializer == null || (barcodeArDeserializerListener = barcodeArDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeArDeserializerListener.onModeDeserializationStarted(deserializer, mode, json);
    }

    @Override // com.scandit.datacapture.barcode.ar.serialization.BarcodeArDeserializerListener
    public final void onSettingsDeserializationFinished(BarcodeArDeserializer deserializer, BarcodeArSettings settings, JsonValue json) {
        BarcodeArDeserializerListener barcodeArDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeArDeserializer barcodeArDeserializer = (BarcodeArDeserializer) this.a.get();
        if (barcodeArDeserializer == null || (barcodeArDeserializerListener = barcodeArDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeArDeserializerListener.onSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.barcode.ar.serialization.BarcodeArDeserializerListener
    public final void onSettingsDeserializationStarted(BarcodeArDeserializer deserializer, BarcodeArSettings settings, JsonValue json) {
        BarcodeArDeserializerListener barcodeArDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeArDeserializer barcodeArDeserializer = (BarcodeArDeserializer) this.a.get();
        if (barcodeArDeserializer == null || (barcodeArDeserializerListener = barcodeArDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeArDeserializerListener.onSettingsDeserializationStarted(deserializer, settings, json);
    }
}
