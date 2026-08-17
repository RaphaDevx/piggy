package com.scandit.datacapture.core.internal.sdk.ui.overlay;

import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function1 {
    public final /* synthetic */ TrackedObjectAdvancedOverlay a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ PointWithUnit c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(TrackedObjectAdvancedOverlay trackedObjectAdvancedOverlay, Object obj, PointWithUnit pointWithUnit) {
        super(1);
        this.a = trackedObjectAdvancedOverlay;
        this.b = obj;
        this.c = pointWithUnit;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedObjectAdvancedOverlay it = (TrackedObjectAdvancedOverlay) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.getAugmenter().setOffsetForTrackedObject(this.b, this.c);
        return Unit.INSTANCE;
    }
}
