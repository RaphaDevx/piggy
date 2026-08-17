package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: ch.datatrans.payment.q, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0198q extends ContinuationImpl {
    public Object a;
    public /* synthetic */ Object c;
    public final /* synthetic */ C0204r d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0198q(C0204r c0204r, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = c0204r;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return this.d.a(this);
    }
}
