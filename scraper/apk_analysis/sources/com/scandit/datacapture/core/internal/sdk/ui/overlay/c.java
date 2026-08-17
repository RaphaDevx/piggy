package com.scandit.datacapture.core.internal.sdk.ui.overlay;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ TrackedObjectAdvancedOverlay a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(TrackedObjectAdvancedOverlay trackedObjectAdvancedOverlay) {
        super(1);
        this.a = trackedObjectAdvancedOverlay;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedObjectAdvancedOverlay it = (TrackedObjectAdvancedOverlay) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.getAugmenter().clearAugmentations();
        return Unit.INSTANCE;
    }
}
