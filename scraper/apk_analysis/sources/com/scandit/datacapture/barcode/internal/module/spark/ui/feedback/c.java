package com.scandit.datacapture.barcode.internal.module.spark.ui.feedback;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c extends View {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setVisibility(8);
        setBackground(new ColorDrawable(0));
    }

    public final void a(int i) {
        setBackground(new ColorDrawable(i));
        setAlpha(0.0f);
        setVisibility(0);
        animate().alpha(1.0f).setDuration(200L).withEndAction(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.feedback.c$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                c.a(c.this);
            }
        });
    }

    public static final void a(c this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setVisibility(8);
    }
}
