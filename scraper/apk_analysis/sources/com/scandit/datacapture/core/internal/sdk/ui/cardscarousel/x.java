package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import android.view.GestureDetector;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class x extends Lambda implements Function0 {
    public final /* synthetic */ Context a;
    public final /* synthetic */ ExpandedCardsView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Context context, ExpandedCardsView expandedCardsView) {
        super(0);
        this.a = context;
        this.b = expandedCardsView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ExpandedCardsView$gestureListener$1 expandedCardsView$gestureListener$1;
        Context context = this.a;
        expandedCardsView$gestureListener$1 = this.b.h;
        return new GestureDetector(context, expandedCardsView$gestureListener$1);
    }
}
