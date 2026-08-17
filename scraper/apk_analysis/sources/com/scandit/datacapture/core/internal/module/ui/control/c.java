package com.scandit.datacapture.core.internal.module.ui.control;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ ToggleImageButton a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(ToggleImageButton toggleImageButton, boolean z) {
        super(1);
        this.a = toggleImageButton;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ToggleImageButton it = (ToggleImageButton) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.setVisibility(this.b ? 0 : 8);
        return Unit.INSTANCE;
    }
}
