package com.scandit.datacapture.barcode.internal.module.pick.ui;

import android.content.Context;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements f {
    public final Context a;
    public final String b;
    public final String c;

    public h(Context context, String loadingTextForPicking, String loadingTextForUnpicking) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(loadingTextForPicking, "loadingTextForPicking");
        Intrinsics.checkNotNullParameter(loadingTextForUnpicking, "loadingTextForUnpicking");
        this.a = context;
        this.b = loadingTextForPicking;
        this.c = loadingTextForUnpicking;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.f
    public final View a() {
        return new e(this.a, this.b, this.c);
    }
}
