package ch.datatrans.payment;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* renamed from: ch.datatrans.payment.q8, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0202q8 extends SuspendLambda implements Function2 {
    public final /* synthetic */ C0247w8 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0202q8(C0247w8 c0247w8, Continuation continuation) {
        super(2, continuation);
        this.a = c0247w8;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0202q8(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0202q8(this.a, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        C0247w8 c0247w8 = this.a;
        int i = C0247w8.r;
        c0247w8.a(true);
        return Unit.INSTANCE;
    }
}
