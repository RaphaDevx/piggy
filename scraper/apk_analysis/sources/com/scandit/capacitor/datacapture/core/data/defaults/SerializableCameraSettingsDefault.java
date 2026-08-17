package com.scandit.capacitor.datacapture.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.capacitor.datacapture.core.data.SerializableData;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FocusGestureStrategyUtilsKt;
import com.scandit.datacapture.core.source.VideoResolutionUtilsKt;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import lib.android.paypal.com.magnessdk.c;
import org.json.JSONObject;

/* compiled from: SerializableCameraSettingsDefault.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000b\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\t\u0010\u0012\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÂ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÂ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0017\u001a\u00020\nHÂ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/scandit/capacitor/datacapture/core/data/defaults/SerializableCameraSettingsDefault;", "Lcom/scandit/capacitor/datacapture/core/data/SerializableData;", "prefResolution", "", SerializableCameraSettingsDefault.FIELD_ZOOM_FACTOR, "", SerializableCameraSettingsDefault.FIELD_FOCUS_RANGE, SerializableCameraSettingsDefault.FIELD_ZOOM_GESTURE_ZOOM_FACTOR, SerializableCameraSettingsDefault.FIELD_FOCUS_GESTURE_STRATEGY, SerializableCameraSettingsDefault.FIELD_FOCUS_SHOULD_PREFER_SMOOTH_AUTOFOCUS, "", "<init>", "(Ljava/lang/String;FLjava/lang/String;FLjava/lang/String;Z)V", "settings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "(Lcom/scandit/datacapture/core/source/CameraSettings;)V", "toJson", "Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Request.JsonKeys.OTHER, "", "hashCode", "", "toString", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SerializableCameraSettingsDefault implements SerializableData {
    private static final String FIELD_FOCUS_GESTURE_STRATEGY = "focusGestureStrategy";
    private static final String FIELD_FOCUS_RANGE = "focusRange";
    private static final String FIELD_FOCUS_SHOULD_PREFER_SMOOTH_AUTOFOCUS = "shouldPreferSmoothAutoFocus";
    private static final String FIELD_PREFERRED_RESOLUTION = "preferredResolution";
    private static final String FIELD_ZOOM_FACTOR = "zoomFactor";
    private static final String FIELD_ZOOM_GESTURE_ZOOM_FACTOR = "zoomGestureZoomFactor";
    private final String focusGestureStrategy;
    private final String focusRange;
    private final String prefResolution;
    private final boolean shouldPreferSmoothAutoFocus;
    private final float zoomFactor;
    private final float zoomGestureZoomFactor;

    /* renamed from: component1, reason: from getter */
    private final String getPrefResolution() {
        return this.prefResolution;
    }

    /* renamed from: component2, reason: from getter */
    private final float getZoomFactor() {
        return this.zoomFactor;
    }

    /* renamed from: component3, reason: from getter */
    private final String getFocusRange() {
        return this.focusRange;
    }

    /* renamed from: component4, reason: from getter */
    private final float getZoomGestureZoomFactor() {
        return this.zoomGestureZoomFactor;
    }

    /* renamed from: component5, reason: from getter */
    private final String getFocusGestureStrategy() {
        return this.focusGestureStrategy;
    }

    /* renamed from: component6, reason: from getter */
    private final boolean getShouldPreferSmoothAutoFocus() {
        return this.shouldPreferSmoothAutoFocus;
    }

    public static /* synthetic */ SerializableCameraSettingsDefault copy$default(SerializableCameraSettingsDefault serializableCameraSettingsDefault, String str, float f, String str2, float f2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serializableCameraSettingsDefault.prefResolution;
        }
        if ((i & 2) != 0) {
            f = serializableCameraSettingsDefault.zoomFactor;
        }
        float f3 = f;
        if ((i & 4) != 0) {
            str2 = serializableCameraSettingsDefault.focusRange;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            f2 = serializableCameraSettingsDefault.zoomGestureZoomFactor;
        }
        float f4 = f2;
        if ((i & 16) != 0) {
            str3 = serializableCameraSettingsDefault.focusGestureStrategy;
        }
        String str5 = str3;
        if ((i & 32) != 0) {
            z = serializableCameraSettingsDefault.shouldPreferSmoothAutoFocus;
        }
        return serializableCameraSettingsDefault.copy(str, f3, str4, f4, str5, z);
    }

    public final SerializableCameraSettingsDefault copy(String prefResolution, float zoomFactor, String focusRange, float zoomGestureZoomFactor, String focusGestureStrategy, boolean shouldPreferSmoothAutoFocus) {
        Intrinsics.checkNotNullParameter(prefResolution, "prefResolution");
        Intrinsics.checkNotNullParameter(focusRange, "focusRange");
        Intrinsics.checkNotNullParameter(focusGestureStrategy, "focusGestureStrategy");
        return new SerializableCameraSettingsDefault(prefResolution, zoomFactor, focusRange, zoomGestureZoomFactor, focusGestureStrategy, shouldPreferSmoothAutoFocus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializableCameraSettingsDefault)) {
            return false;
        }
        SerializableCameraSettingsDefault serializableCameraSettingsDefault = (SerializableCameraSettingsDefault) other;
        return Intrinsics.areEqual(this.prefResolution, serializableCameraSettingsDefault.prefResolution) && Float.compare(this.zoomFactor, serializableCameraSettingsDefault.zoomFactor) == 0 && Intrinsics.areEqual(this.focusRange, serializableCameraSettingsDefault.focusRange) && Float.compare(this.zoomGestureZoomFactor, serializableCameraSettingsDefault.zoomGestureZoomFactor) == 0 && Intrinsics.areEqual(this.focusGestureStrategy, serializableCameraSettingsDefault.focusGestureStrategy) && this.shouldPreferSmoothAutoFocus == serializableCameraSettingsDefault.shouldPreferSmoothAutoFocus;
    }

    public int hashCode() {
        return (((((((((this.prefResolution.hashCode() * 31) + Float.hashCode(this.zoomFactor)) * 31) + this.focusRange.hashCode()) * 31) + Float.hashCode(this.zoomGestureZoomFactor)) * 31) + this.focusGestureStrategy.hashCode()) * 31) + Boolean.hashCode(this.shouldPreferSmoothAutoFocus);
    }

    public String toString() {
        return "SerializableCameraSettingsDefault(prefResolution=" + this.prefResolution + ", zoomFactor=" + this.zoomFactor + ", focusRange=" + this.focusRange + ", zoomGestureZoomFactor=" + this.zoomGestureZoomFactor + ", focusGestureStrategy=" + this.focusGestureStrategy + ", shouldPreferSmoothAutoFocus=" + this.shouldPreferSmoothAutoFocus + ")";
    }

    public SerializableCameraSettingsDefault(String prefResolution, float f, String focusRange, float f2, String focusGestureStrategy, boolean z) {
        Intrinsics.checkNotNullParameter(prefResolution, "prefResolution");
        Intrinsics.checkNotNullParameter(focusRange, "focusRange");
        Intrinsics.checkNotNullParameter(focusGestureStrategy, "focusGestureStrategy");
        this.prefResolution = prefResolution;
        this.zoomFactor = f;
        this.focusRange = focusRange;
        this.zoomGestureZoomFactor = f2;
        this.focusGestureStrategy = focusGestureStrategy;
        this.shouldPreferSmoothAutoFocus = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SerializableCameraSettingsDefault(CameraSettings settings) {
        this(VideoResolutionUtilsKt.toJson(settings.getPreferredResolution()), settings.getZoomFactor(), c.g, settings.getZoomGestureZoomFactor(), FocusGestureStrategyUtilsKt.toJson(settings.getFocusGestureStrategy()), settings.getShouldPreferSmoothAutoFocus());
        Intrinsics.checkNotNullParameter(settings, "settings");
    }

    @Override // com.scandit.capacitor.datacapture.core.data.SerializableData
    public JSONObject toJson() {
        return new JSONObject(MapsKt.mapOf(TuplesKt.to(FIELD_PREFERRED_RESOLUTION, this.prefResolution), TuplesKt.to(FIELD_ZOOM_FACTOR, Float.valueOf(this.zoomFactor)), TuplesKt.to(FIELD_FOCUS_RANGE, this.focusRange), TuplesKt.to(FIELD_ZOOM_GESTURE_ZOOM_FACTOR, Float.valueOf(this.zoomGestureZoomFactor)), TuplesKt.to(FIELD_FOCUS_GESTURE_STRATEGY, this.focusGestureStrategy), TuplesKt.to(FIELD_FOCUS_SHOULD_PREFER_SMOOTH_AUTOFOCUS, Boolean.valueOf(this.shouldPreferSmoothAutoFocus))));
    }
}
