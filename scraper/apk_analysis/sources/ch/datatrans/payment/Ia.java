package ch.datatrans.payment;

import androidx.lifecycle.ViewModelKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;

/* loaded from: classes3.dex */
public final class Ia extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ Ka b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ia(Ka ka, Continuation continuation) {
        super(2, continuation);
        this.b = ka;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new Ia(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new Ia(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.a = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Ka ka = this.b;
        int i2 = Ka.i;
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(ka), null, null, new Ja(ka, null), 3, null);
        return Unit.INSTANCE;
    }
}
