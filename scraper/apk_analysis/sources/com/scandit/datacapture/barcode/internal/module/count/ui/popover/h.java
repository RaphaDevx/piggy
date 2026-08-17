package com.scandit.datacapture.barcode.internal.module.count.ui.popover;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountNotInListActionSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.C0320x;
import com.scandit.datacapture.barcode.internal.module.ui.popover.C0491m;
import com.scandit.datacapture.barcode.internal.module.ui.popover.InterfaceC0492n;
import com.scandit.datacapture.barcode.internal.module.ui.popover.InterfaceC0493o;
import com.scandit.datacapture.barcode.internal.module.ui.popover.K;
import com.scandit.datacapture.barcode.internal.module.ui.popover.LinearPopoverButtonViewSettings;
import com.scandit.datacapture.barcode.internal.module.ui.popover.P;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h {
    public static final Lazy f = LazyKt.lazy(a.a);
    public final Context a;
    public final g b;
    public final InterfaceC0492n c;
    public final InterfaceC0493o d;
    public P e;

    public h(Context context, FrameLayout container, C0320x listener) {
        C0491m popover = new C0491m(context);
        Map buttonSettings = f.a(context, new BarcodeCountNotInListActionSettings(), NativeBarcodeCountBasicOverlayColorScheme.DEFAULT, listener);
        K popoverPositioner = new K();
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(popover, "popover");
        Intrinsics.checkNotNullParameter(buttonSettings, "buttonSettings");
        Intrinsics.checkNotNullParameter(popoverPositioner, "popoverPositioner");
        this.a = context;
        this.b = listener;
        this.c = popover;
        this.d = popoverPositioner;
        a(buttonSettings);
        Intrinsics.checkNotNullParameter(container, "container");
        container.addView(popover, new FrameLayout.LayoutParams(-2, -2));
    }

    public final void a(Map map) {
        C0491m c0491m = (C0491m) this.c;
        Iterator it = c0491m.a.values().iterator();
        while (it.hasNext()) {
            c0491m.removeView((View) it.next());
        }
        c0491m.a.clear();
        for (Map.Entry entry : map.entrySet()) {
            ((C0491m) this.c).a((LinearPopoverButtonViewSettings) entry.getKey(), (Function0) entry.getValue());
        }
        this.e = null;
    }
}
