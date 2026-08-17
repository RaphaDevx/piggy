package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.content.Context;
import android.graphics.PointF;
import android.view.ViewConfiguration;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0438b {
    public final int a;

    public C0438b(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.a = scaledTouchSlop * scaledTouchSlop;
    }

    public final EnumC0437a a(PointF start, PointF end) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        float f = end.x - start.x;
        float f2 = end.y - start.y;
        if ((f2 * f2) + (f * f) < this.a) {
            return EnumC0437a.b;
        }
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        return f >= 0.0f ? f2 >= 0.0f ? abs >= abs2 ? EnumC0437a.d : EnumC0437a.e : abs >= abs2 ? EnumC0437a.d : EnumC0437a.c : f2 >= 0.0f ? abs >= abs2 ? EnumC0437a.f : EnumC0437a.e : abs >= abs2 ? EnumC0437a.f : EnumC0437a.c;
    }
}
