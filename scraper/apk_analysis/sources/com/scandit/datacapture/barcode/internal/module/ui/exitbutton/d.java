package com.scandit.datacapture.barcode.internal.module.ui.exitbutton;

import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ a a;
    public final /* synthetic */ e b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(b bVar, e eVar) {
        super(0);
        this.a = bVar;
        this.b = eVar;
    }

    public static final void a(e this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0 function0 = this$0.b;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final com.scandit.datacapture.barcode.internal.module.ui.b invoke() {
        com.scandit.datacapture.barcode.internal.module.ui.b a = ((b) this.a).a();
        final e eVar = this.b;
        a.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ui.exitbutton.d$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                d.a(e.this, view);
            }
        });
        return a;
    }
}
