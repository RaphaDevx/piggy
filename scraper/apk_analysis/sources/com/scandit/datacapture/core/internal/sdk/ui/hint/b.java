package com.scandit.datacapture.core.internal.sdk.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.hint.HintView;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public final /* synthetic */ HintHolderV2Impl a;
    public final /* synthetic */ HintView b;
    public final /* synthetic */ ToastHint c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(HintHolderV2Impl hintHolderV2Impl, HintView hintView, ToastHint toastHint) {
        super(0);
        this.a = hintHolderV2Impl;
        this.b = hintView;
        this.c = toastHint;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Map map;
        Map map2;
        this.a.removeView(this.b);
        map = this.a.d;
        map.remove(this.c);
        map2 = this.a.e;
        HintHolderV2Impl hintHolderV2Impl = this.a;
        for (Map.Entry entry : map2.entrySet()) {
            ((HintView) entry.getValue()).a((GuidanceHint) entry.getKey(), hintHolderV2Impl.getViewFinderRect());
        }
        return Unit.INSTANCE;
    }
}
