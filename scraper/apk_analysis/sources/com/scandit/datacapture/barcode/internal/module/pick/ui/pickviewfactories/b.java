package com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ BarcodePick a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(BarcodePick barcodePick) {
        super(1);
        this.a = barcodePick;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedObject it = (TrackedObject) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        BarcodePick._onTrackedObjectTap$scandit_barcode_capture$default(this.a, it, null, 2, null);
        return Unit.INSTANCE;
    }
}
