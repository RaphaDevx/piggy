package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes3.dex */
public final class E2 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ L3 b;
    public final /* synthetic */ M2 c;
    public final /* synthetic */ SavedPaymentMethod d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E2(L3 l3, M2 m2, SavedPaymentMethod savedPaymentMethod, Continuation continuation) {
        super(2, continuation);
        this.b = l3;
        this.c = m2;
        this.d = savedPaymentMethod;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new E2(this.b, this.c, this.d, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((E2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        if (r5 == r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x009b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0099, code lost:
    
        if (r5 == r0) goto L25;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.a
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1c
            if (r1 == r3) goto L17
            if (r1 != r2) goto Lf
            goto L17
        Lf:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L17:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L9c
        L1c:
            kotlin.ResultKt.throwOnFailure(r6)
            ch.datatrans.payment.L3 r6 = r5.b
            if (r6 == 0) goto L49
            java.lang.String r6 = r6.a
            if (r6 == 0) goto L49
            ch.datatrans.payment.M2 r6 = r5.c
            ch.datatrans.payment.P3 r6 = r6.n
            ch.datatrans.payment.paymentmethods.SavedPaymentMethod r1 = r5.d
            java.lang.String r1 = r1.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String()
            r5.a = r3
            ch.datatrans.payment.R9 r5 = r6.a
            java.lang.String r6 = ch.datatrans.payment.P3.b(r1)
            kotlin.Unit r5 = r5.a(r6, r3)
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L44
            goto L46
        L44:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L46:
            if (r5 != r0) goto L9c
            goto L9b
        L49:
            ch.datatrans.payment.M2 r6 = r5.c
            ch.datatrans.payment.P3 r6 = r6.n
            ch.datatrans.payment.paymentmethods.SavedPaymentMethod r1 = r5.d
            java.lang.String r1 = r1.getCh.datatrans.payment.paymentmethods.SavedPaymentMethod.ALIAS_KEY java.lang.String()
            r5.a = r2
            ch.datatrans.payment.N3 r5 = r6.a(r1)
            java.text.SimpleDateFormat r2 = r6.c
            java.util.Calendar r4 = java.util.Calendar.getInstance()
            java.util.Date r4 = r4.getTime()
            java.lang.String r2 = r2.format(r4)
            java.lang.String r4 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            java.lang.String r4 = "<set-?>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            r5.b = r2
            int r2 = r5.a
            int r2 = r2 + r3
            r5.a = r2
            ch.datatrans.payment.R9 r6 = r6.a
            java.lang.String r1 = ch.datatrans.payment.P3.b(r1)
            com.google.gson.Gson r2 = new com.google.gson.Gson
            r2.<init>()
            java.lang.String r5 = r2.toJson(r5)
            java.lang.String r2 = "toJson(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
            kotlin.Unit r5 = r6.a(r1, r5, r3)
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L97
            goto L99
        L97:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L99:
            if (r5 != r0) goto L9c
        L9b:
            return r0
        L9c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.E2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
