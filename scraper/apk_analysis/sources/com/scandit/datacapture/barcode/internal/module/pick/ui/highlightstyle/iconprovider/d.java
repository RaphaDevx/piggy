package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider;

import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.m;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements c {
    public final c b;
    public final c c;

    public d(BarcodePickHighlightStyleIconsHolder overrides, c defaultsProvider) {
        Intrinsics.checkNotNullParameter(overrides, "overrides");
        Intrinsics.checkNotNullParameter(defaultsProvider, "defaultsProvider");
        this.b = overrides;
        this.c = defaultsProvider;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c
    public final BarcodePickIcon a(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BarcodePickIcon a = this.b.a(state);
        return Intrinsics.areEqual(a, m.a) ? this.c.a(state) : a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c
    public final BarcodePickIcon b(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BarcodePickIcon b = this.b.b(state);
        return Intrinsics.areEqual(b, m.a) ? this.c.b(state) : b;
    }
}
