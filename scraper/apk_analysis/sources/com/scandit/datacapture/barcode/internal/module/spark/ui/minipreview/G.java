package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class G {
    public static final boolean a(ViewGroup viewGroup, View child) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        return viewGroup.indexOfChild(child) > -1;
    }
}
