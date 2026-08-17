package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class LinearPopoverButtonViewSettings {
    private static final Typeface k = Typeface.DEFAULT;
    private static final Lazy l = LazyKt.lazy(C0486h.a);
    private final int a;
    private final Drawable b;
    private final String c;
    private final String d;
    private final float e;
    private final int f;
    private final Typeface g;
    private final Integer h;
    private final boolean i;
    private final boolean j;

    public LinearPopoverButtonViewSettings(int i, Drawable drawable, String title, String str, float f, int i2, Typeface typeface, boolean z, boolean z2, int i3) {
        f = (i3 & 16) != 0 ? 10.0f : f;
        i2 = (i3 & 32) != 0 ? ((Number) l.getValue()).intValue() : i2;
        if ((i3 & 64) != 0) {
            typeface = k;
            Intrinsics.checkNotNullExpressionValue(typeface, "DEFAULT_TYPE_FACE");
        }
        z = (i3 & 256) != 0 ? true : z;
        z2 = (i3 & 512) != 0 ? true : z2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        this.a = i;
        this.b = drawable;
        this.c = title;
        this.d = str;
        this.e = f;
        this.f = i2;
        this.g = typeface;
        this.h = null;
        this.i = z;
        this.j = z2;
    }

    public final Integer a() {
        return this.h;
    }

    public final String b() {
        return this.d;
    }

    public final boolean c() {
        return this.i;
    }

    public final Drawable d() {
        return this.b;
    }

    public final int e() {
        return this.a;
    }

    public final int f() {
        return this.f;
    }

    public final float g() {
        return this.e;
    }

    public final String h() {
        return this.c;
    }

    public final Typeface i() {
        return this.g;
    }

    public final boolean j() {
        return this.j;
    }
}
