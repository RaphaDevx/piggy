package ch.datatrans.payment;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class Fe {
    public static final String a(Function1 elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Ge ge = new Ge();
        elements.invoke(ge);
        String sb = ge.a.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }
}
