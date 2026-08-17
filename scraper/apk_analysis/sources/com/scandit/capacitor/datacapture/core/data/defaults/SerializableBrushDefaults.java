package com.scandit.capacitor.datacapture.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.capacitor.datacapture.core.data.SerializableData;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SerializableBrushDefaults.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0013\b\u0016\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0007\u0010\u000bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0006\u0010\u000f\u001a\u00020\u000eJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0002\u0010\u0013J2\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/scandit/capacitor/datacapture/core/data/defaults/SerializableBrushDefaults;", "Lcom/scandit/capacitor/datacapture/core/data/SerializableData;", SerializableBrushDefaults.FIELD_FILL_COLOR, "", SerializableBrushDefaults.FIELD_STROKE_COLOR, SerializableBrushDefaults.FIELD_STROKE_WIDTH, "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;)V", "brush", "Lcom/scandit/datacapture/core/ui/style/Brush;", "(Lcom/scandit/datacapture/core/ui/style/Brush;)V", "Ljava/lang/Float;", "toJson", "Lorg/json/JSONObject;", "toJSONObject", "component1", "component2", "component3", "()Ljava/lang/Float;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;)Lcom/scandit/capacitor/datacapture/core/data/defaults/SerializableBrushDefaults;", "equals", "", Request.JsonKeys.OTHER, "", "hashCode", "", "toString", "Companion", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SerializableBrushDefaults implements SerializableData {
    private static final String FIELD_FILL_COLOR = "fillColor";
    private static final String FIELD_STROKE_COLOR = "strokeColor";
    private static final String FIELD_STROKE_WIDTH = "strokeWidth";
    private final String fillColor;
    private final String strokeColor;
    private final Float strokeWidth;

    /* renamed from: component1, reason: from getter */
    private final String getFillColor() {
        return this.fillColor;
    }

    /* renamed from: component2, reason: from getter */
    private final String getStrokeColor() {
        return this.strokeColor;
    }

    /* renamed from: component3, reason: from getter */
    private final Float getStrokeWidth() {
        return this.strokeWidth;
    }

    public static /* synthetic */ SerializableBrushDefaults copy$default(SerializableBrushDefaults serializableBrushDefaults, String str, String str2, Float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serializableBrushDefaults.fillColor;
        }
        if ((i & 2) != 0) {
            str2 = serializableBrushDefaults.strokeColor;
        }
        if ((i & 4) != 0) {
            f = serializableBrushDefaults.strokeWidth;
        }
        return serializableBrushDefaults.copy(str, str2, f);
    }

    public final SerializableBrushDefaults copy(String fillColor, String strokeColor, Float strokeWidth) {
        return new SerializableBrushDefaults(fillColor, strokeColor, strokeWidth);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializableBrushDefaults)) {
            return false;
        }
        SerializableBrushDefaults serializableBrushDefaults = (SerializableBrushDefaults) other;
        return Intrinsics.areEqual(this.fillColor, serializableBrushDefaults.fillColor) && Intrinsics.areEqual(this.strokeColor, serializableBrushDefaults.strokeColor) && Intrinsics.areEqual((Object) this.strokeWidth, (Object) serializableBrushDefaults.strokeWidth);
    }

    public int hashCode() {
        String str = this.fillColor;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.strokeColor;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Float f = this.strokeWidth;
        return hashCode2 + (f != null ? f.hashCode() : 0);
    }

    public String toString() {
        return "SerializableBrushDefaults(fillColor=" + this.fillColor + ", strokeColor=" + this.strokeColor + ", strokeWidth=" + this.strokeWidth + ")";
    }

    public SerializableBrushDefaults(String str, String str2, Float f) {
        this.fillColor = str;
        this.strokeColor = str2;
        this.strokeWidth = f;
    }

    public SerializableBrushDefaults(Brush brush) {
        this(brush != null ? ExtentionsKt.getHexString(brush.getFillColor()) : null, brush != null ? ExtentionsKt.getHexString(brush.getStrokeColor()) : null, brush != null ? Float.valueOf(brush.getStrokeWidth()) : null);
    }

    @Override // com.scandit.capacitor.datacapture.core.data.SerializableData
    public JSONObject toJson() {
        return new JSONObject(MapsKt.mapOf(TuplesKt.to(FIELD_FILL_COLOR, this.fillColor), TuplesKt.to(FIELD_STROKE_COLOR, this.strokeColor), TuplesKt.to(FIELD_STROKE_WIDTH, this.strokeWidth)));
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(FIELD_FILL_COLOR, this.fillColor);
        jSONObject.put(FIELD_STROKE_COLOR, this.strokeColor);
        jSONObject.put(FIELD_STROKE_WIDTH, this.strokeWidth != null ? r3.floatValue() : 0.0d);
        return jSONObject;
    }
}
