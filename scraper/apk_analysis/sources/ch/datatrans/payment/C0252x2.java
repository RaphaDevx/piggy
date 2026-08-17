package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.Card;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* renamed from: ch.datatrans.payment.x2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0252x2 extends ContinuationImpl {
    public Nb a;
    public Object b;
    public /* synthetic */ Object c;
    public final /* synthetic */ M2 d;
    public int e;
    public Card f;
    public Object g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0252x2(M2 m2, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = m2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return this.d.a(this);
    }
}
