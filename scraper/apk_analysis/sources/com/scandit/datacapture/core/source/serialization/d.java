package com.scandit.datacapture.core.source.serialization;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ FrameSourceDeserializer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(FrameSourceDeserializer frameSourceDeserializer) {
        super(0);
        this.a = frameSourceDeserializer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
