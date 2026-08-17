package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import java.util.Map;
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
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h hVar;
        int intValue = ((Number) obj).intValue();
        for (Map.Entry entry : this.a.m.entrySet()) {
            int intValue2 = ((Number) entry.getKey()).intValue();
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) entry.getValue();
            if (intValue2 != intValue && (hVar = eVar.h) != null) {
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.b onFinished = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.b.a;
                Intrinsics.checkNotNullParameter(onFinished, "onFinished");
                if (hVar.o == com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.b) {
                    hVar.a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.a, onFinished);
                } else {
                    onFinished.invoke();
                }
            }
        }
        return Unit.INSTANCE;
    }
}
