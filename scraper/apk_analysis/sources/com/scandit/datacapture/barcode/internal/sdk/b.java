package com.scandit.datacapture.barcode.internal.sdk;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public final /* synthetic */ NativeTrackedObject a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(NativeTrackedObject nativeTrackedObject) {
        super(0);
        this.a = nativeTrackedObject;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new TrackedObject(this.a);
    }
}
