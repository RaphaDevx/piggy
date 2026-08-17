package com.scandit.capacitor.datacapture.barcode.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.tealium.library.DataSources;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: AdvancedOverlayViewSerialization.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J:\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006\""}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewData;", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView;", "trackedBarcodeId", "", "sessionFrameSequenceId", "", ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY, "<init>", "(Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView;ILjava/lang/Long;I)V", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getView", "()Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView;", "getTrackedBarcodeId", "()I", "getSessionFrameSequenceId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDataCaptureViewId", "component1", "component2", "component3", "component4", "copy", "(Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView;ILjava/lang/Long;I)Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewData;", "equals", "", Request.JsonKeys.OTHER, "hashCode", "toString", "", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SerializableAdvancedOverlayViewData {
    private final int dataCaptureViewId;
    private final Long sessionFrameSequenceId;
    private final int trackedBarcodeId;
    private final SerializableAdvancedOverlayView view;

    public static /* synthetic */ SerializableAdvancedOverlayViewData copy$default(SerializableAdvancedOverlayViewData serializableAdvancedOverlayViewData, SerializableAdvancedOverlayView serializableAdvancedOverlayView, int i, Long l, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            serializableAdvancedOverlayView = serializableAdvancedOverlayViewData.view;
        }
        if ((i3 & 2) != 0) {
            i = serializableAdvancedOverlayViewData.trackedBarcodeId;
        }
        if ((i3 & 4) != 0) {
            l = serializableAdvancedOverlayViewData.sessionFrameSequenceId;
        }
        if ((i3 & 8) != 0) {
            i2 = serializableAdvancedOverlayViewData.dataCaptureViewId;
        }
        return serializableAdvancedOverlayViewData.copy(serializableAdvancedOverlayView, i, l, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final SerializableAdvancedOverlayView getView() {
        return this.view;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTrackedBarcodeId() {
        return this.trackedBarcodeId;
    }

    /* renamed from: component3, reason: from getter */
    public final Long getSessionFrameSequenceId() {
        return this.sessionFrameSequenceId;
    }

    /* renamed from: component4, reason: from getter */
    public final int getDataCaptureViewId() {
        return this.dataCaptureViewId;
    }

    public final SerializableAdvancedOverlayViewData copy(SerializableAdvancedOverlayView view, int trackedBarcodeId, Long sessionFrameSequenceId, int dataCaptureViewId) {
        return new SerializableAdvancedOverlayViewData(view, trackedBarcodeId, sessionFrameSequenceId, dataCaptureViewId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializableAdvancedOverlayViewData)) {
            return false;
        }
        SerializableAdvancedOverlayViewData serializableAdvancedOverlayViewData = (SerializableAdvancedOverlayViewData) other;
        return Intrinsics.areEqual(this.view, serializableAdvancedOverlayViewData.view) && this.trackedBarcodeId == serializableAdvancedOverlayViewData.trackedBarcodeId && Intrinsics.areEqual(this.sessionFrameSequenceId, serializableAdvancedOverlayViewData.sessionFrameSequenceId) && this.dataCaptureViewId == serializableAdvancedOverlayViewData.dataCaptureViewId;
    }

    public int hashCode() {
        SerializableAdvancedOverlayView serializableAdvancedOverlayView = this.view;
        int hashCode = (((serializableAdvancedOverlayView == null ? 0 : serializableAdvancedOverlayView.hashCode()) * 31) + Integer.hashCode(this.trackedBarcodeId)) * 31;
        Long l = this.sessionFrameSequenceId;
        return ((hashCode + (l != null ? l.hashCode() : 0)) * 31) + Integer.hashCode(this.dataCaptureViewId);
    }

    public String toString() {
        return "SerializableAdvancedOverlayViewData(view=" + this.view + ", trackedBarcodeId=" + this.trackedBarcodeId + ", sessionFrameSequenceId=" + this.sessionFrameSequenceId + ", dataCaptureViewId=" + this.dataCaptureViewId + ")";
    }

    public SerializableAdvancedOverlayViewData(SerializableAdvancedOverlayView serializableAdvancedOverlayView, int i, Long l, int i2) {
        this.view = serializableAdvancedOverlayView;
        this.trackedBarcodeId = i;
        this.sessionFrameSequenceId = l;
        this.dataCaptureViewId = i2;
    }

    public final SerializableAdvancedOverlayView getView() {
        return this.view;
    }

    public final int getTrackedBarcodeId() {
        return this.trackedBarcodeId;
    }

    public final Long getSessionFrameSequenceId() {
        return this.sessionFrameSequenceId;
    }

    public final int getDataCaptureViewId() {
        return this.dataCaptureViewId;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SerializableAdvancedOverlayViewData(JSONObject json) {
        this(SerializableAdvancedOverlayView.INSTANCE.fromJson(json.optJSONObject("viewJson")), json.getInt("trackedBarcodeIdentifier"), (json.has("sessionFrameSequenceID") && (json.get("sessionFrameSequenceID") instanceof Long)) ? Long.valueOf(json.getLong("sessionFrameSequenceID")) : null, json.getInt(ExtentionsKt.DATA_CAPTURE_VIEW_ID_KEY));
        Intrinsics.checkNotNullParameter(json, "json");
    }
}
