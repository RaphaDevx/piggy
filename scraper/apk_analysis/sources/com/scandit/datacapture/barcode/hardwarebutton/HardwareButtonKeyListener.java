package com.scandit.datacapture.barcode.hardwarebutton;

import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B?\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rB%\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\f\u0010\u000eJ\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/barcode/hardwarebutton/HardwareButtonKeyListener;", "Landroid/view/View$OnUnhandledKeyEventListener;", "", "keyCode", "Lkotlin/Function1;", "Lcom/scandit/datacapture/barcode/hardwarebutton/HardwareKeyEvent;", "", "onEvent", "startAction", "endAction", "Landroid/os/Handler;", "handler", "<init>", "(ILkotlin/jvm/functions/Function1;IILandroid/os/Handler;)V", "(ILkotlin/jvm/functions/Function1;)V", "Landroid/view/View;", "v", "Landroid/view/KeyEvent;", "event", "", "onUnhandledKeyEvent", "(Landroid/view/View;Landroid/view/KeyEvent;)Z", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class HardwareButtonKeyListener implements View.OnUnhandledKeyEventListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long i = ViewConfiguration.getLongPressTimeout();
    private final int a;
    private final Function1 b;
    private final int c;
    private final int d;
    private final Handler e;
    private boolean f;
    private boolean g;
    private final Runnable h;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0081\u0003\u0018\u00002\u00020\u0001R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/hardwarebutton/HardwareButtonKeyListener$Companion;", "", "", "LONG_PRESS_THRESHOLD_MS", "J", "getLONG_PRESS_THRESHOLD_MS", "()J", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final long getLONG_PRESS_THRESHOLD_MS() {
            return HardwareButtonKeyListener.i;
        }
    }

    public HardwareButtonKeyListener(int i2, Function1<? super HardwareKeyEvent, Unit> onEvent, int i3, int i4, Handler handler) {
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a = i2;
        this.b = onEvent;
        this.c = i3;
        this.d = i4;
        this.e = handler;
        this.h = new Runnable() { // from class: com.scandit.datacapture.barcode.hardwarebutton.HardwareButtonKeyListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HardwareButtonKeyListener.a(HardwareButtonKeyListener.this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(HardwareButtonKeyListener this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.g = true;
        this$0.b.invoke(HardwareKeyEvent.LONG_PRESS_START);
    }

    @Override // android.view.View.OnUnhandledKeyEventListener
    public boolean onUnhandledKeyEvent(View v, KeyEvent event) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getKeyCode() == this.a && event.getAction() == this.c && !this.f) {
            this.f = true;
            this.g = false;
            event.getEventTime();
            this.e.removeCallbacksAndMessages(null);
            this.e.postDelayed(this.h, i);
            return true;
        }
        if (event.getKeyCode() != this.a || event.getAction() != this.d || !this.f) {
            return false;
        }
        if (this.g) {
            this.b.invoke(HardwareKeyEvent.LONG_PRESS_END);
        } else {
            this.e.removeCallbacksAndMessages(null);
            this.b.invoke(HardwareKeyEvent.CLICK);
        }
        this.f = false;
        return true;
    }

    public /* synthetic */ HardwareButtonKeyListener(int i2, Function1 function1, int i3, int i4, Handler handler, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, function1, i3, i4, (i5 & 16) != 0 ? new Handler(Looper.getMainLooper()) : handler);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HardwareButtonKeyListener(int i2, Function1<? super HardwareKeyEvent, Unit> onEvent) {
        this(i2, onEvent, 0, 1, null, 16, null);
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
    }
}
