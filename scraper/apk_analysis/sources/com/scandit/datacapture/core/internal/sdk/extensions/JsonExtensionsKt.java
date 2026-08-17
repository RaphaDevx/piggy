package com.scandit.datacapture.core.internal.sdk.extensions;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"bitmapByKey", "Landroid/graphics/Bitmap;", "Lcom/scandit/datacapture/core/json/JsonValue;", "key", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class JsonExtensionsKt {
    /* JADX WARN: Removed duplicated region for block: B:25:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final android.graphics.Bitmap bitmapByKey(com.scandit.datacapture.core.json.JsonValue r4, java.lang.String r5) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)
            r1 = 0
            if (r4 != 0) goto Lb
            return r1
        Lb:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L16
            java.lang.String r2 = r4.getByKeyAsNullableString$scandit_capture_core(r5, r0)     // Catch: java.lang.Throwable -> L16
            java.lang.Object r2 = kotlin.Result.m3945constructorimpl(r2)     // Catch: java.lang.Throwable -> L16
            goto L21
        L16:
            r2 = move-exception
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r2 = kotlin.Result.m3945constructorimpl(r2)
        L21:
            boolean r3 = kotlin.Result.m3952isSuccessimpl(r2)
            if (r3 == 0) goto L32
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L32
            android.graphics.Bitmap r2 = com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt.bitmapFromBase64(r2)
            if (r2 == 0) goto L32
            return r2
        L32:
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L6e
            com.scandit.datacapture.core.json.JsonValue r4 = r4.getByKeyAsObject(r5, r1)     // Catch: java.lang.Throwable -> L6e
            if (r4 == 0) goto L68
            java.lang.String r5 = "type"
            java.lang.String r5 = r4.getByKeyAsNullableString$scandit_capture_core(r5, r1)     // Catch: java.lang.Throwable -> L6e
            if (r5 == 0) goto L68
            java.lang.String r2 = "base64"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r2)     // Catch: java.lang.Throwable -> L6e
            if (r2 == 0) goto L55
            java.lang.String r5 = "data"
            java.lang.String r4 = r4.getByKeyAsString(r5, r0)     // Catch: java.lang.Throwable -> L6e
            android.graphics.Bitmap r4 = com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt.bitmapFromBase64(r4)     // Catch: java.lang.Throwable -> L6e
            goto L69
        L55:
            java.lang.String r2 = "resource"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r2)     // Catch: java.lang.Throwable -> L6e
            if (r5 == 0) goto L68
            java.lang.String r5 = "name"
            java.lang.String r4 = r4.getByKeyAsString(r5, r0)     // Catch: java.lang.Throwable -> L6e
            android.graphics.Bitmap r4 = com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt.bitmapFromResourceName(r4)     // Catch: java.lang.Throwable -> L6e
            goto L69
        L68:
            r4 = r1
        L69:
            java.lang.Object r4 = kotlin.Result.m3945constructorimpl(r4)     // Catch: java.lang.Throwable -> L6e
            goto L79
        L6e:
            r4 = move-exception
            kotlin.Result$Companion r5 = kotlin.Result.INSTANCE
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m3945constructorimpl(r4)
        L79:
            boolean r5 = kotlin.Result.m3952isSuccessimpl(r4)
            if (r5 == 0) goto L82
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            return r4
        L82:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.extensions.JsonExtensionsKt.bitmapByKey(com.scandit.datacapture.core.json.JsonValue, java.lang.String):android.graphics.Bitmap");
    }
}
