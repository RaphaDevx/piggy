package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FocusGestureStrategyUtilsKt;
import com.scandit.datacapture.core.source.VideoResolutionUtilsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import lib.android.paypal.com.magnessdk.c;

/* compiled from: CameraSettingsDefaults.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0001\nB%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "settings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "settingPropertiesDefaults", "", "", "", "(Lcom/scandit/datacapture/core/source/CameraSettings;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CameraSettingsDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FOCUS_GESTURE_STRATEGY = "focusGestureStrategy";
    private static final String FIELD_FOCUS_RANGE = "focusRange";
    private static final String FIELD_PREFERRED_RESOLUTION = "preferredResolution";
    private static final String FIELD_PROPERTIES = "properties";
    private static final String FIELD_SHOULD_PREFER_SMOOTH_AUTO_FOCUS = "shouldPreferSmoothAutoFocus";
    private static final String FIELD_ZOOM_FACTOR = "zoomFactor";
    private static final String FIELD_ZOOM_GESTURE_ZOOM_FACTOR = "zoomGestureZoomFactor";
    private final Map<String, Object> settingPropertiesDefaults;
    private final CameraSettings settings;

    public /* synthetic */ CameraSettingsDefaults(CameraSettings cameraSettings, Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(cameraSettings, map);
    }

    @JvmStatic
    public static final CameraSettingsDefaults create(CameraSettings cameraSettings) {
        return INSTANCE.create(cameraSettings);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private CameraSettingsDefaults(CameraSettings cameraSettings, Map<String, ? extends Object> map) {
        this.settings = cameraSettings;
        this.settingPropertiesDefaults = map;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_PREFERRED_RESOLUTION, VideoResolutionUtilsKt.toJson(this.settings.getPreferredResolution())), TuplesKt.to(FIELD_ZOOM_FACTOR, Float.valueOf(this.settings.getZoomFactor())), TuplesKt.to(FIELD_FOCUS_RANGE, c.g), TuplesKt.to(FIELD_FOCUS_GESTURE_STRATEGY, FocusGestureStrategyUtilsKt.toJson(this.settings.getFocusGestureStrategy())), TuplesKt.to(FIELD_ZOOM_GESTURE_ZOOM_FACTOR, Float.valueOf(this.settings.getZoomGestureZoomFactor())), TuplesKt.to(FIELD_SHOULD_PREFER_SMOOTH_AUTO_FOCUS, Boolean.valueOf(this.settings.getShouldPreferSmoothAutoFocus())), TuplesKt.to(FIELD_PROPERTIES, this.settingPropertiesDefaults));
    }

    /* compiled from: CameraSettingsDefaults.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0016\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults$Companion;", "", "()V", "FIELD_FOCUS_GESTURE_STRATEGY", "", "FIELD_FOCUS_RANGE", "FIELD_PREFERRED_RESOLUTION", "FIELD_PROPERTIES", "FIELD_SHOULD_PREFER_SMOOTH_AUTO_FOCUS", "FIELD_ZOOM_FACTOR", "FIELD_ZOOM_GESTURE_ZOOM_FACTOR", "create", "Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults;", "cameraSettings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "get", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            CameraSettings cameraSettings = new CameraSettings();
            return new CameraSettingsDefaults(cameraSettings, CameraSettingsPropertiesDefaults.INSTANCE.get(cameraSettings), null).toMap();
        }

        @JvmStatic
        public final CameraSettingsDefaults create(CameraSettings cameraSettings) {
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            return new CameraSettingsDefaults(cameraSettings, CameraSettingsPropertiesDefaults.INSTANCE.get(cameraSettings), null);
        }
    }
}
