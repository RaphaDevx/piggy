package ch.datatrans.payment;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.j4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0162j4 extends AbstractC0135e9 {
    public final InterfaceC0235ud b;
    public final int c;
    public final String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0162j4(InterfaceC0235ud name, int i, String str, Function0 selectionAction) {
        super(selectionAction);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(selectionAction, "selectionAction");
        this.b = name;
        this.c = i;
        this.d = str;
    }
}
