package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class od extends ContinuationImpl {
    public Object a;
    public /* synthetic */ Object b;
    public final /* synthetic */ C0224td c;
    public int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public od(C0224td c0224td, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.c = c0224td;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return C0224td.a(this.c, (Nb) null, this);
    }
}
