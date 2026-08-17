package com.scandit.datacapture.barcode.internal.module.ui.shutterbutton;

import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function0 {
    public final /* synthetic */ i a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(i iVar) {
        super(0);
        this.a = iVar;
    }

    public static final void a(i this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 function0 = this$0.c;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final f invoke() {
        f a = ((b) this.a.a).a();
        final i iVar = this.a;
        a.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.h$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                h.a(i.this, view);
            }
        });
        return a;
    }
}
