package com.scandit.datacapture.core.internal.module.ui.control.zoom;

import android.graphics.Bitmap;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.ui.control.ZoomSwitchControl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class ZoomControlIconsHandler {
    private final a a;
    private b b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;

    public ZoomControlIconsHandler() {
        a defaults = new a();
        Intrinsics.checkNotNullParameter(defaults, "defaults");
        this.a = defaults;
        this.c = BitmapExtensionsKt.bitmapFromResource(defaults.a);
        this.d = BitmapExtensionsKt.bitmapFromResource(defaults.b);
        this.e = BitmapExtensionsKt.bitmapFromResource(defaults.c);
        this.f = BitmapExtensionsKt.bitmapFromResource(defaults.d);
    }

    public final void a(ZoomSwitchControl.AnonymousClass1 anonymousClass1) {
        this.b = anonymousClass1;
    }

    public final void b(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.f)) {
            return;
        }
        this.f = value;
        b bVar = this.b;
        if (bVar != null) {
            bVar.onIconsChanged(ZoomSwitchControl.ZoomState.ZoomedIn.INSTANCE, true);
        }
    }

    public final void c(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.c)) {
            return;
        }
        this.c = value;
        b bVar = this.b;
        if (bVar != null) {
            bVar.onIconsChanged(ZoomSwitchControl.ZoomState.ZoomedOut.INSTANCE, false);
        }
    }

    public final void d(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.d)) {
            return;
        }
        this.d = value;
        b bVar = this.b;
        if (bVar != null) {
            bVar.onIconsChanged(ZoomSwitchControl.ZoomState.ZoomedOut.INSTANCE, true);
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

    public final Bitmap a(ZoomSwitchControl.ZoomState state, boolean z) {
        Intrinsics.checkNotNullParameter(state, "state");
        boolean z2 = state instanceof ZoomSwitchControl.ZoomState.ZoomedOut;
        if (z2 && !z) {
            return this.c;
        }
        if (z2 && z) {
            return this.d;
        }
        boolean z3 = state instanceof ZoomSwitchControl.ZoomState.ZoomedIn;
        if (z3 && !z) {
            return this.e;
        }
        if (z3 && z) {
            return this.f;
        }
        return null;
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
        b bVar = this.b;
        if (bVar != null) {
            bVar.onIconsChanged(ZoomSwitchControl.ZoomState.ZoomedIn.INSTANCE, false);
        }
    }

    public final Bitmap a() {
        return BitmapExtensionsKt.bitmapFromResource(this.a.c);
    }
}
