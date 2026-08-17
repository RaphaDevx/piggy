package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.s, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0374s extends Lambda implements Function1 {
    public final /* synthetic */ C0384x a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0374s(C0384x c0384x) {
        super(1);
        this.a = c0384x;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a.a(it);
    }
}
