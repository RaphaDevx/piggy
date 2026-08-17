package com.scandit.datacapture.frameworks.barcode.batch.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AdvancedOverlayViewData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/AdvancedOverlayViewData;", "", "widgetBytes", "", "trackedBarcodeId", "", "sessionFrameSequenceId", "", "dataCaptureViewId", "([BILjava/lang/Long;I)V", "getDataCaptureViewId", "()I", "getSessionFrameSequenceId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getTrackedBarcodeId", "getWidgetBytes", "()[B", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AdvancedOverlayViewData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_DATA_CAPTURE_VIEW_ID = "dataCaptureViewId";
    private static final String FIELD_FRAME_SEQUENCE_ID = "sessionFrameSequenceID";
    private static final String FIELD_TRACKED_BARCODE_ID = "trackedBarcodeIdentifier";
    private static final String FIELD_WIDGET = "widget";
    private final int dataCaptureViewId;
    private final Long sessionFrameSequenceId;
    private final int trackedBarcodeId;
    private final byte[] widgetBytes;

    public AdvancedOverlayViewData(byte[] bArr, int i, Long l, int i2) {
        this.widgetBytes = bArr;
        this.trackedBarcodeId = i;
        this.sessionFrameSequenceId = l;
        this.dataCaptureViewId = i2;
    }

    public final byte[] getWidgetBytes() {
        return this.widgetBytes;
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

    /* compiled from: AdvancedOverlayViewData.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\b\u001a\u00020\t2&\u0010\n\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/AdvancedOverlayViewData$Companion;", "", "()V", "FIELD_DATA_CAPTURE_VIEW_ID", "", "FIELD_FRAME_SEQUENCE_ID", "FIELD_TRACKED_BARCODE_ID", "FIELD_WIDGET", "create", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/AdvancedOverlayViewData;", "data", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.scandit.datacapture.frameworks.barcode.batch.data.AdvancedOverlayViewData create(java.util.HashMap<java.lang.String, java.lang.Object> r6) {
            /*
                r5 = this;
                java.lang.String r5 = "data"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r5)
                java.lang.String r5 = "widget"
                boolean r0 = r6.containsKey(r5)
                r1 = 0
                if (r0 == 0) goto L19
                java.lang.Object r5 = r6.get(r5)
                boolean r0 = r5 instanceof byte[]
                if (r0 == 0) goto L19
                byte[] r5 = (byte[]) r5
                goto L1a
            L19:
                r5 = r1
            L1a:
                java.lang.String r0 = "trackedBarcodeIdentifier"
                java.lang.Object r0 = r6.get(r0)
                java.lang.String r2 = "null cannot be cast to non-null type kotlin.Int"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r2)
                java.lang.Integer r0 = (java.lang.Integer) r0
                int r0 = r0.intValue()
                java.lang.String r3 = "sessionFrameSequenceID"
                boolean r4 = r6.containsKey(r3)
                if (r4 == 0) goto L3f
                java.lang.Object r1 = r6.get(r3)
                java.lang.String r1 = java.lang.String.valueOf(r1)
                java.lang.Long r1 = kotlin.text.StringsKt.toLongOrNull(r1)
            L3f:
                com.scandit.datacapture.frameworks.barcode.batch.data.AdvancedOverlayViewData r3 = new com.scandit.datacapture.frameworks.barcode.batch.data.AdvancedOverlayViewData
                java.lang.String r4 = "dataCaptureViewId"
                java.lang.Object r6 = r6.get(r4)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r2)
                java.lang.Integer r6 = (java.lang.Integer) r6
                int r6 = r6.intValue()
                r3.<init>(r5, r0, r1, r6)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.frameworks.barcode.batch.data.AdvancedOverlayViewData.Companion.create(java.util.HashMap):com.scandit.datacapture.frameworks.barcode.batch.data.AdvancedOverlayViewData");
        }
    }
}
