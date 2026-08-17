package com.scandit.datacapture.core.internal.module.ui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.scandit.datacapture.core.common.ContextStatus;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class ErrorOverlay extends FrameLayout {
    private final m a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorOverlay(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        setBackgroundColor(1996488704);
        m mVar = new m(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        mVar.setGravity(17);
        mVar.setLayoutParams(layoutParams);
        mVar.setTextAlignment(4);
        mVar.setTextColor(-1);
        mVar.setBackgroundColor(0);
        int pxFromDp$default = PixelExtensionsKt.pxFromDp$default(20, (Context) null, 1, (Object) null);
        mVar.setPadding(pxFromDp$default, pxFromDp$default, pxFromDp$default, pxFromDp$default);
        mVar.setTextSize(25.0f);
        mVar.setAlpha(0.5f);
        this.a = mVar;
        addView(mVar);
    }

    public final void a(ContextStatus contextStatus) {
        Intrinsics.checkNotNullParameter(contextStatus, "contextStatus");
        String string = "Error " + contextStatus.getCode() + ": " + contextStatus.getMessage();
        Intrinsics.checkNotNullParameter(string, "string");
        this.a.setText(string);
    }

    public final void a() {
        Intrinsics.checkNotNullParameter("", TypedValues.Custom.S_STRING);
        this.a.setText("");
    }
}
