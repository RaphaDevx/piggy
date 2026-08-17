package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.source.FrameSource;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a0 extends Lambda implements Function0 {
    public final /* synthetic */ FrameSource a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(FrameSource frameSource) {
        super(0);
        this.a = frameSource;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
