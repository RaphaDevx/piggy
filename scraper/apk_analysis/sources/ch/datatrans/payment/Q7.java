package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Q7 {
    public final C7 a;

    public Q7(C7 networking) {
        Intrinsics.checkNotNullParameter(networking, "networking");
        this.a = networking;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(ch.datatrans.payment.api.tokenization.PCIPTokenization r11, ch.datatrans.payment.paymentmethods.Card r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.Q7.a(ch.datatrans.payment.api.tokenization.PCIPTokenization, ch.datatrans.payment.paymentmethods.Card, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(java.lang.String r9, ch.datatrans.payment.paymentmethods.Card r10, kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof ch.datatrans.payment.P7
            if (r0 == 0) goto L13
            r0 = r11
            ch.datatrans.payment.P7 r0 = (ch.datatrans.payment.P7) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            ch.datatrans.payment.P7 r0 = new ch.datatrans.payment.P7
            r0.<init>(r8, r11)
        L18:
            java.lang.Object r11 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r8 = r0.f
            java.lang.String r8 = (java.lang.String) r8
            ch.datatrans.payment.paymentmethods.Card r10 = r0.b
            java.lang.Object r8 = r0.a
            java.lang.String r8 = (java.lang.String) r8
            kotlin.ResultKt.throwOnFailure(r11)
            goto L78
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.String r11 = r10.getCvv()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r4 = "merchantId"
            org.json.JSONObject r2 = r2.put(r4, r9)
            java.lang.String r4 = "cvv"
            org.json.JSONObject r11 = r2.put(r4, r11)
            java.lang.String r11 = r11.toString()
            java.lang.String r2 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            ch.datatrans.payment.C7 r8 = r8.a
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.a = r9
            r0.b = r10
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r0.f = r9
            r0.e = r3
            java.lang.Object r11 = r8.a(r11, r0)
            if (r11 != r1) goto L78
            return r1
        L78:
            java.lang.String r11 = (java.lang.String) r11
            org.json.JSONObject r8 = new org.json.JSONObject
            r8.<init>(r11)
            java.lang.String r1 = r10.getNumber()
            ch.datatrans.payment.paymentmethods.PaymentMethodType r9 = r10.getType()
            ch.datatrans.payment.paymentmethods.CardExpiryDate r7 = r10.getExpiryDate()
            ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess r10 = new ch.datatrans.payment.api.tokenization.PCIPTokenizationSuccess
            java.lang.String r11 = "tokenizationId"
            java.lang.String r8 = ch.datatrans.payment.l7.c(r8, r11)
            if (r8 != 0) goto L97
            r10 = 0
            goto La5
        L97:
            ch.datatrans.payment.api.tokenization.PCIPCardInfo r11 = new ch.datatrans.payment.api.tokenization.PCIPCardInfo
            r5 = 0
            r6 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r0 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            r10.<init>(r8, r9, r11)
        La5:
            if (r10 == 0) goto La8
            return r10
        La8:
            ch.datatrans.payment.exception.TechnicalException r8 = new ch.datatrans.payment.exception.TechnicalException
            r5 = 14
            r6 = 0
            java.lang.String r1 = "Invalid tokenization response returned from backend"
            r2 = 0
            r3 = 0
            r4 = 0
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.Q7.a(java.lang.String, ch.datatrans.payment.paymentmethods.Card, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
