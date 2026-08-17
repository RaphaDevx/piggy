package com.scandit.datacapture.barcode.ui.overlay;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public static final a a = new a();

    public a() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return "This overlay's mode and view are attached to different data capture contexts!";
    }
}
