package ch.datatrans.payment;

import android.content.Context;
import ch.datatrans.payment.paymentmethods.GooglePayConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class H4 implements b9 {
    public final Context a;
    public final boolean b;
    public final PaymentMethodType c;
    public final C0173l5 d;

    public H4(Context context, GooglePayConfig config, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.a = context;
        this.b = z;
        this.c = PaymentMethodType.GOOGLE_PAY;
        this.d = new C0173l5(config);
    }

    @Override // ch.datatrans.payment.b9
    public final PaymentMethodType a() {
        return this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // ch.datatrans.payment.b9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.Continuation r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof ch.datatrans.payment.F4
            if (r0 == 0) goto L13
            r0 = r5
            ch.datatrans.payment.F4 r0 = (ch.datatrans.payment.F4) r0
            int r1 = r0.c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.c = r1
            goto L18
        L13:
            ch.datatrans.payment.F4 r0 = new ch.datatrans.payment.F4
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L5b
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            ch.datatrans.payment.paymentmethods.PaymentMethodType r5 = r4.c
            kotlin.jvm.functions.Function0 r5 = r5.isModuleAvailable$lib_release()
            if (r5 == 0) goto L66
            java.lang.Object r5 = r5.invoke()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != r3) goto L66
            r0.c = r3
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            ch.datatrans.payment.G4 r2 = new ch.datatrans.payment.G4
            r3 = 0
            r2.<init>(r4, r3)
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r2, r0)
            if (r5 != r1) goto L5b
            return r1
        L5b:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r4 = r5.booleanValue()
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        L66:
            r4 = 0
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.H4.a(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
