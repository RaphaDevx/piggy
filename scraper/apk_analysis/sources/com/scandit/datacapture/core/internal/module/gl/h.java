package com.scandit.datacapture.core.internal.module.gl;

import android.graphics.SurfaceTexture;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements SurfaceTexture.OnFrameAvailableListener {
    public final l a;

    public h(l parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.a = parent;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Intrinsics.checkNotNullParameter(surfaceTexture, "surfaceTexture");
        l.a(this.a, surfaceTexture);
        Function0 function0 = this.a.u;
        if (function0 != null) {
            function0.invoke();
        }
    }
}
