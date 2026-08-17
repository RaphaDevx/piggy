package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components.BinsView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function0 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ BinsView b;
    public final /* synthetic */ BinsView.TouchedElement.SubHandle c;
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(boolean z, BinsView binsView, BinsView.TouchedElement.SubHandle subHandle, int i) {
        super(0);
        this.a = z;
        this.b = binsView;
        this.c = subHandle;
        this.d = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        if (this.a) {
            BinsView.access$swapSubIndex(this.b, this.c.getCom.google.firebase.analytics.FirebaseAnalytics.Param.INDEX java.lang.String(), this.d);
        }
        this.b.q = BinsView.TouchedElement.Nothing.INSTANCE;
        this.b.r = null;
        BinsView.access$displayToteSubs(this.b);
        return Unit.INSTANCE;
    }
}
