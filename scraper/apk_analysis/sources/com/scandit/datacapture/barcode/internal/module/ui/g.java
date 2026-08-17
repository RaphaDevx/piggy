package com.scandit.datacapture.barcode.internal.module.ui;

import android.content.Context;
import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g extends TouchDelegate {
    public static final int e = PixelExtensionsKt.pxFromDp$default(48, (Context) null, 1, (Object) null);
    public final ViewGroup a;
    public final int b;
    public View c;
    public Rect d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(ViewGroup view) {
        super(new Rect(), view);
        Intrinsics.checkNotNullParameter(view, "view");
        this.a = view;
        this.b = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.d = new Rect();
    }

    public static Rect a(View view) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        int width = view.getWidth();
        int i = e;
        if (width < i) {
            int width2 = (i - view.getWidth()) / 2;
            rect.left -= width2;
            rect.right += width2;
        }
        if (view.getHeight() < i) {
            int height = (i - view.getHeight()) / 2;
            rect.top -= height;
            rect.bottom += height;
        }
        return rect;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
    
        if (r2 != 6) goto L12;
     */
    @Override // android.view.TouchDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r12) {
        /*
            r11 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            float r0 = r12.getX()
            int r0 = (int) r0
            float r1 = r12.getY()
            int r1 = (int) r1
            int r2 = r12.getActionMasked()
            r3 = 0
            r4 = 2
            r5 = 0
            r6 = 1
            if (r2 == 0) goto L4f
            if (r2 == r6) goto L38
            if (r2 == r4) goto L38
            r7 = 3
            if (r2 == r7) goto L2a
            r3 = 5
            if (r2 == r3) goto L38
            r3 = 6
            if (r2 == r3) goto L38
        L26:
            r0 = r6
            r6 = r5
            goto L9d
        L2a:
            android.view.View r0 = r11.c
            if (r0 == 0) goto L30
            r0 = r6
            goto L31
        L30:
            r0 = r5
        L31:
            r11.c = r3
            r10 = r6
            r6 = r0
            r0 = r10
            goto L9d
        L38:
            android.view.View r2 = r11.c
            if (r2 == 0) goto L3e
            r2 = r6
            goto L3f
        L3e:
            r2 = r5
        L3f:
            if (r2 == 0) goto L4c
            android.graphics.Rect r3 = r11.d
            boolean r0 = r3.contains(r0, r1)
            if (r0 != 0) goto L4c
            r6 = r2
            r0 = r5
            goto L9d
        L4c:
            r0 = r6
            r6 = r2
            goto L9d
        L4f:
            android.view.ViewGroup r2 = r11.a
            java.lang.Iterable r2 = com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt.getChildren(r2)
            com.scandit.datacapture.barcode.internal.module.ui.f r7 = new com.scandit.datacapture.barcode.internal.module.ui.f
            r7.<init>()
            java.util.List r2 = kotlin.collections.CollectionsKt.sortedWith(r2, r7)
            java.util.Iterator r2 = r2.iterator()
        L62:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L80
            java.lang.Object r7 = r2.next()
            r8 = r7
            android.view.View r8 = (android.view.View) r8
            android.graphics.Rect r9 = a(r8)
            boolean r9 = r9.contains(r0, r1)
            if (r9 == 0) goto L62
            boolean r8 = r8.isClickable()
            if (r8 == 0) goto L62
            goto L81
        L80:
            r7 = r3
        L81:
            android.view.View r7 = (android.view.View) r7
            if (r7 == 0) goto L9a
            android.graphics.Rect r0 = a(r7)
            r11.c = r7
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>(r0)
            r11.d = r1
            int r0 = r11.b
            int r0 = -r0
            r1.inset(r0, r0)
            r0 = r6
            goto L9d
        L9a:
            r11.c = r3
            goto L26
        L9d:
            android.view.View r1 = r11.c
            if (r6 == 0) goto Lc1
            if (r1 == 0) goto Lc1
            if (r0 == 0) goto Lb5
            int r11 = r1.getWidth()
            int r11 = r11 / r4
            float r11 = (float) r11
            int r0 = r1.getHeight()
            int r0 = r0 / r4
            float r0 = (float) r0
            r12.setLocation(r11, r0)
            goto Lbd
        Lb5:
            int r11 = r11.b
            int r11 = r11 * r4
            float r11 = (float) r11
            float r11 = -r11
            r12.setLocation(r11, r11)
        Lbd:
            boolean r5 = r1.dispatchTouchEvent(r12)
        Lc1:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.ui.g.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
