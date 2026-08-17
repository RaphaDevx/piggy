package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.widget.EditText;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements CardsCarouselView.Listener {
    public final /* synthetic */ CardsCarouselView a;

    public h(CardsCarouselView cardsCarouselView) {
        this.a = cardsCarouselView;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final Integer iconResForTextChange(String oldText, String newText) {
        Intrinsics.checkNotNullParameter(oldText, "oldText");
        Intrinsics.checkNotNullParameter(newText, "newText");
        CardsCarouselView.Listener listener = this.a.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
        if (listener != null) {
            return listener.iconResForTextChange(oldText, newText);
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onCardIconTapped(CardData cardData, Integer num, String str) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        CardsCarouselView.Listener listener = this.a.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
        if (listener != null) {
            listener.onCardIconTapped(cardData, num, str);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onCardTapped(CardData cardData) {
        List list;
        List list2;
        CardsCarouselView.Listener listener;
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        list = this.a.c;
        if (list.size() > 1) {
            CardsCarouselView.UiListener uiListener = this.a.getUiListener();
            if (uiListener != null) {
                uiListener.onTappedWhileCollapsed();
                return;
            }
            return;
        }
        list2 = this.a.c;
        CardData cardData2 = (CardData) CollectionsKt.firstOrNull(list2);
        if (cardData2 == null || (listener = this.a.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        listener.onCardTapped(cardData2);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onEditableFieldFinishedEditing(CardData cardData, EditText editText) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        Intrinsics.checkNotNullParameter(editText, "editText");
        CardsCarouselView.Listener listener = this.a.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
        if (listener != null) {
            listener.onEditableFieldFinishedEditing(cardData, editText);
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView.Listener
    public final void onEditableFieldTapped(CardData cardData, EditText editText) {
        Intrinsics.checkNotNullParameter(cardData, "cardData");
        Intrinsics.checkNotNullParameter(editText, "editText");
        CardsCarouselView.Listener listener = this.a.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
        if (listener != null) {
            listener.onEditableFieldTapped(cardData, editText);
        }
    }
}
