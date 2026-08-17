package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.graphics.Rect;
import android.view.MotionEvent;
import com.scandit.datacapture.barcode.internal.module.spark.ui.EnumC0437a;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* loaded from: classes2.dex */
public final /* synthetic */ class v extends FunctionReferenceImpl implements Function0 {
    public v(Object obj) {
        super(0, obj, w.class, "onLongPressStarted", "onLongPressStarted()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        w wVar = (w) this.receiver;
        wVar.getClass();
        Rect rect = new Rect();
        wVar.b.getGlobalVisibleRect(rect);
        MotionEvent motionEvent = wVar.j;
        if (motionEvent != null && rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
            wVar.c.invoke(EnumC0437a.g);
            wVar.m = true;
        }
        return Unit.INSTANCE;
    }
}
