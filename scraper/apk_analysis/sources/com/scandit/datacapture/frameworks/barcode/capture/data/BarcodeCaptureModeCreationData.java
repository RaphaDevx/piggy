package com.scandit.datacapture.frameworks.barcode.capture.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.json.JsonValue;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCaptureModeCreationData.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureModeCreationData;", "", "modeJson", "", "modeId", "", "hasListener", "", "isEnabled", "modeType", BarcodeCaptureModeCreationData.PARENT_ID_KEY, "(Ljava/lang/String;IZZLjava/lang/String;I)V", "getHasListener", "()Z", "getModeId", "()I", "getModeJson", "()Ljava/lang/String;", "getModeType", "getParentId", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCaptureModeCreationData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HAS_LISTENERS_KEY = "hasListeners";
    private static final String MODE_ENABLED_KEY = "enabled";
    private static final String MODE_ID_KEY = "modeId";
    public static final String MODE_TYPE = "barcodeCapture";
    private static final String PARENT_ID_KEY = "parentId";
    private final boolean hasListener;
    private final boolean isEnabled;
    private final int modeId;
    private final String modeJson;
    private final String modeType;
    private final int parentId;

    public BarcodeCaptureModeCreationData(String modeJson, int i, boolean z, boolean z2, String modeType, int i2) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(modeType, "modeType");
        this.modeJson = modeJson;
        this.modeId = i;
        this.hasListener = z;
        this.isEnabled = z2;
        this.modeType = modeType;
        this.parentId = i2;
    }

    public final String getModeJson() {
        return this.modeJson;
    }

    public final int getModeId() {
        return this.modeId;
    }

    public final boolean getHasListener() {
        return this.hasListener;
    }

    /* renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final String getModeType() {
        return this.modeType;
    }

    public final int getParentId() {
        return this.parentId;
    }

    /* compiled from: BarcodeCaptureModeCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureModeCreationData$Companion;", "", "()V", "HAS_LISTENERS_KEY", "", "MODE_ENABLED_KEY", "MODE_ID_KEY", "MODE_TYPE", "PARENT_ID_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/capture/data/BarcodeCaptureModeCreationData;", "modeJson", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeCaptureModeCreationData fromJson(String modeJson) {
            Intrinsics.checkNotNullParameter(modeJson, "modeJson");
            JsonValue jsonValue = new JsonValue(modeJson);
            String byKeyAsString = jsonValue.getByKeyAsString("type", "");
            if (!Intrinsics.areEqual(byKeyAsString, BarcodeCaptureModeCreationData.MODE_TYPE)) {
                return new BarcodeCaptureModeCreationData(modeJson, -1, false, false, byKeyAsString, -1);
            }
            boolean byKeyAsBoolean = jsonValue.getByKeyAsBoolean(BarcodeCaptureModeCreationData.HAS_LISTENERS_KEY, false);
            boolean byKeyAsBoolean2 = jsonValue.getByKeyAsBoolean("enabled", false);
            int byKeyAsInt = jsonValue.getByKeyAsInt("modeId", -1);
            int byKeyAsInt2 = jsonValue.getByKeyAsInt(BarcodeCaptureModeCreationData.PARENT_ID_KEY, -1);
            if (byKeyAsInt == -1) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            return new BarcodeCaptureModeCreationData(modeJson, byKeyAsInt, byKeyAsBoolean, byKeyAsBoolean2, byKeyAsString, byKeyAsInt2);
        }
    }
}
