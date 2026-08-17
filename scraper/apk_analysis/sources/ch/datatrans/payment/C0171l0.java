package ch.datatrans.payment;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;

/* renamed from: ch.datatrans.payment.l0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0171l0 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ C0177m0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0171l0(boolean z, C0177m0 c0177m0, Continuation continuation) {
        super(2, continuation);
        this.b = z;
        this.c = c0177m0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0171l0(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0171l0(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.b) {
                this.a = 1;
                if (DelayKt.delay(200L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.c.g.postValue(EnumC0152h0.b);
        return Unit.INSTANCE;
    }
}
