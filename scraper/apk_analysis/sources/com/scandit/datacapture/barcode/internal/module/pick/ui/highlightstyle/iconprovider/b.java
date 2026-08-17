package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider;

import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.l;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.o;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements c {
    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c
    public final BarcodePickIcon a(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        int i = a.a[state.ordinal()];
        if (i == 1) {
            return l.a;
        }
        if (i == 2) {
            return l.a;
        }
        if (i == 3) {
            return new o(R.drawable.sc_ic_barcode_pick_plus);
        }
        if (i == 4) {
            return new o(R.drawable.sc_ic_barcode_pick_picked);
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c
    public final BarcodePickIcon b(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        return m.a;
    }
}
