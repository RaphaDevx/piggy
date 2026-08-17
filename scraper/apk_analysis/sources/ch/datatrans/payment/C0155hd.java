package ch.datatrans.payment;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.hd, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0155hd extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0176ld b;
    public Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0155hd(C0176ld c0176ld, Continuation continuation) {
        super(2, continuation);
        this.b = c0176ld;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0155hd(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0155hd(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        H h;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.b.e().d == null) {
                        W8 w8 = this.b.e().k;
                        Intrinsics.checkNotNull(w8);
                        String str = this.b.e().n;
                        Intrinsics.checkNotNull(str);
                        String str2 = w8.c;
                        h = new H(w8.a, str, str2, w8.d, w8.e, w8.f, w8.b);
                    } else {
                        C0131e c0131e = this.b.e().d;
                        Intrinsics.checkNotNull(c0131e);
                        String str3 = this.b.e().n;
                        Intrinsics.checkNotNull(str3);
                        h = new H(0, str3, c0131e.a, c0131e.c, c0131e.d, c0131e.e, c0131e.b);
                    }
                    C7 a = r4.a();
                    this.c = SpillingKt.nullOutSpilledVariable(h);
                    this.a = 1;
                    obj = a.a(h, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                Log.e("DTPL", "Twint not cancelled on server", e);
            }
            C0176ld c0176ld = this.b;
            int i2 = C0176ld.j;
            c0176ld.c.postValue(Unit.INSTANCE);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            C0176ld c0176ld2 = this.b;
            int i3 = C0176ld.j;
            c0176ld2.c.postValue(Unit.INSTANCE);
            throw th;
        }
    }
}
