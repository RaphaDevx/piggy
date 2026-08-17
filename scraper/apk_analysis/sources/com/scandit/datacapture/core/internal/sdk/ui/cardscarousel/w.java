package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardSubView;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.ExpandedCardsView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class w extends BaseAdapter {
    public final /* synthetic */ ExpandedCardsView a;

    public w(ExpandedCardsView expandedCardsView) {
        this.a = expandedCardsView;
    }

    public static final void a(ExpandedCardsView this$0, View view) {
        int positionForView;
        List list;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardsCarouselView.Listener cardEventListener = this$0.getCardEventListener();
        if (cardEventListener == null || (positionForView = this$0.getPositionForView(view)) == -1) {
            return;
        }
        list = this$0.c;
        cardEventListener.onCardTapped((CardData) list.get(positionForView));
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        List list;
        list = this.a.c;
        return list.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        List list;
        list = this.a.c;
        return list.get(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup container) {
        List list;
        CardSubView textCardView;
        ExpandedCardsView.CardListenerForwarder cardListenerForwarder;
        Intrinsics.checkNotNullParameter(container, "container");
        list = this.a.c;
        Object obj = list.get(i);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData");
        CardData cardData = (CardData) obj;
        CardView cardView = view instanceof CardView ? (CardView) view : null;
        if (cardView == null) {
            Context context = container.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            CardSubView.Companion companion = CardSubView.INSTANCE;
            Context context2 = this.a.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            Class<?> cls = cardData.getClass();
            if (Intrinsics.areEqual(cls, EditableCardData.class)) {
                textCardView = new EditableCardView(context2, false, 2, null);
            } else {
                if (!Intrinsics.areEqual(cls, TextCardData.class)) {
                    throw new IllegalStateException("Unsupported configuration type".toString());
                }
                textCardView = new TextCardView(context2);
            }
            cardListenerForwarder = this.a.f;
            textCardView.setListener(cardListenerForwarder);
            CardView cardView2 = new CardView(context, textCardView);
            final ExpandedCardsView expandedCardsView = this.a;
            cardView2.setElevation(CardView.INSTANCE.getELEVATION$scandit_capture_core());
            cardView2.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.w$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    w.a(ExpandedCardsView.this, view2);
                }
            });
            cardView = cardView2;
        }
        ExpandedCardsView.access$bind(this.a, cardView, cardData);
        return cardView;
    }
}
