package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class m implements View.OnAttachStateChangeListener {
    public final /* synthetic */ n a;

    public m(n nVar) {
        this.a = nVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        n nVar = this.a;
        if (nVar.a == l.b) {
            view.setOnTouchListener(nVar.b);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.a.a == l.b) {
            view.setOnTouchListener(null);
        }
    }
}
