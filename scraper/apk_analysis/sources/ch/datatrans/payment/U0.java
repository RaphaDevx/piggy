package ch.datatrans.payment;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

/* loaded from: classes3.dex */
public final class U0 extends ContinuationImpl {
    public Object a;
    public Object b;
    public /* synthetic */ Object c;
    public final /* synthetic */ V0 d;
    public int e;
    public Object f;
    public Object g;
    public Q3 h;
    public Function2 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U0(V0 v0, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = v0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return V0.a(this.d, null, null, null, null, null, null, this);
    }
}
