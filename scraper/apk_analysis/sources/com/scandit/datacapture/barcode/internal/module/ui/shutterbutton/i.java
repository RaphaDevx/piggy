package com.scandit.datacapture.barcode.internal.module.ui.shutterbutton;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i implements ShutterButtonWrapper {
    public final a a;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d b;
    public Function0 c;
    public RelativeLayout d;
    public final Lazy e;

    public i(b shutterButtonFactory, com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d positioner) {
        Intrinsics.checkNotNullParameter(shutterButtonFactory, "shutterButtonFactory");
        Intrinsics.checkNotNullParameter(positioner, "positioner");
        this.a = shutterButtonFactory;
        this.b = positioner;
        this.e = LazyKt.lazy(new h(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper
    public final void a(ViewGroup viewGroup, g data) {
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(data, "data");
        this.d = parent;
        parent.addView((f) this.e.getValue());
        Intrinsics.checkNotNullParameter(data, "data");
        ((f) this.e.getValue()).setVisibility(!data.a ? 4 : 0);
        a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper
    public final void b() {
        ((f) this.e.getValue()).getForeground().setLevel(1);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper
    public final void c() {
        ((f) this.e.getValue()).getForeground().setLevel(0);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper
    public final void d() {
        ((f) this.e.getValue()).a.stop();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper
    public final void a(com.scandit.datacapture.barcode.pick.ui.b bVar) {
        this.c = bVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.ShutterButtonWrapper
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d dVar = this.b;
        f fVar = (f) this.e.getValue();
        RelativeLayout relativeLayout = this.d;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            relativeLayout = null;
        }
        dVar.a(fVar, relativeLayout);
    }
}
