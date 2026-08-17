package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0391b extends ViewOutlineProvider {
    public final int a;
    public final int b;
    public final /* synthetic */ C0402m c;

    public C0391b(C0402m c0402m, int i, int i2) {
        this.c = c0402m;
        this.a = i;
        this.b = i2;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(outline, "outline");
        if (this.c.e) {
            Lazy lazy = C0402m.f;
            i = (int) (C0398i.b() - ((Number) C0402m.h.getValue()).floatValue());
        } else {
            i = 0;
        }
        outline.setOval(i, i, this.a - i, this.b - i);
    }
}
