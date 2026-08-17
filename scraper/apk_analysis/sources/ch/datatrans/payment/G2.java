package ch.datatrans.payment;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes3.dex */
public final /* synthetic */ class G2 extends FunctionReferenceImpl implements Function0 {
    public G2(M2 m2) {
        super(0, m2, M2.class, "startWithGivenPayment", "startWithGivenPayment()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ((M2) this.receiver).d();
        return Unit.INSTANCE;
    }
}
