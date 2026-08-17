package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class t extends RelativeLayout {
    public final w a;
    public x b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(RelativeLayout container, SparkScanStateManager stateManager) {
        super(container.getContext());
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        w touchListener = new w(context, stateManager, container);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(touchListener, "touchListener");
        this.a = touchListener;
        setBackgroundColor(0);
        s sVar = new s(this);
        Intrinsics.checkNotNullParameter(sVar, "<set-?>");
        touchListener.c = sVar;
        setOnTouchListener(touchListener);
    }
}
