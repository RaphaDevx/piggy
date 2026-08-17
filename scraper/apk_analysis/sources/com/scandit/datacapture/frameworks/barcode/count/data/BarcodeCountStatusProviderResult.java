package com.scandit.datacapture.frameworks.barcode.count.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusResult;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusResultAbort;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusResultError;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusResultSuccess;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeCountStatusProviderResult.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\b\u0010\u000e\u001a\u00020\nH\u0002J\u0016\u0010\u000f\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0016\u0010\u0012\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountStatusProviderResult;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "requestId", "", "getRequestId", "()Ljava/lang/String;", "get", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusResult;", "barcodesFromEvent", "", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "getAbort", "getError", "getStatusList", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusItem;", "getSuccess", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountStatusProviderResult {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_ID = "barcodeId";
    private static final String FIELD_DISABLED_MESSAGE = "statusModeDisabledMessage";
    private static final String FIELD_ENABLED_MESSAGE = "statusModeEnabledMessage";
    private static final String FIELD_ERROR_MESSAGE = "errorMessage";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_STATUS_LIST = "statusList";
    private static final String FIELD_TYPE = "type";
    private final JSONObject json;

    public /* synthetic */ BarcodeCountStatusProviderResult(JSONObject jSONObject, DefaultConstructorMarker defaultConstructorMarker) {
        this(jSONObject);
    }

    private BarcodeCountStatusProviderResult(JSONObject jSONObject) {
        this.json = jSONObject;
    }

    public final String getRequestId() {
        Object obj = this.json.get("requestId");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        return (String) obj;
    }

    public final BarcodeCountStatusResult get(List<TrackedBarcode> barcodesFromEvent) {
        Intrinsics.checkNotNullParameter(barcodesFromEvent, "barcodesFromEvent");
        Object obj = this.json.get("type");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj;
        int hashCode = str.hashCode();
        if (hashCode != -941931246) {
            if (hashCode != -937757718) {
                if (hashCode == 369783141 && str.equals("barcodeCountStatusResultSuccess")) {
                    return getSuccess(barcodesFromEvent);
                }
            } else if (str.equals("barcodeCountStatusResultError")) {
                return getError(barcodesFromEvent);
            }
        } else if (str.equals("barcodeCountStatusResultAbort")) {
            return getAbort();
        }
        throw new IllegalArgumentException("Invalid BarcodeCountStatusResult type = " + str);
    }

    private final BarcodeCountStatusResult getSuccess(List<TrackedBarcode> barcodesFromEvent) {
        return BarcodeCountStatusResultSuccess.INSTANCE.create(getStatusList(barcodesFromEvent), (String) this.json.get(FIELD_ENABLED_MESSAGE), (String) this.json.get(FIELD_DISABLED_MESSAGE));
    }

    private final BarcodeCountStatusResult getError(List<TrackedBarcode> barcodesFromEvent) {
        return BarcodeCountStatusResultError.INSTANCE.create(getStatusList(barcodesFromEvent), (String) this.json.get(FIELD_ERROR_MESSAGE), (String) this.json.get(FIELD_DISABLED_MESSAGE));
    }

    private final BarcodeCountStatusResult getAbort() {
        return BarcodeCountStatusResultAbort.INSTANCE.create((String) this.json.get(FIELD_ERROR_MESSAGE));
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0046, code lost:
    
        r3 = com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountStatusProviderResultKt.toBarcodeCountStatus(r3.get("status").toString());
        r0.add(r5.create(r7, r3));
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusItem> getStatusList(java.util.List<com.scandit.datacapture.barcode.batch.data.TrackedBarcode> r10) {
        /*
            r9 = this;
            org.json.JSONObject r9 = r9.json
            java.lang.String r0 = "statusList"
            org.json.JSONArray r9 = r9.getJSONArray(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r0 = (java.util.List) r0
            int r1 = r9.length()
            r2 = 0
        L14:
            if (r2 >= r1) goto L66
            org.json.JSONObject r3 = r9.getJSONObject(r2)
            java.lang.String r4 = "barcodeId"
            java.lang.Object r4 = r3.get(r4)
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Int"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusItem$Companion r5 = com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusItem.INSTANCE
            r6 = r10
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r6 = r6.iterator()
        L34:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L5e
            java.lang.Object r7 = r6.next()
            com.scandit.datacapture.barcode.batch.data.TrackedBarcode r7 = (com.scandit.datacapture.barcode.batch.data.TrackedBarcode) r7
            int r8 = r7.getIdentifier()
            if (r8 != r4) goto L34
            java.lang.String r4 = "status"
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = r3.toString()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus r3 = com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountStatusProviderResultKt.access$toBarcodeCountStatus(r3)
            com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusItem r3 = r5.create(r7, r3)
            r0.add(r3)
            int r2 = r2 + 1
            goto L14
        L5e:
            java.util.NoSuchElementException r9 = new java.util.NoSuchElementException
            java.lang.String r10 = "Collection contains no element matching the predicate."
            r9.<init>(r10)
            throw r9
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountStatusProviderResult.getStatusList(java.util.List):java.util.List");
    }

    /* compiled from: BarcodeCountStatusProviderResult.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountStatusProviderResult$Companion;", "", "()V", "FIELD_BARCODE_ID", "", "FIELD_DISABLED_MESSAGE", "FIELD_ENABLED_MESSAGE", "FIELD_ERROR_MESSAGE", "FIELD_STATUS", "FIELD_STATUS_LIST", "FIELD_TYPE", "createFromJson", "Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountStatusProviderResult;", "barcodeCountStatusResultJson", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeCountStatusProviderResult createFromJson(String barcodeCountStatusResultJson) {
            Intrinsics.checkNotNullParameter(barcodeCountStatusResultJson, "barcodeCountStatusResultJson");
            return new BarcodeCountStatusProviderResult(new JSONObject(barcodeCountStatusResultJson), null);
        }
    }
}
