package com.scandit.datacapture.barcode.spark.serialization;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.barcode.data.CapturePreset;
import com.scandit.datacapture.barcode.internal.module.spark.serialization.SparkScanSettingsDeserializer;
import com.scandit.datacapture.barcode.spark.capture.SparkScan;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSettings;
import com.scandit.datacapture.core.json.JsonValue;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B;\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u001a\u0010\b\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00070\u0004\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\f\u0010\rB\t\b\u0016¢\u0006\u0004\b\f\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0019\u0010\u001aR$\u0010\"\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializer;", "", "Lcom/scandit/datacapture/barcode/internal/module/spark/serialization/SparkScanSettingsDeserializer;", "settingsDeserializer", "Lkotlin/Function1;", "", "Lcom/scandit/datacapture/barcode/data/CapturePreset;", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "settingsFactory", "Lkotlin/Function0;", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "modeFactory", "<init>", "(Lcom/scandit/datacapture/barcode/internal/module/spark/serialization/SparkScanSettingsDeserializer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "()V", "", "jsonData", "modeFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "mode", "updateModeFromJson", "(Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "settingsFromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "settings", "updateSettingsFromJson", "(Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;Ljava/lang/String;)Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializerListener;", "d", "Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializerListener;", "getListener", "()Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializerListener;", "setListener", "(Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializerListener;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class SparkScanDeserializer {
    private final SparkScanSettingsDeserializer a;
    private final Function1 b;
    private final Function0 c;

    /* renamed from: d, reason: from kotlin metadata */
    private SparkScanDeserializerListener listener;

    public SparkScanDeserializer(SparkScanSettingsDeserializer settingsDeserializer, Function1<? super Set<? extends CapturePreset>, SparkScanSettings> settingsFactory, Function0<SparkScan> modeFactory) {
        Intrinsics.checkNotNullParameter(settingsDeserializer, "settingsDeserializer");
        Intrinsics.checkNotNullParameter(settingsFactory, "settingsFactory");
        Intrinsics.checkNotNullParameter(modeFactory, "modeFactory");
        this.a = settingsDeserializer;
        this.b = settingsFactory;
        this.c = modeFactory;
    }

    private final void a(SparkScan sparkScan, JsonValue jsonValue) {
        SparkScanDeserializerListener sparkScanDeserializerListener = this.listener;
        if (sparkScanDeserializerListener != null) {
            sparkScanDeserializerListener.onModeDeserializationStarted(this, sparkScan, jsonValue);
        }
        String byKeyAsString = jsonValue.getByKeyAsString("type", "");
        if (!Intrinsics.areEqual(byKeyAsString, "sparkScan")) {
            throw new JSONException("invalid type, \"sparkScan\" expected, but found \"" + byKeyAsString + '\"');
        }
        JsonValue byKeyAsObject = jsonValue.getByKeyAsObject("settings", new JsonValue("{}"));
        if (byKeyAsObject == null) {
            byKeyAsObject = new JsonValue("{}");
        }
        SparkScanSettings sparkScanSettings = (SparkScanSettings) this.b.invoke(this.a.a(byKeyAsObject));
        SparkScanDeserializerListener sparkScanDeserializerListener2 = this.listener;
        if (sparkScanDeserializerListener2 != null) {
            sparkScanDeserializerListener2.onSettingsDeserializationStarted(this, sparkScanSettings, byKeyAsObject);
        }
        this.a.a(sparkScanSettings, byKeyAsObject);
        SparkScanDeserializerListener sparkScanDeserializerListener3 = this.listener;
        if (sparkScanDeserializerListener3 != null) {
            sparkScanDeserializerListener3.onSettingsDeserializationFinished(this, sparkScanSettings, byKeyAsObject);
        }
        sparkScan.applySettings(sparkScanSettings);
        SparkScanDeserializerListener sparkScanDeserializerListener4 = this.listener;
        if (sparkScanDeserializerListener4 != null) {
            sparkScanDeserializerListener4.onModeDeserializationFinished(this, sparkScan, jsonValue);
        }
    }

    public final SparkScanDeserializerListener getListener() {
        return this.listener;
    }

    public final SparkScan modeFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        JsonValue jsonValue = new JsonValue(jsonData);
        SparkScan sparkScan = (SparkScan) this.c.invoke();
        a(sparkScan, jsonValue);
        return sparkScan;
    }

    public final void setListener(SparkScanDeserializerListener sparkScanDeserializerListener) {
        this.listener = sparkScanDeserializerListener;
    }

    public final SparkScanSettings settingsFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        JsonValue jsonValue = new JsonValue(jsonData);
        SparkScanSettings sparkScanSettings = (SparkScanSettings) this.b.invoke(this.a.a(jsonValue));
        SparkScanDeserializerListener sparkScanDeserializerListener = this.listener;
        if (sparkScanDeserializerListener != null) {
            sparkScanDeserializerListener.onSettingsDeserializationStarted(this, sparkScanSettings, jsonValue);
        }
        this.a.a(sparkScanSettings, jsonValue);
        SparkScanDeserializerListener sparkScanDeserializerListener2 = this.listener;
        if (sparkScanDeserializerListener2 != null) {
            sparkScanDeserializerListener2.onSettingsDeserializationFinished(this, sparkScanSettings, jsonValue);
        }
        return sparkScanSettings;
    }

    public final SparkScan updateModeFromJson(SparkScan mode, String jsonData) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        a(mode, new JsonValue(jsonData));
        return mode;
    }

    public final SparkScanSettings updateSettingsFromJson(SparkScanSettings settings, String jsonData) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        JsonValue jsonValue = new JsonValue(jsonData);
        SparkScanDeserializerListener sparkScanDeserializerListener = this.listener;
        if (sparkScanDeserializerListener != null) {
            sparkScanDeserializerListener.onSettingsDeserializationStarted(this, settings, jsonValue);
        }
        this.a.a(settings, jsonValue);
        SparkScanDeserializerListener sparkScanDeserializerListener2 = this.listener;
        if (sparkScanDeserializerListener2 != null) {
            sparkScanDeserializerListener2.onSettingsDeserializationFinished(this, settings, jsonValue);
        }
        return settings;
    }

    public SparkScanDeserializer() {
        this(new com.scandit.datacapture.barcode.internal.module.spark.serialization.a(), a.a, b.a);
    }
}
