package com.scandit.datacapture.frameworks.barcode.pick.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodePickViewHighlightStyleDefaults.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000eH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickViewHighlightStyleDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "rectangular", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$Rectangular;", "rectangularWithIcons", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$RectangularWithIcons;", "dot", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$Dot;", "dotWithIcons", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$DotWithIcons;", "customView", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$CustomView;", "(Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$Rectangular;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$RectangularWithIcons;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$Dot;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$DotWithIcons;Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle$CustomView;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickViewHighlightStyleDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_CUSTOM_VIEW = "CustomView";
    private static final String FIELD_DOT = "Dot";
    private static final String FIELD_DOT_WITH_ICON = "DotWithIcons";
    private static final String FIELD_RECTANGULAR = "Rectangular";
    private static final String FIELD_RECTANGULAR_WITH_ICON = "RectangularWithIcons";
    private final BarcodePickViewHighlightStyle.CustomView customView;
    private final BarcodePickViewHighlightStyle.Dot dot;
    private final BarcodePickViewHighlightStyle.DotWithIcons dotWithIcons;
    private final BarcodePickViewHighlightStyle.Rectangular rectangular;
    private final BarcodePickViewHighlightStyle.RectangularWithIcons rectangularWithIcons;

    public /* synthetic */ BarcodePickViewHighlightStyleDefaults(BarcodePickViewHighlightStyle.Rectangular rectangular, BarcodePickViewHighlightStyle.RectangularWithIcons rectangularWithIcons, BarcodePickViewHighlightStyle.Dot dot, BarcodePickViewHighlightStyle.DotWithIcons dotWithIcons, BarcodePickViewHighlightStyle.CustomView customView, DefaultConstructorMarker defaultConstructorMarker) {
        this(rectangular, rectangularWithIcons, dot, dotWithIcons, customView);
    }

    private BarcodePickViewHighlightStyleDefaults(BarcodePickViewHighlightStyle.Rectangular rectangular, BarcodePickViewHighlightStyle.RectangularWithIcons rectangularWithIcons, BarcodePickViewHighlightStyle.Dot dot, BarcodePickViewHighlightStyle.DotWithIcons dotWithIcons, BarcodePickViewHighlightStyle.CustomView customView) {
        this.rectangular = rectangular;
        this.rectangularWithIcons = rectangularWithIcons;
        this.dot = dot;
        this.dotWithIcons = dotWithIcons;
        this.customView = customView;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_RECTANGULAR, this.rectangular.toJson()), TuplesKt.to(FIELD_RECTANGULAR_WITH_ICON, this.rectangularWithIcons.toJson()), TuplesKt.to(FIELD_DOT, this.dot.toJson()), TuplesKt.to(FIELD_DOT_WITH_ICON, this.dotWithIcons.toJson()), TuplesKt.to(FIELD_CUSTOM_VIEW, this.customView.toJson()));
    }

    /* compiled from: BarcodePickViewHighlightStyleDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickViewHighlightStyleDefaults$Companion;", "", "()V", "FIELD_CUSTOM_VIEW", "", "FIELD_DOT", "FIELD_DOT_WITH_ICON", "FIELD_RECTANGULAR", "FIELD_RECTANGULAR_WITH_ICON", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> get() {
            return new BarcodePickViewHighlightStyleDefaults(new BarcodePickViewHighlightStyle.Rectangular(), new BarcodePickViewHighlightStyle.RectangularWithIcons(), new BarcodePickViewHighlightStyle.Dot(), new BarcodePickViewHighlightStyle.DotWithIcons(), new BarcodePickViewHighlightStyle.CustomView(), null).toMap();
        }
    }
}
