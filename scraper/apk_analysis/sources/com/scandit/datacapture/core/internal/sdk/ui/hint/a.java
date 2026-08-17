package com.scandit.datacapture.core.internal.sdk.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.hint.HintView;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ HintHolderV2Impl a;
    public final /* synthetic */ HintView b;
    public final /* synthetic */ GuidanceHint c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(HintHolderV2Impl hintHolderV2Impl, HintView hintView, GuidanceHint guidanceHint) {
        super(0);
        this.a = hintHolderV2Impl;
        this.b = hintView;
        this.c = guidanceHint;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Map map;
        this.a.removeView(this.b);
        map = this.a.e;
        map.remove(this.c);
        return Unit.INSTANCE;
    }
}
