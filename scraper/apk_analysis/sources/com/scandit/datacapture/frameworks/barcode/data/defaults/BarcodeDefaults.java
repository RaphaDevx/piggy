package com.scandit.datacapture.frameworks.barcode.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureSettings;
import com.scandit.datacapture.barcode.data.CompositeTypeDescription;
import com.scandit.datacapture.barcode.data.SymbologyDescription;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeDefaults.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB9\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/data/defaults/BarcodeDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "symbologySettingsDefaults", "", "", "", "symbologyDescriptionsDefaults", "", "compositeTypeDescriptions", "(Ljava/util/Map;Ljava/util/List;Ljava/util/List;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_COMPOSITE_TYPE_DESCRIPTION_DEFAULTS = "CompositeTypeDescriptions";
    private static final String FIELD_SYMBOLOGY_DESCRIPTION_DEFAULTS = "SymbologyDescriptions";
    private static final String FIELD_SYMBOLOGY_SETTINGS_DEFAULTS = "SymbologySettings";
    private final List<String> compositeTypeDescriptions;
    private final List<String> symbologyDescriptionsDefaults;
    private final Map<String, Object> symbologySettingsDefaults;

    public /* synthetic */ BarcodeDefaults(Map map, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, list, list2);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeDefaults(Map<String, ? extends Object> map, List<String> list, List<String> list2) {
        this.symbologySettingsDefaults = map;
        this.symbologyDescriptionsDefaults = list;
        this.compositeTypeDescriptions = list2;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_SYMBOLOGY_SETTINGS_DEFAULTS, this.symbologySettingsDefaults), TuplesKt.to(FIELD_SYMBOLOGY_DESCRIPTION_DEFAULTS, this.symbologyDescriptionsDefaults), TuplesKt.to(FIELD_COMPOSITE_TYPE_DESCRIPTION_DEFAULTS, this.compositeTypeDescriptions));
    }

    /* compiled from: BarcodeDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/data/defaults/BarcodeDefaults$Companion;", "", "()V", "FIELD_COMPOSITE_TYPE_DESCRIPTION_DEFAULTS", "", "FIELD_SYMBOLOGY_DESCRIPTION_DEFAULTS", "FIELD_SYMBOLOGY_SETTINGS_DEFAULTS", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            BarcodeCaptureSettings barcodeCaptureSettings = new BarcodeCaptureSettings();
            List<SymbologyDescription> all = SymbologyDescription.INSTANCE.all();
            List<CompositeTypeDescription> all2 = CompositeTypeDescription.INSTANCE.all();
            Map<String, Object> map = SymbologySettingsDefaults.INSTANCE.get(barcodeCaptureSettings);
            List<SymbologyDescription> list = all;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((SymbologyDescription) it.next()).toJson());
            }
            ArrayList arrayList2 = arrayList;
            List<CompositeTypeDescription> list2 = all2;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(((CompositeTypeDescription) it2.next()).toJson());
            }
            return new BarcodeDefaults(map, arrayList2, arrayList3, null).toMap();
        }
    }
}
