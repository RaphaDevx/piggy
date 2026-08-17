package com.scandit.datacapture.core.internal.module.source.api2;

import android.util.Rational;
import android.util.Size;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.z, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0582z implements Comparator {
    public final /* synthetic */ float a;

    public C0582z(float f) {
        this.a = f;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Size size = (Size) obj;
        Size size2 = (Size) obj2;
        return ComparisonsKt.compareValues(Float.valueOf(Math.abs(this.a - new Rational(size.getWidth(), size.getHeight()).floatValue())), Float.valueOf(Math.abs(this.a - new Rational(size2.getWidth(), size2.getHeight()).floatValue())));
    }
}
