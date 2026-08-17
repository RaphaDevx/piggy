package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon;

import android.view.View;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ e a;
    public final /* synthetic */ TrackedObject b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(e eVar, TrackedObject trackedObject) {
        super(1);
        this.a = eVar;
        this.b = trackedObject;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        View statusIconView = (View) obj;
        Intrinsics.checkNotNullParameter(statusIconView, "statusIconView");
        if (e.a(this.a, statusIconView)) {
            e eVar = this.a;
            TrackedObject trackedObject = this.b;
            if (!eVar.f) {
                eVar.f = true;
                Function1 function1 = eVar.i;
                if (function1 != null) {
                    function1.invoke(Integer.valueOf(trackedObject.getIdentifier()));
                }
                h hVar = eVar.h;
                if (hVar != null) {
                    int ordinal = hVar.o.ordinal();
                    if (ordinal == 0) {
                        c onFinished = new c(eVar);
                        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
                        if (hVar.o == com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.a) {
                            CharSequence text = hVar.h.getText();
                            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
                            if (!StringsKt.isBlank(text)) {
                                hVar.a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.b, onFinished);
                            }
                        }
                        onFinished.invoke();
                    } else if (ordinal == 1) {
                        d onFinished2 = new d(eVar);
                        Intrinsics.checkNotNullParameter(onFinished2, "onFinished");
                        if (hVar.o == com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.b) {
                            hVar.a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.a, onFinished2);
                        } else {
                            onFinished2.invoke();
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
