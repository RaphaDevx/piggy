package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Size;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class a {
    public final float a;
    public final float b;
    public final int c;
    public Size d;
    public final SharedPreferences e;

    public a(Context context, float f, float f2, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = f;
        this.b = f2;
        this.c = i;
        this.d = new Size(0, 0);
        this.e = context.getSharedPreferences("com.scandit.barcode.count_shutter_button", 0);
    }

    public final float a() {
        return RangesKt.coerceAtMost(this.e.getFloat("barcode-count-floating-shutter-x-location", this.a), this.d.getWidth() - this.c);
    }

    public final float b() {
        return RangesKt.coerceAtMost(this.e.getFloat("barcode-count-floating-shutter-y-location", this.b), this.d.getHeight() - this.c);
    }
}
