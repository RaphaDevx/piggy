package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function0 {
    public final /* synthetic */ CardsCarouselView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(CardsCarouselView cardsCarouselView) {
        super(0);
        this.a = cardsCarouselView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        StackedCardsView stackedCardsView;
        ExpandedCardsView expandedCardsView;
        this.a.l = false;
        stackedCardsView = this.a.k;
        stackedCardsView.setVisibility(8);
        expandedCardsView = this.a.j;
        expandedCardsView.setVisibility(0);
        return Unit.INSTANCE;
    }
}
