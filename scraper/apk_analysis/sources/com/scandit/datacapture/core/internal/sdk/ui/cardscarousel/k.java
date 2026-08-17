package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.widget.RelativeLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function0 {
    public final /* synthetic */ CardsCarouselView a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(CardsCarouselView cardsCarouselView, Function0 function0) {
        super(0);
        this.a = cardsCarouselView;
        this.b = function0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        RelativeLayout relativeLayout;
        this.a.l = true;
        if (this.a.getButtonBarSettings().buttonBarVisibilityCanChange()) {
            relativeLayout = this.a.i;
            relativeLayout.setVisibility(this.a.getButtonBarSettings().buttonBarVisibility(false));
        }
        this.b.invoke();
        return Unit.INSTANCE;
    }
}
