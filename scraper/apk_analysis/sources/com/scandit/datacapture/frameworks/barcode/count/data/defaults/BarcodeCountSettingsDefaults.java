package com.scandit.datacapture.frameworks.barcode.count.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.capture.BarcodeCountSettings;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeCountSettingsDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountSettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "barcodeCountSettings", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;", "(Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountSettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_FILTER_SETTINGS = "barcodeFilterSettings";
    private static final String FIELD_EXPECTS_ONLY_UNIQUE_BARCODES = "expectOnlyUniqueBarcodes";
    private static final String FIELD_MAPPING_ENABLED = "mappingEnabled";
    private final BarcodeCountSettings barcodeCountSettings;

    public /* synthetic */ BarcodeCountSettingsDefaults(BarcodeCountSettings barcodeCountSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeCountSettings);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeCountSettingsDefaults(BarcodeCountSettings barcodeCountSettings) {
        this.barcodeCountSettings = barcodeCountSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_BARCODE_FILTER_SETTINGS, BarcodeFilterSettingsDefaults.INSTANCE.get(this.barcodeCountSettings.getFilterSettings())), TuplesKt.to(FIELD_EXPECTS_ONLY_UNIQUE_BARCODES, Boolean.valueOf(this.barcodeCountSettings.getExpectsOnlyUniqueBarcodes())), TuplesKt.to(FIELD_MAPPING_ENABLED, Boolean.valueOf(this.barcodeCountSettings.getMappingEnabled())));
    }

    /* compiled from: BarcodeCountSettingsDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountSettingsDefaults$Companion;", "", "()V", "FIELD_BARCODE_FILTER_SETTINGS", "", "FIELD_EXPECTS_ONLY_UNIQUE_BARCODES", "FIELD_MAPPING_ENABLED", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeCountSettingsDefaults(new BarcodeCountSettings(), null).toMap();
        }
    }
}
