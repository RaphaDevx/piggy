package ch.datatrans.payment;

import java.util.Map;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Ab extends SuspendLambda implements Function1 {
    public int a;
    public final /* synthetic */ Hb b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Function1 d;
    public Object e;
    public Object f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ab(Hb hb, String str, Function1 function1, Continuation continuation) {
        super(1, continuation);
        this.b = hb;
        this.c = str;
        this.d = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new Ab(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return ((Ab) create((Continuation) obj)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        yb ybVar;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Nb nb = this.b.a;
            W8 w8 = nb.k;
            if (w8 != null) {
                ybVar = new yb(w8.c, w8.a, w8.b);
            } else {
                C0131e c0131e = nb.d;
                Intrinsics.checkNotNull(c0131e);
                ybVar = new yb(c0131e.a, 0, c0131e.b);
            }
            String merchantId = ybVar.a;
            boolean z = ybVar.b == 0;
            C7 a = r4.a();
            String transactionId = this.c;
            this.e = SpillingKt.nullOutSpilledVariable(ybVar);
            this.f = SpillingKt.nullOutSpilledVariable(merchantId);
            this.a = 1;
            Map metaInformation = MapsKt.toMutableMap(a.c.b());
            Intrinsics.checkNotNullParameter(metaInformation, "metaInformation");
            Intrinsics.checkNotNullParameter(merchantId, "merchantId");
            Intrinsics.checkNotNullParameter(transactionId, "transactionId");
            obj = a.a(Lf.a(metaInformation, merchantId, MapsKt.mapOf(TuplesKt.to("uppTransactionId", transactionId))), z, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Ya ya = (Ya) obj;
        this.b.a.n = ya.b;
        this.d.invoke(Boxing.boxBoolean(ya.a));
        return Unit.INSTANCE;
    }
}
