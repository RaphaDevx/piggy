package com.scandit.datacapture.core.internal.module.ui.hint;

import android.os.Handler;
import android.os.Looper;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.ui.hint.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0610d extends Lambda implements Function0 {
    public static final C0610d a = new C0610d();

    public C0610d() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new Handler(Looper.getMainLooper());
    }
}
