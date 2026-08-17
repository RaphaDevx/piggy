package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.Card;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class P7 extends ContinuationImpl {
    public Object a;
    public Card b;
    public /* synthetic */ Object c;
    public final /* synthetic */ Q7 d;
    public int e;
    public Object f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P7(Q7 q7, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = q7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return this.d.a((String) null, (Card) null, this);
    }
}
