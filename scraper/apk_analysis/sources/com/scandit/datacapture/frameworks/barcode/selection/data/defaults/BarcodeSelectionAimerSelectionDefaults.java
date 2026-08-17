package com.scandit.datacapture.frameworks.barcode.selection.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionAimerSelection;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeSelectionAimerSelectionDefaults.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionAimerSelectionDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "aimerSelection", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerSelection;", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionAimerSelection;)V", "toMap", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeSelectionAimerSelectionDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_DEFAULT_SELECTION_STRATEGY = "defaultSelectionStrategy";
    private final BarcodeSelectionAimerSelection aimerSelection;

    public /* synthetic */ BarcodeSelectionAimerSelectionDefaults(BarcodeSelectionAimerSelection barcodeSelectionAimerSelection, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeSelectionAimerSelection);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeSelectionAimerSelectionDefaults(BarcodeSelectionAimerSelection barcodeSelectionAimerSelection) {
        this.aimerSelection = barcodeSelectionAimerSelection;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, String> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_DEFAULT_SELECTION_STRATEGY, this.aimerSelection.getSelectionStrategy().toJson()));
    }

    /* compiled from: BarcodeSelectionAimerSelectionDefaults.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionAimerSelectionDefaults$Companion;", "", "()V", "FIELD_DEFAULT_SELECTION_STRATEGY", "", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeSelectionAimerSelectionDefaults(new BarcodeSelectionAimerSelection(), null).toMap();
        }
    }
}
