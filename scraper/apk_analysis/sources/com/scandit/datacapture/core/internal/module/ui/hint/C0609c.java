package com.scandit.datacapture.core.internal.module.ui.hint;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.ui.hint.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0609c extends Lambda implements Function0 {
    public final /* synthetic */ j a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0609c(j jVar) {
        super(0);
        this.a = jVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ViewParent parent = ((TextView) this.a.a.getValue()).getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        return Integer.valueOf(((ViewGroup) parent).getHeight());
    }
}
