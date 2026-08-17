package com.scandit.datacapture.frameworks.barcode.find.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindView;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewDefaults;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeFindViewFrameworksDefaults.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/defaults/BarcodeFindViewFrameworksDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "()V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeFindViewFrameworksDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to("hardwareTriggerSupported", Boolean.valueOf(BarcodeFindView.INSTANCE.getHardwareTriggerSupported())), TuplesKt.to("shouldShowCarousel", true), TuplesKt.to("shouldShowFinishButton", true), TuplesKt.to("shouldShowHints", true), TuplesKt.to("shouldShowPauseButton", true), TuplesKt.to("shouldShowProgressBar", false), TuplesKt.to("shouldShowUserGuidanceView", true), TuplesKt.to("shouldShowTorchControl", false), TuplesKt.to("shouldShowZoomControl", false), TuplesKt.to("textForAllItemsFoundSuccessfullyHint", BarcodeFindViewDefaults.getDefaultTextForAllItemsFoundSuccessfullyHint()), TuplesKt.to("textForCollapseCardsButton", BarcodeFindViewDefaults.getDefaultTextForCollapseCardsButton()), TuplesKt.to("textForMoveCloserToBarcodesHint", BarcodeFindViewDefaults.getDefaultTextForMoveCloserToBarcodesHint()), TuplesKt.to("textForPointAtBarcodesToSearchHint", BarcodeFindViewDefaults.getDefaultTextForPointAtBarcodesToSearchHint()), TuplesKt.to("textForTapShutterToPauseScreenHint", BarcodeFindViewDefaults.getDefaultTextForTapShutterToPauseScreenHint()), TuplesKt.to("textForTapShutterToResumeSearchHint", BarcodeFindViewDefaults.getDefaultTextForTapShutterToResumeSearchHint()), TuplesKt.to("textForItemListUpdatedHint", BarcodeFindViewDefaults.getDefaultTextForItemListUpdatedHint()), TuplesKt.to("textForItemListUpdatedWhenPausedHint", BarcodeFindViewDefaults.getDefaultTextForItemListUpdatedWhenPausedHint()));
    }

    /* compiled from: BarcodeFindViewFrameworksDefaults.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0007¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/defaults/BarcodeFindViewFrameworksDefaults$Companion;", "", "()V", "get", "", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeFindViewFrameworksDefaults().toMap();
        }
    }
}
