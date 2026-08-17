package com.scandit.datacapture.frameworks.core.data.defaults;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.capture.DataCaptureVersion;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CoreDefaults.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\t\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u008b\u0001\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CoreDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "cameraDefaults", "", "", "", "dataCaptureViewDefaults", "rectangularViewfinderDefaults", "brushDefaults", "aimerViewFinderDefaults", "laserlineViewFinderDefaults", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CoreDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_AIMER_VIEW_FINDER_DEFAULTS = "AimerViewfinder";
    private static final String FIELD_BRUSH_DEFAULTS = "Brush";
    private static final String FIELD_CAMERA_DEFAULTS = "Camera";
    private static final String FIELD_DATA_CAPTURE_VIEW_DEFAULTS = "DataCaptureView";
    private static final String FIELD_DEVICE_ID = "deviceID";
    private static final String FIELD_LASER_LINE_FINDER_DEFAULTS = "LaserlineViewfinder";
    private static final String FIELD_RECTANGULAR_VIEW_FINDER_DEFAULTS = "RectangularViewfinder";
    private static final String FIELD_VERSION = "Version";
    private final Map<String, Object> aimerViewFinderDefaults;
    private final Map<String, Object> brushDefaults;
    private final Map<String, Object> cameraDefaults;
    private final Map<String, Object> dataCaptureViewDefaults;
    private final Map<String, Object> laserlineViewFinderDefaults;
    private final Map<String, Object> rectangularViewfinderDefaults;

    public /* synthetic */ CoreDefaults(Map map, Map map2, Map map3, Map map4, Map map5, Map map6, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, map2, map3, map4, map5, map6);
    }

    @JvmStatic
    public static final Map<String, Object> get(Context context) {
        return INSTANCE.get(context);
    }

    private CoreDefaults(Map<String, ? extends Object> map, Map<String, ? extends Object> map2, Map<String, ? extends Object> map3, Map<String, ? extends Object> map4, Map<String, ? extends Object> map5, Map<String, ? extends Object> map6) {
        this.cameraDefaults = map;
        this.dataCaptureViewDefaults = map2;
        this.rectangularViewfinderDefaults = map3;
        this.brushDefaults = map4;
        this.aimerViewFinderDefaults = map5;
        this.laserlineViewFinderDefaults = map6;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_VERSION, DataCaptureVersion.VERSION_STRING), TuplesKt.to(FIELD_CAMERA_DEFAULTS, this.cameraDefaults), TuplesKt.to(FIELD_DATA_CAPTURE_VIEW_DEFAULTS, this.dataCaptureViewDefaults), TuplesKt.to(FIELD_RECTANGULAR_VIEW_FINDER_DEFAULTS, this.rectangularViewfinderDefaults), TuplesKt.to(FIELD_BRUSH_DEFAULTS, this.brushDefaults), TuplesKt.to(FIELD_DEVICE_ID, DataCaptureContext.DEVICE_ID), TuplesKt.to(FIELD_AIMER_VIEW_FINDER_DEFAULTS, this.aimerViewFinderDefaults), TuplesKt.to(FIELD_LASER_LINE_FINDER_DEFAULTS, this.laserlineViewFinderDefaults));
    }

    /* compiled from: CoreDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CoreDefaults$Companion;", "", "()V", "FIELD_AIMER_VIEW_FINDER_DEFAULTS", "", "FIELD_BRUSH_DEFAULTS", "FIELD_CAMERA_DEFAULTS", "FIELD_DATA_CAPTURE_VIEW_DEFAULTS", "FIELD_DEVICE_ID", "FIELD_LASER_LINE_FINDER_DEFAULTS", "FIELD_RECTANGULAR_VIEW_FINDER_DEFAULTS", "FIELD_VERSION", "get", "", "context", "Landroid/content/Context;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new CoreDefaults(CameraDefaults.INSTANCE.get(), DataCaptureViewDefaults.INSTANCE.get(context), RectangularViewfinderDefaults.INSTANCE.get(), BrushDefaults.INSTANCE.get(Brush.INSTANCE.transparent()), AimerViewfinderDefaults.INSTANCE.get(), LaserlineViewfinderDefaults.INSTANCE.get(), null).toMap();
        }
    }
}
