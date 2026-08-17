package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public final /* synthetic */ BinsView a;
    public final /* synthetic */ BinsView.TouchedElement.ToteHandle b;
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(BinsView binsView, BinsView.TouchedElement.ToteHandle toteHandle, int i) {
        super(0);
        this.a = binsView;
        this.b = toteHandle;
        this.c = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BinsView.access$swapToteIndex(this.a, this.b.getCom.google.firebase.analytics.FirebaseAnalytics.Param.INDEX java.lang.String(), this.c);
        this.a.q = BinsView.TouchedElement.Nothing.INSTANCE;
        this.a.r = null;
        return Unit.INSTANCE;
    }
}
