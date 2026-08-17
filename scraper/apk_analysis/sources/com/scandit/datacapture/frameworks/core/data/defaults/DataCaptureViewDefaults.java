package com.scandit.datacapture.frameworks.core.data.defaults;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.AnchorUtilsKt;
import com.scandit.datacapture.core.common.geometry.MarginsWithUnitUtilsKt;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.gesture.FocusGesture;
import com.scandit.datacapture.core.ui.gesture.ZoomGesture;
import com.scandit.datacapture.core.ui.serialization.LogoStyleUtilsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import io.sentry.protocol.Request;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataCaptureViewDefaults.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0006\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000eH\u0016J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/DataCaptureViewDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "dataCaptureView", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "(Lcom/scandit/datacapture/core/ui/DataCaptureView;)V", "component1", "copy", "equals", "", Request.JsonKeys.OTHER, "", "hashCode", "", "toMap", "", "", "toString", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DataCaptureViewDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FOCUS_GESTURE = "focusGesture";
    private static final String FIELD_LOGO_ANCHOR = "logoAnchor";
    private static final String FIELD_LOGO_OFFSET = "logoOffset";
    private static final String FIELD_LOGO_STYLE = "logoStyle";
    private static final String FIELD_MARGINS = "scanAreaMargins";
    private static final String FIELD_POI = "pointOfInterest";
    private static final String FIELD_ZOOM_GESTURE = "zoomGesture";
    private final DataCaptureView dataCaptureView;

    /* renamed from: component1, reason: from getter */
    private final DataCaptureView getDataCaptureView() {
        return this.dataCaptureView;
    }

    public static /* synthetic */ DataCaptureViewDefaults copy$default(DataCaptureViewDefaults dataCaptureViewDefaults, DataCaptureView dataCaptureView, int i, Object obj) {
        if ((i & 1) != 0) {
            dataCaptureView = dataCaptureViewDefaults.dataCaptureView;
        }
        return dataCaptureViewDefaults.copy(dataCaptureView);
    }

    @JvmStatic
    public static final Map<String, Object> get(Context context) {
        return INSTANCE.get(context);
    }

    public final DataCaptureViewDefaults copy(DataCaptureView dataCaptureView) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        return new DataCaptureViewDefaults(dataCaptureView);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DataCaptureViewDefaults) && Intrinsics.areEqual(this.dataCaptureView, ((DataCaptureViewDefaults) other).dataCaptureView);
    }

    public int hashCode() {
        return this.dataCaptureView.hashCode();
    }

    public String toString() {
        return "DataCaptureViewDefaults(dataCaptureView=" + this.dataCaptureView + ')';
    }

    public DataCaptureViewDefaults(DataCaptureView dataCaptureView) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        this.dataCaptureView = dataCaptureView;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        Pair[] pairArr = new Pair[7];
        pairArr[0] = TuplesKt.to(FIELD_MARGINS, MarginsWithUnitUtilsKt.toJson(this.dataCaptureView.getScanAreaMargins()));
        pairArr[1] = TuplesKt.to(FIELD_POI, PointWithUnitUtilsKt.toJson(this.dataCaptureView.getPointOfInterest()));
        pairArr[2] = TuplesKt.to(FIELD_LOGO_ANCHOR, AnchorUtilsKt.toJson(this.dataCaptureView.getLogoAnchor()));
        pairArr[3] = TuplesKt.to(FIELD_LOGO_OFFSET, PointWithUnitUtilsKt.toJson(this.dataCaptureView.getLogoOffset()));
        FocusGesture focusGesture = this.dataCaptureView.getFocusGesture();
        pairArr[4] = TuplesKt.to(FIELD_FOCUS_GESTURE, focusGesture != null ? focusGesture.toJson() : null);
        ZoomGesture zoomGesture = this.dataCaptureView.getZoomGesture();
        pairArr[5] = TuplesKt.to(FIELD_ZOOM_GESTURE, zoomGesture != null ? zoomGesture.toJson() : null);
        pairArr[6] = TuplesKt.to(FIELD_LOGO_STYLE, LogoStyleUtilsKt.toJson(this.dataCaptureView.getLogoStyle()));
        return MapsKt.mapOf(pairArr);
    }

    /* compiled from: DataCaptureViewDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/DataCaptureViewDefaults$Companion;", "", "()V", "FIELD_FOCUS_GESTURE", "", "FIELD_LOGO_ANCHOR", "FIELD_LOGO_OFFSET", "FIELD_LOGO_STYLE", "FIELD_MARGINS", "FIELD_POI", "FIELD_ZOOM_GESTURE", "get", "", "context", "Landroid/content/Context;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new DataCaptureViewDefaults(DataCaptureView.INSTANCE.newInstance(context, null)).toMap();
        }
    }
}
