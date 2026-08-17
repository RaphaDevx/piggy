package ch.datatrans.payment;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class K extends SuspendLambda implements Function2 {
    public final /* synthetic */ e0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(e0 e0Var, Continuation continuation) {
        super(2, continuation);
        this.a = e0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new K(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new K(this.a, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        C0204r c0204r = this.a.c;
        if (c0204r == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binRangesRepository");
            c0204r = null;
        }
        if (C0204r.c == null) {
            R9 r9 = (R9) c0204r.a;
            r9.getClass();
            Intrinsics.checkNotNullParameter("BIN_RANGES_PREFERENCES_KEY", "key");
            C0204r.c = C0204r.a(r9.a.getString("BIN_RANGES_PREFERENCES_KEY", null));
        } else {
            c0204r.getClass();
        }
        Intrinsics.checkNotNull(C0204r.c);
        return Unit.INSTANCE;
    }
}
