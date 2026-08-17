package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinder;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderAnimation;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyle;
import com.scandit.datacapture.core.ui.viewfinder.serialization.RectangularViewfinderUtilsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: RectangularViewfinderDefaults.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/RectangularViewfinderDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "viewFinder", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinder;", "(Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinder;)V", "createViewfinderDefaults", "", "", "", RectangularViewfinderDefaults.FIELD_VIEW_FINDER_STYLE, "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "toMap", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectangularViewfinderDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_VIEW_FINDER_ANIMATION = "animation";
    private static final String FIELD_VIEW_FINDER_COLOR = "color";
    private static final String FIELD_VIEW_FINDER_DEFAULT_STYLE = "defaultStyle";
    private static final String FIELD_VIEW_FINDER_DIMMING = "dimming";
    private static final String FIELD_VIEW_FINDER_DISABLED_COLOR = "disabledColor";
    private static final String FIELD_VIEW_FINDER_DISABLED_DIMMING = "disabledDimming";
    private static final String FIELD_VIEW_FINDER_LINE_STYLE = "lineStyle";
    private static final String FIELD_VIEW_FINDER_SIZE = "size";
    private static final String FIELD_VIEW_FINDER_STYLE = "style";
    private static final String FIELD_VIEW_FINDER_STYLES = "styles";
    private final RectangularViewfinder viewFinder;

    public /* synthetic */ RectangularViewfinderDefaults(RectangularViewfinder rectangularViewfinder, DefaultConstructorMarker defaultConstructorMarker) {
        this(rectangularViewfinder);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private RectangularViewfinderDefaults(RectangularViewfinder rectangularViewfinder) {
        this.viewFinder = rectangularViewfinder;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_VIEW_FINDER_DEFAULT_STYLE, RectangularViewfinderUtilsKt.toJson(this.viewFinder.getStyle())), TuplesKt.to(FIELD_VIEW_FINDER_STYLES, MapsKt.mapOf(TuplesKt.to(RectangularViewfinderUtilsKt.toJson(RectangularViewfinderStyle.ROUNDED), createViewfinderDefaults(RectangularViewfinderStyle.ROUNDED)), TuplesKt.to(RectangularViewfinderUtilsKt.toJson(RectangularViewfinderStyle.SQUARE), createViewfinderDefaults(RectangularViewfinderStyle.SQUARE)))));
    }

    private final Map<String, Object> createViewfinderDefaults(RectangularViewfinderStyle style) {
        RectangularViewfinder rectangularViewfinder = new RectangularViewfinder(style, null, 2, null);
        Pair[] pairArr = new Pair[8];
        pairArr[0] = TuplesKt.to("size", rectangularViewfinder.getSizeWithUnitAndAspect().toJson());
        pairArr[1] = TuplesKt.to("color", ExtentionsKt.getHexString(rectangularViewfinder.getColor()));
        pairArr[2] = TuplesKt.to(FIELD_VIEW_FINDER_STYLE, RectangularViewfinderUtilsKt.toJson(style));
        pairArr[3] = TuplesKt.to(FIELD_VIEW_FINDER_LINE_STYLE, RectangularViewfinderUtilsKt.toJson(rectangularViewfinder.getLineStyle()));
        pairArr[4] = TuplesKt.to(FIELD_VIEW_FINDER_DIMMING, Float.valueOf(rectangularViewfinder.getDimming()));
        RectangularViewfinderAnimation animation = rectangularViewfinder.getAnimation();
        pairArr[5] = TuplesKt.to(FIELD_VIEW_FINDER_ANIMATION, animation != null ? animation.toJson() : null);
        pairArr[6] = TuplesKt.to(FIELD_VIEW_FINDER_DISABLED_DIMMING, Float.valueOf(rectangularViewfinder.getDisabledDimming()));
        pairArr[7] = TuplesKt.to(FIELD_VIEW_FINDER_DISABLED_COLOR, ExtentionsKt.getHexString(rectangularViewfinder.getDisabledColor()));
        return MapsKt.mapOf(pairArr);
    }

    /* compiled from: RectangularViewfinderDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/RectangularViewfinderDefaults$Companion;", "", "()V", "FIELD_VIEW_FINDER_ANIMATION", "", "FIELD_VIEW_FINDER_COLOR", "FIELD_VIEW_FINDER_DEFAULT_STYLE", "FIELD_VIEW_FINDER_DIMMING", "FIELD_VIEW_FINDER_DISABLED_COLOR", "FIELD_VIEW_FINDER_DISABLED_DIMMING", "FIELD_VIEW_FINDER_LINE_STYLE", "FIELD_VIEW_FINDER_SIZE", "FIELD_VIEW_FINDER_STYLE", "FIELD_VIEW_FINDER_STYLES", "get", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new RectangularViewfinderDefaults(new RectangularViewfinder(RectangularViewfinderStyle.ROUNDED, null, 2, null), null).toMap();
        }
    }
}
