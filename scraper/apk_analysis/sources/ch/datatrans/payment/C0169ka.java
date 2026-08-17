package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: ch.datatrans.payment.ka, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0169ka extends ContinuationImpl {
    public /* synthetic */ Object a;
    public final /* synthetic */ C0193oa b;
    public int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0169ka(C0193oa c0193oa, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.b = c0193oa;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.a = obj;
        this.c |= Integer.MIN_VALUE;
        return this.b.a((ContinuationImpl) this);
    }
}
