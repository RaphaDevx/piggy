package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.widget.EditText;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z implements CardsCarouselView.Listener {
    public final /* synthetic */ StackedCardsView a;

    public z(StackedCardsView stackedCardsView) {
        this.a = stackedCardsView;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final Integer iconResForTextChange(String oldText, String newText) {
        Intrinsics.checkNotNullParameter(oldText, "oldText");
        Intrinsics.checkNotNullParameter(newText, "newText");
        CardsCarouselView.Listener cardEventListener = this.a.getCardEventListener();
        if (cardEventListener != null) {
            return cardEventListener.iconResForTextChange(oldText, newText);
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onCardIconTapped(CardData cardData, Integer num, String str) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        CardsCarouselView.Listener cardEventListener = this.a.getCardEventListener();
        if (cardEventListener != null) {
            cardEventListener.onCardIconTapped(cardData, num, str);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onCardTapped(CardData cardData) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        CardsCarouselView.Listener cardEventListener = this.a.getCardEventListener();
        if (cardEventListener != null) {
            cardEventListener.onCardTapped(cardData);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onEditableFieldFinishedEditing(CardData cardData, EditText editText) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        Intrinsics.checkNotNullParameter(editText, "editText");
        CardsCarouselView.Listener cardEventListener = this.a.getCardEventListener();
        if (cardEventListener != null) {
            cardEventListener.onEditableFieldFinishedEditing(cardData, editText);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onEditableFieldTapped(CardData cardData, EditText editText) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        Intrinsics.checkNotNullParameter(editText, "editText");
        CardsCarouselView.Listener cardEventListener = this.a.getCardEventListener();
        if (cardEventListener != null) {
            cardEventListener.onEditableFieldTapped(cardData, editText);
        }
    }
}
