package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class v extends Lambda implements Function0 {
    public final /* synthetic */ EditableCardView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(EditableCardView editableCardView) {
        super(0);
        this.a = editableCardView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        EditableCardData editableCardData;
        EditableCardView editableCardView;
        CardsCarouselView.Listener listener;
        ListenableEditText listenableEditText;
        editableCardData = this.a.b;
        if (editableCardData != null && (listener = (editableCardView = this.a).getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) != null) {
            listenableEditText = editableCardView.e;
            listener.onEditableFieldFinishedEditing(editableCardData, listenableEditText);
        }
        return Unit.INSTANCE;
    }
}
