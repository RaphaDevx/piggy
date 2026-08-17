package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import com.scandit.datacapture.barcode.internal.module.spark.ui.EnumC0437a;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class s extends Lambda implements Function1 {
    public final /* synthetic */ t a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(t tVar) {
        super(1);
        this.a = tVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        x xVar;
        EnumC0437a gesture = (EnumC0437a) obj;
        Intrinsics.checkNotNullParameter(gesture, "gesture");
        int ordinal = gesture.ordinal();
        if (ordinal == 1) {
            x xVar2 = this.a.b;
            if (xVar2 != null) {
                ((SparkScanViewPresenter) xVar2).s();
            }
        } else if (ordinal == 6) {
            x xVar3 = this.a.b;
            if (xVar3 != null) {
                ((SparkScanViewPresenter) xVar3).i(true);
            }
        } else if (ordinal == 7 && (xVar = this.a.b) != null) {
            ((SparkScanViewPresenter) xVar).i(false);
        }
        return Unit.INSTANCE;
    }
}
