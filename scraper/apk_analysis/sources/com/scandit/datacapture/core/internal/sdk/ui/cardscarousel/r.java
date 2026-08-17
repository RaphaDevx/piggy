package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function0 {
    public final /* synthetic */ CardsCarouselView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(CardsCarouselView cardsCarouselView) {
        super(0);
        this.a = cardsCarouselView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        List list;
        CardsCarouselView.UiListener uiListener;
        list = this.a.c;
        if (list.size() > 1 && (uiListener = this.a.getUiListener()) != null) {
            uiListener.onSwipedUpWhileCollapsed();
        }
        return Unit.INSTANCE;
    }
}
