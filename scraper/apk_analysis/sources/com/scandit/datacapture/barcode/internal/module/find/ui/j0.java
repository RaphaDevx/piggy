package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardData;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ Map b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(u0 u0Var, Map map) {
        super(1);
        this.a = u0Var;
        this.b = map;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TextCardData copy;
        CardsCarouselView it = (CardsCarouselView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        CardsCarouselView cardsCarouselView = this.a.i;
        List<CardData> data = cardsCarouselView.getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.collections.List<com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardData>");
        u0 u0Var = this.a;
        Map map = this.b;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(data, 10));
        Iterator<T> it2 = data.iterator();
        while (it2.hasNext()) {
            TextCardData textCardData = (TextCardData) it2.next();
            boolean containsKey = map.containsKey(textCardData.getItemId());
            u0Var.getClass();
            copy = textCardData.copy((r20 & 1) != 0 ? textCardData.itemId : null, (r20 & 2) != 0 ? textCardData.title : null, (r20 & 4) != 0 ? textCardData.contentDescription : null, (r20 & 8) != 0 ? textCardData.backgroundColor : 0, (r20 & 16) != 0 ? textCardData.errorColor : 0, (r20 & 32) != 0 ? textCardData.iconRes : containsKey ? Integer.valueOf(R.drawable.sc_magnifying_glass_check) : null, (r20 & 64) != 0 ? textCardData.showError : false, (r20 & 128) != 0 ? textCardData.com.google.firebase.analytics.FirebaseAnalytics.Param.CONTENT java.lang.String : null, (r20 & 256) != 0 ? textCardData.image : null);
            arrayList.add(copy);
        }
        cardsCarouselView.setData(arrayList);
        this.a.i.setCollapsed(true, false);
        this.a.k.b();
        return Unit.INSTANCE;
    }
}
