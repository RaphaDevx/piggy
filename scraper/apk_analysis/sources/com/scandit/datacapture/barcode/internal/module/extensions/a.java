package com.scandit.datacapture.barcode.internal.module.extensions;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public abstract class a {
    public static final int a(int i, int i2) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (RangesKt.coerceIn(i2, 0, 255) << 24);
    }

    public static final String a(int i) {
        String format = String.format("#%06X%02X", Arrays.copyOf(new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK), Integer.valueOf(Color.alpha(i))}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
