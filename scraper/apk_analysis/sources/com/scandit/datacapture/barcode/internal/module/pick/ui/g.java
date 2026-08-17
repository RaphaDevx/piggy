package com.scandit.datacapture.barcode.internal.module.pick.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g implements f {
    public final Context a;

    public g(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
    }

    public static final void a(View view) {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.f
    public final View a() {
        FrameLayout frameLayout = new FrameLayout(this.a);
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.pick.ui.g$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                g.a(view);
            }
        });
        return frameLayout;
    }
}
