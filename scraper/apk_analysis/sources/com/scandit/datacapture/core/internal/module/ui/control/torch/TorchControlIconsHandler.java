package com.scandit.datacapture.core.internal.module.ui.control.torch;

import android.graphics.Bitmap;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class TorchControlIconsHandler {
    private final b a;
    private c b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;

    public TorchControlIconsHandler() {
        b defaults = new b();
        Intrinsics.checkNotNullParameter(defaults, "defaults");
        this.a = defaults;
        this.c = BitmapExtensionsKt.bitmapFromResource(defaults.a);
        this.d = BitmapExtensionsKt.bitmapFromResource(defaults.b);
        this.e = BitmapExtensionsKt.bitmapFromResource(defaults.c);
        this.f = BitmapExtensionsKt.bitmapFromResource(defaults.d);
    }

    public final void a(TorchSwitchControl.AnonymousClass1 anonymousClass1) {
        this.b = anonymousClass1;
    }

    public final void b(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.f)) {
            return;
        }
        this.f = value;
        c cVar = this.b;
        if (cVar != null) {
            cVar.onIconsChanged(TorchState.OFF, value, true);
        }
    }

    public final void c(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.c)) {
            return;
        }
        this.c = value;
        c cVar = this.b;
        if (cVar != null) {
            cVar.onIconsChanged(TorchState.ON, value, false);
        }
    }

    public final void d(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.d)) {
            return;
        }
        this.d = value;
        c cVar = this.b;
        if (cVar != null) {
            cVar.onIconsChanged(TorchState.ON, value, true);
        }
    }

    public final Bitmap e() {
        return this.e;
    }

    public final Bitmap f() {
        return this.f;
    }

    public final Bitmap g() {
        return this.c;
    }

    public final Bitmap h() {
        return this.d;
    }

    public final Bitmap a(TorchState torchState, boolean z) {
        Intrinsics.checkNotNullParameter(torchState, "torchState");
        int i = d.a[torchState.ordinal()];
        if (i == 1) {
            return !z ? this.c : this.d;
        }
        if (i == 2) {
            return !z ? this.e : this.f;
        }
        if (i == 3) {
            return !z ? this.e : this.f;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Bitmap b() {
        return BitmapExtensionsKt.bitmapFromResource(this.a.d);
    }

    public final Bitmap c() {
        return BitmapExtensionsKt.bitmapFromResource(this.a.a);
    }

    public final Bitmap d() {
        return BitmapExtensionsKt.bitmapFromResource(this.a.b);
    }

    public final void a(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.e)) {
            return;
        }
        this.e = value;
        c cVar = this.b;
        if (cVar != null) {
            cVar.onIconsChanged(TorchState.OFF, value, false);
        }
    }

    public final Bitmap a() {
        return BitmapExtensionsKt.bitmapFromResource(this.a.c);
    }
}
