package com.scandit.datacapture.core.internal.module.device.profiles.camera;

import android.hardware.Camera;
import android.util.Range;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;

/* loaded from: classes2.dex */
public interface CameraProfile {
    Range a(Range[] rangeArr, float f);

    f a();

    void a(Camera.Parameters parameters);

    boolean a(String str);

    int b();

    Range b(Range[] rangeArr, float f);

    boolean c();

    boolean d();

    NativeFocusStrategy e();

    String f();

    boolean g();

    boolean h();

    boolean i();

    NativeCameraApi j();
}
