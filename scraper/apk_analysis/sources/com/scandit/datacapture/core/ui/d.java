package com.scandit.datacapture.core.ui;

import com.scandit.datacapture.core.internal.module.ui.GestureRecognizer;
import com.scandit.datacapture.core.internal.module.ui.r;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ GestureRecognizer a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(GestureRecognizer gestureRecognizer) {
        super(0);
        this.a = gestureRecognizer;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new r(this.a);
    }
}
