package com.scandit.datacapture.core.internal.module.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.NativeHintStyle;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class u extends w {
    public final String a;
    public final NativeHintStyle b;

    public u(String hint, NativeHintStyle style) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(style, "style");
        this.a = hint;
        this.b = style;
    }
}
