package com.scandit.datacapture.core.internal.sdk.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.hint.HintView;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ HintHolderV2Impl a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(HintHolderV2Impl hintHolderV2Impl) {
        super(0);
        this.a = hintHolderV2Impl;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Map map;
        map = this.a.e;
        HintHolderV2Impl hintHolderV2Impl = this.a;
        for (Map.Entry entry : map.entrySet()) {
            ((HintView) entry.getValue()).a((GuidanceHint) entry.getKey(), hintHolderV2Impl.getViewFinderRect());
        }
        return Unit.INSTANCE;
    }
}
