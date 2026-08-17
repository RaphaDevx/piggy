package com.scandit.datacapture.barcode.internal.module.ui.viewpositioner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.tealium.library.DataSources;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f {
    public static final e e = new e();
    public final d a;
    public final Function1 b;
    public View c;
    public ViewGroup d;

    public f(d positioner, Function1 factory) {
        Intrinsics.checkNotNullParameter(positioner, "positioner");
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.a = positioner;
        this.b = factory;
    }

    public final ViewGroup a() {
        ViewGroup viewGroup = this.d;
        if (viewGroup != null) {
            return viewGroup;
        }
        Intrinsics.throwUninitializedPropertyAccessException("parent");
        return null;
    }

    public final View b() {
        View view = this.c;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    public final void a(ViewGroup parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Function1 function1 = this.b;
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        View view = (View) function1.invoke(context);
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.c = view;
        parent.addView(b());
        Intrinsics.checkNotNullParameter(parent, "<set-?>");
        this.d = parent;
        this.a.a(b(), a());
    }
}
