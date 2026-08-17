package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: ch.datatrans.payment.i, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0157i extends ContinuationImpl {
    public Nb a;
    public Object b;
    public /* synthetic */ Object c;
    public final /* synthetic */ C0182n d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0157i(C0182n c0182n, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = c0182n;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return C0182n.a(this.d, null, this);
    }
}
