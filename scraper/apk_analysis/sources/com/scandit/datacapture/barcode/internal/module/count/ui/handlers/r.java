package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.W;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class r implements BarcodeCountBrushHandler {
    public final InterfaceC0329a a;
    public final ConcurrentHashMap b;
    public final ConcurrentHashMap c;
    public final ConcurrentHashMap d;
    public final ConcurrentHashMap e;
    public final ConcurrentHashMap f;

    public r(BarcodeCountBasicOverlay delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.a = delegate;
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
        this.e = new ConcurrentHashMap();
        this.f = new ConcurrentHashMap();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler
    public final void a(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        ConcurrentHashMap concurrentHashMap = this.b;
        Integer valueOf = Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture());
        if (brush == null) {
            brush = Brush.INSTANCE.transparent();
        }
        concurrentHashMap.put(valueOf, brush);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler
    public final void b(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        ConcurrentHashMap concurrentHashMap = this.e;
        Integer valueOf = Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture());
        if (brush == null) {
            brush = Brush.INSTANCE.transparent();
        }
        concurrentHashMap.put(valueOf, brush);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler
    public final void c(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        ConcurrentHashMap concurrentHashMap = this.f;
        Integer valueOf = Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture());
        if (brush == null) {
            brush = Brush.INSTANCE.transparent();
        }
        concurrentHashMap.put(valueOf, brush);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler
    public final void d(TrackedBarcode barcode, Brush brush) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        ConcurrentHashMap concurrentHashMap = this.d;
        Integer valueOf = Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture());
        if (brush == null) {
            brush = Brush.INSTANCE.transparent();
        }
        concurrentHashMap.put(valueOf, brush);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler
    public final Brush a(TrackedBarcode barcode, W status) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(status, "status");
        return ((Boolean) a(status, barcode, new n(this, barcode), new o(this, barcode), p.a, new q(status, this, barcode))).booleanValue() ? (Brush) a(status, barcode, new C0338j(this, barcode), new k(this, barcode), l.a, new m(status, this, barcode)) : this.a.isListenerSet() ? (Brush) a(status, barcode, new C0334f(this), new C0335g(this), new C0336h(this), new C0337i(status, this)) : (Brush) a(status, Unit.INSTANCE, new C0330b(this), new C0331c(this), new C0332d(this), new C0333e(status, this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler
    public final Brush a() {
        W status = W.g;
        Intrinsics.checkNotNullParameter(status, "status");
        return (Brush) a(status, Unit.INSTANCE, new C0330b(this), new C0331c(this), new C0332d(this), new C0333e(status, this));
    }

    public static Object a(W w, Object obj, Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
        int ordinal = w.ordinal();
        if (ordinal == 0) {
            return function1.invoke(obj);
        }
        if (ordinal == 1 || ordinal == 2 || ordinal == 3) {
            return function14.invoke(obj);
        }
        if (ordinal == 4) {
            return function12.invoke(obj);
        }
        if (ordinal == 5) {
            return function13.invoke(obj);
        }
        throw new NoWhenBranchMatchedException();
    }
}
