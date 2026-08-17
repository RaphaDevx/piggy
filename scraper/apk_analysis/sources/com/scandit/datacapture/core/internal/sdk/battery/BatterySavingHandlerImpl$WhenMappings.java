package com.scandit.datacapture.core.internal.sdk.battery;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.source.BatterySavingMode;
import kotlin.Metadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* synthetic */ class BatterySavingHandlerImpl$WhenMappings {
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
