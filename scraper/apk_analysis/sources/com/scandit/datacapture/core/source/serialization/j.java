package com.scandit.datacapture.core.source.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function0 {
    public final /* synthetic */ FrameSourceDeserializerHelper a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(FrameSourceDeserializerHelper frameSourceDeserializerHelper) {
        super(0);
        this.a = frameSourceDeserializerHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new FrameSourceDeserializerHelperReversedAdapter(this.a, null, 2, null);
    }
}
