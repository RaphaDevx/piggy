package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final class Db extends ContinuationImpl {
    public Function1 a;
    public /* synthetic */ Object c;
    public final /* synthetic */ Hb d;
    public int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Db(Hb hb, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = hb;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return this.d.a((Function1) null, this);
    }
}
