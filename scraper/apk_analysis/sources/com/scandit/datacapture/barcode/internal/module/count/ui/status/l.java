package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l extends Lambda implements Function0 {
    public final /* synthetic */ View a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Function0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(ImageView imageView, boolean z, boolean z2, Function0 function0) {
        super(0);
        this.a = imageView;
        this.b = z;
        this.c = z2;
        this.d = function0;
    }

    public final void a() {
        View view = this.a;
        boolean z = this.b;
        boolean z2 = this.c;
        final Function0 function0 = this.d;
        float f = z ? 1.0f : 0.0f;
        if (z2) {
            view.animate().setInterpolator(new LinearInterpolator()).setDuration(200L).scaleX(f).scaleY(f).withEndAction(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.status.l$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    l.a(Function0.this);
                }
            }).start();
        } else {
            view.setScaleX(f);
            view.setScaleY(f);
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }

    public static final void a(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }
}
