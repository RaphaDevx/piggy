package com.scandit.datacapture.core.internal.sdk.ui.hint;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ HintHolderV2Impl a;
    public final /* synthetic */ ToastHint b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(HintHolderV2Impl hintHolderV2Impl, ToastHint toastHint) {
        super(0);
        this.a = hintHolderV2Impl;
        this.b = toastHint;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        HideHintListener hideHintListener = this.a.getHideHintListener();
        if (hideHintListener != null) {
            hideHintListener.userWantsToHideToast(this.b);
        }
        return Unit.INSTANCE;
    }
}
