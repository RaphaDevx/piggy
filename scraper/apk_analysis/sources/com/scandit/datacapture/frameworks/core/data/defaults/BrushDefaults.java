package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BrushDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/BrushDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "brush", "Lcom/scandit/datacapture/core/ui/style/Brush;", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BrushDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FILL_COLOR = "fillColor";
    private static final String FIELD_STROKE_COLOR = "strokeColor";
    private static final String FIELD_STROKE_WIDTH = "strokeWidth";
    private final Brush brush;

    public /* synthetic */ BrushDefaults(Brush brush, DefaultConstructorMarker defaultConstructorMarker) {
        this(brush);
    }

    @JvmStatic
    public static final Map<String, Object> get(Brush brush) {
        return INSTANCE.get(brush);
    }

    private BrushDefaults(Brush brush) {
        this.brush = brush;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        Pair[] pairArr = new Pair[3];
        Brush brush = this.brush;
        pairArr[0] = TuplesKt.to(FIELD_FILL_COLOR, brush != null ? ExtentionsKt.getHexString(brush.getFillColor()) : null);
        Brush brush2 = this.brush;
        pairArr[1] = TuplesKt.to(FIELD_STROKE_COLOR, brush2 != null ? ExtentionsKt.getHexString(brush2.getStrokeColor()) : null);
        pairArr[2] = TuplesKt.to(FIELD_STROKE_WIDTH, Double.valueOf(this.brush != null ? r4.getStrokeWidth() : 0.0d));
        return MapsKt.mapOf(pairArr);
    }

    /* compiled from: BrushDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/BrushDefaults$Companion;", "", "()V", "FIELD_FILL_COLOR", "", "FIELD_STROKE_COLOR", "FIELD_STROKE_WIDTH", "get", "", "brush", "Lcom/scandit/datacapture/core/ui/style/Brush;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(Brush brush) {
            return new BrushDefaults(brush, null).toMap();
        }
    }
}
