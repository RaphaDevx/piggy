package com.scandit.capacitor.datacapture.barcode.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: AdvancedOverlayViewSerialization.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewOptions;", "", SerializableAdvancedOverlayViewOptions.FIELD_SCALE, "", "width", "", "height", "<init>", "(FII)V", "getScale", "()F", "getWidth", "()I", "getHeight", "component1", "component2", "component3", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "toString", "", "Companion", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SerializableAdvancedOverlayViewOptions {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_HEIGHT = "height";
    private static final String FIELD_SCALE = "scale";
    private static final String FIELD_SIZE = "size";
    private static final String FIELD_WIDTH = "width";
    private final int height;
    private final float scale;
    private final int width;

    public static /* synthetic */ SerializableAdvancedOverlayViewOptions copy$default(SerializableAdvancedOverlayViewOptions serializableAdvancedOverlayViewOptions, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            f = serializableAdvancedOverlayViewOptions.scale;
        }
        if ((i3 & 2) != 0) {
            i = serializableAdvancedOverlayViewOptions.width;
        }
        if ((i3 & 4) != 0) {
            i2 = serializableAdvancedOverlayViewOptions.height;
        }
        return serializableAdvancedOverlayViewOptions.copy(f, i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final float getScale() {
        return this.scale;
    }

    /* renamed from: component2, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component3, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    public final SerializableAdvancedOverlayViewOptions copy(float scale, int width, int height) {
        return new SerializableAdvancedOverlayViewOptions(scale, width, height);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializableAdvancedOverlayViewOptions)) {
            return false;
        }
        SerializableAdvancedOverlayViewOptions serializableAdvancedOverlayViewOptions = (SerializableAdvancedOverlayViewOptions) other;
        return Float.compare(this.scale, serializableAdvancedOverlayViewOptions.scale) == 0 && this.width == serializableAdvancedOverlayViewOptions.width && this.height == serializableAdvancedOverlayViewOptions.height;
    }

    public int hashCode() {
        return (((Float.hashCode(this.scale) * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
    }

    public String toString() {
        return "SerializableAdvancedOverlayViewOptions(scale=" + this.scale + ", width=" + this.width + ", height=" + this.height + ")";
    }

    public SerializableAdvancedOverlayViewOptions(float f, int i, int i2) {
        this.scale = f;
        this.width = i;
        this.height = i2;
    }

    public final float getScale() {
        return this.scale;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    /* compiled from: AdvancedOverlayViewSerialization.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewOptions$Companion;", "", "<init>", "()V", "FIELD_WIDTH", "", "FIELD_HEIGHT", "FIELD_SIZE", "FIELD_SCALE", "fromJson", "Lcom/scandit/capacitor/datacapture/barcode/utils/SerializableAdvancedOverlayViewOptions;", "json", "Lorg/json/JSONObject;", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SerializableAdvancedOverlayViewOptions fromJson(JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            float optDouble = (float) json.optDouble(SerializableAdvancedOverlayViewOptions.FIELD_SCALE, 1.0d);
            JSONObject optJSONObject = json.optJSONObject("size");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            double optDouble2 = optJSONObject.optDouble("width");
            int i = Double.isNaN(optDouble2) ? -2 : (int) (optDouble2 * optDouble);
            double optDouble3 = optJSONObject.optDouble("height");
            return new SerializableAdvancedOverlayViewOptions(optDouble, i, Double.isNaN(optDouble3) ? -2 : (int) (optDouble3 * optDouble));
        }
    }
}
