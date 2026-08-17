package com.scandit.datacapture.core.internal.module.gl;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public static final e a = new e();

    public e() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        l lVar = (l) g.a.getValue();
        lVar.g.sendEmptyMessage(5);
        return lVar;
    }
}
