package ch.datatrans.payment;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;

/* loaded from: classes3.dex */
public final class y7 extends ContinuationImpl {
    public Object a;
    public /* synthetic */ Object b;
    public final /* synthetic */ C7 c;
    public int d;
    public String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y7(C7 c7, Continuation continuation) {
        super(continuation);
        this.c = c7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.b = obj;
        this.d |= Integer.MIN_VALUE;
        return this.c.a((Function0) null, (String) null, this);
    }
}
