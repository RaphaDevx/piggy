package com.scandit.datacapture.core.internal.module.ui.hint;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import com.scandit.datacapture.core.internal.module.ui.NativeHintPresenter;
import com.scandit.datacapture.core.internal.module.ui.NativeHintStyle;
import com.scandit.datacapture.core.ui.animation.SpringAnimation;
import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z extends NativeHintPresenter {
    public final s a;
    public final Handler b;
    public final WeakReference c;
    public final CopyOnWriteArrayList d;
    public x e;

    public z(j hintHolder, k operationsHandler) {
        Handler handler = new Handler(Looper.getMainLooper());
        Intrinsics.checkNotNullParameter(hintHolder, "hintHolder");
        Intrinsics.checkNotNullParameter(operationsHandler, "operationsHandler");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = operationsHandler;
        this.b = handler;
        this.c = new WeakReference(hintHolder);
        this.d = new CopyOnWriteArrayList();
        this.e = x.a;
    }

    public final synchronized void a() {
        s sVar = this.a;
        x state = this.e;
        CopyOnWriteArrayList operations = this.d;
        ((k) sVar).getClass();
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(operations, "operations");
        if (state == x.a && !operations.isEmpty()) {
            w wVar = (w) this.d.remove(0);
            this.e = x.b;
            if (wVar instanceof u) {
                a((u) wVar);
            } else if (wVar instanceof t) {
                c();
            } else if (wVar instanceof v) {
                a((v) wVar);
            }
        }
    }

    public final synchronized void b() {
        s sVar = this.a;
        x state = this.e;
        CopyOnWriteArrayList operations = this.d;
        ((k) sVar).getClass();
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(operations, "operations");
        if (state != x.b || operations.isEmpty()) {
            this.e = x.a;
        } else {
            w wVar = (w) this.d.remove(0);
            if (wVar instanceof u) {
                a((u) wVar);
            } else if (wVar instanceof t) {
                c();
            } else if (wVar instanceof v) {
                a((v) wVar);
            }
        }
    }

    public final void c() {
        this.b.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.z$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                z.a(z.this);
            }
        });
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenter
    public final void hideHint() {
        this.d.add(t.a);
        a();
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenter
    public final void showHint(String hint, NativeHintStyle style) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(style, "style");
        this.d.add(new u(hint, style));
        a();
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativeHintPresenter
    public final void updateHint(NativeHintStyle style) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.d.add(new v(style));
        a();
    }

    public final void a(final u uVar) {
        this.b.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.z$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                z.a(z.this, uVar);
            }
        });
    }

    public static final void a(final z this$0, final u operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        final q qVar = (q) this$0.c.get();
        if (qVar != null) {
            ((TextView) ((j) qVar).a.getValue()).post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.z$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    z.a(q.this, operation, this$0);
                }
            });
        }
    }

    public static final void a(final z this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final q qVar = (q) this$0.c.get();
        if (qVar != null) {
            ((TextView) ((j) qVar).a.getValue()).post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.z$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    z.a(q.this, this$0);
                }
            });
        }
    }

    public static final void a(q holder, z this$0) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        y then = new y(this$0);
        j jVar = (j) holder;
        jVar.getClass();
        Intrinsics.checkNotNullParameter(then, "then");
        ((Handler) jVar.g.getValue()).removeCallbacksAndMessages(null);
        ((SpringAnimation) jVar.e.getValue()).animateToFinalPosition(0.0f);
        SpringAnimation springAnimation = (SpringAnimation) jVar.f.getValue();
        springAnimation.animateToFinalPosition(((Number) jVar.b.getValue()).floatValue());
        springAnimation.addEndListener(new h(springAnimation, new C0611e(then)));
        if (jVar.d) {
            return;
        }
        ((SpringAnimation) jVar.e.getValue()).skipToEnd();
        ((SpringAnimation) jVar.f.getValue()).skipToEnd();
    }

    public final void a(final v vVar) {
        this.b.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.z$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                z.a(z.this, vVar);
            }
        });
    }

    public static final void a(final z this$0, final v operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        final q qVar = (q) this$0.c.get();
        if (qVar != null) {
            ((TextView) ((j) qVar).a.getValue()).post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.ui.hint.z$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    z.a(q.this, operation, this$0);
                }
            });
        }
    }

    public static final void a(q holder, u operation, z this$0) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String text = operation.a;
        NativeHintStyle style = operation.b;
        j jVar = (j) holder;
        jVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        ((TextView) jVar.a.getValue()).setText(text);
        jVar.a(style);
        jVar.a();
        this$0.b();
    }

    public static final void a(q holder, v operation, z this$0) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((j) holder).a(operation.a);
        this$0.b();
    }
}
