package ch.datatrans.payment;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes3.dex */
public final class S3 extends SuspendLambda implements Function2 {
    public Object a;
    public int b;
    public final /* synthetic */ AbstractActivityC0147g4 c;
    public Object d;
    public Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S3(AbstractActivityC0147g4 abstractActivityC0147g4, Continuation continuation) {
        super(2, continuation);
        this.c = abstractActivityC0147g4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new S3(this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new S3(this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00cf, code lost:
    
        if (r1.a(r8) != r0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0065, code lost:
    
        if (r1.a(r8) == r0) goto L31;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.b
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L48
            if (r1 == r4) goto L3b
            if (r1 == r3) goto L2a
            if (r1 != r2) goto L22
            java.lang.Object r0 = r8.e
            ch.datatrans.payment.r r0 = (ch.datatrans.payment.C0204r) r0
            java.lang.Object r0 = r8.d
            ch.datatrans.payment.P5 r0 = (ch.datatrans.payment.P5) r0
            java.lang.Object r8 = r8.a
            ch.datatrans.payment.R9 r8 = (ch.datatrans.payment.R9) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto Ld2
        L22:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2a:
            java.lang.Object r1 = r8.e
            ch.datatrans.payment.r r1 = (ch.datatrans.payment.C0204r) r1
            java.lang.Object r3 = r8.d
            ch.datatrans.payment.P5 r3 = (ch.datatrans.payment.P5) r3
            java.lang.Object r4 = r8.a
            ch.datatrans.payment.R9 r4 = (ch.datatrans.payment.R9) r4
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lb7
        L3b:
            java.lang.Object r1 = r8.d
            ch.datatrans.payment.P5 r1 = (ch.datatrans.payment.P5) r1
            java.lang.Object r5 = r8.a
            ch.datatrans.payment.R9 r5 = (ch.datatrans.payment.R9) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            goto L68
        L48:
            kotlin.ResultKt.throwOnFailure(r9)
            ch.datatrans.payment.R9 r9 = new ch.datatrans.payment.R9
            ch.datatrans.payment.g4 r1 = r8.c
            r9.<init>(r1)
            ch.datatrans.payment.P5 r1 = new ch.datatrans.payment.P5
            r1.<init>(r9)
            r8.a = r9
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.d = r5
            r8.b = r4
            kotlin.Unit r5 = r1.a(r8)
            if (r5 != r0) goto L68
            goto Ld1
        L68:
            ch.datatrans.payment.r r5 = new ch.datatrans.payment.r
            ch.datatrans.payment.E4 r6 = new ch.datatrans.payment.E4
            ch.datatrans.payment.g4 r7 = r8.c
            r6.<init>(r7)
            r5.<init>(r9, r6)
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r8.a = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.d = r6
            r8.e = r5
            r8.b = r3
            r9.getClass()
            java.lang.String r3 = "key"
            java.lang.String r6 = "BIN_RANGES_PREFERENCES_KEY"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r3)
            android.content.SharedPreferences r3 = r9.a
            boolean r3 = r3.contains(r6)
            if (r3 != 0) goto Laf
            java.lang.String r3 = r5.b
            kotlin.Unit r3 = r9.a(r6, r3, r4)
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r3 != r4) goto La3
            goto La5
        La3:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
        La5:
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r3 != r4) goto Lac
            goto Lb1
        Lac:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            goto Lb1
        Laf:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
        Lb1:
            if (r3 != r0) goto Lb4
            goto Ld1
        Lb4:
            r4 = r9
            r3 = r1
            r1 = r5
        Lb7:
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r8.a = r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r8.d = r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.e = r9
            r8.b = r2
            java.lang.Object r8 = r1.a(r8)
            if (r8 != r0) goto Ld2
        Ld1:
            return r0
        Ld2:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.S3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
