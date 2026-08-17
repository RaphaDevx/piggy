package ch.datatrans.payment;

import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.kd, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0170kd extends SuspendLambda implements Function1 {
    public int a;
    public final /* synthetic */ C0176ld b;
    public Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0170kd(C0176ld c0176ld, Continuation continuation) {
        super(1, continuation);
        this.b = c0176ld;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new C0170kd(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new C0170kd(this.b, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C0131e c0131e = this.b.e().d;
            Intrinsics.checkNotNull(c0131e);
            C7 a = r4.a();
            LinkedHashMap linkedHashMap = this.b.e().h.k;
            this.c = SpillingKt.nullOutSpilledVariable(c0131e);
            this.a = 1;
            obj = a.a(c0131e, linkedHashMap, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Lb lb = (Lb) obj;
        this.b.e().n = lb.a;
        this.b.h.postValue(lb.b);
        return Unit.INSTANCE;
    }
}
