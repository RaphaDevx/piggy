package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import android.view.MotionEvent;
import com.scandit.datacapture.barcode.internal.module.spark.ui.EnumC0437a;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes2.dex */
public final /* synthetic */ class m extends FunctionReferenceImpl implements Function0 {
    public m(Object obj) {
        super(0, obj, n.class, "onLongPressStarted", "onLongPressStarted()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        n nVar = (n) this.receiver;
        MotionEvent motionEvent = nVar.n;
        if (motionEvent != null) {
            f a = nVar.a();
            a.getClass();
            float width = a.getWidth() / 2;
            float x = motionEvent.getX() - width;
            float y = motionEvent.getY() - width;
            if ((y * y) + (x * x) <= r2 * r2) {
                nVar.r.invoke(EnumC0437a.i);
                nVar.g = true;
            }
        }
        nVar.r.invoke(EnumC0437a.g);
        nVar.f = true;
        return Unit.INSTANCE;
    }
}
