package ch.datatrans.payment;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* renamed from: ch.datatrans.payment.h5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0154h5 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ i5 b;
    public Object c;
    public Object d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0154h5(i5 i5Var, Continuation continuation) {
        super(2, continuation);
        this.b = i5Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0154h5(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0154h5(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b0, code lost:
    
        if (r3.a(r4, r5, r11) == r0) goto L27;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.a
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L2b
            if (r1 == r3) goto L23
            if (r1 != r2) goto L1b
            java.lang.Object r0 = r11.d
            ch.datatrans.payment.ee r0 = (ch.datatrans.payment.C0137ee) r0
            java.lang.Object r0 = r11.c
            ch.datatrans.payment.fe r0 = (ch.datatrans.payment.C0142fe) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            goto Lce
        L1b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L23:
            java.lang.Object r1 = r11.c
            ch.datatrans.payment.fe r1 = (ch.datatrans.payment.C0142fe) r1
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            goto L7c
        L2b:
            kotlin.ResultKt.throwOnFailure(r12)
            ch.datatrans.payment.i5 r12 = r11.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.Nb r12 = r12.e()     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.W8 r12 = r12.k     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            if (r12 == 0) goto L50
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.fe r1 = new ch.datatrans.payment.fe     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            int r5 = r12.a     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r6 = r12.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r7 = r12.c     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r8 = r12.d     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r9 = r12.e     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r10 = r12.f     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            goto L60
        L50:
            ch.datatrans.payment.i5 r12 = r11.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.Nb r12 = r12.e()     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.e r12 = r12.d     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.fe r12 = ch.datatrans.payment.AbstractC0150ge.a(r12)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r1 = r12
        L60:
            ch.datatrans.payment.C7 r12 = ch.datatrans.payment.r4.a()     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.i5 r4 = r11.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.de r4 = r4.i     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.paymentmethods.PaymentMethodType r5 = ch.datatrans.payment.paymentmethods.PaymentMethodType.GOOGLE_PAY     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r11.c = r6     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r11.a = r3     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Object r12 = r12.a(r1, r4, r5, r11)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            if (r12 != r0) goto L7c
            goto Lb2
        L7c:
            ch.datatrans.payment.ee r12 = (ch.datatrans.payment.C0137ee) r12     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.i5 r4 = r11.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r4.j = r12     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Boolean r4 = r12.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            if (r3 == 0) goto L96
            ch.datatrans.payment.i5 r0 = r11.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r12 = r12.a     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r0.a(r12)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            goto Lce
        L96:
            ch.datatrans.payment.i5 r3 = r11.b     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.Hb r3 = r3.k     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.String r4 = r12.a     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            ch.datatrans.payment.paymentmethods.CardExpiryDate r5 = r12.c     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r11.c = r1     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r11.d = r12     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            r11.a = r2     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            java.lang.Object r11 = r3.a(r4, r5, r11)     // Catch: java.lang.Exception -> Lb3 ch.datatrans.payment.D7 -> Lbc javax.net.ssl.SSLException -> Lc2
            if (r11 != r0) goto Lce
        Lb2:
            return r0
        Lb3:
            r12 = move-exception
            ch.datatrans.payment.i5 r11 = r11.b
            int r0 = ch.datatrans.payment.i5.l
            r11.a(r12)
            goto Lce
        Lbc:
            ch.datatrans.payment.i5 r11 = r11.b
            r11.a()
            goto Lce
        Lc2:
            ch.datatrans.payment.i5 r12 = r11.b
            ch.datatrans.payment.Sf r0 = new ch.datatrans.payment.Sf
            ch.datatrans.payment.i5 r11 = r11.b
            r0.<init>(r11)
            r12.c(r0)
        Lce:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0154h5.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
