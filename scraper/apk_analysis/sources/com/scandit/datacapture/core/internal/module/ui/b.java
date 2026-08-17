package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.core.source.FrameSource;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ FrameSource a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(FrameSource frameSource) {
        super(1);
        this.a = frameSource;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        use.w = this.a != null;
        return Unit.INSTANCE;
    }
}
