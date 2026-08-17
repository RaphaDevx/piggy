package ch.datatrans.payment;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes3.dex */
public final /* synthetic */ class H5 extends FunctionReferenceImpl implements Function0 {
    public H5(N5 n5) {
        super(0, n5, N5.class, "start", "start()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ((N5) this.receiver).a();
        return Unit.INSTANCE;
    }
}
