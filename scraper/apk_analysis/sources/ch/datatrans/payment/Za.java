package ch.datatrans.payment;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Za implements InterfaceC0235ud {
    public final String a;

    public Za(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a = text;
    }

    @Override // ch.datatrans.payment.InterfaceC0235ud
    public final String a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.a;
    }
}
