package com.scandit.datacapture.core.internal.module.ui.control.layout;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.ui.control.Control;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public final /* synthetic */ Control a;
    public final /* synthetic */ ControlLayout b;
    public final /* synthetic */ Anchor c;
    public final /* synthetic */ PointWithUnit d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Control control, ControlLayout controlLayout, Anchor anchor, PointWithUnit pointWithUnit) {
        super(1);
        this.a = control;
        this.b = controlLayout;
        this.c = anchor;
        this.d = pointWithUnit;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        c cVar;
        ControlLayout it = (ControlLayout) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        View view = this.a.get_view();
        cVar = this.b.a;
        ((d) cVar).a(view, this.c, this.d, new Size2(this.b.getWidth(), this.b.getHeight()));
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        this.b.addView(view, view.getLayoutParams());
        return Unit.INSTANCE;
    }
}
