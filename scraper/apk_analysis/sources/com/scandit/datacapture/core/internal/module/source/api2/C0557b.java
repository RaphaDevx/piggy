package com.scandit.datacapture.core.internal.module.source.api2;

import android.os.Handler;
import android.os.Looper;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0557b extends Lambda implements Function0 {
    public static final C0557b a = new C0557b();

    public C0557b() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            throw new IllegalArgumentException("Can only create the handler from a Looper thread".toString());
        }
        Intrinsics.checkNotNullExpressionValue(myLooper, "requireNotNull(...)");
        if (Intrinsics.areEqual(myLooper, Looper.getMainLooper())) {
            return null;
        }
        return new Handler(myLooper);
    }
}
