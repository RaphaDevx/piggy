package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;

/* loaded from: classes2.dex */
public final class r implements CardsCarouselView.UiListener {
    public final /* synthetic */ u0 a;

    public r(u0 u0Var) {
        this.a = u0Var;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.UiListener
    public final void onAcceptScanButtonTapped() {
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.UiListener
    public final void onCancelScanButtonTapped() {
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.UiListener
    public final void onRestartScanButtonTapped() {
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.UiListener
    public final void onSwipedDownWhileExpanded() {
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.UiListener
    public final void onSwipedUpWhileCollapsed() {
        CardsCarouselView.setCollapsed$default(this.a.i, false, false, 2, null);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.UiListener
    public final void onTappedWhileCollapsed() {
        CardsCarouselView.setCollapsed$default(this.a.i, false, false, 2, null);
    }
}
