package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes3.dex */
public final class V0 {
    public final CoroutineScope a;

    public V0(CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.a = scope;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(ch.datatrans.payment.V0 r7, ch.datatrans.payment.C0219t0 r8, ch.datatrans.payment.paymentmethods.PaymentMethodType r9, java.lang.String r10, java.lang.String r11, ch.datatrans.payment.Q3 r12, kotlin.jvm.functions.Function2 r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            r7.getClass()
            boolean r0 = r14 instanceof ch.datatrans.payment.U0
            if (r0 == 0) goto L16
            r0 = r14
            ch.datatrans.payment.U0 r0 = (ch.datatrans.payment.U0) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.e = r1
            goto L1b
        L16:
            ch.datatrans.payment.U0 r0 = new ch.datatrans.payment.U0
            r0.<init>(r7, r14)
        L1b:
            r6 = r0
            java.lang.Object r7 = r6.c
            java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.e
            r1 = 1
            if (r0 == 0) goto L49
            if (r0 != r1) goto L41
            kotlin.jvm.functions.Function2 r13 = r6.i
            ch.datatrans.payment.Q3 r12 = r6.h
            java.lang.Object r8 = r6.g
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r6.f
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r6.b
            ch.datatrans.payment.paymentmethods.PaymentMethodType r8 = (ch.datatrans.payment.paymentmethods.PaymentMethodType) r8
            java.lang.Object r8 = r6.a
            ch.datatrans.payment.t0 r8 = (ch.datatrans.payment.C0219t0) r8
            kotlin.ResultKt.throwOnFailure(r7)
            goto L85
        L41:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L49:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.String r7 = r8.a
            if (r7 == 0) goto L8a
            boolean r7 = r8.a(r9)
            if (r7 == 0) goto L8a
            ch.datatrans.payment.C7 r7 = ch.datatrans.payment.r4.a()
            java.lang.String r2 = r8.a
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r6.a = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r6.b = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r6.f = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r6.g = r8
            r6.h = r12
            r6.i = r13
            r6.e = r1
            r1 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            java.lang.Object r7 = r1.a(r2, r3, r4, r5, r6)
            if (r7 != r14) goto L85
            return r14
        L85:
            ch.datatrans.payment.X0 r7 = (ch.datatrans.payment.X0) r7
            r13.invoke(r7, r12)
        L8a:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.V0.a(ch.datatrans.payment.V0, ch.datatrans.payment.t0, ch.datatrans.payment.paymentmethods.PaymentMethodType, java.lang.String, java.lang.String, ch.datatrans.payment.Q3, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
