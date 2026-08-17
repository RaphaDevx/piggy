package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip;

import com.scandit.datacapture.core.common.geometry.Anchor;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class c {
    public static final /* synthetic */ void a(a aVar, Anchor anchor) {
        float f;
        Intrinsics.checkNotNullParameter(aVar, "<this>");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        int i = b.a[anchor.ordinal()];
        if (i == 1) {
            f = 180.0f;
        } else if (i == 2) {
            f = 0.0f;
        } else if (i == 3) {
            f = 90.0f;
        } else {
            if (i != 4) {
                throw new IllegalArgumentException("Unsupported anchor: " + anchor);
            }
            f = 270.0f;
        }
        aVar.setRotation(f);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void b(com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a r5, com.scandit.datacapture.core.common.geometry.Anchor r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "anchor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            int[] r0 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.b.a
            int r1 = r6.ordinal()
            r0 = r0[r1]
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L3d
            r3 = 2
            if (r0 == r3) goto L39
            r3 = 3
            if (r0 == r3) goto L36
            r3 = 4
            if (r0 != r3) goto L22
            r6 = r1
            r0 = r2
            goto L40
        L22:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported anchor: "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            throw r5
        L36:
            r6 = r2
            r0 = r6
            goto L41
        L39:
            r0 = r1
            r6 = r2
            r1 = r6
            goto L41
        L3d:
            r6 = r2
            r0 = r6
            r2 = r1
        L40:
            r1 = r0
        L41:
            android.view.ViewGroup$LayoutParams r3 = r5.getLayoutParams()
            boolean r4 = r3 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r4 == 0) goto L4c
            android.view.ViewGroup$MarginLayoutParams r3 = (android.view.ViewGroup.MarginLayoutParams) r3
            goto L4d
        L4c:
            r3 = 0
        L4d:
            if (r3 == 0) goto L55
            r3.setMargins(r1, r2, r6, r0)
            r5.setLayoutParams(r3)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.c.b(com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a, com.scandit.datacapture.core.common.geometry.Anchor):void");
    }
}
