package com.scandit.datacapture.barcode.internal.module.batch.ui.overlay;

import android.view.View;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.ObjectOverlayUtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements View.OnLayoutChangeListener {
    public final /* synthetic */ Function1 a;

    public c(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (Intrinsics.areEqual(ObjectOverlayUtilsKt.getSize(view), Size2UtilsKt.getSIZE_2_ZERO())) {
            return;
        }
        this.a.invoke(view);
        view.removeOnLayoutChangeListener(this);
    }
}
