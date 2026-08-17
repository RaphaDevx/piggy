package com.scandit.datacapture.barcode.internal.module.batch.ui.overlay;

import android.view.View;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.Augmentation;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ e a;
    public final /* synthetic */ Augmentation b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(e eVar, Augmentation augmentation) {
        super(1);
        this.a = eVar;
        this.b = augmentation;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        View view = (View) obj;
        Intrinsics.checkNotNullParameter(view, "view");
        this.a.a(this.b);
        view.setVisibility(0);
        return Unit.INSTANCE;
    }
}
