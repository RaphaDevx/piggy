package com.scandit.datacapture.core.internal.sdk.ui.overlay;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public final /* synthetic */ TrackedObjectAdvancedOverlay a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ View c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(TrackedObjectAdvancedOverlay trackedObjectAdvancedOverlay, Object obj, View view) {
        super(1);
        this.a = trackedObjectAdvancedOverlay;
        this.b = obj;
        this.c = view;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedObjectAdvancedOverlay it = (TrackedObjectAdvancedOverlay) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.getAugmenter().setViewForTrackedObject(this.b, this.c);
        return Unit.INSTANCE;
    }
}
