package com.scandit.datacapture.core.internal.module.capture;

/* loaded from: classes2.dex */
public abstract class b {
    /* JADX WARN: Removed duplicated region for block: B:13:0x0065 A[LOOP:0: B:11:0x005f->B:13:0x0065, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.ArrayList a(android.content.Context r14) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            android.content.pm.PackageManager r0 = r14.getPackageManager()
            java.lang.String r1 = r14.getPackageName()
            r2 = 134217728(0x8000000, float:3.85186E-34)
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)
            android.content.pm.SigningInfo r0 = r0.signingInfo
            r1 = 0
            if (r0 == 0) goto L3b
            boolean r2 = r0.hasMultipleSigners()
            if (r2 == 0) goto L23
            android.content.pm.Signature[] r0 = r0.getApkContentsSigners()
            goto L3c
        L23:
            android.content.pm.Signature[] r0 = r0.getSigningCertificateHistory()
            java.lang.String r2 = "getSigningCertificateHistory(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            java.lang.Object r0 = kotlin.collections.ArraysKt.firstOrNull(r0)
            android.content.pm.Signature r0 = (android.content.pm.Signature) r0
            if (r0 == 0) goto L3b
            r2 = 1
            android.content.pm.Signature[] r2 = new android.content.pm.Signature[r2]
            r2[r1] = r0
            r0 = r2
            goto L3c
        L3b:
            r0 = 0
        L3c:
            if (r0 != 0) goto L52
            android.content.pm.PackageManager r0 = r14.getPackageManager()
            java.lang.String r14 = r14.getPackageName()
            r2 = 64
            android.content.pm.PackageInfo r14 = r0.getPackageInfo(r14, r2)
            android.content.pm.Signature[] r0 = r14.signatures
            if (r0 != 0) goto L52
            android.content.pm.Signature[] r0 = new android.content.pm.Signature[r1]
        L52:
            java.util.List r14 = kotlin.collections.ArraysKt.filterNotNull(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r14 = r14.iterator()
        L5f:
            boolean r1 = r14.hasNext()
            if (r1 == 0) goto Lb8
            java.lang.Object r1 = r14.next()
            android.content.pm.Signature r1 = (android.content.pm.Signature) r1
            java.lang.String r2 = "SHA-1"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)
            byte[] r3 = r1.toByteArray()
            byte[] r4 = r2.digest(r3)
            java.lang.String r2 = "digest(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
            com.scandit.datacapture.core.internal.module.capture.a r3 = com.scandit.datacapture.core.internal.module.capture.a.a
            r11 = 30
            r12 = 0
            java.lang.String r5 = ""
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = r3
            java.lang.String r4 = kotlin.collections.ArraysKt.joinToString$default(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            java.lang.String r5 = "SHA-256"
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r5)
            byte[] r1 = r1.toByteArray()
            byte[] r5 = r5.digest(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
            r12 = 30
            r13 = 0
            java.lang.String r6 = ""
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = r3
            java.lang.String r1 = kotlin.collections.ArraysKt.joinToString$default(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            java.lang.String[] r1 = new java.lang.String[]{r4, r1}
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            kotlin.collections.CollectionsKt.addAll(r0, r1)
            goto L5f
        Lb8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.capture.b.a(android.content.Context):java.util.ArrayList");
    }
}
