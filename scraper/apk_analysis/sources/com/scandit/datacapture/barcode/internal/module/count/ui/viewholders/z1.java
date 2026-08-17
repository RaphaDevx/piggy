package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z1 {
    public final ViewGroup a;
    public final Handler b;

    public z1(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = container;
        this.b = new Handler(Looper.getMainLooper());
    }

    public final void a(View view, Function0 destructor) {
        Intrinsics.checkNotNullParameter(destructor, "destructor");
        if (view != null) {
            this.a.removeView(view);
            destructor.invoke();
        }
    }

    public final void b(View view, Function0 creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        if (view == null) {
            View view2 = (View) creator.invoke();
            if (view2 != null) {
                this.a.addView(view2);
                return;
            }
            return;
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        this.a.addView(view);
    }

    public final void a(final Function1 action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.b.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.z1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                z1.a(Function1.this, this);
            }
        });
    }

    public static final void a(Function1 action, z1 this$0) {
        Intrinsics.checkNotNullParameter(action, "$action");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        action.invoke(this$0.a);
    }
}
