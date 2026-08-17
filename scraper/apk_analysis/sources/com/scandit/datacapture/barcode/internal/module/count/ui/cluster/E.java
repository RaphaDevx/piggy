package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class E implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        FrameLayout parent = (FrameLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    }
}
