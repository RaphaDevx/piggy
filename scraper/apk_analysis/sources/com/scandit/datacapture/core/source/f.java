package com.scandit.datacapture.core.source;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public final /* synthetic */ SequenceFrameSourceProcessListener a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(SequenceFrameSourceProcessListener sequenceFrameSourceProcessListener) {
        super(1);
        this.a = sequenceFrameSourceProcessListener;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        byte[] buffer = (byte[]) obj;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.a.processingFinished(buffer);
        return Unit.INSTANCE;
    }
}
