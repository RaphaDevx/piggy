package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0416e extends Lambda implements Function1 {
    public final /* synthetic */ CardsCarouselView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0416e(CardsCarouselView cardsCarouselView) {
        super(1);
        this.a = cardsCarouselView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a;
    }
}
