package com.scandit.datacapture.frameworks.barcode.selection.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelection;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionTapSelectionKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeSelectionTapSelectionDefaults.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionTapSelectionDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "tapSelection", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapSelection;", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapSelection;)V", "toMap", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeSelectionTapSelectionDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_DEFAULT_FREEZE_BEHAVIOUR = "defaultFreezeBehaviour";
    private static final String FIELD_DEFAULT_TAP_BEHAVIOUR = "defaultTapBehaviour";
    private final BarcodeSelectionTapSelection tapSelection;

    public /* synthetic */ BarcodeSelectionTapSelectionDefaults(BarcodeSelectionTapSelection barcodeSelectionTapSelection, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeSelectionTapSelection);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeSelectionTapSelectionDefaults(BarcodeSelectionTapSelection barcodeSelectionTapSelection) {
        this.tapSelection = barcodeSelectionTapSelection;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, String> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_DEFAULT_FREEZE_BEHAVIOUR, BarcodeSelectionTapSelectionKt.toJson(this.tapSelection.getFreezeBehavior())), TuplesKt.to(FIELD_DEFAULT_TAP_BEHAVIOUR, BarcodeSelectionTapSelectionKt.toJson(this.tapSelection.getTapBehavior())));
    }

    /* compiled from: BarcodeSelectionTapSelectionDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionTapSelectionDefaults$Companion;", "", "()V", "FIELD_DEFAULT_FREEZE_BEHAVIOUR", "", "FIELD_DEFAULT_TAP_BEHAVIOUR", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeSelectionTapSelectionDefaults(new BarcodeSelectionTapSelection(), null).toMap();
        }
    }
}
