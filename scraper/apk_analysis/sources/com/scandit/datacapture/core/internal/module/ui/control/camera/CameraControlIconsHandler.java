package com.scandit.datacapture.core.internal.module.ui.control.camera;

import android.graphics.Bitmap;
import com.scandit.datacapture.core.internal.sdk.extensions.BitmapExtensionsKt;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.ui.control.CameraSwitchControl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class CameraControlIconsHandler {
    private final Camera a;
    private final Camera b;
    private final a c;
    private b d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;

    public CameraControlIconsHandler(Camera primaryCamera, Camera secondaryCamera) {
        a defaults = new a();
        Intrinsics.checkNotNullParameter(primaryCamera, "primaryCamera");
        Intrinsics.checkNotNullParameter(secondaryCamera, "secondaryCamera");
        Intrinsics.checkNotNullParameter(defaults, "defaults");
        this.a = primaryCamera;
        this.b = secondaryCamera;
        this.c = defaults;
        this.e = BitmapExtensionsKt.bitmapFromResource(defaults.a);
        this.f = BitmapExtensionsKt.bitmapFromResource(defaults.b);
        this.g = BitmapExtensionsKt.bitmapFromResource(defaults.c);
        this.h = BitmapExtensionsKt.bitmapFromResource(defaults.d);
    }

    public final void a(CameraSwitchControl.AnonymousClass1 anonymousClass1) {
        this.d = anonymousClass1;
    }

    public final void b(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.f)) {
            return;
        }
        this.f = value;
        b bVar = this.d;
        if (bVar != null) {
            bVar.onIconsChanged(this.a, value, true);
        }
    }

    public final void c(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.g)) {
            return;
        }
        this.g = value;
        b bVar = this.d;
        if (bVar != null) {
            bVar.onIconsChanged(this.b, value, false);
        }
    }

    public final void d(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.h)) {
            return;
        }
        this.h = value;
        b bVar = this.d;
        if (bVar != null) {
            bVar.onIconsChanged(this.b, value, true);
        }
    }

    public final Bitmap e() {
        return this.e;
    }

    public final Bitmap f() {
        return this.f;
    }

    public final Bitmap g() {
        return this.g;
    }

    public final Bitmap h() {
        return this.h;
    }

    public final Bitmap a(Camera camera, boolean z) {
        Intrinsics.checkNotNullParameter(camera, "camera");
        if (Intrinsics.areEqual(camera, this.a) && !z) {
            return this.e;
        }
        if (Intrinsics.areEqual(camera, this.a) && z) {
            return this.f;
        }
        if (Intrinsics.areEqual(camera, this.b) && !z) {
            return this.g;
        }
        if (Intrinsics.areEqual(camera, this.b) && z) {
            return this.h;
        }
        return null;
    }

    public final Bitmap b() {
        return BitmapExtensionsKt.bitmapFromResource(this.c.b);
    }

    public final Bitmap c() {
        return BitmapExtensionsKt.bitmapFromResource(this.c.c);
    }

    public final Bitmap d() {
        return BitmapExtensionsKt.bitmapFromResource(this.c.d);
    }

    public final void a(Bitmap value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.e)) {
            return;
        }
        this.e = value;
        b bVar = this.d;
        if (bVar != null) {
            bVar.onIconsChanged(this.a, value, false);
        }
    }

    public final Bitmap a() {
        return BitmapExtensionsKt.bitmapFromResource(this.c.a);
    }
}
