package com.scandit.datacapture.frameworks.barcode.batch.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeBatchOverlayCreationData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/BarcodeBatchOverlayCreationData;", "", "isBasic", "", "isAdvanced", "overlayJsonString", "", "hasListeners", ExtentionsKt.MODE_ID_KEY, "", "(ZZLjava/lang/String;ZI)V", "getHasListeners", "()Z", "getModeId", "()I", "getOverlayJsonString", "()Ljava/lang/String;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeBatchOverlayCreationData {
    private static final String ADVANCED_OVERLAY_TYPE = "barcodeTrackingAdvanced";
    private static final String BASIC_OVERLAY_TYPE = "barcodeTrackingBasic";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean hasListeners;
    private final boolean isAdvanced;
    private final boolean isBasic;
    private final int modeId;
    private final String overlayJsonString;

    public BarcodeBatchOverlayCreationData(boolean z, boolean z2, String overlayJsonString, boolean z3, int i) {
        Intrinsics.checkNotNullParameter(overlayJsonString, "overlayJsonString");
        this.isBasic = z;
        this.isAdvanced = z2;
        this.overlayJsonString = overlayJsonString;
        this.hasListeners = z3;
        this.modeId = i;
    }

    /* renamed from: isBasic, reason: from getter */
    public final boolean getIsBasic() {
        return this.isBasic;
    }

    /* renamed from: isAdvanced, reason: from getter */
    public final boolean getIsAdvanced() {
        return this.isAdvanced;
    }

    public final String getOverlayJsonString() {
        return this.overlayJsonString;
    }

    public final boolean getHasListeners() {
        return this.hasListeners;
    }

    public final int getModeId() {
        return this.modeId;
    }

    /* compiled from: BarcodeBatchOverlayCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/BarcodeBatchOverlayCreationData$Companion;", "", "()V", "ADVANCED_OVERLAY_TYPE", "", "BASIC_OVERLAY_TYPE", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/BarcodeBatchOverlayCreationData;", "overlayJsonString", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeBatchOverlayCreationData fromJson(String overlayJsonString) {
            Intrinsics.checkNotNullParameter(overlayJsonString, "overlayJsonString");
            JsonValue jsonValue = new JsonValue(overlayJsonString);
            String byKeyAsString = jsonValue.getByKeyAsString("type", "");
            return new BarcodeBatchOverlayCreationData(Intrinsics.areEqual(byKeyAsString, BarcodeBatchOverlayCreationData.BASIC_OVERLAY_TYPE), Intrinsics.areEqual(byKeyAsString, BarcodeBatchOverlayCreationData.ADVANCED_OVERLAY_TYPE), overlayJsonString, jsonValue.getByKeyAsBoolean("hasListener", false), jsonValue.getByKeyAsInt(ExtentionsKt.MODE_ID_KEY, -1));
        }
    }
}
