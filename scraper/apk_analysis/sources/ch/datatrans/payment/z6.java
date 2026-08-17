package ch.datatrans.payment;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes3.dex */
public final class z6 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ String b;
    public final /* synthetic */ A6 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z6(String str, A6 a6, Continuation continuation) {
        super(2, continuation);
        this.b = str;
        this.c = a6;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new z6(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new z6(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
    
        if (r2.a("language", r6, false) == r0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
    
        if (r6.a("language", false) == r0) goto L17;
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
            if (r1 == 0) goto L1b
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
            goto L3f
        L1b:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = r5.b
            r1 = 0
            java.lang.String r4 = "language"
            if (r6 == 0) goto L32
            ch.datatrans.payment.A6 r2 = r5.c
            ch.datatrans.payment.R9 r2 = r2.a
            r5.a = r3
            kotlin.Unit r5 = r2.a(r4, r6, r1)
            if (r5 != r0) goto L3f
            goto L3e
        L32:
            ch.datatrans.payment.A6 r6 = r5.c
            ch.datatrans.payment.R9 r6 = r6.a
            r5.a = r2
            kotlin.Unit r5 = r6.a(r4, r1)
            if (r5 != r0) goto L3f
        L3e:
            return r0
        L3f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.z6.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
