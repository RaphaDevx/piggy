package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Q extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ BarcodeFindItem b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(u0 u0Var, BarcodeFindItem barcodeFindItem) {
        super(1);
        this.a = u0Var;
        this.b = barcodeFindItem;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        CardsCarouselView it = (CardsCarouselView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.i.moveItemOnTopAndCollapse(String.valueOf(this.b.hashCode()));
        return Unit.INSTANCE;
    }
}
