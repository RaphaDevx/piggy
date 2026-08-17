package com.scandit.datacapture.core.internal.module.source;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.w, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0603w {
    public Camera a;
    public final AtomicBoolean b = new AtomicBoolean(false);
    public Camera.Parameters c;

    public final boolean a(SurfaceTexture texture) {
        Intrinsics.checkNotNullParameter(texture, "texture");
        Camera camera = this.a;
        if (camera == null) {
            return false;
        }
        try {
            camera.setPreviewTexture(texture);
            camera.setDisplayOrientation(0);
            return true;
        } catch (IOException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            return false;
        } catch (RuntimeException e2) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Either the Camera object has been released or a hardware or other low-level error occurred", e2);
            return false;
        }
    }
}
