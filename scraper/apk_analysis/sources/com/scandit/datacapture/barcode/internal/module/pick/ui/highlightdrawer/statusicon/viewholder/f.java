package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder;

import android.view.MotionEvent;
import android.view.View;
import com.scandit.datacapture.core.common.geometry.Point;
import kotlin.jvm.functions.Function1;

/* loaded from: classes2.dex */
public final class f implements View.OnTouchListener {
    public long a;
    public final /* synthetic */ h b;

    public f(h hVar) {
        this.b = hVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (view != null && motionEvent != null) {
            int action = motionEvent.getAction();
            if (action == 0) {
                if (((Boolean) this.b.m.invoke(new Point(motionEvent.getX() + view.getX(), motionEvent.getY() + view.getY()), Integer.valueOf(this.b.k.a))).booleanValue()) {
                    this.a = System.currentTimeMillis();
                    return true;
                }
            } else if (action == 1 && System.currentTimeMillis() - this.a < 200) {
                view.performClick();
                h hVar = this.b;
                Function1 function1 = hVar.n;
                if (function1 != null) {
                    function1.invoke(hVar.b);
                }
                return true;
            }
        }
        return false;
    }
}
