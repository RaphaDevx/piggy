package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindSettings;
import com.scandit.datacapture.barcode.find.serialization.BarcodeFindDeserializer;
import com.scandit.datacapture.barcode.find.serialization.BarcodeFindDeserializerListener;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSettings;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class n extends NativeBarcodeFindDeserializerListener {
    public final e a;
    public final ProxyCache b;
    public final WeakReference c;

    public n(com.scandit.datacapture.barcode.find.serialization.b _BarcodeFindDeserializerInternalListener, BarcodeFindDeserializer _BarcodeFindDeserializer) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_BarcodeFindDeserializerInternalListener, "_BarcodeFindDeserializerInternalListener");
        Intrinsics.checkNotNullParameter(_BarcodeFindDeserializer, "_BarcodeFindDeserializer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _BarcodeFindDeserializerInternalListener;
        this.b = proxyCache;
        this.c = new WeakReference(_BarcodeFindDeserializer);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerListener
    public final void onModeDeserializationFinished(NativeBarcodeFindDeserializer deserializer, NativeBarcodeFind mode, NativeJsonValue json) {
        BarcodeFindDeserializerListener barcodeFindDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeFindDeserializer barcodeFindDeserializer = (BarcodeFindDeserializer) this.c.get();
        if (barcodeFindDeserializer != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFindDeserializer.class), null, deserializer, new f(barcodeFindDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeFindDeserializer deserializer2 = (BarcodeFindDeserializer) orPut;
            BarcodeFind mode2 = (BarcodeFind) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode);
            JsonValue json2 = (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new g(json));
            com.scandit.datacapture.barcode.find.serialization.b bVar = (com.scandit.datacapture.barcode.find.serialization.b) this.a;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(deserializer2, "deserializer");
            Intrinsics.checkNotNullParameter(mode2, "mode");
            Intrinsics.checkNotNullParameter(json2, "json");
            BarcodeFindDeserializer barcodeFindDeserializer2 = (BarcodeFindDeserializer) bVar.a.get();
            if (barcodeFindDeserializer2 == null || (barcodeFindDeserializerListener = barcodeFindDeserializer2.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
                return;
            }
            barcodeFindDeserializerListener.onModeDeserializationFinished(deserializer2, mode2, json2);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerListener
    public final void onModeDeserializationStarted(NativeBarcodeFindDeserializer deserializer, NativeBarcodeFind mode, NativeJsonValue json) {
        BarcodeFindDeserializerListener barcodeFindDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeFindDeserializer barcodeFindDeserializer = (BarcodeFindDeserializer) this.c.get();
        if (barcodeFindDeserializer != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFindDeserializer.class), null, deserializer, new h(barcodeFindDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeFindDeserializer deserializer2 = (BarcodeFindDeserializer) orPut;
            BarcodeFind mode2 = (BarcodeFind) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFind.class), null, mode);
            JsonValue json2 = (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new i(json));
            com.scandit.datacapture.barcode.find.serialization.b bVar = (com.scandit.datacapture.barcode.find.serialization.b) this.a;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(deserializer2, "deserializer");
            Intrinsics.checkNotNullParameter(mode2, "mode");
            Intrinsics.checkNotNullParameter(json2, "json");
            BarcodeFindDeserializer barcodeFindDeserializer2 = (BarcodeFindDeserializer) bVar.a.get();
            if (barcodeFindDeserializer2 == null || (barcodeFindDeserializerListener = barcodeFindDeserializer2.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
                return;
            }
            barcodeFindDeserializerListener.onModeDeserializationStarted(deserializer2, mode2, json2);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerListener
    public final void onSettingsDeserializationFinished(NativeBarcodeFindDeserializer deserializer, NativeBarcodeFindSettings settings, NativeJsonValue json) {
        BarcodeFindDeserializerListener barcodeFindDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeFindDeserializer barcodeFindDeserializer = (BarcodeFindDeserializer) this.c.get();
        if (barcodeFindDeserializer != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFindDeserializer.class), null, deserializer, new j(barcodeFindDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeFindDeserializer deserializer2 = (BarcodeFindDeserializer) orPut;
            BarcodeFindSettings settings2 = (BarcodeFindSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFindSettings.class), null, settings);
            JsonValue json2 = (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new k(json));
            com.scandit.datacapture.barcode.find.serialization.b bVar = (com.scandit.datacapture.barcode.find.serialization.b) this.a;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(deserializer2, "deserializer");
            Intrinsics.checkNotNullParameter(settings2, "settings");
            Intrinsics.checkNotNullParameter(json2, "json");
            BarcodeFindDeserializer barcodeFindDeserializer2 = (BarcodeFindDeserializer) bVar.a.get();
            if (barcodeFindDeserializer2 == null || (barcodeFindDeserializerListener = barcodeFindDeserializer2.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
                return;
            }
            barcodeFindDeserializerListener.onSettingsDeserializationFinished(deserializer2, settings2, json2);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializerListener
    public final void onSettingsDeserializationStarted(NativeBarcodeFindDeserializer deserializer, NativeBarcodeFindSettings settings, NativeJsonValue json) {
        BarcodeFindDeserializerListener barcodeFindDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        BarcodeFindDeserializer barcodeFindDeserializer = (BarcodeFindDeserializer) this.c.get();
        if (barcodeFindDeserializer != null) {
            Object orPut = this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeBarcodeFindDeserializer.class), null, deserializer, new l(barcodeFindDeserializer));
            Intrinsics.checkNotNullExpressionValue(orPut, "getOrPut(...)");
            BarcodeFindDeserializer deserializer2 = (BarcodeFindDeserializer) orPut;
            BarcodeFindSettings settings2 = (BarcodeFindSettings) this.b.require(Reflection.getOrCreateKotlinClass(NativeBarcodeFindSettings.class), null, settings);
            JsonValue json2 = (JsonValue) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeJsonValue.class), null, json, new m(json));
            com.scandit.datacapture.barcode.find.serialization.b bVar = (com.scandit.datacapture.barcode.find.serialization.b) this.a;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(deserializer2, "deserializer");
            Intrinsics.checkNotNullParameter(settings2, "settings");
            Intrinsics.checkNotNullParameter(json2, "json");
            BarcodeFindDeserializer barcodeFindDeserializer2 = (BarcodeFindDeserializer) bVar.a.get();
            if (barcodeFindDeserializer2 == null || (barcodeFindDeserializerListener = barcodeFindDeserializer2.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
                return;
            }
            barcodeFindDeserializerListener.onSettingsDeserializationStarted(deserializer2, settings2, json2);
        }
    }
}
