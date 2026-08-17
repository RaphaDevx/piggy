package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function1 {
    public static final d a = new d();

    public d() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SubView it = (SubView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        it.setAlpha(0.0f);
        return Unit.INSTANCE;
    }
}
