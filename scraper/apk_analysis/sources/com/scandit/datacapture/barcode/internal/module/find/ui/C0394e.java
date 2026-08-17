package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.math.MathKt;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0394e extends Lambda implements Function0 {
    public static final C0394e a = new C0394e();

    public C0394e() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Lazy lazy = C0402m.f;
        return Integer.valueOf(MathKt.roundToInt(C0398i.b() * 2));
    }
}
