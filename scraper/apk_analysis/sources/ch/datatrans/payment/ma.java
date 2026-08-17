package ch.datatrans.payment;

import com.samsung.android.sdk.samsungpay.v2.SamsungPay;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes3.dex */
public final class ma extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0193oa b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ma(C0193oa c0193oa, Continuation continuation) {
        super(2, continuation);
        this.b = c0193oa;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ma(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new ma(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        C0193oa c0193oa = this.b;
        this.a = 1;
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(this));
        new SamsungPay(c0193oa.a, c0193oa.b.createPartnerInfo$lib_release(c0193oa.c)).getSamsungPayStatus(new C0175la(c0193oa, safeContinuation));
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(this);
        }
        return orThrow == coroutine_suspended ? coroutine_suspended : orThrow;
    }
}
