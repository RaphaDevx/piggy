package ch.datatrans.payment;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes3.dex */
public final class Y3 extends Lambda implements Function0 {
    public final /* synthetic */ AbstractActivityC0147g4 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y3(AbstractActivityC0147g4 abstractActivityC0147g4) {
        super(0);
        this.a = abstractActivityC0147g4;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.getDefaultViewModelCreationExtras();
    }
}
