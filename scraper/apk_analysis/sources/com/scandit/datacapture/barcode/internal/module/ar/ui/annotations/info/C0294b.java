package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0294b implements InterfaceC0293a {
    public final Context a;
    public final Function0 b;

    public C0294b(Context context, com.scandit.datacapture.barcode.ar.ui.annotations.d bodyDataProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bodyDataProvider, "bodyDataProvider");
        this.a = context;
        this.b = bodyDataProvider;
    }
}
