package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.widget.RelativeLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class n extends Lambda implements Function1 {
    public final /* synthetic */ CardsCarouselView a;
    public final /* synthetic */ ExpandedCardsView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(CardsCarouselView cardsCarouselView, ExpandedCardsView expandedCardsView) {
        super(1);
        this.a = cardsCarouselView;
        this.b = expandedCardsView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean z;
        RelativeLayout relativeLayout;
        int intValue = ((Number) obj).intValue();
        z = this.a.m;
        if (z && this.b.getVisibility() == 0) {
            relativeLayout = this.a.i;
            relativeLayout.setTranslationY(Math.max(0.0f, -intValue));
        }
        return Unit.INSTANCE;
    }
}
