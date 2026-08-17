package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import com.scandit.datacapture.barcode.internal.module.spark.ui.EnumC0437a;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function1 {
    public final /* synthetic */ f a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(f fVar) {
        super(1);
        this.a = fVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        i iVar;
        i iVar2;
        i iVar3;
        EnumC0437a gesture = (EnumC0437a) obj;
        Intrinsics.checkNotNullParameter(gesture, "gesture");
        int ordinal = gesture.ordinal();
        if (ordinal == 0) {
            f fVar = this.a;
            if ((fVar.c instanceof b) && (iVar = fVar.b) != null) {
                ((SparkScanViewPresenter) iVar).A();
            }
        } else if (ordinal != 1) {
            switch (ordinal) {
                case 6:
                    i iVar4 = this.a.b;
                    if (iVar4 != null) {
                        ((SparkScanViewPresenter) iVar4).E();
                        break;
                    }
                    break;
                case 7:
                    i iVar5 = this.a.b;
                    if (iVar5 != null) {
                        ((SparkScanViewPresenter) iVar5).D();
                        break;
                    }
                    break;
                case 8:
                    i iVar6 = this.a.b;
                    if (iVar6 != null) {
                        ((SparkScanViewPresenter) iVar6).a(true);
                        break;
                    }
                    break;
                case 9:
                    i iVar7 = this.a.b;
                    if (iVar7 != null) {
                        ((SparkScanViewPresenter) iVar7).a(false);
                        break;
                    }
                    break;
                case 10:
                    f fVar2 = this.a;
                    if ((fVar2.c instanceof b) && (iVar3 = fVar2.b) != null) {
                        ((SparkScanViewPresenter) iVar3).B();
                        break;
                    }
                    break;
            }
        } else {
            f fVar3 = this.a;
            if ((fVar3.c instanceof a) && (iVar2 = fVar3.b) != null) {
                ((SparkScanViewPresenter) iVar2).A();
            }
        }
        return Unit.INSTANCE;
    }
}
