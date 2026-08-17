package com.scandit.datacapture.frameworks.barcode.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureSettings;
import com.scandit.datacapture.barcode.data.SymbologyDescription;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: SymbologySettingsDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/data/defaults/SymbologySettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "barcodeCaptureSettings", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "(Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SymbologySettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeCaptureSettings barcodeCaptureSettings;

    public /* synthetic */ SymbologySettingsDefaults(BarcodeCaptureSettings barcodeCaptureSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeCaptureSettings);
    }

    @JvmStatic
    public static final Map<String, Object> get(BarcodeCaptureSettings barcodeCaptureSettings) {
        return INSTANCE.get(barcodeCaptureSettings);
    }

    private SymbologySettingsDefaults(BarcodeCaptureSettings barcodeCaptureSettings) {
        this.barcodeCaptureSettings = barcodeCaptureSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        List<SymbologyDescription> all = SymbologyDescription.INSTANCE.all();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(all, 10)), 16));
        for (SymbologyDescription symbologyDescription : all) {
            Pair pair = TuplesKt.to(symbologyDescription.getIdentifier(), this.barcodeCaptureSettings.getSymbologySettings(symbologyDescription.getSymbology()).toJson());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    /* compiled from: SymbologySettingsDefaults.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/data/defaults/SymbologySettingsDefaults$Companion;", "", "()V", "get", "", "", "barcodeCaptureSettings", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSettings;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(BarcodeCaptureSettings barcodeCaptureSettings) {
            Intrinsics.checkNotNullParameter(barcodeCaptureSettings, "barcodeCaptureSettings");
            return new SymbologySettingsDefaults(barcodeCaptureSettings, null).toMap();
        }
    }
}
