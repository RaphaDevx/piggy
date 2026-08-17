package com.scandit.datacapture.barcode.spark.serialization;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.source.BatterySavingMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/spark/serialization/BatterySavingModeSerializer;", "", "Lcom/scandit/datacapture/core/source/BatterySavingMode;", "batterySavingMode", "", "toJson", "(Lcom/scandit/datacapture/core/source/BatterySavingMode;)Ljava/lang/String;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BatterySavingModeSerializer {
    public static final BatterySavingModeSerializer INSTANCE = new BatterySavingModeSerializer();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BatterySavingMode.values().length];
            try {
                iArr[BatterySavingMode.ON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BatterySavingMode.OFF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BatterySavingMode.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BatterySavingModeSerializer() {
    }

    @JvmStatic
    public static final String toJson(BatterySavingMode batterySavingMode) {
        Intrinsics.checkNotNullParameter(batterySavingMode, "batterySavingMode");
        int i = WhenMappings.$EnumSwitchMapping$0[batterySavingMode.ordinal()];
        if (i == 1) {
            return "on";
        }
        if (i == 2) {
            return "off";
        }
        if (i == 3) {
            return "auto";
        }
        throw new NoWhenBranchMatchedException();
    }
}
