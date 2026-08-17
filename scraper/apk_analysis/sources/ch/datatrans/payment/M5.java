package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.Collection;
import java.util.Iterator;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes3.dex */
public final class M5 extends ContinuationImpl {
    public Object a;
    public E5 b;
    public Object c;
    public Object d;
    public Collection e;
    public /* synthetic */ Object f;
    public final /* synthetic */ N5 g;
    public int h;
    public Object i;
    public Iterator j;
    public Object k;
    public Object l;
    public Object m;
    public PaymentMethodType n;
    public Object o;
    public int p;
    public int q;
    public int r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M5(N5 n5, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.g = n5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.f = obj;
        this.h |= Integer.MIN_VALUE;
        return N5.a(this.g, this);
    }
}
