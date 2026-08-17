package com.scandit.datacapture.barcode.internal.module.ui;

import android.util.Size;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public static final a a = new a();

    public static boolean a(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return view.getHeight() > view.getWidth();
    }

    public static boolean a(Size size) {
        Intrinsics.checkNotNullParameter(size, "size");
        return size.getHeight() > size.getWidth();
    }
}
