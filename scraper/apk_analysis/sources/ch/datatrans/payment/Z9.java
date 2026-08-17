package ch.datatrans.payment;

import android.content.Context;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Z9 implements InterfaceC0235ud {
    public final int a;
    public final Object[] b;

    public Z9(int i, Object... formatArgs) {
        Intrinsics.checkNotNullParameter(formatArgs, "formatArgs");
        this.a = i;
        this.b = formatArgs;
    }

    @Override // ch.datatrans.payment.InterfaceC0235ud
    public final String a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = this.a;
        Object[] objArr = this.b;
        String string = context.getString(i, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
