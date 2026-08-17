package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* renamed from: ch.datatrans.payment.w4, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0246w4 implements Y9 {
    public final String a;
    public final Be b;

    public C0246w4(String errorUrl, Be listener) {
        Intrinsics.checkNotNullParameter(errorUrl, "errorUrl");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = errorUrl;
        this.b = listener;
    }

    @Override // ch.datatrans.payment.Y9
    public final boolean a(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.startsWith(url, this.a, true);
    }

    @Override // ch.datatrans.payment.Y9
    public final boolean b(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x006b  */
    @Override // ch.datatrans.payment.Y9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "url"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.util.Map r10 = ch.datatrans.payment.vd.a(r10)
            java.lang.String r0 = "errorCode"
            java.lang.Object r0 = r10.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L27
            int r1 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L1c
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.NumberFormatException -> L1c
            goto L28
        L1c:
            java.lang.String r1 = "Unable to parse error code: "
            java.lang.String r0 = r1.concat(r0)
            java.lang.String r1 = "DTPL"
            android.util.Log.e(r1, r0)
        L27:
            r0 = 0
        L28:
            if (r0 == 0) goto L2f
            int r0 = r0.intValue()
            goto L30
        L2f:
            r0 = -1
        L30:
            r4 = r0
            java.lang.String r0 = "errorMessage"
            java.lang.Object r0 = r10.get(r0)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r0 = "errorDetail"
            java.lang.Object r0 = r10.get(r0)
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r0 = "uppTransactionId"
            java.lang.Object r0 = r10.get(r0)
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r0 = "acqErrorCode"
            java.lang.Object r0 = r10.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "acqAuthorizationCode"
            java.lang.Object r1 = r10.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r6 = "threeDScardholderInfo"
            java.lang.Object r10 = r10.get(r6)
            java.lang.String r10 = (java.lang.String) r10
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap
            r7.<init>()
            if (r0 == 0) goto L73
            java.lang.String r8 = "byjunoAcqErrorCode"
            java.lang.Object r0 = r7.put(r8, r0)
            java.lang.String r0 = (java.lang.String) r0
        L73:
            if (r1 == 0) goto L7d
            java.lang.String r0 = "byjunoAcqAuthorizationCode"
            java.lang.Object r0 = r7.put(r0, r1)
            java.lang.String r0 = (java.lang.String) r0
        L7d:
            if (r10 == 0) goto L8d
            java.lang.CharSequence r10 = kotlin.text.StringsKt.trim(r10)
            java.lang.String r10 = r10.toString()
            java.lang.Object r10 = r7.put(r6, r10)
            java.lang.String r10 = (java.lang.String) r10
        L8d:
            ch.datatrans.payment.exception.BackendException r10 = new ch.datatrans.payment.exception.BackendException
            r1 = r10
            r6 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            ch.datatrans.payment.Be r9 = r9.b
            r9.a(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0246w4.c(java.lang.String):void");
    }
}
