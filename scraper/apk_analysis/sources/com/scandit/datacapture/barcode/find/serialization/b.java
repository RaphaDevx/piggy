package com.scandit.datacapture.barcode.find.serialization;

import com.scandit.datacapture.barcode.internal.module.find.serialization.e;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements e {
    public final WeakReference a;

    public b(BarcodeFindDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }
}
