package ch.datatrans.payment;

import android.content.Intent;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class Z4 extends ContinuationImpl {
    public Object a;
    public Object b;
    public /* synthetic */ Object c;
    public final /* synthetic */ i5 d;
    public int e;
    public Object f;
    public Object g;
    public String h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z4(i5 i5Var, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.d = i5Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.c = obj;
        this.e |= Integer.MIN_VALUE;
        return this.d.a(0, (Intent) null, this);
    }
}
