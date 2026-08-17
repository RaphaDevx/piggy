package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k1 extends Lambda implements Function1 {
    public final /* synthetic */ n1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k1(n1 n1Var) {
        super(1);
        this.a = n1Var;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            return PointWithUnitUtilsKt.PointWithUnit(this.a.b.i() + AbstractC0341b.e + AbstractC0341b.g, this.a.b.g() + AbstractC0341b.f, MeasureUnit.PIXEL);
        }
        int i = this.a.b.i();
        int i2 = AbstractC0341b.f;
        return PointWithUnitUtilsKt.PointWithUnit(i + i2, this.a.b.g() + i2, MeasureUnit.PIXEL);
    }
}
