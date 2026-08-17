package com.scandit.datacapture.core.internal.sdk.battery;

import com.scandit.datacapture.core.source.BatterySavingMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements BatterySavingHandler {
    public final BatterySavingManager a;
    public BatterySavingMode b;
    public Function1 c;
    public final a d;

    public b(BatterySavingManager batterySavingManager) {
        Intrinsics.checkNotNullParameter(batterySavingManager, "batterySavingManager");
        this.a = batterySavingManager;
        this.b = BatterySavingMode.OFF;
        this.d = new a(this);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler
    public final boolean getShouldSaveBattery() {
        int i = BatterySavingHandlerImpl$WhenMappings.$EnumSwitchMapping$0[this.b.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return false;
        }
        if (i == 3) {
            return this.a.getShouldEnableBatterySaving();
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler
    public final void setBatterySavingMode(BatterySavingMode batterySavingMode) {
        Intrinsics.checkNotNullParameter(batterySavingMode, "batterySavingMode");
        this.b = batterySavingMode;
        int i = BatterySavingHandlerImpl$WhenMappings.$EnumSwitchMapping$0[batterySavingMode.ordinal()];
        if (i == 1) {
            this.a.removeListener(this.d);
            Function1 function1 = this.c;
            if (function1 != null) {
                function1.invoke(Boolean.TRUE);
                return;
            }
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.a.addListener(this.d);
        } else {
            this.a.removeListener(this.d);
            Function1 function12 = this.c;
            if (function12 != null) {
                function12.invoke(Boolean.FALSE);
            }
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingHandler
    public final void setListener(Function1 function1) {
        this.c = function1;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(getShouldSaveBattery()));
        }
    }
}
