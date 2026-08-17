package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewUiListener;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class w0 extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeFindViewUiListener a;
    public final /* synthetic */ Set b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(BarcodeFindViewUiListener barcodeFindViewUiListener, Set set) {
        super(0);
        this.a = barcodeFindViewUiListener;
        this.b = set;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.onFinishButtonTapped(this.b);
        return Unit.INSTANCE;
    }
}
