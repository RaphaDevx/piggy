package com.scandit.datacapture.frameworks.barcode.pick.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodePickDefaults.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u008b\u0001\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "settingsDefaults", "", "", "", "recommendedCameraSettings", "viewSettings", "barcodePickViewHighlightStyleDefaults", "symbologySettings", "barcodePickStatusIconSettings", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_PICK_SETTINGS = "BarcodePickSettings";
    private static final String FIELD_BARCODE_PICK_STATUS_ICON_SETTINGS = "BarcodePickStatusIconSettings";
    private static final String FIELD_BARCODE_PICK_VIEW_HIGHLIGHT_STYLE = "BarcodePickViewHighlightStyle";
    private static final String FIELD_BARCODE_PICK_VIEW_SETTINGS = "ViewSettings";
    private static final String FIELD_RECOMMENDED_CAMERA_SETTINGS = "RecommendedCameraSettings";
    private static final String FIELD_SYMBOLOGY_SETTINGS = "SymbologySettings";
    private final Map<String, Object> barcodePickStatusIconSettings;
    private final Map<String, Object> barcodePickViewHighlightStyleDefaults;
    private final Map<String, Object> recommendedCameraSettings;
    private final Map<String, Object> settingsDefaults;
    private final Map<String, Object> symbologySettings;
    private final Map<String, Object> viewSettings;

    public /* synthetic */ BarcodePickDefaults(Map map, Map map2, Map map3, Map map4, Map map5, Map map6, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, map2, map3, map4, map5, map6);
    }

    private BarcodePickDefaults(Map<String, ? extends Object> map, Map<String, ? extends Object> map2, Map<String, ? extends Object> map3, Map<String, ? extends Object> map4, Map<String, ? extends Object> map5, Map<String, ? extends Object> map6) {
        this.settingsDefaults = map;
        this.recommendedCameraSettings = map2;
        this.viewSettings = map3;
        this.barcodePickViewHighlightStyleDefaults = map4;
        this.symbologySettings = map5;
        this.barcodePickStatusIconSettings = map6;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_BARCODE_PICK_SETTINGS, this.settingsDefaults), TuplesKt.to(FIELD_RECOMMENDED_CAMERA_SETTINGS, this.recommendedCameraSettings), TuplesKt.to(FIELD_BARCODE_PICK_VIEW_SETTINGS, this.viewSettings), TuplesKt.to(FIELD_BARCODE_PICK_VIEW_HIGHLIGHT_STYLE, this.barcodePickViewHighlightStyleDefaults), TuplesKt.to(FIELD_SYMBOLOGY_SETTINGS, this.symbologySettings), TuplesKt.to(FIELD_BARCODE_PICK_STATUS_ICON_SETTINGS, this.barcodePickStatusIconSettings));
    }

    /* compiled from: BarcodePickDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickDefaults$Companion;", "", "()V", "FIELD_BARCODE_PICK_SETTINGS", "", "FIELD_BARCODE_PICK_STATUS_ICON_SETTINGS", "FIELD_BARCODE_PICK_VIEW_HIGHLIGHT_STYLE", "FIELD_BARCODE_PICK_VIEW_SETTINGS", "FIELD_RECOMMENDED_CAMERA_SETTINGS", "FIELD_SYMBOLOGY_SETTINGS", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> get() {
            return new BarcodePickDefaults(BarcodePickSettingsDefaults.INSTANCE.get(), CameraSettingsDefaults.INSTANCE.create(BarcodePick.INSTANCE.createRecommendedCameraSettings()).toMap(), BarcodePickViewSettingsFrameworksDefaults.INSTANCE.get(), BarcodePickViewHighlightStyleDefaults.INSTANCE.get(), BarcodePickSymbologySettingsDefaults.INSTANCE.get(), BarcodePickStatusIconSettingsDefaults.INSTANCE.get(), null).toMap();
        }
    }
}
