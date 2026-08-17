package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.graphics.Bitmap;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f implements BarcodePickHighlightStyleIconsHolder {
    public final LinkedHashMap b = new LinkedHashMap();
    public final LinkedHashMap c = new LinkedHashMap();

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder
    public final void a(ScanditIcon icon, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(state, "state");
        this.b.put(state, i.a(icon, l.a));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder
    public final void b(Bitmap bitmap, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.b.put(state, i.a(bitmap, l.a));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder
    public final void a(Bitmap bitmap, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.c.put(state, i.a(bitmap, m.a));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder
    public final void b(int i, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.b.put(state, i.a(i, l.a));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder
    public final void b(ScanditIcon icon, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(state, "state");
        this.c.put(state, i.a(icon, m.a));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickHighlightStyleIconsHolder
    public final void a(int i, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.c.put(state, i.a(i, m.a));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c
    public final BarcodePickIcon b(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BarcodePickIcon barcodePickIcon = (BarcodePickIcon) this.c.get(state);
        return barcodePickIcon == null ? m.a : barcodePickIcon;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c
    public final BarcodePickIcon a(BarcodePickState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BarcodePickIcon barcodePickIcon = (BarcodePickIcon) this.b.get(state);
        return barcodePickIcon == null ? m.a : barcodePickIcon;
    }
}
