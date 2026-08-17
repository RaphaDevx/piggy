package com.scandit.datacapture.core.ui;

import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListener;
import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListenerReversedAdapter;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ NeedsRedrawListener a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(NeedsRedrawListener needsRedrawListener) {
        super(0);
        this.a = needsRedrawListener;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new NeedsRedrawListenerReversedAdapter(this.a, null, 2, null);
    }
}
