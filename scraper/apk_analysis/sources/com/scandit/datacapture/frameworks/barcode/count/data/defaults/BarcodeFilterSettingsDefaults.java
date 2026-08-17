package com.scandit.datacapture.frameworks.barcode.count.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.filter.capture.BarcodeFilterSettings;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeFilterSettingsDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeFilterSettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "barcodeFilterSettings", "Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "(Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeFilterSettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_EXCLUDED_SYMBOLOGIES = "excludedSymbologies";
    private static final String FIELD_EXCLUDED_SYMBOL_COUNTS = "excludedSymbolCounts";
    private static final String FIELD_EXCLUDE_EAN13 = "excludeEan13";
    private static final String FIELD_EXCLUDE_REGEX = "excludedCodesRegex";
    private static final String FIELD_EXCLUDE_UPCA = "excludeUpca";
    private final BarcodeFilterSettings barcodeFilterSettings;

    public /* synthetic */ BarcodeFilterSettingsDefaults(BarcodeFilterSettings barcodeFilterSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeFilterSettings);
    }

    @JvmStatic
    public static final Map<String, Object> get(BarcodeFilterSettings barcodeFilterSettings) {
        return INSTANCE.get(barcodeFilterSettings);
    }

    private BarcodeFilterSettingsDefaults(BarcodeFilterSettings barcodeFilterSettings) {
        this.barcodeFilterSettings = barcodeFilterSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        Pair[] pairArr = new Pair[5];
        pairArr[0] = TuplesKt.to(FIELD_EXCLUDE_EAN13, Boolean.valueOf(this.barcodeFilterSettings.getExcludeEan13()));
        pairArr[1] = TuplesKt.to(FIELD_EXCLUDE_UPCA, Boolean.valueOf(this.barcodeFilterSettings.getExcludeUpca()));
        pairArr[2] = TuplesKt.to(FIELD_EXCLUDE_REGEX, this.barcodeFilterSettings.getExcludedCodesRegex());
        pairArr[3] = TuplesKt.to(FIELD_EXCLUDED_SYMBOL_COUNTS, this.barcodeFilterSettings.getExcludedSymbolCounts());
        Set<Symbology> excludedSymbologies = this.barcodeFilterSettings.getExcludedSymbologies();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(excludedSymbologies, 10));
        Iterator<T> it = excludedSymbologies.iterator();
        while (it.hasNext()) {
            arrayList.add(((Symbology) it.next()).name());
        }
        pairArr[4] = TuplesKt.to(FIELD_EXCLUDED_SYMBOLOGIES, arrayList);
        return MapsKt.mapOf(pairArr);
    }

    /* compiled from: BarcodeFilterSettingsDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeFilterSettingsDefaults$Companion;", "", "()V", "FIELD_EXCLUDED_SYMBOLOGIES", "", "FIELD_EXCLUDED_SYMBOL_COUNTS", "FIELD_EXCLUDE_EAN13", "FIELD_EXCLUDE_REGEX", "FIELD_EXCLUDE_UPCA", "get", "", "barcodeFilterSettings", "Lcom/scandit/datacapture/barcode/filter/capture/BarcodeFilterSettings;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(BarcodeFilterSettings barcodeFilterSettings) {
            Intrinsics.checkNotNullParameter(barcodeFilterSettings, "barcodeFilterSettings");
            return new BarcodeFilterSettingsDefaults(barcodeFilterSettings, null).toMap();
        }
    }
}
