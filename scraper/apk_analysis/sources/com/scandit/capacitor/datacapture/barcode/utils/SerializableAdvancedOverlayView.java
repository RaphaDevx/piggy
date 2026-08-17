package com.scandit.capacitor.datacapture.barcode.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: AdvancedOverlayViewSerialization.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView;", "", "data", "", "options", "Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewOptions;", "<init>", "(Ljava/lang/String;Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewOptions;)V", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getData", "()Ljava/lang/String;", "getOptions", "()Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewOptions;", "component1", "component2", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "Companion", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SerializableAdvancedOverlayView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_DATA = "data";
    private static final String FIELD_OPTIONS = "options";
    private final String data;
    private final SerializableAdvancedOverlayViewOptions options;

    public static /* synthetic */ SerializableAdvancedOverlayView copy$default(SerializableAdvancedOverlayView serializableAdvancedOverlayView, String str, SerializableAdvancedOverlayViewOptions serializableAdvancedOverlayViewOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serializableAdvancedOverlayView.data;
        }
        if ((i & 2) != 0) {
            serializableAdvancedOverlayViewOptions = serializableAdvancedOverlayView.options;
        }
        return serializableAdvancedOverlayView.copy(str, serializableAdvancedOverlayViewOptions);
    }

    /* renamed from: component1, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component2, reason: from getter */
    public final SerializableAdvancedOverlayViewOptions getOptions() {
        return this.options;
    }

    public final SerializableAdvancedOverlayView copy(String data, SerializableAdvancedOverlayViewOptions options) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(options, "options");
        return new SerializableAdvancedOverlayView(data, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializableAdvancedOverlayView)) {
            return false;
        }
        SerializableAdvancedOverlayView serializableAdvancedOverlayView = (SerializableAdvancedOverlayView) other;
        return Intrinsics.areEqual(this.data, serializableAdvancedOverlayView.data) && Intrinsics.areEqual(this.options, serializableAdvancedOverlayView.options);
    }

    public int hashCode() {
        return (this.data.hashCode() * 31) + this.options.hashCode();
    }

    public String toString() {
        return "SerializableAdvancedOverlayView(data=" + this.data + ", options=" + this.options + ")";
    }

    public SerializableAdvancedOverlayView(String data, SerializableAdvancedOverlayViewOptions options) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(options, "options");
        this.data = data;
        this.options = options;
    }

    public final String getData() {
        return this.data;
    }

    public final SerializableAdvancedOverlayViewOptions getOptions() {
        return this.options;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SerializableAdvancedOverlayView(org.json.JSONObject r4) {
        /*
            r3 = this;
            java.lang.String r0 = "jsonObject"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "data"
            java.lang.String r0 = r4.getString(r0)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayViewOptions$Companion r1 = com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayViewOptions.INSTANCE
            java.lang.String r2 = "options"
            org.json.JSONObject r4 = r4.getJSONObject(r2)
            java.lang.String r2 = "getJSONObject(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayViewOptions r4 = r1.fromJson(r4)
            r3.<init>(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.capacitor.datacapture.barcode.utils.SerializableAdvancedOverlayView.<init>(org.json.JSONObject):void");
    }

    /* compiled from: AdvancedOverlayViewSerialization.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView$Companion;", "", "<init>", "()V", "FIELD_DATA", "", "FIELD_OPTIONS", "fromJson", "Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayView;", "jsonObject", "Lorg/json/JSONObject;", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SerializableAdvancedOverlayView fromJson(JSONObject jsonObject) {
            if (jsonObject == null) {
                return null;
            }
            return new SerializableAdvancedOverlayView(jsonObject);
        }
    }
}
