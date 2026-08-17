package com.scandit.datacapture.frameworks.barcode.capture.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCaptureOverlayCreationData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureOverlayCreationData;", "", "isBasic", "", "overlayJsonString", "", ExtentionsKt.MODE_ID_KEY, "", "(ZLjava/lang/String;I)V", "()Z", "getModeId", "()I", "getOverlayJsonString", "()Ljava/lang/String;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCaptureOverlayCreationData {
    private static final String BASIC_OVERLAY_TYPE = "barcodeCapture";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isBasic;
    private final int modeId;
    private final String overlayJsonString;

    public BarcodeCaptureOverlayCreationData(boolean z, String overlayJsonString, int i) {
        Intrinsics.checkNotNullParameter(overlayJsonString, "overlayJsonString");
        this.isBasic = z;
        this.overlayJsonString = overlayJsonString;
        this.modeId = i;
    }

    /* renamed from: isBasic, reason: from getter */
    public final boolean getIsBasic() {
        return this.isBasic;
    }

    public final String getOverlayJsonString() {
        return this.overlayJsonString;
    }

    public final int getModeId() {
        return this.modeId;
    }

    /* compiled from: BarcodeCaptureOverlayCreationData.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureOverlayCreationData$Companion;", "", "()V", "BASIC_OVERLAY_TYPE", "", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureOverlayCreationData;", "overlayJsonString", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeCaptureOverlayCreationData fromJson(String overlayJsonString) {
            Intrinsics.checkNotNullParameter(overlayJsonString, "overlayJsonString");
            JsonValue jsonValue = new JsonValue(overlayJsonString);
            String byKeyAsString = jsonValue.getByKeyAsString("type", "");
            return new BarcodeCaptureOverlayCreationData(Intrinsics.areEqual(byKeyAsString, "barcodeCapture"), overlayJsonString, jsonValue.getByKeyAsInt(ExtentionsKt.MODE_ID_KEY, -1));
        }
    }
}
