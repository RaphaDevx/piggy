package ch.datatrans.payment;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class W2 extends Lambda implements Function0 {
    public final /* synthetic */ r3 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W2(r3 r3Var) {
        super(0);
        this.a = r3Var;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.requireActivity().getDefaultViewModelCreationExtras();
    }
}
