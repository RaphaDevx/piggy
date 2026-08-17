package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class F2 extends ContinuationImpl {
    public /* synthetic */ Object b;
    public final /* synthetic */ M2 c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F2(M2 m2, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.c = m2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return M2.a(this.c, this);
    }
}
