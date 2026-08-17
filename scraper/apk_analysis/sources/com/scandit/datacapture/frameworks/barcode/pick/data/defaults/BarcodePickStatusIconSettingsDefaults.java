package com.scandit.datacapture.frameworks.barcode.pick.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconSettings;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodePickStatusIconSettingsDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickStatusIconSettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "settings", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconSettings;", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickStatusIconSettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_MAX_SIZE = "maxSize";
    private static final String FIELD_MIN_SIZE = "minSize";
    private static final String FIELD_RATIO_TO_HIGHLIGHT_SIZE = "ratioToHighlightSize";
    private final BarcodePickStatusIconSettings settings;

    public /* synthetic */ BarcodePickStatusIconSettingsDefaults(BarcodePickStatusIconSettings barcodePickStatusIconSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodePickStatusIconSettings);
    }

    private BarcodePickStatusIconSettingsDefaults(BarcodePickStatusIconSettings barcodePickStatusIconSettings) {
        this.settings = barcodePickStatusIconSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_RATIO_TO_HIGHLIGHT_SIZE, Float.valueOf(this.settings.getRatioToHighlightSize())), TuplesKt.to(FIELD_MIN_SIZE, Integer.valueOf(this.settings.getMinSize())), TuplesKt.to(FIELD_MAX_SIZE, Integer.valueOf(this.settings.getMaxSize())));
    }

    /* compiled from: BarcodePickStatusIconSettingsDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickStatusIconSettingsDefaults$Companion;", "", "()V", "FIELD_MAX_SIZE", "", "FIELD_MIN_SIZE", "FIELD_RATIO_TO_HIGHLIGHT_SIZE", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> get() {
            return new BarcodePickStatusIconSettingsDefaults(new BarcodePickStatusIconSettings(), null).toMap();
        }
    }
}
