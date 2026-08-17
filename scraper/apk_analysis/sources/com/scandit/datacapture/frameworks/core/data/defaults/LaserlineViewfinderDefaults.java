package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.FloatWithUnitUtilsKt;
import com.scandit.datacapture.core.ui.viewfinder.LaserlineViewfinder;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LaserlineViewfinderDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/LaserlineViewfinderDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "viewFinder", "Lcom/scandit/datacapture/core/ui/viewfinder/LaserlineViewfinder;", "(Lcom/scandit/datacapture/core/ui/viewfinder/LaserlineViewfinder;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LaserlineViewfinderDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_VIEW_FINDER_DISABLED_COLOR = "disabledColor";
    private static final String FIELD_VIEW_FINDER_ENABLED_COLOR = "enabledColor";
    private static final String FIELD_VIEW_FINDER_WIDTH = "width";
    private final LaserlineViewfinder viewFinder;

    public /* synthetic */ LaserlineViewfinderDefaults(LaserlineViewfinder laserlineViewfinder, DefaultConstructorMarker defaultConstructorMarker) {
        this(laserlineViewfinder);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private LaserlineViewfinderDefaults(LaserlineViewfinder laserlineViewfinder) {
        this.viewFinder = laserlineViewfinder;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to("width", FloatWithUnitUtilsKt.toJson(this.viewFinder.getWidth())), TuplesKt.to(FIELD_VIEW_FINDER_ENABLED_COLOR, ExtentionsKt.getHexString(this.viewFinder.getEnabledColor())), TuplesKt.to(FIELD_VIEW_FINDER_DISABLED_COLOR, ExtentionsKt.getHexString(this.viewFinder.getDisabledColor())));
    }

    /* compiled from: LaserlineViewfinderDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/LaserlineViewfinderDefaults$Companion;", "", "()V", "FIELD_VIEW_FINDER_DISABLED_COLOR", "", "FIELD_VIEW_FINDER_ENABLED_COLOR", "FIELD_VIEW_FINDER_WIDTH", "get", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new LaserlineViewfinderDefaults(new LaserlineViewfinder(), null).toMap();
        }
    }
}
