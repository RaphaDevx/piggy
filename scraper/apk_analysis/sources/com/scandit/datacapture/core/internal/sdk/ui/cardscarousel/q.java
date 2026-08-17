package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import java.util.Comparator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class q extends Lambda implements Function0 {
    public final /* synthetic */ CardsCarouselView a;
    public final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(CardsCarouselView cardsCarouselView, String str) {
        super(0);
        this.a = cardsCarouselView;
        this.b = str;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        List list;
        CardsCarouselView cardsCarouselView = this.a;
        list = cardsCarouselView.c;
        final String str = this.b;
        cardsCarouselView.setData(CollectionsKt.sortedWith(list, new Comparator() { // from class: com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView$moveItemOnTopAndCollapse$2$invoke$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Boolean.valueOf(Intrinsics.areEqual(((CardData) t2).getItemId(), str)), Boolean.valueOf(Intrinsics.areEqual(((CardData) t).getItemId(), str)));
            }
        }));
        return Unit.INSTANCE;
    }
}
