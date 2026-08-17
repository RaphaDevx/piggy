package com.scandit.datacapture.barcode.internal.module.count.ui.popover;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.C0320x;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.C0321y;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes2.dex */
public final /* synthetic */ class e extends FunctionReferenceImpl implements Function0 {
    public e(Object obj) {
        super(0, obj, g.class, "onCancelButtonTapped", "onCancelButtonTapped()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        TrackedBarcode trackedBarcode;
        C0321y c0321y = (C0321y) ((C0320x) ((g) this.receiver)).a.get();
        if (c0321y != null && (trackedBarcode = c0321y.e) != null) {
            c0321y.a.didCancelNotInListBarcode(trackedBarcode);
        }
        return Unit.INSTANCE;
    }
}
