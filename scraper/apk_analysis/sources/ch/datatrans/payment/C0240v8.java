package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: ch.datatrans.payment.v8, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0240v8 extends ContinuationImpl {
    public Xa a;
    public /* synthetic */ Object c;
    public final /* synthetic */ C0247w8 d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0240v8(C0247w8 c0247w8, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = c0247w8;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return C0247w8.a(this.d, this);
    }
}
