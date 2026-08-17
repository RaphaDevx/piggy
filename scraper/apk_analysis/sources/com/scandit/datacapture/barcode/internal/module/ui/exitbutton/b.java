package com.scandit.datacapture.barcode.internal.module.ui.exitbutton;

import android.content.Context;
import android.view.View;
import com.scandit.datacapture.barcode.R;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements a {
    public final Context a;

    public b(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
    }

    public final com.scandit.datacapture.barcode.internal.module.ui.b a() {
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = new com.scandit.datacapture.barcode.internal.module.ui.b(this.a);
        bVar.setId(View.generateViewId());
        bVar.setContentDescription(bVar.getContext().getString(R.string.sc_button_exit_contentDescription));
        bVar.b.setImageResource(R.drawable.sc_ic_exit);
        return bVar;
    }
}
