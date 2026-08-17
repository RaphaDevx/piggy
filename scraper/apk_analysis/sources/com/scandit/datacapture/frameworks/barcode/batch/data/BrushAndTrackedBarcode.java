package com.scandit.datacapture.frameworks.barcode.batch.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.Metadata;

/* compiled from: BrushAndTrackedBarcode.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B!\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/BrushAndTrackedBarcode;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", BrushAndTrackedBarcode.FIELD_BRUSH, "Lcom/scandit/datacapture/core/ui/style/Brush;", "trackedBarcodeId", "", "sessionFrameSequenceId", "", "(Lcom/scandit/datacapture/core/ui/style/Brush;ILjava/lang/Long;)V", "getBrush", "()Lcom/scandit/datacapture/core/ui/style/Brush;", "getSessionFrameSequenceId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTrackedBarcodeId", "()I", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BrushAndTrackedBarcode {
    private static final String FIELD_BRUSH = "brush";
    private static final String FIELD_FRAME_SEQUENCE_ID = "sessionFrameSequenceID";
    private static final String FIELD_TRACKED_BARCODE_ID = "trackedBarcodeIdentifier";
    private final Brush brush;
    private final Long sessionFrameSequenceId;
    private final int trackedBarcodeId;

    public BrushAndTrackedBarcode(Brush brush, int i, Long l) {
        this.brush = brush;
        this.trackedBarcodeId = i;
        this.sessionFrameSequenceId = l;
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final int getTrackedBarcodeId() {
        return this.trackedBarcodeId;
    }

    public final Long getSessionFrameSequenceId() {
        return this.sessionFrameSequenceId;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BrushAndTrackedBarcode(org.json.JSONObject r7) {
        /*
            r6 = this;
            java.lang.String r0 = "jsonObject"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "brush"
            boolean r1 = r7.has(r0)
            r2 = 0
            if (r1 == 0) goto L22
            boolean r1 = r7.isNull(r0)
            if (r1 != 0) goto L22
            java.lang.String r0 = r7.getString(r0)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.scandit.datacapture.core.ui.style.Brush r0 = com.scandit.datacapture.core.ui.style.BrushDeserializer.fromJson(r0)
            goto L23
        L22:
            r0 = r2
        L23:
            java.lang.String r1 = "trackedBarcodeIdentifier"
            int r1 = r7.getInt(r1)
            java.lang.String r3 = "sessionFrameSequenceID"
            boolean r4 = r7.has(r3)
            if (r4 == 0) goto L3b
            r4 = -1
            long r2 = r7.optLong(r3, r4)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
        L3b:
            r6.<init>(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.frameworks.barcode.batch.data.BrushAndTrackedBarcode.<init>(org.json.JSONObject):void");
    }
}
