package com.scandit.datacapture.frameworks.barcode.spark.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSettings;
import com.scandit.datacapture.barcode.spark.serialization.BatterySavingModeSerializer;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SparkScanSettingsDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanSettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "sparkScanSettings", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;", "(Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SparkScanSettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BATTERY_SAVING = "batterySaving";
    private static final String FIELD_CODE_DUPLICATE_FILTER = "codeDuplicateFilter";
    private static final String FIELD_SCAN_INTENTION = "scanIntention";
    private final SparkScanSettings sparkScanSettings;

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    public SparkScanSettingsDefaults(SparkScanSettings sparkScanSettings) {
        Intrinsics.checkNotNullParameter(sparkScanSettings, "sparkScanSettings");
        this.sparkScanSettings = sparkScanSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_CODE_DUPLICATE_FILTER, Integer.valueOf((int) this.sparkScanSettings.getCodeDuplicateFilter().asMillis())), TuplesKt.to(FIELD_BATTERY_SAVING, BatterySavingModeSerializer.toJson(this.sparkScanSettings.getBatterySaving())), TuplesKt.to(FIELD_SCAN_INTENTION, ExtensionsKt.toJson(this.sparkScanSettings.getScanIntention())));
    }

    /* compiled from: SparkScanSettingsDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanSettingsDefaults$Companion;", "", "()V", "FIELD_BATTERY_SAVING", "", "FIELD_CODE_DUPLICATE_FILTER", "FIELD_SCAN_INTENTION", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new SparkScanSettingsDefaults(new SparkScanSettings()).toMap();
        }
    }
}
