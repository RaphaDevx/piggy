package com.scandit.datacapture.core.internal.module.ui.control;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class ToggleImageButton extends ImageButton {
    private static final Lazy d = LazyKt.lazy(a.a);
    private static final Lazy e = LazyKt.lazy(b.a);
    private volatile boolean a;
    private Function0 b;
    private Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToggleImageButton(Context context, ViewGroup.LayoutParams initialParams) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(initialParams, "initialParams");
        setLayoutParams(initialParams);
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        setBackground(new ColorDrawable(0));
        setPadding(0, 0, 0, 0);
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.module.ui.control.ToggleImageButton$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToggleImageButton.a(ToggleImageButton.this, view);
            }
        });
    }

    public final boolean b() {
        return this.a;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0 && !this.a) {
            this.a = true;
            Function0 function0 = this.b;
            if (function0 != null) {
                function0.invoke();
            }
        } else if ((event.getAction() == 1 || event.getAction() == 3) && this.a) {
            this.a = false;
            Function0 function02 = this.b;
            if (function02 != null) {
                function02.invoke();
            }
        }
        return super.onTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(ToggleImageButton this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 function0 = this$0.c;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void b(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.c = block;
    }

    public final void a(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.b = block;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ToggleImageButton(android.content.Context r4) {
        /*
            r3 = this;
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams
            kotlin.Lazy r1 = com.scandit.datacapture.core.internal.module.ui.control.ToggleImageButton.d
            java.lang.Object r2 = r1.getValue()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r1 = r1.getValue()
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            r0.<init>(r2, r1)
            r3.<init>(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.ui.control.ToggleImageButton.<init>(android.content.Context):void");
    }
}
