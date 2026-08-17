package com.scandit.datacapture.frameworks.barcode.pick.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.SymbologyDescription;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickSettings;
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
import kotlin.ranges.RangesKt;

/* compiled from: BarcodePickSymbologySettingsDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickSymbologySettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "barcodePickSettings", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;", "(Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickSymbologySettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodePickSettings barcodePickSettings;

    public /* synthetic */ BarcodePickSymbologySettingsDefaults(BarcodePickSettings barcodePickSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodePickSettings);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodePickSymbologySettingsDefaults(BarcodePickSettings barcodePickSettings) {
        this.barcodePickSettings = barcodePickSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        List<SymbologyDescription> all = SymbologyDescription.INSTANCE.all();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(all, 10)), 16));
        for (SymbologyDescription symbologyDescription : all) {
            Pair pair = TuplesKt.to(symbologyDescription.getIdentifier(), this.barcodePickSettings.getSymbologySettings(symbologyDescription.getSymbology()).toJson());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    /* compiled from: BarcodePickSymbologySettingsDefaults.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0007¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickSymbologySettingsDefaults$Companion;", "", "()V", "get", "", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodePickSymbologySettingsDefaults(new BarcodePickSettings(), null).toMap();
        }
    }
}
