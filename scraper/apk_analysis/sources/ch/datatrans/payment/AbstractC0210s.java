package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* renamed from: ch.datatrans.payment.s, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0210s {
    /* JADX WARN: Code restructure failed: missing block: B:13:0x006f, code lost:
    
        if (r9 <= r11) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0076, code lost:
    
        if (java.lang.Integer.parseInt(r0) == r11) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String a(ch.datatrans.payment.models.BackendErrorRule r10, ch.datatrans.payment.exception.BackendException r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "exception"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = r11.getErrorMessage()
            java.lang.String r1 = r10.getMessage()
            boolean r0 = a(r0, r1)
            r1 = 0
            if (r0 == 0) goto L7d
            java.lang.String r0 = r11.getDetail()
            java.lang.String r2 = r10.getDetail()
            boolean r0 = a(r0, r2)
            if (r0 == 0) goto L7d
            int r11 = r11.getErrorCode()
            java.lang.String r0 = r10.getCode()
            if (r0 == 0) goto L78
            int r2 = r0.length()
            if (r2 != 0) goto L38
            goto L78
        L38:
            r2 = 2
            java.lang.String r8 = "..."
            r9 = 0
            boolean r2 = kotlin.text.StringsKt.contains$default(r0, r8, r9, r2, r1)
            if (r2 == 0) goto L72
            java.lang.String[] r3 = new java.lang.String[]{r8}
            r6 = 6
            r7 = 0
            r4 = 0
            r5 = 0
            r2 = r0
            java.util.List r2 = kotlin.text.StringsKt.split$default(r2, r3, r4, r5, r6, r7)
            java.lang.Object r2 = r2.get(r9)
            java.lang.String r2 = (java.lang.String) r2
            int r9 = java.lang.Integer.parseInt(r2)
            java.lang.String[] r3 = new java.lang.String[]{r8}
            r2 = r0
            java.util.List r0 = kotlin.text.StringsKt.split$default(r2, r3, r4, r5, r6, r7)
            r2 = 1
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = java.lang.Integer.parseInt(r0)
            if (r11 > r0) goto L7d
            if (r9 > r11) goto L7d
            goto L78
        L72:
            int r0 = java.lang.Integer.parseInt(r0)
            if (r0 != r11) goto L7d
        L78:
            java.lang.String r10 = r10.getName()
            return r10
        L7d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.AbstractC0210s.a(ch.datatrans.payment.models.BackendErrorRule, ch.datatrans.payment.exception.BackendException):java.lang.String");
    }

    public static final boolean a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return true;
        }
        if (StringsKt.first(str2) == '\"' && StringsKt.last(str2) == '\"') {
            return str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) StringsKt.trim((CharSequence) StringsKt.replace$default(str2, '\"', ' ', false, 4, (Object) null)).toString(), false, 2, (Object) null);
        }
        return Intrinsics.areEqual(str, str2);
    }
}
