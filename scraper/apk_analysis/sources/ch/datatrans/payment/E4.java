package ch.datatrans.payment;

import android.content.Context;
import android.content.res.Resources;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class E4 {
    public final Resources a;
    public final String b;

    public E4(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context.getResources();
        this.b = context.getPackageName();
    }
}
