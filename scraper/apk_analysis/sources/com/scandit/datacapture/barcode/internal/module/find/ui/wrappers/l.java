package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.content.Context;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l extends Lambda implements Function1 {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.ui.b a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(com.scandit.datacapture.barcode.internal.module.ui.b bVar) {
        super(1);
        this.a = bVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context context = (Context) obj;
        Intrinsics.checkNotNullParameter(context, "context");
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setId(R.id.sc_find_exit_button_wrapper_view);
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.a;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        Unit unit = Unit.INSTANCE;
        frameLayout.addView(bVar, layoutParams);
        frameLayout.setVisibility(4);
        return frameLayout;
    }
}
