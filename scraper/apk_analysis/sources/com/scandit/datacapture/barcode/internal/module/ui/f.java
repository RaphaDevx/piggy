package com.scandit.datacapture.barcode.internal.module.ui;

import android.view.View;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

/* loaded from: classes2.dex */
public final class f implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt.compareValues(Float.valueOf(((View) obj2).getZ()), Float.valueOf(((View) obj).getZ()));
    }
}
