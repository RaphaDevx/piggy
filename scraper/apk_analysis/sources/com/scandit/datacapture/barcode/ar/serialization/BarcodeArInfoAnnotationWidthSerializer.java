package com.scandit.datacapture.barcode.ar.serialization;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationWidthPreset;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/serialization/BarcodeArInfoAnnotationWidthSerializer;", "", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;", "width", "", "toJson", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationWidthPreset;)Ljava/lang/String;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArInfoAnnotationWidthSerializer {
    public static final BarcodeArInfoAnnotationWidthSerializer INSTANCE = new BarcodeArInfoAnnotationWidthSerializer();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BarcodeArInfoAnnotationWidthPreset.values().length];
            try {
                iArr[BarcodeArInfoAnnotationWidthPreset.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BarcodeArInfoAnnotationWidthPreset.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BarcodeArInfoAnnotationWidthPreset.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BarcodeArInfoAnnotationWidthSerializer() {
    }

    @JvmStatic
    public static final String toJson(BarcodeArInfoAnnotationWidthPreset width) {
        Intrinsics.checkNotNullParameter(width, "width");
        int i = WhenMappings.$EnumSwitchMapping$0[width.ordinal()];
        if (i == 1) {
            return "small";
        }
        if (i == 2) {
            return "medium";
        }
        if (i == 3) {
            return "large";
        }
        throw new NoWhenBranchMatchedException();
    }
}
