package com.scandit.datacapture.frameworks.barcode.batch.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatch;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSettings;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayStyle;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayStyleUtilsKt;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.BrushDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeBatchBasicOverlayDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\t\u001a\u00020\u0003H\u0002J\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/defaults/BarcodeBatchBasicOverlayDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", BarcodeBatchBasicOverlayDefaults.FIELD_DEFAULT_STYLE, "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;", "(Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayStyle;)V", "createOverlayForStyle", "", "", "", "style", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeBatchBasicOverlayDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BRUSHES = "Brushes";
    private static final String FIELD_DEFAULT_STYLE = "defaultStyle";
    private final BarcodeBatchBasicOverlayStyle defaultStyle;

    public /* synthetic */ BarcodeBatchBasicOverlayDefaults(BarcodeBatchBasicOverlayStyle barcodeBatchBasicOverlayStyle, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeBatchBasicOverlayStyle);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeBatchBasicOverlayDefaults(BarcodeBatchBasicOverlayStyle barcodeBatchBasicOverlayStyle) {
        this.defaultStyle = barcodeBatchBasicOverlayStyle;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_DEFAULT_STYLE, BarcodeBatchBasicOverlayStyleUtilsKt.toJson(this.defaultStyle)), TuplesKt.to(FIELD_BRUSHES, MapsKt.mapOf(TuplesKt.to(BarcodeBatchBasicOverlayStyleUtilsKt.toJson(BarcodeBatchBasicOverlayStyle.DOT), createOverlayForStyle(BarcodeBatchBasicOverlayStyle.DOT)), TuplesKt.to(BarcodeBatchBasicOverlayStyleUtilsKt.toJson(BarcodeBatchBasicOverlayStyle.FRAME), createOverlayForStyle(BarcodeBatchBasicOverlayStyle.FRAME)))));
    }

    private final Map<String, Object> createOverlayForStyle(BarcodeBatchBasicOverlayStyle style) {
        BarcodeBatchBasicOverlay newInstance = BarcodeBatchBasicOverlay.INSTANCE.newInstance(BarcodeBatch.INSTANCE.forDataCaptureContext(null, new BarcodeBatchSettings()), null, style);
        return BrushDefaults.INSTANCE.get(newInstance.getBrush() != null ? newInstance.getBrush() : Brush.INSTANCE.transparent());
    }

    /* compiled from: BarcodeBatchBasicOverlayDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/defaults/BarcodeBatchBasicOverlayDefaults$Companion;", "", "()V", "FIELD_BRUSHES", "", "FIELD_DEFAULT_STYLE", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeBatchBasicOverlayDefaults(BarcodeBatchBasicOverlay.INSTANCE.newInstance(BarcodeBatch.INSTANCE.forDataCaptureContext(null, new BarcodeBatchSettings()), null).getStyle(), null).toMap();
        }
    }
}
