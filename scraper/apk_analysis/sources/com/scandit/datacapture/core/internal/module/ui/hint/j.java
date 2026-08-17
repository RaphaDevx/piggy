package com.scandit.datacapture.core.internal.module.ui.hint;

import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;
import com.scandit.datacapture.core.ui.animation.SpringAnimation;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements q {
    public final Lazy a;
    public final Lazy b;
    public final Lazy c;
    public boolean d;
    public final Lazy e;
    public final Lazy f;
    public final Lazy g;

    public j(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = LazyKt.lazy(new f(container));
        this.b = LazyKt.lazy(new g(this));
        this.c = LazyKt.lazy(new C0609c(this));
        this.d = true;
        this.e = LazyKt.lazy(new C0608b(this));
        this.f = LazyKt.lazy(new i(this));
        this.g = LazyKt.lazy(C0610d.a);
    }

    public static final void a(j this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((SpringAnimation) this$0.e.getValue()).animateToFinalPosition(1.0f);
    }

    public final void a() {
        ((Handler) this.g.getValue()).postDelayed(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.j$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                j.a(j.this);
            }
        }, 200L);
        SpringAnimation springAnimation = (SpringAnimation) this.f.getValue();
        ViewGroup.LayoutParams layoutParams = ((TextView) this.a.getValue()).getLayoutParams();
        springAnimation.animateToFinalPosition((layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null) != null ? r1.topMargin : 0);
        if (this.d) {
            return;
        }
        ((SpringAnimation) this.e.getValue()).skipToEnd();
        ((SpringAnimation) this.f.getValue()).skipToEnd();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.core.internal.module.ui.NativeHintStyle r13) {
        /*
            Method dump skipped, instructions count: 814
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.ui.hint.j.a(com.scandit.datacapture.core.internal.module.ui.NativeHintStyle):void");
    }
}
