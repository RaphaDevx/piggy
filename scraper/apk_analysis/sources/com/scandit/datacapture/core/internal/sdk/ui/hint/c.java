package com.scandit.datacapture.core.internal.sdk.ui.hint;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ HintHolderV2Impl a;
    public final /* synthetic */ GuidanceHint b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(HintHolderV2Impl hintHolderV2Impl, GuidanceHint guidanceHint) {
        super(0);
        this.a = hintHolderV2Impl;
        this.b = guidanceHint;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        HideHintListener hideHintListener = this.a.getHideHintListener();
        if (hideHintListener != null) {
            hideHintListener.userWantsToHideGuidance(this.b);
        }
        return Unit.INSTANCE;
    }
}
